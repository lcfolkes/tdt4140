package tdt4140.gr1823.app.ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import tdt4140.gr1823.app.db.DBManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class LoginScreenController implements Initializable {
	
	@FXML
	TextField getUsername;
	
	@FXML
	PasswordField getPassword;
	
	@FXML
	Button loginButton;
	
	@FXML
	Label errorLabel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		errorLabel.setVisible(true);
		
		String password = getPassword.getText();
		String username = getUsername.getText();
		
		loginButton.setOnAction(e -> {
			SceneNavigator.loadScene(SceneNavigator.MAINSCREEN);
			System.out.println("loginbutton");
		});
		errorLabel.setTextFill(Color.RED); //css styling of error label

	}
		
}
