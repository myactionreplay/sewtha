package org.sewtha.planmaker.client.rpcclient;

import java.util.Collection;

import org.sewtha.planmaker.client.EnergyPlanException;
import org.sewtha.planmaker.client.SimplePlanData;
import org.sewtha.planmaker.client.SimplerPlan;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("energyPlanService")

public interface EnergyPlanService extends RemoteService {
	
	/**
	 * Obtains the names of all plans - including personal plans and the 
	 * standard ones.
	 * @return
	 */
	Collection<SimplerPlan> getAllPlanNames() throws EnergyPlanException;
	
	/**
	 * Obtains the information about the specified plan 
	 * @param planId - identifier from the datastore
	 * @return
	 */
	SimplePlanData getEnergyPlan(long planId) throws EnergyPlanException;
	
	/**
	 * Adds  a new EnergyPlan
	 * @param energyPlan
	 * @return TODO
	 */
	long addEnergyPlan (SimplePlanData energyPlan) throws EnergyPlanException;
	
	/**
	 * Updates an existing energy plan - specifies the plan identifier
	 * @param newPlan
	 * @param planId
	 */
	void updateEnergyPlan (SimplePlanData newPlan, long planId) throws EnergyPlanException;
	
	/**
	 * Returns the number of all the plans
	 */
	long getTotalNumberOfPlans();
	 
	
}
