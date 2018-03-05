package ma.fgs.product.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.fgs.product.domain.Product;
import ma.fgs.product.service.api.IProductService;
import ma.fgs.product.service.exception.NotFoundException;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

	@Autowired
	private IProductService service;
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Product> findProduct(@PathVariable long id) throws NotFoundException {
		Product product = service.findProduct(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> findAllProducts() {
		List<Product> products = service.findAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		Product savedProduct =  service.addProduct(product);
		return new ResponseEntity<Product>(savedProduct, HttpStatus.CREATED);	
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable long id) throws NotFoundException {
		service.deleteProduct(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/search")
	public ResponseEntity<List<Product>> searchProducts(@RequestBody Product productDto) throws NotFoundException {
		List<Product> products = service.searchProducts(productDto);
		return new ResponseEntity<>(products, HttpStatus.OK);
	} 
	
}
