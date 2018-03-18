package tdt4140.gr1823.app.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FxApp extends Application {
	
	public static Stage window;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
    		
    		window = primaryStage;
    		
    		primaryStage.setTitle("National step database"); //title of window
    		Pane MainScreenPane = (Pane) FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
    		Scene MainScreenScene = new Scene(MainScreenPane);
    		primaryStage.setScene(MainScreenScene);
    		primaryStage.getScene().getStylesheets().add("gui.css");
    		primaryStage.show();
    		
    		
    		
    		//Parent root = FXMLLoader.load(getClass().getResource("FxFiltering.fxml")); //decides fxml file to run
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

