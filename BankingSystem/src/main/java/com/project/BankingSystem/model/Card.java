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
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor

@Entity
public class Card {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(length=16,nullable=false,unique=true)
	private String no;
	
	@Column(length=10,nullable=false)
	private String type;
	
	private LocalDate expiryDate;
	
	@Column(nullable=false,length=3)
	private String cvc;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(nullable=true,name="account_id")
	private Account account;
	
	@Enumerated(EnumType.STRING)
	private Status status=Status.Active;

	public Card(String no, String type, LocalDate expiryDate, String cvc, Account account) {
		super();
		this.no = no;
		this.type = type;
		this.expiryDate = expiryDate;
		this.cvc = cvc;
		this.account = account;
	}

	@Override
	public String toString() {
		return "Card_id : " + id + "\nCard_no : " + no + "\nType : " + type + "\nExpiryDate : " + expiryDate + "\nCvc : " + cvc
				+ "\nAccount_No : " + account.getAccount_number() + "\nStatus : " + status + "\n";
	}
	
	
	

	
	
	
//	 @PrePersist
//	    public void setDefaultExpDate() {
//	        if (expiryDate == null) {
//	            LocalDate now = LocalDate.now();
//	            this.expiryDate = LocalDate.of(now.getYear() + 4, now.getMonth(), now.getDayOfMonth());
//	        }
//	    }

}
