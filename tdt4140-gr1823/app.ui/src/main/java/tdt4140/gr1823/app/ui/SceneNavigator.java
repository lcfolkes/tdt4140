package tdt4140.gr1823.app.ui;
// inspiration: https://gist.github.com/jewelsea/6460130

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

/** This class is used to navigate between different scenes. The different scenes are saved as static final fields. 
 * 
 * @author Gruppe 23
 *
 */
public class SceneNavigator {
	
	//The scenes that we want to switch between are listed here
    public static final String SERVICEPROVIDER = "ServiceProvider.fxml";
    public static final String LOGINSCREEN = "LoginScreen.fxml";
    public static final String FXAPP = "FxApp.fxml";
    //Need an FxAppController object
    private static FxAppController fxAppController;
    
    /** This method sets the parameter given as the FxAppController as the FxAppController of this scene navigator.
     * 
     * @param fxAppController
     */
    public static void setMainController(FxAppController fxAppController) {
        SceneNavigator.fxAppController = fxAppController;
    }
    
    /** This methods loads the scene given by a fxml file as a string.
     * 
     * @param fxml
     */
    public static void loadScene(String fxml) {
        try {
            fxAppController.setScene((Node) FXMLLoader.load(SceneNavigator.class.getResource(fxml)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
	
