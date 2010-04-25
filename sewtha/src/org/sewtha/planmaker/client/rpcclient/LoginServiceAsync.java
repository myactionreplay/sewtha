package org.sewtha.planmaker.client.rpcclient;


import org.sewtha.planmaker.client.SimplePlanData;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {

	void login(String requestUri, AsyncCallback<UserInfo> async);
	
	void addPlan(SimplePlanData plan, AsyncCallback<Boolean> async);

}
