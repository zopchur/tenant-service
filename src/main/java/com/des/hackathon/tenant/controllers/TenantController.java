package com.des.hackathon.tenant.controllers;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.des.hackathon.tenant.beans.ResponseInfo;
import com.des.hackathon.tenant.beans.Tenant;
import com.des.hackathon.tenant.repository.TenantRepository;
import com.des.hackathon.tenant.service.TenantService;

@RestController
public class TenantController {
	
	@Autowired
	private TenantRepository tenantRepo;
	
	@Autowired 
	private TenantService tenantService;
	
	@GetMapping(value = "/tenant", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Tenant>> getTenantDetails() {
		
		try {
			List<Tenant> tenantList = tenantService.getAllTenant();
			if (Objects.isNull(tenantList)) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(tenantList, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@PostMapping(value = "/tenant", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Tenant> addTenant(@RequestBody Tenant tenant) {	
		try {
		      Tenant tenantDetail = tenantService.addTenant(tenant);
		      return new ResponseEntity<>(tenantDetail, HttpStatus.CREATED);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@GetMapping(value = "/tenant/verify/{orgName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Tenant> verfiyOrgName(@PathVariable("orgName") String orgName) {
	
		Tenant tenant = tenantService.getTenantByOrgName(orgName);
	    if (Objects.nonNull(tenant)) {
	      return new ResponseEntity<>(tenant, HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@PatchMapping(value = "/tenant/reject/{orgName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Tenant> rejectTenant(@PathVariable("orgName") String orgName) {
		try {
			Tenant tenant = tenantService.rejectTenant(orgName);
			if (Objects.nonNull(tenant)) {
		      return new ResponseEntity<>(tenant, HttpStatus.OK);
		    } else {
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		} catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} 
	
	@PatchMapping(value = "/tenant/approve/{orgName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Tenant> approveTenant(@PathVariable("orgName") String orgName, @RequestBody Tenant tenant){
		try {
			Tenant tenantDetails = tenantService.approveTenant(orgName, tenant);
			if (Objects.nonNull(tenantDetails)) {
		      return new ResponseEntity<>(tenantDetails, HttpStatus.OK);
		    } else {
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		} catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/tenant/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Tenant> deleteTenant(){
		try {
			tenantRepo.deleteAll();
			return new ResponseEntity<>(null, HttpStatus.OK);
		} catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
