package tdt4140.gr1823.app.ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import tdt4140.gr1823.app.db.DBManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginScreenController implements Initializable {
	
	@FXML
	TextField getUsername;
	
	@FXML
	PasswordField getPassword;
	
	@FXML
	Button loginButton;
	
	@FXML
	TextField errorLabel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		errorLabel.setVisible(false);
		
		String password = getPassword.getText();
		String username = getUsername.getText();
		
		
		
//		//errorLabel.setVisible(false);
//		//errorLabel.setTextFill(Color.RED); //css styling of error label
//		
//		getRecDailyActivity.setText(serviceProvider.getRecommendedDailyActivity() + " steps");
//		
//		try {
//			getNumOfUsers.setText((Integer.toString(userManager.getNumberOfUsers()) + " users"));
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	
//		try {
//			getNationalAverage.setText(Double.toString(activityManager.getNationalAverage()) + " steps");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		setValueButton.setOnAction((e) -> {
//			//Old version
//			//serviceProvider.setRecommendedDailyActivity(Integer.parseInt(setValueField.getText()));
//			
//			String inputText = getInput(setValueField);
//			serviceProvider.setRecommendedDailyActivity(Integer.parseInt(inputText));
//			getRecDailyActivity.setText(serviceProvider.getRecommendedDailyActivity() + " steps");
//		
//		});
//		
//		//Create method to connect to FxFiltering.java, in order to run the applications when pushing the "FILTER STEPS" button
//		
//		filterStepsButton.setOnAction((e) -> {
//			SceneNavigator.loadScene(SceneNavigator.FILTER);
//			}
	}
}
