/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sewtha.planmaker.client;

import java.util.ArrayList;
import java.util.Collection;

import org.sewtha.planmaker.client.maps.RenewableAreaGeogTypeEnum;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.maps.client.overlay.Polygon;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author BarnabyK
 * @date
 */
public abstract class EnergyProducerOrConsumer {

	private EnergyProducerOrConsumerGroup eGroup;
	private ProducerOrConsumerEnum eType;
	private NumberFormat df;
	private NumberFormat threeDf;
	private NumberFormat nf;
	private NumberFormat pf;
	private String dfStr = "###,###,##0.##";
	private String numStr = "##,###,###";
	private String threeDfStr = "###,###,##0.####";
	private String percStr = "0.00%";
	protected EnergyPlan planHolder = null;
	private Collection<Overlay> oColl = new ArrayList();

	// sub class will change if there is an equivalent
	private boolean anyEquivalent = false;
	// set as decimal rather than percentage first subclasses can overcome
	private boolean isDecimal = true;
	private int index;
	private boolean shownInQuestion = true;
	private int questionId = -1;
	// default years to implement is 10
	protected int yearsToImplement = NaturalConstants.DEFAULT_YEARS_TO_IMPLEMENT;
	private double targetUsage = -1;

	/**
	 * This constructor is used when it definately will be in the question
	 * 
	 * @param eGroup
	 * @param eType
	 * @param index
	 */
	public EnergyProducerOrConsumer(EnergyProducerOrConsumerGroup eGroup,
			ProducerOrConsumerEnum eType, int index, EnergyPlan planHolder) {
		shownInQuestion = true;
		this.planHolder = planHolder;
		setUpClass(eGroup, eType, index);
		questionId = planHolder.getCurrentQuestionCounter();
		planHolder.incrementCurrentQuestionCounter();
	}

	public EnergyProducerOrConsumer(EnergyProducerOrConsumerGroup eGroup,
			ProducerOrConsumerEnum eType, int index, boolean shownInQuestion,
			EnergyPlan planHolder) {
		this.shownInQuestion = shownInQuestion;
		this.planHolder = planHolder;
		if (shownInQuestion) {
			questionId = planHolder.getCurrentQuestionCounter();
			planHolder.incrementCurrentQuestionCounter();
		}
		setUpClass(eGroup, eType, index);
	}

	private void setUpClass(EnergyProducerOrConsumerGroup eGroup,
			ProducerOrConsumerEnum eType, int index) {
		this.eGroup = eGroup;
		df = NumberFormat.getFormat(dfStr);
		nf = NumberFormat.getFormat(numStr);
		// pf = NumberFormat.getPercentFormat();
		pf = NumberFormat.getFormat(percStr);
		threeDf = NumberFormat.getFormat(threeDfStr);
		this.eType = eType;
		this.index = index;
	}

	public boolean getShownInQuestion() {
		return shownInQuestion;
	}

	public int getQuestionId() {
		return questionId;
	}

	protected NumberFormat getThreeDecumalFormat() {
		return threeDf;
	}

	protected NumberFormat getPercentageFormat() {
		return pf;
	}

	protected NumberFormat getNumberFormat() {
		return nf;
	}

	protected NumberFormat getDecimalFormat() {
		return df;
	}

	public boolean getIsDecimal() {
		return isDecimal;
	}

	public double getStepSizeForSlider() {
		if (getIsDecimal()) {
			return UILookAndFeel.STEP_SIZE;
		}
		return UILookAndFeel.PERCENTAGE_STEP_SIZE;
	}

	/**
	 * Calls the container to fire an event to any interested listeners that
	 * tells them that a value has changed
	 * 
	 * @param metricValue
	 * @param KWHdpValue
	 */
	protected void fireEventThroughContainer() {
		planHolder.setValueChanged(planHolder.getSelectedComponent());
	}

	/**
	 * This is used if an energy source is changed rather than just a value
	 * 
	 * @param e
	 */
	protected void fireEventThroughContainer(EnergyProducerOrConsumer e) {
		planHolder.setValueChanged(e);
	}

	public abstract String getDescription();

	public abstract double getMinUsage();

	public abstract double getMaxUsage();

	public abstract String getQuestionForChange();

	public abstract double getKWHdp();

	// could be a number of different values or metrics - eg percentage etc
	public abstract double setNewUsage(double changedValue);

	/**
	 * Gets the current usage value
	 * 
	 * @return
	 */
	public abstract double getCurrentUsageValue();

	public EnergyProducerOrConsumerGroup getEnergyOrConsumerGroup() {
		return eGroup;
	}

	public ProducerOrConsumerEnum getEnergyType() {
		return eType;
	}

	public boolean anyEquivalent() {
		return anyEquivalent;
	}

	public void setAnyEquivalent(boolean setEquiv) {
		this.anyEquivalent = setEquiv;
	}

	/**
	 * Sets whether the main measure (not equivalent) is a percentage measure or
	 * decimal based measure. e.g. wind will be percentage as the question asks
	 * for the percentage of available land, Nuclear will be decimal as it asks
	 * for the number of power stations.
	 * 
	 * @param isDecimal
	 */
	public void setIsDecimal(boolean isDecimal) {
		this.isDecimal = isDecimal;
	}

	public String getPlannedUsageValueStr() {
		String s = "";
		if (isDecimal) {
			s = getNumberFormat().format(getCurrentUsageValue());
		} else {
			s = getPercentageFormat().format(getCurrentUsageValue());
		}
		return s;
	}

	public abstract String getUsageMetricString();

	/**
	 * MAKE ABSTRACT
	 * 
	 * @return
	 */
	public String getPlannedEquivalentValue() {
		return "";
	}

	/**
	 * MAKE ABSTRACT
	 * 
	 * @return
	 */
	public String getEquivalentMetricString() {
		return "";
	}

	public String get2006UsageMetricStr() {
		String s;
		if (isDecimal) {
			s = getThreeDecumalFormat().format(get2006KWHdpMetric());
		} else {
			s = getPercentageFormat().format(get2006KWHdpMetric());
		}
		return s;
	}

	/**
	 * MAKE ABSTRACT
	 * 
	 * @return
	 */
	public String getTodayEquivalentStr() {
		return "";
	}

	public String getPercentageIncreaseStr() {
		String s = getPercentageFormat().format(
				(getCurrentUsageValue() / get2006KWHdpMetric()) - 1);
		return s;
	}

	/**
	 * MAKE ABSTRACT
	 * 
	 * @return
	 */
	public abstract double get2006KWHdp();

	public abstract double get2006KWHdpMetric();

	// public int populateSelectionDescArea(FlexTable table, int rowToPopulate)
	// {

	public VerticalPanel populateSelectionDescArea() {
		VerticalPanel r = new VerticalPanel();
		r.setSpacing(2);
		HorizontalPanel r1 = new HorizontalPanel();
		Label r1_1Label = new Label("You have selected ");
		r1_1Label.addStyleName("inspectorDescLeft");
		r1.add(r1_1Label);
		Label r1_2Label = new Label(getPlannedUsageValueStr());
		r1_2Label.addStyleName("inspectorDescCentre");
		r1.add(r1_2Label);
		Label r1_3Label = new Label(getUsageMetricString());
		r1_3Label.addStyleName("inspectorDescRight");
		r1.add(r1_3Label);
		r.add(r1);
		if (anyEquivalent()) {
			HorizontalPanel r2 = new HorizontalPanel();
			Label r2_1Label = new Label("This is equivalent to ");
			r2_1Label.addStyleName("inspectorDescLeft");
			r2.add(r2_1Label);
			Label r2_2Label = new Label(getPlannedEquivalentValue());
			r2_2Label.addStyleName("inspectorDescCentre");
			r2.add(r2_2Label);
			Label r2_3Label = new Label(getEquivalentMetricString());
			r2_3Label.addStyleName("inspectorDescRight");
			r2.add(r2_3Label);
			r.add(r2);
		}
		HorizontalPanel r3 = new HorizontalPanel();
		Label r3_1Label = new Label("Currently there is ");
		r3_1Label.addStyleName("inspectorDescLeft");
		r3.add(r3_1Label);
		Label r3_2Label = new Label(get2006UsageMetricStr());
		r3_2Label.addStyleName("inspectorDescCentre");
		r3.add(r3_2Label);
		Label r3_3Label = new Label(getUsageMetricString());
		r3_3Label.addStyleName("inspectorDescRight");
		r3.add(r3_3Label);
		r.add(r3);
		if (anyEquivalent()) {
			HorizontalPanel r4 = new HorizontalPanel();
			Label r4_1Label = new Label("Equivalent to ");
			r4_1Label.addStyleName("inspectorDescLeft");
			r4.add(r4_1Label);
			Label r4_2Label = new Label(getTodayEquivalentStr());
			r4_2Label.addStyleName("inspectorDescCentre");
			r4.add(r4_2Label);
			Label r4_3Label = new Label(getEquivalentMetricString());
			r4_3Label.addStyleName("inspectorDescRight");
			r4.add(r4_3Label);
			r.add(r4);
		}
		// only do a percentage increase if there is something to increase
		if (get2006KWHdp() > 0) {
			HorizontalPanel r5 = new HorizontalPanel();
			Label r5_1Label = new Label("Your plan requires a ");
			r5_1Label.addStyleName("inspectorDescLeft");
			r5.add(r5_1Label);
			Label r5_2Label = new Label(getPercentageIncreaseStr());
			r5_2Label.addStyleName("inspectorDescCentre");
			r5.add(r5_2Label);
			Label r5_3Label = new Label(" increase on the current value");
			r5_3Label.addStyleName("inspectorDescRight");
			r5.add(r5_3Label);
			r.add(r5);
		}
		return r;

		// table.setWidget(rowToPopulate, 0, new Label("You have selected "));
		// table.setWidget(rowToPopulate, 1, new
		// Label(getPlannedUsageValueStr()));
		// table.setWidget(rowToPopulate, 2, new Label(getUsageMetricString()));
		// rowToPopulate++;
		// if (anyEquivalent()) {
		// table.setWidget(rowToPopulate, 0, new Label(
		// "This is equivalent to "));
		// table.setWidget(rowToPopulate, 1, new Label(
		// getPlannedEquivalentValue()));
		// table.setWidget(rowToPopulate, 2, new Label(
		// getEquivalentMetricString()));
		// rowToPopulate++;
		// }
		// table.setWidget(rowToPopulate, 0, new Label("Currently there is "));
		// table.setWidget(rowToPopulate, 1, new
		// Label(get2006UsageMetricStr()));
		// table.setWidget(rowToPopulate, 2, new Label(getUsageMetricString()));
		// rowToPopulate++;
		// if (anyEquivalent()) {
		// table.setWidget(rowToPopulate, 0, new Label("Equivalent to "));
		// table.setWidget(rowToPopulate, 1,
		// new Label(getTodayEquivalentStr()));
		// table.setWidget(rowToPopulate, 2, new Label(
		// getEquivalentMetricString()));
		// rowToPopulate++;
		// }
		// // only do a percentage increase if there is something to increase
		// if (getMinUsage() > 0) {
		// rowToPopulate++;
		// table.setWidget(rowToPopulate, 0,
		// new Label("Your plan requires a "));
		// table.setWidget(rowToPopulate, 1, new Label(
		// getPercentageIncreaseStr()));
		// table.setWidget(rowToPopulate, 2, new Label(
		// " increase on the current usage"));
		// }
		// return rowToPopulate;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getKWHdpStr() {
		return getNumberFormat().format(getKWHdp()) + " kWh/d";
	}

	public String getName() {
		String clsname = this.getClass().toString();
		int mid = clsname.lastIndexOf('.') + 1;
		String finalClsName = clsname.substring(mid);
		return finalClsName;
	}

	public int getYearsToImplement() {
		return yearsToImplement;
	}

	public void setYearsToImplement(int yearsToImplement) {
		this.yearsToImplement = yearsToImplement;
		fireEventThroughContainer();
	}

	/**
	 * This should only be called if the plan is used for simulation over time
	 * ideally this would throw an exception if not but this is not done yet.
	 * 
	 * @return
	 */
	public double getTargetUsage() {
		return targetUsage;
	}

	/**
	 * This should only be set if being used as part of a simulation for motion
	 * graphs.
	 * 
	 * @param targetUsage
	 */
	public void setTargetUsage(double targetUsage) {
		this.targetUsage = targetUsage;
	}

	/**
	 * Returns the visualisation currently set up for this energy
	 * 
	 * @return
	 */
	public Collection<Overlay> getVisualization() {
		return oColl;
	}

	/**
	 * Adds an overlay for this energy producer or consumer
	 */
	public void addOverlay(Overlay o) {
		oColl.add(o);
	}

	/**
	 * Removes an overlay for this energy producer or consumer
	 */
	public void removeOverlay(Overlay o) {
		oColl.remove(o);
	}

	public void replaceOverlay(Overlay oToReplace, Overlay oReplacement) {
		oColl.remove(oToReplace);
		oColl.add(oReplacement);
	}

	/**
	 * Gets a number returning the amount that should have been plotted
	 * according to the plan.
	 * 
	 * @return
	 */
	public float getVisualisationAreaPlotted() {
		return -1;
	}

	/**
	 * Gets a number returning the amount that should have been plotted. Note
	 * that if this is point objects they may be the number in the collection
	 * but if it is area based it may be the total amount.
	 * 
	 * @return
	 */
	public float getVisualisationAreaRequired() {
		return -1;
	}

	/**
	 * Gets the percentage of the total visualisation required that has been
	 * plotted.
	 * 
	 * @return
	 */
	public int getVisualisationPercentagePlotted() {
		return -1;
	}

	/**
	 * Gets the type of renewable area we are expecting. Note that we will
	 * probably use polygons for everything - even points will be circles.
	 * 
	 * @return
	 */
	public RenewableAreaGeogTypeEnum getVisualisationType() {
		return null;
	}
}
