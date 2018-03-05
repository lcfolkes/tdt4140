package tdt4140.gr1823.app.ui;

import java.util.concurrent.TimeoutException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.ComboBoxMatchers;
import org.testfx.util.WaitForAsyncUtils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.Assert;
//import junit.framework.Assert;
import tdt4140.gr1823.app.ui.FxFilteringController;


public class FxFilteringTest extends ApplicationTest {
	
	 private FxFilteringController testController; 
	 ChoiceBox<String> choiceBox;
	 TextField text1;
	 TextField text2;
	 Button button;
	 //Parent mainNode;
	
	
	@BeforeClass
	public static void headless() {
	    	if (Boolean.valueOf(System.getProperty("gitlab-ci", "false"))) {
	    		GitlabCISupport.headless();
	  	}
	 }
	
	@Before
	public void setUpClass() throws Exception{
		ApplicationTest.launch(FxFiltering.class);
	} 
	
   
	
	@Override
    public void start(Stage stage) throws Exception {
		FXMLLoader loader= new FXMLLoader(getClass().getResource("FxFiltering.fxml"));
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
        //testController.textInput1.setText("33");
        //testController.textInput2.setText("100");	
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
		Assert.assertTrue(find("#cbGender") instanceof ChoiceBox);
		Assert.assertTrue(find("#submitButton") instanceof Button);
    }
	
    @Test
    public void testControllerConnection() {
    	
	    	try {
	    		choiceBox = (ChoiceBox) find("#cbGender");
	    		text1 = (TextField) find("#textInput1");
	    		text2 = (TextField) find("#textInput2");
	    		button = (Button) find("#submitButton");
	    		
	    		
	    		/* Testing selected item choicebox*/
	    		clickOn(choiceBox);
	        	type(KeyCode.DOWN);
	        	type(KeyCode.DOWN);
	        	type(KeyCode.ENTER);
	        	Assert.assertEquals("MALE", choiceBox.getValue());
	        	
	        /*Testing text field show correct String on input*/
	        	clickOn(text1).write("33");
	    		Assert.assertEquals("33", text1.getText());
	    	
	    		clickOn(text2).write("100");
	    		Assert.assertEquals("100", text2.getText());
	    		
	    		
	    	}
	    	catch(ClassCastException e) {
	    		System.out.println(e);
	    		//System.out.println(text1.getText());
	    		Assert.assertFalse(true);
	    	}
	    	
    }

    
   /* @Test
    public void testClickOnButtonSubmit() {
        String command = FxFilteringController.getChoice(choiceBox, text1, text2);
        clickOn(button);
        WaitForAsyncUtils.waitForFxEvents();

        assertEquals(darker, picker.getValue());
    } */
    
   

    
    
   /* @Test
    public void ensureNotEmptyChoiceBoxHasItems() {
    	verifyThat(ComboBoxMatchers.containsExactlyItems("","MALE", "FEMALE"),"#cbGender");
    }    
   */
    
   /* @Test
    public void testChoiceBox() {
    	clickOn("#cbGender");
    	type(KeyCode.DOWN);
    	type(KeyCode.ENTER);
    }*/
    
    //@Test
    //public void testTextField() {
    	//	TextField textField1 = (TextField) lookup("#textInput1");
    //		String text = "hei";
    //		clickOn(textField1).write(text);
    	

    //}
}
