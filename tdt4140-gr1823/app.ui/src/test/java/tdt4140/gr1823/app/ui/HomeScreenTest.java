package tdt4140.gr1823.app.ui;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import static org.testfx.api.FxAssert.verifyThat;

import java.sql.SQLException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tdt4140.gr1823.app.db.ActivityManager;
import tdt4140.gr1823.app.db.UserManager;
import javafx.scene.Node;

public class HomeScreenTest extends ApplicationTest{

	@BeforeClass
	public static void headless() {
    	if (Boolean.valueOf(System.getProperty("gitlab-ci", "false"))) {
    		GitlabCISupport.headless(); }
	}
	
	
	public void start(Stage stage) throws Exception {
		Parent mainNode = FXMLLoader.load(UserScreen.class.getResource("FxHomeScreen.fxml"));
		stage.setScene(new Scene(mainNode));
		stage.show();
		stage.toFront();
	}
	
	@After
	public void tearDown() throws Exception {
		FxToolkit.hideStage();
		release(new KeyCode[] {});
		//release(new MouseButton[] {});
	}
	


	@Before
    public void setUp() {
    }
	
	@Test
	public void testFieldExist() { //Checks if all fields are in the Stage
		Assert.assertTrue(find("#genderPieChart") instanceof PieChart);
		Assert.assertTrue(find("#agePieChart") instanceof PieChart);
		Assert.assertTrue(find("#genderBarChart") instanceof BarChart);
		Assert.assertTrue(find("#ageBarChart") instanceof BarChart);
		Assert.assertTrue(find("#lineChart") instanceof LineChart);
		Assert.assertTrue(find("#numUsers") instanceof Text);
		Assert.assertTrue(find("#ageBarChart") instanceof BarChart);
    }

	@Test
	public void setUpCorrect() {
		UserManager um = new UserManager();
		try {
			verifyThat("#numUsers", NodeMatchers.hasText(Integer.toString(um.getNumberOfUsers())));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public <T extends Node> T find(final String query) {
		return lookup(query).query();
	}

}
