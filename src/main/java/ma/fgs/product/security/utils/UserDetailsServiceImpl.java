package ma.fgs.product.security.utils;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ma.fgs.product.domain.Account;
import ma.fgs.product.domain.User;
import ma.fgs.product.service.api.IUserService;
import ma.fgs.product.service.exception.NotFoundException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private IUserService userService;

	public UserDetailsServiceImpl(IUserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = null;
		User user = null;
		try {
			user = userService.findUserByUsername(username);
			account = user.getAccount();
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		
		return new org.springframework.security.core.userdetails.User(username, account.getPassword(), Arrays.asList(new SimpleGrantedAuthority(user.getRole().getRoleLabel())));

	}
}