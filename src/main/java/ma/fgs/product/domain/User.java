package ma.fgs.product.domain;

import java.time.LocalDate;
import java.util.Arrays;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "USER_TABLE")
public class User {

	@Id
	@GeneratedValue
	private Long id;

	private String email;

	private String firstName;

	private String lastName;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate bDate;

	@OneToOne(cascade = CascadeType.ALL)
	private Account account;

	@ManyToOne
	private Role role;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "CART_ID")
	private Cart cart;

	private byte[] photo;

	public User() {
		super();
	}

	public User(Long id, String email, String firstName, String lastName, LocalDate bDate, Account account, Role role,
			byte[] photo, Cart cart) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.bDate = bDate;
		this.account = account;
		this.role = role;
		this.photo = photo;
		this.cart = cart;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getbDate() {
		return bDate;
	}

	public void setbDate(LocalDate bDate) {
		this.bDate = bDate;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", bDate=" + bDate + ", account=" + account + ", role=" + role + ", cart=" + cart + ", photo="
				+ Arrays.toString(photo) + "]";
	}

}
