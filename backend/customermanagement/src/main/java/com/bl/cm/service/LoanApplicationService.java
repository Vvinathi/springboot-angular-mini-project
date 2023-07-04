package com.bl.cm.service;

import com.bl.cm.exception.AgeBarException;
import com.bl.cm.exception.DuplicateAccountException;
import com.bl.cm.exception.LoanUnderProcessingException;
import com.bl.cm.model.CustomerMaster;
import com.bl.cm.model.LoanApplication;
import com.bl.cm.repo.CustomerMasterRepo;
import com.bl.cm.repo.LoanApplicationRepo;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LoanApplicationService {
	private final LoanApplicationRepo loanApplicationRepo;
	private final CustomerMasterRepo customerMasterRepo;

	@Autowired
	private CustomerService customerService;

	@Autowired
	public LoanApplicationService(CustomerMasterRepo customerMasterRepo, LoanApplicationRepo loanApplicationRepo) {
		this.customerMasterRepo = customerMasterRepo;
		this.loanApplicationRepo = loanApplicationRepo;
	}

	public LoanApplication createLoanApplication(LoanApplication loanApplication) {
		loanApplication.setLoanAppId(null);

		LoanApplication createdLoanApplication = loanApplicationRepo.save(loanApplication);

		return createdLoanApplication;
	}

	public void updateLoanStatus(Long loanAppId, LoanApplication loanApp) {
		LoanApplication existingLoanApplication = loanApplicationRepo.findById(loanAppId).orElse(null);
		if (loanApp.getCustomer() == null) {
			throw new IllegalArgumentException("Customer object cannot be null.");
		}

		if (existingLoanApplication != null) {
			System.out.println("App Status: " + existingLoanApplication.getAppStatus());
			if (existingLoanApplication.getAppStatus() != null
					&& !existingLoanApplication.getAppStatus().equals("NewLoan")) {
				throw new LoanUnderProcessingException("Loan application is under processing and cannot be updated.");
			}

			validateCustomerAge(loanApp.getCustomer().getBirthDate());
			validateUniqueAdharCard(loanApp.getCustomer().getAdharCard());

			loanApp.setLoanAppId(existingLoanApplication.getLoanAppId());
			loanApplicationRepo.save(loanApp);
		} else {
			throw new IllegalArgumentException("Loan application not found.");
		}
	}

	// service methods..

	public List<LoanApplication> getLoanRecordsByStatus(String status) {
		List<LoanApplication> loanApplications = loanApplicationRepo.findByStatus(status);
		return loanApplications;
	}

	public List<LoanApplication> getLoanApplicationsByStatus(String appStatus) {
		return loanApplicationRepo.findByAppStatus(appStatus);
	}

	public List<LoanApplication> getAllLoanApplications() {
		return loanApplicationRepo.findAll();
	}

	public List<LoanApplication> getLoanRecordsByDate(String date) {
		Date loanAppDate = convertStringToDate(date);
		if (loanAppDate == null) {
			return new ArrayList<>();
		}

		List<LoanApplication> loanApplications = loanApplicationRepo.findByLoanAppDate(loanAppDate);
		return loanApplications;
	}

	public Date convertStringToDate(String dateString) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void updateLoanRequest(Long loanAppId, LoanApplication updatedLoanApp) {
		LoanApplication existingLoanApp = loanApplicationRepo.findById(loanAppId)
				.orElseThrow(() -> new RuntimeException("Loan application not found"));

		// Updating the existing loan request with the updatedLoanApp object
		existingLoanApp.setLoanAmt(updatedLoanApp.getLoanAmt());
		existingLoanApp.setNoOfYears(updatedLoanApp.getNoOfYears());
		existingLoanApp.setPurpose(updatedLoanApp.getPurpose());
		existingLoanApp.setAppStatus(updatedLoanApp.getAppStatus());
		existingLoanApp.setTypeOfLoan(updatedLoanApp.getTypeOfLoan());
		existingLoanApp.setStatus(updatedLoanApp.getStatus());

		loanApplicationRepo.save(existingLoanApp);
	}

	public LoanApplication viewLoanAccount(Long loanAppId) {
		LoanApplication loanApplication = loanApplicationRepo.findById(loanAppId).orElse(null);
		if (loanApplication != null) {
			return mapLoanApplicationToLoanApplicationDTO(loanApplication);
		} else {
			throw new IllegalArgumentException("Loan application not found.");
		}
	}

	public LoanApplication checkCustomerAcceptanceStatus(Long loanAppId) {
		LoanApplication loanApplication = loanApplicationRepo.findById(loanAppId).orElse(null);
		if (loanApplication != null) {
			LoanApplication loanAppDTO = mapLoanApplicationToLoanApplicationDTO(loanApplication);
			return loanAppDTO;
		} else {
			throw new IllegalArgumentException("Loan application not found.");
		}
	}

	private LoanApplication mapLoanApplicationToLoanApplicationDTO(LoanApplication loanApp) {
		LoanApplication loanApplicationDTO = new LoanApplication();

		// Map relevant fields from LoanApplication to LoanApplicationDTO
		loanApplicationDTO.setLoanAppId(loanApp.getLoanAppId());
		loanApplicationDTO.setLoanAmt(loanApp.getLoanAmt());
		loanApplicationDTO.setNoOfYears(loanApp.getNoOfYears());
		loanApplicationDTO.setPurpose(loanApp.getPurpose());
		loanApplicationDTO.setAppStatus(loanApp.getAppStatus());
		loanApplicationDTO.setTypeOfLoan(loanApp.getTypeOfLoan());
		loanApplicationDTO.setLoanAppDate(loanApp.getLoanAppDate());
		loanApplicationDTO.setStatus(loanApp.getStatus());

		return loanApplicationDTO;
	}

	private void validateCustomerAge(Date birthDate) {
		LocalDate currentDate = LocalDate.now();
		if (birthDate == null) {
			throw new IllegalArgumentException("Birth date cannot be null.");
		}
		long age = ChronoUnit.YEARS.between(birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
				currentDate);
		if (age < 18 || age > 58) {
			throw new AgeBarException("Customer age should be between 18 and 58 years.");
		}
	}

	private void validateUniqueAdharCard(int adharCard) {
		List<LoanApplication> existingLoanApplications = loanApplicationRepo.findByCustomerAdharCard(adharCard);
		System.out.println("Number of existing loan applications: " + existingLoanApplications.size());
		if (!existingLoanApplications.isEmpty()) {
			throw new DuplicateAccountException("A loan application with the same Aadhar card already exists.");
		}
	}

}
