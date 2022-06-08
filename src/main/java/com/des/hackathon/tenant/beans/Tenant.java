package com.des.hackathon.tenant.beans;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "enroll_tenant")
public class Tenant {

	private String mobileNo;
	private String firstName;
	private String lastName;
	@Id
	private String orgName;
	private String emailId;
	private String status;
	
	public Tenant() {}

	public Tenant(String mobileNo, String firstName, String lastName, String orgName, String emailId, String status) {
		super();
		this.mobileNo = mobileNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.orgName = orgName;
		this.emailId = emailId;
		this.status = status;
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "Tenant [mobileNo=" + mobileNo + ", firstName=" + firstName + ", lastName=" + lastName + ", orgName="
				+ orgName + ", emailId=" + emailId + ", status=" + status + "]";
	}
	
}
