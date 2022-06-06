package com.des.hackathon.tenant.service.impl;

import org.springframework.stereotype.Service;

import com.des.hackathon.tenant.beans.Tenant;
import com.des.hackathon.tenant.service.TenantService;
import com.des.hackathon.tenant.util.TenantConstant;

@Service
public class TenantServiceImpl implements TenantService{

	@Override
	public Tenant getTenantDetail(String tenandId) {
		Tenant tenant = TenantConstant.tenantMap.get(tenandId);
		/*
		 * try { Thread.sleep(1000); } catch (InterruptedException e) {
		 * e.printStackTrace(); }
		 */
		System.out.println("Tenant Detail : "+tenant);
		return tenant;
	}

}
