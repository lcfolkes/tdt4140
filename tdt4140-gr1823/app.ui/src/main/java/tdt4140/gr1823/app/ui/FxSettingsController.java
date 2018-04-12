package tdt4140.gr1823.app.ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import tdt4140.gr1823.app.db.SPManager;

public class FxSettingsController implements Initializable {
	
	private SPManager SPManager = new SPManager();
	 
	@FXML
	protected Button recActButton;
	
	@FXML
	protected Button profileButton;
	
	@FXML 
	protected TextField recActInput;
	
	@FXML 
	protected TextField username;
	
	@FXML 
	protected TextField password;
	
	@FXML
	protected Label errorLabel;
	
	@FXML
	protected Text recommendedActivity;
	
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		recActInput.setText(""); //default values
		username.setPromptText("Username");  
		username.setPromptText("Password");  
		//username.setText(""); 
		//password.setText(""); 
		
		String prevValue = Integer.toString(SPManager.getRecommendedDailyActivity());
		recommendedActivity.setText(prevValue); //error label gets displayed
		
		errorLabel.setVisible(false);
		errorLabel.setTextFill(Color.RED);  //css styling of error label
		
		recActButton.setOnAction(e -> {
			try {
				getChoice(recActInput);
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
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
		if (inputInt < 0) {
			return false; 
		};
		return true;
	}
	
		
	private void getChoice(TextField input) throws NumberFormatException, SQLException {
		recActInput.getStyleClass().remove("error");
		
		if(!(isValidInput(input))) {
			recActInput.getStyleClass().add("error");
				errorLabel.setVisible(true); //error label gets displayed
		} else {
			errorLabel.setVisible(false);
			recActInput.getStyleClass().remove("error");
		
			String recAct = input.getText();
			int newValue = Integer.parseInt(recAct);
			SPManager.setRecommendedDailyActivity(newValue);
			String rs = Integer.toString(newValue);
			recommendedActivity.setText(rs);
			
			
		}
	}
}
