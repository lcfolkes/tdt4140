package tdt4140.gr1823.app.core;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserManager {
	
	DBManager myCon;
	
	public UserManager() {
		myCon = new DBManager();
	}
    
    public void addUser(User user) {
    myCon.execute("INSERT INTO Person VALUES ('"+ user.getName() +"', "+ user.getGender() +"', '"+ user.getb_Date() +"', '"+ user.getEmail()+"', '"+ user.getUsername()+ "', '"+ user.getPassword());
    }
 
    public void deleteUser(User user) {
        myCon.execute("DELETE FROM Person WHERE 'ID="+ user.getID()+"'");
    }
   
    public int getNumberOfUsers() throws SQLException {
    		ArrayList ret = myCon.retrieve("SELECT COUNT(*) FROM Person");
    		ArrayList insideFirstArray = (ArrayList) ret.get(0);
    		String insideSecondArray = (String) insideFirstArray.get(0);
    		return Integer.parseInt(insideSecondArray);
 
    }
    
    
}
