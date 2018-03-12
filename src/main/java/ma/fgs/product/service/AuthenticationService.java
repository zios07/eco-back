package ma.fgs.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.fgs.product.domain.Account;
import ma.fgs.product.repository.AccountRepository;
import ma.fgs.product.service.api.IAuthenticationService;
import ma.fgs.product.service.exception.BadCredentialsException;
import ma.fgs.product.service.exception.NotFoundException;

@Service
public class AuthenticationService implements IAuthenticationService {

	@Autowired 
	private AccountRepository accountRepo;
	
	@Override
	public Account authenticate(Account credentials) throws NotFoundException, BadCredentialsException {
		Account account = accountRepo.findByUsername(credentials.getUsername());
		if(account == null || (account.getPassword() != null && !account.getPassword().equals(credentials.getPassword())))
			throw new BadCredentialsException("AUTHENTICATION.ERROR", "Bad credentials");
		return account;
	}

}
