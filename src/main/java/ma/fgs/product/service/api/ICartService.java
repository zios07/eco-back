package ma.fgs.product.service.api;

import java.util.List;

import ma.fgs.product.domain.Cart;
import ma.fgs.product.domain.dto.CartDto;
import ma.fgs.product.service.exception.NotFoundException;

public interface ICartService {

	Cart addToCart(CartDto cart);
	
	Cart minusProductFromCart(CartDto cart) throws NotFoundException;

	Cart findCart(long id) throws NotFoundException;

	List<Cart> findAllCarts();

	void deleteCart(long id) throws NotFoundException;

	List<Cart> searchCarts(Cart cartDto) throws NotFoundException;

	Cart findByUserId(Long userId) throws NotFoundException;

	void deleteProductFromCart(String productId, String username);

	Cart findByUsername(String username) throws NotFoundException;

}
