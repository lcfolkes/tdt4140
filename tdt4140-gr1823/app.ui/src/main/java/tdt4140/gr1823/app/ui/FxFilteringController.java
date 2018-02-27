package tdt4140.gr1823.app.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.*; 

public class FxFilteringController implements Initializable {
	
	public Button button;
	public ToggleGroup toggleGroup;
	
	ObservableList<String> genders 
    = FXCollections.observableArrayList("MALE", "FEMALE"); //get from Gender() enum/class
	
	@FXML 
	private ChoiceBox choiceBox; //references id:choiceBox in FXML
	//choiceBox = new ChoiceBox<String>(FXCollections.observableArrayList(genders));
	
	@FXML
	private Label gender; //references id:gender in FXML
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Loading user data...");
		gender.setText("Specify gender:"); //connects to id:gender in FXML
		choiceBox.setItems(genders);
		
		
	}
	
	public void handleButtonClick() {
		System.out.println("Run some code the user doesn't see");
		button.setText("Never gonna give you up\n" + 
				"Never gonna let you down\n" + 
				"Never gonna run around and desert you\n" + 
				"Never gonna make you cry\n" + 
				"Never gonna say goodbye\n" + 
				"Never gonna tell a lie and hurt you!");
	}
	

	
	
	
}
