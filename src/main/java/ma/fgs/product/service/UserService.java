package ma.fgs.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.fgs.product.domain.User;
import ma.fgs.product.repository.AccountRepository;
import ma.fgs.product.repository.UserRepository;
import ma.fgs.product.service.api.IUserService;
import ma.fgs.product.service.exception.NotFoundException;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public User addUser(User user) {
		return repo.save(user);
	}

	@Override
	public User findUser(long id) throws NotFoundException {
		if(!repo.exists(id))
			throw new NotFoundException("code", "message");
		return repo.findOne(id);
	}

	@Override
	public List<User> findAllUsers() {
		return repo.findAll();
	}

	@Override
	public void deleteUser(long id) throws NotFoundException {
		if(!repo.exists(id))
			throw new NotFoundException("code", "message");
		repo.delete(id);
	}

	@Override
	public List<User> searchUsers(User userDto) throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(User user) throws NotFoundException {
		if(!repo.exists(user.getId()))
			throw new NotFoundException("code", "message");
		return repo.save(user);
	}

	@Override
	public User findUserByUsername(String username) throws NotFoundException {
		User user = repo.findByAccountUsername(username);
		if(user == null)
			throw new NotFoundException("NOT.FOUND", "User with username: " + username + " not found");
		return user;
	}

}
