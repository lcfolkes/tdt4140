package tdt4140.gr1823.app.ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import tdt4140.gr1823.app.core.User;
import tdt4140.gr1823.app.db.SPManager;
import tdt4140.gr1823.app.ui.SceneNavigator;
import javafx.scene.control.PasswordField;

public class FxSettingsController implements Initializable {
	
	private SPManager SPManager = new SPManager();
	 
	@FXML
	protected Button recActButton;
	
	@FXML
	protected Button setUsernameButton;
	
	@FXML
	protected Button setPasswordButton;
	
	@FXML 
	protected TextField recActInput;
	
	@FXML 
	protected TextField username;
	
	@FXML 
	protected PasswordField password;
	
	@FXML
	protected Label errorLabel;
	
	@FXML
	protected Text recommendedActivity;
	
	@FXML
	protected Button logOutButton;
	
	@FXML
	protected Text incorrectInput;
	
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		incorrectInput.setVisible(false);
		recActInput.setText(""); //default values
		username.setPromptText("Username");  
		password.setPromptText("Password");  
		//username.setText(""); 
		//password.setText(""); 
		
		String prevValue = Integer.toString(SPManager.getRecommendedDailyActivity("RecommendedDailyActivity"));
		recommendedActivity.setText(prevValue); //error label gets displayed
		
		errorLabel.setVisible(false);
		errorLabel.setTextFill(Color.RED);  //css styling of error label
		recActButton.defaultButtonProperty().bind(recActButton.focusedProperty());
		setUsernameButton.defaultButtonProperty().bind(setUsernameButton.focusedProperty());
		setPasswordButton.defaultButtonProperty().bind(setPasswordButton.focusedProperty());


		
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
		
		logOutButton.setOnAction(e -> {
			SceneNavigator.loadScene(SceneNavigator.LOGINSCREEN);
		});
		
		setUsernameButton.setOnAction(e -> {
			String newUsername = username.getText();
			SPManager.updateUsername(newUsername, "User");
			username.clear();
		});
		
		setPasswordButton.setOnAction(e -> {
			incorrectInput.setVisible(false);
			String newPassword = password.getText();
			if (User.isValidPassword(newPassword)) {
				SPManager.updatePassword(newPassword, "User");
				password.clear();
			} else {
				incorrectInput.setVisible(true);
				incorrectInput.setFill(Color.RED);
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
			SPManager.setRecommendedDailyActivity(newValue, "RecommendedDailyActivity");
			String rs = Integer.toString(newValue);
			recommendedActivity.setText(rs);	
			
		}
	}
}
