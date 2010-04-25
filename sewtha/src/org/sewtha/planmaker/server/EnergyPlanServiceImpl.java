package org.sewtha.planmaker.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.sewtha.planmaker.client.EnergyPlanException;
import org.sewtha.planmaker.client.SimplePlanData;
import org.sewtha.planmaker.client.SimplerPlan;
import org.sewtha.planmaker.client.rpcclient.EnergyPlanService;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class EnergyPlanServiceImpl extends RemoteServiceServlet implements
		EnergyPlanService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger
			.getLogger(EnergyPlanServiceImpl.class.getName());

	/**
	 * Adds an energy plan - even if a user is not logged in they can create an
	 * 'anonymous' plan which can then be viewed by others. Note that no plan
	 * will be added as Global. These can only be done statically.
	 */
	@Override
	public long addEnergyPlan(SimplePlanData energyPlan)
			throws EnergyPlanException {
		EnergyPlanJDO r = null;
		// Global plans will be added statically so double check here
		energyPlan.setGlobal(false);

		// we do allow anonymous updates of plans to datastore
		PersistenceManager pm = getPersistenceManager();
		LOG.info("Obtained persistence manager");
		try {

			r = pm.makePersistent(DataConversionUtils
					.convertClientPlanToJDOPlan(energyPlan, getUser()));
			LOG.info("made the JDO plan persistent");
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Problem with the persistence = "
					+ e.getLocalizedMessage());
		} finally {
			pm.close();
		}

		if (r == null) {
			return -1l;
		}
		return r.getId();
	}

	public long getTotalNumberOfPlans() {
		PersistenceManager pm = getPersistenceManager();
		long noOfPlans = 0;
		try {
			// get the global list
			Query q = pm.newQuery(EnergyPlanJDO.class);
			Collection<EnergyPlanJDO> plans = (Collection<EnergyPlanJDO>)q.execute();
			noOfPlans = plans.size();
			LOG.info("Number of Plans returned = " + noOfPlans);
		} finally {
			pm.close();
		}
		return noOfPlans;
	}

	public Collection<SimplerPlan> getAllPlanNames() throws EnergyPlanException {
		// check whether the user is logged in
		// we are noto checkin logged in at the moment
		// boolean loggedIn = false;
		// try {
		// checkUser();
		// loggedIn = true;
		// } catch (EnergyPlanException e) {
		// loggedIn = false;
		// }
		LOG.info("In getAllPlanNames method");
		List<EnergyPlanJDO> dbPlans;
		Collection<SimplerPlan> planNames = new ArrayList<SimplerPlan>();
		PersistenceManager pm = getPersistenceManager();
		try {
			// get the global list
			Query q = pm.newQuery(EnergyPlanJDO.class,
					"isGlobalPlan == isGlobalPlanParam");
			q.declareParameters("java.lang.Boolean isGlobalPlanParam");
			// q.setOrdering("createDate");
			dbPlans = (List<EnergyPlanJDO>) q.execute(true);
			for (EnergyPlanJDO e : dbPlans) {
				planNames.add(new SimplerPlan(e.getName(), e.getId(), e
						.isGlobalPlan()));
				LOG.info("long identifier placed in return for global plans = "
						+ new Long(e.getId()).toString());
			}
			// we are not doing logged in at the moment
			// if (loggedIn) {
			// // get the personal list
			// q = pm.newQuery(EnergyPlanJDO.class, "user == u");
			// q.declareParameters("com.google.appengine.api.users.User u");
			// // q.setOrdering("createDate");
			// dbPlans = (List<EnergyPlanJDO>) q.execute(getUser());
			// for (EnergyPlanJDO e : dbPlans) {
			// planNames.add(new SimplerPlan(e.getName(), e.getId(),
			// e.isGlobalPlan()));
			// LOG
			// .info("long identifier placed in return for personal plans = "
			// + new Long(e.getId()).toString());
			// }
			// }
		} finally {
			pm.close();
		}
		return planNames;
	}

	/**
	 * Anyone can get a plan - it is only on saving that we check the user is
	 * updating their own plan. Throws an exception if there is no plan found
	 */

	public SimplePlanData getEnergyPlan(long planId) throws EnergyPlanException {

		SimplePlanData plan;
		PersistenceManager pm = getPersistenceManager();
		try {
			// get the plan according to the number
			EnergyPlanJDO ePlan = pm.getObjectById(EnergyPlanJDO.class, planId);
			if (ePlan == null) {
				throw new EnergyPlanException("No Plan Found");
			}
			plan = DataConversionUtils.convertJDOPlanToClientPlan(ePlan);
		} finally {
			pm.close();
		}
		return plan;
	}

	/**
	 * Currently we are not doing logged in but will keep the code here
	 */
	public void updateEnergyPlan(SimplePlanData newPlan, long planId)
			throws EnergyPlanException {
		User u = getUser();
		if (u == null) {
			throw new EnergyPlanException(
					"You can only update plans if you are logged in");
		}
		PersistenceManager pm = getPersistenceManager();
		try {
			// get the identified plan
			EnergyPlanJDO ePlan = pm.getObjectById(EnergyPlanJDO.class, planId);
			if (ePlan == null) {
				throw new EnergyPlanException("No Plan Found");
			}
			// double check that the plan is not one of the global ones
			if (u.equals(ePlan.getUser()) && !ePlan.isGlobalPlan()) {
				DataConversionUtils.updateEnergyPlanJDOWithSimplePlan(ePlan,
						newPlan);
			} else {
				throw new EnergyPlanException(
						"Only users who created the plan can update it, but you can"
								+ " create another one for yourself");
			}

		} finally {
			pm.close();
		}

	}

	private PersistenceManager getPersistenceManager() {
		return LoginServiceImpl.PMF.getPersistenceManager();
	}

	private User getUser() {
		UserService userService = UserServiceFactory.getUserService();
		return userService.getCurrentUser();
	}

	private void checkUser() throws EnergyPlanException {
		User user = getUser();
		if (user == null) {
			throw new EnergyPlanException("User not logged in");
		}
	}

}
