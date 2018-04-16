package tdt4140.gr1823.app.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DBManager {
	
	private Connection myCon;
	private Statement myStatement;
	private ResultSet myResultSet;
	
	/**This class handles all database-functionality. 
	All other classes in the db-package uses this class to:
	connect, disconnect, retreive and execute queries. */
	public DBManager() {
	}
	
	/**Sets up a connection to the database */
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Properties p = new Properties ();
			 p.put("user","erlenhst_DB");
			 p.put("password","gruppe23");
			 myCon = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/erlenhst_Database?useSSL=false",p); 
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to connect", e);
		}
	}
	
	/**Disconnects from the database */
	public void disconnect() throws SQLException {
		myCon.close();
	}
	
	/**This metod is used to retrieve data from the database */
	public ArrayList<ArrayList<String>> retrieve(String query) throws SQLException {
		try {
			connect();
			myStatement = myCon.createStatement();
			myStatement.executeQuery(query);
			myResultSet = myStatement.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();}
		
		ArrayList<ArrayList<String>> returnList = new ArrayList<>();
		while (myResultSet.next()) {
			int index = 1;
			ArrayList<String> innerList = new ArrayList<>();
			while (true) {
				try {
					String temp = myResultSet.getString(index);
					innerList.add(temp);
					index++;
				}catch (Exception e) {
					break;
				}
			}
			returnList.add(innerList);
		}
		try {
			disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnList;
	}

	/**This method is used to execute SQL-queries on the database	*/
	public void execute(String query) throws SQLException{
		try {
			connect();
            myStatement = myCon.createStatement();
            myStatement.executeUpdate(query);
        } catch (Exception e) {
        		e.printStackTrace();
        } finally {
        		disconnect();
        }
	}
		
	/**Helper-method to accsess the string placed inside retrieveArray from DB */
	//This method is used in ActivityManager, SPManager and UserManager.
  	public static String getElementInArray(ArrayList<ArrayList<String>> retrieveArray) {
      	ArrayList<String> insideFirstArray = retrieveArray.get(0);
      	String insideSecondArray;
      	insideSecondArray = insideFirstArray.get(0);
      	if(insideSecondArray == null) {
      		return"0";
      	}
      	return insideSecondArray;	
      }

}
