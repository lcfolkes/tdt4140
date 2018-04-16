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
	protected Text getDailyActivity; //placeholder to show the daily activity of a user. 
	
	@FXML
	protected TextField setUsername; //TextField to type in username
	
	@FXML
	protected Button recordUsernameButton; //Button to click after typed username (login-button)
	
	@FXML
	protected Text getRecActivity; //placeholder to show the recommended daily activity set by Helsedirektoratet.
	
	@FXML
	protected Text getNationalAverage; //placeholder to show the national average of number of steps.
	
	@FXML
	protected CheckBox yesBox; //Box to tell whether or not the user wants to share their step-data
	
	@FXML
	protected CheckBox noBox;	//Box to tell whether or not the user wants to share their step-data
	
	@FXML
	protected Text acceptDataSharingText; //information box

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Hide the fields before login of a user
		acceptDataSharingText.setVisible(false);
		yesBox.setVisible(false);
		noBox.setVisible(false);
		
		//Fill the placeholders getRecActivity and getNationalAverage with values from DB
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
		
		//Prompt text inside username-field to indicate that the user should type in their username 
		setUsername.setPromptText("Username");
		
		//Whenever recordUsernameButton is hit, do the following: 
		//Update the number of steps walked today by the user. Display info on whether or not the user share his/hers step-data
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
	
	//Updates the DB-column "Share" for the specified user to match the state of the check-boxes. 
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
	
	//Updates the DB-column "Share" for the specified user to match the state of the check-boxes. 
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











