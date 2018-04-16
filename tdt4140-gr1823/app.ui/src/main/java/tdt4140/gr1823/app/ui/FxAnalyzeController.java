package tdt4140.gr1823.app.ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import tdt4140.gr1823.app.db.ActivityManager;
import tdt4140.gr1823.app.db.SPManager;
import tdt4140.gr1823.app.db.UserManager;


/** This class controls the analyze screen in the app. This is where the user selects which segments it wants to see the average for
 * 
 * @author Gruppe23
 *
 */
public class FxAnalyzeController implements Initializable {
	
	//Database manager objects used for communication with the database.
	private ActivityManager ActivityManager = new ActivityManager();
	private SPManager SPManager = new SPManager();
	private UserManager UManager = new UserManager();
		
	ObservableList<String> genders = FXCollections.observableArrayList("NOT SPECIFIED","MALE", "FEMALE"); 
    
	//FXML objects with fx:id from FxAnalyzeScreen 
	@FXML
	protected Button submitButton; //Button for submitting selection of segment
	@FXML 
	protected ComboBox<String> cbGender; //Pull down menu for gender
	@FXML 
	protected TextField ageFromField; //Lower boundary for age of user segment
	@FXML 
	protected TextField ageToField; //Upper boundary for age of user segment
	@FXML
	protected Label errorLabel; //Label for incorrect input
	@FXML
	protected Label averageLabel; //Field displaying the average number of steps of the user segment
	@FXML
	protected Text numUsersText;
	
	@FXML
	protected Label caption;
	
	//Bar chart with three bars (National average, Recommended average and the average of the selected segment)
	@FXML
	protected BarChart<String,Integer> barChart;
	Series<String, Integer> chartData = new Series<>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		//Hide fields not to be shown before selection is made
		numUsersText.setVisible(false);
		errorLabel.setVisible(false);
		
		//Set properties
		cbGender.setItems(genders);
		errorLabel.setTextFill(Color.RED);  //css styling of error label
		initializeBarChart();
		submitButton.defaultButtonProperty().bind(submitButton.focusedProperty()); //Press enter
		
		//Setting default values
		cbGender.setValue("NOT SPECIFIED"); 
		ageFromField.setText(""); 
		ageToField.setText(""); 
		

		//When the submit button is pressed the updateSegment method runs
		submitButton.setOnAction(e -> {
			try {
				updateSegment(cbGender, ageFromField, ageToField);
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			}
		});
	}
	
	//checking if age group input is valid. ie. not a string unless empty string and not invalid integer
	/** Checks if the input is valid, specifically the age group input. Input has to be an integer between 0-120 or can be empty 
	 * @param 
	 * @return boolean value
	 */
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
	
	/** Checks if the input is valid, specifically the age group input. Input has to be in ascending order.
	 * @param 
	 * @return boolean value
	 */
	private boolean isValidOrder(TextField ageFromField, TextField ageToField) {
			try {
				return Integer.parseInt(ageFromField.getText()) < Integer.parseInt(ageToField.getText());
				}
			catch(NumberFormatException e) {
				if(ageFromField.getText().isEmpty() || ageToField.getText().isEmpty()){
					return true;
				}
			}
		return false;
	}
	
	/** Method for initializing bar chart with recommended daily steps and national average
	 */
	private void initializeBarChart() {
		//Set default as zero
		int nationalAverage = 0;
		int recDailyActivity = 0;
		
		//Fetch data from database
		try {
			nationalAverage = (int) ActivityManager.getNationalAverage("DailySteps");
			recDailyActivity = (int) SPManager.getRecommendedDailyActivity("RecommendedDailyActivity");
		} catch (NumberFormatException f) {
			f.printStackTrace();
		} catch (SQLException g) {
			g.printStackTrace();
		}
		
		//Set format and data
		chartData.getData().clear();
		barChart.setLegendVisible(false);       
		chartData.getData().add(new XYChart.Data<>("National average", nationalAverage));
		chartData.getData().add(new XYChart.Data<>("Recommended daily activity", recDailyActivity));
		chartData.getData().add(new XYChart.Data<>("Filter result", 0));
		barChart.getData().add(chartData);
	}
	
	/** Updates bar char with data from the selection
	 */
	private void updateBarChart (int rs) {
		chartData.getData().remove(2);
		chartData.getData().add(new XYChart.Data<>("Filter result", rs));
	}
 
	/** Take in segment selection and find number of users in section as well as update bar graph
	 */
	private void updateSegment(ComboBox<String> comboBox, TextField input1, TextField input2) {
		//Removes potential error fields from last selection
		ageFromField.getStyleClass().remove("error");
		ageToField.getStyleClass().remove("error");
		
		if(!(isValidInput(input1) && isValidInput(input2) && isValidOrder(input1, input2))){
			if (!isValidInput(input1)){
				ageFromField.getStyleClass().add("error");
			}
			if (!isValidInput(input2)){
				ageToField.getStyleClass().add("error");
			}
			else if(!isValidOrder(input1, input2)){
				ageFromField.getStyleClass().add("error");
				ageToField.getStyleClass().add("error");
			}
			errorLabel.setVisible(true); //error label gets displayed
			averageLabel.setText("");
		}
		else {
			errorLabel.setVisible(false);
			ageFromField.getStyleClass().remove("error");
			ageToField.getStyleClass().remove("error");
			
			String gender = comboBox.getValue();
			String fromAge = input1.getText();
			String toAge = input2.getText();
			double rs;
			int result = 0;
			
			//Part for finding number of users in segment
			Integer numUsers = 0;
			int totalUsers = 0;
			
			try {
				rs = activityManager.filter(fromAge, toAge, gender, "DailySteps", "Person");
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
				if (fromAge.equals("")) { fromAge = "0"; }
				if (toAge.equals("")) { toAge = "120"; }
				numUsers = UManager.getNumberOfUsers("Person", fromAge, toAge, gender);				
			}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			numUsersText.setText(numUsers + "/" + totalUsers);
			numUsersText.setVisible(true);	
			if(result != 0){
				averageLabel.setText(Integer.toString(result));
			}
			else{
				averageLabel.setText("N/A");
			}	
		}
	}
}
