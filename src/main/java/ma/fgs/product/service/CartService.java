package ma.fgs.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.fgs.product.domain.Cart;
import ma.fgs.product.domain.CartProduct;
import ma.fgs.product.domain.User;
import ma.fgs.product.domain.dto.CartDto;
import ma.fgs.product.repository.CartProductRepository;
import ma.fgs.product.repository.CartRepository;
import ma.fgs.product.repository.UserRepository;
import ma.fgs.product.service.api.ICartService;
import ma.fgs.product.service.exception.NotFoundException;

@Service
public class CartService implements ICartService {

	@Autowired
	private CartRepository repo;

	@Autowired
	private CartProductRepository cartProductRepository;

	@Autowired
	private UserRepository userRepository;

	boolean foundProduct = false;

	@Override
	public Cart addToCart(CartDto dto) {

		double totalPrice = 0;
		foundProduct = false;
		Cart cart = repo.findByUserAccountUsername(dto.getUsername());

		if (cart != null) {
			List<CartProduct> cartProducts = cart.getProducts();

			// If product already exist in cart, increment quantity
			cartProducts.forEach(p -> {
				if (p.getProduct().equals(dto.getProduct())) {
					foundProduct = true;
					p.setQuantity(p.getQuantity() + 1);
				}
			});

			// else add new product to cart
			if (!foundProduct) {
				CartProduct cartProduct = new CartProduct();
				cartProduct.setProduct(dto.getProduct());
				cartProduct.setQuantity(1);
				cartProducts.add(cartProduct);
			}

			for (CartProduct cProduct : cart.getProducts()) {
				totalPrice += cProduct.getProduct().getPrice();
			}

			cart.setTotalPrice(totalPrice);
			return repo.save(cart);

		} else {

			cart = new Cart();
			String username = dto.getUsername();
			User user = userRepository.findByAccountUsername(username);

			List<CartProduct> products = new ArrayList<>();
			CartProduct cartProduct = new CartProduct();
			cartProduct.setProduct(dto.getProduct());
			cartProduct.setQuantity(1);
			products.add(cartProduct);

			totalPrice = products.get(0).getProduct().getPrice();

			cart.setUser(user);
			cart.setProducts(products);
			cart.setTotalPrice(totalPrice);
			return repo.save(cart);
		}
	}

	@Override
	public Cart minusProductFromCart(CartDto dto) throws NotFoundException {
		Cart cart = repo.findByUserAccountUsername(dto.getUsername());

		if (cart != null) {
			List<CartProduct> cartProducts = new ArrayList<>(cart.getProducts());
			cartProducts.forEach(cartProduct -> {
				if (cartProduct.getProduct() != null
						&& cartProduct.getProduct().getId().equals(dto.getProduct().getId())) {
					if (cartProduct.getQuantity() > 1)
						cartProduct.setQuantity(cartProduct.getQuantity() - 1);
					else {
						cartProductRepository.delete(cartProduct.getId());
					}
				}
			});
		} else {
			throw new NotFoundException("NOT.FOUND", "Cart not found for user with username: " + dto.getUsername());
		}
		return repo.save(cart);
	}

	@Override
	public Cart findCart(long id) throws NotFoundException {
		if (!repo.exists(id))
			throw new NotFoundException("code", "message");
		return repo.findOne(id);
	}

	@Override
	public List<Cart> findAllCarts() {
		return repo.findAll();
	}

	@Override
	public void deleteCart(long id) throws NotFoundException {
		if (!repo.exists(id))
			throw new NotFoundException("code", "message");
		repo.delete(id);
	}

	@Override
	public Cart findByUserId(Long userId) throws NotFoundException {
		Cart cart = repo.findByUserId(userId);
		if (cart == null)
			throw new NotFoundException("USER.CART.NOT.FOUND", "No cart found for user with id: " + userId);
		return cart;
	}

	@Override
	public List<Cart> searchCarts(Cart cartDto) throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProductFromCart(Long productid, String username) {
		Cart target = repo.findByUserAccountUsername(username);
		List<CartProduct> cartProducts = target.getProducts();
		
		if(!cartProducts.isEmpty()) {
			Optional<CartProduct> match = cartProducts.stream()
				.filter(cp -> (cp.getProduct().getId() == productid))
				.findFirst();
			target.getProducts().set(cartProducts.indexOf(match.get()), null);
		}
		repo.save(target);
	}

}
