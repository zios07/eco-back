package ma.fgs.product.domain.dto;

import ma.fgs.product.domain.Product;

public class CartDto {

	private String username;
	
	private Product product;

	public CartDto() {
		
	}
	
	public CartDto(String username, Product product) {
		super();
		this.username = username;
		this.product = product;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
