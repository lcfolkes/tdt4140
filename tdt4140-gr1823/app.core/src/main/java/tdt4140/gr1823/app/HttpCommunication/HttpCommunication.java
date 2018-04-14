package tdt4140.gr1823.app.HttpCommunication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;

public class HttpCommunication {

	// Skrevet test for denne
	public int getRecommendedDailyActivity(String tableName) throws IOException {
		HttpURLConnection con = createHttpConnection("http://localhost:8080/getRecommendedDailyActivityServlet?tableName="+tableName);
		int status = con.getResponseCode();
		String response = readResponse(new BufferedReader(new InputStreamReader(con.getInputStream()))).toString();
		System.out.println(status);
		return Integer.parseInt(response);
	}
	
	// Skrevet test for denne
	public double getDailyActivity(String Username, LocalDate Date, String tableName) throws IOException{
    		HttpURLConnection con = createHttpConnection("http://localhost:8080/dailyActivityServlet?username="+Username+"&localDate="+Date+"&tableName="+tableName);
    		int status = con.getResponseCode();
    		String response = readResponse(new BufferedReader(new InputStreamReader(con.getInputStream()))).toString(); 
    		System.out.println(status);
    		return Double.parseDouble(response);
	}   	
    	
	
	public double getNationalAverage(String tableName) throws IOException {
		HttpURLConnection con = createHttpConnection("http://localhost:8080/getNationalAverage/?tableName="+tableName);
		int status = con.getResponseCode();
		String response = readResponse(new BufferedReader(new InputStreamReader(con.getInputStream()))).toString();
		System.out.println(status);
		return Math.floor(Double.parseDouble(response));
	}
	

	public double getTodaySteps(String Username, String tableName) throws IOException {
		return getDailyActivity(Username, LocalDate.now(), tableName);
	}

	
	// Skrevet test for denne
	public boolean getShareValue(String username, String tableName) throws IOException  { //Henter share value fra databasen. Her må tableName = Person/testPerson
		HttpURLConnection con = createHttpConnection("http://localhost:8080/shareData/?username="+username+"&tableName="+tableName);
		int status = con.getResponseCode();
		System.out.println(status);
		String response = readResponse(new BufferedReader(new InputStreamReader(con.getInputStream()))).toString();
		int shareData = Integer.parseInt(response);
		if(shareData == 1) {
			return true;
		}else {
			return false;
		}
		
	}
	
	// Skrevet test for denne
	public void setShareValue(String username, int share, String tableName) throws SQLException, IOException {
		HttpURLConnection con = createHttpConnection("http://localhost:8080/shareData/?username="+username+"&share="+share+"&tableName="+tableName);
		int status = con.getResponseCode();
		System.out.println(status);   
	  }
	
	private HttpURLConnection createHttpConnection(String urlString) throws IOException {
		URL url = new URL(urlString);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		return con;	
	}
	
	private StringBuffer readResponse(BufferedReader in) throws IOException{
		String inputLine;
    		StringBuffer content = new StringBuffer();
    		while((inputLine = in.readLine()) != null) {
    		content.append(inputLine);
    		}
    		in.close();
    		return content;
	}
}
