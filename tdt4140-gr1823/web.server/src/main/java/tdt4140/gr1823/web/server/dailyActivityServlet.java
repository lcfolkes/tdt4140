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
	
	//Creates an instance of DBManager to handle database communication.
	@Override
	public void init() {
		db = new DBManager();
	}
	
	/**Expects url-string with three parameters; Username, localDate and tableName (the table to act on in the DB)
	Use these parameters to handle the wanted SQL call to the DB.
	Return the result in the response. **/
	
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
