package ma.fgs.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.fgs.product.domain.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
//
//	Cart findByUserAccountUsername(String username);
//
//	Cart findByUserId(Long userId);
	
}
	
