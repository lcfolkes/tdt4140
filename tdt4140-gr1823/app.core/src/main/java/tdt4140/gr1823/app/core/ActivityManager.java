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
    public int getNationalAverage() throws SQLException {
    		ArrayList<ArrayList<Integer>> ret = myCon.retrieve("SELECT AVG(Steps) AS AverageSteps FROM DailySteps");
    		ArrayList<Integer> insideFirstArray = (ArrayList) ret.get(0);
    		int insideSecondArray = (Integer) insideFirstArray.get(0);
    		return insideSecondArray;
 
    }
}