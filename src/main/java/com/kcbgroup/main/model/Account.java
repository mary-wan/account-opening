package com.kcbgroup.main.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ken20956_Account")
public class Account {

//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private long id;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(unique = true, nullable = false)
	private String accountNumber;

	@Column(name = "account_type")
	private String accountType;
	
	@Column(name = "kra_pin")
	private String kraPin;
	
	@Column(name = "customer_account")
	private String customerAccount;
//	
//	@Column(name = "nationality")
//	private String nationality;
//	
//	@Column(name = "industry")
//	private String industry;
	
//
//	public Customer getCustomer() {
//		return customer;
//	}
//
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = {CascadeType.ALL})
//	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "customer_id_number", nullable = false, referencedColumnName = "customer_id_number")

	private Customer customer;

}
