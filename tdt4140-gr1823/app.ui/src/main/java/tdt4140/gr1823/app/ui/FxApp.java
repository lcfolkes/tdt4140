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
    		
    		//window = primaryStage;
    		primaryStage.setTitle("National step database"); //title of window
    		
    		primaryStage.setScene(createScene(loadMainPane()));
    		//Pane MainScreenPane = (Pane) FXMLLoader.load(getClass().getResource("FxApp.fxml"));
    		//Scene MainScreenScene = new Scene(MainScreenPane);
    		//primaryStage.setScene(MainScreenScene);
    		//primaryStage.getScene().getStylesheets().add("gui.css");
    		//createscene og loadmainpane gjør alt dette
    		
    		primaryStage.show();
    		
    		
    		
    		//Parent root = FXMLLoader.load(getClass().getResource("FxFiltering.fxml")); //decides fxml file to run
        
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
	    	SceneNavigator.loadScene(SceneNavigator.MAINSCREEN);
	    	return mainPane;
    	
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

