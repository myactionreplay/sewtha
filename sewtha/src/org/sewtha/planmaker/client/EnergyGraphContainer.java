/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sewtha.planmaker.client;

import java.util.Date;

import org.sewtha.planmaker.client.maps.MapContainer;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.Selection;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.events.OnMouseOutHandler;
import com.google.gwt.visualization.client.events.OnMouseOverHandler;
import com.google.gwt.visualization.client.events.SelectHandler;
import com.google.gwt.visualization.client.visualizations.ColumnChart;
import com.google.gwt.visualization.client.visualizations.MotionChart;
import com.google.gwt.visualization.client.visualizations.ColumnChart.Options;

/**
 * 
 * @author BarnabyK
 */
public class EnergyGraphContainer extends Composite {

	private static final int LEGEND_FONT_SIZE = 10;
	private static final int MAX_Y_AXIS = 190;
	private static EnergyGraphContainer instance = null;
	private DataTable data;
	private AbstractDataTable motionData;
	private Options options;
	private com.google.gwt.visualization.client.visualizations.MotionChart.Options motionOptions;
	private ColumnChart chart;
	private MotionChart motionChart;
	private VerticalPanel vp = new VerticalPanel();
	private HorizontalPanel planBalanceHp = new HorizontalPanel();
	private HorizontalPanel co2ReductionHp = new HorizontalPanel();
	private HorizontalPanel descHp = new HorizontalPanel();
	private VerticalPanel visHolder = new VerticalPanel();
	private static final String BACKGROUND_COLOR = "#7DA767";
	private PopupPanel graphToolTip = null;
	private PortalLayoutEnum layoutState = PortalLayoutEnum.STATIC_GRAPH;

	private boolean motionChartLoaded = false;

	public EnergyGraphContainer() {
		EnergyPlan.getMainInstance()
				.addListener(new EnergySourceListenerImpl());
		DecoratorPanel dp = createGraphPanel();
		initWidget(dp);
	}

	public static EnergyGraphContainer getInstance() {
		if (instance == null) {
			instance = new EnergyGraphContainer();
		}
		return instance;
	}

	/**
	 * Creates the Bar chart graph and clears the Vertical Panel firstly
	 * 
	 * @return
	 */
	private DecoratorPanel createGraphPanel() {
		vp.clear();
		options = createBarOptions();
		motionOptions = createMotionChartOptions();
		loadAndInitialiseBarChart();
		// loadAndInitialiseMotionChartVis();
		vp.add(createSwitchTo());
	
		DecoratorPanel dp = new DecoratorPanel();
		dp.setStylePrimaryName("customDecoratorPanel");
		vp.addStyleName("graphContainer");
		dp.add(vp);
		return dp;
	}

	private void loadAndInitialiseBarChart() {
		Runnable onLoadCallback = new Runnable() {
			public void run() {
				createBarChartViz();
				vp.add(visHolder);

			}
		};
		// Load the visualization api, passing the onLoadCallback to be called
		// when loading is done.
		VisualizationUtils.loadVisualizationApi(onLoadCallback,
				ColumnChart.PACKAGE);
	}

	public void createMapViz() {
		visHolder.clear();
		visHolder = new VerticalPanel();
		MapContainer map = MapContainer.getInstance();
		visHolder.add(map);
	    vp.add(visHolder);
		visHolder.add(map);
		visHolder.addStyleName("graphHolder");
	}

	public void createBarChartViz() {
		// tried to fix the chrome and Firefox bug here
		visHolder.clear();
		visHolder = new VerticalPanel();

		data = DataVisualisationEnergyModel.getInstance().getDataTableForVis();
		String t = EnergyPlan.getMainInstance().getPlanName();
		options.setTitle(t);

		chart = new ColumnChart(data, options);
		chart.setTitle(t);
		chart.setStylePrimaryName("energyGraph");
		chart.addOnMouseOverHandler(createMouseOverHandler());
		chart.addOnMouseOutHandler(createMouseOutHandler());
		chart.addSelectHandler(createSelectHandler(chart));
		// chart.addSelectHandler(createSelectHandler(chart));
		visHolder.add(chart);
		refreshAddUpPanelAndDescription();
		visHolder.add(planBalanceHp);
		visHolder.add(co2ReductionHp);
		visHolder.add(descHp);
		visHolder.addStyleName("graphHolder");
	}

	private HorizontalPanel createSwitchTo() {
		HorizontalPanel r = new HorizontalPanel();
		String buttonLabel = "Switch to Motion Graph";
		Button switchTo = new Button(buttonLabel);
		switchTo.addClickHandler(new SwitchToGraphListenerImpl(switchTo, false));
		Button visSwitch = new Button("Visualise Plan");
		visSwitch.addClickHandler(new SwitchToGraphListenerImpl(switchTo, true));
		r.add(visSwitch);
		r.add(switchTo);
		return r;
	}

	/**
	 * Creates the Bar chart graph and clears the Vertical Panel firstly
	 * 
	 * @return
	 */
	public void loadAndInitialiseMotionChartVis() {
		Runnable onLoadCallback = new Runnable() {
			public void run() {
				motionChartLoaded = true;
				createMotionChartViz();
				vp.add(visHolder);
			}
		};
		// Load the visualization api, passing the onLoadCallback to be called
		// when loading is done.
		VisualizationUtils.loadVisualizationApi(onLoadCallback,
				MotionChart.PACKAGE);

	}

	@SuppressWarnings("deprecation")
	private void createMotionChartViz() {
		// tried to fix chrome and firefox bug here
		// visHolder = new VerticalPanel();
		visHolder.clear();
		visHolder = new VerticalPanel();
		motionData = DataVisualisationEnergyModel.getInstance()
				.getDataTableForMotionVis();
		// motionData =
		// DataVisualisationEnergyModel.getInstance().getStaticData();
		String t = EnergyPlan.getMainInstance().getPlanName();

		motionChart = new MotionChart(motionData, motionOptions);

		motionChart.setTitle(t);

		motionChart.setStylePrimaryName("motionGraph");
		visHolder.add(motionChart);
		visHolder.addStyleName("graphHolder");

	}

	private OnMouseOutHandler createMouseOutHandler() {
		return new OnMouseOutHandler() {

			@Override
			public void onMouseOutEvent(OnMouseOutEvent event) {
				if (graphToolTip != null) {
					graphToolTip.hide();
				}
			}
		};
	}

	private OnMouseOverHandler createMouseOverHandler() {
		return new OnMouseOverHandler() {

			@Override
			public void onMouseOverEvent(OnMouseOverEvent event) {
				int row = event.getRow();
				int column = event.getColumn();
				EnergyProducerOrConsumerGroup g = EnergyPlan.getMainInstance()
						.getEGroupFromModelRowAndColumn(row, column);
				graphToolTip = Utils.showToolTip(g.getEnergyGroupName() + "\n"
						+ Utils.roundToTwoDecPlaces(g.getKWHdpForGroup())
						+ " kWh/d", 0, 0);

			}

		};
	}

	private SelectHandler createSelectHandler(final ColumnChart chart) {
		return new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				String message = "";

				// May be multiple selections.
				JsArray<Selection> selections = chart.getSelections();
				for (int i = 0; i < selections.length(); i++) {
					// add a new line for each selection
					message += i == 0 ? "" : "\n";

					Selection selection = selections.get(i);
					if (selection.isCell()) {
						int row = selection.getRow();
						int column = selection.getColumn();
						createGraphToolTip(row, column);
						return;
					} else if (selection.isRow()) {
						// isRow() returns true if an entire row has been
						// selected.
						// think we will ignore this
					} else {
						// unreachable
						message += "Pie chart selections should be either row selections or cell selections.";
						message += "  Other visualizations support column selections as well.";
					}
				}

			}

			private void createGraphToolTip(int row, int column) {
				EnergyProducerOrConsumerGroup g = EnergyPlan.getMainInstance()
						.getEGroupFromModelRowAndColumn(row, column);
				// Utils.showDialogue(g.getEnergyGroupName() + "\n"
				// + g.getKWHdpForGroup());
				EnergyPlan.getMainInstance().setSelectedGroup(g);

			}
		};
	}

	private void refreshAddUpPanelAndDescription() {
		createPlanBalance();
		createFossilFuelBalance();
		createPlanDescription();

	}

	private void createFossilFuelBalance() {
		// co2 reduction
		EnergyProducerOrConsumer fossilFuel = EnergyPlan.getMainInstance()
				.getEnergyProducerOrConsumer(
						ProducerOrConsumerEnum.FOSSIL_FUELS);
		if (fossilFuel != null) {
			co2ReductionHp.clear();
			co2ReductionHp.setSpacing(10);
			Label co2ReductionText = new Label("Reduction in CO2 Emissions = ");
			co2ReductionText.addStyleName("surpOrDefText");
			String percentNumber = fossilFuel.getPercentageFormat().format(
					fossilFuel.getCurrentUsageValue());
			Label co2ReducNumber = new Label(percentNumber);
			String s = "defecit";
			if (fossilFuel.getCurrentUsageValue() >= 0.5) {
				s = "surplas";
			}
			co2ReducNumber.addStyleName(s + "Number");
			co2ReductionHp.add(co2ReductionText);
			co2ReductionHp.add(co2ReducNumber);
		}
	}

	private void createPlanDescription() {
		descHp.clear();
		descHp.addStyleName("descriptionBoxUnderGraph");
		descHp.setSpacing(10);
		descHp.add(new Label());
		descHp
				.add(new Label(EnergyPlan.getMainInstance()
						.getPlanDescription()));
		descHp.add(new Label());
	}

	private void createPlanBalance() {
		planBalanceHp.clear();
		planBalanceHp.setSpacing(10);
		Label energyPlanText = new Label("Energy Plan ");
		energyPlanText.addStyleName("surpOrDefText");
		Label surpOrDef = new Label(getEnergySurplasOrDefecit());
		Label surpNum = new Label("" + getEnergySurplasOrDefecitNumber());
		String s = "defecit";
		if (EnergyPlan.getMainInstance().getEnergySurplasOrDefecit() >= 0) {
			s = "surplas";
		}
		surpNum.addStyleName(s + "Number");
		surpOrDef.addStyleName(s + "Number");
		planBalanceHp.add(energyPlanText);
		planBalanceHp.add(surpOrDef);
		planBalanceHp.add(surpNum);
		Label kwhdpText = new Label("kWh/d");
		kwhdpText.addStyleName("surpOrDefText");
		planBalanceHp.add(kwhdpText);
	}

	private String getEnergySurplasOrDefecit() {
		EnergyPlan e = EnergyPlan.getMainInstance();
		if (e.getEnergySurplasOrDefecit() >= 0) {
			return "Surplus = ";
		}
		return "Deficit = 	";
	}

	private double getEnergySurplasOrDefecitNumber() {
		EnergyPlan e = EnergyPlan.getMainInstance();
		return Utils.roundToTwoDecPlaces(e.getEnergySurplasOrDefecit());
	}

	private com.google.gwt.visualization.client.visualizations.MotionChart.Options createMotionChartOptions() {
		com.google.gwt.visualization.client.visualizations.MotionChart.Options options = com.google.gwt.visualization.client.visualizations.MotionChart.Options
				.create();
		// options.set3D(true);
		// use the EnergyPlan to provide all colours
		// String[] colors = EnergyPlan.getInstance().getColors();

		// options.setColors(colors);
		// options.setOption("legendFontSize", LEGEND_FONT_SIZE);
		// options.setOption("backgroundColor", BACKGROUND_COLOR);
		// options.setOption("legendBackgroundColor", BACKGROUND_COLOR);
		// options.setOption("legend", "right");
		// options.set3D(true);
		// options.setStacked(true);
		// options.setEnableTooltip(false);
		// options.setTitle(EnergyPlan.getInstance().getPlanName());
		// options.setTitleX(" ");
		// options.setTitleY("KWH/d/p");

		options
				.setOption(
						"state",
						// "{\"yZoomedIn\":false,\"time\":\"2008-02-01\",\"yLambda\":1,\"playDuration\":15000,\"xZoomedIn\":false,\"dimensions\":{\"iconDimensions\":[\"dim0\"]},\"colorOption\":\"4\",\"xAxisOption\":\"_ALPHABETICAL\",\"orderedByY\":false,\"uniColorForNonSelected\":false,\"yAxisOption\":\"2\",\"nonSelectedAlpha\":0.4,\"uniColorForNonSelected\":false,\"orderedByY\":false,\"sizeOption\":\"_UNISIZE\",\"xZoomedDataMax\":120.5907,\"yZoomedDataMax\":83.56925890246166,\"orderedByX\":false,\"iconKeySettings\":[],\"xLambda\":1,\"showTrails\":true,\"iconType\":\"BAR\",\"yZoomedDataMin\":0,\"xZoomedDataMin\":0,\"xAxisOption\":\"2\",\"duration\":{\"timeUnit\":\"D\",\"multiplier\":1}}");
						"{\"yZoomedIn\":false,\"time\":\"2008-02-01\",\"yLambda\":1,\"playDuration\":15000,\"dimensions\":{\"iconDimensions\":[\"dim0\"]},\"colorOption\":\"4\",\"xAxisOption\":\"_ALPHABETICAL\",\"orderedByY\":false,\"uniColorForNonSelected\":false,\"nonSelectedAlpha\":0.4,\"orderedByX\":true,\"sizeOption\":\"_UNISIZE\",\"xZoomedDataMax\":20,\"yZoomedDataMax\":130,\"yAxisOption\":\"2\",\"iconKeySettings\":[],\"xLambda\":1,\"showTrails\":false,\"iconType\":\"VBAR\",\"yZoomedDataMin\":-0.8,\"xZoomedDataMin\":0,\"xZoomedIn\":false,\"duration\":{\"timeUnit\":\"D\",\"multiplier\":1}}");

		options.setWidth(950);
		options.setHeight(500);
		return options;
	}

	private Options createBarOptions() {
		Options options = Options.create();
		options.set3D(true);
		// use the EnergyPlan to provide all colours
		String[] colors = EnergyPlan.getMainInstance().getColors();

		options.setColors(colors);
		options.setOption("legendFontSize", LEGEND_FONT_SIZE);
		options.setOption("backgroundColor", BACKGROUND_COLOR);
		options.setOption("legendBackgroundColor", BACKGROUND_COLOR);
		options.setOption("legend", "right");
		options.set3D(true);
		options.setStacked(true);
		options.setEnableTooltip(false);
		options.setTitle(EnergyPlan.getMainInstance().getPlanName());
		options.setTitleX(" ");
		options.setTitleY("KWH/d/p");
		return options;
	}

	public void refreshGraphAndDetails() {
		// also get the title which may have changed - this probably should be
		// sent in
		String t = EnergyPlan.getMainInstance().getPlanName();
		switch (layoutState) {
		case MOTION_GRAPH:
			loadAndInitialiseMotionChartVis();
			// updateMotionChart(t);
			break;
		case STATIC_GRAPH:
			updateBarChart(t);
			break;
		}

	}

	private void updateMotionChart(String t) {
		if (motionChart != null) {
			motionChart.setTitle(t);
			motionData = DataVisualisationEnergyModel.getInstance()
					.getDataTableForMotionVis();
			motionChart.draw(motionData, motionOptions);
		}
	}

	/**
	 * Updates the chart if something has changed
	 * 
	 * @param t
	 */
	private void updateBarChart(String t) {
		if (chart != null) {
			data = DataVisualisationEnergyModel.getInstance()
					.getDataTableForVis();
			chart.setTitle(t);
			options.setTitle(t);
			chart.draw(data, options);
			refreshAddUpPanelAndDescription();
		}
		// sometimes this can get stuck
		if (graphToolTip != null) {
			graphToolTip.hide();
		}
	}

	class SwitchToGraphListenerImpl implements ClickHandler {

		private Button b;
		private boolean isVis = false;

		public SwitchToGraphListenerImpl(Button b, boolean isVis) {
			this.b = b;
			this.isVis = isVis;
		}

		public void onClick(ClickEvent event) {
			switch (layoutState) {
			case STATIC_GRAPH:
				if (isVis) {
					layoutState = PortalLayoutEnum.VISUALISATION;
					createMapViz();
					SewthaPortal.getInstance().setLayout(
							PortalLayoutEnum.VISUALISATION);
				} else {
					// change to motion
					layoutState = PortalLayoutEnum.MOTION_GRAPH;
					if (!motionChartLoaded) {
						loadAndInitialiseMotionChartVis();
					} else {
						// bug?? seems only to work with a load and Initialise
						// even
						// after
						// loaded once
						loadAndInitialiseMotionChartVis();
						// createMotionChartViz();
					}
					SewthaPortal.getInstance().setLayout(
							PortalLayoutEnum.MOTION_GRAPH);
				}
				b.setText("Switch To Overview Graph");
				break;
			case MOTION_GRAPH:
				// change to static
				layoutState = PortalLayoutEnum.STATIC_GRAPH;
				// only works in hosted mode
				// createBarChartViz();
				loadAndInitialiseBarChart();
				b.setText("Switch To Motion Graph");
				SewthaPortal.getInstance().setLayout(
						PortalLayoutEnum.STATIC_GRAPH);
				break;

			case VISUALISATION:
				// change to static
				layoutState = PortalLayoutEnum.STATIC_GRAPH;
				// only works in hosted mode
				// createBarChartViz();
				loadAndInitialiseBarChart();
				b.setText("Create Visualisation of Plan");
				SewthaPortal.getInstance().setLayout(
						PortalLayoutEnum.STATIC_GRAPH);
				break;
			}

		}

	}

	class EnergySourceListenerImpl implements EnergyChangeListener {

		@Override
		public void EnergySourceChanged(EnergyProducerOrConsumer changedSource) {
			// Probably don't care - although it may be highlighted in a
			// different colour
			// refreshGraphAndDetails();
		}

		@Override
		public void EnergyValueChanged(double metricValue, double KWHdpValue,
				EnergyProducerOrConsumer changedProdOrCons) {
			refreshGraphAndDetails();
		}

		@Override
		public void EnergyPlanChanged() {
			refreshGraphAndDetails();

		}
	}
}
