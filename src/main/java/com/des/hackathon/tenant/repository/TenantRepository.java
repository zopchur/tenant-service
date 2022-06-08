package com.des.hackathon.tenant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.des.hackathon.tenant.beans.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, String>{
	

}
