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
import javafx.scene.control.PasswordField;

/** The class for controlling the settings window. This is where the user can change their username and password as well as log out. 
 * The user can also update the recommended daily activity
 * @author Gruppe 23
 *
 */
public class FxSettingsController implements Initializable {
	
	//Use SPManager to communicate with the database
	private SPManager SPManager = new SPManager();
	 
	//FXML objects with fx:id from FxSettingsScreen.fxml
	@FXML
	protected Button recActButton; //Button for updating the recommended daily step count
	@FXML
	protected Button setUsernameButton; //Button for updating username
	@FXML
	protected Button setPasswordButton; //Button for updating password
	@FXML 
	protected TextField recActInput; //Text field for new recommended daily activity step count
	@FXML 
	protected TextField username; //Text field for entering new username
	@FXML 
	protected PasswordField password; //Text field for entering new password
	@FXML
	protected Label errorLabel; //Label for incorrect input (for recommended activity)
	@FXML
	protected Text recommendedActivity; //Text displaying current recommended daily activity
	@FXML
	protected Button logOutButton; //Button to log out
	@FXML
	protected Text incorrectInput; //Text for incorrect input (for password)
	
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		//Hide fields not to be shown from start
		incorrectInput.setVisible(false);
		errorLabel.setVisible(false);
		
		//Set default values and promt text
		recActInput.setText("");
		username.setPromptText("Username");  
		username.setPromptText("Password");  
		errorLabel.setTextFill(Color.RED);  //css styling of error label
		
		//Get current recommended activity from database and display it
		String prevValue = Integer.toString(SPManager.getRecommendedDailyActivity("RecommendedDailyActivity"));
		recommendedActivity.setText(prevValue);
		
		//PRocedures when buttons are pressed
		recActButton.setOnAction(e -> {
			try {
				updateRecAct(recActInput);
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	
		});
		
		setUsernameButton.setOnAction(e -> {
			updateUsername();
		});
		
		setPasswordButton.setOnAction(e -> {
			updatePassword();
		});
		
		//Obviously logs out
		logOutButton.setOnAction(e -> {
			SceneNavigator.loadScene(SceneNavigator.LOGINSCREEN);
		});
	}
	
	/** 
	 * Method for updating password. First checks if new password meets requirements and then updates it in the database.
	 */
	private void updatePassword() {
		incorrectInput.setVisible(false);
		String newPassword = password.getText();
		if (User.isValidPassword(newPassword)) {
			SPManager.updatePassword(newPassword, "User");
			password.clear();
		} else {
			incorrectInput.setVisible(true);
			incorrectInput.setFill(Color.RED);
		}
	}

	/** 
	 * Method for updating username in database.
	 */
	private void updateUsername() {
		String newUsername = username.getText();
		SPManager.updateUsername(newUsername, "User");
		username.clear();
	}

	/** 
	 * Method for checking if the input is valid. Check if it is an integer.
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
		if (inputInt < 0) {
			return false; 
		};
		return true;
	}
	
	/** 
	 * Method for updating the recommended daily activity. First uses {@link #isValidInput(TextField) isValidInput} to check if input is valid and then updates
	 * in the database.
	 */
	private void updateRecAct(TextField input) throws NumberFormatException, SQLException {
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
