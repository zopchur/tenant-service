package com.des.hackathon.tenant.service;

import java.util.List;

import com.des.hackathon.tenant.beans.Tenant;

public interface TenantService {
	
	public List<Tenant> getAllTenant();
	public Tenant getTenantByOrgName(String orgName);
	public Tenant rejectTenant(String orgName);
	public Tenant approveTenant(String orgName, Tenant tenant);
	public Tenant addTenant(Tenant tenant);
	
}
