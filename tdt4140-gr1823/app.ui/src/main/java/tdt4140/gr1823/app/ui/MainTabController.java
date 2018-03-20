package tdt4140.gr1823.app.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane; 

public class MainTabController implements Initializable{
	
	@FXML
	private TabPane tabPane;
	
	@FXML
	private Tab serviceProviderTab;
	
	@FXML 
	private MainScreenController serviceProviderTabController;
	
	@FXML
	private Tab userTab;
	
	@FXML
	private FxFilteringController userTabController;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		System.out.println(serviceProviderTabController);
	}
}
