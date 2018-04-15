package tdt4140.gr1823.app.ui;

//import static org.junit.Assert.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.ComboBoxMatchers;
import org.testfx.util.WaitForAsyncUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.Assert;
import tdt4140.gr1823.app.ui.FxAnalyzeController;


public class FxAnalyzeTest extends ApplicationTest {
	
	 private FxAnalyzeController testController; 
	 public ExpectedException exception; 
	 ComboBox<String> comboBox;
	 TextField text1;
	 TextField text2;
	 Button button;
	 Label errorLabel;
	 BarChart barChart;
	 
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
		comboBox = (ComboBox) find("#cbGender");
		text1 = (TextField) find("#textInput1");
		text2 = (TextField) find("#textInput2");
		button = (Button) find("#submitButton");
		errorLabel = (Label) find("#errorLabel");
		barChart = (BarChart) find("#barChart");
		
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

		Assert.assertTrue(find("#textInput1") instanceof TextField);
		Assert.assertTrue(find("#textInput2") instanceof TextField);
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

    /*@Test
    public void containsItems() {
 	   //in order
 	   assertThat(comboBox, ComboBoxMatchers.containsItems("","MALE", "FEMALE"));
 	   //not in order
 	   assertThat(comboBox, ComboBoxMatchers.containsItems("MALE","FEMALE", ""));
 	   //partial
 	   assertThat(comboBox, ComboBoxMatchers.containsItems("", "FEMALE"));
    }
    */ 
    
    @Test
    public void containsExactlyItems() {
 	   //in order
 	   assertThat(comboBox, ComboBoxMatchers.containsExactlyItems("","MALE", "FEMALE"));
 	   //not in order
 	   assertThat(comboBox, ComboBoxMatchers.containsExactlyItems("MALE","FEMALE", ""));
    }
    
    @Test
    public void testControllerConnection() throws InterruptedException {
	    	try {
	    		comboBox = (ComboBox) find("#cbGender");
	    		text1 = (TextField) find("#textInput1");
	    		text2 = (TextField) find("#textInput2");
	    		button = (Button) find("#submitButton");
	    		errorLabel = (Label) find("#errorLabel");
	    		
	    		
	    		/* Testing selected item choicebox*/
	    		clickOn(comboBox);
	        	type(KeyCode.DOWN);
	        	type(KeyCode.DOWN);
	        	type(KeyCode.ENTER);
	        	Assert.assertEquals("MALE", comboBox.getValue());
	        	
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
