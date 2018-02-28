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
	
	public Button button;
	public ToggleGroup toggleGroup;
	
	ObservableList<String> genders = FXCollections.observableArrayList("MALE", "FEMALE"); 
	//get from Gender() enum/class, choices in choicebox for gender selection
	
	ObservableList<String> ages = FXCollections.observableArrayList("Under 25", "25-50", "Over 50");
	//choices in choicebox for selecting age group
	
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
		/*cbGender.getSelectionModel().selecte
		
		selectedIndexProperty().addListener(new 
				Changelistener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_balue) {
				label.setText(genders[new_value.intValue()]);
			}
		});*/
		//SingleSelectionModel<String> selectedGender = cbGender.getSelectionModel();
		//System.out.println(selectedGender.toString());
		
		ageGroup.setText("Specify age group:");
		cbAgeGroup.setItems(ages);
		
		
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
