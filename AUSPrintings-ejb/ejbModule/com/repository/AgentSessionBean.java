package com.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.entities.Agent;
import com.entities.Customer;
import com.entities.IndustryType;


@Stateless
public class AgentSessionBean implements AgentRepository{

	
	@PersistenceContext(unitName="AUSPrintings-ejb")
    private EntityManager entityManager;
	
	public Agent getLoggedAgentDetails(String loggedUserName) {
		
		Agent agent = (Agent) entityManager.createQuery("SELECT a FROM Agent a where a.userName = :loggedUserName").setParameter("loggedUserName", loggedUserName).getSingleResult();
		return agent;
	}

	@Override
	public List<IndustryType> getAllIndustryTypes() {
		IndustryType i = new IndustryType();
		entityManager.refresh(i);
		List<IndustryType> allIndustryTypes = entityManager.createNamedQuery(IndustryType.GET_ALL_QUERY_NAME).getResultList();
		entityManager.refresh(allIndustryTypes);
		return allIndustryTypes;
	}

}
