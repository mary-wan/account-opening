package com.kcbgroup.main.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(unique = true, nullable = true)
	private String accountNumber;


	@Column(name = "account_type")
	private String accountType;
	
	@Column(name = "kra_pin")
	private String kraPin;

	@Column(name = "customer_number")
	private String customerNumber;

	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = {CascadeType.ALL})
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "customer_id_number", nullable = false, referencedColumnName = "customer_id_number")
	
	private Customer customer;

}
