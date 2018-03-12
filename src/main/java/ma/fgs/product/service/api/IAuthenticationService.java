package ma.fgs.product.service.api;

import ma.fgs.product.domain.Account;
import ma.fgs.product.service.exception.BadCredentialsException;
import ma.fgs.product.service.exception.NotFoundException;

public interface IAuthenticationService {

	Account authenticate(Account account) throws NotFoundException, BadCredentialsException;
}
