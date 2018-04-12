package tdt4140.gr1823.web.server;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tdt4140.gr1823.app.db.DBManager;

public class ShareDataServlet extends HttpServlet {
	
	private DBManager db; 
	
	@Override
	public void init() {
		db = new DBManager();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
			String[] queryString = request.getQueryString().split("&");
			
			String tableName; 
			ArrayList<String> result;
			try {
				db.connect();
				if(queryString.length == 2) {
					String username = queryString[0].split("=")[1];
					tableName = queryString[1].split("=")[1];
					ArrayList<ArrayList<String>> ret;
					ret = db.retrieve("SELECT Share FROM "+tableName+" WHERE Username='"+username+"';");
					result = ret.get(0);
					response.setStatus(HttpServletResponse.SC_OK);
					response.getWriter().write(result.get(0));	
				} else{
					String username = queryString[0].split("=")[1];
					int share = Integer.parseInt(queryString[1].split("=")[1]);
					tableName = queryString[2].split("=")[1];
					System.out.println(tableName);
					db.execute("UPDATE "+tableName+" SET Share=" + share + " WHERE Username='"+username+"';");
					response.setStatus(HttpServletResponse.SC_OK);
					}				
			}catch (SQLException e) {
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