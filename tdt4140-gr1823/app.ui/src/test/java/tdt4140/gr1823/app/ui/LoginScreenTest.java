package tdt4140.gr1823.app.ui;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

public class LoginScreenTest extends ApplicationTest {
	
	private TextField usernameField;
	private PasswordField passwordField;
	private Button loginButton;
	private Label errorLabel;
	private LoginScreenController testController;
	private FXMLLoader loader;
	
	@BeforeClass
	public static void headless() {
	    	if (Boolean.valueOf(System.getProperty("gitlab-ci", "false"))) {
	    		GitlabCISupport.headless();
	  	}
	 }
	
	@Override
	public void start(Stage stage) throws Exception {
		loader = new FXMLLoader(getClass().getResource("LoginScreen.fxml"));
		Parent root = loader.load();
		this.testController = loader.getController();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		stage.toFront();
	}

	@Before
	public void setUp() throws Exception {
		usernameField = (TextField) find("#usernameField");
		passwordField = (PasswordField) find("#passwordField");
		loginButton = (Button) find("#loginButton");
		errorLabel = (Label) find("#errorLabel");
	}
	
	@After
	public void tearDown() throws TimeoutException{
		FxToolkit.hideStage();
		release(new KeyCode[] {});
		release(new MouseButton[] {});
	}

	
	@Test
	public void testFieldExists() {
		assertTrue(find("#loginButton") instanceof Button);
		assertTrue(find("#passwordField") instanceof PasswordField);
		assertTrue(find("#usernameField") instanceof TextField);
		assertTrue(find("#errorLabel") instanceof Label);
	}
	
	@Test
	public void testLabelNotVisible() {
		//label should not be visible at default
		assertFalse(errorLabel.isVisible());
	}
	
	@Test
	public void testLabelVisible() throws InterruptedException {	
		//when typing wrong combo of username/password, label should be visible
		clickOn(usernameField).write("feilbrukernavn");
		clickOn(passwordField).write("feilPassord");
		clickOn(loginButton);
		TimeUnit.SECONDS.sleep(1);
		assertTrue(errorLabel.isVisible());	
	}

	//Hjelpemetode for å finne JavaFX elementer på ID
	public <T extends Node> T find(final String query) {
		return lookup(query).query();
	}
}
