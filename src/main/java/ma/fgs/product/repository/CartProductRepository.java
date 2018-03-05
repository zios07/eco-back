package ma.fgs.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.fgs.product.domain.CartProduct;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, Long> {

}
