package com.project.BankingSystem.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Single table for both Admin and User
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING) // Column storing types
@DiscriminatorValue("Admin")
public class Admin {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(length=20,nullable=false)
	private String name;
	
	@Column(length=50,nullable=false,unique=true)
	private String email;
	
	@Column(length=10,nullable=false,unique=true)
	private long contact;
	
	@Column(length=50,nullable=false,unique=true)
	private String userName;
	
	@Column(length=20,nullable=false)
	private String password;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="address_id")
	private Address address;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 20)  // Ensure it has a name and proper length
	private Status status = Status.Active;

	public Admin(String name, String email, long contact, String userName, String password, Address address) {
		super();
		this.name = name;
		this.email = email;
		this.contact = contact;
		this.userName = userName;
		this.password = password;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Admin_id : " + id + "\nName : " + name + "\nEmail : " + email + "\nContact : " + contact + "\nUserName : "
				+ userName + "\nPassword : " + password + "\nAddress : " + address + "\nStatus : " + status + "\n";
	}
	

	
	
	
	
}
