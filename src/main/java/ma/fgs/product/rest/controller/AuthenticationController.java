package ma.fgs.product.rest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.fgs.product.domain.Account;
import ma.fgs.product.service.exception.BadCredentialsException;
import ma.fgs.product.service.exception.NotFoundException;

@RestController
@RequestMapping("api/v1/authentication")
public class AuthenticationController {

	@PostMapping(value = "authenticate")
	public String authenticate(@RequestBody Account accountCredentiels)
			throws NotFoundException, BadCredentialsException {
//		Account account = service.authenticate(accountCredentiels);
//		ResponseToken token = null;
//		if (account != null)
//			token = new ResponseToken((handler.build(account.getUsername())));
//		return new ResponseEntity<ResponseToken>(token, HttpStatus.OK);
		return "OK";
	}

}
