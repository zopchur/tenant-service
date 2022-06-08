package com.des.hackathon.tenant.util;

import java.util.HashMap;
import java.util.Map;

import com.des.hackathon.tenant.beans.Tenant;

public class TenantConstant {

	public static Map<String, Tenant> tenantMap = new HashMap<>();
	
	static {
		tenantMap.put("101", new Tenant("101", "Idea","INDIA","","","Pending"));
		tenantMap.put("102", new Tenant("102", "Vodafone","SA","","", "Pending"));
		tenantMap.put("103", new Tenant("103", "Airtel","USA","","", "Pending"));
		tenantMap.put("104", new Tenant("104", "Reliance","INDIA","","", "Pending"));
	}
	
	public static String KEYCLOCK_URL = "http://localhost:8080/auth/realms/master/protocol/openid-connect/token";
	public static String KEYCLOCK_CREATE_RELEAM = "http://localhost:8080/auth/admin/realms";
	public static String KEYCLOCK_USER = "http://localhost:8080/auth/admin/realms";
}
