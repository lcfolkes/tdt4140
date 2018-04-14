package tdt4140.gr1823.app.db;

import java.sql.SQLException;
import java.util.ArrayList;

import tdt4140.gr1823.app.core.SPUser;
import tdt4140.gr1823.app.core.User;

public class SPManager {

	DBManager myCon;

    public SPManager() {
    	myCon = new DBManager();
    }
    
  //Adds a user on the format User(Username, password, name, b_date, gender, share)
    public void addUser(SPUser user, String tableName) { // tar inn user-tabellen
    try {
        myCon.execute("INSERT INTO "+tableName+" VALUES ('"+ user.getUsername() +"', '"+ user.getPassword()+"');");
	} catch (SQLException e) {
		e.printStackTrace();
	}
    }
 
    public void deleteUser(SPUser user, String tableName) { // Tar inn user-tabellen
    try {
    			myCon.execute("DELETE FROM "+tableName+" WHERE Username= '"+ user.getUsername()+"';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public void setRecommendedDailyActivity(int newRecommendedDailyActivity, String tableName) {
    	try {
    			myCon.execute("UPDATE "+tableName+" SET Steps =" + newRecommendedDailyActivity);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public int getRecommendedDailyActivity(String tableName) {
    	try {
			ArrayList<ArrayList<String>> list = myCon.retrieve("SELECT * FROM "+tableName+";");
			int a = Integer.parseInt(ActivityManager.getElementInArray(list));
			System.out.println(a);
			return Integer.parseInt(ActivityManager.getElementInArray(list));
		} catch (SQLException e) {
			System.out.println("ERROR: Can't retrieve data. Check syntax..");
			e.printStackTrace();
		} 
    	return 0; //Just need this for syntax
		}
    
    public boolean isValidPassword(String username, String password) {
    		String dbPassword = "";
    		System.out.println(username);
    		try {
    				ArrayList<ArrayList<String>> list = myCon.retrieve("SELECT Password FROM User WHERE Username='"+ username + "'");
    				if(!list.isEmpty()) {
				// TODO Auto-generated catch block
    					dbPassword = ActivityManager.getElementInArray(list);
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
    
    public void updatePassword(String newPass, String tablename) {
    		try {
				myCon.execute("UPDATE " + tablename + " SET Password= '" + newPass + "';");
			} catch (SQLException e) {
				e.printStackTrace();
			}
    }
    
    public void updateUsername(String newUsername, String tablename) {
		try {
			myCon.execute("UPDATE " + tablename + " SET Username= '" + newUsername + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
}
    
}
