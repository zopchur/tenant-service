package com.des.hackathon.tenant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.des.hackathon.tenant.beans.Tenant;
import com.des.hackathon.tenant.service.TenantService;

@RestController
@RequestMapping("/tenant")
public class TenantController {

	@Autowired
	private TenantService tenantService;
	
	@GetMapping(value = "/{tenandId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Tenant getProductDetail(@PathVariable String tenandId) {
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
		
		Tenant product = tenantService.getTenantDetail(tenandId);
		
		return product;
	}
	
}
