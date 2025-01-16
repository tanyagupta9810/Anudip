package com.project.BankingSystem.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name="Transaction")
public class Transactions {
	@Id
	@GeneratedValue
	private int id;
	@Column(length = 10, nullable = false)
	private String type;
	@Column(length = 10, nullable = false)
	private String t_status; // Success/Failed

	private Double amount;

	private LocalDate date;

	@ManyToOne
	@JoinColumn(name = "account_id", nullable = false)
	private Account account;

	@Enumerated(EnumType.STRING)
    private Status status=Status.Active;

	public Transactions(String type, String t_status, Double amount, LocalDate date, Account account) {
		super();
		this.type = type;
		this.t_status = t_status;
		this.amount = amount;
		this.date = date;
		this.account = account;
	}

	@Override
	public String toString() {
		return "Transactions_id : " + id + "\nType : " + type + "\nTransaction_status : " + t_status + "\nAmount : " + amount + "\nDate : "
				+ date + "\nAccount_No : " + account.getAccount_number() + "\nStatus  : " + status + "\n";
	}
	
	
	
	
}
