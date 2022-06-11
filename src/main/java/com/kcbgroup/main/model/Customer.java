package com.kcbgroup.main.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ken20956_Customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_generator")
	private long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "customer_id_number")
	private String customerIdNumber;
	
	@Column(name="DOB")
	private Date dob;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "email")
	private String email;

	public Customer map(Customer customer) {
		// TODO Auto-generated method stub
		return customer;
	}

}
