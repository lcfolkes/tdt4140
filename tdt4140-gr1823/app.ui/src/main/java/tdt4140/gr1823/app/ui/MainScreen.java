package tdt4140.gr1823.app.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Class only for testing purposes
public class MainScreen extends Application {

	public Stage window;
	Scene filterScene;
	
    @Override
    public void start(Stage primaryStage) throws Exception {
    	window = primaryStage;
    	
    	Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene mainScene = new Scene(root);
        window.setScene(mainScene);
        filterScene = new Scene(FXMLLoader.load(getClass().getResource("FxFiltering.fxml")));

        
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
