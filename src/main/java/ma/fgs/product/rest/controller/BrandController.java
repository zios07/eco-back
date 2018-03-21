package ma.fgs.product.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.fgs.product.domain.Brand;
import ma.fgs.product.service.api.IBrandService;
import ma.fgs.product.service.exception.NotFoundException;

@RestController
@RequestMapping(value = "/api/v1/brand")
public class BrandController {

	@Autowired
	private IBrandService service;
	
	@GetMapping
	public List<Brand> loadBrands() {
		return service.findAllBrands();
	}
	
	@GetMapping(value = "{id}")
	public Brand getBrand(@PathVariable Long id) throws NotFoundException {
		return service.findBrand(id);
	}
	
	@PostMapping
	public Brand saveBrand(@RequestBody Brand brand) {
		return service.addBrand(brand);
	}
	
	@DeleteMapping(value = "{id}")
	public void deleteBrand(@PathVariable Long id) throws NotFoundException {
		service.deleteBrand(id);
	}
}
