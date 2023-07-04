package com.bl.cm.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name = "loan_application")
public class LoanApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loanAppId")
	private Long loanAppId;

	@ManyToOne
	@JoinColumn(name = "custId")
	private CustomerMaster customer;

	@Column(name = "loanAmt")
	private double loanAmt;

	@Column(name = "noOfYears")
	private int noOfYears;

	@Column(name = "purpose")
	private String purpose;

	@Column(name = "appStatus")
	@NotNull
	@Pattern(regexp = "NewLoan|Canceled|Approved|Sanctioned", message = "Invalid appStatus")
	private String appStatus;

	@Column(name = "typeOfLoan")
	private String typeOfLoan;

	@Column(name = "loanAppDate")
	@NotNull(message = "loanAppDate must not be null")
	@Temporal(TemporalType.DATE)
	private Date loanAppDate;

	@Column(name = "status")
	@NotNull
	@Pattern(regexp = "accepted|rejected|no status", message = "Invalid status")
	private String status;

	public LoanApplication() {
		this.loanAppDate = new Date();
	}

	public Long getLoanAppId() {
		return loanAppId;
	}

	public void setLoanAppId(Long loanAppId) {
		this.loanAppId = loanAppId;
	}

	public CustomerMaster getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerMaster customer) {
		this.customer = customer;
	}

	public double getLoanAmt() {
		return loanAmt;
	}

	public void setLoanAmt(double loanAmount) {
		this.loanAmt = loanAmount;
	}

	public int getNoOfYears() {
		return noOfYears;
	}

	public void setNoOfYears(int noOfYears) {
		this.noOfYears = noOfYears;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	public String getTypeOfLoan() {
		return typeOfLoan;
	}

	public void setTypeOfLoan(String typeOfLoan) {
		this.typeOfLoan = typeOfLoan;
	}

	public Date getLoanAppDate() {
		return loanAppDate;
	}

	public void setLoanAppDate(Date loanAppDate) {
		this.loanAppDate = loanAppDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
