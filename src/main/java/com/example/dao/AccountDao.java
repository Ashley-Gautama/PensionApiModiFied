package com.example.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.model.Account;
@Repository
public class AccountDao {

	@Autowired
	private SessionFactory sf;
	
	private Session getCurrentSession(){
		return sf.getCurrentSession();
	}
	
public void createAccount(Account account){
		
		getCurrentSession().save(account);
	}
	
	public List<Account> accounts(){
		Session session=this.getCurrentSession();
		String hql="from Adress ";
		Query query=session.createQuery(hql);
		return (List<Account>) query.list();	
	}
	
	public Account getAccountById(long id){
		Session session=this.getCurrentSession();
		String hql="from Account a where a.id=:id ";
		Query query=session.createQuery(hql);
		query.setParameter("id", id);
		Account account=(Account)query.list().get(0);
		return account;
	}
}
