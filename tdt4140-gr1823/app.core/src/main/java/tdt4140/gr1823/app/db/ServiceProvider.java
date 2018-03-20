package tdt4140.gr1823.app.db;

import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceProvider {

	DBManager MyCon;

    public ServiceProvider() {
    	MyCon = new DBManager();
    }
    
    public void setRecommendedDailyActivity(int newRecommendedDailyActivity) {
    	try {
    			MyCon.execute("UPDATE RecommendedDailyActivity SET Steps =" + newRecommendedDailyActivity);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public int getRecommendedDailyActivity() {
    	try {
			ArrayList<ArrayList<String>> list = MyCon.retrieve("SELECT * FROM RecommendedDailyActivity");
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
		ServiceProvider Test = new ServiceProvider();
		Test.setRecommendedDailyActivity(7043);
		Test.getRecommendedDailyActivity();
	}
    
}
