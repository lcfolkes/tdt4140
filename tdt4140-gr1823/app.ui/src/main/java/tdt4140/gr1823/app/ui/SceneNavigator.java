package tdt4140.gr1823.app.ui;
//https://gist.github.com/jewelsea/6460130

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class SceneNavigator {
   
	public static final String FXAPP = "FxApp.fxml";
    public static final String MAINSCREEN    = "MainScreen.fxml";
    public static final String FILTER = "FxFiltering.fxml";
    public static final String MAINTAB1 = "MainTab1.fxml";
    public static final String MAINTAB2 = "MainTab2.fxml";
    
    private static FxAppController fxAppController;
    
    public static void setMainController(FxAppController fxAppController) {
        SceneNavigator.fxAppController = fxAppController;
    }
    
    public static void loadScene(String fxml) {
        try {
            fxAppController.setScene(
               (Node) FXMLLoader.load(
                    SceneNavigator.class.getResource(
                        fxml
                    )
                )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
	