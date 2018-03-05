package ma.fgs.product.service.api;

import java.util.List;

import ma.fgs.product.domain.Account;
import ma.fgs.product.service.exception.NotFoundException;

public interface IAccountService {

	Account addAccount(Account accountd);

	Account findAccount(long id) throws NotFoundException;

	List<Account> findAllAccounts();

	void deleteAccount(long id) throws NotFoundException;

	List<Account> searchAccounts(Account accountdtDto) throws NotFoundException;
	
	Account findAccountByUsername(String username) throws NotFoundException;
}
