package for_final_project;

import java.util.*;
import java.io.*;

/**
 * A Name object defines a name as a first, middle, and family name,
 * where first, middle and family names are all of type string.
 * @author Kristin Jones
 */
public class Name implements Serializable, Comparable<Name>{

	/** The instance variable representing the first name of a Name. */
	private String myFirst;
	
	/** The instance variable representing the middle name of a Name. */
	private String myMiddle;
	
	/** The instance variable representing the last or family name of a Name. */
	private String myFamily;
	
	
	/** 
	 * Creates a Name by assigning the strings for first, middle, and family names. 
	 * @param theFirst The first name string assigned to myFirst
	 * @param theMiddle  The middle name string assigned to myMiddle
	 * @param theFamily  The family name string assigned to myFamily
	 */
	public Name (String theFirst, String theMiddle, String theFamily) {
		myFirst = theFirst;
		myMiddle = theMiddle;
		myFamily = theFamily;	
	}//3 parameter constructor
	
	
	/** 
	 * Creates a Name by assigning the strings for first and last names only, 
	 * leaving middle name empty. 
	 * @param theFirst  The first name string assigned to myFirst
	 * @param theFamily  The family name string assigned to myFamily
	 */
	public Name (String theFirst, String theFamily) {
		myFirst = theFirst;
		myMiddle = "";
		myFamily = theFamily;
	}//2 parameter constructor
	
	
	/** 
	 * Returns this Name's first name. 
	 * @return Returns string representation of first name.
	 * */
	public String getFirst() {
		return myFirst;
	}//getFirst
	
	
	/**
	 * Sets the first name of this Name.  Changes the value returned by calls to {@link #getFirst}.
	 * @param theFirst The string representing the first name.
	 */
	public void setFirst(String theFirst) {
		myFirst = theFirst;
	}//setFirst
	
	
	/** 
	 * Returns this Name's middle name.
	 * @return Returns string representation of middle name.
	 */
	public String getMiddle() {
		return myMiddle;
	}//getMiddle
	
	
	/**
	 * Sets the middle name of this Name.  Changes the value returned by calls to {@link #getMiddle}.
	 * @param theMiddle The string representing the middle name.
	 */
	public void setMiddle(String theMiddle) {
		myMiddle = theMiddle;
	}//setMiddle
	
	
	/** 
	 * Returns this Name's family name. 
	 * @return Returns string representation of family name.
	 */
	public String getFamily() {
		return myFamily;
	}//getFamily
	
	
	/**
	 * Sets the family name of this Name.  Changes the value returned by calls to {@link #getFamily}.
	 * @param theFamily The string representing the family name.
	 */
	public void setFamily(String theFamily) {
		myFamily = theFamily;
	}//setFamily
	
	
	/**
	 * Determines that two Names are equal iff the first, middle and family name strings are identical.
	 * @param theOther	The other Name to which the invoking name is to be compared.
	 * @return Returns a boolean if the invoking name is identical to the parameter name.
	 */
	@Override
	public boolean equals(Object theOther){
		Name theName = (Name)theOther;
		return myFirst.equals(theName.myFirst)
		&& myMiddle.equals(theName.myMiddle)
		&& myFamily.equals(theName.myFamily);
	}//equals
		
	
	/**
	 * Details how the Comparable interface should compare two instances of Name.  
	 * Calls the compareTo method of the Object class and returns the natural alphabetical ordering.
	 * @param theName	The Name instance to which the invoking Name instance is compared.
	 * @return Returns an int representing alphabetical order: -1 if the invoking name
	 * is before the parameter name, 0 if they are the same, or else
	 * 1 if the invoking name is after the parameter name.
	 */
	public int compareTo(Name theName){
		int compareFamily = myFamily.compareTo(theName.myFamily);
		if(compareFamily != 0) {
			return compareFamily;
		}//family names were different
		int compareFirst = myFirst.compareTo(theName.myFirst);
		if(compareFirst != 0) {
			return compareFirst;
		}//family names were equal and first names were different
			return myMiddle.compareTo(theName.myMiddle);
		}//compareTo
		
	
	/** 
	 * Returns a String representation of the Name in the form "First Middle Last",
	 * for example "Kristin Alise Jones" 
	 * @return Returns string representation of entire name. */
	@Override
	public String toString() {
		return myFamily + ", " + myFirst +
		(myMiddle.equals("") ? "" : " " + myMiddle);
	}//toString
	
	
}//Name
