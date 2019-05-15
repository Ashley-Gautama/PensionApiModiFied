package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.AccountDao;
import com.example.model.Account;

@Service
@Transactional
public class AccountService {
	
	@Autowired
	private AccountDao aDao;

	public AccountService() {

	}

	public void add(Account acc) {
		aDao.createAccount(acc);
	}
	
	public Account get(int id) {
		return aDao.getAccountById(id);
	}


	public List<Account> getAll() {
		return aDao.accounts();
	}

}
