package tdt4140.gr1823.app.core;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserManager {
	
	DBManager myCon;
	
	public UserManager() {
		myCon = new DBManager();
	}
    
	//Adds a user on the format User(Username, password, name, b_date, gender, share)
    public void addUser(User user) {
    try {
        myCon.execute("INSERT INTO Person VALUES ('"+ user.getUsername() +"', "+ user.getPassword()+"', "+ user.getName() +"', "+ user.getb_Date() +"', '"+ user.getGender() +"', '"+ user.getSharing());
	} catch (SQLException e) {
		e.printStackTrace();
	}
    }
 
    public void deleteUser(User user) {
    try {
    			myCon.execute("DELETE FROM Person WHERE 'Username="+ user.getUsername()+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
   
   public int getNumberOfUsers() throws SQLException {
		ArrayList<ArrayList<String>> ret = myCon.retrieve("SELECT COUNT(*) FROM Person");
    		return Integer.parseInt(ActivityManager.getElementInArray(ret));
    }
   
    
    public static void main(String[] args) throws Exception {
	UserManager um = new UserManager();
	//User user = new User("Andreas", LocalDate.of(1995, 06,10), Gender.MALE, "test@mail.com", "username", "password");
	//um.addUser(user);
	System.out.println(um.getNumberOfUsers());
    }
    
}
