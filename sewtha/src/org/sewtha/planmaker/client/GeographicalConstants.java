/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sewtha.planmaker.client;

/**
 * Will be set as default to the UK but other country classes can extend and
 * override the methods. Also note that this is Geographical constants for the
 * country, as more accuracy is gained these will move into the energy source
 * specific one.
 * 
 * @author BarnabyK
 */
public class GeographicalConstants {

	private float windspeedMetresPerSecond = 6.0f;
	private int availableLandOnshoreWindMetresSquaredPerPerson = 4000;
	private double latitudeOfLocation = 52.00;
	private double averageSunlightIntensityPercentage = 32.00;
	private double averageSunshineHoursPercentage = 34.00;
	private int availableAreaForShallowOffshoreKmSquared = 40000;
	private int availableAreaForDeepOffshoreKmSquared = 80000;
	private int availableCoastlineKm = 1000;
	private double tidalRange = 4.00;
	private int population = 60000000;
	private float currentCoalCapacityGW = 30f;
	private int availableAreaForLowlandHydroMetreSquaredPerPerson = 2700;
	private float gravitationalPowerLowlandHydroWattsPerMetreSquared = 0.02f;
	private int availableAreaForHighlandHydroMetreSquaredPerPerson = 1300;
	private float gravitationalPowerHighlandHydroWattsPerMetreSquared = 0.24f;
	private int availableCoastlineForWaveKm = 1000;
	// from all 7 regions in the UK this is the power for all 7 at 100%
	// efficiency
	private float totalAvailableTidalPowerInKWHdp = 18.24f;
	private float currentHeatingCapacityKWHdp = 40;
	// this is the figure before the 50% efficiency is taken into account
	private float powerOfAllBarrageKWHdp = 1.66f;
	// this is the value before conversion efficiency gets in the way
	private float powerOfOneLagoonRegionKWHdp = 1.2f;
	private double maxPossibleLagoons = 2;
	private double currentElectricGadgetKWHdp = 18;
	private double currentTransportKWHdp = 40;
	private float wind2006KWhdp = 0.16f;
	// from page 264
	private double windTurbineKWHdp;
	private float solarHeating2006KWhdp = 0.014f;
	private float solarPV2006KWhdp = 0.0f;
	private float solarDesertKWhdp = 0.0f;
	private float solarPVRoof2006KWhdp = 0.0003f;
	private float solarBiomass2006KWhdp = 0.13f;
	private float shallowWind2006KWHdp = 0.03f;
	private float deepWind2006KWhdp = 0.0f;
	// this is .12 + .07
	private float wood2006KWhdp = 0.19f;
	private float wave2006KWhdp = 0.0f;
	private float waste2006KWHdp = 0.3f;
	private float barrage2006KWHdp = 0.0f;
	// UK - total from pg 111 is 0.212 divided equally betweeen the two
	private double hydoHigh2006KWHdp = 0.022;
	private double hydroLow2006KWHdp = 0.19;

	private double cleanCoal2006KWhdp = 0;
	private double lagoon2006KWHdp = 0;
	private float nuclear2006KWHdp = 3.4f;
	private double tidal2006KWHdp = 0;
	// these are the equivalent metric to use - it could be calculated
	// dynamically but at the moment
	// we will keep static
	private double solarHeating20006Metric = 0.0102;
	private double solarPV2006Metric = 0.0006;
	private double solarDesert2006Metric = 0;
	private double solarBiomass2006Metric = 15.522;
	private double solarPVRoofMetric = 0.00055;
	private double onshoreWind2006Metric = .00073;
	private double shallowWind2006Metric = 0.00054;
	private double deepWind2006Metric = 0;
	private double wood2006Metric = 19.39;
	private double waste2006Metric = 0.577;
	private double barrage2006Metric = 0;
	private double hydroHigh2006Metric = 0.02436;
	private double hydoLow2006Metric = 0.01629;
	//this is minus 100% as there has been none done with 0 meaning all current coal refitted
	private double cleanCoal2006Metric = -1;
	private double lagoon2006Metric = 0;
	private double nuclear2006Metric = 9;
	private double tidal2006Metric = 0;
	private double wave2006Metric = 0;
	private double lagoonKKmSquared = 400;
	// from random internet site
	private double averageCoalStationGW = 0.600;
	private double totalAvailableTidalAreaInSqKm = 372.24;
	private double availableAreaForBarrageSqKm = 550;
	// based on a 30MW power station
	private double wastePowerStationsPerKWHdp = 80;
	private double PumpedHeat2006KWHdp = 0;
	private double pumpedHeat2006Metric = 0;
	private double fossilFuel2006KWHdp = 120.5907;
	private double fossilFuel2006Metric = 0;
	private double transport2006KWHdp = 40;
	private double transport2006Metric = 0;
	private double heating2006KWHdp = 40;
	private double heating2006Metric = 0;
	private double elecThings200KWHdp = 18;
	private double elecThings2006Metric = 0;



	private static GeographicalConstants geog = null;

	public GeographicalConstants() {
		windTurbineKWHdp = 500000 / NaturalConstants.CONVERT_WATTS_TO_KWHDP
				/ getPopulation();
	}

	public double getWave2006Metric() {
		return wave2006Metric;
	}

	public double getOnshoreWind2006Metric() {
		return onshoreWind2006Metric;
	}

	public double getSolarHeating20006Metric() {
		return solarHeating20006Metric;
	}

	public double getSolarPV2006Metric() {
		return solarPV2006Metric;
	}

	public double getSolarDesert2006Metric() {
		return solarDesert2006Metric;
	}

	public double getSolarBiomass2006Metric() {
		return solarBiomass2006Metric;
	}

	public double getDeepWind2006Metric() {
		return deepWind2006Metric;
	}

	public double getWood2006Metric() {
		return wood2006Metric;
	}

	public double getWaste2006Metric() {
		return waste2006Metric;
	}

	public double getHydroHigh2006Metric() {
		return hydroHigh2006Metric;
	}

	public double getHydoLow2006Metric() {
		return hydoLow2006Metric;
	}

	public double getNuclear2006Metric() {
		return nuclear2006Metric;
	}

	public double getTidal2006Metric() {
		return tidal2006Metric;
	}

	public double getSolarPVRoof2006Metric() {
		return solarPVRoofMetric;
	}

	public double getShallowWind2006Metric() {
		return shallowWind2006Metric;
	}

	public double getBarrage2006Metric() {
		return barrage2006Metric;
	}

	public double getCleanCoal2006Metric() {
		return cleanCoal2006Metric;
	}

	public double getLagoon2006Metric() {
		return lagoon2006Metric;
	}

	/**
	 * @return the windspeedMetresPerSecond
	 */
	public float getWindspeedMetresPerSecond() {
		return windspeedMetresPerSecond;
	}

	/**
	 * @param windspeedMetresPerSecond
	 *            the windspeedMetresPerSecond to set
	 */
	public void setWindspeedMetresPerSecond(float windspeedMetresPerSecond) {
		this.windspeedMetresPerSecond = windspeedMetresPerSecond;
	}

	/**
	 * @return the availableLandMetresSquaredPerPerson
	 */
	public int getAvailableLandOnshoreWindMetresSquaredPerPerson() {
		return availableLandOnshoreWindMetresSquaredPerPerson;
	}

	/**
	 * @param availableLandMetresSquaredPerPerson
	 *            the availableLandMetresSquaredPerPerson to set
	 */
	public void setAvailableLandMetresSquaredPerPerson(
			int availableLandMetresSquaredPerPerson) {
		this.availableLandOnshoreWindMetresSquaredPerPerson = availableLandMetresSquaredPerPerson;
	}

	/**
	 * @return the latitudeOfLocation
	 */
	public double getLatitudeOfLocation() {
		return latitudeOfLocation;
	}

	/**
	 * @param latitudeOfLocation
	 *            the latitudeOfLocation to set
	 */
	public void setLatitudeOfLocation(double latitudeOfLocation) {
		this.latitudeOfLocation = latitudeOfLocation;
	}

	/**
	 * @return the averageSunlightIntensityPercentage
	 */
	public double getAverageSunlightIntensityPercentage() {
		return averageSunlightIntensityPercentage;
	}

	/**
	 * @param averageSunlightIntensityPercentage
	 *            the averageSunlightIntensityPercentage to set
	 */
	public void setAverageSunlightIntensityPercentage(
			double averageSunlightIntensityPercentage) {
		this.averageSunlightIntensityPercentage = averageSunlightIntensityPercentage;
	}

	/**
	 * @return the averageSunshineHoursPercentage
	 */
	public double getAverageSunshineHoursPercentage() {
		return averageSunshineHoursPercentage;
	}

	/**
	 * @param averageSunshineHoursPercentage
	 *            the averageSunshineHoursPercentage to set
	 */
	public void setAverageSunshineHoursPercentage(
			double averageSunshineHoursPercentage) {
		this.averageSunshineHoursPercentage = averageSunshineHoursPercentage;
	}

	/**
	 * @return the availableAreaForShallowOffshoreKmSquared
	 */
	public int getAvailableAreaForShallowOffshoreKmSquared() {
		return availableAreaForShallowOffshoreKmSquared;
	}

	/**
	 * @param availableAreaForShallowOffshoreKmSquared
	 *            the availableAreaForShallowOffshoreKmSquared to set
	 */
	public void setAvailableAreaForShallowOffshoreKmSquared(
			int availableAreaForShallowOffshoreKmSquared) {
		this.availableAreaForShallowOffshoreKmSquared = availableAreaForShallowOffshoreKmSquared;
	}

	/**
	 * @return the availableAreaForDeepOffshoreKmSquared
	 */
	public int getAvailableAreaForDeepOffshoreKmSquared() {
		return availableAreaForDeepOffshoreKmSquared;
	}

	/**
	 * @param availableAreaForDeepOffshoreKmSquared
	 *            the availableAreaForDeepOffshoreKmSquared to set
	 */
	public void setAvailableAreaForDeepOffshoreKmSquared(
			int availableAreaForDeepOffshoreKmSquared) {
		this.availableAreaForDeepOffshoreKmSquared = availableAreaForDeepOffshoreKmSquared;
	}

	/**
	 * @return the availableCoastlineKm
	 */
	public int getAvailableCoastlineKm() {
		return availableCoastlineKm;
	}

	/**
	 * @param availableCoastlineKm
	 *            the availableCoastlineKm to set
	 */
	public void setAvailableCoastlineKm(int availableCoastlineKm) {
		this.availableCoastlineKm = availableCoastlineKm;
	}

	/**
	 * @return the tidalRange
	 */
	public double getTidalRange() {
		return tidalRange;
	}

	/**
	 * @param tidalRange
	 *            the tidalRange to set
	 */
	public void setTidalRange(double tidalRange) {
		this.tidalRange = tidalRange;
	}

	/**
	 * @return the population
	 */
	public int getPopulation() {
		return population;
	}

	/**
	 * @param population
	 *            the population to set
	 */
	public void setPopulation(int population) {
		this.population = population;
	}

	/**
	 * @return the currentCoalCapacityGW
	 */
	public float getCurrentCoalCapacityGW() {
		return currentCoalCapacityGW;
	}

	/**
	 * @param currentCoalCapacityGW
	 *            the currentCoalCapacityGW to set
	 */
	public void setCurrentCoalCapacityGW(int currentCoalCapacityGW) {
		this.currentCoalCapacityGW = currentCoalCapacityGW;
	}

	public static GeographicalConstants getInstance() {
		if (geog == null) {
			geog = new GeographicalConstants();
		}
		return geog;
	}

	public void setAvailableAreaForLowlandHydroMetreSquaredPerPerson(
			int availableAreaForLowlandHydroMetreSquaredPerPerson) {
		this.availableAreaForLowlandHydroMetreSquaredPerPerson = availableAreaForLowlandHydroMetreSquaredPerPerson;
	}

	public int getAvailableAreaForLowlandHydroMetreSquaredPerPerson() {
		return availableAreaForLowlandHydroMetreSquaredPerPerson;
	}

	public void setGravitationalPowerLowlandHydroWattsPerMetreSquared(
			float gravitationalPowerLowlandHydroWattsPerMetreSquared) {
		this.gravitationalPowerLowlandHydroWattsPerMetreSquared = gravitationalPowerLowlandHydroWattsPerMetreSquared;
	}

	public float getGravitationalPowerLowlandHydroWattsPerMetreSquared() {
		return gravitationalPowerLowlandHydroWattsPerMetreSquared;
	}

	public void setAvailableAreaForHighlandHydroMetreSquaredPerPerson(
			int availableAreaForHighlandHydroMetreSquaredPerPerson) {
		this.availableAreaForHighlandHydroMetreSquaredPerPerson = availableAreaForHighlandHydroMetreSquaredPerPerson;
	}

	public int getAvailableAreaForHighlandHydroMetreSquaredPerPerson() {
		return availableAreaForHighlandHydroMetreSquaredPerPerson;
	}

	public void setGravitationalPowerHighlandHydroWattsPerMetreSquared(
			float gravitationalPowerHighlandHydroWattsPerMetreSquared) {
		this.gravitationalPowerHighlandHydroWattsPerMetreSquared = gravitationalPowerHighlandHydroWattsPerMetreSquared;
	}

	public float getGravitationalPowerHighlandHydroWattsPerMetreSquared() {
		return gravitationalPowerHighlandHydroWattsPerMetreSquared;
	}

	private void setAvailableCoastlineForWaveKm(int availableCoastlineForWaveKm) {
		this.availableCoastlineForWaveKm = availableCoastlineForWaveKm;
	}

	public int getAvailableCoastlineForWaveKm() {
		return availableCoastlineForWaveKm;
	}

	public float getTotalAvailableTidalPowerInKWHdp() {
		return totalAvailableTidalPowerInKWHdp;

	}

	public float getCurrentHeatingUsageKWHdp() {
		return currentHeatingCapacityKWHdp;
	}

	public float getPowerOfAllBarageKWHdp() {
		return powerOfAllBarrageKWHdp;

	}

	public float getPowerOfOneLagoonRegionKWHdp() {
		return powerOfOneLagoonRegionKWHdp;
	}

	public double getMaxNumberOfPossibleLagoons() {
		return maxPossibleLagoons;
	}

	public double getCurrentElectricGadgetKWHdp() {
		return currentElectricGadgetKWHdp;
	}

	public double getCurrentTransportKWHdp() {
		return currentTransportKWHdp;
	}

	public float get2006WindKWHdp() {
		return wind2006KWhdp;
	}

	public double getWindTurbineKWhdp() {
		return windTurbineKWHdp;

	}

	public double get2006SolarHeatingKWHdp() {
		return solarHeating2006KWhdp;
	}

	public double get2006SolarPVKWHdp() {
		return solarPV2006KWhdp;
	}

	public double get2006SolarDesertKWHdp() {
		return solarDesertKWhdp;
	}

	public double get2006SolarPVRoofKWHdp() {
		return solarPVRoof2006KWhdp;
	}

	public double get2006BiomassKWHdp() {
		return solarBiomass2006KWhdp;
	}

	public double get2006ShallowOffWindKWHdp() {
		return shallowWind2006KWHdp;
	}

	public double get2006DeepOffWindKWHdp() {
		return deepWind2006KWhdp;
	}

	public double get2006WoodKWHdp() {
		return wood2006KWhdp;
	}

	public double get2006WaveKWHdp() {
		return wave2006KWhdp;
	}

	public double get2006WasteKWHdp() {
		return waste2006KWHdp;
	}

	public double get2006BarrageKWHdp() {
		return barrage2006KWHdp;
	}

	public double get2006HydroHighlandKWHdp() {
		return hydoHigh2006KWHdp;
	}

	public double get2006HydroLowlandKWHdp() {
		return hydroLow2006KWHdp;
	}

	public double get2006CleanCoalKWHdp() {
		return cleanCoal2006KWhdp;
	}

	public double get2006LagoonKWHdp() {
		return lagoon2006KWHdp;
	}

	public double get2006NuclearKWHdp() {
		return nuclear2006KWHdp;
	}

	public double get2006TidalKWHdp() {
		return tidal2006KWHdp;
	}

	public double getLagoonKmSquared() {
		return lagoonKKmSquared;
	}

	public double getAverageCoalStationGW() {
		return averageCoalStationGW;
	}

	public double getTotalAvailableTidalAreaInSqKm() {
		return totalAvailableTidalAreaInSqKm;
	}

	public double getAvailableAreaForBarrageSqKm() {
		return availableAreaForBarrageSqKm;
	}

	public double getWastePowerStationsPerKWHdp() {
		return wastePowerStationsPerKWHdp;
	}

	public double get2006PumpedHeatKWHdp() {
		return PumpedHeat2006KWHdp;
	}

	public double getPumpedHeat2006Metric() {
		return pumpedHeat2006Metric;
	}

	public double get2006FossilFuelKWHdp() {
		return fossilFuel2006KWHdp;
	}

	public double getFossilFuel2006Metric() {
		return fossilFuel2006Metric;
	}

	public double get2006TransportkWhd() {
		return transport2006KWHdp;
	}

	public double get2006HeatingkWhd() {
		return heating2006KWHdp;
	}

	public double get2006ElectricalThingskWhd() {
		return elecThings200KWHdp;
	}
	public double getTransport2006Metric() {
		return transport2006Metric;
	}

	public double getHeating2006Metric() {
		return heating2006Metric;
	}

	public double getElecThings2006Metric() {
		return elecThings2006Metric;
	}

}
