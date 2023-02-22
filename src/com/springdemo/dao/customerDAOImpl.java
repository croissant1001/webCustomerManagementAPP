package com.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springdemo.entity.Customer;

@Repository
public class customerDAOImpl implements customerDAO {

	//inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
		
	@Override
	public List<Customer> getCustomers() {
		//get current hibernate session
		Session currentSession =sessionFactory.getCurrentSession();
		
		//creat a query...sort by last name
		Query<Customer> theQuery=
				currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		//execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		//return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		//get current hibernate session
		Session currentSession =sessionFactory.getCurrentSession();
		
		//save the customer
		currentSession.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomers(int theId) {
		Session currentSession =sessionFactory.getCurrentSession();
		Customer theCustomer = currentSession.get(Customer.class, theId);
		return theCustomer;
		
	}
 
	@Override
	public void deleteCustomer(int theId) {
		Session currentSession =sessionFactory.getCurrentSession();
		Query<Customer> theQuery=
				currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		theQuery.executeUpdate();
	}

	public List<Customer> searchCustomers(String theSearchName) {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        
        Query theQuery = null;
        
        //
        // only search by name if theSearchName is not empty
        //
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            // search for firstName or lastName ... case insensitive
            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from Customer", Customer.class);            
        }
        
        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();
                
        // return the results        
        return customers;
        
    }
}