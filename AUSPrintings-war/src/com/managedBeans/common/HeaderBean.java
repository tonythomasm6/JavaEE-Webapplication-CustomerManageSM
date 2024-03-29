package com.managedBeans.common;

import java.io.Serializable;
import java.security.Principal;
import java.util.Map;

import javax.el.ELContext;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.entities.Agent;

@Named
@SessionScoped
public class HeaderBean implements Serializable{

	private String loggedUserName;

	private String homeLink;
	
	private String isAdmin;
	
	public String getIsAdmin() {
		return isAdmin;
	}


	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}


	public String getHomeLink() {
		return homeLink;
	}


	public void setHomeLink(String homeLink) {
		this.homeLink = homeLink;
	}



	public String getLoggedUserName() {
		return loggedUserName;
	}



	public void setLoggedUserName(String loggedUserName) {
		this.loggedUserName = loggedUserName;
	}
	
	
	@ManagedProperty(value = "#{managedBeanRepository}")
	private ManagedBeanRepository managedBeanRepository;


	public HeaderBean() {
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		 managedBeanRepository = (ManagedBeanRepository) FacesContext.getCurrentInstance().getApplication()
	                .getELResolver().getValue(elContext, null, "managedBeanRepository");
		 Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		 if(principal != null) {
			 loggedUserName = principal.getName();
		 }
		 Agent agent = managedBeanRepository.getLoggedAgentDetails(loggedUserName);
		 
		 if(agent.getRole().equalsIgnoreCase("admin")) {
			 homeLink ="/faces/admin/home.xhtml";
			 isAdmin = "true";
		 }else {
			 homeLink = "/faces/staff/home.xhtml";
			 isAdmin = "false";
		 }
		
		loggedUserName = agent.getFirstName() + " " + agent.getLastName();
		
	}
}
