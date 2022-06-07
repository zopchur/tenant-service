package com.des.hackathon.tenant.service;

import com.des.hackathon.tenant.beans.TenantInfo;

public interface TenantService {

	public TenantInfo getTenantDetail(String productId);
	public TenantInfo tenantApprove(String tenantId, TenantInfo tenantInfo);
}
