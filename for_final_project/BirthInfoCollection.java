package for_final_project;


import java.util.*;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * A BirthInfoCollection object defines a collection of BirthInfos as an alphabetically ordered binary tree of BirthInfos
 * and a chronologically ordered binary tree of BirthInfos.
 * @author Kristin Jones
 */
public class BirthInfoCollection {
	
	/** The instance variable representing the alphabetically ordered binary tree of BirthInfos. */
	private TreeSet<BirthInfo> myAlphaList;
	
	/** The instance variable representing the chronologically ordered binary tree of BirthInfos. */
	private TreeSet<BirthInfo> myChronList;
	
	
	/** Creates a BirthInfoCollection by constructing one binary tree of alphabetically ordered 
	 * BirthInfos with the 0-parameter BirthInfo constructor and one binary tree of chronologically
	 * ordered BirthInfos with the 1-parameter BirthInfo constructor.
	 */
	public BirthInfoCollection() {
		myAlphaList = new TreeSet<BirthInfo>();
		myChronList = new TreeSet<BirthInfo> (BirthInfo.DATE_COMPARATOR);
	}//constructor
	
	/**
	 * A method to search for a given BirthInfo in the BirthInfoCollection.
	 * @param findMe The BirthInfo for which this method searches in the collection.
	 * @return Returns the found BirthInfo instance, or else null if not found.
	 */
	public BirthInfo search(BirthInfo findMe){
		if(!myAlphaList.contains(findMe)){
			return null;
		}//no matching BirthInfo object in the TreeSet
		Iterator< BirthInfo > it = myAlphaList.iterator();
		while (it.hasNext()){
			BirthInfo current = it.next();
			if(current.equals(findMe)){
				return current;
			}//if found a match
		}//while
		return null;
	}//search
	
	/**
	 * A method to add a new BirthInfo object to the collection.  
	 * @param addMe The BirthInfo to be added to the collection.
	 * @return Returns true if the BirthInfo was successfully added to collection, else false.
	 */
	public boolean add(BirthInfo addMe){
		if(myAlphaList.contains(addMe)){
			return false;
		}//duplicate name in Collection
		return myAlphaList.add(addMe) && myChronList.add(addMe);
	}//add
	
	
	/**
	 * A method to delete a BirthInfo object from the collection.
	 * @param deleteMe The BirthInfo to be deleted from the collection.
	 * @return Returns true if the BirthInfo was successfully deleted from the collection, else false.
	 */
	public boolean delete(BirthInfo deleteMe){
		BirthInfo found = search(deleteMe);
		if(found == null){
			return false;
		}//cannot delete because it is not in the collection
		return myAlphaList.remove(found) && myChronList.remove(found);
	}//delete
	
	
	/**
	 * A method to represent the collection's alphabetically ordered list as a String.
	 * @return Returns a string representation of the collection's alphabetically ordered list of BirthInfos.
	 */
	public String toStringAlphabetical(){
		return myAlphaList.toString();
	}//toStringAlphabetical

	
	/**
	 * A method to represent the collection's chronologically ordered list as a String.
	 * @return Returns a string representation of the collection's chronologically ordered list of BirthInfos.
	 */
	public String toStringChronological(){
		return myChronList.toString();
	}//toStringByAddress
	
	
	/**
	 * A method to save the contents of the alphabetically ordered list in the collection to a file.
	 * @param fileName The name of the file to be created.
	 * @return Returns the string returned by the caught Exception, or else empty string.
	 */
	public String saveToFile(String fileName){
		String messageFromSave = "";
		Iterator< BirthInfo > it = myAlphaList.iterator();
		ObjectOutputStream oOS;
		try{
			oOS = new ObjectOutputStream(new FileOutputStream(fileName));
			while (it.hasNext()){
				BirthInfo current = it.next();
				oOS.writeObject(current);
			}//while
			oOS.flush();
			oOS.close();
		}//try
		catch(Exception e) {
			messageFromSave = e.toString();
		}//catch
		return messageFromSave;
	}//saveToFile
		
	/**
	 * A method to load the alphabetically ordered list in the collection from a saved file to the application.
	 * @param fileName The name of the file that must be loaded.
	 * @return Returns the string warning from the caught Exception, or else a warning that contents of file 
	 * were not added, or else empty string.
	 */
	public String loadFromFile(String fileName){
		String toReturn = "";
		ObjectInputStream oIS; 
		try{
			oIS = new ObjectInputStream(
					new FileInputStream(fileName));
			while(true){
				BirthInfo fromFile = (BirthInfo) oIS.readObject();
				BirthInfo found = search(fromFile);
				if(found == null) {
					if(!add(fromFile)){
						toReturn += fromFile + "not added to DB.\n" ;
					}//if add failed
				} else {
					toReturn += fromFile + "not added because a duplicate name already in DB.\n";
				}//else
			}//while
		}//try
		catch (EOFException eOF){
		}//catch for all went well
		catch (Exception e){
			toReturn += e.toString() + "\n";
		}//catch anything else
		return toReturn;
	}//loadFromFile
	
	
}//BirthInfoCollection