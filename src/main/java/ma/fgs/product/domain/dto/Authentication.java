package ma.fgs.product.domain.dto;

import ma.fgs.product.domain.User;

public class Authentication {

	private User user;

	private Boolean authenticated;
	
	private String token;

	public Authentication() {

	}

	public Authentication(User user, Boolean authenticated, String token) {
		super();
		this.user = user;
		this.authenticated = authenticated;
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(Boolean authenticated) {
		this.authenticated = authenticated;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
