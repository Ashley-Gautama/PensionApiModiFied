package com.example.dao;


	

	import java.util.List;

	import org.hibernate.query.Query;
	import org.hibernate.Session;
	import org.hibernate.SessionFactory;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Repository;

import com.example.model.Address;

	@Repository
	public class AddressDao {
	@Autowired
	private SessionFactory sf;

		public AddressDao() {
			
		}
		
		private Session getCurrentSession(){
			return sf.getCurrentSession();
		}
		
		public void createAdress(Address adress){
			
			getCurrentSession().save(adress);
		}
		
		public List<Address> listAdresses(){
			Session session=this.getCurrentSession();
			String hql="from Address ";
			Query query=session.createQuery(hql);
			return (List<Address>) query.list();	
		}
		
		public Address getAdressbyId(long id){
			Session session=this.getCurrentSession();
			String hql="from Address a where a.id=:id ";
			Query query=session.createQuery(hql);
			query.setParameter("id", id);
			Address adress=(Address)query.list().get(0);
			return adress;
		}

	}

