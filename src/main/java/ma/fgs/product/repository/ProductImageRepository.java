package ma.fgs.product.repository;

import ma.fgs.product.domain.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

  @Modifying
  @Query("update ProductImage pi set pi.productId = ?2 where pi.productId = ?1")
  int updateProductId(String uuid, String newProductId);

  Set<ProductImage> findByProductId(String id);

  @Query("select pi from ProductImage pi where pi.main = true")
  List<ProductImage> findMainImages();
}
