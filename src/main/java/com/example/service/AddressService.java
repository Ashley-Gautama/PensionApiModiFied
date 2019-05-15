package com.example.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.AddressDao;
import com.example.model.Address;


@Service
@Transactional
public class AddressService {

	@Autowired
	private AddressDao aDao;

	public AddressService() {

	}

	public void add(Address address) {
		aDao.createAdress(address);
	}
	

	

	public Address get(int id) {
		return aDao.getAdressbyId(id);
	}



	public List<Address> getAll() {
		return aDao.listAdresses();
	}

	
}
