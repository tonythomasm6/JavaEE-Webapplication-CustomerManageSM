package com.managedBeans.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.entities.Agent;
import com.entities.Customer;
import com.entities.CustomerContact;
import com.entities.IndustryType;
import com.repository.AdminRepository;
import com.repository.AgentRepository;
import com.repository.ContactsRepository;
import com.repository.CustomerRepository;

@Named
@RequestScoped
public class ManagedBeanRepository implements Serializable {
	
	@EJB
	AgentRepository agentRepository;
	
	@EJB
	CustomerRepository customerRepository;
	
	@EJB
	ContactsRepository contactsRepository;
	
	@EJB
	AdminRepository adminRepository;
	
	public Agent getLoggedAgentDetails(String loggedUserName) {
		Agent agent = agentRepository.getLoggedAgentDetails(loggedUserName);
		return agent;
	}
	
	public List<Customer> getCustomers(int agentId, String role){
		List<Customer> customers = customerRepository.getCustomers(agentId, role);
		return customers;
		
	}
	
	public void addCustomer(Customer customer) {
		customerRepository.addCustomer(customer);
	}

	public List<IndustryType> getAllIndustryTypes() {
		List<IndustryType> allIndustryTypes = agentRepository.getAllIndustryTypes();
		return allIndustryTypes;
	}

	public Customer getCustomer(int customerId) {
		Customer customer = customerRepository.getCustomer(customerId);
		return customer;
	}
	
	public void editCustomer(Customer c) {
		customerRepository.editCustomer(c);
	}
	
	public  void deleteCustomer(Customer customer) {
		customerRepository.deleteCustomer(customer);
	}
	
	public List<CustomerContact> getContactsForCustomer(int customerId){
		List<CustomerContact> contacts = contactsRepository.getContactsForCustomer(customerId);
		return contacts;
	}
	
	public void addCustomerContact(CustomerContact c) {
		contactsRepository.addCustomerContact(c);
	}
	
	public CustomerContact getCustomerContactFromId(int contactId) {
		CustomerContact contact = contactsRepository.getCustomerContactFromId(contactId);
		return contact;
	}
	
	public void editContact(CustomerContact c) {
		contactsRepository.editContact(c);
	}
	
	public void deleteContact(CustomerContact c) {
		contactsRepository.deleteContact(c);
	}
	
	public List<Agent> getAllStaff() {
		List<Agent> agents =agentRepository.getAllStaff();
		return agents;
	}
	
	public void addStaff(Agent staff) {
		adminRepository.addStaff(staff);
	}
	
	public Agent getAgentFromId(int agentId) {
		Agent a = adminRepository.getAgentFromId(agentId);
		return a;
	}
	
	public void editAgent(Agent a) {
		adminRepository.editAgent(a);
	}
	
	public void deleteAgent(Agent a) {
		adminRepository.deleteAgent(a);
	}
	
	public void updateCustomerStaffAllocation(Customer customer) {
		adminRepository.updateCustomerStaffAllocation(customer);
	}
	
	public List<Customer> getCustomers(int agentId, String role, int industryTypeId){
		List<Customer> customers = customerRepository.getCustomer(agentId, role, industryTypeId);
		return customers;
	}
	
	
}
