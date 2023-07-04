package com.bl.cm.controllers;

import com.bl.cm.model.CustomerMaster;
import com.bl.cm.model.LoanApplication;
import com.bl.cm.service.CustomerService;
import com.bl.cm.service.LoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/customers")
public class CustomerController {
	private final CustomerService customerService;
	private final LoanApplicationService loanApplicationService;

	@Autowired
	public CustomerController(CustomerService customerService, LoanApplicationService loanApplicationService) {
		this.customerService = customerService;
		this.loanApplicationService = loanApplicationService;
	}

 // Endpoint 1: To register a customer
	@PostMapping("/new")
	public CustomerMaster addCustomer(@RequestBody CustomerMaster customer) {

		return customerService.addCustomer(customer);

	}

 // Endpoint 2: To view all customer records present
	@GetMapping("/all")
	public List<CustomerMaster> getAllCustomers() {
		return customerService.getCustomers();
	}
 // Endpoint 3: View customer record based on custId
	@GetMapping("/{custId}")
	public CustomerMaster getCustomerById(@PathVariable Long custId) {
		return customerService.getCustomerById(custId);
	}
 // Endpoint 4: Update a customer record
	@PutMapping("/{custId}/update")
	public CustomerMaster updateCustomer(@PathVariable Long custId, @RequestBody CustomerMaster updatedCustomer) {
		return customerService.updateCustomer(custId, updatedCustomer);
	}
}
