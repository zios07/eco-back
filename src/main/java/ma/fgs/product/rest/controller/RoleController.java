package ma.fgs.product.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.fgs.product.domain.Role;
import ma.fgs.product.service.api.IRoleService;
import ma.fgs.product.service.exception.ServiceException;

@RestController
@RequestMapping("api/v1/roles")
public class RoleController {

	@Autowired
	private IRoleService service;
	
	@RequestMapping
	public Role findRoleByUsername(@RequestParam String username) throws ServiceException {
		return service.findRoleByUsername(username);
	}
	
}
