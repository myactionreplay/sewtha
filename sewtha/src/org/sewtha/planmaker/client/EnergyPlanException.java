package org.sewtha.planmaker.client;

import java.io.Serializable;

public class EnergyPlanException extends Exception implements Serializable {

	public EnergyPlanException() {
	    super();
	  }

	  public EnergyPlanException(String message) {
	    super(message);
	  }

}
