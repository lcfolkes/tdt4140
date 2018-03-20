package tdt4140.gr1823.app.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class FxAppController{
	
	@FXML
	private StackPane sceneHolder;
	
	
	public void setScene(Node node) {
		sceneHolder.getChildren().setAll(node);
	}

}
