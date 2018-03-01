package tdt4140.gr1823.app.ui;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.FlowPane;


public class FxFiltering extends Application {
	Stage window;
	Stage scene;
	
public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
    		
    		window = primaryStage; 
    		window.setTitle("FilterFunc"); //title of window
    		Parent root = FXMLLoader.load(getClass().getResource("FxFiltering.fxml")); //decides fxml file to run
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	
}
