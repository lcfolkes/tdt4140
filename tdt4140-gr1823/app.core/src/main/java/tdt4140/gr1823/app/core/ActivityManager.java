package tdt4140.gr1823.app.core;

import java.sql.SQLException;
import java.util.ArrayList;

public class ActivityManager {

	DBManager myCon;
	
	public ActivityManager() {
		myCon = new DBManager();
	}
    
    public void addActivity(DailyActivity activity) {
    myCon.execute("INSERT INTO DailyActivity VALUES ('"+ activity.getUser().getID() +"',"+ activity.getDate()+", "+ activity.getSteps());
    }
    
   //This method is not done yet
    public double getNationalAverage() throws SQLException {
    	ArrayList<ArrayList<String>>ret = myCon.retrieve("SELECT AVG(Steps) AS AverageSteps FROM DailySteps");
			ArrayList<String> insideFirstArray = ret.get(0);
    		String insideSecondArray = (String) insideFirstArray.get(0);
    		return Double.parseDouble(insideSecondArray);
 
    }
    public static void main(String[] args) {
		ActivityManager am = new ActivityManager();
		try {
			System.out.println(am.getNationalAverage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}