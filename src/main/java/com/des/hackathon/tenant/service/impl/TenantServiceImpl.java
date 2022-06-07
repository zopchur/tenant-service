package com.des.hackathon.tenant.service.impl;

import com.des.hackathon.tenant.beans.AuthInfo;
import com.des.hackathon.tenant.beans.RealmsInfo;
import com.des.hackathon.tenant.proxy.KeyClockProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.des.hackathon.tenant.beans.TenantInfo;
import com.des.hackathon.tenant.service.TenantService;
import com.des.hackathon.tenant.util.TenantConstant;

@Service
public class TenantServiceImpl implements TenantService{

	@Autowired
	private KeyClockProxy keyClockProxy;

	@Override
	public TenantInfo getTenantDetail(String tenandId) {
		TenantInfo tenant = TenantConstant.tenantMap.get(tenandId);
		/*
		 * try { Thread.sleep(1000); } catch (InterruptedException e) {
		 * e.printStackTrace(); }
		 */
		System.out.println("Tenant Detail : "+tenant);
		return tenant;
	}

	@Override
	public TenantInfo tenantApprove(String tenantId, TenantInfo tenantInfo){
		AuthInfo authInfo = keyClockProxy.autheticateService();

		if(null != authInfo){
			RealmsInfo result = keyClockProxy.createRelam(authInfo.getAccess_token(),tenantInfo);
			if(result!=null){
				keyClockProxy.createUserByRealms(authInfo.getAccess_token(),tenantInfo);
			}
		}
		return new TenantInfo();

	}
}
