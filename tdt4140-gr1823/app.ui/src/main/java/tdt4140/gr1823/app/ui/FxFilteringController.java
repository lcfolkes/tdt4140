package tdt4140.gr1823.app.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.*; 

public class FxFilteringController implements Initializable {
	
	
	public ToggleGroup toggleGroup;
	
	ObservableList<String> genders = FXCollections.observableArrayList("MALE", "FEMALE"); 
	//get from Gender() enum/class, choices in choicebox for gender selection
	
	ObservableList<String> ages = FXCollections.observableArrayList("Under 25", "25-50", "Over 50");
	//choices in choicebox for selecting age group
	
	@FXML
	private Button buttonDemographic;
	
	@FXML 
	private ChoiceBox<String> cbGender; //references id:cbGender in FXML
	
	@FXML
	private ChoiceBox<String> cbAgeGroup; //references id: cbAgeGroup fxml
	
	@FXML
	private Label gender; //references id:gender in FXML
	@FXML
	private Label ageGroup; //references id:ageGroup 
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Loading user data...");
		
		gender.setText("Specify gender:"); //connects to id:gender in FXML
		cbGender.setItems(genders);
		
		
		ageGroup.setText("Specify age group:"); //connects to id:ageGroup in fxml
		cbAgeGroup.setItems(ages); 
		
		buttonDemographic.setText("Submit"); 
		buttonDemographic.setOnAction(e -> getChoice(cbGender, cbAgeGroup)); 
		//when pressing button getChoice gets called
		
	}
	
	//To get the values of the selected items. Both gender and age
	//Need to implement method that returns enum Gender object? 
	private void getChoice(ChoiceBox<String> choiceBox1, ChoiceBox<String> choiceBox2 ) {
		String gender = choiceBox1.getValue();
		String age = choiceBox2.getValue(); 
		System.out.println(gender);
		System.out.println(age);
	}

	

	
	
	
}
