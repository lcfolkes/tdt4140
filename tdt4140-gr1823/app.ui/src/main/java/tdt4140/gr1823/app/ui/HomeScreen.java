package tdt4140.gr1823.app.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class HomeScreen extends Application {

	public Stage window;
	Scene mainScene;
	
    @Override
    public void start(Stage primaryStage) throws Exception {
    	window = primaryStage;
    	
    	Parent root = FXMLLoader.load(getClass().getResource("FxHomeScreen.fxml"));
        Scene mainScene = new Scene(root);
        window.setScene(mainScene);
        window.show();
    }
    	

    public static void main(String[] args) {
        launch(args);
    }
    
    public Scene getScene () {
    		return mainScene;
    }
}