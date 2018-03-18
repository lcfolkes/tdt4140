package tdt4140.gr1823.app.core;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;
public class DBManager {
	
	private Connection myCon;
	private Statement myStatement;
	private ResultSet myResultSet;
	
	public DBManager() {
	}
	
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Properties p = new Properties ();
			 p.put("user","erlenhst_DB");
			 p.put("password","gruppe23");
			 myCon = DriverManager.getConnection(
			 "jdbc:mysql://mysql.stud.ntnu.no:3306/erlenhst_Database?useSSL=false",p); 
			System.out.println("Succsessfully connected");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to connect", e);
		}
	}
	
	public void disconnect() throws SQLException {
		myCon.close();
	}
	
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
       //System.out.println(returnList);
		try {
			disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return returnList;
	}

		
		public void execute(String query) throws SQLException{
			try {
				connect();
	            myStatement = myCon.createStatement();
	            myStatement.executeUpdate(query);
	            System.out.println("Success.");	            
	        } catch (Exception e) {
	            System.out.println("The query failed. Check your sql syntax.");
	        } finally {
	        		disconnect();
	        }
		}
	
	public static void main(String[] args) throws Exception {
		
		DBManager myCon = new DBManager();
		User user = new User("andreas@gmail.com","Password1","Andreas", LocalDate.of(1995, 06,10), Gender.MALE, 1);
		myCon.execute("INSERT INTO Person(Username, Password,Name, B_Date, Gender, Share) VALUES('"+user.getUsername()+"','"+user.getPassword()+"','"+user.getName()+"','"+user.getb_Date()+"','"+user.getGender()+"')");
		try {
			System.out.println(myCon.retrieve("SELECT * FROM Person"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
