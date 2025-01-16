package com.project.BankingSystem.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


@Entity
public class Branch {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(length = 20, nullable = false)
	private String name;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Admin manager;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable = false, name = "aid")
	private Address address;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Account> accounts;

	@Enumerated(EnumType.STRING)
	private Status status = Status.Active;

	public Branch(String name, Admin manager, Address address, List<Account> accounts) {
		super();
		this.name = name;
		this.manager = manager;
		this.address = address;
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return "Branch_id : " + id + "\nName : " + name + "\nManager : " + manager.getName() + "\nAddress : " + address +"\nStatus : " + status +  "\nAccounts:- \n"
		    	+ accounts +"\n";
	}
	
	
	
}
