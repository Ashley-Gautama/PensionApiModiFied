package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.BankAccountHolderDao;
import com.example.model.BankAccountHolder;

@Service
@Transactional
public class BankAccountHolderService {
	
	@Autowired
	private BankAccountHolderDao aDao;

	public BankAccountHolderService() {

	}

	public void add(BankAccountHolder holder) {
		aDao.createHolder(holder);
	}
	

	

	public BankAccountHolder get(int id) {
		return aDao.getHolderById(id);
	}



	public List<BankAccountHolder> getAll() {
		return aDao.listHolders();
	}
}
