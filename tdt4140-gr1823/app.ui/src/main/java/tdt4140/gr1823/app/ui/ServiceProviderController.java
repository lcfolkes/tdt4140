package tdt4140.gr1823.app.ui; 
 
import java.net.URL; 
import java.util.ResourceBundle; 
 
import javafx.fxml.FXML; 
import javafx.fxml.Initializable; 
import javafx.scene.control.Tab; 
import javafx.scene.control.TabPane;  
 
public class ServiceProviderController implements Initializable{ 
   
	//FXML objects with fx:id from FxAnalyzeScreen 
	  @FXML 
	  private TabPane tabPane; //This is the field with the three different tabs to navigate the service provider interface
	  @FXML 
	  private Tab homeScreenTab; // The tab going to the home screen (with standard data)
	  @FXML 
	  private Tab analyzeTab;  //The tab going to the interactive analyze screen
	  @FXML 
	  private Tab settingsTab; // The tab going to the settings screen
	  @FXML  
	  private FxSettingsController fxSettingsController; //Controller relating to the settings screen
	  @FXML 
	  private FxAnalyzeController fxAnalyzeController; //Controller relating to the analyzing screen
	  @FXML  
	  private HomeScreenController homeScreenController; //Controller relating to the analyze screen
	  
	  @Override 
	  public void initialize(URL location, ResourceBundle resources) { 
	    //Not really in use. The FXML file contains most of the logic
	  } 
} 