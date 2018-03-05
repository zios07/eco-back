package ma.fgs.product.service.api;

import ma.fgs.product.service.exception.NotFoundException;

public interface ICartProductService {

	void deleteCartProduct(Long id) throws NotFoundException;
	
}
