package com.des.hackathon.tenant.proxy.impl;

import com.des.hackathon.tenant.beans.AuthInfo;
import com.des.hackathon.tenant.beans.RealmsInfo;
import com.des.hackathon.tenant.beans.Tenant;
import com.des.hackathon.tenant.beans.UserInfo;
import com.des.hackathon.tenant.proxy.KeyClockProxy;
import com.des.hackathon.tenant.util.TenantConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class KeyClockProxyImpl implements KeyClockProxy {

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public AuthInfo autheticateService() {
        MultiValueMap<String,String> input = new LinkedMultiValueMap<>();
        input.put("username", Arrays.asList("admin"));
        input.put("password",Arrays.asList("admin"));
        input.put("client_id",Arrays.asList("admin-cli"));
        input.put("grant_type",Arrays.asList("password"));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity(input, headers);

        ResponseEntity<AuthInfo> response = restTemplate.postForEntity(TenantConstant.KEYCLOCK_URL, entity, AuthInfo.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Request Successful");
            System.out.println(response.getBody());
            return response.getBody();
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
            return null;
        }
    }
    @Override
    public RealmsInfo createRelam(String token, Tenant tenantInfo) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        RealmsInfo info = new RealmsInfo();
        info.setId(tenantInfo.getOrgName());
        info.setRealm(tenantInfo.getFirstName());
        info.setEnabled(Boolean.TRUE);
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity(info,headers);

        ResponseEntity<String> response = restTemplate.postForEntity(TenantConstant.KEYCLOCK_CREATE_RELEAM, entity, String.class);

        if (response.getStatusCode() == HttpStatus.CREATED) {
            System.out.println("Request Successful");
            System.out.println(response.getBody());
            return info;
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
            return null;
        }
    }

    @Override
    public String createUserByRealms(String token, Tenant tenantInfo){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        UserInfo info = new UserInfo();
        info.setUsername(tenantInfo.getFirstName()+"User");
        info.setFirstName(tenantInfo.getFirstName()+"User");
        info.setLastName(tenantInfo.getLastName()+"User");
        info.setEnabled(Boolean.TRUE);
        info.setEmailVerified(Boolean.TRUE);

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity(info,headers);

        ResponseEntity<String> response = restTemplate.postForEntity(TenantConstant.KEYCLOCK_USER+"/"+tenantInfo.getFirstName()+"/users", entity, String.class);

        if (response.getStatusCode() == HttpStatus.CREATED) {
            System.out.println("Request Successful");
            System.out.println(response.getBody());
            userPasswordReset(token,tenantInfo);
            return "CREATED";
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
            return null;
        }
    }

    private String userPasswordReset(String token, Tenant tenantInfo){
        ResponseEntity<String> response=null;
        Map<String,String> input = new HashMap<>();
        input.put("type", "password");
        input.put("value","thales@123");
        input.put("temporary","true");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        UserInfo info = getUserDetail(token,tenantInfo);
        if(info!=null && info.getId()!=null){
            HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity(input,headers);
            restTemplate.put(TenantConstant.KEYCLOCK_USER+"/"+tenantInfo.getFirstName()+"/users/"+info.getId()+"/reset-password", entity);
        }
        return "SUCCESS";
    }
    private UserInfo getUserDetail(String token, Tenant tenantInfo){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity(headers);

        ResponseEntity<UserInfo[]> response = restTemplate.exchange(TenantConstant.KEYCLOCK_USER+"/"+tenantInfo.getFirstName()+"/users", HttpMethod.GET, entity, UserInfo[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Request Successful");
            System.out.println(response.getBody());
            UserInfo [] userInfo = response.getBody();
            return userInfo[0];
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
            return null;
        }
    }
}
