package tdt4140.gr1823.web.server;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tdt4140.gr1823.app.db.DBManager;


public class dailyActivityServlet extends HttpServlet {
	
	private DBManager db; 
	@Override
	public void init() {
		db = new DBManager();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
			String[] queryString = request.getQueryString().split("&");
			String username = queryString[0].split("=")[1];
			String localDate = queryString[1].split("=")[1];
			String tableName = queryString[2].split("=")[1];
			ArrayList<String> result;
			try {
				db.connect();
				ArrayList<ArrayList<String>> ret = db.retrieve("SELECT Steps FROM "+tableName+" WHERE Username = '"+ username + "' AND Date = '" + localDate + "';");
				result = ret.get(0);
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().write(result.get(0));
			} catch (SQLException e) {
				e.printStackTrace();
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			} finally {
				try {
					db.disconnect();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} 
}
