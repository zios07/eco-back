package ma.fgs.product.service.api;

import org.springframework.data.domain.Page;

import ma.fgs.product.domain.Product;
import ma.fgs.product.domain.dto.ProductSearchDto;
import ma.fgs.product.service.exception.NotFoundException;

public interface IProductService {

	Product addProduct(Product product);

	Product findProduct(long id) throws NotFoundException;

	Page<Product> findAllProducts(int page, int size);

	void deleteProduct(long id) throws NotFoundException;

	Page<Product> searchProducts(ProductSearchDto dto, int page, int size) throws NotFoundException;

}
