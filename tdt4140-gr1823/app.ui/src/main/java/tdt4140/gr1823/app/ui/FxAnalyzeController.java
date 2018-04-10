package tdt4140.gr1823.app.ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import tdt4140.gr1823.app.db.ActivityManager;
import tdt4140.gr1823.app.db.SPManager;

public class FxAnalyzeController implements Initializable {
	
	private ActivityManager activityManager = new ActivityManager();
	private SPManager SPManager = new SPManager();
	
	//public ToggleGroup toggleGroup;
	
	//get from Gender() enum/class, choices in choicebox for gender selection
	ObservableList<String> genders = FXCollections.observableArrayList("","MALE", "FEMALE"); 

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
	protected Button backButton;
	
	@FXML
	protected Label averageLabel;
	
	@FXML
	protected BarChart<String,Integer> BarChart;
	
	XYChart.Series<String, Integer> chartData = new XYChart.Series<>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Loading user data...");
		
		cbGender.setItems(genders);
		cbGender.setValue("NOT SPECIFIED"); //default value
		
		textInput1.setText(""); //default value 
		textInput2.setText(""); //default value
		
		errorLabel.setVisible(false);
		errorLabel.setTextFill(Color.RED);  //css styling of error label
		
		//submitButton.getStyleClass().add("button.success");
		
		submitButton.setOnAction(e -> {
			try {
				getChoice(cbGender, textInput1, textInput2);
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		updateBarChart(0);
			
		});
		
		backButton.setOnAction(e -> {
			SceneNavigator.loadScene(SceneNavigator.MAINSCREEN);
		});
		
		//styling
		backButton.getStyleClass().add("button");
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
	
	private void updateBarChart(int average) {
		int nationalAverage = 0;
		int recDailyActivity = 0;
		int result = 0;
	
		try {
			nationalAverage = (int) activityManager.getNationalAverage();
			recDailyActivity = (int) SPManager.getRecommendedDailyActivity();
		} catch (NumberFormatException f) {
			f.printStackTrace();
		} catch (SQLException g) {
			g.printStackTrace();
		}   
	
		chartData.getData().clear();
	   chartData.setName("Compare results");       
	   chartData.getData().add(new XYChart.Data<>("National average", nationalAverage));
	   chartData.getData().add(new XYChart.Data<>("Recommended daily activity", recDailyActivity));
	   chartData.getData().add(new XYChart.Data<>("Filter result", result));
	   BarChart.getData().add(chartData);
	}

	//To get the values of the selected items. Both gender and age
	//Need to implement method that returns enum Gender object? 
	private void getChoice(ComboBox<String> comboBox, TextField input1, TextField input2) throws NumberFormatException, SQLException {
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
			Double average = activityManager.filter(fromAge, toAge, gender);
			int intAverage = average.intValue();
			updateBarChart(intAverage);
			
			BarChart.getData().add(chartData);
				
			if(average != 0){
				averageLabel.setText(average.toString());
			}
			else{
				averageLabel.setText("Cannot find data for this request in the database.");
			}
			
		}
	}
}
