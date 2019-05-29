package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.AccountDao;
import com.example.dao.AddressDao;
import com.example.dao.BankAccountHolderDao;
import com.example.model.Account;
import com.example.model.Address;
import com.example.model.BankAccountHolder;

@Service
@Transactional
public class BankAccountHolderService {
	@Autowired
	private BankAccountHolderDao baDao;
	@Autowired
	private AddressDao addDao;
	@Autowired
	private AccountDao accDao;

	public void add(BankAccountHolder holder) {
		baDao.save(holder);
	}

	public Optional<BankAccountHolder> findById(long id) {
		return baDao.findById(id);
	}

	public BankAccountHolder findByemailAddress(String emailAddress) {
		return baDao.findByemailAddress(emailAddress);
	}

	public BankAccountHolder findByAddress(String Address) {
		return baDao.findByAddress(Address);
	}

	public BankAccountHolder findByAccount(String account) {
		return baDao.findByAccount(account);
	}

	public void add(Address acc) {
		addDao.save(acc);
	}

	public Address findByStreet(String street) {
		return addDao.findByStreet(street);
	}

	public Address findByZipCode(String zip) {
		return addDao.findByZipcode(zip);
	}

	public Address findByCity(String city) {
		return addDao.findByCity(city);
	}

	public void add(Account acc) {
		accDao.save(acc);
	}

	public Account findByBankNumber(int id) {
		return accDao.findByBankNumber(id);
	}

	public List<Account> getAll() {
		return (List<Account>) accDao.findAll();
	}
}
