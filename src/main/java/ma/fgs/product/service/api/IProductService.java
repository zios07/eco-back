package ma.fgs.product.service.api;

import ma.fgs.product.domain.Product;
import ma.fgs.product.domain.dto.ProductSearchDto;
import ma.fgs.product.service.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IProductService {

	Product addProduct(Product product);

	Product findProduct(String id) throws NotFoundException;

	Page<Product> findAllProducts(int page, int size);

	void deleteProduct(String id) throws NotFoundException;

	Page<Product> searchProducts(ProductSearchDto dto, int page, int size) throws NotFoundException;

	void uploadProductPhotos(MultipartFile[] photos, String uuid) throws IOException;

  void updateProductIdForPhoto(String uuid, String newProductId);

}
