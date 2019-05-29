package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.model.BankAccountHolder;

@Repository
public interface BankAccountHolderDao extends JpaRepository<BankAccountHolder, Long> {
	
	public BankAccountHolder findByemailAddress(String emailAddress);

	public BankAccountHolder findByAddress(String Address);

	public BankAccountHolder findByAccount(String account);
}
