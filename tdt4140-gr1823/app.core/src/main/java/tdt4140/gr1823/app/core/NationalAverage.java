package tdt4140.gr1823.app.core;

public class NationalAverage {

public static int average;

private int[] NUMBERS = {1, 24, 45, 62, 85, 8, 91, 3, 5, 56, 9};

public void calculateNationalAverage() {
	    int total = 0;
	    for (int element : NUMBERS) {
	        total += element;
	    }
	    total = total / NUMBERS.length;
	this.average = total;
	}

public int getAverage() {
	return this.average;
}

public static void main(String[] args) {
	NationalAverage a = new NationalAverage();
	System.out.println(a.getAverage());
}
}

