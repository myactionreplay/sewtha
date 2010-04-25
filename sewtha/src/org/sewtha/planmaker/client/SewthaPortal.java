package org.sewtha.planmaker.client;

import java.util.Collection;

import org.sewtha.planmaker.client.rpcclient.EnergyPlanService;
import org.sewtha.planmaker.client.rpcclient.EnergyPlanServiceAsync;
import org.sewtha.planmaker.client.rpcclient.LoginService;
import org.sewtha.planmaker.client.rpcclient.LoginServiceAsync;
import org.sewtha.planmaker.client.rpcclient.UserInfo;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SewthaPortal extends Composite {

	private final VerticalPanel mainVP = new VerticalPanel();
	private UserInfo loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Anchor signInLink = new Anchor("Sign In");
	private Anchor signOutLink = new Anchor("Sign Out");
	private ListBox dropDownList = new ListBox();
	private Button saveButton;
	private EnergyGraphContainer energyGraphPanel;
	private EnergySourceInspector energyInspectorPanel;
	private EnergySourceOverview overviewPanel;
	private static SewthaPortal instance = null;

	private EnergyPlanServiceAsync energyService = GWT
			.create(EnergyPlanService.class);

	protected Collection<SimplerPlan> planCollectionRaw = null;
	private HorizontalPanel staticMainBodyHolder = null;
	private VerticalPanel motionMainBodyHolder = null;

	public SewthaPortal() {

	}

	public static SewthaPortal getInstance() {
		if (instance == null) {
			instance = new SewthaPortal();
		}
		return instance;
	}

	public void init() {
		constructClass(null);
	}

	public void init(String sharedPlanId) {
		constructClass(sharedPlanId);
	}

	private void constructClass(String sharedPlanId) {
		DialogBox d = Utils.getHelpDialogue();
		// d.addStyleName("custDialog");

		EnergyPlan plan = EnergyPlan.getMainInstance();
		plan.init();
		// // plan has been created so we can collect the shared plan
		if (sharedPlanId != null) {
			try {
				long id = Long.parseLong(sharedPlanId);
				// we try and load the plan if we can, if it does not load we
				// already have defaults
				loadEnergyPlan(id);
			} catch (NumberFormatException e) {
				// do nothing here as it may just be a strange parameter we just
				// carry on
			}
		}
		createPanels(plan);
		// set initial layout
		setLayout(PortalLayoutEnum.STATIC_GRAPH);
		// getNoOfPlans();
		initWidget(mainVP);
	}

	private void createPanels(EnergyPlan plan) {
		// mainVP.add(createInstructionsComponent());

		mainVP.setSpacing(4);
		// mainVP.add(createNameAndDescComponent());
		// currently only doing share
		mainVP.add(createSaveBar());
		EnergyProducerOrConsumer e = plan.getSelectedComponent();
		energyGraphPanel = EnergyGraphContainer.getInstance();
		energyInspectorPanel = new EnergySourceInspector(e);

		overviewPanel = new EnergySourceOverview(plan);

		/**
		 * VerticalPanel rightVp = new VerticalPanel(); rightVp.setSpacing(4);
		 * rightVp.add(energyInspectorPanel); rightVp.add(overview);
		 * HorizontalPanel graphAndOverview = new HorizontalPanel();
		 * graphAndOverview.setSpacing(10); graphAndOverview.add(energyGraph);
		 * // graphAndOverview.add(overview); graphAndOverview.add(rightVp);
		 * graphAndOverview.addStyleName("graphAndOverviewPanel");
		 * mainVP.add(graphAndOverview); // mainVP.add(energyInspectorPanel);
		 * 
		 */
	}

	private void createStaticGraphLayout() {
		if (motionMainBodyHolder != null){
			mainVP.remove(motionMainBodyHolder);
		}
		staticMainBodyHolder = new HorizontalPanel();
		staticMainBodyHolder.setSpacing(10);
		staticMainBodyHolder.add(energyGraphPanel);
		// energyGraphPanel.setStylePrimaryName("energyGraphPanelStatic");
		// add the overview and inspector
		VerticalPanel inspAndOverview = new VerticalPanel();
		inspAndOverview.setSpacing(10);
		energyInspectorPanel.setLayoutCSS(PortalLayoutEnum.STATIC_GRAPH);
		overviewPanel.setLayoutCSS(PortalLayoutEnum.STATIC_GRAPH);
		inspAndOverview.add(energyInspectorPanel);
		inspAndOverview.add(overviewPanel);
		staticMainBodyHolder.add(inspAndOverview);
		mainVP.add(staticMainBodyHolder);
	}

	private void createMotionChartLayout() {
		if (staticMainBodyHolder != null) {
			mainVP.remove(staticMainBodyHolder);
		}
		motionMainBodyHolder = new VerticalPanel();
		motionMainBodyHolder.setSpacing(10);
		motionMainBodyHolder.add(energyGraphPanel);
		// energyGraphPanel.setStylePrimaryName("energyGraphPanelMotion");
		// use a horizontal panel in this view
		HorizontalPanel inspAndOverview = new HorizontalPanel();
		inspAndOverview.setSpacing(10);
		energyInspectorPanel.setLayoutCSS(PortalLayoutEnum.MOTION_GRAPH);
		overviewPanel.setLayoutCSS(PortalLayoutEnum.MOTION_GRAPH);
		inspAndOverview.add(overviewPanel);
		inspAndOverview.add(energyInspectorPanel);
		motionMainBodyHolder.add(inspAndOverview);
		mainVP.add(motionMainBodyHolder);
	}

	public void setLayout(PortalLayoutEnum layoutType) {
		switch (layoutType) {
		case STATIC_GRAPH:
			createStaticGraphLayout();
			break;
		case MOTION_GRAPH:
			createMotionChartLayout();
			break;
		case VISUALISATION:
			createMotionChartLayout();
			break;
		}
	}

	private DecoratorPanel createInstructionsComponent() {
		Label instructionsTitle = new Label("Instructions");
		DisclosurePanel discPanel = new DisclosurePanel(
				"Click Here to Hide / Show Instructions", true);
		Label instructions = new Label(
				"Make a plan that adds up - Answer each of the questions"
						+ "in the Question box by moving the slider to indicate how much of an energy source you want"
						+ "or how much you want to cut current energy consumption by. The graph will show you the outcome "
						+ "of your decisions. If you want to modify the plan quickly just use the sliders in the Overview"
						+ "panel. If you are struggling to create a plan you can choose one of the template plans from the "
						+ "following drop down box.");
		discPanel.add(instructions);
		VerticalPanel vp = new VerticalPanel();
		vp.add(instructionsTitle);
		vp.add(discPanel);

		DecoratorPanel dp = new DecoratorPanel();
		dp.add(vp);
		return dp;
	}

	private DecoratorPanel createNameAndDescComponent() {
		DecoratorPanel r = new DecoratorPanel();
		VerticalPanel vp = new VerticalPanel();
		Label name = new Label(EnergyPlan.getMainInstance().getPlanName());
		name.setStylePrimaryName("planName");
		ScrollPanel sp = new ScrollPanel();
		Label description = new Label(EnergyPlan.getMainInstance()
				.getPlanDescription());
		sp.add(description);
		sp.setStylePrimaryName("planDescription");
		vp.add(name);
		vp.add(sp);
		r.add(vp);
		return r;
	}

	private DecoratorPanel createSaveBar() {
		DecoratorPanel r = new DecoratorPanel();
		r.setStylePrimaryName("customDecoratorPanel");
		HorizontalPanel hp = new HorizontalPanel();
		hp.setSpacing(5);
		hp.setStylePrimaryName("savePanel");
		dropDownList.addItem("Loading Plans");
		dropDownList.addChangeHandler(new DropDownListHandler());
		collectPlanNames();
		hp.add(new Label("Load a Plan"));
		hp.add(dropDownList);
		/*
		 * Not using Save anty more saveButton = new Button("Save Plan");
		 * saveButton.addStyleName("saveButton"); saveButton.setEnabled(false);
		 * saveButton.addClickHandler(new SaveButtonClickHandler());
		 * hp.add(saveButton);
		 */
		Button shareButton = new Button("Share Plan");
		shareButton.addStyleName("saveButton");
		shareButton.addClickHandler(new ShareButtonClickHandler());
		hp.add(shareButton);
		// not currently doing save just share
		// createUserLogin(hp);
		Button helpButton = new Button("Help");
		helpButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Utils.getHelpDialogue();
			}

		});
		helpButton.addStyleName("saveButton");
		hp.add(helpButton);
		VerticalPanel vp = new VerticalPanel();
		vp.add(hp);
		String shareThis = "<!-- AddThis Button BEGIN -->"
				+ "<a class=\"addthis_button\" href=\"http://www.addthis.com/bookmark.php?v=250&amp;pub=barnabykent\"><img src=\"http://s7.addthis.com/static/btn/v2/lg-share-en.gif\" width=\"125\" height=\"16\" alt=\"Bookmark and Share\" style=\"border:0\"/></a><script type=\"text/javascript\" src=\"http://s7.addthis.com/js/250/addthis_widget.js#pub=barnabykent\"></script>"
				+ "<!-- AddThis Button END -->";
		HorizontalPanel bottomRow = new HorizontalPanel();
		bottomRow.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		bottomRow.add(new HTML(shareThis));
		vp.add(bottomRow);
		r.add(vp);
		return r;
	}

	private void collectPlanNames() {
		EnergyPlanServiceAsync energyService = GWT
				.create(EnergyPlanService.class);
		energyService
				.getAllPlanNames(new AsyncCallback<Collection<SimplerPlan>>() {

					@Override
					public void onFailure(Throwable caught) {
						mainVP.add(new Label(
								"Failed to collect plan names from server"));

					}

					@Override
					public void onSuccess(Collection<SimplerPlan> result) {
						planCollectionRaw = result;
						dropDownList.clear();

						for (SimplerPlan e : result) {
							dropDownList.addItem(e.getName(), new Long(e
									.getId()).toString());
						}
						dropDownList.setSelectedIndex(5);
					}

				});
	}

	private void createUserLogin(final HorizontalPanel dp) {
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL(),
				new AsyncCallback<UserInfo>() {
					public void onFailure(Throwable error) {
						dp.add(new Label("Failed to get Login "
								+ error.getLocalizedMessage()));
					}

					public void onSuccess(UserInfo result) {
						saveButton.setEnabled(true);
						loginInfo = result;
						if (loginInfo.isLoggedIn()) {
							signOutLink.setHref(loginInfo.getLogoutUrl());
							dp.add(signOutLink);
							// dp.add(new Label("Sign out if you want to "
							// + loginInfo.getEmailAddress()));
						} else {
							// Assemble login panel.
							signInLink.setHref(loginInfo.getLoginUrl());
							// loginPanel.add(loginLabel);

							loginPanel.add(signInLink);
							dp.add(loginPanel);
						}
					}
				});
	}

	private void setPlanWarning(ClickEvent event) {
		final PopupPanel pp = new PopupPanel();
		pp.setTitle("Plan Warning!");
		VerticalPanel vp = new VerticalPanel();
		vp.setSpacing(4);
		vp.add(new Label("Your Plan does not add up!"));
		vp
				.add(new Label(
						"You need to increase"
								+ " Production or decrease Consumption before you can save it"));
		Button close = new Button("Close");
		close.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				pp.hide();
			}
		});
		vp.add(close);
		pp.setAutoHideEnabled(true);
		pp.addStyleName("popupName");
		Widget source = (Widget) event.getSource();
		int left = source.getAbsoluteLeft() + 10;
		int top = source.getAbsoluteTop() + 10;
		pp.setPopupPosition(left, top);
		pp.add(vp);
		pp.show();
	}

	private SavePanel createSaveOrSharePanel(boolean isSave) {
		SavePanel sp;
		if (isSave) {
			sp = new SavePanel(loginInfo, planCollectionRaw);
		} else {
			sp = new SavePanel(planCollectionRaw);
		}
		sp.center();
		sp.show();
		sp.addCloseHandler(new CloseHandler<PopupPanel>() {

			@Override
			public void onClose(CloseEvent<PopupPanel> event) {
				collectPlanNames();

			}
		});
		return sp;
	}

	private void loadEnergyPlan(long planId) {
		energyService.getEnergyPlan(planId,
				new AsyncCallback<SimplePlanData>() {

					@Override
					public void onFailure(Throwable caught) {
						mainVP.add(new Label("FAILURE For drop down list"));

					}

					@Override
					public void onSuccess(SimplePlanData result) {

						String name = result.getName();
						if (name != null) {
							EnergyPlan.getMainInstance().setPlanName(name);
						}
						String desc = result.getDescription();
						if (desc != null) {
							EnergyPlan.getMainInstance().setPlanDescription(desc);
						}
						EnergyPlan.getMainInstance().setNewPlan(result);

					}

				});
	}

	public void getNoOfPlans(final Label l) {
		energyService.getTotalNumberOfPlans(new AsyncCallback<Long>() {

			@Override
			public void onFailure(Throwable caught) {
				// we won't record the number if they are not here

			}

			@Override
			public void onSuccess(Long result) {
				int i = 0;
				while (i <= result) {
					l.setText("" + i);
					// try {
					// this.wait(10);
					// } catch (Exception e) {
					// // TODO Auto-generated catch block
					// e.printStackTrace();
					// }
					i++;
				}
			}

		});
	}

	class ShareButtonClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			if (EnergyPlan.getMainInstance().getEnergySurplasOrDefecit() < 0) {
				setPlanWarning(event);
			} else {
				SavePanel sp = new SavePanel(planCollectionRaw);
				sp.center();
				sp.show();
				// no need to do this any more as we are currently not having
				// login
				// sp.addCloseHandler(new CloseHandler<PopupPanel>() {
				//
				// @Override
				// public void onClose(CloseEvent<PopupPanel> event) {
				// collectPlanNames();
				//
				// }
				// });
			}
		}
	}

	class SaveButtonClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			if (EnergyPlan.getMainInstance().getEnergySurplasOrDefecit() < 0) {
				setPlanWarning(event);
			} else {
				SavePanel sp = new SavePanel(loginInfo, planCollectionRaw);
				sp.center();
				sp.show();
				sp.addCloseHandler(new CloseHandler<PopupPanel>() {

					@Override
					public void onClose(CloseEvent<PopupPanel> event) {
						collectPlanNames();

					}
				});
			}
		}
	}

	class DropDownListHandler implements ChangeHandler {

		@Override
		public void onChange(ChangeEvent event) {
			String planIdStr = dropDownList.getValue(dropDownList
					.getSelectedIndex());
			long planId = Long.parseLong(planIdStr);
			loadEnergyPlan(planId);
		}
	}

}
