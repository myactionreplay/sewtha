/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sewtha.planmaker.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author BarnabyK
 */
public class EnergySourceOverview extends Composite {

	private VerticalPanel verPanel = new VerticalPanel();
	private VerticalPanel producerVerPan = new VerticalPanel();
	private VerticalPanel consumerVerPan = new VerticalPanel();
	private ScrollPanel sp = new ScrollPanel();

	public EnergySourceOverview(EnergyPlan eColl) {

		super();
		setLayoutCSS(PortalLayoutEnum.STATIC_GRAPH);
		for (EnergyProducerOrConsumerGroup group : eColl
				.getAllEnergyProducerGroups()) {
			if (group.getAllEnergyInGroup().size() > 1
					&& group.getShownInQuestion()) {
				// could put a panel in for the group here if we want
				Label groupLabel = new Label();
				groupLabel.setText(group.getEnergyGroupName());
				Label groupKWHdpLabel = new Label();
				groupKWHdpLabel.setText("" + group.getKWHdpForGroup());
				groupLabel.setStylePrimaryName("overviewLabel");
				groupKWHdpLabel.setStylePrimaryName("overviewLabel");
				producerVerPan.add(groupLabel);
				// producerVerPan.add(groupKWHdpLabel);
			}
			for (EnergyProducerOrConsumer e : group.getAllEnergyInGroup()) {
				producerVerPan.add(new EnergySourceOverviewComponent(e));
			}
		}

		for (EnergyProducerOrConsumerGroup group : eColl
				.getAllEnergyConsumerGroups()) {
			if (group.getAllEnergyInGroup().size() > 1
					&& group.getShownInQuestion()) {
				// could put a panel in for the group here if we want
				Label groupLabel = new Label();
				groupLabel.setText(group.getEnergyGroupName());
				Label groupKWHdpLabel = new Label();
				groupKWHdpLabel.setText("" + group.getKWHdpForGroup());
				consumerVerPan.add(groupLabel);
				consumerVerPan.add(groupKWHdpLabel);
			}
			for (EnergyProducerOrConsumer e : group.getAllEnergyInGroup()) {
				if (e.getShownInQuestion()) {
					consumerVerPan.add(new EnergySourceOverviewComponent(e));
				}
			}

		}

		verPanel.add(producerVerPan);
		verPanel.add(consumerVerPan);
		sp.add(verPanel);
		DecoratorPanel dp = new DecoratorPanel();
		dp.setStylePrimaryName("customDecoratorPanel");
		dp.add(sp);
		initWidget(dp);

	}

	/**
	 * Dynamically sets the CSS layout style
	 * @param layoutType
	 */
	public void setLayoutCSS(PortalLayoutEnum layoutType) {
		switch (layoutType) {
		case STATIC_GRAPH:
			sp.setStylePrimaryName("energyOverview");
			break;
		case MOTION_GRAPH:
			sp.setStylePrimaryName("energyOverviewMotion");
			break;
		}

	}
}
