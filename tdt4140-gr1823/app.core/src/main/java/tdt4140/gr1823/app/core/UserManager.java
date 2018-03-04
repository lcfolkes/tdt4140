package tdt4140.gr1823.app.core;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserManager {
	
	DBManager myCon;
	
	public void UserManager() {
		myCon = new DBManager();
	}
    
    public void addUser(User user) {
    myCon.insert("INSERT INTO Person (Name, Gender, B_Date, Email, Username, Password)\n" + 
                "VALUES ('user.getName()', 'user.getGender()', 'user.getDateOfBirth()', 'user.getEmail()', 'user.getUsername()', 'user.getPassword()');");
    }
 
    public void deleteUser(User user) {
        myCon.delete("DELETE FROM Person WHERE 'ID=user.getID()'");
    }
   
    public int getNumberOfUsers() throws SQLException {
    		ArrayList ret = myCon.retrieve("SELECT COUNT(*) FROM Person");
    		ArrayList insideFirstArray = (ArrayList) ret.get(0);
    		String insideSecondArray = (String) insideFirstArray.get(0);
    		return Integer.parseInt(insideSecondArray);
 
    }
    
    
}
