package ma.fgs.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.fgs.product.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByAccountId(Long accountID);
	User findByAccountUsername(String username);

	
}	
