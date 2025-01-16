package com.project.BankingSystem.model;

import java.time.LocalDate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
//(name="account")
public class Account {

	@Id
	@GeneratedValue
	private int id;
	
	@Enumerated(EnumType.STRING)
	private AccountType type;
	
	@Column(nullable=false)
	private double balance;
	
	private LocalDate createdOn;
	
	@Column(unique=true,nullable=false,length=12)
	private String account_number;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(nullable=true,name="card_id")
	private Card card;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(nullable=true,name="branch_id")
	private Branch branch;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(nullable=false,name="user_id")
	private User user;
	
	@Enumerated(EnumType.STRING)
	private Status status=Status.Active;

	public Account(AccountType type, double balance, LocalDate createdOn, String account_number, Card card, Branch branch,
			User user) {
		super();
		this.type = type;
		this.balance = balance;
		this.createdOn = createdOn;
		this.account_number = account_number;
		this.card = card;
		this.branch = branch;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Account_id : " + id + "\nType :" + type + "\nBalance : " + balance + "\nCreatedOn : " + createdOn
				+ "\nAccount_number : " + account_number + "\nCard : " + (card!=null?card.getNo():"null") + "\nBranch : " + branch.getName() + "\nAccount Holder Name : " + user.getName()
				+ "\nStatus  : " + status + "\n";
	}	

	
}

