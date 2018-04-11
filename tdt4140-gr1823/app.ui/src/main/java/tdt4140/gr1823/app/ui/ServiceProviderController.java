package tdt4140.gr1823.app.ui; 
 
import java.net.URL; 
import java.util.ResourceBundle; 
 
import javafx.fxml.FXML; 
import javafx.fxml.Initializable; 
import javafx.scene.control.Tab; 
import javafx.scene.control.TabPane;  
 
public class ServiceProviderController implements Initializable{ 
   
  @FXML 
  private TabPane tabPane; 
   
  @FXML 
  private Tab homeScreenTab; 
   
  @FXML  
  private HomeScreenController homeScreenController; 
   
  @FXML 
  private Tab analyzeTab; 
   
  @FXML 
  private FxAnalyzeController fxAnalyzeController; 
 
  @Override 
  public void initialize(URL location, ResourceBundle resources) { 
    // TODO Auto-generated method stub 
    //System.out.println("HELLO"); 
  } 
} 