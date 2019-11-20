package com.example.learn.model;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty @Column(name="username", length = 50, nullable = false, unique = true)
	private String username;
	@Size(min=2, message="First name should have atleast 2 characters")
	@Column(name="firstname", length = 50, nullable = false)
	private String firstname;
	@Column(name="lastname", length = 50, nullable = false)
	private String lastname;
	@Column(name="email", length = 50, nullable = false)
	private String email;
	@Column(name="role", length = 50, nullable = false)
	private String role;
	@Column(name="ssn", length = 50, nullable = false, unique = true)
	private String ssn;
	
//	@OneToOne(mappedBy = "user")
//	private Order order;

	 @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
		    private List<Order> order ;
	
	public User() {
	}

	public User(Long id, String username, String firstname, String lastname, String email, String role, String ssn) {
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
	}

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
	}
	
	//	public Order getOrder() {
	//	return order;
	//}
	//public void setOrder(Order order) {
	//	this.order = order;
	//}
	
	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}


	
}
