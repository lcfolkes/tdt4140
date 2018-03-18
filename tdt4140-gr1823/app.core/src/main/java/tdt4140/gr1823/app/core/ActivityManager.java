package tdt4140.gr1823.app.core;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ActivityManager {

	DBManager myCon;
	
	public ActivityManager() {
		myCon = new DBManager();
	}
	 //Method for getting daily steps based on users ID
	public double getDailyActivity(int PersonID, LocalDate Date) throws SQLException {
		myCon.connect();
		ArrayList<ArrayList<String>> ret = myCon.retrieve("SELECT Steps FROM DailySteps WHERE PersonID = '"+ PersonID + "' AND Date = '" + Date + "'");
		try {
			myCon.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String result = getElementInArray(ret);
		return Double.parseDouble(result);
	}
	
	public double getTodaySteps(int PersonID) throws SQLException {
		return getDailyActivity(PersonID, LocalDate.now());
	}
	
    
    public void addActivity(DailyActivity activity) {
    	myCon.connect();
    myCon.execute("INSERT INTO DailyActivity VALUES ('"+ activity.getUser().getID() +"',"+ activity.getDate()+", "+ activity.getSteps());
	try {
		myCon.disconnect();
	} catch (SQLException e) {
		e.printStackTrace();
	}
    }
    
    public double getNationalAverage() throws SQLException {
    		myCon.connect();
    		ArrayList<ArrayList<String>>ret = myCon.retrieve("SELECT AVG(Steps) FROM DailySteps");
		ArrayList<String> insideFirstArray = ret.get(0);
    		String insideSecondArray = (String) insideFirstArray.get(0);
    		try {
    			myCon.disconnect();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return Double.parseDouble(insideSecondArray);
 
    }
    
    //Delegater
    public double filter(String ageFrom, String ageTo, String gender) throws NumberFormatException, SQLException{
    	
	    	if(ageFrom.equals("") && ageTo.equals("") && gender.equals("")) {
	    		return getNationalAverage();
	    	}
	    	else if(gender.equals("")){
	    		return filterByAge(ageFrom,ageTo);
	    	}
	    	else if(ageFrom.equals("") && ageTo.equals("")) {
	    		return filterByGender(gender);
	    	}
	    	else {
	    		return filterByGenderAge(ageFrom, ageTo, gender);
	    	}
    }
    
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
    private double filterByGenderAge(String ageFrom, String ageTo, String gender) throws NumberFormatException, SQLException {
    		Gender genderEnum = gender.equals("MALE") ? Gender.MALE : Gender.FEMALE;
    		ArrayList<ArrayList<String>> ret;
    		if(ageFrom.isEmpty()) {
    			myCon.connect();
    			ret = myCon.retrieve("SELECT AVG(Steps) FROM DailySteps INNER JOIN Person ON DailySteps.PersonID = Person.ID WHERE B_Date >= '" + convertAgeToDate(Integer.parseInt(ageTo)+1)+"' AND Gender = '"+genderEnum +"'");
    			try {
    				myCon.disconnect();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}
    		else if(ageTo.isEmpty()) {
    			myCon.connect();
    			ret = myCon.retrieve("SELECT AVG(Steps) FROM DailySteps INNER JOIN Person ON DailySteps.PersonID = Person.ID WHERE B_Date <= '" + convertAgeToDate(Integer.parseInt(ageFrom)) +"' AND Gender = '"+genderEnum + "'");
    			try {
    				myCon.disconnect();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		} else {
    			myCon.connect();
    			ret = myCon.retrieve("SELECT AVG(Steps) FROM DailySteps INNER JOIN Person ON DailySteps.PersonID = Person.ID WHERE B_Date <= '" + convertAgeToDate(Integer.parseInt(ageFrom)) + "' AND B_Date > '" + convertAgeToDate(Integer.parseInt(ageTo)) + "' AND Gender = '"+genderEnum+"'");
    			try {
    				myCon.disconnect();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}
    		String result = getElementInArray(ret);
    		return Double.parseDouble(result);
    		
	}
    
    
	//This should be called with example-input filter(5,80,null)
    private double filterByAge(String ageFrom, String ageTo) throws NumberFormatException, SQLException {
    		ArrayList<ArrayList<String>> ret;
    		if(ageFrom.isEmpty()) {
    			myCon.connect();
    			ret = myCon.retrieve("SELECT AVG(Steps) FROM DailySteps INNER JOIN Person ON DailySteps.PersonID = Person.ID WHERE B_Date >= '" + convertAgeToDate(Integer.parseInt(ageTo)+1)+"'");
    			try {
    				myCon.disconnect();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}
    		else if(ageTo.isEmpty()) {
    			myCon.connect();
    			ret = myCon.retrieve("SELECT AVG(Steps) FROM DailySteps INNER JOIN Person ON DailySteps.PersonID = Person.ID WHERE B_Date <= '" + convertAgeToDate(Integer.parseInt(ageFrom)) +"'");
    			try {
    				myCon.disconnect();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		} else {
    			myCon.connect();
    			ret = myCon.retrieve("SELECT AVG(Steps) FROM DailySteps INNER JOIN Person ON DailySteps.PersonID = Person.ID WHERE B_Date <= '" + convertAgeToDate(Integer.parseInt(ageFrom)) + "' AND B_Date > '" + convertAgeToDate(Integer.parseInt(ageTo)) + "'");
    			try {
    				myCon.disconnect();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}
    		String result = getElementInArray(ret);
    		System.out.println(result);
    		return Double.parseDouble(result);
    	
    }
    
    private double filterByGender(String gender) throws NumberFormatException, SQLException {
    		Gender genderEnum = gender.equals("MALE") ? Gender.MALE : Gender.FEMALE;
    		ArrayList<ArrayList<String>> ret;
    		myCon.connect();
    		ret = myCon.retrieve("SELECT AVG(Steps) FROM Person INNER JOIN DailySteps ON DailySteps.PersonID = Person.ID WHERE Gender = '"+genderEnum+"'");
    		try {
    			myCon.disconnect();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		String result = getElementInArray(ret);
    		return Double.parseDouble(result);
    }
    
    //Helper-method to convert specified age to date-intervals
    protected LocalDate convertAgeToDate(int age){
    		LocalDate today = LocalDate.now();
    		LocalDate returnDate = LocalDate.of(today.getYear() - age, today.getMonthValue(), today.getDayOfMonth());
    		return returnDate;
    }
    public static void main(String[] args) throws NumberFormatException, SQLException {
		ActivityManager am = new ActivityManager();
		System.out.println(am.getDailyActivity(37, LocalDate.of(2018, 03, 12)));
		
	
		// System.out.println(am.("22","44",Gender.MALE));
	}
}