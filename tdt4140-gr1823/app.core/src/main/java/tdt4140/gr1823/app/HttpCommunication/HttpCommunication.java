package tdt4140.gr1823.app.HttpCommunication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class HttpCommunication {

//	public int getRecommendedDailyActivity() {
//		
//	}
	
	
	public double getDailyActivity(String Username, LocalDate Date) throws IOException{
		URL url = new URL("http://localhost:8080/dailyActivityServlet?username="+Username+"&localDate="+Date);
    		HttpURLConnection con = (HttpURLConnection) url.openConnection();
    		con.setRequestMethod("GET");
    		int status = con.getResponseCode();
    		String response = readResponse(new BufferedReader(new InputStreamReader(con.getInputStream()))).toString(); 
    		return Double.parseDouble(response);
	}   	
    	
	
//	public double getNationalAverage() throws SQLException {
//		
//	}
//	
//	
//	public double getTodaySteps(String Username) throws SQLException {
//		return getDailyActivity(Username, LocalDate.now());
//	}
//	
//	public boolean getShareValue(String username)  { //Henter share value fra databasen. 
//	   
//	}
//	
	private StringBuffer readResponse(BufferedReader in) throws IOException{
		String inputLine;
    		StringBuffer content = new StringBuffer();
    		while((inputLine = in.readLine()) != null) {
    		content.append(inputLine);
    		}
    		in.close();
    		return content;
	}
	
	public static void main(String[] args) throws IOException {
		HttpCommunication test = new HttpCommunication();
		System.out.println(test.getDailyActivity("hilde@gmail.com", LocalDate.now()));
	}
}
