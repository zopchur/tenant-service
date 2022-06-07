package com.des.hackathon.tenant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.des.hackathon.tenant.beans.TenantInfo;
import com.des.hackathon.tenant.service.TenantService;

@RestController
@RequestMapping("/tenant")
public class TenantController {

	@Autowired
	private TenantService tenantService;
	
	@GetMapping(value = "/{tenandId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TenantInfo getProductDetail(@PathVariable String tenandId) {
	/*	Keycloak keycloak = KeycloakBuilder.builder()
	            .serverUrl("http://localhost:8090/auth")
	            .realm("master")
	            .clientId("admin-cli")
	            .username("admin")
	            .password("admin")
	            .build();
		RealmRepresentation rr = new RealmRepresentation();;
		rr.setId("test1-realm");
		rr.setRealm("test1-realm");
		rr.setEnabled(true);

		keycloak.realms().create(rr);*/
		
		TenantInfo product = tenantService.getTenantDetail(tenandId);
		
		return product;
	}
	@PatchMapping(value = "/tenantApprove/{tenantId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TenantInfo tenantApprove(@PathVariable String tenantId, @RequestBody TenantInfo tenantInfo){

		tenantService.tenantApprove(tenantId,tenantInfo);

		return tenantInfo;
	}

}
