package org.sewtha.planmaker.client.rpcclient;

import java.util.Collection;

import org.sewtha.planmaker.client.SimplePlanData;
import org.sewtha.planmaker.client.SimplerPlan;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EnergyPlanServiceAsync {

	
	void getAllPlanNames(AsyncCallback<Collection<SimplerPlan>> callback);
	
	void getEnergyPlan(long planId, AsyncCallback<SimplePlanData> callback);
	
	void addEnergyPlan (SimplePlanData energyPlan, AsyncCallback<Long> callback);
	
	void updateEnergyPlan (SimplePlanData newPlan, long planId, AsyncCallback<Void> callback);
	
	void getTotalNumberOfPlans(AsyncCallback<Long> callback);

}
