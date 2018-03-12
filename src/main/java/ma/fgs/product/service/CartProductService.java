package ma.fgs.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.fgs.product.domain.CartProduct;
import ma.fgs.product.repository.CartProductRepository;
import ma.fgs.product.service.api.ICartProductService;
import ma.fgs.product.service.exception.NotFoundException;

@Service
public class CartProductService implements ICartProductService {

	@Autowired
	private CartProductRepository  cartProductRepo;

	@Override
	public void deleteCartProduct(Long id) throws NotFoundException {
 		if(!cartProductRepo.exists(id))
			throw new NotFoundException("NOT.FOUND", "Cart product not found");
 		CartProduct cp = cartProductRepo.findOne(id);
		cartProductRepo.delete(cp);
	}
	
}
