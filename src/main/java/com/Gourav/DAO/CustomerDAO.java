package com.Gourav.DAO;

import com.Gourav.models.Customer;

public interface CustomerDAO {
	
	public void addCustomer(Customer customer);
	public boolean verifyCustomer(String email, String password);
	public Customer getCustomerById(String email);
	public void updateCustomer(Customer customer);
}
