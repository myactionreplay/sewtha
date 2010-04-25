/*
 * MainEntryPoint.java
 *
 * Created on 03 August 2009, 20:48
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.sewtha.planmaker.client;



import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalSplitPanel;

//import com.google.gwt.visualization.client.visualizations.PieChart.Options;
/**
 *
 * @author BarnabyK
 */
public class MainEntryPoint implements EntryPoint {

    public void onModuleLoad() {
        // Create a callback to be called when the visualization API
        // has been loaded.
        // Send the query.
        // Get the data from the QueryResponse.
//        OnshoreWindFarms onshoreWind  = new OnshoreWindFarms(GeographicalConstants.getInstance());
        //       SolarHeating solarHeating = new SolarHeating(GeographicalConstants.getInstance());
        EnergyProducerOrConsumer e = EnergyPlan.getMainInstance().getSelectedComponent();
      //  EnergyGraphContainer energyGraph = new EnergyGraphContainer(e);
        EnergySourceInspector energyInspectorPanel = new EnergySourceInspector(e);
//        EnergyGraphContainer energyGraph = new EnergyGraphContainer(onshoreWind);
 //       EnergySourceInspector energyInspectorPanel = new EnergySourceInspector(onshoreWind);
        VerticalSplitPanel vp = new VerticalSplitPanel();
        HorizontalSplitPanel hp = new HorizontalSplitPanel();
        //hp.setLeftWidget(energyGraph);
        //hp.setLeftWidget(energyGraph);
//        VerticalPanel vpInner = new VerticalPanel();
        //vpInner.add(new SliderBar(0.0, 100.0));
        //      vpInner.setWidth("300px");
//        EnergySourceOverview onWind = new EnergySourceOverview(onshoreWind);
        //       EnergySourceOverview solarHeat = new EnergySourceOverview(solarHeating);
        //      vpInner.add(onWind);
        //     vpInner.add(solarHeat);
//        vpInner.add(new EnergySourceOverview(new OnshoreWindFarms(GeographicalConstants.getInstance())));
//        vpInner.add(new EnergySourceOverview(new OnshoreWindFarms(GeographicalConstants.getInstance())));
//        vpInner.add(new EnergySourceOverview(new OnshoreWindFarms(GeographicalConstants.getInstance())));
//        vpInner.add(new EnergySourceOverview(new OnshoreWindFarms(GeographicalConstants.getInstance())));
//        vpInner.add(new EnergySourceOverview(new OnshoreWindFarms(GeographicalConstants.getInstance())));
//        vpInner.add(new EnergySourceOverview(new OnshoreWindFarms(GeographicalConstants.getInstance())));
//        vpInner.add(new EnergySourceOverview(new OnshoreWindFarms(GeographicalConstants.getInstance())));
//        vpInner.add(new EnergySourceOverview(new OnshoreWindFarms(GeographicalConstants.getInstance())));
//        vpInner.add(new EnergySourceOverview(new OnshoreWindFarms(GeographicalConstants.getInstance())));
//        vpInner.add(new EnergySourceOverview(new OnshoreWindFarms(GeographicalConstants.getInstance())));
//        vpInner.add(new EnergySourceOverview(new OnshoreWindFarms(GeographicalConstants.getInstance())));
//        vpInner.add(new EnergySourceOverview(new OnshoreWindFarms(GeographicalConstants.getInstance())));
//        vpInner.add(new EnergySourceOverview(new OnshoreWindFarms(GeographicalConstants.getInstance())));
//        vpInner.add(new EnergySourceOverview(new OnshoreWindFarms(GeographicalConstants.getInstance())));
//        vpInner.add(new EnergySourceOverview(new OnshoreWindFarms(GeographicalConstants.getInstance())));
//        vpInner.add(new EnergySourceOverview(new OnshoreWindFarms(GeographicalConstants.getInstance())));
//        vpInner.add(new EnergySourceOverview(new OnshoreWindFarms(GeographicalConstants.getInstance())));
//        vpInner.add(new EnergySourceOverview(new OnshoreWindFarms(GeographicalConstants.getInstance())));
//        vpInner.add(new EnergySourceOverview(new OnshoreWindFarms(GeographicalConstants.getInstance())));
//        vpInner.add(new EnergySourceOverview(new OnshoreWindFarms(GeographicalConstants.getInstance())));
//        vpInner.add(new EnergySourceOverview(new OnshoreWindFarms(GeographicalConstants.getInstance())));
//        vpInner.add(new EnergySourceOverview(new OnshoreWindFarms(GeographicalConstants.getInstance())));
//        vpInner.add(new EnergySourceOverview(new OnshoreWindFarms(GeographicalConstants.getInstance())));
//        vpInner.add(new EnergySourceOverview(new OnshoreWindFarms(GeographicalConstants.getInstance())));
        EnergySourceOverview overview = new EnergySourceOverview(EnergyPlan.getMainInstance());
        //hp.setRightWidget(overview);


        hp.setWidth("1280px");
        hp.setSplitPosition("600px");
        //vp.add(hp);
        //vp.add(slider);
//                            vp.setBottomWidget(slider);
        vp.setTopWidget(hp);
        vp.setSplitPosition("490px");
        //vp.setBottomWidget(energyInspectorPanel);
        vp.setHeight("1000px");
        Panel panel = RootPanel.get();
        //panel.add(bar);
        //panel.add(slider);
        panel.add(vp);
    }

    /** Creates a new instance of MainEntryPoint */
    public MainEntryPoint() {
    }
}


