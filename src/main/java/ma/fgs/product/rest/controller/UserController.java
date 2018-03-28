package ma.fgs.product.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.fgs.product.domain.User;
import ma.fgs.product.service.api.IUserService;
import ma.fgs.product.service.exception.NotFoundException;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

	@Autowired
	private IUserService service;

	@GetMapping(value = "{id}")
	public ResponseEntity<User> findUser(@PathVariable long id) throws NotFoundException {
		User user = service.findUser(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<User>> findAllUsers() {
		List<User> users = service.findAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PostMapping("register")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		User savedUser = service.addUser(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<User> updateUser(@RequestBody User user) throws NotFoundException {
		User updatedUser = service.updateUser(user);
		return new ResponseEntity<User>(updatedUser, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable long id) throws NotFoundException {
		service.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(value = "/search")
	public ResponseEntity<List<User>> searchUsers(@RequestBody User userDto) throws NotFoundException {
		List<User> users = service.searchUsers(userDto);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

}
