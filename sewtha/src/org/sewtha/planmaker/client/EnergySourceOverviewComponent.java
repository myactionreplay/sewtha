/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sewtha.planmaker.client;



import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.widgetideas.client.SliderBar;

/**
 *
 * @author BarnabyK
 */
public class EnergySourceOverviewComponent extends Composite {

    private EnergyProducerOrConsumer eInfo;
    private HorizontalPanel horPan = new HorizontalPanel();
    private Label elabel = new Label();
    private SliderBar slider;
    private Label metricLabel = new Label();
    private Label kwhdpLabel = new Label();

    public EnergySourceOverviewComponent(EnergyProducerOrConsumer eInfo) {
        super();
        this.eInfo = eInfo;
        //add a listener to the container in case someone else changes values
        EnergyPlan.getMainInstance().addListener(new EnergyChangeListenerImpl());
        elabel.setText(eInfo.getDescription());
        elabel.setSize("175px", "15px");
        //need to increase max size as it may disallow due to step size and decimal
        //step size small now so do not need to do this
        //double maxSize = eInfo.getMaxUsage() + eInfo.getStepSizeForSlider();
        
        Image icon = new Image("/images/" + eInfo.getName() + ".png");
        icon.addStyleName("overviewIcon");
        slider = new SliderBar(eInfo.getMinUsage(), eInfo.getMaxUsage());
        slider.setNumTicks(UILookAndFeel.NUM_TICKS);
        slider.setNumLabels(UILookAndFeel.NUM_LABELS);
        slider.setStepSize(eInfo.getStepSizeForSlider());
        slider.setCurrentValue(eInfo.getCurrentUsageValue());
        slider.setSize("135px", "15px");
        //slider.setStylePrimaryName("constantSlider");
        //slider.setSize("250px", "30px");
        metricLabel.setText(eInfo.getPlannedUsageValueStr());
        kwhdpLabel.setText("" + Math.round(eInfo.getKWHdp()*100)/100.0);
        MouseUpHandler mu = new MouseUpHandlerImpl();
        elabel.addMouseUpHandler(mu);
        metricLabel.addMouseUpHandler(mu);
        kwhdpLabel.addMouseUpHandler(mu);
        slider.addMouseUpHandler(mu);

        elabel.setStylePrimaryName("overviewLabel");
        metricLabel.setStylePrimaryName("overviewLabel");
        kwhdpLabel.setStylePrimaryName("overviewLabel");
        //add horizontal panels
        horPan.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        horPan.add(elabel);
        horPan.add(icon);
        horPan.setSpacing(8);
        horPan.add(slider);

        //horPan.setSpacing(8);
        //horPan.add(metricLabel);
        //horPan.setSpacing(8);
        //horPan.add(kwhdpLabel);
        initWidget(horPan);

    }

    private void updateOverviewMetric() {
        slider.setCurrentValue(eInfo.getCurrentUsageValue());
        this.metricLabel.setText(eInfo.getPlannedUsageValueStr());
    }
    
    

    private void updateOverviewKWHdp() {
        this.kwhdpLabel.setText("" + Math.round(eInfo.getKWHdp()*100)/100.0);
    }

    public void refreshComponent() {
    }

    public HorizontalPanel getPanel() {
        return horPan;
    }

    class MouseUpHandlerImpl implements MouseUpHandler {

        public void onMouseUp(MouseUpEvent event) {
            double newValue = slider.getCurrentValue();
            //set this energy as the selected component in case this is the first mouse up
            eInfo.getEnergyOrConsumerGroup().setSelectedComponent(eInfo);
            eInfo.setNewUsage(newValue);
            

        }
    }

    class EnergyChangeListenerImpl implements EnergyChangeListener {


		@Override
		public void EnergySourceChanged(EnergyProducerOrConsumer changedSource) {
			// don't think we care about the source changing here as we show it
			//all the time - we may want to show it is highlighted?
			
		}

		@Override
		public void EnergyValueChanged(double metricValue, double KWHdpValue,
				EnergyProducerOrConsumer changedProdOrCons) {
			updateOverviewMetric();
            updateOverviewKWHdp();
			
		}

		@Override
		public void EnergyPlanChanged() {
			// The plan will have modified the energy component so no need to change it
			//just refresh
			updateOverviewMetric();
            updateOverviewKWHdp();
			
		}
    }
}
