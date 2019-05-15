package com.example.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.model.BankAccountHolder;

@Repository
public class BankAccountHolderDao {
	@Autowired
	private SessionFactory sf;
	
	private Session getCurrentSession(){
		return sf.getCurrentSession();
	}
	
	
	public BankAccountHolderDao() {
		super();
	}

	public void createHolder(BankAccountHolder holder){
		
		getCurrentSession().save(holder);
	}
	
	public List<BankAccountHolder> listHolders(){
		Session session=this.getCurrentSession();
		String hql="from BankAccountHolder ";
		Query query=session.createQuery(hql);
		return (List<BankAccountHolder>) query.list();	
	}
	
	public BankAccountHolder getHolderById(long id){
		Session session=this.getCurrentSession();
		String hql="from BankAccountHolder b where b.id=:id ";
		Query query=session.createQuery(hql);
		query.setParameter("id", id);
		BankAccountHolder holder=(BankAccountHolder)query.list().get(0);
		return holder;
	}
}
