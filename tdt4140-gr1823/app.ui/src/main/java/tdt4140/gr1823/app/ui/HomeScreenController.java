package tdt4140.gr1823.app.ui;

import java.awt.Label;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import tdt4140.gr1823.app.db.ActivityManager;
import tdt4140.gr1823.app.db.UserManager;

public class HomeScreenController implements Initializable{
	
		
	@FXML
	protected Text title;
		
	@FXML
	protected PieChart genderPieChart;
	
	@FXML
	protected PieChart agePieChart;
	
	@FXML
	protected BarChart<String, Integer> genderBarChart;
	
	@FXML
	protected BarChart<String,Integer> ageBarChart;
	
	@FXML
	protected LineChart<Integer,Integer> lineChart;
	
	@FXML
	protected Text numUsers;
	
	protected UserManager um;
	protected ActivityManager am;

	@Override
	public void initialize(URL location, ResourceBundle resources) {	
	
	//Number of Users
	UserManager um = new UserManager();
	int num = 0;
	try {
		num = um.getNumberOfUsers("Person");
	} catch (SQLException e) {
		e.printStackTrace();
	}
	numUsers.setText(Integer.toString(num));
	
	//Pie charts
		
	Double malePercentage = (double) 0;
	try {
		malePercentage = (double) ((um.getNumberOfUsers("Person","MALE")*100)/(um.getNumberOfUsers("Person")));
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	ObservableList<PieChart.Data> genderPieChartData =
        FXCollections.observableArrayList(
        new PieChart.Data("MALE", malePercentage),
        new PieChart.Data("FEMALE", 100-malePercentage));
		genderPieChart.setData(genderPieChartData); 
		
	Double firstPercentage = (double) 0;
	Double secondPercentage = (double) 0;
	Double thirdPercentage = (double) 0;
	Double fourthPercentage = (double) 0;
	try {
		firstPercentage = (double) ((um.getNumberOfUsers("Person","0","20")*100)/(um.getNumberOfUsers("Person")));
		secondPercentage = (double) ((um.getNumberOfUsers("Person","21","40")*100)/(um.getNumberOfUsers("Person")));
		thirdPercentage = (double) ((um.getNumberOfUsers("Person","41","60")*100)/(um.getNumberOfUsers("Person")));
		fourthPercentage = (double) ((um.getNumberOfUsers("Person","61","120")*100)/(um.getNumberOfUsers("Person")));
	} catch (SQLException e) {
		e.printStackTrace();
	}
	  
	ObservableList<PieChart.Data> agePieChartData =
        FXCollections.observableArrayList(
        new PieChart.Data("0-20", firstPercentage),
        new PieChart.Data("21-40", secondPercentage),
		new PieChart.Data("41-60", thirdPercentage),
		new PieChart.Data("60-120", fourthPercentage));
	   	agePieChart.setData(agePieChartData); 
	 
	
  //Gender Bar chart
	   	
	ActivityManager am = new ActivityManager();
	int maleSteps = 0;
	int femaleSteps = 0;
	try {
		maleSteps = (int) am.filter("", "", "MALE", "DailySteps", "Person");
		femaleSteps = (int) am.filter("", "", "FEMALE", "DailySteps", "Person");
	} catch (NumberFormatException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	   	
	   	
   XYChart.Series<String, Integer> genderData = new XYChart.Series<>();
	   genderData.setName("GENDER");  
	   genderData.getData().add(new XYChart.Data<>("MALE", maleSteps));
	   genderData.getData().add(new XYChart.Data<>("FEMALE", femaleSteps));
	   genderBarChart.getData().add(genderData);
  
   //Age Bar chart
	   
	int firstSteps = 0;
	int secondSteps = 0;
	int thirdSteps = 0;
	int fourthSteps = 0;
	try {
		firstSteps = (int) am.filter("0", "20", "", "DailySteps", "Person");
		secondSteps = (int) am.filter("21", "40", "", "DailySteps", "Person");
		thirdSteps = (int) am.filter("41", "60", "", "DailySteps", "Person");
		fourthSteps = (int) am.filter("61", "120", "", "DailySteps", "Person");
	} catch (NumberFormatException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}   
	      
   XYChart.Series<String, Integer> ageData = new XYChart.Series<>();
	   ageData.setName("AGE");       
	   ageData.getData().add(new XYChart.Data<>("0-20", firstSteps));
	   ageData.getData().add(new XYChart.Data<>("21-40", secondSteps));
	   ageData.getData().add(new XYChart.Data<>("41-60", thirdSteps));
	   ageData.getData().add(new XYChart.Data<>("61-120", fourthSteps));
	   ageBarChart.getData().add(ageData);
   
	 
   //Line chart - last 12 months

	   	int today = 0, one = 0,  two = 0, three = 0, four = 0, five = 0, six = 0, seven = 0, eight = 0, nine = 0, ten = 0, eleven = 0, twelve = 0;
	try {
		today = (int) am.getNationalAverageByMonth(0);
		one = (int) am.getNationalAverageByMonth(1);
		two = (int) am.getNationalAverageByMonth(2);
		three = (int) am.getNationalAverageByMonth(3);
		four = (int) am.getNationalAverageByMonth(4);
		five = (int) am.getNationalAverageByMonth(5);
		six = (int) am.getNationalAverageByMonth(6);
		seven = (int) am.getNationalAverageByMonth(7);
		eight = (int) am.getNationalAverageByMonth(8);
		nine = (int) am.getNationalAverageByMonth(9);
		ten = (int) am.getNationalAverageByMonth(10);
		eleven = (int) am.getNationalAverageByMonth(11);
		twelve = (int) am.getNationalAverageByMonth(12);
		 
   
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	XYChart.Series<Integer, Integer> averageData = new XYChart.Series<>();
	   averageData.setName("Historical average");       
	   averageData.getData().add(new XYChart.Data<>(0, twelve));
	   averageData.getData().add(new XYChart.Data<>(1, eleven));
	   averageData.getData().add(new XYChart.Data<>(2, ten));
	   averageData.getData().add(new XYChart.Data<>(3, nine));
	   averageData.getData().add(new XYChart.Data<>(4, eight));
	   averageData.getData().add(new XYChart.Data<>(5, seven));
	   averageData.getData().add(new XYChart.Data<>(6, six));
	   averageData.getData().add(new XYChart.Data<>(7, five));
	   averageData.getData().add(new XYChart.Data<>(8, four));
	   averageData.getData().add(new XYChart.Data<>(9, three));
	   averageData.getData().add(new XYChart.Data<>(10, two));
	   averageData.getData().add(new XYChart.Data<>(11, one));
	   averageData.getData().add(new XYChart.Data<>(12, today));
	   lineChart.getData().add(averageData);
	   
	   lineChart.getXAxis().setLabel("Months");
	   lineChart.getYAxis().setLabel("Number of steps");
	   
	}//end initialize
	
}











