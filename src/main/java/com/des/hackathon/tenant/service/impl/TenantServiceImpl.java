package com.des.hackathon.tenant.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.des.hackathon.tenant.beans.AuthInfo;
import com.des.hackathon.tenant.beans.RealmsInfo;
import com.des.hackathon.tenant.beans.Tenant;
import com.des.hackathon.tenant.proxy.KeyClockProxy;
import com.des.hackathon.tenant.repository.TenantRepository;
import com.des.hackathon.tenant.service.TenantService;

@Service
public class TenantServiceImpl implements TenantService {

	@Autowired
	private TenantRepository tenantRepo;

	@Autowired
	private KeyClockProxy keyClockProxy;

	@Override
	public Tenant approveTenant(String orgName, Tenant tenant) {
	
		AuthInfo authInfo = keyClockProxy.autheticateService();

		if (null != authInfo) {
			RealmsInfo result = keyClockProxy.createRelam(authInfo.getAccess_token(), tenant);
			if (result != null) {
				keyClockProxy.createUserByRealms(authInfo.getAccess_token(), tenant);
			//	sendMail(tenant.getEmailId());
			}
		} 
		Optional<Tenant> tenantDetail = tenantRepo.findById(orgName);
		if (tenantDetail.isPresent()) {
			Tenant tenantDet = tenantDetail.get();
			tenantDet.setStatus("Approved");
			return addTenant(tenantDet);
		} else {
			return null;
		}

	}

	@Override
	public List<Tenant> getAllTenant() {
		List<Tenant> tenantList = tenantRepo.findAll();
		if (tenantList.isEmpty()) {
			return null;
		}
		return tenantList;
	}

	@Override
	public Tenant getTenantByOrgName(String orgName) {
		Optional<Tenant> tenant = tenantRepo.findById(orgName);
		if (tenant.isPresent()) {
			return tenant.get();
		} else {
			return null;
		}

	}

	@Override
	public Tenant rejectTenant(String orgName) {
		Optional<Tenant> tenant = tenantRepo.findById(orgName);
		if (tenant.isPresent()) {
			Tenant tenantDetail = tenant.get();
			tenantDetail.setStatus("Rejected");
			return addTenant(tenantDetail);
		} else {
			return null;
		}
	}

	@Override
	public Tenant addTenant(Tenant tenant) {
		Tenant tenantDetail = tenantRepo
				.save(new Tenant(tenant.getMobileNo(), tenant.getFirstName(), tenant.getLastName(), tenant.getOrgName(),
						tenant.getEmailId(), Objects.nonNull(tenant.getStatus()) ? tenant.getStatus() : "Pending"));
		return tenantDetail;
	}

	private static void sendMail(String emailId) {
	   Properties props = new Properties();
	   props.put("mail.smtp.auth", "true");
	   props.put("mail.smtp.starttls.enable", "true");
	   props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	   props.put("mail.smtp.host", "smtp.gmail.com");
	   props.put("mail.smtp.port", "587");
	   
	   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	      protected PasswordAuthentication getPasswordAuthentication() {
	         return new PasswordAuthentication("narendra629.hbti@gmail.com", "emcoayuzbfoyykmd");
	      }
	   });
	   Message msg = new MimeMessage(session);
	   try {
		   msg.setFrom(new InternetAddress("narendra629.hbti@gmail.com", true));
		
		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailId));
		   msg.setSubject("Welcome to thales");
		   msg.setContent("your account has been created use below url to access your account", "text/html");
		   msg.setSentDate(new Date());
		   MimeBodyPart messageBodyPart = new MimeBodyPart();
		   messageBodyPart.setContent("your account has been created use below url to access your account", "text/html");
		
		   Multipart multipart = new MimeMultipart();
		   multipart.addBodyPart(messageBodyPart);
		   MimeBodyPart attachPart = new MimeBodyPart();
		
		  // attachPart.attachFile("/var/tmp/image19.png");
		  // multipart.addBodyPart(attachPart);
		   msg.setContent(multipart);
		   Transport.send(msg);
	   } catch (AddressException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
			} catch (MessagingException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
			}
	   
	   
	}

}
