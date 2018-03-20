package tdt4140.gr1823.app.HttpCommunication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;

public class HttpCommunication {

	public int getRecommendedDailyActivity() throws IOException {
		HttpURLConnection con = createHttpConnection("http://localhost:8080/getRecommendedDailyActivityServlet/");
		int status = con.getResponseCode();
		String response = readResponse(new BufferedReader(new InputStreamReader(con.getInputStream()))).toString();
		System.out.println(status);
		return Integer.parseInt(response);
	}
	
	
	public double getDailyActivity(String Username, LocalDate Date) throws IOException{
    		HttpURLConnection con = createHttpConnection("http://localhost:8080/dailyActivityServlet?username="+Username+"&localDate="+Date);
    		int status = con.getResponseCode();
    		String response = readResponse(new BufferedReader(new InputStreamReader(con.getInputStream()))).toString(); 
    		System.out.println(status);
    		return Double.parseDouble(response);
	}   	
    	
	
	public double getNationalAverage() throws IOException {
		HttpURLConnection con = createHttpConnection("http://localhost:8080/getNationalAverage");
		int status = con.getResponseCode();
		String response = readResponse(new BufferedReader(new InputStreamReader(con.getInputStream()))).toString();
		System.out.println(status);
		return Math.floor(Double.parseDouble(response));
	}
	

	public double getTodaySteps(String Username) throws IOException {
		return getDailyActivity(Username, LocalDate.now());
	}

	public boolean getShareValue(String username) throws IOException  { //Henter share value fra databasen. 
		HttpURLConnection con = createHttpConnection("http://localhost:8080/shareData?username="+username);
		int status = con.getResponseCode();
		String response = readResponse(new BufferedReader(new InputStreamReader(con.getInputStream()))).toString();
		System.out.println(status);
		int shareData = Integer.parseInt(response);
		if(shareData == 1) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public void setShareValue(String username, int share) throws SQLException, IOException {
		HttpURLConnection con = createHttpConnection("http://localhost:8080/shareData?username="+username+"&share="+share);
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
	
	public static void main(String[] args) throws IOException {
		HttpCommunication test = new HttpCommunication();
		System.out.println(test.getDailyActivity("hilde@gmail.com", LocalDate.now()));
	}
}
