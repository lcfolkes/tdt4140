package tdt4140.gr1823.app.ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import tdt4140.gr1823.app.db.DBManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import tdt4140.gr1823.app.db.SPManager;

public class LoginScreenController implements Initializable {
	
	@FXML
	protected TextField usernameField;
	
	@FXML
	protected PasswordField passwordField;
	
	@FXML
	protected Button loginButton;
	
	@FXML
	protected Label errorLabel;
	
	private String password;
	private String username;
	private SPManager spManager = new SPManager();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		errorLabel.setVisible(false);
		errorLabel.setTextFill(Color.RED); //css styling of error label
		
		loginButton.setOnAction(e -> {
			System.out.println("loginbutton");
			try {
				password = passwordField.getText();
				username = usernameField.getText();
			}
			catch(NullPointerException e1) {
				e1.printStackTrace();
			}
			//System.out.println(password + username);
			if(validInput(username, password)) {
				SceneNavigator.loadScene(SceneNavigator.SERVICEPROVIDER);
			}
			else {
				errorLabel.setVisible(true);
			}
			
			
		});
		

	}

	private boolean validInput(String username, String password) {
		return spManager.isValidPassword(username, password);
	}
		
}
