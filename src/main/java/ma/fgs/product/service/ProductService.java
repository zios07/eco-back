package ma.fgs.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.fgs.product.domain.Product;
import ma.fgs.product.repository.ProductRepository;
import ma.fgs.product.service.api.IProductService;
import ma.fgs.product.service.exception.NotFoundException;

@Service
public class ProductService implements IProductService {
	
	@Autowired
	private ProductRepository repo;

	@Override
	public Product addProduct(Product product) {
		return repo.save(product);
	}

	@Override
	public Product findProduct(long id) throws NotFoundException {
		if(!repo.exists(id))
			throw new NotFoundException("code", "message");
		return repo.findOne(id);
	}

	@Override
	public List<Product> findAllProducts() {
		return repo.findAll();
	}

	@Override
	public void deleteProduct(long id) throws NotFoundException {
		if(!repo.exists(id))
			throw new NotFoundException("code", "message");
		repo.delete(id);
	}

	@Override
	public List<Product> searchProducts(Product productDto) throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
