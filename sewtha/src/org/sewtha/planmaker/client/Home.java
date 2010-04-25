package org.sewtha.planmaker.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

public class Home extends Composite {

	private Label homeText = new Label();
	public Home(){
		homeText.setText("This is where the Home text will go");
		initWidget(homeText);
	}
}
