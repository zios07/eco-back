package ma.fgs.product.service.api;

import java.util.List;

import ma.fgs.product.domain.Role;
import ma.fgs.product.service.exception.ServiceException;

public interface IRoleService {

	Role findRoleByUsername(String username) throws ServiceException;

	Role findById(Long id);
	
	List<Role> findAll();

}
