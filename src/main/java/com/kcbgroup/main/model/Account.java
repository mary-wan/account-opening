package com.kcbgroup.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_generator")
	private long id;

	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(unique = true, nullable = false)
	private String accountNumber;

	@Column(name = "status")
	private String status;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumns({
		@JoinColumn(name = "customer_id_number", nullable = false),
//		@JoinColumn(name = "customer_id", nullable = false)
		
	})
	private Customer customer;

}
