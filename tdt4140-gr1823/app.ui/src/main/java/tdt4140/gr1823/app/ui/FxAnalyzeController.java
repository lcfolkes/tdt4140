package tdt4140.gr1823.app.ui;

import java.awt.List;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.Chart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import tdt4140.gr1823.app.db.ActivityManager;
import tdt4140.gr1823.app.db.SPManager;
import tdt4140.gr1823.app.db.UserManager;

public class FxAnalyzeController implements Initializable {
	
	private ActivityManager activityManager = new ActivityManager();
	private SPManager SPManager = new SPManager();
	private UserManager UManager = new UserManager();
		
	ObservableList<String> genders = FXCollections.observableArrayList("NOT SPECIFIED","MALE", "FEMALE"); 
     
	@FXML
	protected Button submitButton;
	
	@FXML 
	protected ComboBox<String> cbGender;
	
	@FXML 
	protected TextField textInput1; //referenced id: textInput1 in FXML
	
	@FXML 
	protected TextField textInput2;
	
	@FXML
	protected Label errorLabel;
	
	@FXML
	protected Label averageLabel;
	
	@FXML
	protected Text numUsersText;
	
	
	@FXML
	protected BarChart<String,Integer> barChart;
	
	Series<String, Integer> chartData = new Series<>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Loading user data...");
		
		numUsersText.setVisible(false);
		
		cbGender.setItems(genders);
		cbGender.setValue("NOT SPECIFIED"); //default value
		
		textInput1.setText(""); //default value 
		textInput2.setText(""); //default value
		
		errorLabel.setVisible(false);
		errorLabel.setTextFill(Color.RED);  //css styling of error label
				
		initializeBarChart();
		
		submitButton.defaultButtonProperty().bind(submitButton.focusedProperty());
		
		submitButton.setOnAction(e -> {
			try {
				getChoice(cbGender, textInput1, textInput2);
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			}
		});
	}
	
	//checking if age group input is valid. ie. not a string unless empty string and not invalid integer
	private boolean isValidInput(TextField textInput) {
		String input = textInput.getText();
		int inputInt;
		try {
			inputInt = Integer.parseInt(input);
		}
		catch(NumberFormatException e) {
			if(input.isEmpty()){
				return true;
			}
			return false;
		}
		if (inputInt < 0 || inputInt > 120){
			return false; 
		};
		return true;
	}
	
	private boolean isValidOrder(TextField textInput1, TextField textInput2) {
			try {
				return Integer.parseInt(textInput1.getText()) < Integer.parseInt(textInput2.getText());
				}
			catch(NumberFormatException e) {
				if(textInput1.getText().isEmpty() || textInput2.getText().isEmpty()){
					return true;
				}
			}
		
		return false;
	}
	
	private void initializeBarChart() {
		int nationalAverage = 0;
		int recDailyActivity = 0;
		try {
			nationalAverage = (int) activityManager.getNationalAverage("DailySteps");
			recDailyActivity = (int) SPManager.getRecommendedDailyActivity("RecommendedDailyActivity");
		} catch (NumberFormatException f) {
			f.printStackTrace();
		} catch (SQLException g) {
			g.printStackTrace();
		}
		chartData.getData().clear();
		barChart.setLegendVisible(false);
		//chartData.setName("Compare results");       
		chartData.getData().add(new XYChart.Data<>("National average", nationalAverage));
		chartData.getData().add(new XYChart.Data<>("Recommended daily activity", recDailyActivity));
		chartData.getData().add(new XYChart.Data<>("Filter result", 0));
		barChart.getData().add(chartData);
	}
	
	private void updateBarChart (int rs) {
		chartData.getData().remove(2);
		chartData.getData().add(new XYChart.Data<>("Filter result", rs));
	}
 
	//To get the values of the selected items. Both gender and age
	//Need to implement method that returns enum Gender object? 
	private void getChoice(ComboBox<String> comboBox, TextField input1, TextField input2) {
		textInput1.getStyleClass().remove("error");
		textInput2.getStyleClass().remove("error");
				
		if(!(isValidInput(input1) && isValidInput(input2) && isValidOrder(input1, input2))){
			if (!isValidInput(input1)){
				textInput1.getStyleClass().add("error");
			}
			if (!isValidInput(input2)){
				textInput2.getStyleClass().add("error");
			}
			else if(!isValidOrder(input1, input2)){
				textInput1.getStyleClass().add("error");
				textInput2.getStyleClass().add("error");
			}
			errorLabel.setVisible(true); //error label gets displayed
			averageLabel.setText("");
			System.out.println("Invalid input. Please try again");
		}
		else {
			errorLabel.setVisible(false);
			textInput1.getStyleClass().remove("error");
			textInput2.getStyleClass().remove("error");
			
			String gender = comboBox.getValue();
			String fromAge = input1.getText();
			String toAge = input2.getText();
			double rs;
			Integer numUsers = 0;
			int result = 0;
			int totalUsers = 0;
			
			try {
				rs = activityManager.filter(fromAge, toAge, gender, "DailySteps", "Person");
			System.out.println(fromAge + toAge +  gender);
			result = (int) rs;
			updateBarChart(result);
			totalUsers = UManager.getNumberOfUsers("Person");
			
			if (fromAge.equals("") && toAge.equals("") && gender.equals("NOT SPECIFIED")) {
				numUsers = UManager.getNumberOfUsers("Person");
			} else if (gender.equals("NOT SPECIFIED")) {
				if (fromAge.equals("")) { fromAge = "0"; }
				if (toAge.equals("")) { toAge = "120"; }
				numUsers = UManager.getNumberOfUsers("Person", fromAge, toAge);
			} else if (fromAge.equals("") && toAge.equals("")) {
				numUsers = UManager.getNumberOfUsers("Person", gender);
			} else {
				numUsers = UManager.getNumberOfUsers("Person", fromAge, toAge, gender);				
			}
			
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//BarChart.getData().add(chartData);
			
			numUsersText.setText(numUsers + "/" + totalUsers);
			numUsersText.setVisible(true);
				
			if(result != 0){
				averageLabel.setText(Integer.toString(result));
			}
			else{
				averageLabel.setText("Cannot find data for this request in the database.");
			}
			
		}
	}
}
