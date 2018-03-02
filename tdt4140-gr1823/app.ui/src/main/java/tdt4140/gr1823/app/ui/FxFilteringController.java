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
	
	ObservableList<String> genders = FXCollections.observableArrayList(null,"MALE", "FEMALE"); 
	//get from Gender() enum/class, choices in choicebox for gender selection
	
	ObservableList<String> ages = FXCollections.observableArrayList(null, "Under 25", "25-50", "Over 50");
	//choices in choicebox for selecting age group
	
	@FXML
	private Button buttonDemographic;
	
	@FXML 
	private ChoiceBox<String> cbGender; //references id:cbGender in FXML
	
	@FXML
	private TextField textInput1;
	
	@FXML 
	private TextField textInput2;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Loading user data...");
		
		//gender.setText("Specify gender:"); //connects to id:gender in FXML
		cbGender.setItems(genders);
		cbGender.setValue(null); //default value
		
		textInput1.setText(""); //default value 
		textInput2.setText(""); //default value
		
		buttonDemographic.setText("Submit"); 
		buttonDemographic.setOnAction(e -> getChoice(cbGender, textInput1, textInput2));
		//when pressing button getChoice gets called
		
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

	//To get the values of the selected items. Both gender and age
	//Need to implement method that returns enum Gender object? 
	private void getChoice(ChoiceBox<String> choiceBox1, TextField input1, TextField input2) {
		if(!(isValidInput(input1) && isValidInput(input2))){
			System.out.println("Invalid input. Please try again");//popup invalid input. try again
		}
		else {
			String gender = choiceBox1.getValue();
			String fromAge = input1.getText();
			String toAge = input2.getText();
			System.out.println(gender + fromAge + toAge);
			//calculateSteps(gender, fromAge, toAge);
		}
		
		
	}
	

	

	
	
	
}
