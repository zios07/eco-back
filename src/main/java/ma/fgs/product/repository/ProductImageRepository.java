package ma.fgs.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.fgs.product.domain.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

}
