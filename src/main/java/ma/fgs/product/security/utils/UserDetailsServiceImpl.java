package ma.fgs.product.security.utils;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ma.fgs.product.domain.Account;
import ma.fgs.product.service.AccountService;
import ma.fgs.product.service.exception.NotFoundException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private AccountService service;
	
	public UserDetailsServiceImpl(AccountService service) {
		this.service = service;
	}
		
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = null;

		try {
			account = service.findAccountByUsername(username);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new User(username, account.getPassword(), Collections.emptyList());

	}
}