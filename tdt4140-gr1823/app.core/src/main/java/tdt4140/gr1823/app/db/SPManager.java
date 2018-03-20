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
    public void addUser(SPUser user) {
    try {
        myCon.execute("INSERT INTO Person VALUES ('"+ user.getUsername() +"', "+ user.getPassword()+"');");
	} catch (SQLException e) {
		e.printStackTrace();
	}
    }
 
    public void deleteUser(SPUser user) {
    try {
    			myCon.execute("DELETE FROM Person WHERE 'Username="+ user.getUsername()+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public void setRecommendedDailyActivity(int newRecommendedDailyActivity) {
    	try {
    			myCon.execute("UPDATE RecommendedDailyActivity SET Steps =" + newRecommendedDailyActivity);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public int getRecommendedDailyActivity() {
    	try {
			ArrayList<ArrayList<String>> list = myCon.retrieve("SELECT * FROM RecommendedDailyActivity");
			int a = Integer.parseInt(ActivityManager.getElementInArray(list));
			System.out.println(a);
			return Integer.parseInt(ActivityManager.getElementInArray(list));
		} catch (SQLException e) {
			System.out.println("ERROR: Can't retrieve data. Check syntax..");
			e.printStackTrace();
		} 
    	return 0; //Just need this for syntax
		}
    
    public static void main(String[] args) {
		SPManager Test = new SPManager();
		Test.setRecommendedDailyActivity(7043);
		Test.getRecommendedDailyActivity();
	}
    
}
