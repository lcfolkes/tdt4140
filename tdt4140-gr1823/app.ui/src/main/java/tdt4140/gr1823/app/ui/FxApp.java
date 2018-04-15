package tdt4140.gr1823.app.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class FxApp extends Application {
	
	//public static Stage window;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
    		primaryStage.setTitle("National step database"); //title of window
    		primaryStage.setScene(createScene(loadMainPane()));
    	    primaryStage.setResizable(false);
    		primaryStage.show();
    }
    
    private Scene createScene(Pane pane) {
		Scene scene = new Scene(pane);
		scene.getStylesheets().add("gui.css");
		return scene;
	}

	private Pane loadMainPane() throws IOException{
	    	FXMLLoader loader = new FXMLLoader();
	    	Pane mainPane = (Pane) loader.load(getClass().getResourceAsStream(SceneNavigator.FXAPP));
	    FxAppController fxAppController = loader.getController();
	    	System.out.println("appcontroller= " + fxAppController);
	    	SceneNavigator.setMainController(fxAppController);
	    	SceneNavigator.loadScene(SceneNavigator.LOGINSCREEN);
	    	return mainPane;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

