package tdt4140.gr1823.app.ui;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.api.FxToolkit;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tdt4140.gr1823.app.HttpCommunication.HttpCommunication;
import tdt4140.gr1823.app.db.ActivityManager;
import tdt4140.gr1823.app.db.SPManager;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.testfx.api.FxAssert.verifyThat;

import java.io.IOException;
import java.sql.SQLException;


public class UserScreenTest extends ApplicationTest {
	
	@BeforeClass
	public static void headless() {
    	if (Boolean.valueOf(System.getProperty("gitlab-ci", "false"))) {
    		GitlabCISupport.headless(); }
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent mainNode = FXMLLoader.load(UserScreen.class.getResource("UserScreen.fxml"));
		stage.setScene(new Scene(mainNode));
		stage.show();
		stage.toFront();
	}
	
	@After
	public void tearDown() throws Exception {
		FxToolkit.hideStage();
		release(new KeyCode[] {});
		release(new MouseButton[] {});
	}
	
	@Before
    public void setUp() {
    }
	
	
	@Test
	public void testFieldExist() {
		Assert.assertTrue(find("#setUsername") instanceof TextField);
		Assert.assertTrue(find("#getDailyActivity") instanceof Text);
		Assert.assertTrue(find("#getRecActivity") instanceof Text);
		Assert.assertTrue(find("#getNationalAverage") instanceof Text);
		Assert.assertTrue(find("#recordUsernameButton") instanceof Button);
    }
	
//	@Test
//	public void setUpCorrect() {
//		SPManager serviceProvider = new SPManager();
//		ActivityManager activityManager = new ActivityManager();
//		verifyThat("#getDailyActivity", NodeMatchers.hasText("The number of steps walked today will pop up here when you log in."));
//		verifyThat("#getRecActivity", NodeMatchers.hasText("The recommended activity level is " + serviceProvider.getRecommendedDailyActivity() + " steps"));
//		try {
//			verifyThat("#getNationalAverage", NodeMatchers.hasText("Today the average number of steps among users is " + activityManager.getNationalAverage() + " steps"));
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}	
//	}
	
	@Test
	public void testLogin() {
		clickOn("#setUsername");
		write("hilde@gmail.com");
		clickOn("#recordUsernameButton");
//		verifyThat("#getDailyActivity", NodeMatchers.hasText("You have walked 15000.0 steps today"));
//		Denne kodelinjen fungerer bare når hilde har 15000 skritt loggført på i dag..så kommenteres ut
	}
	
	@Test
	public void testWrongCredentials() {
		clickOn("#setUsername");
		write("testing wrong input");
		clickOn("#recordUsernameButton");
		verifyThat("#getDailyActivity", NodeMatchers.hasText("You have no recorded data for today. Are you sure you entered the correct ID?"));
	}
	
	@Test
	public void testFieldExistSharing() {
		Assert.assertTrue(find("#yesBox") instanceof CheckBox);
		Assert.assertTrue(find("#noBox") instanceof CheckBox);
		Assert.assertTrue(find("#acceptDataSharingText") instanceof Text);
	}
	
	
//	@Test
//	public void testGetValueOfSharing() throws IOException {
//		HttpCommunication http = new HttpCommunication();
//		if (http.getShareValue("hilde@gmail.com")) {
//			clickOn("#setUsername");
//			write("hilde@gmail.com");
//			clickOn("#recordUsernameButton");
//			clickOn("yesBox");
//			Assert.assertFalse(http.getShareValue("hilde@gmail.com"));
//			clickOn("noBox");
//			Assert.assertTrue(http.getShareValue("hilde@gmail.com"));	
//		}
//	}
	
	
	public <T extends Node> T find(final String query) {
		return lookup(query).query();
	}

 }
