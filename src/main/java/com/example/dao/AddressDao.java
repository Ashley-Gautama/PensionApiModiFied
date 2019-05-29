package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.model.Address;

@Repository
public interface AddressDao extends JpaRepository<Address, Long> {
	public Address findByStreet(String street);

	public Address findByZipcode(String street);

	public Address findByCity(String street);
}
