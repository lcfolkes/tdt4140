package tdt4140.gr1823.web.server;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tdt4140.gr1823.app.db.ActivityManager;
import tdt4140.gr1823.app.db.DBManager;

public class getRecommendedDailyActivityServlet extends HttpServlet {
	
	private DBManager db; 
	@Override
	public void init() {
		db = new DBManager();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String tableName = request.getQueryString().split("=")[1];
			
			db.connect();
			ArrayList<ArrayList<String>> list = db.retrieve("SELECT * FROM "+tableName+";");
			String result = ActivityManager.getElementInArray(list);
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().write(result);
		} catch (SQLException e) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			e.printStackTrace();
		}finally {
			try {
				db.disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
