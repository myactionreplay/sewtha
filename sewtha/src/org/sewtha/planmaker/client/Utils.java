package org.sewtha.planmaker.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Utils {

	public static int POPUP_LEFT_POS = 100;
	public static int POPUP_TOP_POS = 200;
	public static int POPUP_LEFT_POS_INSP = 500;
	public static int POPUP_TOP_POS_INSP = 300;
	public static int TOOLTIP_LEFT_POS = 100;
	public static int TOOLTIP_TOP_POS = 200;

	public static final double roundToTwoDecPlaces(double no) {
		return Math.round(no * 100) / 100.0;
	}

	public static final DialogBox showDialogue(String t) {
		final DialogBox e = new DialogBox();
		e.setText("Sorry you cannot change that");
		VerticalPanel v = new VerticalPanel();
		v.setSpacing(10);
		v.add(new Label(t));
		e.setPopupPosition(POPUP_LEFT_POS_INSP, POPUP_TOP_POS_INSP);
		Button close = new Button("Close");
		close.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				e.hide();
			}

		});
		v.add(close);
		e.add(v);
		e.show();
		e.setAutoHideEnabled(true);
		e.setAnimationEnabled(true);
		return e;
	}

	public static final PopupPanel showToolTip(String t, int left, int top) {
		PopupPanel toolTipPopup = new PopupPanel();
		toolTipPopup.setStylePrimaryName("toolTip");
		toolTipPopup.add(new Label(t));
		toolTipPopup.setAnimationEnabled(true);
		toolTipPopup.setAutoHideEnabled(true);
		toolTipPopup.setPopupPosition(TOOLTIP_LEFT_POS, TOOLTIP_TOP_POS);
		toolTipPopup.show();
		return toolTipPopup;
	}

	public static final DialogBox getHelpDialogue() {
		final DialogBox e = new DialogBox();
		e.setTitle("Energy Plan Maker");
		e.setText("Welcome to the Energy Plan Maker");
		e.setWidth("450px");
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(10);
		Label para1 = new Label("you can now create your own Energy Plan "
				+ "for the UK based on the calculations in David Mackay's "
				+ "book.");
		p.add(para1);
		HTML hypLink = new HTML(
				"<a href=\"http://www.withouthotair.com\">Sustainable Energy - Without the Hot Air</a>");
		p.add(hypLink);
		Label para2 = new Label(
				"Answer each of the questions in the box to the right about the different types of "
						+ "energy to use by using the slider and see the graph change accordingly.");
		p.add(para2);
		Label para2A = new Label("You can also choose the number of years you think it will take" +
				" to implement the amount of energy you have chosen, use the slider to choose anything from 1 - 50 years." + 
				"Click on the 'Switch to Motion Chart'" +
				" button to see your plan change over time");
		p.add(para2A);
		Label para2B = new Label(
				"You can also adapt any part of your plan by click and dragging"
						+ " on the sliders in the box in the bottom right as a shortcut");
		p.add(para2B);
		Label para3 = new Label("Once your plan is complete you can then "
				+ "share it with others - but only if it 'adds up.'");
		p.add(para3);
		Label para4 = new Label(
				"If you have any questions please email them to:");
		p.add(para4);
		HTML emailLink = new HTML("<a href=\"mailto:info@energyplanmaker.com\">info@energyplanmaker.com</a>");
		p.add(emailLink);
		Button close = new Button("Click Here to Start Making a Plan");
		close.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				e.hide();
			}

		});
		p.add(close);
		e.add(p);
		e.show();
		e.setPopupPosition(POPUP_LEFT_POS, POPUP_TOP_POS);
		e.setAnimationEnabled(true);
		e.setAutoHideEnabled(true);
		return e;
	}
}
