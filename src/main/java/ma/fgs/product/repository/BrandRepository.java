package ma.fgs.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.fgs.product.domain.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

}
