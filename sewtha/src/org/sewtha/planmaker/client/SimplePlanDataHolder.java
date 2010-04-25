package org.sewtha.planmaker.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;



public class SimplePlanDataHolder implements Serializable {
	private ProducerOrConsumerEnum type;
	private double usage = 0;
	private Collection<SimplePlanData> holder = new ArrayList();
	//private List<EnergyPlanJDO> jdoPlans;

	public SimplePlanDataHolder(ProducerOrConsumerEnum type, double usage) {
		this.type = type;
		this.usage = usage;
	}
	
//	public SimplePlanDataHolder(List<EnergyPlanJDO> plans){
//		for (EnergyPlanJDO db : plans){
//			
//		}
//	}
	
	public SimplePlanDataHolder(){
		
	}

	public ProducerOrConsumerEnum getType() {
		return type;
	}

	public double getUsage() {
		return usage;
	}
	
	public void addPlan(SimplePlanData plan){
		holder.add(plan);
	}
	
	public Collection<SimplePlanData> getSimplePlans(){
		return holder;
	}
	
	//public List<EnergyPlanJDO> getSimpleJDOPlans(){
	//	return jdoPlans;
	//}
}
