package com.banklending.customermanagementTests;

import com.bl.cm.exception.AgeBarException;

import com.bl.cm.exception.DuplicateAccountException;
import com.bl.cm.model.CustomerMaster;
import com.bl.cm.repo.CustomerMasterRepo;
import com.bl.cm.service.CustomerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

	@Mock
	private CustomerMasterRepo customerMasterRepo;

	@InjectMocks
	private CustomerService customerService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	/*
	 * @Test void testAddCustomer_ValidCustomer_SuccessfullyAdded() { // Arrange
	 * CustomerMaster customer = new CustomerMaster(); // Set customer properties
	 * 
	 * when(customerMasterRepo.save(any(CustomerMaster.class))).thenReturn(customer)
	 * ;
	 * 
	 * // Act CustomerMaster result = customerService.addCustomer(customer);
	 * 
	 * // Assert assertNotNull(result); // Add additional assertions if needed }
	 */
	@Test
	void testAddCustomer_ValidationFails_RuntimeExceptionThrown() {
		CustomerMaster customer = new CustomerMaster();
		doThrow(new RuntimeException("Validation error")).when(customerMasterRepo).save(any(CustomerMaster.class));

		assertThrows(RuntimeException.class, () -> customerService.addCustomer(customer));
	}

	@Test
	void testUpdateCustomer_ExistingCustomer_SuccessfullyUpdated() {
		Long customerId = 1L;
		CustomerMaster existingCustomer = new CustomerMaster();
		CustomerMaster updatedCustomer = new CustomerMaster();

		when(customerMasterRepo.findById(customerId)).thenReturn(Optional.of(existingCustomer));
		when(customerMasterRepo.save(existingCustomer)).thenReturn(existingCustomer);

		CustomerMaster result = customerService.updateCustomer(customerId, updatedCustomer);

		assertNotNull(result);
		assertEquals(updatedCustomer.getCustFirstName(), result.getCustFirstName());
	}

	@Test
	void testUpdateCustomer_NonExistingCustomer_RuntimeExceptionThrown() {
		Long customerId = 1L;
		CustomerMaster updatedCustomer = new CustomerMaster();

		when(customerMasterRepo.findById(customerId)).thenReturn(Optional.empty());

		assertThrows(RuntimeException.class, () -> customerService.updateCustomer(customerId, updatedCustomer));
	}

	@Test
	void testValidatePhoneNumber_ValidPhoneNumber_ReturnsNormalizedPhoneNumber() {
		String phoneNumber = "(123) 456-7890";
		String expectedNormalizedPhoneNumber = "1234567890";

		String result = customerService.validatePhoneNumber(phoneNumber);

		assertEquals(expectedNormalizedPhoneNumber, result);
	}

	// Write similar unit tests for the remaining helper methods (validateEmail,
	// validateName, etc.)

	@Test
	void testGenerateCustomerId_NoExistingRecords_ReturnsDefaultId() {
		when(customerMasterRepo.getMaxCustomerId()).thenReturn(null);
		long expectedId = 1;

		long result = customerService.generateCustomerId();

		assertEquals(expectedId, result);
	}

	@Test
	void testGenerateCustomerId_ExistingRecords_ReturnsNewId() {
		long existingMaxId = 10;
		when(customerMasterRepo.getMaxCustomerId()).thenReturn(existingMaxId);
		long expectedId = existingMaxId + 1;

		long result = customerService.generateCustomerId();

		assertEquals(expectedId, result);
	}
}
