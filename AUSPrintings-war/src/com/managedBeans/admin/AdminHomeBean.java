package com.managedBeans.admin;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.entities.Agent;
import com.entities.IndustryType;
import com.managedBeans.common.ManagedBeanRepository;


@Named
@RequestScoped
public class AdminHomeBean implements Serializable{

	private String loggedUserName;
	

	public String getLoggedUserName() {
		return loggedUserName;
	}

	public void setLoggedUserName(String loggedUserName) {
		this.loggedUserName = loggedUserName;
	}

	@ManagedProperty(value = "#{managedBeanRepository}")
	private ManagedBeanRepository managedBeanRepository;
	
	public AdminHomeBean() {
		Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		 if(principal != null) {
			 loggedUserName = principal.getName();
		 }
		 
		 ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		 managedBeanRepository = (ManagedBeanRepository) FacesContext.getCurrentInstance().getApplication()
	                .getELResolver().getValue(elContext, null, "managedBeanRepository");
	        
	        
		 getLoggedAgentDetails(loggedUserName);
		 
		 
		 
	}
	
	public void getLoggedAgentDetails(String loggedUserName) {
		Agent agent = managedBeanRepository.getLoggedAgentDetails(loggedUserName);
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		
		loggedUserName = agent.getFirstName() + " " + agent.getLastName();
		
		sessionMap.put("loggedUserName", loggedUserName);
		sessionMap.put("loggedAgent", agent);
		
		
	}
	

}
