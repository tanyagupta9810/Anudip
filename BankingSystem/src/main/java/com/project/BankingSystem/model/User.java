package com.project.BankingSystem.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@DiscriminatorValue("User") 
public class User extends  Admin {

	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="account_id")
	private List<Account> account;
	

	public User(String name, String email, long contact, String userName, String password, Address address,
			List<Account> account) {
		super(name, email, contact, userName, password, address);
		this.account = account;
	}


	@Override
	public String toString() {
		return  "User_id : " + getId() + "\nName : "+ getName() + "\nEmail : " + getEmail() + "\nContact : " + getContact() + "\nUserName : "
				+ getUserName() + "\nPassword : " + getPassword() + "\nAddress : " + getAddress()
				+ "\nStatus : " + getStatus() + "\nAccounts :- \n" + account +"\n";
	}


	
	
}
