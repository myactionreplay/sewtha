package org.sewtha.planmaker.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

public class About extends Composite {

	private Label aboutText = new Label();
	public About(){
		aboutText.setText("This is where the About text will go");
		initWidget(aboutText);
	}
}
