package ma.fgs.product.service.api;

import java.util.List;

import ma.fgs.product.domain.Brand;
import ma.fgs.product.service.exception.NotFoundException;

public interface IBrandService {

	Brand addBrand(Brand brand);

	Brand findBrand(long id) throws NotFoundException;

	List<Brand> findAllBrands();

	void deleteBrand(long id) throws NotFoundException;

	List<Brand> searchBrands(Brand brandtDto) throws NotFoundException;

}
