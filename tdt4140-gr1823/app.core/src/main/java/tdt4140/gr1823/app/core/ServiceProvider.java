package tdt4140.gr1823.app.core;

import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceProvider {

	DBManager MyCon;

    public ServiceProvider() {
    	MyCon = new DBManager();
    }
    
    public void setRecommendedDailyActivity(int newRecommendedDailyActivity) {
    	MyCon.connect();
    	MyCon.execute("UPDATE RecommendedDailyActivity SET Steps =" + newRecommendedDailyActivity);
    	try {
			MyCon.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public int getRecommendedDailyActivity() {
    	MyCon.connect();
    	try {
			ArrayList<ArrayList<String>> list = MyCon.retrieve("SELECT * FROM RecommendedDailyActivity");
				ArrayList<String> insideFirstArray = list.get(0);
	    			String insideSecondArray = (String) insideFirstArray.get(0);
	    			try {
	    				MyCon.disconnect();
	    			} catch (SQLException e) {
	    				e.printStackTrace();
	    			}
	    		return Integer.parseInt(insideSecondArray);
		} catch (SQLException e) {
			System.out.println("ERROR: Can't retrieve data. Check syntax..");
			e.printStackTrace();
		} 
    	try {
			MyCon.disconnect();
		} catch (SQLException e) {
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
