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
    		window.setTitle("FilterFunc");
    		Parent root = FXMLLoader.load(getClass().getResource("FxFiltering.fxml"));
    		
    		//Separator separator = new Separator();
    		//FlowPane root = new FlowPane(); 
    		/*Label label = new Label("Select gender:");
    		
    		ObservableList<String> genders 
            = FXCollections.observableArrayList("MALE", "FEMALE"); //get from Gender() enum/class
 
    		ChoiceBox<String> choiceBox = new ChoiceBox<String>(FXCollections.observableArrayList(genders));
    		*/
    		/*root.setPadding(new Insets(10));
    		 
            root.getChildren().addAll(label, choiceBox);
            root.setPadding(new Insets(10));
            root.setHgap(10);
    */
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


	
}
