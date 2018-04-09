package tdt4140.gr1823.app.core;

import java.time.LocalDate;
import java.util.regex.*;

public class SPUser {
	
	//Attributes
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE); 
	public static final Pattern VALID_PASSWORD_REGEX = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$");
	
	private String username; //use email as username. Have reused the same methods for set ang get emial for username. 
	private String password; //setter with encapsulation and getter
	
	//CONSTRUCTORS
	
	//Constructor for creating and setting up new user
	public SPUser (String username, String password) throws Exception {
		setUsername(username);
		setPassword(password);
	}
	
	// GETTERS AND SETTERS FOR ATTRIBUTTES
	
	//Username
	public void setUsername(String username) throws Exception {
		//Checks if email is valid and unique
		if(isValidUsername(username)) {
			this.username = username;
		} else {
			throw new IllegalArgumentException("Your username/email does not match our required format");
		}
	}
	protected boolean isValidUsername(String username) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(username);
		return matcher.find();
	}
	
	public String getUsername() {
		return username;
	}
	
	//Password
	protected void setPassword(String psw) {
		if (isValidPassword(psw)) {
			password = psw;
		} else {
			throw new IllegalArgumentException("Password requirements are: minimum eight letters with one uppercase and one lowercase letter, and one digit.");
		}
	}
	public boolean isValidPassword(String psw) {
		Matcher matcher = VALID_PASSWORD_REGEX.matcher(psw);
		return matcher.find();
	}
	public String getPassword() {
		return password;
	}
	
}
