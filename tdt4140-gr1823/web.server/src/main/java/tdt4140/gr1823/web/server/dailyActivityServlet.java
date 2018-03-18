package tdt4140.gr1823.web.server;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tdt4140.gr1823.app.core.DBManager;

public class dailyActivityServlet extends HttpServlet {
	
	
	private DBManager db; 
	@Override
	public void init() {
		db = new DBManager();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String[] queryString = request.getQueryString().split("&");
		
		String username = queryString[0].split("=")[1];
		String localDate = queryString[1].split("=")[1];
		
		db.connect();
		
		try {
			ArrayList<ArrayList<String>> ret = db.retrieve("SELECT Steps FROM DailySteps WHERE Username = '"+ username + "' AND Date = '" + localDate + "';");
			ArrayList<String> result = ret.get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	

}
