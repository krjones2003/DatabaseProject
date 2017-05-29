package for_final_project;

import java.util.*;
import java.io.*;

/**
 * A BirthInfo object defines complete birthday information as a name plus a birthday,
 * where the name is a Name object and birthday is a BirthDate object.
 * @author Kristin Jones
 */
public class BirthInfo implements Serializable, Comparable<BirthInfo>{

	/** A class variable that references the inner class ChronComp 
	 * and orders BirthInfos by date using Comparable interface */ 
	public static final ChronComp DATE_COMPARATOR = new ChronComp();
	
	/** The instance variable representing the name of a BirthInfo. */
	private Name myName;
	
	/** The instance variable representing the birthday of a BirthInfo. */
	private BirthDate myBirthDate;
	
	
	/** 
	 * Creates a BirthInfo by assigning the three strings required 
	 * for a Name and the three ints required for a BirthDate. 
	 * @param theFirst The first name string assigned to myFirst
	 * @param theMiddle The middle name string assigned to myMiddle
	 * @param theFamily The family name string assigned to myFamily
	 * @param theMonth The month int assigned to myMonth
	 * @param theDay The day int assigned to myDay
	 * @param theYear The year int assigned to myYear
	 */
	public BirthInfo(String theFirst, String theMiddle, String theFamily, int theMonth, int theDay, int theYear){
		myName = new Name(theFirst, theMiddle, theFamily);
		myBirthDate = new BirthDate(theMonth, theDay, theYear);
	}//6-parameter constructor
	
	
	/** 
	 * Creates a BirthInfo by assigning the three strings required for a Name and 
	 * only two ints required for the month and day of a BirthDate. 
	 * @param theFirst  The first name string assigned to myFirst
	 * @param theMiddle  The middle name string assigned to myMiddle
	 * @param theFamily  The family name string assigned to myFamily
	 * @param theMonth  The month int assigned to myMonth
	 * @param theDay  The day int assigned to myDay
	 */
	public BirthInfo(String theFirst, String theMiddle, String theFamily, int theMonth, int theDay){
		myName = new Name(theFirst, theMiddle, theFamily);
		myBirthDate = new BirthDate(theMonth, theDay);
	}//5-parameter constructor
	
	
	/** 
	 * Creates a BirthInfo by assigning the three ints required for a BirthDate 
	 * and only the first and last name strings for a Name. 
	 * @param theFirst  The first name string assigned to myFirst
	 * @param theFamily  The family name string assigned to myFamily
	 * @param theMonth  The month int assigned to myMonth
	 * @param theDay  The day int assigned to myDay
	 * @param theYear  The year int assigned to myYear
	 */
	public BirthInfo(String theFirst, String theFamily, int theMonth, int theDay, int theYear){
		myName = new Name(theFirst, theFamily);
		myBirthDate = new BirthDate(theMonth, theDay, theYear);
	}//5-parameter constructor
	
	
	/** 
	 * Creates a BirthInfo by assigning the three strings required for a name and assigning the default
	 * value for a birthday using the 0-parameter BirthDate constructor. 
	 * @param theFirst  The first name string assigned to myFirst
	 * @param theMiddle  The middle name string assigned to myMiddle
	 * @param theFamily  The family name string assigned to myFamily
	 */
	public BirthInfo(String theFirst, String theMiddle, String theFamily) {
		myName = new Name(theFirst, theMiddle, theFamily);
		myBirthDate = new BirthDate();
	}//3-parameter constructor
	
	
	/** 
	 * Returns this BirthInfo's name.
	 * @return Returns the name of the BirthInfo. */
	public Name getName() {
		return myName;
	}
	
	/** 
	 * Returns this BirthInfo's birthday. 
	 * @return Returns the birthdate of this BirthInfo.
	*/
	public BirthDate getBirthDate(){
		return myBirthDate;
	}
	
	
	/**
	 * Sets the name of this BirthInfo.  Changes the value returned by calls to name getter.
	 * @param theName	The new name to be assigned.
	 */
	public void setName(Name theName) {
		myName = theName;
	}
	
	
	/**
	 * Sets the birthday of this BirthInfo.  Changes the value returned by calls to birthday getter.
	 * @param theBirthDate  The birthday to be assigned.
	 */
	public void setBirthDate(BirthDate theBirthDate){
		myBirthDate = theBirthDate;
	}
	
	
	 /** Returns a String representation of the BirthInfo in the form "First Middle Family, Month Day, Year",
	  * for example "Kristin Alise Jones, June 2, 1980" */
	@Override
	public String toString(){
		return myName.toString() + ", " + myBirthDate.toString(); 
	}
	
	
	/**
	 * Determines that two BirthInfo objects are equal iff the names are identical.
	 * @param theObject The BirthDate to which the invoking BirthDate is to be compared.
	 * @return Returns true if the BirthInfo parameter has the same name as the invoking BirthInfo.
	 */
	@Override
	public boolean equals(Object theObject) {
		BirthInfo thisBirthInfo = (BirthInfo)theObject;
		return myName.equals(thisBirthInfo.myName);
	}//equals
	
	
	/**
	 * Determines that one BirthInfo is alphabetically before another BirthInfo.  Compares two names using the Name compareTo method 
	 * to determine alphabetical order. 
	 * @param theBirthInfo	The BirthInfo to which the invoking BirthInfo is to be compared.
	 * @return Returns true if the invoking BirthInfo is chronologically before the parameter BirthInfo.
	 */
	public boolean beforeAlpha(BirthInfo theBirthInfo){
		return myName.compareTo(theBirthInfo.myName) < 0;
	}//beforeAlpha
	
	
	/**
	 * Determines that one BirthInfo is chronologically before another BirthInfo.  Compares two birthdays using the BirthDate compareTo method 
	 * to determine chronological order. 
	 * @param theBirthInfo	The BirthInfo to which the invoking BirthInfo is to be compared.
	 * @return Returns true if the invoking BirthInfo is chronologically before the parameter BirthInfo.
	 */
	public boolean beforeChron(BirthInfo theBirthInfo) {
		int myCompare = myBirthDate.compareTo(theBirthInfo.myBirthDate);
		if(myCompare < 0){
			return true;
		} else if (myCompare == 0) {
			if(this.beforeAlpha(theBirthInfo)) {
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}//beforeChron
	
	

	/**
	 * Details how the Comparable interface should compare two instances of BirthInfo.  
	 * Calls the compareTo method of the Name class.
	 * @param theBirthInfo	The BirthInfo instance to which the invoking BirthInfo is compared.
	 * @return Returns an int representing alphabetical order: -1 if the invoking name
	 * is before the parameter name, 0 if they are the same, or else
	 * 1 if the invoking name is after the parameter name.
	 */
	public int compareTo(BirthInfo theBirthInfo){
		return myName.compareTo(theBirthInfo.myName);
	}//compareTo
	
	
	/** 
	 * An inner class for a ChronComp object that orders BirthInfos 
	 * by the alternative, chronological ordering scheme of BirthDates
	 * using the Comparator interface
	 * @author Kristin Jones
	 */
	private static class ChronComp implements Comparator<BirthInfo>{
		public int compare(BirthInfo birthInfo1, BirthInfo birthInfo2) {
			return birthInfo1.myBirthDate.compareTo(birthInfo2.myBirthDate);
		}//compare
	}//ChronComp
	
}
