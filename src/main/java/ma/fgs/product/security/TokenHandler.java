package ma.fgs.product.security;

import static ma.fgs.product.security.utils.SecurityConstants.EXPIRATION_TIME;
import static ma.fgs.product.security.utils.SecurityConstants.SECRET;
import static ma.fgs.product.security.utils.SecurityConstants.TOKEN_PREFIX;
import static ma.fgs.product.service.utils.UtilContants.ROLE_CLAIM;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenHandler {
	
	final long EXPIRATIONTIME = 5 * 60 * 1000;// 10 days

	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * Generate a token from the username.
	 * 
	 * @param username
	 *            The subject from which generate the token.
	 * 
	 * @return The generated token.
	 */
	public String build(String username) {
		UserDetails user = userDetailsService.loadUserByUsername(username);
		String role = user.getAuthorities().iterator().next().getAuthority();
		
		return Jwts.builder()
                .setSubject(username)
                .claim(ROLE_CLAIM, role)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();

	}

	/**
	 * Parse a token and extract the subject (username).
	 * 
	 * @param token
	 *            A token to parse.
	 * 
	 * @return The subject (username) of the token.
	 */
	public String parse(String token) {

		String username = extractClaims(token).getSubject();

		return userDetailsService.loadUserByUsername(username).getUsername();

	}

	/**
	 * Parse a token and extract the claims .
	 * 
	 * @param token
	 *            A token to parse.
	 * 
	 * @return The claims of the token.
	 */
	public Claims extractClaims(String token) {

		return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();

	}

}
