package com.juan.bus.payload.request;

import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignupRequest {
	@NotBlank
	@Size(min = 3, max = 50)
	private String username;
	
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	
	@NotBlank
	@Size(max = 120)
	private String firstName;
	
	@NotBlank
	@Size(max = 120)
	private String lastName;
	
	@NotBlank
	@Size(max = 120)
	private String mobileNumber;
	
	public SignupRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SignupRequest(@NotBlank @Size(min = 3, max = 50) String username,
			@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 120) String firstName,
			@NotBlank @Size(max = 120) String lastName, @NotBlank @Size(max = 120) String mobileNumber,
			Set<String> role, @NotBlank @Size(min = 6, max = 40) String password) {
		super();
		this.username = username;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.role = role;
		this.password = password;
	}

	@Override
	public String toString() {
		return "SignupRequest [username=" + username + ", email=" + email + ", firstName=" + firstName + ", lastName="
				+ lastName + ", mobileNumber=" + mobileNumber + ", role=" + role + ", password=" + password
				+ ", hashCode()=" + hashCode() + ", getUsername()=" + getUsername() + ", getEmail()=" + getEmail()
				+ ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName() + ", getMobileNumber()="
				+ getMobileNumber() + ", getRole()=" + getRole() + ", getPassword()=" + getPassword() + ", getClass()="
				+ getClass() + ", toString()=" + super.toString() + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, lastName, mobileNumber, password, role, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SignupRequest other = (SignupRequest) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(mobileNumber, other.mobileNumber)
				&& Objects.equals(password, other.password) && Objects.equals(role, other.role)
				&& Objects.equals(username, other.username);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private Set<String> role;
	
	@NotBlank
	@Size(min = 6, max = 40)
	private String password;
}
