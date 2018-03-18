package tdt4140.gr1823.app.ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import tdt4140.gr1823.app.core.ActivityManager;
import tdt4140.gr1823.app.core.ServiceProvider;
import tdt4140.gr1823.app.core.UserManager;

public class UserScreenController implements Initializable{
	
	private ActivityManager activityManager = new ActivityManager();
	private UserManager userManager = new UserManager();
	private ServiceProvider serviceProvider = new ServiceProvider();
	private boolean acceptDataSharing;
	
	private String username;
	
	
	@FXML
	protected Text getDailyActivity;
	
	@FXML
	protected TextField setUsername;
	
	@FXML
	protected Button recordUsernameButton;
	
	@FXML
	protected Text getRecActivity;
	
	@FXML
	protected Text getNationalAverage;
	
	@FXML
	protected CheckBox yesBox;
	
	@FXML
	protected Text acceptDataSharingText;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		acceptDataSharingText.setVisible(false);
		yesBox.setVisible(false);
		//getDailyActivity.setText("Enter user ID below to see your step count for the day");
		getRecActivity.setText("The recommended activity level is " + serviceProvider.getRecommendedDailyActivity() + " steps");
		try {
			getNationalAverage.setText("Today the average number of steps among users is " + activityManager.getNationalAverage() + " steps");
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		setUsername.setPromptText("Username");
		
		recordUsernameButton.setOnAction((e) -> {
			username = setUsername.getText();
			try {
				getDailyActivity.setText("You have walked " + (activityManager.getTodaySteps(username) + " steps today"));
				System.out.println(activityManager.getTodaySteps(username));
			} catch (Exception e1) {
				e1.printStackTrace();
				getDailyActivity.setText("You have no recorded data for today. Are you sure you entered the correct ID?");
			}
			/*
			try {
				yesBox.setSelected(userManager.getShareValue(username));
				acceptDataSharingText.setVisible(true);
				yesBox.setVisible(true);
		} catch (Exception e2) {
				e2.printStackTrace();
			}
			 */		
		}
				);
		
		
	}
	
	@FXML
	private void handleYesBox() {
		if(yesBox.isSelected()) {
			userManager.setShareValue(username, true);
		}
	}
	
	

}











