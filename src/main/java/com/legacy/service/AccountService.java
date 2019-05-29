package com.legacy.service;

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
		aDao.save(acc);
	}

	public Account findByBankNumber(int id) {
		return aDao.findByBankNumber(id);
	}

	public List<Account> getAll() {
		return (List<Account>) aDao.findAll();
	}
}
