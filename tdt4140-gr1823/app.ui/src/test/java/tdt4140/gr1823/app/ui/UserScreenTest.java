package tdt4140.gr1823.app.ui;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.api.FxToolkit;
import org.junit.After;
import org.junit.Before;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.testfx.api.FxAssert.verifyThat;


public class UserScreenTest extends ApplicationTest {
	private final UserScreen userScreen = new UserScreen();
	//private static GuiTest controller;
	
//	private UserScreenController testController;
//	
//	private Text getDailyActivity, getRecActivity, getNationalAverage;
//	private TextField setUsername;
//	private Button recordUsernameButton;
//	private Text acceptDataSharingText;
//	private CheckBox yesBox;
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent mainNode = FXMLLoader.load(UserScreen.class.getResource("UserScreen.fxml"));
		stage.setScene(new Scene(mainNode));
		stage.show();
		stage.toFront();
	}
	
	@Before
	public void setUp() throws Exception {
		
	}
	
	@After
	public void tearDown() throws Exception {
		FxToolkit.hideStage();
		release(new KeyCode[] {});
		release(new MouseButton[] {});
	}
	
//	@Test
//	public void setUpCorrect() {
//		verifyThat("#getDailyActivity", NodeMatchers.hasText("The number of"))
//	}
	
//	@Test
//	public void testLogin() {
//		clickOn("#setUsername");
//		write("hilde@gmail.com");
//		clickOn("#recordUsernameButton");
//		verifyThat("#getDailyActivity", NodeMatchers.hasText("You have walked 15000.0 steps today"));
//	}
	


}
