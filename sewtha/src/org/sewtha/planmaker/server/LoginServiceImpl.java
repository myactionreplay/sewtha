package org.sewtha.planmaker.server;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.sewtha.planmaker.client.SimplePlanData;
import org.sewtha.planmaker.client.rpcclient.LoginService;
import org.sewtha.planmaker.client.rpcclient.UserInfo;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class LoginServiceImpl extends RemoteServiceServlet implements
		LoginService {

	private static final Logger LOG = Logger.getLogger(LoginServiceImpl.class
			.getName());
	public static final PersistenceManagerFactory PMF = JDOHelper
			.getPersistenceManagerFactory("transactions-optional");
	private static final String USER_SESSION_STRING = "userSession";

	@Override
	public UserInfo login(String requestUri) {
		UserInfo userInfo = new UserInfo();
		User user = getUser();
		List<EnergyPlanJDO> dbPlans = null;
		if (user != null) {
			userInfo.setLoggedIn(true);
			userInfo.setEmailAddress(user.getEmail());
			userInfo.setNickname(user.getNickname());
			userInfo.setLogoutUrl(UserServiceFactory.getUserService()
					.createLogoutURL(requestUri));
			PersistenceManager pm = getPersistenceManager();
			try {
				// get the personal list
				Query q = pm.newQuery(EnergyPlanJDO.class, "user == u");
				q.declareParameters("com.google.appengine.api.users.User u");
				// q.setOrdering("createDate");
				dbPlans = (List<EnergyPlanJDO>) q.execute(getUser());

				List<SimplePlanData> plans = DataConversionUtils
						.convertJDOPlansToClientPlans(dbPlans);
				userInfo.setPlans(plans);
			} catch (Exception e) {
				LOG.log(Level.SEVERE, "Problem getting plans from db =  "+ e.getLocalizedMessage());
				//do not want to throw the exception upwards here
			} finally {
				pm.close();
			}
			return userInfo;
		} else {
			userInfo.setLoggedIn(false);
			userInfo.setLoginUrl(UserServiceFactory.getUserService()
					.createLoginURL(requestUri));
		}
		return userInfo;
	}

	@Override
	public boolean addPlan(SimplePlanData plan) {
		LOG.info("In Add Plan Method");
		User user = getUser();
		List<EnergyPlanJDO> dbPlans = null;
		if (user != null) {
			LOG.info("User is logged in so trying to persist");
			PersistenceManager pm = getPersistenceManager();
			try {
				LOG.info("About to make persistent");
				pm.makePersistent(DataConversionUtils
						.convertClientPlanToJDOPlan(plan, user));
			} finally {
				pm.close();
			}
			return true;
		} else {
			LOG.info("No User so returning false");
			return false;
		}
	}

	// public static List<SimplePlanData> convertJDOPlansToClientPlans(
	// List<EnergyPlanJDO> plans) {
	// List<SimplePlanData> r = new ArrayList();
	// for (EnergyPlanJDO dbPlan : plans) {
	// r.add(new SimplePlanData(dbPlan.isGlobalPlan(), dbPlan.getBarrage(),
	// dbPlan.getCleanCoal(), dbPlan.getElecLosses(), dbPlan
	// .getElecThings(), dbPlan.getGeothermal(), dbPlan
	// .getHeatingSavings(), dbPlan.getHydroHigh(), dbPlan
	// .getHydroLow(), dbPlan.getLagoons(), dbPlan
	// .getNuclear(), dbPlan.getOffshoreWindDeep(), dbPlan
	// .getOffshoreWindShallow(), dbPlan.getOnshoreWind(),
	// dbPlan.getPumpedHeat(), dbPlan.getSolarBiomass(), dbPlan
	// .getSolarDesert(), dbPlan.getSolarFarming(), dbPlan
	// .getSolarHeating(), dbPlan.getSolarPv(), dbPlan
	// .getTidal(), dbPlan.getTransportSavings(), dbPlan
	// .getWaste(), dbPlan.getWave(), dbPlan.getWood(), dbPlan.getName(),
	// dbPlan.getDescription(), dbPlan.getId()));
	// }
	// return r;
	// }

	// public static EnergyPlanJDO convertClientPlanToJDOPlans(
	// SimplePlanData clientPlan, User user) {
	//
	// //if the plan is sent from a user it will always be non-global
	// boolean isGlobal = false;
	// EnergyPlanJDO r = new EnergyPlanJDO(user, isGlobal,
	// clientPlan.getBarrage(),
	// clientPlan.getCleanCoal(), clientPlan.getElecLosses(),
	// clientPlan.getElecThings(), clientPlan.getGeothermal(),
	// clientPlan.getHeatingSavings(), clientPlan.getHydroHigh(),
	// clientPlan.getHydroLow(), clientPlan.getLagoons(), clientPlan
	// .getNuclear(), clientPlan.getOffshoreWindDeep(),
	// clientPlan.getOffshoreWindShallow(), clientPlan
	// .getOnshoreWind(), clientPlan.getPumpedHeat(),
	// clientPlan.getSolarBiomass(), clientPlan.getSolarDesert(),
	// clientPlan.getSolarFarming(), clientPlan.getSolarHeating(),
	// clientPlan.getSolarPv(), clientPlan.getTidal(), clientPlan
	// .getTransportSavings(), clientPlan.getWaste(),
	// clientPlan.getWave(), clientPlan.getWood());
	//
	// return r;
	// }

	private PersistenceManager getPersistenceManager() {
		return PMF.getPersistenceManager();
	}

	private User getUser() {
		UserService userService = UserServiceFactory.getUserService();
		return userService.getCurrentUser();
	}

}
