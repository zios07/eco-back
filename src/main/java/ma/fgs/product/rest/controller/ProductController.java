package ma.fgs.product.rest.controller;

import ma.fgs.product.domain.Product;
import ma.fgs.product.domain.dto.ProductSearchDto;
import ma.fgs.product.service.api.IProductService;
import ma.fgs.product.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

	@Autowired
	private IProductService service;

	@GetMapping(value = "{id}")
	public ResponseEntity<Product> findProduct(@PathVariable String id) throws NotFoundException {
		Product product = service.findProduct(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Page<Product>> findAllProducts(@RequestParam(required = false) int page,
			@RequestParam(required = false) int size) {
		Page<Product> products = service.findAllProducts(page, size);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@PostMapping(value = "add/photo/upload")
	public ResponseEntity<?> uploadProductPhotos(@RequestParam("photos") MultipartFile[] photos,
			@RequestParam("uuid") String uuid) throws IOException {
		service.uploadProductPhotos(photos, uuid);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PostMapping
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		Product savedProduct = service.addProduct(product);
    return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable String id) throws NotFoundException {
		service.deleteProduct(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(value = "/search")
	public ResponseEntity<Page<Product>> searchProducts(@RequestBody ProductSearchDto dto, @RequestParam int page,
			@RequestParam int size) throws NotFoundException {
		Page<Product> products = service.searchProducts(dto, page, size);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

}
