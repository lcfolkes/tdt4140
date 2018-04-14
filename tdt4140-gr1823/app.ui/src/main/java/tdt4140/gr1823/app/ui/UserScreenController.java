package tdt4140.gr1823.app.ui;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import tdt4140.gr1823.app.HttpCommunication.HttpCommunication;;

public class UserScreenController implements Initializable{
	
	private HttpCommunication http = new HttpCommunication();
	
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
	protected CheckBox noBox;
	
	@FXML
	protected Text acceptDataSharingText;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		acceptDataSharingText.setVisible(false);
		yesBox.setVisible(false);
		noBox.setVisible(false);
		//getDailyActivity.setText("Enter user ID below to see your step count for the day");
		
		try {
			getRecActivity.setText("The recommended activity level is " + http.getRecommendedDailyActivity("RecommendedDailyActivity") + " steps");
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		try {
			getNationalAverage.setText("Today the average number of steps among users is " + http.getNationalAverage("DailySteps") + " steps");
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		setUsername.setPromptText("Username");
		
		recordUsernameButton.setOnAction((e) -> {
			username = setUsername.getText();
			try {
				getDailyActivity.setText("You have walked " + (http.getTodaySteps(username, "DailySteps") + " steps today"));
			} catch (Exception e1) {
				e1.printStackTrace();
				getDailyActivity.setText("You have no recorded data for today. Are you sure you entered the correct ID?");
			}
			
			try {
				if (http.getShareValue(username, "Person") == true) {
					yesBox.setSelected(true);
					noBox.setSelected(false);
				} else if (http.getShareValue(username, "Person") == false) {
					yesBox.setSelected(false);
					noBox.setSelected(true);
				}
				acceptDataSharingText.setVisible(true);
				yesBox.setVisible(true);
				noBox.setVisible(true);
		} 
			catch (Exception e2) {
				e2.printStackTrace();
			}
		});
		
		
	}
	
	@FXML
	private void handleYesBox() {
		try {
			if ((http.getShareValue(username, "Person") == true)) {
				noBox.setSelected(true);
				yesBox.setSelected(false);
				http.setShareValue(username, 0, "Person");
			} else if ((http.getShareValue(username, "Person") == false)) {
				noBox.setSelected(false);
				yesBox.setSelected(true);
				http.setShareValue(username, 1, "Person");
			}
	} 
		catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	@FXML
	private void handleNoBox() {
		try {
			if ((http.getShareValue(username, "Person") == true)) {
				noBox.setSelected(true);
				yesBox.setSelected(false);
				http.setShareValue(username, 0, "Person");
			} else if ((http.getShareValue(username, "Person") == false)) {
				noBox.setSelected(false);
				yesBox.setSelected(true);
				http.setShareValue(username, 1, "Person");
			}
	} 
		catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	

}











