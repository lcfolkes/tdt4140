package tdt4140.gr1823.app.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/** Class for the application as a whole, which connects the service provider interface and the login window. This is the only class which has to be run.
 * 
 * @author Gruppe 23
 *
 */
public class FxApp extends Application {
	
	//public static Stage window;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
    		primaryStage.setTitle("Helsedirektoratet - Activity monitor"); //title of window
    		primaryStage.setScene(createScene(loadMainPane()));
    	    primaryStage.setResizable(false);
    		primaryStage.show();
    }
    
    private Scene createScene(Pane pane) {
		Scene scene = new Scene(pane);
		scene.getStylesheets().add("gui.css");
		return scene;
	}
   
    /** Starts the application by loading the main pane and using the scene navigator to start app with login window
     * 
     * @return
     * @throws IOException
     */
	private Pane loadMainPane() throws IOException{
	    	FXMLLoader loader = new FXMLLoader();
	    	
	    	// Use scene navigator to connect the scenes
	    	Pane mainPane = (Pane) loader.load(getClass().getResourceAsStream(SceneNavigator.FXAPP));
	    FxAppController fxAppController = loader.getController();
	    	SceneNavigator.setMainController(fxAppController);
	    	//The app obviously starts with the login screen
	    	SceneNavigator.loadScene(SceneNavigator.LOGINSCREEN);
	    	return mainPane;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

