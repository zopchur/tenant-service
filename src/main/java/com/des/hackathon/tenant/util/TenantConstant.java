package com.des.hackathon.tenant.util;

import java.util.HashMap;
import java.util.Map;

import com.des.hackathon.tenant.beans.Tenant;

public class TenantConstant {

	public static Map<String, Tenant> tenantMap = new HashMap<>();
	
	static {
		tenantMap.put("101", new Tenant("101", "Idea","INDIA"));
		tenantMap.put("102", new Tenant("102", "Vodafone","SA"));
		tenantMap.put("103", new Tenant("103", "Airtel","USA"));
		tenantMap.put("104", new Tenant("104", "Reliance","INDIA"));
	}
}
