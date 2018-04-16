package tdt4140.gr1823.app.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import tdt4140.gr1823.app.db.SPManager;

/** 
 * The class for controlling the login window. This is where the user (helsedirektoratet) logs in with a username and password.
 * The username and password has to be an instance of the database.
 * @author Gruppe 23
 *
 */
public class LoginScreenController implements Initializable {

	//Use SPManager to communicate with the database
	private SPManager spManager = new SPManager();
	
	//FXML objects with fx:id from FxSettingsScreen.fxml
	@FXML
	protected TextField usernameField; //Where the user enters their username
	@FXML
	protected PasswordField passwordField; //Where the user enters their password
	@FXML
	protected Button loginButton; //Button for logging in after entering information
	@FXML
	protected Label errorLabel; //Label for incorrect input
	
	//Other attributtes
	private String password;
	private String username;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Hide and style error label
		errorLabel.setVisible(false);
		errorLabel.setTextFill(Color.RED); //css styling of error label
		
		loginButton.defaultButtonProperty().bind(loginButton.focusedProperty());

		
		//Log in
		loginButton.setOnAction(e -> {
			try {
				password = passwordField.getText();
				username = usernameField.getText();				
			}
			catch(NullPointerException e1) {
				e1.printStackTrace();
			}
			if(validInput(username, password)) {
				SceneNavigator.loadScene(SceneNavigator.SERVICEPROVIDER); //Goes to the service provider window
			}
			else {
				errorLabel.setVisible(true);
			}
		});
		
	}

	/** Checks if input is valid
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	private boolean validInput(String username, String password) {
		if((username == null || password == null) || (password.equals("") || username.equals(""))){
			return false;
		}
		return spManager.isValidPassword(username, password);
	}
		
}
