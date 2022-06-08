package com.des.hackathon.tenant.proxy;

import com.des.hackathon.tenant.beans.AuthInfo;
import com.des.hackathon.tenant.beans.RealmsInfo;
import com.des.hackathon.tenant.beans.Tenant;

public interface KeyClockProxy {

    AuthInfo autheticateService();
    RealmsInfo createRelam(String token, Tenant tenantInfo);
    String createUserByRealms(String token, Tenant tenantInfo);
}
