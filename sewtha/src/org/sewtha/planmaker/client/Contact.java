package org.sewtha.planmaker.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Contact extends Composite {

	private Label contactText = new Label();
	private DockPanel dockPanel = new DockPanel();
	private VerticalPanel vPanel = new VerticalPanel();
	
	public Contact(){
		contactText.setText("This is where the Contact text will go");
		dockPanel.add(new Label("Instructions"), DockPanel.NORTH);
		dockPanel.add(new Label("Graph Place"), DockPanel.WEST);
		vPanel.add(new Label("Your plan adds up"));
		vPanel.add(new Label("Inspector Panel"));
		vPanel.add(new Label("Save your plan"));
		dockPanel.add(new Label("Inspector Panel"), DockPanel.SOUTH);
		dockPanel.add(new Label("Overview"), DockPanel.CENTER);
		initWidget(dockPanel);
	}
}
