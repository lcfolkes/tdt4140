package tdt4140.gr1823.app.ui;


import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

/** This class controls and connects the login window and the Service Provider interface with its respective tabs.
 * 
 * @author Gruppe23
 *
 */
public class FxAppController{
	
	@FXML
	private StackPane sceneHolder;
	public void setScene(Node node) {
		sceneHolder.getChildren().setAll(node);
	}
}
