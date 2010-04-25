package org.sewtha.planmaker.client.rpcclient;

import org.sewtha.planmaker.client.SimplePlanData;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {

	public UserInfo login(String requestUri);
	
	public boolean addPlan(SimplePlanData plan);
}
