package org.sewtha.planmaker.client;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.visualizations.MotionChart;
import com.google.gwt.visualization.client.visualizations.MotionChart.Options;
import com.google.gwt.widgetideas.client.ProgressBar;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Sewtha implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	private final DecoratedTabPanel tabPanel = new DecoratedTabPanel();
	private final VerticalPanel mainPanel = new VerticalPanel();
	private final Label planNoTextLabel = new Label("");
	private final ProgressBar progressBar = new ProgressBar(0, 100);
	// testing
	private final VerticalPanel stVP = new VerticalPanel();

	public void onModuleLoad() {
		final Panel panel = RootPanel.get();
		progressBar.setTitle("Loading Energy Plan Maker");
		progressBar.setWidth("200px");
		progressBar.setHeight("10px");
		progressBar.setVisible(true);

		// panel.add(progressBar);
		String sharedPlanId = getSharedPlanFromRequest();
		progressBar.setProgress(10);
		SewthaPortal mainPort = SewthaPortal.getInstance();
		mainPort.init(sharedPlanId);
		progressBar.setProgress(40);
		// tabPanel.setAnimationEnabled(true);
		// tabPanel.getDeckPanel().setHeight("100%");

		Window.Location.getParameter("planid");
		String s = GWT.getModuleBaseURL();
		progressBar.setProgress(50);
		mainPanel.addStyleName("mainHolder");
		mainPanel.add(constructHeader(mainPort));
		progressBar.setProgress(70);
		mainPanel.add(mainPort);
		progressBar.setProgress(80);
//		stVP.add(new Label("Hello"));
//		getMotionChart();
//		panel.add(stVP);

		// panel.remove(progressBar);
		 panel.add(mainPanel);

	}

	// Java method declaration...

	private void getMotionChart() {
		Runnable onLoadCallback = new Runnable() {
			public void run() {
//				AbstractDataTable data = DataVisualisationEnergyModel.getInstance().getStaticData();

//				DataTable data = DataTable.create();
//
//				data.addColumn(ColumnType.STRING, "Fruit");
//				data.addColumn(ColumnType.DATE, "Date");
//				data.addColumn(ColumnType.NUMBER, "Sales");
//				data.addColumn(ColumnType.NUMBER, "Expenses");
//				data.addColumn(ColumnType.STRING, "Location");
//				data.addRow();
//				int futureYear = 2008;
//				int month = 1;
//				int day = 1;
//				int rowN = 0;
//				data.setValue(rowN, 0, "Apples");
//				data.setValue(rowN, 1, new Date(futureYear, month, day));
//				data.setValue(rowN, 2, 1111);
//				data.setValue(rowN, 3, 777);
//				data.setValue(rowN, 4, "Bris");
//				data.addRow();
//				rowN++;
//				day++;
//				data.setValue(rowN, 0, "Oranges");
//				data.setValue(rowN, 1, new Date(futureYear, month, day));
//				data.setValue(rowN, 2, 1155);
//				data.setValue(rowN, 3, 77722);
//				data.setValue(rowN, 4, "Birm");
//				data.addRow();
//				rowN++;
//				day++;
//				data.setValue(rowN, 0, "Apples");
//				data.setValue(rowN, 1, new Date(futureYear, month, day));
//				data.setValue(rowN, 2, 1199);
//				data.setValue(rowN, 3, 333);
//				data.setValue(rowN, 4, "Lon");
				
//				Options motionOptions = getMotionOptions();
//				MotionChart motionChart = new MotionChart(data, motionOptions);
//				//motionChart.draw(data, motionOptions);
//				motionChart.setTitle("Hello");
//
//				motionChart.setStylePrimaryName("motionGraph");
//				stVP.add(motionChart);
			}
		};
		// Load the visualization api, passing the onLoadCallback to be called
		// when loading is done.
		VisualizationUtils.loadVisualizationApi(onLoadCallback,
				MotionChart.PACKAGE);

	}

	private Options getMotionOptions() {
		Options options = Options.create();
		options.setWidth(950);
		options.setHeight(500);
		return options;
	}

	private String getSharedPlanFromRequest() {
		String id = Window.Location.getParameter("planid");
		return id;
	}

	private AbsolutePanel constructHeader(SewthaPortal sewtha) {
		AbsolutePanel ab = new AbsolutePanel();
		// number of plans number
		refreshTotalNoInBanner(sewtha);
		planNoTextLabel.addStyleName("bannerTextNum");
		HorizontalPanel planNoTextPan = new HorizontalPanel();
		planNoTextPan.setSpacing(5);
		planNoTextPan.add(planNoTextLabel);
		// number of plans desc
		Label lTextTop = new Label(" Plans created");
		Label lTextBottom = new Label(" that add+ up");
		lTextTop.addStyleName("bannerText");
		lTextBottom.addStyleName("bannerText");
		VerticalPanel banTextV = new VerticalPanel();
		banTextV.add(lTextTop);
		banTextV.add(lTextBottom);
		HorizontalPanel numText = new HorizontalPanel();
		numText.addStyleName("numAndBannerText");
		// numText.setSpacing(5);
		// numText.add(new Label());
		numText.add(planNoTextPan);
		numText.add(banTextV);
		// r.add(numText);
		ab.add(numText, 650, 10);
		ab.addStyleName("banner");
		return ab;
		// rPanel.add(ab);
		// rPanel.addStyleName("banner");
		// return rPanel;
	}

	public void refreshTotalNoInBanner(SewthaPortal sewtha) {
		sewtha.getNoOfPlans(planNoTextLabel);

	}

	private HorizontalPanel constructFooter() {
		HorizontalPanel r = new HorizontalPanel();
		r.add(new Label("Contact"));
		r.add(new Label("Site Design"));
		r.addStyleName("footer");
		return r;
	}

	/** Creates a new instance of MainEntryPoint */
	public Sewtha() {
	}
}
