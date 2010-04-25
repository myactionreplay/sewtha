/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sewtha.planmaker.client;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.widgetideas.client.SliderBar;

/**
 * 
 * @author BarnabyK
 */
public class EnergySourceInspector extends Inspector {

	private Label questionLabel = new Label();
	private SliderBar usageSlider = new SliderBar(0.0, 0.0);
	private EnergyProducerOrConsumer currentEnergyUnderInspection;
	private Label currentUsage = new Label();
	private Label KWHdp = new Label();
	// private FlexTable table = new FlexTable();
	private VerticalPanel inspVP = new VerticalPanel();
	private static final int NUM_TICKS = 0;
	private static final int NUM_LABELS = 0;
	boolean firstTimeForPrev = true;
	private SliderBar yearSlider = null;
	final TextBox yToImp = new TextBox();

	public EnergySourceInspector(EnergyProducerOrConsumer eInfo) {
		super();
		setCurrentEnergyUnderInspection(eInfo);
		// currentEnergyUnderInspection = eInfo;
		// eInfo.addListener(new EnergySourceListenerImpl());
		EnergyPlan.getMainInstance()
				.addListener(new EnergyChangeListenerImpl());
		usageSlider.addMouseUpHandler(new MouseUpHandlerImpl());
		// previous button should be disabled first

		refreshInspectorPanel();
		DecoratorPanel decPan = new DecoratorPanel();
		decPan.setStylePrimaryName("customDecoratorPanel");
		setLayoutCSS(PortalLayoutEnum.STATIC_GRAPH);
		decPan.add(inspVP);
		initWidget(decPan);
	}

	/**
	 * Dynamically sets the CSS layout style
	 * 
	 * @param layoutType
	 */
	public void setLayoutCSS(PortalLayoutEnum layoutType) {
		switch (layoutType) {
		case STATIC_GRAPH:
			inspVP.setStylePrimaryName("inspectorPanel");
			break;
		case MOTION_GRAPH:
			inspVP.setStylePrimaryName("inspectorPanelMotion");
			break;
		}

	}

	private void refreshInspectorPanel() {
		inspVP.clear();
		// table.clear();
		EnergyProducerOrConsumer e = getCurrentEnergyUnderInspection();
		questionLabel.setText(e.getQuestionForChange());
		questionLabel.addStyleName("questionLabelInspector");

		usageSlider.setMinValue(e.getMinUsage());
		// have to increase the max size as it may not allow due to step size
		// Step size is small enough now
		// double maxSize = e.getMaxUsage() + e.getStepSizeForSlider();
		usageSlider.setMaxValue(e.getMaxUsage());
		usageSlider.setNumTicks(NUM_TICKS);
		usageSlider.setNumLabels(NUM_LABELS);
		usageSlider.setStepSize(e.getStepSizeForSlider());
		usageSlider.setCurrentValue(e.getCurrentUsageValue());
		usageSlider.addStyleName("usageSliderInspector");
		// currentUsage.setText(e.getPlannedUsageValueStr());
		KWHdp.setText(e.getKWHdpStr());
		HorizontalPanel navs = new HorizontalPanel();
		navs.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		Image icon = new Image("/images/" + e.getName() + ".png");
		icon.setHeight("60px");
		icon.setWidth("60px");
		setUpNextAndPreviousButtons(navs, icon, e);
		HorizontalPanel slideHp = new HorizontalPanel();
		slideHp.setSpacing(5);
		slideHp.add(usageSlider);
		// slideHp.add(currentUsage);
		slideHp.add(new Label(" equivalent to "));
		slideHp.add(KWHdp);
		VerticalPanel desc = e.populateSelectionDescArea();
		desc.addStyleName("descAndEquivArea");
		desc.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
		inspVP.setSpacing(2);
		inspVP.add(questionLabel);
		inspVP.add(slideHp);
		inspVP.add(desc);
		inspVP.add(navs);
	}

	private void setUpNextAndPreviousButtons(HorizontalPanel navs, Image icon,
			EnergyProducerOrConsumer e) {
		final Button prevButton = new Button("Previous");
		final Button nextButton = new Button("Next");
		if (EnergyPlan.getMainInstance().getSelectedComponent().getQuestionId() == 0) {
			prevButton.setEnabled(false);
		}
		if (EnergyPlan.getMainInstance().getSelectedComponent().getQuestionId() == EnergyPlan
				.getMainInstance().getCurrentQuestionCounter() - 1) {
			nextButton.setEnabled(false);
		}
		nextButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				boolean r = EnergyPlan.getMainInstance()
						.setNextEnergyOrConsumer();
				prevButton.setEnabled(true);
				nextButton.setEnabled(!r);
			}
		});

		prevButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				nextButton.setEnabled(true);
				boolean r = EnergyPlan.getMainInstance()
						.setPreviousEnergyOrConsumer();
				// above will return true if button should be disabled
				prevButton.setEnabled(!r);
			}
		});
		Label counter = new Label((getCurrentEnergyUnderInspection()
				.getQuestionId())
				+ 1
				+ " of "
				+ EnergyPlan.getMainInstance().getCurrentQuestionCounter());
		counter.addStyleName("counterName");
		navs.setSpacing(5);
		navs.add(icon);
		setUpYearsToImplement(navs, e);
		VerticalPanel nAndP = setUpPrevAndNext(prevButton, nextButton, counter);
		navs.add(nAndP);
	}

	/**
	 * Sets up the previous and next part with the buttons and the counter
	 * 
	 * @param prevButton
	 * @param nextButton
	 * @param counter
	 * @return
	 */
	private VerticalPanel setUpPrevAndNext(final Button prevButton,
			final Button nextButton, Label counter) {
		VerticalPanel nAndP = new VerticalPanel();
		HorizontalPanel topNandP = new HorizontalPanel();
		topNandP.add(prevButton);
		topNandP.add(nextButton);
		HorizontalPanel bottomNandP = new HorizontalPanel();
		bottomNandP.add(counter);
		nAndP.add(topNandP);
		nAndP.add(bottomNandP);
		return nAndP;
	}

	/**
	 * Adds a HorizontalPanel into the current HorizontalPanel
	 * 
	 * @param navs
	 * @param e
	 */
	private void setUpYearsToImplement(HorizontalPanel navs,
			EnergyProducerOrConsumer e) {
		VerticalPanel holdingP = new VerticalPanel();
		HorizontalPanel yearsToImp = new HorizontalPanel();
		yearsToImp.setStylePrimaryName("yearsToImpBox");
		yearsToImp.add(new Label("Years to Implement"));

		yearSlider = new SliderBar(NaturalConstants.MIN_YEAR_TO_IMPL,
				NaturalConstants.MAX_YEAR_TO_IMPL);

		yearSlider.setStepSize(1);
		yearSlider.addStyleName("yearSliderInspector");
		// have to hard code otherwise it seems to elongate when you click on it
		yearSlider.setWidth("125px");
		yearSlider.setCurrentValue(e.getYearsToImplement());
		if (e.getEnergyType() == ProducerOrConsumerEnum.FOSSIL_FUELS){
			yearSlider.setEnabled(false);
			yearSlider.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					Utils.showDialogue(
							"It has been assumed that fossil fuels will always 'fill the gap' " +
							"and be the balancing figure, therefore the years to implement for " +
							"fossil fuels will be the same as the highest 'years to implement' " +
							"number on any of the other renewable energies"
							);
					
				}
			});
		}

		setUpYToImplTextBox(e, yearSlider);
		yearsToImp.add(yToImp);
		holdingP.add(yearsToImp);

		yearSlider.addMouseUpHandler(new MouseUpHandlerYearsToImpl(yearSlider,
				yToImp));
		holdingP.add(yearSlider);
		navs.add(holdingP);
	}

	/**
	 * Sets up the years to implement text box and change handler
	 * 
	 * @param e
	 */
	private void setUpYToImplTextBox(EnergyProducerOrConsumer e,
			SliderBar yToImplSlider) {
		yToImp.setStylePrimaryName("yearsToImpTextBox");
		yToImp.setValue("" + e.getYearsToImplement());
		yToImp.addChangeHandler(new TextBoxChangeHandlerImpl(yToImplSlider));
		if (e.getEnergyType() == ProducerOrConsumerEnum.FOSSIL_FUELS){
			yToImp.setEnabled(false);
			//don't worry about a box for this one
		}
	}

	/**
	 * @return the currentEnergyUnderInspection
	 */
	public EnergyProducerOrConsumer getCurrentEnergyUnderInspection() {
		return currentEnergyUnderInspection;
	}

	/**
	 * @param currentEnergyUnderInspection
	 *            the currentEnergyUnderInspection to set
	 */
	public void setCurrentEnergyUnderInspection(
			EnergyProducerOrConsumer currentEnergyUnderInspection) {
		this.currentEnergyUnderInspection = currentEnergyUnderInspection;
		refreshInspectorPanel();
	}

	class TextBoxChangeHandlerImpl implements ChangeHandler {

		private SliderBar yearsToImpl;

		public TextBoxChangeHandlerImpl(SliderBar yearsToImpl) {
			this.yearsToImpl = yearsToImpl;
		}

		@Override
		public void onChange(ChangeEvent event) {
			EnergyProducerOrConsumer e = EnergyPlan.getMainInstance()
					.getSelectedComponent();
			if (e.getEnergyType() == ProducerOrConsumerEnum.FOSSIL_FUELS) {
				Utils.showDialogue(
								"It has been assumed that fossil fuels will always 'fill the gap' and be the balancing figure, therefore the years to implement for fossil fuels will be the same as the highest number on any of the other energies"
								);
				yearsToImpl.setCurrentValue(e.getYearsToImplement());
			} else {
				try {
					int years = Integer.parseInt(yToImp.getValue());
					if (years > 0) {
						e.setYearsToImplement(years);
						yearsToImpl.setCurrentValue(years);
						// we need to set the maximum years to implement for
						// Fossil
						// Fuel here

					} else {
						// just set it back to the value in the producer or
						// consumer
						yToImp.setValue("" + e.getYearsToImplement());
						yearsToImpl.setCurrentValue(e.getYearsToImplement());

					}
				} catch (NumberFormatException nfe) {
					// just set the box back to the value in the energy producer
					// or consumer
					yToImp.setValue("" + e.getYearsToImplement());
				}
			}

		}
	}

	class EnergyChangeListenerImpl implements EnergyChangeListener {

		public void EnergySourceChanged(EnergyProducerOrConsumer changedSource) {
			setCurrentEnergyUnderInspection(changedSource);
		}

		@Override
		public void EnergyValueChanged(double metricValue, double KWHdpValue,
				EnergyProducerOrConsumer changedProdOrCons) {
			refreshInspectorPanel();

		}

		@Override
		public void EnergyPlanChanged() {
			setCurrentEnergyUnderInspection(EnergyPlan.getMainInstance()
					.getSelectedComponent());

		}
	}

	/**
	 * Handler for the slider in the years to implement box
	 * 
	 * @author BarnabyK
	 * 
	 */
	class MouseUpHandlerYearsToImpl implements MouseUpHandler {
		private SliderBar yearSlider;
		private TextBox yToImpl;

		public MouseUpHandlerYearsToImpl(SliderBar yearSlider, TextBox yToImpl) {
			this.yearSlider = yearSlider;
			this.yToImpl = yToImpl;
		}

		public void onMouseUp(MouseUpEvent event) {
			EnergyProducerOrConsumer e = EnergyPlan.getMainInstance()
					.getSelectedComponent();
			if (e.getEnergyType() == ProducerOrConsumerEnum.FOSSIL_FUELS) {
				Utils.showDialogue(
								"It has been assumed that fossil fuels will always 'fill the gap' and be the balancing figure, therefore the years to implement for fossil fuels will be the same as the highest number on any of the other energies"
								);
				yearSlider.setCurrentValue(e.getYearsToImplement());

			} else {
			double years = yearSlider.getCurrentValue();
			yToImp.setText("" + (int) years);
			e.setYearsToImplement((int) years);
			yToImpl.setValue("" + years);
			// we need to set the maximum years to implement for Fossil Fuel
			// here
			}

		}
	}

	class MouseUpHandlerImpl implements MouseUpHandler {

		public void onMouseUp(MouseUpEvent event) {
			double newValue = usageSlider.getCurrentValue();
			currentEnergyUnderInspection.setNewUsage(newValue);

		}
	}
}
