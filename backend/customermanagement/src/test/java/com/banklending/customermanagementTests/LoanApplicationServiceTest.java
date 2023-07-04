package com.banklending.customermanagementTests;

import com.bl.cm.exception.AgeBarException;
import com.bl.cm.exception.DuplicateAccountException;
import com.bl.cm.exception.LoanUnderProcessingException;
import com.bl.cm.model.CustomerMaster;
import com.bl.cm.model.LoanApplication;
import com.bl.cm.repo.CustomerMasterRepo;
import com.bl.cm.repo.LoanApplicationRepo;
import com.bl.cm.service.LoanApplicationService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

public class LoanApplicationServiceTest {

	@Mock
	private LoanApplicationRepo loanApplicationRepo;

	@Mock
	private CustomerMasterRepo customerMasterRepo;

	@InjectMocks
	private LoanApplicationService loanApplicationService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	/*
	 * @Test public void testCreateLoanApplication() { LoanApplication
	 * loanApplication = new LoanApplication(); loanApplication.setLoanAppId(1L);
	 * 
	 * when(loanApplicationRepo.save(loanApplication)).thenReturn(loanApplication);
	 * 
	 * LoanApplication result =
	 * loanApplicationService.createLoanApplication(loanApplication);
	 * 
	 * Assertions.assertNotNull(result); Assertions.assertEquals(1L,
	 * result.getLoanAppId());
	 * 
	 * verify(loanApplicationRepo, times(1)).save(loanApplication); }
	 * 
	 * 
	 * @Test public void testUpdateLoanStatus() { Long loanAppId = 1L;
	 * LoanApplication loanApp = new LoanApplication(); loanApp.setCustomer(new
	 * CustomerMaster());
	 * 
	 * LoanApplication existingLoanApplication = new LoanApplication();
	 * existingLoanApplication.setLoanAppId(loanAppId);
	 * existingLoanApplication.setAppStatus("NewLoan");
	 * 
	 * when(loanApplicationRepo.findById(loanAppId)).thenReturn(java.util.Optional.
	 * of(existingLoanApplication));
	 * when(loanApplicationRepo.save(loanApp)).thenReturn(loanApp);
	 * 
	 * Assertions.assertThrows(AgeBarException.class, () ->
	 * loanApplicationService.updateLoanStatus(loanAppId, loanApp));
	 * 
	 * verify(loanApplicationRepo, times(1)).findById(loanAppId);
	 * verify(loanApplicationRepo, never()).save(loanApp); }
	 */

	@Test
	public void testGetLoanRecordsByStatus() {
		String status = "Approved";
		List<LoanApplication> loanApplications = new ArrayList<>();

		when(loanApplicationRepo.findByStatus(status)).thenReturn(loanApplications);

		List<LoanApplication> result = loanApplicationService.getLoanRecordsByStatus(status);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(loanApplications, result);

		verify(loanApplicationRepo, times(1)).findByStatus(status);
	}

	@Test
	public void testGetLoanApplicationsByStatus() {
		String appStatus = "In Process";
		List<LoanApplication> loanApplications = new ArrayList<>();

		when(loanApplicationRepo.findByAppStatus(appStatus)).thenReturn(loanApplications);

		List<LoanApplication> result = loanApplicationService.getLoanApplicationsByStatus(appStatus);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(loanApplications, result);

		verify(loanApplicationRepo, times(1)).findByAppStatus(appStatus);
	}

	@Test
	public void testGetAllLoanApplications() {
		List<LoanApplication> loanApplications = new ArrayList<>();

		when(loanApplicationRepo.findAll()).thenReturn(loanApplications);

		List<LoanApplication> result = loanApplicationService.getAllLoanApplications();

		Assertions.assertNotNull(result);
		Assertions.assertEquals(loanApplications, result);

		verify(loanApplicationRepo, times(1)).findAll();
	}

	/*
	 * @Test public void testGetLoanRecordsByDate() { String date = "2023-06-10";
	 * Date loanAppDate = new Date();
	 * 
	 * when(loanApplicationRepo.findByLoanAppDate(loanAppDate)).thenReturn(new
	 * ArrayList<>());
	 * 
	 * List<LoanApplication> result =
	 * loanApplicationService.getLoanRecordsByDate(date);
	 * 
	 * Assertions.assertNotNull(result); Assertions.assertTrue(result.isEmpty());
	 * 
	 * verify(loanApplicationRepo, times(1)).findByLoanAppDate(loanAppDate); }
	 */
	@Test
	public void testUpdateLoanRequest() {
		Long loanAppId = 1L;
		LoanApplication updatedLoanApp = new LoanApplication();
		updatedLoanApp.setLoanAmt(50000);

		LoanApplication existingLoanApp = new LoanApplication();
		existingLoanApp.setLoanAppId(loanAppId);

		when(loanApplicationRepo.findById(loanAppId)).thenReturn(java.util.Optional.of(existingLoanApp));
		when(loanApplicationRepo.save(existingLoanApp)).thenReturn(existingLoanApp);

		loanApplicationService.updateLoanRequest(loanAppId, updatedLoanApp);

		Assertions.assertEquals(updatedLoanApp.getLoanAmt(), existingLoanApp.getLoanAmt());

		verify(loanApplicationRepo, times(1)).findById(loanAppId);
		verify(loanApplicationRepo, times(1)).save(existingLoanApp);
	}

	@Test
	public void testViewLoanAccount() {
		Long loanAppId = 1L;
		LoanApplication loanApplication = new LoanApplication();
		loanApplication.setLoanAppId(loanAppId);

		when(loanApplicationRepo.findById(loanAppId)).thenReturn(java.util.Optional.of(loanApplication));

		LoanApplication result = loanApplicationService.viewLoanAccount(loanAppId);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(loanAppId, result.getLoanAppId());

		verify(loanApplicationRepo, times(1)).findById(loanAppId);
	}

	@Test
	public void testCheckCustomerAcceptanceStatus() {
		Long loanAppId = 1L;
		LoanApplication loanApplication = new LoanApplication();
		loanApplication.setLoanAppId(loanAppId);

		when(loanApplicationRepo.findById(loanAppId)).thenReturn(java.util.Optional.of(loanApplication));

		LoanApplication result = loanApplicationService.checkCustomerAcceptanceStatus(loanAppId);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(loanAppId, result.getLoanAppId());

		verify(loanApplicationRepo, times(1)).findById(loanAppId);
	}

}
