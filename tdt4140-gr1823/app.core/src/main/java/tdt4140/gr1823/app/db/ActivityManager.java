package tdt4140.gr1823.app.db;

import java.sql.SQLException;
import java.math.*;
import java.time.LocalDate;
import java.util.ArrayList;

import tdt4140.gr1823.app.core.DailyActivity;
import tdt4140.gr1823.app.core.Gender;

public class ActivityManager {

	DBManager myCon;
	int acceptDataSharing;
	
	public ActivityManager() {
		myCon = new DBManager();
	}
	 //Method for getting daily steps based on username
	public double getDailyActivity(String Username, LocalDate Date, String TableName) throws SQLException {
		myCon.connect();
		ArrayList<ArrayList<String>> ret = myCon.retrieve("SELECT Steps FROM " + TableName + " WHERE Username = '"+ Username + "' AND Date = '" + Date + "';");
		try {
			myCon.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//String result = getElementInArray(ret);
		ArrayList<String> result = ret.get(0);
		return Math.floor(Double.parseDouble(result.get(0)));
	}
	
	
	public double getTodaySteps(String Username, String tableName) throws SQLException {
		return getDailyActivity(Username, LocalDate.now(), tableName);
	}
	
	
	
	// SPRINT 3 - UPDATE
	
	public void deleteDailyActivity(String Username, LocalDate Date, String TableName) throws SQLException {
		myCon.connect();
		myCon.execute("DELETE FROM " + TableName + " WHERE Username = '"+ Username + "' AND Date = '" + Date + "';");
		try {
			myCon.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// SPRINT 3 - END of UPDATE
	
	
	
	
    
    public void addActivity(DailyActivity activity, String TableName) {
	try {
		 myCon.execute("INSERT INTO " + TableName + " VALUES ('"+ activity.getUser().getUsername() +"','"+ activity.getDate()+"', "+ activity.getSteps()+","+ activity.getUser().getSharing()+");");
	} catch (SQLException e) {
		e.printStackTrace();
		}
    }
    
    public double getNationalAverage(String TableName) throws SQLException {
    		myCon.connect();
    		ArrayList<ArrayList<String>>ret = myCon.retrieve("SELECT AVG(Steps) FROM "+TableName+";");
		ArrayList<String> insideFirstArray = ret.get(0);
    		String insideSecondArray = (String) insideFirstArray.get(0);
    		try {
    			myCon.disconnect();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return Math.floor(Double.parseDouble(insideSecondArray));
 
    }
    
    //Delegater
    public double filter(String ageFrom, String ageTo, String gender, String tableName1, String tableName2) throws NumberFormatException, SQLException{
    	
	    	if(ageFrom.equals("") && ageTo.equals("") && gender.equals("")) {
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
    
  //this method should be moved to DBManager
  //Helper-method to accsess the string placed inside retrieveArray from DB
  	public static String getElementInArray(ArrayList<ArrayList<String>> retrieveArray) {
      	ArrayList<String> insideFirstArray = retrieveArray.get(0);
      	String insideSecondArray;
      	insideSecondArray = insideFirstArray.get(0);
      	if(insideSecondArray == null) {
      		return"0";
      	}
      	return insideSecondArray;	
      }
    
    //This should be called with example-input filter(20,60,MALE)
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
    		String result = getElementInArray(ret);
    		return Math.floor(Double.parseDouble(result));
    		
	}
    
    
	//This should be called with example-input filter(5,80,null)
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
    		String result = getElementInArray(ret);
    		System.out.println(result);
    		return Math.floor(Double.parseDouble(result));
    	
    }
    
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
    		String result = getElementInArray(ret);
    		return Math.floor(Double.parseDouble(result));
    }
    
    //Helper-method to convert specified age to date-intervals
    protected LocalDate convertAgeToDate(int age){
    		LocalDate today = LocalDate.now();
    		LocalDate returnDate = LocalDate.of(today.getYear() - age, today.getMonthValue(), today.getDayOfMonth());
    		return returnDate;
    }
    public static void main(String[] args) throws NumberFormatException, SQLException {
		// ActivityManager am = new ActivityManager();
		// System.out.println(am.("22","44",Gender.MALE));
	}
}