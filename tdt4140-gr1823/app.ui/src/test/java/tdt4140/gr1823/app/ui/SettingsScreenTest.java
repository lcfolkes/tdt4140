package tdt4140.gr1823.app.ui;

import java.util.concurrent.TimeoutException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.Assert;
import tdt4140.gr1823.app.ui.FxSettingsController;

public class SettingsScreenTest extends ApplicationTest {
	
	 private FxSettingsController testController; 
	 public ExpectedException exception; 
	 Button recActButton;
	 Button setUsernameButton;
	 Button setPasswordButton;
	 TextField recActInput;
	 TextField username;
	 PasswordField password;
	 Label errorLabel;
	 Text recommendedActivity;
	 Button logOutButton;
	 Text incorrectInput;
	 
	@BeforeClass
	public static void headless() {
	    	if (Boolean.valueOf(System.getProperty("gitlab-ci", "false"))) {
	    		GitlabCISupport.headless();
	  	}
	 }
	
 	
	@Override
  public void start(Stage stage) throws Exception {
		FXMLLoader loader= new FXMLLoader(getClass().getResource("FxSettingsScreen.fxml"));
		Parent root = loader.load();
	    this.testController = loader.getController();
	    Scene scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	    stage.toFront();
  }
	@Before
  public void setUp() {
      /* Just retrieving the tested widgets from the GUI. */ 
		recActButton = (Button) find("#recActButton");
		setUsernameButton = (Button) find("#setUsernameButton");
		setPasswordButton = (Button) find("#setPasswordButton");
		recActInput = (TextField) find("#recActInput");
		username = (TextField) find("#username");
		password = (PasswordField) find("#password");
		errorLabel = (Label) find("#errorLabel");
		recommendedActivity = (Text) find("#recommendedActivity");
		logOutButton = (Button) find("#logOutButton");
		incorrectInput = (Text) find("#incorrectInput");	
  }
	 
	@After
	public void tearDown() throws TimeoutException{
		/* Closing window, reopens at next test */
		FxToolkit.hideStage();
		release(new KeyCode[] {}); 
		release(new MouseButton[] {}); 
	}
	
	/* helper method to retrieve JavaFx gui components by id */ 
	public <T extends Node> T find(final String query) {
		return lookup(query).query();
	}
	
  @Test
	public void testFieldExist() {
		/* Testing if all fields exist*/
		Assert.assertTrue(find("#recActButton") instanceof Button);
		Assert.assertTrue(find("#setUsernameButton") instanceof Button);
		Assert.assertTrue(find("#setPasswordButton") instanceof Button);
		Assert.assertTrue(find("#recActInput") instanceof TextField);
		Assert.assertTrue(find("#username") instanceof TextField);
		Assert.assertTrue(find("#password") instanceof PasswordField);
		Assert.assertTrue(find("#errorLabel") instanceof Label);
		Assert.assertTrue(find("#recommendedActivity") instanceof Text);
		Assert.assertTrue(find("#logOutButton") instanceof Button);
		Assert.assertTrue(find("#incorrectInput") instanceof Text);
  }

  @Test
  public void testControllerConnection() throws InterruptedException {
	    	try {
	    		recActButton = (Button) find("#recActButton");
	    		setUsernameButton = (Button) find("#setUsernameButton");
	    		setPasswordButton = (Button) find("#setPasswordButton");
	    		recActInput = (TextField) find("#recActInput");
	    		username = (TextField) find("#username");
	    		password = (PasswordField) find("#password");
	    		errorLabel = (Label) find("#errorLabel");
	    		recommendedActivity = (Text) find("#recommendedActivity");
	    		logOutButton = (Button) find("#logOutButton");
	    		incorrectInput = (Text) find("#incorrectInput");	
	        	
	        /*Testing text field show correct String on input*/
	        	clickOn(recActInput).write("10000");
	    		Assert.assertEquals("10000", recActInput.getText());
	    	
	    		clickOn(username).write("helsedirektoratet");
	    		Assert.assertEquals("helsedirektoratet", username.getText());
	    		
	    		clickOn(password).write("gruppe23");
	    		Assert.assertEquals("gruppe23", password.getText());
	    	}
	    	catch(ClassCastException e) {
	    		System.out.println(e);
	    		Assert.assertFalse(true);
	    	}   	
  }

  @Test(expected = AssertionError.class)
  public void testErrorLabel(){
  		/*check initial state*/
		Assert.assertEquals(false, errorLabel.isVisible());
  		
		/*check invalid input*/
		clickOn(password).write("gruppe23");
		Assert.assertEquals(true, errorLabel.isVisible());
  }
}