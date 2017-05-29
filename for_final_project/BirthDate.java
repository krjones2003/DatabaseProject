package for_final_project;

import java.io.*;
import java.util.Comparator;

/**
 * A BirthDate object defines a birthday as a month, day, and year,
 * where month, day, and year are each of the type int.
 * @author Kristin Jones
 */
public class BirthDate implements Serializable, Comparable<BirthDate>{

	/** The instance variable representing the month of a BirthDate. */
	private int myMonth;
	
	/** The instance variable representing the day of a BirthDate. */
	private int myDay;
	
	/** The instance variable representing the year of a BirthDat.e */
	private int myYear; 
	
	/** A class variable to represent any month int as a string. */
	public static final String[] MONTHS = {"--", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", 
		"November", "December"};
	
	/** 
	 * Creates a BirthDate by assigning the ints for month, day, and year.
	 * @param theMonth	Month string to be assigned to myMonth.
	 * @param theDay  Day string to be assigned to myDay.
	 * @param theYear  Year string to be assigned to myYear.
	*/
	public BirthDate(int theMonth, int theDay, int theYear) {
		myMonth = theMonth;
		myDay = theDay;
		myYear = theYear;
	}//3-parameter constructor
	
	/** 
	 * Creates a BirthDate by assigning the given ints for month and day and assigning year to 0000. 
	 * @param theMonth  Month string to be assigned to myMonth.
	 * @param theDay  Day string to be assigned to myDay.
	 */
	public BirthDate(int theMonth, int theDay) {
		myMonth = theMonth;
		myDay = theDay;
		myYear = 0000;
	}//2-parameter constructor
	
	/** Creates a BirthDate by assigning 0 to month, 0 to day, and 0000 to year. */
	public BirthDate() {
		myMonth = 0;
		myDay = 0;
		myYear = 0000;
	}//0-parameter constructor
	
	/** 
	 * Returns this BirthDate's month.
	 * @return Returns an int representing the month.
	 */
	public int getMonth() {
		return myMonth;
	}//getMonth
	
	/** 
	 * Returns this BirthDate's day.
	 * @return Returns an int representing the day.
	 */
	public int getDay() {
		return myDay;
	}//getDay

	/** 
	 * Returns this BirthDate's year.
	 * @return Returns an int representing the year. 
	 */
	public int getYear() {
		return myYear;
	}//getYear

	/** 
	 * Sets the month of this BirthDate.  Changes the value returned by calls to month getter.
	 * Rejects any month value less than 1 or greater than 12, sets the highest day value to 29 for the month of February, 
	 * and sets the highest day value to 30 for the months of April, June, September and November.
	 * @param theMonth	The new month for the BirthDate.
	 */
	public void setMonth(int theMonth) {
		if(theMonth < 1 || theMonth > 12) {
			System.out.println("Invalid value for the month.");
		}else{
			myMonth = theMonth;
			if(theMonth == 2 && myDay > 29){
				myDay = 29;//set day to the maximum valid day for February
			}//if
			if((theMonth == 4 || theMonth == 6 || theMonth == 9 || theMonth == 11) && myDay > 30){
				myDay = 30;//set day to the maximum valid day for April, June, September and November
			}//if
		}//else
	}//setMonth

	
	/**
	 * Sets the day of this BirthDate.  Changes the value returned by calls to day getter.
	 * Rejects any day value less than 0 or greater than 31, and rejects day values greater than 29 for February
	 * and rejects day values greater than 30 for April, June, September and November.
	 * @param theDay	The new day for the BirthDate.
	 */
	public void setDay(int theDay) {
		if(theDay < 0 || theDay > 31) {
			System.out.println("Day is out of bounds.");
		}else if(myMonth == 2 && theDay > 29) {
			System.out.println("Invalid day for the month of February.");
		}else if (theDay > 30 && (myMonth == 4 || myMonth == 6 || myMonth == 9 || myMonth == 11)) {
			System.out.println("Invalid day for the given month.");
		}else{
			myDay = theDay;
		}//else
	}//setDay
	
	
	/**
	 * Sets the year of this BirthDate.  Changes the value returned by calls to year getter.
	 * @param theYear  The new year for the BirthDate.
	 */
	public void setYear(int theYear) {
		myYear = theYear;
	}//setYear
	
	
	/**
	 * Determines that two BirthDate objects are equal iff they share the same day, month, and year.
	 * @param theObject The BirthDate to which the invoking BirthDate is to be compared.
	 * @return Returns true if the BirthDate parameter is the same as the invoking BirthDate.
	 */
	@Override
	public boolean equals(Object theObject) {
		BirthDate myBirthDate = (BirthDate)theObject;
		return myDay == myBirthDate.myDay && myMonth == myBirthDate.myMonth && myYear == myBirthDate.myYear;	
	}//equals
	
	
	/**
	 * Determines that one BirthDate is chronologically before another BirthDate.  Compares year, month, and day 
	 * to determine temporal order. 
	 * @param theBirthDate	The BirthDate to which the invoking BirthDate is to be compared.
	 * @return Returns true if the invoking BirthDate is chronologically before the parameter BirthDate.
	 */
	public boolean before(BirthDate theBirthDate) {
		if(myYear < theBirthDate.myYear){
			return true;
		} else if (myYear == theBirthDate.myYear) {
			if(myMonth < theBirthDate.myMonth) {
				return true;
			} else if (myMonth == theBirthDate.myMonth) {
				if(myDay < theBirthDate.myDay) {
					return true;
				} else {
					return false;
				}//else
			}//else if
		}//else if 
		return false;
	}//before
	
	
	/**
	 * Details how the Comparable interface should compare two instances of BirthDate.  
	 * Calls the before method of this class.
	 * @param theBirthDate	The BirthDate instance to which the invoking BirthDate is compared.
	 * @return Returns an int representing chronological order: -1 if the invoking birthday
	 * is before the parameter BirthDate, 0 if they are the same, or else
	 * 1 if the invoking BirthDate is after the parameter BirthDate.
	 */
	public int compareTo(BirthDate theBirthDate) {
		if(this.before(theBirthDate)){
			return -1;
		}else if (this.equals(theBirthDate)) {
			return 0;
		}else{
			return 1;
		}
	}//compareTo
	
	/**
	 * Converts the int value of the month to its String representation, 
	 * for example, 2 would return as February.
	 * @param theMonth	The int representation of the month
	 * @return Returns a String representation of the month
	 */
	public String convertMonthToString(int theMonth) {
		String toReturn = "";
		if(theMonth == 1) {
			toReturn = "January";
		}else if (theMonth == 2) {
			toReturn = "February";
		}else if (theMonth == 3) {
			toReturn = "March";
		}else if (theMonth == 4) {
			toReturn = "April";
		}else if (theMonth == 5) {
			toReturn = "May";
		}else if (theMonth == 6) {
			toReturn = "June";
		}else if (theMonth == 7) {
			toReturn = "July";
		}else if (theMonth == 8) {
			toReturn = "August";
		}else if (theMonth == 9) {
			toReturn = "September";
		}else if (theMonth == 10) {
			toReturn = "October";
		}else if (theMonth == 11) {
			toReturn = "November";
		}else if (theMonth == 12) {
			toReturn = "December";
		}else {
			toReturn = "Unknown";
		}
		return toReturn;	
	}//convertMonthToString
		

	 /** Returns a String representation of the BirthDate in the form "Month Day, Year",
	  * for example "February 2, 1980" */
	@Override
	public String toString(){
		String theMonth = convertMonthToString(myMonth);
		String toReturn = "";
		if(myYear == 0000 && myMonth != 0) {
			toReturn = theMonth + " " + myDay;
		}
		if(myYear != 0000 ){
			toReturn = theMonth + " " + myDay + ", " + myYear;
		}
		return toReturn;
	}//toString
	
	
}
