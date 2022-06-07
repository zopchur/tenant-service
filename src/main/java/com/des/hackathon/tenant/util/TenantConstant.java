package com.des.hackathon.tenant.util;

import java.util.HashMap;
import java.util.Map;

import com.des.hackathon.tenant.beans.TenantInfo;

public class TenantConstant {

	public static Map<String, TenantInfo> tenantMap = new HashMap<>();
	
	static {
		tenantMap.put("101", new TenantInfo("101", "Idea","INDIA"));
		tenantMap.put("102", new TenantInfo("102", "Vodafone","SA"));
		tenantMap.put("103", new TenantInfo("103", "Airtel","USA"));
		tenantMap.put("104", new TenantInfo("104", "Reliance","INDIA"));
	}

	public static String KEYCLOCK_URL = "http://localhost:8090/auth/realms/master/protocol/openid-connect/token";
	public static String KEYCLOCK_CREATE_RELEAM = "http://localhost:8090/auth/admin/realms";
	public static String KEYCLOCK_CREATE_USER = "http://localhost:8090/auth/admin/realms";
}
