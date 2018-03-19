package tdt4140.gr1823.app.ui;

import org.junit.Assert;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.concurrent.TimeoutException;
import org.junit.rules.ExpectedException;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.TextMatchers;
import org.testfx.util.WaitForAsyncUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.api.FxToolkit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import tdt4140.gr1823.app.ui.MainScreen;


public class MainScreenTest extends ApplicationTest {
 
	private MainScreenController testController;
	
	Text recDailyActivityText;
	Text getNumOfUsersText;
	Text getNationalAverageText;
	Button setValueButtonButton;
	TextField setValueFieldField;
	//Button filterStepsButtonButton;
	
	@BeforeClass
	public static void headless() {
    	if (Boolean.valueOf(System.getProperty("gitlab-ci", "false"))) {
    		GitlabCISupport.headless(); }
	}
	
	@Before 
	public void setUpClass() throws Exception{
		ApplicationTest.launch(MainScreen.class);
	}
	
	
	@Override
	 public void start(Stage stage) throws Exception {
		FXMLLoader loader= new FXMLLoader(getClass().getResource("MainScreen.fxml"));
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
		recDailyActivityText = (Text) find("#getRecDailyActivity");
		getNumOfUsersText = (Text) find("#getNumOfUsers");
		getNationalAverageText = (Text) find("#getNationalAverage");
		setValueButtonButton = (Button) find("#setValueButton");
		setValueFieldField = (TextField) find("#setValueField");
		//filterStepsButtonButton = (Button) find("#filterStepsButton");
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
		//Testing if all fields exist
		Assert.assertTrue(find("#getRecDailyActivity") instanceof Text);
		Assert.assertTrue(find("#getNumOfUsers") instanceof Text);
		Assert.assertTrue(find("#getNationalAverage") instanceof Text);
		Assert.assertTrue(find("#setValueButton") instanceof Button);
		Assert.assertTrue(find("#setValueField") instanceof TextField);
		//Assert.assertTrue(find("#filterStepsButton") instanceof Button);	
	}
}
	
	

