package tdt4140.gr1823.app.core;

import java.time.LocalDate;

public class DailyActivity {
	
	private User user;
	private int steps;
	private LocalDate date;
	
	public DailyActivity(User user,int steps, LocalDate date) {
		this.setUser(user);
		this.setSteps(steps);
		this.setDate(date);
		
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
}
