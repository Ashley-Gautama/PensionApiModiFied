package com.legacy.service;

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

	public void add(Address acc) {
		aDao.save(acc);
	}

	public Address findByStreet(String street) {
		return aDao.findByStreet(street);
	}

	public Address findByZipCode(String zip) {
		return aDao.findByZipcode(zip);
	}

	public Address findByCity(String city) {
		return aDao.findByCity(city);
	}
	
	
}
