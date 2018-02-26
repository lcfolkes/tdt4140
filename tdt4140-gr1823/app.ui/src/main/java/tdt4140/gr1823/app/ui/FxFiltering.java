package tdt4140.gr1823.app.ui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;


public class FxFiltering extends Application {
	
	Stage window;
	Stage scene;
	Button button; 
	
public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
    		window = primaryStage; 
    		window.setTitle("FilterFunc");
    		button = new Button("Click me!");
        Parent root = FXMLLoader.load(getClass().getResource("FxFiltering.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


	
}
