package ma.fgs.product.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CART")
public class Cart {

	@Id
	@GeneratedValue
	private Long id;

	// Add quantity of products in cart
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable( name = "CART_CART_PRODUCT", 
				joinColumns = @JoinColumn(name = "CART_ID"), 
				inverseJoinColumns = @JoinColumn(name = "CART_PRODUCT_ID"))
	private List<CartProduct> products;
	
	@OneToOne
	@JoinColumn(name = "USER_ID", unique = true)
	private User user;

	private Double totalPrice;

	public Cart(Long id, List<CartProduct> products, Double totalPrice, User user) {
		super();
		this.id = id;
		this.products = products;
		this.totalPrice = totalPrice;
		this.user = user;
	}

	public Cart() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<CartProduct> getProducts() {
		return products;
	}

	public void setProducts(List<CartProduct> products) {
		this.products = products;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		result = prime * result + ((totalPrice == null) ? 0 : totalPrice.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		if (totalPrice == null) {
			if (other.totalPrice != null)
				return false;
		} else if (!totalPrice.equals(other.totalPrice))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
}
