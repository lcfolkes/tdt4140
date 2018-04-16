package tdt4140.gr1823.app.db;

import java.sql.SQLException;
import java.util.ArrayList;
import tdt4140.gr1823.app.core.SPUser;

public class SPManager {

	DBManager myCon;
	
	/**This class handles all ServiceProvider-related functionality against the database.  
	Used to add/delete SPUsers and change recommended activity level */

    public SPManager() {
    	myCon = new DBManager();
    }
    
  /** Adds a user on the format User(Username, password, name, b_date, gender, share) */
    public void addUser(SPUser user, String tableName) { // tar inn user-tabellen
	    try {
	        myCon.execute("INSERT INTO "+tableName+" VALUES ('"+ user.getUsername() +"', '"+ user.getPassword()+"');");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    /** Deletes a user from the database */
    public void deleteUser(SPUser user, String tableName) { 
    try {
    			myCon.execute("DELETE FROM "+tableName+" WHERE Username= '"+ user.getUsername()+"';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    /** Changes the Recommended Daily Activity in the database */
    public void setRecommendedDailyActivity(int newRecommendedDailyActivity, String tableName) {
    	try {
    			myCon.execute("UPDATE "+tableName+" SET Steps =" + newRecommendedDailyActivity);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    /** Returns Recommended Daily Activity from the database */
    public int getRecommendedDailyActivity(String tableName) {
    	try {
			ArrayList<ArrayList<String>> list = myCon.retrieve("SELECT * FROM "+tableName+";");
			return Integer.parseInt(DBManager.getElementInArray(list));
		} catch (SQLException e) {
			e.printStackTrace();
		} 
    	return 0; //Just need this for syntax
		}
    
    /** Checks if input (password) is the same as is saved in the database */
    public boolean isValidPassword(String username, String password) {
    		String dbPassword = "";
    		try {
    				ArrayList<ArrayList<String>> list = myCon.retrieve("SELECT Password FROM User WHERE Username='"+ username + "'");
    				if(!list.isEmpty()) {
				// TODO Auto-generated catch block
    					dbPassword = DBManager.getElementInArray(list);
    				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
    		if(dbPassword.equals(password)) {
    			return true;
    		}
    		return false;	
    }
    
    /** Updates the password for a SPUser in the database*/
    public void updatePassword(String newPass, String tablename) {
    		try {
				myCon.execute("UPDATE " + tablename + " SET Password= '" + newPass + "';");
			} catch (SQLException e) {
				e.printStackTrace();
			}
    }
    
    /** Sets a new Username */
    public void updateUsername(String newUsername, String tablename) {
		try {
			myCon.execute("UPDATE " + tablename + " SET Username= '" + newUsername + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
}
    
}
