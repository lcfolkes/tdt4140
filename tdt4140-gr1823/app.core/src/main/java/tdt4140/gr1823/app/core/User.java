package tdt4140.gr1823.app.core;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.*;

public class User {
	
	//Attributes
	public static int numberOfUsers = 0;
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE); 
	public static final Pattern VALID_PASSWORD_REGEX = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$");
	
	private int ID;
	private String name; //
	private LocalDate b_Date; //er på formatet til localDate(year, month, day)
	private Gender gender; //getGender and in constructor
	private String email; //getEmail and setEmail with encapsulation
	private String username; //get and set
	private String password; //setter with encapsulation and getter
	
	//CONSTRUCTORS
	
	//Constructor for creating and setting up new user
	public User (String name, LocalDate b_Date, Gender gender, String email, String username,
			String password) throws Exception {
		this.name = name;
		this.b_Date = b_Date;
		this.gender = gender;
		setEmail(email);
		setUsername(username);
		setPassword(password);
	}
	
	// Constructor for setting up temporary user.
	//Difference is in password and takes in ID
	public User(int ID, String name, LocalDate dateOfBrith, Gender gender, String email, String username) {
		super();
		this.ID = ID;
		this.name = name;
		this.b_Date = dateOfBrith;
		this.gender = gender;
		this.email = email;
		this.username = username;
	}
	
	// GETTERS AND SETTERS FOR ATTRIBUTTES
	
	//ID
	public int getID() {
		return ID;
	}

	//Name
	public String getName() {
		return name;
	}
	
	//Age
	public int getAge() {
		LocalDate now = LocalDate.now();
		Period age = Period.between(b_Date, now);
		return age.getYears();
	}
	
	//B_Date
		public LocalDate getb_Date() {
			return b_Date;
		}
		
	//Gender
	public Gender getGender() {
		return gender;
	}
	
	//Email
	public void setEmail(String email) throws Exception {
		//Checks if email is valid and unique
		if(isValidEmail(email)) {
			this.email = email;
		} else {
			throw new IllegalArgumentException("Your Email does not match our required format");
		}
	}
	protected boolean isValidEmail(String string) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(string);
		return matcher.find();
	}
	public String getEmail() {
		return email;
	}
	
	//Username
	protected void setUsername(String username) throws Exception {
		if (isValidUsername(username)) {			
			this.username = username;
		}
	}
	protected boolean isValidUsername(String uname) { //TODO
		return true;
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
