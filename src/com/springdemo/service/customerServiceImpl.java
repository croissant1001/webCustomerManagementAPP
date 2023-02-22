package com.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springdemo.dao.customerDAO;
import com.springdemo.entity.Customer;

@Service
public class customerServiceImpl implements customerService {

	//inject customerDAO
	@Autowired
	private customerDAO customerDao;
	
	
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerDao.getCustomers();
	}



	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {
		
		customerDao.saveCustomer(theCustomer);
	}



	@Override
	@Transactional
	public Customer getCustomer(int theId) {
		return customerDao.getCustomers(theId);
	}



	@Override
	@Transactional
	public void deleteCustomer(int theId) {
		
		customerDao.deleteCustomer(theId);
	}



	@Override
    @Transactional
    public List<Customer> searchCustomers(String theSearchName) {
        return customerDao.searchCustomers(theSearchName);
    }

}
