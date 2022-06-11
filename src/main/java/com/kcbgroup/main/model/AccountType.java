package com.kcbgroup.main.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ken20956_Account_Type")
public class AccountType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account__type_generator")
	private long id;
	
	private String accountType;
	
//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//	@JoinColumn(name = "customer_id", nullable = false)
//	private Customer customer;
	
//	@OneToOne(fetch = FetchType.LAZY, optional = false)
//	@JoinColumn(name = "account_id", nullable = false)
//	private Account account;

}
