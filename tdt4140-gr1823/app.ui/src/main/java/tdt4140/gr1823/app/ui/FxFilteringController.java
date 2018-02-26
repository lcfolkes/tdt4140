package tdt4140.gr1823.app.ui;

import javafx.scene.control.Button;

public class FxFilteringController {
	
	public Button button;
	
	public void handleButtonClick() {
		System.out.println("Run some code the user doesn't see");
		button.setText("Stop touching me!");
	}
	
	
}
