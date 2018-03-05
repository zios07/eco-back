package ma.fgs.product.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.fgs.product.service.api.IAccountService;
import ma.fgs.product.domain.Account;
import ma.fgs.product.service.exception.NotFoundException;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {

	@Autowired
	private IAccountService service;
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Account> findAccount(@PathVariable long id) throws NotFoundException {
		Account account = service.findAccount(id);
		return new ResponseEntity<>(account, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Account>> findAllAccounts() {
		List<Account> accounts = service.findAllAccounts();
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Account> addAccount(@RequestBody Account account) {
		Account savedAccount =  service.addAccount(account);
		return new ResponseEntity<Account>(savedAccount, HttpStatus.CREATED);	
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Void> deleteAccount(@PathVariable long id) throws NotFoundException {
		service.deleteAccount(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/search")
	public ResponseEntity<List<Account>> searchAccounts(@RequestBody Account accountDto) throws NotFoundException {
		List<Account> accounts = service.searchAccounts(accountDto);
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	} 
	
	
}
