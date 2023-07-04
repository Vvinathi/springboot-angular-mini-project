package com.bl.cm.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "customer_master")
public class CustomerMaster {
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<LoanApplication> loanApplications;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "custId", length = 6)
	@NotNull
	@Size(min = 6, max = 6, message = "custID should be 6 characters long")
	private Long custId;

	@Column(name = "custFirstName")
	@NotNull
	@Size(min = 3, message = "First name must be at least 3 characters long")
	private String custFirstName;

	@Column(name = "custLastName")
	@NotNull
	@Size(min = 3, message = "Last name must be at least 3 characters long")
	private String custLastName;

	@Column(name = "Address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "contactNo")
	private String contactNo;

	@Column(name = "adharCard")
	private int adharCard;

	@Column(name = "emailId")
	private String emailId;

	@Column(name = "birthDate")
	private Date birthDate;

	@Column(name = "monthlySalary")
	private int monthlySalary;

	public Long getCustId() {
		return custId;
	}

	public void setCustId(@NotNull @Size(min = 6, max = 6) Long custId) {
		this.custId = custId;
	}

	public String getCustFirstName() {
		return custFirstName;
	}

	public void setCustFirstName(String custFirstName) {
		this.custFirstName = custFirstName;
	}

	public String getCustLastName() {
		return custLastName;
	}

	public void setCustLastName(String custLastName) {
		this.custLastName = custLastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public int getAdharCard() {
		return adharCard;
	}

	public void setAdharCard(int adharCard) {
		this.adharCard = adharCard;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getMonthlySalary() {
		return monthlySalary;
	}

	public void setMonthlySalary(int monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	public CustomerMaster() {
		// Default constructor
	}

}
