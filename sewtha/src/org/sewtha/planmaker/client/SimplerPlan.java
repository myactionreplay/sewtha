package org.sewtha.planmaker.client;

import java.io.Serializable;

public class SimplerPlan implements Serializable {
	
	private String name;
	private long id;
	private boolean isGlobal = true;
	
	public SimplerPlan(String name, long id, boolean isGlobal){
		this.name = name;
		this.id = id;
		this.isGlobal = isGlobal;
	}
	
	public SimplerPlan(){
		this.name = "";
		this.id = new Long(0);
	}
	
	public boolean getIsGlobal(){
		return isGlobal;
	}
	
	public void setIsGlobal(boolean isGlobal){
		this.isGlobal = isGlobal;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}

}
