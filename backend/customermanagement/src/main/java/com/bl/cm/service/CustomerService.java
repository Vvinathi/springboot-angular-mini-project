package com.bl.cm.service;

import com.bl.cm.exception.AgeBarException;

import com.bl.cm.exception.DuplicateAccountException;
import com.bl.cm.model.CustomerMaster;
import com.bl.cm.repo.CustomerMasterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class CustomerService {
	private final CustomerMasterRepo customerMasterRepo;

	@Autowired
	public CustomerService(CustomerMasterRepo customerMasterRepo) {
		this.customerMasterRepo = customerMasterRepo;
	}

	public List<CustomerMaster> getCustomers() {
		return customerMasterRepo.findAll();
	}

	public CustomerMaster addCustomer(CustomerMaster customer) {
		try {
			validateCustomerData(customer);
			long newId = generateCustomerId(); // Call generateCustomerId() without any arguments
			customer.setCustId(newId);
			return customerMasterRepo.save(customer);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to add customer due to a validation error: " + e.getMessage());
		}
	}

	public CustomerMaster updateCustomer(Long custId, CustomerMaster updatedCustomer) {
		CustomerMaster existingCustomer = getCustomerById(custId);
		if (existingCustomer == null) {
			throw new RuntimeException("Customer with ID " + custId + " does not exist.");
		}

		// Update the existing customer's details with the updatedCustomer object
		existingCustomer.setCustFirstName(updatedCustomer.getCustFirstName());
		existingCustomer.setCustLastName(updatedCustomer.getCustLastName());
		existingCustomer.setContactNo(updatedCustomer.getContactNo());
		existingCustomer.setEmailId(updatedCustomer.getEmailId());
		existingCustomer.setAdharCard(updatedCustomer.getAdharCard());
		existingCustomer.setBirthDate(updatedCustomer.getBirthDate());
		existingCustomer.setMonthlySalary(updatedCustomer.getMonthlySalary());

		return customerMasterRepo.save(existingCustomer);
	}

	public CustomerMaster getCustomerById(Long custId) {
		return customerMasterRepo.findById(custId).orElse(null);
	}

	public void validateCustomerData(CustomerMaster customer) {
		validatePhoneNumber(customer.getContactNo());
		System.out.println("Email address before validation: " + customer.getEmailId());
		validateEmail(customer.getEmailId());
		validateName(customer.getCustFirstName(), "First name");
		validateName(customer.getCustLastName(), "Last name");
		validateUniqueAdharCard(customer.getAdharCard());
		validateAge(customer.getBirthDate());
		validateSalary(customer.getMonthlySalary());
	}

	//PhoneNumber Validation
	public String validatePhoneNumber(String contactNo) {
		if (contactNo != null) {
			contactNo = contactNo.replaceAll("[^0-9]", "");
		}
		return contactNo;
	}

	//Email Validation
	private void validateEmail(String emailId) {
		System.out.println("Email address: " + emailId); // Print email address for debugging
		if (emailId == null || !emailId.matches(".+@cognizant\\.com")) {
			throw new RuntimeException("Email address should always have @cognizant.com.");
		}
	}
    
	//Name Validation
	private void validateName(String name, String fieldName) {
		if (name == null || name.isBlank() || name.length() < 3) {
			throw new RuntimeException(fieldName + " should only have alphabets and be minimum 3 characters long.");
		}
	}

	//AadharCard Validation
	private void validateUniqueAdharCard(int adharCard) {
		CustomerMaster existingCustomer = customerMasterRepo.findByAdharCard(adharCard);
		if (existingCustomer != null) {
			throw new DuplicateAccountException("Duplicate account with Adhar Card: " + adharCard);
		}
	}
  
	//Age Validation
	private void validateAge(Date birthDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(birthDate);
		int yearOfBirth = calendar.get(Calendar.YEAR);
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int age = currentYear - yearOfBirth;
		if (age < 18 || age > 58) {
			throw new AgeBarException("Age should be between 18 and 58 years.");
		}
	}
   //Salary Validation
	private void validateSalary(int monthlySalary) {
		if (monthlySalary <= 0) {
			throw new RuntimeException("Salary should be greater than zero.");
		}
	}

	public long generateCustomerId() {
		Long maxId = customerMasterRepo.getMaxCustomerId();
		long newId;

		if (maxId == null) {
			// No existing records, assign a default value
			newId = 1;
		} else {
			newId = maxId.longValue() + 1;
		}

		return newId;
	}

}
