package tdt4140.gr1823.app.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;

public class FxFilteringController implements Initializable {
	
	public Button button;
	public ToggleGroup toggleGroup;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Loading user data...");
		// TODO Auto-generated method stub
		
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
