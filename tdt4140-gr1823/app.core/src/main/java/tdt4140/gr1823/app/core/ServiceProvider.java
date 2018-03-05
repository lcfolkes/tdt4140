package tdt4140.gr1823.app.core;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ServiceProvider {

	
	private DBManager DBManagerObject;

    public ServiceProvider() {
    	DBManagerObject = new DBManager();
    }
    
    public void setRecommendedDailyActivity(int newRecommendedDailyActivity) {
    	DBManagerObject.execute("UPDATE RecommendedDailyActivity SET Steps =" + newRecommendedDailyActivity);
    }
    
    public int getRecommendedDailyActivity() {
    	try {
			 ArrayList list = DBManagerObject.retrieve("SELECT * FROM RecommendedDailyActivity");
			 ArrayList insideFirstArray = (ArrayList) list.get(0);
			 String insideSecondArray = (String) insideFirstArray.get(0);
			 return Integer.parseInt(insideSecondArray);
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
