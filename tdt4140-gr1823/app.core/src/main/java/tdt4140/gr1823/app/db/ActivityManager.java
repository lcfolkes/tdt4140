package tdt4140.gr1823.app.db;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import tdt4140.gr1823.app.core.DailyActivity;
import tdt4140.gr1823.app.core.Gender;

public class ActivityManager {

	DBManager myCon;
	int acceptDataSharing;
	
	/**This class handles all activity-related data 
	Used to get daily activity and calculate national average etc. */
	
	public ActivityManager() {
		myCon = new DBManager();
	}
	 /**Method for getting daily steps based on username */
	public double getDailyActivity(String Username, LocalDate Date, String TableName) throws SQLException {
		myCon.connect();
		ArrayList<ArrayList<String>> ret = myCon.retrieve("SELECT Steps FROM " + TableName + " WHERE Username = '"+ Username + "' AND Date = '" + Date + "';");
		try {
			myCon.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<String> result = ret.get(0);
		return Math.floor(Double.parseDouble(result.get(0)));
	}
	
	 /**Method for getting the steps on todays date from username */
	public double getTodaySteps(String Username, String tableName) throws SQLException {
		return getDailyActivity(Username, LocalDate.now(), tableName);
	}
	
	 /**Method for adding a new DailyActivity-object to the database */
    public void addActivity(DailyActivity activity, String TableName) {
	try {
		 myCon.execute("INSERT INTO " + TableName + " VALUES ('"+ activity.getUser().getUsername() +"','"+ activity.getDate()+"', "+ activity.getSteps()+","+ activity.getUser().getSharing()+");");
	} catch (SQLException e) {
		e.printStackTrace();
		}
    }
    
	 /**Method for deleting a DailyActivity-object from the database */
    public void deleteDailyActivity(String Username, LocalDate Date, String TableName) throws SQLException {
		myCon.connect();
		myCon.execute("DELETE FROM " + TableName + " WHERE Username = '"+ Username + "' AND Date = '" + Date + "';");
		try {
			myCon.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    
	 /**Method for getting national average of steps from all users from the database */
    public double getNationalAverage(String TableName) throws SQLException {
    		myCon.connect();
    		ArrayList<ArrayList<String>>ret = myCon.retrieve("SELECT AVG(Steps) FROM "+TableName+";");
		ArrayList<String> insideFirstArray = ret.get(0);
    		String insideSecondArray = (String) insideFirstArray.get(0);
    		return Math.floor(Double.parseDouble(insideSecondArray));
    }
    
    /**This method is used in HomeScreenController to make the line chart for the historical data (national average last 12 months)
    Takes in the number of months you want to go back and calculate average for.*/
    public double getNationalAverageByMonth(int month) throws SQLException {
    		LocalDate today = LocalDate.now();
    		LocalDate prevMonthDate = today.minusMonths(month);
    		LocalDate firstDay = prevMonthDate.minusDays(prevMonthDate.getDayOfMonth()-1);
    		LocalDate lastDay = firstDay.plusDays(prevMonthDate.getMonth().maxLength());
    		
    		String fromDate = firstDay.toString();
    		String toDate = lastDay.toString();
    		try {
		ArrayList<ArrayList<String>>ret = myCon.retrieve("SELECT AVG(Steps) FROM DailySteps WHERE Date > '"+fromDate+"' AND Date <= '"+toDate + "';");
    		ArrayList<String> insideFirstArray = ret.get(0);
    		if(insideFirstArray.contains(null)) {
    			return 0;
    		}
		String insideSecondArray = (String) insideFirstArray.get(0);
		return Math.floor(Double.parseDouble(insideSecondArray));
    		} catch (SQLException e) {
    			e.printStackTrace();
    			return 0;
    		}
    }
    
    /** This method is used in AnalyzeScreen to filter a result and get average steps based on the input. */
    public double filter(String ageFrom, String ageTo, String gender, String tableName1, String tableName2) throws NumberFormatException, SQLException{
    	
	    	if(ageFrom.equals("") && ageTo.equals("") && gender.equals("NOT SPECIFIED")) {
	    		return getNationalAverage(tableName1);
	    	}
	    	else if(gender.equals("")){
	    		return filterByAge(ageFrom,ageTo, tableName1, tableName2);
	    	}
	    	else if(ageFrom.equals("") && ageTo.equals("")) {
	    		return filterByGender(gender, tableName1, tableName2);
	    	}
	    	else {
	    		return filterByGenderAge(ageFrom, ageTo, gender, tableName1, tableName2);
	    	}
    }
    
    /** This method returns national average based on filter-input
     * This should be called with example-input filterByGenderAge(20,60,MALE) */
    
    private double filterByGenderAge(String ageFrom, String ageTo, String gender, String tableName1, String tableName2) throws NumberFormatException, SQLException { //TableName1 = DailySteps, TableName2 = Person. Vi tar inn table names for å kunne teste metodene på egne test-tabeller. 
    		Gender genderEnum = gender.equals("MALE") ? Gender.MALE : Gender.FEMALE;
    		ArrayList<ArrayList<String>> ret;
    		if(ageFrom.isEmpty()) {
    			myCon.connect();
    			ret = myCon.retrieve("SELECT AVG(Steps) FROM "+ tableName1 +" INNER JOIN "+tableName2+" ON "+tableName1+".Username = "+tableName2+".Username WHERE B_Date >= '" + convertAgeToDate(Integer.parseInt(ageTo)+1)+"' AND Gender = '"+genderEnum +"'");
    			try {
    				myCon.disconnect();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}
    		else if(ageTo.isEmpty()) {
    			myCon.connect();
    			ret = myCon.retrieve("SELECT AVG(Steps) FROM "+tableName1+" INNER JOIN "+tableName2+" ON "+tableName1+".Username = "+tableName2+".Username WHERE B_Date <= '" + convertAgeToDate(Integer.parseInt(ageFrom)) +"' AND Gender = '"+genderEnum + "'");
    			try {
    				myCon.disconnect();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		} else {
    			myCon.connect();
    			ret = myCon.retrieve("SELECT AVG(Steps) FROM "+tableName1+" INNER JOIN "+tableName2+" ON "+tableName1+".Username = "+tableName2+".Username WHERE B_Date <= '" + convertAgeToDate(Integer.parseInt(ageFrom)) + "' AND B_Date > '" + convertAgeToDate(Integer.parseInt(ageTo)) + "' AND Gender = '"+genderEnum+"'");
    			try {
    				myCon.disconnect();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}
    		String result = DBManager.getElementInArray(ret);
    		return Math.floor(Double.parseDouble(result));
    		
	}
    
    /** This method returns national average based on  age-filter-input 
	 This should be called with example-input filterByAge(5,80) */
 
    private double filterByAge(String ageFrom, String ageTo, String tableName1, String tableName2) throws NumberFormatException, SQLException {
    		ArrayList<ArrayList<String>> ret;
    		if(ageFrom.isEmpty()) {
    			myCon.connect();
    			ret = myCon.retrieve("SELECT AVG(Steps) FROM "+tableName1+" INNER JOIN "+tableName2+" ON "+tableName1+".Username = "+tableName2+".Username WHERE B_Date >= '" + convertAgeToDate(Integer.parseInt(ageTo)+1)+"'");
    			try {
    				myCon.disconnect();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}
    		else if(ageTo.isEmpty()) {
    			myCon.connect();
    			ret = myCon.retrieve("SELECT AVG(Steps) FROM "+tableName1+" INNER JOIN "+tableName2+" ON "+tableName1+".Username = "+tableName2+".Username WHERE B_Date <= '" + convertAgeToDate(Integer.parseInt(ageFrom)) +"'");
    			try {
    				myCon.disconnect();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		} else {
    			myCon.connect();
    			ret = myCon.retrieve("SELECT AVG(Steps) FROM "+tableName1+" INNER JOIN "+tableName2+" ON "+tableName1+".Username = "+tableName2+".Username WHERE B_Date <= '" + convertAgeToDate(Integer.parseInt(ageFrom)) + "' AND B_Date > '" + convertAgeToDate(Integer.parseInt(ageTo)) + "'");
    			try {
    				myCon.disconnect();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}
    		String result = DBManager.getElementInArray(ret);
    		System.out.println(result);
    		return Math.floor(Double.parseDouble(result));
    	
    }
    
    /** This method returns national average based on  gender.  
	 This should be called with example-input filterbyGender(MALE) */
    
    private double filterByGender(String gender, String tableName1, String tableName2) throws NumberFormatException, SQLException {
    		Gender genderEnum = gender.equals("MALE") ? Gender.MALE : Gender.FEMALE;
    		ArrayList<ArrayList<String>> ret;
    		myCon.connect();
    		ret = myCon.retrieve("SELECT AVG(Steps) FROM "+tableName2+" INNER JOIN "+tableName1+" ON "+tableName1+".Username = "+tableName2+".Username WHERE Gender = '"+genderEnum+"'");
    		try {
    			myCon.disconnect();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		String result = DBManager.getElementInArray(ret);
    		return Math.floor(Double.parseDouble(result));
    }
    
    /**Helper-method to convert specified age to date-intervals */
    public static LocalDate convertAgeToDate(int age){
    		LocalDate today = LocalDate.now();
    		LocalDate returnDate = LocalDate.of(today.getYear() - age, today.getMonthValue(), today.getDayOfMonth());
    		return returnDate;
    }

}