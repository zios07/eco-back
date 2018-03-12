package ma.fgs.product.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.fgs.product.domain.Account;
import ma.fgs.product.security.TokenHandler;
import ma.fgs.product.service.api.IAuthenticationService;
import ma.fgs.product.service.exception.BadCredentialsException;
import ma.fgs.product.service.exception.NotFoundException;

@RestController
@RequestMapping("api/v1/authentication")
public class AuthenticationController {

	@Autowired 
	private IAuthenticationService service;

	private TokenHandler handler = new TokenHandler();

	@PostMapping(value = "authenticate")
	public ResponseEntity<ResponseToken> authenticate(@RequestBody Account accountCredentiels)
			throws NotFoundException, BadCredentialsException {
		Account account = service.authenticate(accountCredentiels);
		ResponseToken token = null;
		if (account != null)
			token = new ResponseToken((handler.build(account.getUsername())));
		return new ResponseEntity<ResponseToken>(token, HttpStatus.OK);
	}

}

class ResponseToken {

	private String token;

	public ResponseToken() {

	}

	public ResponseToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
