package com.des.hackathon.tenant.proxy;

import com.des.hackathon.tenant.beans.AuthInfo;
import com.des.hackathon.tenant.beans.RealmsInfo;
import com.des.hackathon.tenant.beans.TenantInfo;

public interface KeyClockProxy {

    AuthInfo autheticateService();
    RealmsInfo createRelam(String token, TenantInfo tenantInfo);
    String createUserByRealms(String token, TenantInfo tenantInfo);
}
