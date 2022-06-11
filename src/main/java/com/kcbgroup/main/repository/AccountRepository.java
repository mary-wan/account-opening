package com.kcbgroup.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kcbgroup.main.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
//	Account getAllUserAccounts(Long customerId);
//	
//	@Query(value = "SELECT *\r\n"
//			+ "FROM ken20956_account\r\n"
//			+ "INNER JOIN ken20956_customer ON ken20956_account.customer_id = ken20956_customer.id;", nativeQuery = true)
//    List<Account> findAccountById(@Param("customerId") Long id);
	
//	@Query(value = "SELECT ken20956_account.account_number FROM ken20956_account INNER JOIN ken20956_customer ON ken20956_account.customer_id_number =:customerIdNumber", nativeQuery = true)
//     List<Account>findAccountById(@Param("customerIdNumber") String customerIdNumber );
	
	@Query(value = "SELECT * FROM ken20956_account INNER JOIN ken20956_customer ON ken20956_account.customer_id_number =:customerIdNumber", nativeQuery = true)
	List<Account> findAccountById(@Param("customerIdNumber") String customerIdNumber);
	
//	List<Account> findAccountBycustomerIdNumber(String customerIdNumber);

}
