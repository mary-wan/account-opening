package com.kcbgroup.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kcbgroup.main.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	@Query(value = "SELECT * FROM ken20956_account WHERE ken20956_account.customer_id_number =:customerIdNumber", nativeQuery = true)
	List<Account> findAccountById(@Param("customerIdNumber") Long customerIdNumber);


}
