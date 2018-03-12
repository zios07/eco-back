package ma.fgs.product.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.fgs.product.domain.Brand;
import ma.fgs.product.service.api.IBrandService;

@RestController
@RequestMapping(value = "/api/v1/brand")
public class BrandController {

	@Autowired
	private IBrandService service;
	
	@GetMapping
	public List<Brand> loadBrands() {
		return service.findAllBrands();
	}
	
}
