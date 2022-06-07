package com.kcbgroup.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kcbgroup.main.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
