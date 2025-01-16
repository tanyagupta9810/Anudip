package com.project.BankingSystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Address {
	@Id
	@GeneratedValue
	@Column(name="aid")
	private int id;
	
	@Column(length=6,unique=true)
	private String hno;
	
	@Column(length=20)
	private String area;
	
	@Column(length=20,nullable=false)
	private String city;
	
	@Column(length=20,nullable=false)
	private String state;
	
	@Column(length=7,nullable=false)
	private String pincode;
	

	@Override
	public String toString() {
		return  hno + "," + area + "," + city + "," + state + "," + pincode ;
	}

}
