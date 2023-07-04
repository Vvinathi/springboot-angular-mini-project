package com.bl.cm.repo;

import com.bl.cm.model.CustomerMaster;
import com.bl.cm.model.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LoanApplicationRepo extends JpaRepository<LoanApplication, Long> {

	List<LoanApplication> findByAppStatus(String appStatus);

	List<LoanApplication> findByCustomerAdharCard(int adharCard);

	List<LoanApplication> findByLoanAppDate(Date loanAppDate);

	LoanApplication findTopByOrderByLoanAppIdDesc();

	Long findMaxLoanAppIdByTypeOfLoan(String typeOfLoan);

	List<LoanApplication> findByTypeOfLoan(String loanType);

	@Modifying
	@Query("UPDATE LoanApplication l SET l.appStatus = :status WHERE l.loanAppId = :loanAppId")
	void updateAppStatus(@Param("loanAppId") Long loanAppId, @Param("status") String status);

	@Query("SELECT l FROM LoanApplication l WHERE l.typeOfLoan = :loanType")
	List<LoanApplication> findLoanApplicationsByLoanType(@Param("loanType") String loanType);

	List<LoanApplication> findByStatus(String status);

}
