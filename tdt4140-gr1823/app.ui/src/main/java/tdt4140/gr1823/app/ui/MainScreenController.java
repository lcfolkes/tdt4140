package tdt4140.gr1823.app.ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tdt4140.gr1823.app.db.ActivityManager;
import tdt4140.gr1823.app.db.SPManager;
import tdt4140.gr1823.app.db.UserManager;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;


public class MainScreenController implements Initializable {

	

	private SPManager serviceProvider = new SPManager();
	private UserManager userManager = new UserManager();
	private ActivityManager activityManager = new ActivityManager();
	
	// Denne klassen er ikke helt ferdig, men pusher den til branchen for at Tor skal kunne jobbe pÃ¥ den. 
	
	// Stage window;
	// Stage scene;
	
	@FXML
	protected Text getRecDailyActivity;
	
	@FXML
	protected Text getNumOfUsers;

	@FXML
	protected Text getNationalAverage;
	
	@FXML
	protected Button setValueButton;
	
	@FXML
	protected TextField setValueField;

	@FXML
	protected Button filterStepsButton;

	@FXML 
	protected Label errorLabel;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//errorLabel.setVisible(false);
		//errorLabel.setTextFill(Color.RED); //css styling of error label
		
		getRecDailyActivity.setText(serviceProvider.getRecommendedDailyActivity() + " steps");
		
		try {
			getNumOfUsers.setText((Integer.toString(userManager.getNumberOfUsers()) + " users"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		try {
			getNationalAverage.setText(Double.toString(activityManager.getNationalAverage()) + " steps");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		setValueButton.setOnAction((e) -> {
			//Old version
			//serviceProvider.setRecommendedDailyActivity(Integer.parseInt(setValueField.getText()));
			
			String inputText = getInput(setValueField);
			serviceProvider.setRecommendedDailyActivity(Integer.parseInt(inputText));
			getRecDailyActivity.setText(serviceProvider.getRecommendedDailyActivity() + " steps");
		
		});
		
		//Create method to connect to FxFiltering.java, in order to run the applications when pushing the "FILTER STEPS" button
		
		
		//styling
		setValueField.getStyleClass().add("");
//		getRecDailyActivity;
//		
//		getNumOfUsers;
//
//		getNationalAverage;
//		
//		setValueButton;
//		
//		setValueField;
//
//		filterStepsButton;

			
	}
		
	
	//checking if the national average input is valid
	private boolean isValidInput(TextField textInput) {
		String input = textInput.getText();
		int inputInt;
		try {
			inputInt = Integer.parseInt(input);
		}
		catch(NumberFormatException e) {
			if(input.isEmpty()) {
				return true;
			}
			return false;
		}
		if (inputInt <= 0){
			return false;
		}
		return true;
	
	}
	
	private String getInput (TextField setValueField) {
		//setValueField.getStyleClass().remove("error");
		String inputString = setValueField.getText();
		
	if (!isValidInput(setValueField)){
			//setValueField.getStyleClass().add("error");
		
		//errorLabel.setVisible(true); //error label gets displayed
		System.out.println("Invalid input. Please try again");
		return null;
		}
	else {
		//errorLabel.setVisible(false);
		//setValueField.getStyleClass().remove("error");
		
		String input = setValueField.getText();
		System.out.println(input);
		return input;
	}
	
	//Testline
	}
}

