package tdt4140.gr1823.app.ui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class SceneNavigator {
    /**
     * Convenience constants for fxml layouts managed by the navigator.
     */
    public static final String MAINSCREEN    = "MainScreen.fxml";
    public static final String FILTER = "FxFiltering.fxml";


    /**
     * Loads the vista specified by the fxml file into the
     * vistaHolder pane of the main application layout.
     *
     * Previously loaded vista for the same fxml file are not cached.
     * The fxml is loaded anew and a new vista node hierarchy generated
     * every time this method is invoked.
     *
     * A more sophisticated load function could potentially add some
     * enhancements or optimizations, for example:
     *   cache FXMLLoaders
     *   cache loaded vista nodes, so they can be recalled or reused
     *   allow a user to specify vista node reuse or new creation
     *   allow back and forward history like a browser
     *
     * @param fxml the fxml file to be loaded.
     */
    public static void loadScene(String fxml) {
        Pane myPane = null;
		try {
			myPane = (Pane) FXMLLoader.load(SceneNavigator.class.getClass().getResource(fxml));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene myScene = new Scene(myPane);
		FxApp.window.setScene(myScene);
        FxApp.window.getScene().getStylesheets().add("gui.css");
		FxApp.window.show();
		}
    }
	
