package com.bl.cm.controllers;

import com.bl.cm.model.LoanApplication;
import com.bl.cm.model.ReducedPaymentDTO;
import com.bl.cm.service.LoanApplicationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/loanapplications")
public class LoanApplicationController {

	private LoanApplicationService loanApplicationService;

	public LoanApplicationController(LoanApplicationService loanApplicationService) {
		this.loanApplicationService = loanApplicationService;
	}

	// Endpoint 5: Create a new loan application
	@PostMapping("/loan/new")
	public ResponseEntity<LoanApplication> createLoanApplication(@RequestBody LoanApplication loanApplication) {
		loanApplication.setLoanAppId(null);

		LoanApplication createdLoanApplication = loanApplicationService.createLoanApplication(loanApplication);

		return ResponseEntity.status(HttpStatus.CREATED).body(createdLoanApplication);
	}

	// Endpoint 6: View loan records based on status
	@GetMapping("/loan/status/{status}")
	public List<LoanApplication> getLoanRecordsByStatus(@PathVariable String status) {
		List<LoanApplication> loanApplications = loanApplicationService.getLoanRecordsByStatus(status);
		return loanApplications;
	}

	// Endpoint 7: View loan records based on date
	@GetMapping("/loan/date/{date}")
	public List<LoanApplication> getLoanRecordsByDate(@PathVariable String date) {
		List<LoanApplication> loanAppDTOs = loanApplicationService.getLoanRecordsByDate(date);
		return loanAppDTOs;
	}

	// Endpoint 8: Update loan request
	@PutMapping("/loan/{loanAppId}/update")
	public ResponseEntity<?> updateLoanRequest(@PathVariable Long loanAppId, @RequestBody LoanApplication loanAppDTO) {
		loanApplicationService.updateLoanRequest(loanAppId, loanAppDTO);
		return ResponseEntity.ok().build();
	}

	// Endpoint 9: View loan account
	@GetMapping("/loan/{loanAppId}")
	public LoanApplication viewLoanAccount(@PathVariable Long loanAppId) {
		LoanApplication loanDTO = loanApplicationService.viewLoanAccount(loanAppId);
		return loanDTO;
	}

	// Endpoint 10: Get loan application status
	@GetMapping("/loan/Appstatus/{appStatus}")
	public ResponseEntity<List<LoanApplication>> getLoanApplicationsByStatus(@PathVariable String appStatus) {
		List<LoanApplication> loanApplications = loanApplicationService.getLoanApplicationsByStatus(appStatus);

		if (!loanApplications.isEmpty()) {
			return ResponseEntity.ok(loanApplications);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Endpoint 11: View all loan applications
	@GetMapping("/loan/all")
	public ResponseEntity<List<LoanApplication>> getAllLoanApplications() {
		List<LoanApplication> loanApplications = loanApplicationService.getAllLoanApplications();

		if (!loanApplications.isEmpty()) {
			return ResponseEntity.ok(loanApplications);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	// Endpoint 12: Receive reduced payment list
	@GetMapping("/loan/receiveSanctionAmount/{loanAppID}")
	public List<ReducedPaymentDTO> receiveSanctionAmount(@PathVariable Long loanAppID) {
		// Created a dummy data for ReducedPaymentDTOs
		List<ReducedPaymentDTO> reducedPaymentDTOs = new ArrayList<>();
		ReducedPaymentDTO payment1 = new ReducedPaymentDTO();
		payment1.setPaymentId("PAY-001");
		payment1.setPaymentDate(new Date());
		payment1.setPaymentAmount(1000.0);

		ReducedPaymentDTO payment2 = new ReducedPaymentDTO();
		payment2.setPaymentId("PAY-002");
		payment2.setPaymentDate(new Date());
		payment2.setPaymentAmount(2000.0);

		reducedPaymentDTOs.add(payment1);
		reducedPaymentDTOs.add(payment2);

		return reducedPaymentDTOs;
	}
}
