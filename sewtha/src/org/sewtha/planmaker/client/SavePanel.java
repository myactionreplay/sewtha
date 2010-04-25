package org.sewtha.planmaker.client;

import java.util.Collection;

import org.sewtha.planmaker.client.rpcclient.EnergyPlanService;
import org.sewtha.planmaker.client.rpcclient.EnergyPlanServiceAsync;
import org.sewtha.planmaker.client.rpcclient.UserInfo;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SavePanel extends Composite {

	private static final int HOR_SPACING = 4;
	private static final int VER_SPACING = 4;
	private UserInfo loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Anchor signInLink = new Anchor("Sign In");
	private Anchor signOutLink = new Anchor("Sign Out");
	private Collection<SimplerPlan> planCollectionRaw = null;
	ListBox dropDown = new ListBox();
	private EnergyPlanServiceAsync energyService = GWT
			.create(EnergyPlanService.class);
	final DialogBox dialogBox = new DialogBox();
	private TextBox name = null;
	private TextBox optionalEmail = null;

	VerticalPanel dialogContents = new VerticalPanel();

	public SavePanel() {
	}

	public SavePanel(Collection<SimplerPlan> planCollectionRaw) {
		this.planCollectionRaw = planCollectionRaw;
		// dialogContents.setStylePrimaryName("customDialog");
		dialogContents.setSpacing(4);
		// dialogContents.setTitle("Share Plan");
		populateSharePanel();

	}

	public SavePanel(UserInfo userInfo,
			Collection<SimplerPlan> planCollectionRaw) {
		this.planCollectionRaw = planCollectionRaw;
		dialogContents.setSpacing(4);

		if (userInfo.isLoggedIn()) {
			createDialogBoxForLoggedInStepOne();
		} else {
			createDialogBoxForNotLoggedInStepOne(userInfo);
		}
		createFooter();

	}

	private void createFooter() {
		Button closeButton = new Button("Close Box", new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});
		dialogContents.add(closeButton);
		dialogContents.setVisible(true);
		dialogBox.add(dialogContents);
		dialogBox.setAnimationEnabled(true);
		dialogBox.setAutoHideEnabled(true);
		// dialogBox.setStylePrimaryName("customDialog");
		dialogBox.show();
	}

	private void createDialogBoxForNotLoggedInStepOne(UserInfo loginInfo) {
		dialogBox.setText("Save Graphs");
		signInLink.setHref(loginInfo.getLoginUrl());
		// loginPanel.add(loginLabel);
		loginPanel
				.add(new Label(
						"You are only able to save your plan once you are signed in. "
								+ "Currently you can use your Google account to sign in with - if you do not have"
								+ "one it is free and easy to register just click on the link below."));
		loginPanel.add(signInLink);
		loginPanel
				.add(new Label(
						"If you do not want to create an account you can still share your"
								+ "plan by clicking on the Share button below. Please note that you will not be able to modify your plan"
								+ " in this way but you will be provided with a link you can share with others."));
		dialogContents.add(loginPanel);
		Button shareButton = new Button("Share", new ShareButtonClickHandler());
		dialogContents.add(shareButton);
		dialogBox.add(dialogContents);
	}

	private void createDialogBoxForLoggedInStepOne() {
		dialogBox.setText("Save Graphs");
		Button saveButton = new Button("Save as New Plan");
		saveButton.addClickHandler(new SaveAsNewPlanClickHanlder());
		dialogContents.add(saveButton);
		dialogContents.add(addSaveAsPanel());

		dialogBox.add(dialogContents);

	}

	private void createNameAndDescription(boolean validMetaInfo) {
		HorizontalPanel instr = new HorizontalPanel();
		if (validMetaInfo) {
			instr
					.add(new Label(
							"You can change the name of your plan or any of the comments"));
		} else {
			instr.add(new Label(
					"Before sharing your plan, give it a name and add any comments"
							+ " you want"));
		}
		HorizontalPanel nameHp = new HorizontalPanel();
		nameHp.setSpacing(HOR_SPACING);
		nameHp.add(new Label("Name your plan:"));
		name = new TextBox();
		if (validMetaInfo) {
			name.setText(EnergyPlan.getMainInstance().getPlanName());
		}
		name.addChangeHandler(new NameBoxHandler(true));
		nameHp.add(name);
		VerticalPanel descVp = new VerticalPanel();
		descVp.setSpacing(VER_SPACING);
		TextBox desc = new TextBox();
		if (validMetaInfo) {
			desc.setText(EnergyPlan.getMainInstance().getPlanDescription());
		}
		desc.addChangeHandler(new NameBoxHandler(false));
		descVp.add(new Label("Comment on your Plan"));
		descVp.add(desc);
		// desc.addStyleName("descriptionBox");
		desc.setStylePrimaryName("descriptionBox");
		// optional email
		HorizontalPanel optEmailHp = new HorizontalPanel();
		optEmailHp.setSpacing(HOR_SPACING);
		optEmailHp.add(new Label("Provide an email address (optional):"));
		optionalEmail = new TextBox();
		String recEmail = EnergyPlan.getMainInstance().getOptionalEmail();
		if (validMetaInfo && recEmail != null) {
			optionalEmail.setText(recEmail);
		}
		optionalEmail.addChangeHandler(new OptionalEmailBoxHandler());
		optEmailHp.add(optionalEmail);
		dialogContents.clear();
		dialogContents.add(instr);
		dialogContents.add(nameHp);
		dialogContents.add(descVp);
		dialogContents.add(optEmailHp);
	}

	private void populateSharePanel() {
		dialogBox.setText("Share Your Plan");
		boolean nameChanged = EnergyPlan.getMainInstance().hasNameChanged();
		// fill out the plan if they have not done so yet
		createNameAndDescription(nameChanged);
		Button sharePlanButton = new Button("Share Plan");
		dialogContents.add(sharePlanButton);
		sharePlanButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (name != null) {
					if (name.getText().length() > 0) {
						addAndProvideShareLink();
					} else {
						// show error
						Utils
								.showDialogue("You need to provide your plan with a name."
										+ " Put the cursor in the name box and enter a name");
					}
				} else {
					// show error
				}
			}
		});

		createFooter();
	}

	private void addAndProvideShareLink() {
		energyService.addEnergyPlan(EnergyPlan.getMainInstance()
				.getSimplePlanFOrServer(), new AsyncCallback<Long>() {

			@Override
			public void onFailure(Throwable caught) {
				// dialogContents.clear();
				dialogContents.add(new Label(
						"There is currently a problem with "
								+ " the plan maker, please try again later."));
				createFooter();
			}

			@Override
			public void onSuccess(Long result) {
				dialogContents.clear();
				dialogContents.setSpacing(4);
				dialogContents.add(new Label(
						"Your Link for sharing your plan is as follows"));
				final TextBox linkText = new TextBox();
				linkText.addStyleName("shareLinkText");
				linkText.setText("http://www.energyplanmaker.com?planid="
						+ result);
				linkText.setReadOnly(true);
				linkText.selectAll();
				linkText.addClickHandler(new ClickHandler() {

					public void onClick(ClickEvent event) {
						linkText.selectAll();
					}
				});
				// Label linkLabel = new Label(
				// "http://www.energyplanmaker.com?planid=" + result);
				DecoratorPanel dp = new DecoratorPanel();
				dp.add(linkText);
				dialogContents.add(dp);
				dialogContents
						.add(new Label(
								"Click in the box above to highlight the link and then copy it to"
										+ " blog postings, email signatures etc. "));
				dialogContents.add(new Label("People will then be able"
										+ " to see your plan by simply clicking on the link. You will"
										+ " also be able to come back to it at any point in the future."));
				final DisclosurePanel discPanel = new DisclosurePanel();
				discPanel.setHeader(new Label("Click here to show plan as plain text"));
				discPanel.addOpenHandler(new OpenHandler<DisclosurePanel>() {
					public void onOpen(OpenEvent<DisclosurePanel> event) {
					discPanel.setHeader(new Label("Click here to hide plain text plan"));	
					}
				});
				discPanel.addCloseHandler(new CloseHandler<DisclosurePanel>() {
					public void onClose(CloseEvent<DisclosurePanel> event) {
					discPanel.setHeader(new Label("Click here to show plan as plain text"));	
					}
				});
				ScrollPanel plainTextScroll = new ScrollPanel();
				plainTextScroll.setStylePrimaryName("plainTextScroll");
				TextBox plainTextPlan = new TextBox();
				plainTextPlan.setWidth("350px");
				plainTextPlan.setHeight("350px");
				plainTextPlan.setText(EnergyPlan.getMainInstance()
						.getPlainTextFromPlan());
				plainTextScroll.add(plainTextPlan);
				discPanel.add(plainTextScroll);
				//dialogContents.add(discPanel);

				// dialogContents
				// .add(new Label(
				// "If you would like to save your plan please click on the Save Button"));
				// dialogBox.add(dialogContents);
				createFooter();
			}
		});
	}

	private HorizontalPanel addSaveAsPanel() {
		HorizontalPanel hp = new HorizontalPanel();
		Button saveAsExisting = new Button("Update Existing Plan");
		if (planCollectionRaw != null) {
			for (SimplerPlan e : planCollectionRaw) {
				if (e.getIsGlobal()) {
					dropDown.addItem(e.getName(), new Long(e.getId())
							.toString());
				}
			}
			dropDown.addChangeHandler(new ChangeHandler() {
				@Override
				public void onChange(ChangeEvent event) {
					// TODO Auto-generated method stub

				}
			});
			saveAsExisting
					.addClickHandler(new SaveAsExistingPlanClickHandler());
			hp.add(saveAsExisting);
			hp.add(dropDown);
		} else {
			saveAsExisting.setEnabled(false);
			hp.add(saveAsExisting);
			// dropDown.addItem("No Plans exist", (new Long(-1)).toString());
		}
		return hp;
	}

	public void addCloseHandler(CloseHandler<PopupPanel> handler) {
		dialogBox.addCloseHandler(handler);
	}

	public void center() {
		this.dialogBox.center();
	}

	public void show() {
		dialogBox.setPopupPosition(Utils.POPUP_LEFT_POS, Utils.POPUP_TOP_POS);
		dialogBox.show();
	}

	public void hide() {
		dialogBox.hide();
	}

	class ShareButtonClickHandler implements ClickHandler {

		public ShareButtonClickHandler() {
		}

		@Override
		public void onClick(ClickEvent event) {
			populateSharePanel();

		}

	}

	class SaveAsNewPlanClickHanlder implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// change panel to include name and description and save button
			dialogContents.add(new Label("Name and comment on your new plan"));
			createNameAndDescription(false);
			Button saveButton = new Button("Save New Plan");
			saveButton.addClickHandler(new SaveButtonClickHandler());
			dialogContents.add(saveButton);
			createFooter();
		}

	}

	class SaveAsExistingPlanClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// Saves as existing plan
			dialogContents
					.add(new Label(
							"Update the Name and Comments on your existing plan if you would like"));
			createNameAndDescription(true);
			Button saveButton = new Button("Update Existing Plan");
			saveButton.addClickHandler(new SaveAsButtonClickHandler());
			dialogContents.add(saveButton);
			createFooter();

		}

	}

	class SaveButtonClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			energyService.addEnergyPlan(EnergyPlan.getMainInstance()
					.getSimplePlanFOrServer(), new AsyncCallback<Long>() {

				@Override
				public void onFailure(Throwable caught) {
					Utils
							.showDialogue("Whoops there was a problem updating your plan, please try again later"
									+ ", click outside the box to continue"
									+ caught.getLocalizedMessage());

				}

				@Override
				public void onSuccess(Long result) {
					dialogBox.hide();
				}
			});
		}
	}

	class SaveAsButtonClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			String s = dropDown.getValue(dropDown.getSelectedIndex());
			long planId = -1;
			try {
				planId = Long.parseLong(s);
				energyService.updateEnergyPlan(EnergyPlan.getMainInstance()
						.getSimplePlanFOrServer(), planId,
						new AsyncCallback<Void>() {
							@Override
							public void onFailure(Throwable caught) {
								DialogBox e = new DialogBox();
								e
										.setText("Whoops there was a problem updating your plan, please try again later"
												+ ", click outside the box to continue");
								e.show();
								e.setAutoHideEnabled(true);
								e.setAnimationEnabled(true);
							}

							@Override
							public void onSuccess(Void result) {
								// TODO Auto-generated method stub
							}
						});
			} catch (NumberFormatException nfe) {
				Utils
						.showDialogue("Whoops there was a problem updating your plan, please try again later"
								+ ", click outside the box to continue");

			}
		}
	}

	class NameBoxHandler implements ChangeHandler {

		private boolean isName = true;

		public NameBoxHandler(boolean isName) {
			this.isName = isName;
		}

		@Override
		public void onChange(ChangeEvent event) {
			TextBox t = (TextBox) event.getSource();
			String s = t.getText();
			if (isName) {
				EnergyPlan.getMainInstance().setPlanName(s);
			} else {
				EnergyPlan.getMainInstance().setPlanDescription(s);
			}
		}

	}

	class OptionalEmailBoxHandler implements ChangeHandler {

		public OptionalEmailBoxHandler() {
		}

		@Override
		public void onChange(ChangeEvent event) {
			TextBox t = (TextBox) event.getSource();
			String s = t.getText();
			EnergyPlan.getMainInstance().setOptionalEmail(s);

		}

	}

}
