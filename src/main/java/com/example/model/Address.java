package com.example.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


	

	@Entity
	public class Address {
		
		@Id
		@GeneratedValue
		private long id;
		private String street;
		private String city;
		private String zipcode;
		
		
		public Address() {
			super();
		}

		public Address( String street, String city, String zipcode) {
			super();
			this.id = 0;
			this.street = street;
			this.city = city;
			this.zipcode = zipcode;
		}
		
		
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getZipcode() {
			return zipcode;
		}

		public void setZipcode(String zipcode) {
			this.zipcode = zipcode;
		}

		
		
	}


