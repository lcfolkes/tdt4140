package tdt4140.gr1823.app.db;

import java.sql.SQLException;
import java.util.ArrayList;

import tdt4140.gr1823.app.core.Gender;
import tdt4140.gr1823.app.core.User;

public class UserManager {
	
	DBManager myCon;
	
	public UserManager() {
		myCon = new DBManager();
	}
    
	//Adds a user on the format User(Username, password, name, b_date, gender, share)
    public void addUser(User user, String tableName) {
    try {
        myCon.execute("INSERT INTO "+tableName+" VALUES ('"+ user.getUsername() +"', '"+ user.getPassword()+"', '"+ user.getName() +"', '"+ user.getb_Date() +"', '"+ user.getGender() +"', "+ user.getSharing()+");");
	} catch (SQLException e) {
		e.printStackTrace();
	}
    }
 
    public void deleteUser(User user, String tableName) {
    try {
    			myCon.execute("DELETE FROM "+tableName+" WHERE Username='"+ user.getUsername()+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
   //Get number of all users
   public int getNumberOfUsers(String tableName) throws SQLException { //Tar inn Person-tabellen
		ArrayList<ArrayList<String>> ret = myCon.retrieve("SELECT COUNT(*) FROM "+tableName+"");
    		return Integer.parseInt(ActivityManager.getElementInArray(ret));
    }
   
   //Get number of users based on gender
   public int getNumberOfUsers(String tableName, String gender) throws SQLException { //Tar inn Person-tabellen
		ArrayList<ArrayList<String>> ret = myCon.retrieve("SELECT COUNT(*) FROM "+tableName+" WHERE Gender = '"+gender+"';");
   		return Integer.parseInt(ActivityManager.getElementInArray(ret));
   }
   
   //Get number of users based on age
   public int getNumberOfUsers(String tableName, String ageFrom, String ageTo) throws SQLException { //Tar inn Person-tabellen
		ArrayList<ArrayList<String>> ret = myCon.retrieve("SELECT COUNT(*) FROM "+tableName+" WHERE B_Date >= '" + ActivityManager.convertAgeToDate(Integer.parseInt(ageTo)+1)+ "' AND B_Date < '" + ActivityManager.convertAgeToDate(Integer.parseInt(ageFrom)+1)+"';");
   		return Integer.parseInt(ActivityManager.getElementInArray(ret));
   }
   
   //Get number of users based on all attributes
   public int getNumberOfUsers(String tableName, String ageFrom, String ageTo, String gender) throws SQLException { //Tar inn Person-tabellen
		ArrayList<ArrayList<String>> ret = myCon.retrieve("SELECT COUNT(*) FROM "+tableName+" WHERE B_Date >= '" + ActivityManager.convertAgeToDate(Integer.parseInt(ageTo)+1)+ "' AND B_Date < '" + ActivityManager.convertAgeToDate(Integer.parseInt(ageFrom)+1)+ " AND Gender = '" + gender +"';");
  		return Integer.parseInt(ActivityManager.getElementInArray(ret));
  }
   
   
   public boolean getShareValue(String username, String tableName) throws SQLException { //Henter share value fra databasen. (Tar inn person-tabellen)
	   	ArrayList<ArrayList<String>> personTable = myCon.retrieve("SELECT Share FROM "+tableName+" WHERE Username = '"+username+"';");
		ArrayList<String> rad = personTable.get(0);
		int acceptDataSharing = Integer.parseInt(rad.get(0)); //lagrer verdien som står den personen som logger inn sin Share-kolonne i variabelen acceptDataSharing.
		if (acceptDataSharing == 0) {
			return false; 
		} else {
			return true;
		}
	}
	
  public void setShareValue(String username, int share, String tableName) throws SQLException { //Tar inn person-tabellen.
	   myCon.execute("UPDATE "+tableName+" SET Share=" + share + " WHERE Username = '" + username + "';" );
	   
  }

 
    
}
