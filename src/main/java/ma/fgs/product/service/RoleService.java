package ma.fgs.product.service;

import static ma.fgs.product.service.utils.UtilContants.ROLE_CODE_ADMIN;
import static ma.fgs.product.service.utils.UtilContants.ROLE_CODE_USER;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.fgs.product.domain.Role;
import ma.fgs.product.domain.User;
import ma.fgs.product.repository.RoleRepository;
import ma.fgs.product.repository.UserRepository;
import ma.fgs.product.service.api.IRoleService;
import ma.fgs.product.service.exception.ServiceException;

@Service
public class RoleService implements IRoleService {
	
	@Autowired
	private RoleRepository repo;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Role findRoleByUsername(String username) throws ServiceException {
		User user = userRepository.findByAccountUsername(username);
		if(user == null)
			throw new ServiceException("INVALID.USERNAME", "Invalid username");
		
		return user.getRole();
	}

	@Override
	public Role findById(Long id) {
		return repo.findOne(id);
	}

	@Override
	public List<Role> findAll() {
		return repo.findAll();
	}

	@Override
	public Role getRoleUser() {
		return repo.findByRoleCode(ROLE_CODE_USER);
	}

	@Override
	public Role getRoleAdmin() {
		return repo.findByRoleCode(ROLE_CODE_ADMIN);
	}

}
