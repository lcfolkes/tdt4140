package tdt4140.gr1823.app.ui;

import static org.hamcrest.MatcherAssert.assertThat;
import java.util.concurrent.TimeoutException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.ComboBoxMatchers;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import tdt4140.gr1823.app.ui.FxAnalyzeController;

import org.junit.Assert;

/** Test class for the analyze screen
 * 
 * @author Gruppe 23
 *
 */
public class FxAnalyzeTest extends ApplicationTest {
	
	 private FxAnalyzeController testController; 
	 
	 //FXML objects with fx:id from FxAnalyzeScreen 
	 public ExpectedException exception; 
	 public ComboBox<String> comboBox;
	 public TextField text1;
	 public TextField text2;
	 public Button button;
	 public Label errorLabel;
	
	/**
	 * Method needed for the tests to run by GitLab
	 */
	@BeforeClass
	public static void headless() {
	    	if (Boolean.valueOf(System.getProperty("gitlab-ci", "false"))) {
	    		GitlabCISupport.headless();
	  	}
	 }
	
   	
	@Override
    public void start(Stage stage) throws Exception {
		FXMLLoader loader= new FXMLLoader(getClass().getResource("FxAnalyzeScreen.fxml"));
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
		comboBox = ((ComboBox) find ("#cbGender"));
		text1 = (TextField) find("#ageFromField");
		text2 = (TextField) find("#ageToField");
		button = (Button) find("#submitButton");
		errorLabel = (Label) find("#errorLabel");
		
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
		Assert.assertTrue(find("#ageFromField") instanceof TextField);
		Assert.assertTrue(find("#ageToField") instanceof TextField);
		Assert.assertTrue(find("#cbGender") instanceof ComboBox);
		Assert.assertTrue(find("#submitButton") instanceof Button);
		Assert.assertTrue(find("#errorLabel") instanceof Label);
		Assert.assertTrue(find("#barChart") instanceof BarChart);
    }
	
    @Test
    public void hasItems() {
    		/*correct number of items*/
    		assertThat(comboBox, ComboBoxMatchers.hasItems(3));
    } 
    
    @Test
    public void containsExactlyItems() {
 	   //in order
 	   assertThat(comboBox, ComboBoxMatchers.containsExactlyItems("NOT SPECIFIED","MALE", "FEMALE"));
 	   //not in order
 	   assertThat(comboBox, ComboBoxMatchers.containsExactlyItems("MALE","FEMALE", "NOT SPECIFIED"));
    }
    
    @Test
    public void testControllerConnection() throws InterruptedException {
	    	try {
	    		text1 = (TextField) find("#ageFromField");
	    		text2 = (TextField) find("#ageToField");
	    		button = (Button) find("#submitButton");
	    		errorLabel = (Label) find("#errorLabel");
	    		
	    		
	    		/* Testing selected item choicebox*/
	    		clickOn(comboBox);
	        	type(KeyCode.DOWN);
	        	type(KeyCode.DOWN);
	        	type(KeyCode.ENTER);
	        	Assert.assertEquals("FEMALE", comboBox.getValue());
	        	
	        /*Testing text field show correct String on input*/
	        	clickOn(text1).write("33");
	    		Assert.assertEquals("33", text1.getText());
	    	
	    		clickOn(text2).write("100");
	    		Assert.assertEquals("100", text2.getText());
	    		
	        clickOn(button);   
	    	}
	    	catch(ClassCastException e) {
	    		System.out.println(e);
	    		Assert.assertFalse(true);
	    	}   	
    }

    @Test(expected = AssertionError.class)
    public void testErrorLabel(){
    		/*valid values*/
    		clickOn(text1).write("3");
    		clickOn(text2).write("65");
		clickOn(button);
		Assert.assertEquals(false, errorLabel.isVisible());
		
		clickOn(text2).write("65");
		clickOn(button);
		Assert.assertEquals(false, errorLabel.isVisible());
		
		/*valid values, invalid order*/
		clickOn(text1).write("65");
		clickOn(text2).write("3");
		clickOn(button);
		Assert.assertEquals(true, errorLabel.isVisible());
		
		/*textfield1 invalid values*/
    		clickOn(text1).write("-1");
    		clickOn(button);
    		Assert.assertEquals(true, errorLabel.isVisible());
    		clickOn(text1).write("123");
    		clickOn(button);
    		Assert.assertEquals(true, errorLabel.isVisible());
    		
    		/*textfield2 invalid values*/
    		Assert.assertEquals(true, errorLabel.isVisible());
    		clickOn(text2).write("-5");
    		clickOn(button);
    		Assert.assertEquals(true, errorLabel.isVisible());
    		clickOn(text2).write("153");
    		clickOn(button);
    		Assert.assertEquals(true, errorLabel.isVisible());
    }
}
