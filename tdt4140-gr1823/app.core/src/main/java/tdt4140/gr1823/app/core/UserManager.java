package tdt4140.gr1823.app.core;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserManager {
	
	DBManager myCon;
	
	public UserManager() {
		myCon = new DBManager();
	}
    
    public void addUser(User user) {
    	myCon.connect();
    myCon.execute("INSERT INTO Person VALUES ('"+ user.getName() +"', "+ user.getGender() +"', '"+ user.getb_Date() +"', '"+ user.getEmail()+"', '"+ user.getUsername()+ "', '"+ user.getPassword());
	try {
		myCon.disconnect();
	} catch (SQLException e) {
		e.printStackTrace();
	}
    }
 
    public void deleteUser(User user) {
    		myCon.connect();
        myCon.execute("DELETE FROM Person WHERE 'ID="+ user.getID()+"'");
        try {
				myCon.disconnect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    }
   
   public int getNumberOfUsers() throws SQLException {
	   myCon.connect();
	   ArrayList<ArrayList<String>> ret = myCon.retrieve("SELECT COUNT(*) FROM Person");
			try {
				myCon.disconnect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    			ArrayList<String> insideFirstArray = ret.get(0);
    		String insideSecondArray = (String) insideFirstArray.get(0);
    		return Integer.parseInt(insideSecondArray);
    }
   
   public boolean getShareValue(String username) throws SQLException { //Henter share value fra databasen. 
		ArrayList<ArrayList<String>> personTable = myCon.retrieve("SELECT Share FROM Person WHERE Username = '"+username+"';");
		ArrayList<String> rad = personTable.get(0);
		int acceptDataSharing = Integer.parseInt(rad.get(0)); //lagrer verdien som står den personen som logger inn sin Share-kolonne i variabelen acceptDataSharing.
		if (acceptDataSharing == 0) {
			return false; 
		} else {
			return true;
		}
	}
	
   public void setShareValue(String username, boolean share) {
	   myCon.execute("UPDATE Person SET Share = '" + share + "' WHERE Username = '" + username + "';" );
	   
   }
   
    
    public static void main(String[] args) throws Exception {
	UserManager um = new UserManager();
	//User user = new User("Andreas", LocalDate.of(1995, 06,10), Gender.MALE, "test@mail.com", "username", "password");
	//um.addUser(user);
	System.out.println(um.getNumberOfUsers());
    }
    
}
