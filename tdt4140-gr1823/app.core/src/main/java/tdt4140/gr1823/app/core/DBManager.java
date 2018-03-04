package tdt4140.gr1823.app.core;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {
	
	private Connection myCon;
	private Statement myStatement;
	private ResultSet myResultSet;
	
	public void DBManager() {
		connect();
	}
	
	public void connect() {
		try {
			myCon = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/erlenhst_Database","erlenhst_DB","gruppe23");
			System.out.println("Succsessfully connected");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
		public ArrayList retrieve(String query) throws SQLException {
			try {
				myStatement = myCon.createStatement();
				myStatement.executeQuery(query);
				myResultSet = myStatement.getResultSet();
			} catch (SQLException e) {
				e.printStackTrace();}
			
		
			ArrayList<ArrayList<String>> returnList = new ArrayList();
			while (myResultSet.next()) {
	            int index = 1;
	            ArrayList<String> innerList = new ArrayList();
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
	        System.out.println(returnList);
	        return returnList;
		}
		
		public void insert(String query) {
			
	        try {
	            myStatement = myCon.createStatement();
	            myStatement.executeUpdate(query);
	            System.out.println("Success.");
	        } catch (Exception e) {
	            System.out.println("The query failed. Check your sql syntax.");
	        }
	    }
		
		public void delete(String query) {
			try {
	            myStatement = myCon.createStatement();
	            myStatement.executeUpdate(query);
	            System.out.println("Success.");
	        } catch (Exception e) {
	            System.out.println("The query failed. Check your sql syntax.");
	        }
		}
		
		public void update(String query){
			
			try {
	            myStatement = myCon.createStatement();
	            myStatement.executeUpdate(query);
	            System.out.println("Success.");
	        } catch (Exception e) {
	            System.out.println("The query failed. Check your sql syntax.");
	        }
			
		}
	
	public static void main(String[] args) {
		
		DBManager myCon = new DBManager();
		myCon.connect();
		
		
		//myCon.delete("DELETE FROM Person WHERE ID=1");
	}
	 

}
