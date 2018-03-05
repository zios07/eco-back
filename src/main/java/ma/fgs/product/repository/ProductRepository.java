package ma.fgs.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.fgs.product.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
