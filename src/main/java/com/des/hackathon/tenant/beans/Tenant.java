package com.des.hackathon.tenant.beans;

public class Tenant {

	private String tenandId;
	private String tenantName;
	private String tenantAddress;
	
	public Tenant() {}
	
	public Tenant(String tenandId, String tenantName, String tenantAddress) {
		super();
		this.tenandId = tenandId;
		this.tenantName = tenantName;
		this.tenantAddress = tenantAddress;
	}
	
	
	public String getTenandId() {
		return tenandId;
	}

	public void setTenandId(String tenandId) {
		this.tenandId = tenandId;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getTenantAddress() {
		return tenantAddress;
	}

	public void setTenantAddress(String tenantAddress) {
		this.tenantAddress = tenantAddress;
	}

	
	@Override
	public String toString() {
		return "Tenant [tenandId=" + tenandId + ", tenantName=" + tenantName
				+ ", tenantAddress=" + tenantAddress + "]";
	}
}
