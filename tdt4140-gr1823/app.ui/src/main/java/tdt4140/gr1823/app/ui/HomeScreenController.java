package tdt4140.gr1823.app.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Text;

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
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		
	ObservableList<PieChart.Data> genderPieChartData =
        FXCollections.observableArrayList(
        new PieChart.Data("MALE", 40),
        new PieChart.Data("FEMALE", 60));
		genderPieChart.setData(genderPieChartData); 
	  
	ObservableList<PieChart.Data> agePieChartData =
        FXCollections.observableArrayList(
        new PieChart.Data("0-20", 40),
        new PieChart.Data("21-40", 20),
		new PieChart.Data("41-60", 20),
		new PieChart.Data("60-120", 0));
	   	agePieChart.setData(agePieChartData); 
	   
  //Gender Bar chart
   XYChart.Series<String, Integer> genderData = new XYChart.Series<>();
	   genderData.setName("GENDER");       
	   genderData.getData().add(new XYChart.Data<>("MALE", 10000));
	   genderData.getData().add(new XYChart.Data<>("FEMALE", 15000));
	   genderBarChart.getData().add(genderData);
  
   //Age Bar chart
   XYChart.Series<String, Integer> ageData = new XYChart.Series<>();
	   ageData.setName("AGE");       
	   ageData.getData().add(new XYChart.Data<>("0-20", 10000));
	   ageData.getData().add(new XYChart.Data<>("21-40", 15000));
	   ageData.getData().add(new XYChart.Data<>("41-60", 12000));
	   ageData.getData().add(new XYChart.Data<>("61-120", 9000));
	   ageBarChart.getData().add(ageData);
   
   //Line chart
   XYChart.Series<Integer, Integer> averageData = new XYChart.Series<>();
	   averageData.setName("Historical average");       
	   averageData.getData().add(new XYChart.Data<>(1, 25));
	   averageData.getData().add(new XYChart.Data<>(2, 435));
	   averageData.getData().add(new XYChart.Data<>(3, 2500));
	   averageData.getData().add(new XYChart.Data<>(4, 267));
	   averageData.getData().add(new XYChart.Data<>(5, 400));
	   averageData.getData().add(new XYChart.Data<>(6, 20000));
	   averageData.getData().add(new XYChart.Data<>(7, 25000));
	   lineChart.getData().add(averageData);

   

   
	}//end initialize
	
}











