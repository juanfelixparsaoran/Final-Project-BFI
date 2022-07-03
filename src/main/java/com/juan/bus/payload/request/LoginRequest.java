package com.juan.bus.payload.request;

import java.util.Objects;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;

	public LoginRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginRequest(@NotBlank String username, @NotBlank String password) {
		super();
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginRequest [username=" + username + ", password=" + password + ", hashCode()=" + hashCode()
				+ ", getUsername()=" + getUsername() + ", getPassword()=" + getPassword() + ", getClass()=" + getClass()
				+ ", toString()=" + super.toString() + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginRequest other = (LoginRequest) obj;
		return Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
