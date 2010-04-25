/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sewtha.planmaker.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;

/**
 * 
 * @author BarnabyK
 */
public class DataVisualisationEnergyModel {

	public static final int PRODUCERS_ROW = 0;
	public static final int CONSUMERS_ROW = 1;
	public static final int MOTION_NAME_COL = 0;
	public static final int MOTION_DATE_COL = 1;
	public static final int MOTION_KWHD_COL = 2;
	public static final int MOTION_PROD_OR_CONS_ROW = 4;
	private static DataVisualisationEnergyModel instance;
	private int month = 1;
	private int day = 1;
	private int currentYear = NaturalConstants.CURRENT_YEAR;
	private int MOTION_PERCENTAGE_SHARE = 3;

	public static DataVisualisationEnergyModel getInstance() {
		if (instance == null) {
			instance = new DataVisualisationEnergyModel();
		}
		return instance;
	}

	public DataTable getDataTableForVis() {
		// set up table
		DataTable data = DataTable.create();
		EnergyPlan container = EnergyPlan.getMainInstance();
		data.addRows(2);

		// do Energy Producer Row
		data.addColumn(ColumnType.STRING);
		data.setValue(PRODUCERS_ROW, 0, "Production");
		int i = 1;
		for (EnergyProducerOrConsumerGroup e : container
				.getAllEnergyProducerGroups()) {
			data.addColumn(ColumnType.NUMBER, e.getEnergyGroupName());
			data.setValue(PRODUCERS_ROW, i, e.getKWHdpForGroup());
			e.setGraphModelRowAndColumn(PRODUCERS_ROW, i);
			i++;
		}
		// do Energy Consumption Row
		data.setValue(CONSUMERS_ROW, 0, "Consumption");
		for (EnergyProducerOrConsumerGroup e : container
				.getAllEnergyConsumerGroups()) {
			data.addColumn(ColumnType.NUMBER, e.getEnergyGroupName());
			data.setValue(CONSUMERS_ROW, i, e.getKWHdpForGroup());
			e.setGraphModelRowAndColumn(CONSUMERS_ROW, i);
			i++;
		}
		return data;
	}

	// public AbstractDataTable getStaticData(){
	// DataTable data = DataTable.create();
	//
	// data.addColumn(ColumnType.STRING, "Fruit");
	// data.addColumn(ColumnType.DATE, "Date");
	// data.addColumn(ColumnType.NUMBER, "Sales");
	// data.addColumn(ColumnType.NUMBER, "Expenses");
	// data.addColumn(ColumnType.STRING, "Location");
	// data.addRow();
	// int futureYear = 2008;
	// int month = 1;
	// int day = 1;
	// int rowN = 0;
	// data.setValue(rowN, 0, "Apples");
	// data.setValue(rowN, 1, new Date(futureYear, month, day));
	// data.setValue(rowN, 2, 1111);
	// data.setValue(rowN, 3, 777);
	// data.setValue(rowN, 4, "Bris");
	// data.addRow();
	// rowN++;
	// day++;
	// data.setValue(rowN, 0, "Oranges");
	// data.setValue(rowN, 1, new Date(futureYear, month, day));
	// data.setValue(rowN, 2, 1155);
	// data.setValue(rowN, 3, 77722);
	// data.setValue(rowN, 4, "Birm");
	// data.addRow();
	// rowN++;
	// day++;
	// data.setValue(rowN, 0, "Apples");
	// data.setValue(rowN, 1, new Date(futureYear, month, day));
	// data.setValue(rowN, 2, 1199);
	// data.setValue(rowN, 3, 333);
	// data.setValue(rowN, 4, "Lon");
	// return data;
	// }

	public AbstractDataTable getDataTableForMotionVis() {
		DataTable data = DataTable.create();
		EnergyPlan container = EnergyPlan.getMainInstance();
		int row = 0;

		// name of data
		data.addColumn(ColumnType.STRING, "Energy Type");
		// date
		data.addColumn(ColumnType.DATE, "Date");
		// energy hours
		data.addColumn(ColumnType.NUMBER, "kWh/d");
		// random row
		data.addColumn(ColumnType.NUMBER, "Percentage Share");
		// producer of consumer row
		data.addColumn(ColumnType.STRING, "Energy Group");
		// TimePlanner t = new TimePlanner();
		// row = populateDataTable(data, container.getAllEnergyProducerGroups(),
		// row, "Production", true, t);
		// row = populateDataTable(data, container.getAllEnergyConsumerGroups(),
		// row, "Consumption", false, t);
		// row = populateDataTableForFossilFuel(row, data, t);
		data = setUpDataTable(data);
		return data;
	}

	/**
	 * Adds data to the table and returns the number of the new row
	 * 
	 * @return
	 * @SuppressWarnings("deprecation")
	 */
	private DataTable setUpDataTable(DataTable data) {
		int row = 0;
		EnergyPlanClone e = (EnergyPlan.getMainInstance()).clone();
		int longestImplInPlan = e.getLongestYearImplementation();
		int i = 0;
		int futureYear = 0;
		while (i <= longestImplInPlan) {
			futureYear = currentYear + i;
			for (EnergyProducerOrConsumerGroup g : e
					.getAllEnergyProducerGroups()) {
				for (EnergyProducerOrConsumer eComp : g.getAllEnergyInGroup()) {
					String eGroup = "Renewable Energy";
					if (eComp.getEnergyType() == ProducerOrConsumerEnum.FOSSIL_FUELS){
						eGroup = "Fossil Fuels";
					}
					// only fill out a year if it is the last year of the
					// energy as we do not want too many steps
					//but always fill out the first year and always fill out
					//fossil fuels
					if (i == eComp.getYearsToImplement()
							|| i == 0
							|| eComp.getEnergyType() == ProducerOrConsumerEnum.FOSSIL_FUELS) {
						data.addRow();
						data.setValue(row, MOTION_NAME_COL, eComp.getName());
						data.setValue(row, MOTION_DATE_COL, new Date(
								futureYear, month, day));
						data.setValue(row, MOTION_KWHD_COL, eComp.getKWHdp());
						data.setValue(row, MOTION_PERCENTAGE_SHARE, (eComp
								.getKWHdp() / e.getProducerKWHdp(false)) * 100);
						data.setValue(row, MOTION_PROD_OR_CONS_ROW, eGroup);
						row++;
					} // end stop in case the energy has not gone up to the end
					else if (i == longestImplInPlan) {
						data.addRow();
						data.setValue(row, MOTION_NAME_COL, eComp.getName());
						data.setValue(row, MOTION_DATE_COL, new Date(
								futureYear, month, day));
						data.setValue(row, MOTION_KWHD_COL, eComp.getKWHdp());
						data.setValue(row, MOTION_PERCENTAGE_SHARE, (eComp
								.getKWHdp() / e.getProducerKWHdp(false)) * 100);
						data.setValue(row, MOTION_PROD_OR_CONS_ROW, eGroup);
						row++;
					}
				}
			}

			// consumers so they can be shown
			// for (EnergyProducerOrConsumerGroup g : e
			// .getAllEnergyConsumerGroups()) {
			// for (EnergyProducerOrConsumer eComp : g.getAllEnergyInGroup()) {
			// // only fill out a year if the energy is not complete
			// // and it is not the last year as we do need an endstop
			// if (i <= eComp.getYearsToImplement()
			// && i <= longestImplInPlan) {
			// data.addRow();
			// data.setValue(row, MOTION_NAME_COL, eComp.getName());
			// data.setValue(row, MOTION_DATE_COL, new Date(
			// futureYear, month, day));
			// data.setValue(row, MOTION_KWHD_COL, eComp.getKWHdp());
			// data.setValue(row, MOTION_PERCENTAGE_SHARE, eComp
			// .getKWHdp()
			// / e.getProducerKWHdp(false));
			// data.setValue(row, MOTION_PROD_OR_CONS_ROW, g
			// .getEnergyGroupName());
			// row++;
			// }
			// }
			// }

			e.rollSimulatorOnAYear();
			i++;
		}
		return data;
	}

	// @SuppressWarnings("deprecation")
	// private int populateDataTable(DataTable data,
	// Collection<EnergyProducerOrConsumerGroup> container, int row,
	// String prodOrConsum, boolean isProduction, TimePlanner t) {
	//
	// int getLongestYear = getLongestYearImplementation(container);
	// int yearsToImpl = 0;
	// for (EnergyProducerOrConsumerGroup g : container) {
	// for (EnergyProducerOrConsumer e : g.getAllEnergyInGroup()) {
	// if (e.getEnergyType() != ProducerOrConsumerEnum.FOSSIL_FUELS) {
	// yearsToImpl = e.getYearsToImplement();
	// int i = 0;
	// double yearlyInc = getYearlyIncrease(e, yearsToImpl);
	// while (i <= yearsToImpl) {
	// setDataTableValues(data, row, g, e, i, yearlyInc, t,
	// isProduction);
	// row++;
	// i++;
	// }
	// row = calculateEndStopYear(data, row, getLongestYear,
	// yearsToImpl, g, e, currentYear + getLongestYear);
	// }
	// }
	// }
	// return row;
	// }
	// //
	// private int populateDataTableForFossilFuel(int row, DataTable data,
	// TimePlanner t) {
	// EnergyProducerOrConsumer fossE = EnergyPlan.getInstance()
	// .getEnergyProducerOrConsumer(
	// ProducerOrConsumerEnum.FOSSIL_FUELS);
	// EnergyProducerOrConsumerGroup fossG = fossE.getEnergyOrConsumerGroup();
	// row = createFossilFuelsBalancingFigure(data, row, fossE, fossG, t);
	// return row;
	// }

	// private int createFossilFuelsBalancingFigure(DataTable data, int row,
	// EnergyProducerOrConsumer e, EnergyProducerOrConsumerGroup g,
	// TimePlanner t) {
	// int i = currentYear;
	// int lastYear = t.getLastYear();
	// while (i <= lastYear) {
	// YearTracker y = t.getYear(i, false);
	// // there may not have been a year entered for this one?
	// if (y != null) {
	// double yearlyCompounded = +y.getBalanceForYear();
	// double kWhdForYear = e.get2006KWHdp() + yearlyCompounded;
	// double percentageForYear = calculatePercentageShare(i, e,
	// kWhdForYear);
	// data.addRow();
	// data.setValue(row, MOTION_NAME_COL, e.getName());
	// data.setValue(row, MOTION_DATE_COL, new Date(i, month, day));
	// data.setValue(row, MOTION_KWHD_COL, kWhdForYear);
	// data.setValue(row, MOTION_PERCENTAGE_SHARE, percentageForYear);
	// data.setValue(row, MOTION_PROD_OR_CONS_ROW, g
	// .getEnergyGroupName());
	// row++;
	// }
	// i++;
	// }
	// return row;
	// }
	//
	// private void setDataTableValues(DataTable data, int row,
	// EnergyProducerOrConsumerGroup g, EnergyProducerOrConsumer e, int i,
	// double yearlyInc, TimePlanner t, boolean isProduction) {
	// int futureYear;
	// futureYear = currentYear + i;
	// double kWhdForYear = e.get2006KWHdp() + (yearlyInc * i);
	// double percentageForYear = calculatePercentageShare(i, e, kWhdForYear);
	// if (isProduction) {
	// data.addRow();
	// data.setValue(row, MOTION_NAME_COL, e.getName());
	// data.setValue(row, MOTION_DATE_COL,
	// new Date(futureYear, month, day));
	// data.setValue(row, MOTION_KWHD_COL, row+10);
	// data.setValue(row, MOTION_PERCENTAGE_SHARE, row/10);
	// data.setValue(row, MOTION_PROD_OR_CONS_ROW, g.getEnergyGroupName());
	// }
	// YearTracker y = t.getYear(futureYear, true);
	// if (isProduction) {
	// // adds to the cumulative total
	// y.setProduction(kWhdForYear);
	// } else {
	// // adds to the cumulative totals
	// y.setConsumption(kWhdForYear);
	// }
	//
	// }
	//
	// /**
	// * Calculates the endstop year so that the motion chart has something to
	// go
	// * to, rather than just ending.
	// *
	// * @param data
	// * @param row
	// * @param getLongestYear
	// * @param yearsToImpl
	// * @param g
	// * @param e
	// * @param futureYear
	// * @return
	// */
	// private int calculateEndStopYear(DataTable data, int row,
	// int getLongestYear, int yearsToImpl,
	// EnergyProducerOrConsumerGroup g, EnergyProducerOrConsumer e,
	// int futureYear) {
	// // check whether we need to put in a value at the end
	// if (yearsToImpl < getLongestYear) {
	// // if it has finished early we need to put in a final one to keep it
	// // there
	// data.addRow();
	// data.setValue(row, MOTION_NAME_COL, e.getName());
	// data.setValue(row, MOTION_DATE_COL,
	// new Date(futureYear, month, day));
	// data.setValue(row, MOTION_KWHD_COL, e.getKWHdp());
	// double percentageShareAtEnd = calculatePercentageShare(e
	// .getYearsToImplement(), e, e.getKWHdp());
	// data.setValue(row, MOTION_PERCENTAGE_SHARE, percentageShareAtEnd);
	// data.setValue(row, MOTION_PROD_OR_CONS_ROW, g.getEnergyGroupName());
	// row++;
	// }
	// return row;
	// }
	//
	// /**
	// * Gets the longest number of years to implement the change. This is
	// * currently for just one implementation.
	// *
	// * @param container
	// * @return
	// */
	// private int getLongestYearImplementation(
	// Collection<EnergyProducerOrConsumerGroup> container) {
	// int yearsToImpl = 0;
	// for (EnergyProducerOrConsumerGroup g : container) {
	// for (EnergyProducerOrConsumer e : g.getAllEnergyInGroup()) {
	// if (e.getYearsToImplement() > yearsToImpl) {
	// yearsToImpl = e.getYearsToImplement();
	// }
	// }
	// }
	// return yearsToImpl;
	// }
	//
	// /**
	// * Calculates the percentage share of the individual producer compared to
	// * the total amount. Please note that this percentage considers the fossil
	// * fuel amount to be a balancing figure rather than the actual amount it
	// is
	// * in the plan.
	// *
	// * @param yearInFocus
	// * @param e
	// * @param kWhdForYear
	// * @return
	// */
	// public double calculatePercentageShare(int yearInFocus,
	// EnergyProducerOrConsumer e, double kWhdForYear) {
	// double currentProdForYearNotIncFossilFuels =
	// calculateCurrentNonFossilFuelTotalProduction(yearInFocus);
	// double currentConsumptionForYear =
	// calculateCurrentTotalConsumption(yearInFocus);
	// double fossilFuelBalance = createFossilFuelBalance(
	// currentProdForYearNotIncFossilFuels, currentConsumptionForYear);
	// double percentageShare = 0;
	// if (e.getEnergyType() != ProducerOrConsumerEnum.FOSSIL_FUELS) {
	// percentageShare = (kWhdForYear / currentConsumptionForYear) * 100;
	// } else {
	// percentageShare = (fossilFuelBalance / currentConsumptionForYear) * 100;
	// }
	// if (percentageShare < 0) {
	// int i = 0;
	// i++;
	// }
	// return percentageShare;
	// }
	//
	// /**
	// * Calculates the fossil fuel kWhd as a balancing figure
	// *
	// * @param currentProdForYearNotIncFossilFuels
	// * @param currentConsumptionForYear
	// * @return
	// */
	// private double createFossilFuelBalance(
	// double currentProdForYearNotIncFossilFuels,
	// double currentConsumptionForYear) {
	// double fossilFuelBalance = currentConsumptionForYear
	// - currentProdForYearNotIncFossilFuels;
	// if (fossilFuelBalance < 0) {
	// fossilFuelBalance = 0;
	// }
	// return fossilFuelBalance;
	// }
	//
	// private double getYearlyIncrease(EnergyProducerOrConsumer e, int
	// yearsToImpl) {
	// double yearlyInc = (e.getKWHdp() - e.get2006KWHdp()) / yearsToImpl;
	// return yearlyInc;
	// }

	// private double calculateCurrentTotalConsumption(int yearNum) {
	// int currentYear = yearNum;
	// double currentTotalConsumption = 0;
	// Collection<EnergyProducerOrConsumerGroup> c = EnergyPlan.getInstance()
	// .getAllEnergyConsumerGroups();
	// for (EnergyProducerOrConsumerGroup group : c) {
	// for (EnergyProducerOrConsumer e : group.getAllEnergyInGroup()) {
	// // if the years to implement is less than the year number,
	// // equalise
	// if (e.getYearsToImplement() < currentYear) {
	// currentYear = e.getYearsToImplement();
	// }
	// // add up current consumption by taking the yearly increase
	// // multiplied by
	// // the number of years and subtracted from the final kWhd
	// double calcAmount = e.get2006KWHdp()
	// + (getYearlyIncrease(e, e.getYearsToImplement()) * currentYear);
	// currentTotalConsumption += calcAmount;
	// }
	// }
	// return currentTotalConsumption;
	// }
	//
	// private double calculateCurrentNonFossilFuelTotalProduction(int yearNum)
	// {
	// int currentYear = yearNum;
	// double currentTotalProduction = 0;
	// Collection<EnergyProducerOrConsumerGroup> c = EnergyPlan.getInstance()
	// .getAllEnergyProducerGroups();
	// for (EnergyProducerOrConsumerGroup group : c) {
	// for (EnergyProducerOrConsumer e : group.getAllEnergyInGroup()) {
	// // if the years to implement is less than the year number,
	// // equalise
	// if (e.getYearsToImplement() < currentYear) {
	// currentYear = e.getYearsToImplement();
	// }
	// // do not include the fossil fuels for production
	// if (e.getEnergyType() != ProducerOrConsumerEnum.FOSSIL_FUELS) {
	// // add up current consumption by taking the yearly increase
	// // multiplied by
	// // the number of years and subtracted from the final kWhd
	// double calcAmount = e.get2006KWHdp()
	// + (getYearlyIncrease(e, e.getYearsToImplement()) * currentYear);
	// currentTotalProduction += calcAmount;
	// }
	// }
	// }
	// return currentTotalProduction;
	// }
	//
	// class TimePlanner {
	//
	// private Collection<YearTracker> years = new ArrayList<YearTracker>();
	// private int lastYear = currentYear;
	//
	// public TimePlanner() {
	//
	// }
	//
	// public int getLastYear() {
	// return lastYear;
	// }
	//
	// public YearTracker getYear(int year, boolean forceCreate) {
	// for (YearTracker y : years) {
	// if (y.getYear() == year) {
	// return y;
	// }
	// }
	// if (forceCreate) {
	// // obviously no year made yet so create it
	// YearTracker y = new YearTracker(year);
	// years.add(y);
	// return y;
	// }
	// return null;
	// }
	// }
	//
	// class YearTracker {
	// private int year;
	// private double consumption = 0;
	// private double nonFossProduction = 0;
	//
	// public YearTracker() {
	// }
	//
	// public YearTracker(int year) {
	// this.year = year;
	// }
	//
	// public double getBalanceForYear() {
	// return nonFossProduction - consumption;
	// }
	//
	// public void setConsumption(double consumption) {
	// this.consumption += consumption;
	// }
	//
	// public double getConsumption() {
	// return consumption;
	// }
	//
	// public void setProduction(double nonFossProduction) {
	// this.nonFossProduction += nonFossProduction;
	// }
	//
	// public double getNonFossProduction() {
	// return nonFossProduction;
	// }
	//
	// public void setYear(int year) {
	// this.year = year;
	// }
	//
	// public int getYear() {
	// return year;
	// }
	//
	// }
}
