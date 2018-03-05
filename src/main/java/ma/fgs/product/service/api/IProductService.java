package ma.fgs.product.service.api;

import java.util.List;

import ma.fgs.product.domain.Product;
import ma.fgs.product.service.exception.NotFoundException;

public interface IProductService {

	Product addProduct(Product product);

	Product findProduct(long id) throws NotFoundException;

	List<Product> findAllProducts();

	void deleteProduct(long id) throws NotFoundException;

	List<Product> searchProducts(Product productDto) throws NotFoundException;

}
