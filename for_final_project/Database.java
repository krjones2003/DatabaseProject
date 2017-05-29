package for_final_project;

import java.awt.*;
import java.awt.event.*;
import java.io.*; // to get the File class

import javax.swing.*;

/**
 * A Database object is a graphical user interface for uploading name and birthday information into a database.
 * @author Kristin Jones
 */
public class Database extends JFrame { 

	/** An object of class BirthInfoCollection */
	private BirthInfoCollection myAICollection;
	
	/** Each new BirthInfo record created in the database */
	private BirthInfo record;
	
	/** The record created to confirm deletion of a record */
	private BirthInfo recordDelete;
	
	/** The instructions label for application */
	private JLabel instructionsL;

	/** The button that adds BirthInfo to the database */
	private JButton addB; 
	
	/** The button that displays BirthInfo to the user */
	private JButton displayB; 
	
	/** This button sorts BirthInfo by name alphabetically and displays it */
	private JButton alphabetSortDisplayB; 
	
	/** This button displays BirthInfos */
	private JButton chronSortDisplayB; 
	
	/** This button searches for BirthInfo */
	private JButton searchB; 
	
	/** This button deletes BirthInfo */
	private JButton deleteB; 
	
	/** This button confirms that user does indeed wish to delete */
	private JButton confirmDeleteB; 
	
	/** This button cancels an accidental click of the delete button */
	private JButton cancelDeleteB;
	
	/** This text area displays the BirthInfo back to the user */
	private JTextArea textAreaTA;
	
	/** This scroll pane allows user to scroll down the text area */
	private JScrollPane scrollPaneSP;
	
	/** This textfield displays user's error messages to the user */
	private JTextArea errorMessageTA;
	
	/** This textfield allows user to enter first name */
	private JTextField firstNameTF;
	
	/** This textfield allows user to enter middle name */
	private JTextField middleNameTF;
	
	/** This textfield allows user to enter last name */
	private JTextField familyNameTF;
	
	/** This combo box gives a list of month abbreviations */
	private JComboBox monthCB;
	
	/** This combo box gives a list of day abbreviations */
	private JTextField dayTF;
	
	/** This textfield allows user to enter year */
	private JTextField yearTF;
	
	/** Label for error message */
	private JLabel errorMessageL;
	
	/** Label for first name textfield */
	private JLabel firstNameL;
	
	/** Label for middle name textfield */
	private JLabel middleNameL;
	
	/** Label for family name textfield */
	private JLabel familyNameL;
	
	/** Label for month combobox */
	private JLabel monthL;
	
	/** Label for month combobox */
	private JLabel dayL;
	
	/** Label for year textfield */
	private JLabel yearL;
	
	/** A reference to the content pane of the JFrame */
	private Container myCP;
	
	/** Text area for displaying added BirthInfo to user */
	private JTextArea birthInfoTA;
	
	/** Scroll pane for the text area */
	private JScrollPane birthInfoSP;
	
	/** Label for file upload field */
	private JLabel fileNameL;
	
	/** Textfield for entering new file */
	private JTextField myFileTF;
	
	/** Button for loading a new file */
	private JButton myFileLoadB;
	
	/** Button for saving file */
	private JButton myFileSaveB;
	
	/** Button for okaying writing to file */
	private JButton myFileOkB;
	
	/** Button for canceling writing to file */
	private JButton myFileCancelB;
	
	/** Set to true when awaiting OK or Cancel during a Save to file operation */
	private boolean processingSave;
	
	/** Set to true when awaiting OK or Cancel during a new data Enter operation */
	private boolean duplicateRecord;
	
	/** Set to true when awaiting OK or Cancel during a delete operation */
	private boolean processingDelete;
	
	/** Refers to a file name being processed by a save or load command */
	private String fileName;
	
	/**
	 * Creates a Database by constructing all graphical user interface and back end components, and adding
	 * front end components to the content pane.
	 */
	public Database () {

		super("Database Application");
		setSize(800, 700);
		setLocation(100,100);
		myCP = getContentPane();
		myCP.setLayout(null);//there is no layout manager
		myCP.setBackground(Color.GRAY);

		//construct all front end components
		constructAllGUIComponents();
		
		//construct back end components	
		myAICollection = new BirthInfoCollection();
		
		// makes the window visible on the screen
		setVisible(true);
	}

	
	/**
	 * A method to construct all labels, buttons, text fields and text areas of the GUI.
	 * Uses the utility methods of class UtilityMethods.
	 */
	private void constructAllGUIComponents() {
		
		instructionsL = UtilityMethods.makeLabel(100, 80, 500, 30, "Enter name and birthdate.  Middle name and birth year are optional.", Color.BLACK, myCP);
		
		firstNameL = UtilityMethods.makeLabel(100, 120, 50, 30, "First:", Color.BLACK, myCP);
		firstNameTF = UtilityMethods.makeTextField(150,120, 100, 30, Color.BLACK, myCP);
		middleNameL = UtilityMethods.makeLabel(300, 120, 50, 30, "Middle:", Color.BLACK, myCP);
		middleNameTF = UtilityMethods.makeTextField(350, 120, 100, 30, Color.BLACK, myCP);
		familyNameL = UtilityMethods.makeLabel(500, 120, 50, 30, "Last:", Color.BLACK, myCP);
		familyNameTF = UtilityMethods.makeTextField(550, 120, 100, 30, Color.BLACK, myCP);		
		
		monthL = UtilityMethods.makeLabel (100, 175, 50, 30, "Month:", Color.BLACK, myCP);		
		monthCB = UtilityMethods.makeComboBox (150, 175, 100, 30,  Color.BLACK, myCP);		
		dayL = UtilityMethods.makeLabel(300, 175, 50, 30, "Day:", Color.BLACK, myCP);		
		dayTF = UtilityMethods.makeTextField(350, 175, 100, 30, Color.BLACK, myCP);	
		yearL = UtilityMethods.makeLabel(500, 175, 50, 30,  "Year:", Color.BLACK, myCP);		
		yearTF = UtilityMethods.makeTextField(550, 175, 100, 30,  Color.BLACK, myCP);		
	
		addB = UtilityMethods.makeButton(150, 230, 100, 30, "Add", Color.BLACK, (ActionEvent e) -> stepsForAdd(), myCP);
		searchB = UtilityMethods.makeButton(100, 285, 100, 30, "Search", Color.BLACK,(ActionEvent e) -> stepsForSearch(), myCP);
		deleteB = UtilityMethods.makeButton(200, 285, 100, 30, "Delete", Color.BLACK,(ActionEvent e) -> stepsForDelete(), myCP);
		confirmDeleteB = UtilityMethods.makeButton(100, 340, 200, 30, "Confirm Delete", Color.BLACK,(ActionEvent e) -> stepsForConfirmDelete(), myCP);
		cancelDeleteB = UtilityMethods.makeButton(100, 395, 200, 30, "Cancel Delete", Color.BLACK,(ActionEvent e) -> stepsForCancelDelete(), myCP);
		
		
		errorMessageL = UtilityMethods.makeLabel(350, 450, 100, 30,  "Error:", Color.BLACK, myCP);
		errorMessageTA = UtilityMethods.makeTextArea(400, 450, 250, 30, Color.BLACK, myCP);
		
		alphabetSortDisplayB = UtilityMethods.makeButton(120, 505, 230, 30, "Alphabetical Order Display", Color.BLACK, (ActionEvent e) -> stepsForAlpha(), myCP);
		chronSortDisplayB = UtilityMethods.makeButton(370, 505, 230, 30, "Chronological Order Display", Color.BLACK, (ActionEvent e) -> stepsForChronDisplay(), myCP);
		
		
		birthInfoTA = new JTextArea();
		birthInfoSP = new JScrollPane(birthInfoTA);
		birthInfoSP.setLocation(350, 230);
		birthInfoSP.setSize(300,200);
		myCP.add(birthInfoSP);		
	
		
		fileNameL = UtilityMethods.makeLabel(200, 545, 70, 30,  "File Name:", Color.BLACK, myCP);
		
		myFileTF = UtilityMethods.makeTextField(275, 545, 250, 30, Color.BLACK, myCP);
		
		myFileLoadB = UtilityMethods.makeButton(120, 585, 100, 30, "Load", Color.BLACK, (ActionEvent e) -> stepsForLoad(), myCP);
		
		myFileSaveB = UtilityMethods.makeButton(235, 585, 100, 30, "Save", Color.BLACK, (ActionEvent e) -> stepsForSave(), myCP);
		
		myFileOkB = UtilityMethods.makeButton(350, 585, 100, 30, "OK", Color.BLACK, (ActionEvent e) -> stepsForOK(), myCP);
		myFileOkB.setEnabled(false);
		myFileCancelB = UtilityMethods.makeButton(465, 585, 100, 30, "Cancel", Color.BLACK, (ActionEvent e) -> stepsForCancelLoad(), myCP);
		myFileCancelB.setEnabled(false);
		processingSave = false; //not processing a save yet
		duplicateRecord = false; //not processing a new entry yet
		processingDelete = false; //not processing a delete yet
		
	}//constructAllGUIComponents
	
	
	/**
	 * The method called when the Load button is pressed.  Calls the loadFromFile method in 
	 * class BirthInfoCollection.
	 */
	private void stepsForLoad(){
		fileName = myFileTF.getText();
		if(fileName.compareTo("") > 0){
			File theFile = new File(fileName);
			if(!theFile.exists()) {
				errorMessageTA.setText(fileName + " does not exist—cannot load data\n");
				birthInfoTA.setText("");
			}else if(!theFile.canRead()) {
				errorMessageTA.setText("Cannot read from " + fileName+"\n");
				birthInfoTA.setText("");
			}else if(theFile.isDirectory()) {
				errorMessageTA.setText("Error: " + fileName+" is a directory.");
				birthInfoTA.setText("");
			} else {
				String fromLoad = myAICollection.loadFromFile(fileName);
				birthInfoTA.setText("Data loaded from "+fileName+"\n" + fromLoad + "\n");
				errorMessageTA.setText("");
		}//else
		clearInputFields();
		} else {
			errorMessageTA.setText("You must enter a file name " +
					"in order to load a file");
			birthInfoTA.setText("");
		}//else
	}//stepsForLoad
	
	/**
	 * The method called when the Save button is pressed.  Calls the saveFromFile method
	 * in the BirthInfoCollection class.
	 */
	private void stepsForSave(){
		fileName = myFileTF.getText();
		myFileTF.setText("");
		String message = "";
		String message2 = "";
		if(fileName.compareTo("") > 0){
			File theFile = new File(fileName);
			if(!theFile.exists()) {
				message = myAICollection.saveToFile(fileName);
				birthInfoTA.setText("Data saved to file "+fileName+".\n"
						+ message + "\n");
			} else if (!theFile.canWrite()){
				errorMessageTA.setText("Error: " + fileName + " is locked and cannot be overwritten.\n");
			} else {
				adjustButtons(false);
				processingSave = true;
				birthInfoTA.setText("\nPress OK to overwrite file " +
						fileName + " or press Cancel to cancel save request\n");
			}//else
		} else {
			errorMessageTA.setText("You must enter a file name in order to save a file");
		}//else
		
	}//stepsForSave
	
	/**
	 * The method called when the user hits the OK button to signal it is ok to save
	 * BirthInfos to a file.  Calls the saveToFile method from the class BirthInfoCollection.
	 */
	private void stepsForOK(){
		if(processingSave) {
			String errmsg = myAICollection.saveToFile(fileName);
			errorMessageTA.setText("");
			birthInfoTA.setText(fileName + " over written.\n" + errmsg + "\n");
			processingSave = false;
		}else {
			System.out.println("OK Button being handled at inappropriate time");
		}//else
		reset();
	}//stepsForOK
	
	
	/**
	 * The method called when the user hits the Cancel button to signal it is not
	 * ok to load BirthInfos into a file.
	 */
	private void stepsForCancelLoad(){
		if(processingSave) {
			errorMessageTA.setText("");
			birthInfoTA.setText("Save request cancelled. "+ fileName + " unchanged.\n");
			processingSave = false;
		} else {
			System.out.println("Cancel Button being handled at inappropriate time");
		}//else
		reset();
	}//stepsForCancelLoad
	
	
	/**
	 * The method called when the user hits the Chronological Sort Display button.
	 * Calls the chronological toString method from the BirthInfoCollection class.
	 */
	private void stepsForChronDisplay(){
		errorMessageTA.setText("");
		birthInfoTA.setText(myAICollection.toStringChronological());
	}//stepsForChronDisplay
	

	/** 
	 * A utility method that converts the string format of month, given in the dropdown 
	 * menu facing the user, to an int that can be processed by the BirthDate class.
	 * @param theMonth The string representation that must be turned into an int
	 * @return Returns an int representing the number of the month.
	 */
	private int convertMonthToInt(String theMonth) {
		int toReturn = 0;
		if(theMonth.equals("January")) {
			toReturn = 1;
		}else if (theMonth.equals("February")) {
			toReturn = 2;
		}else if (theMonth.equals("March")) {
			toReturn = 3;
		}else if (theMonth.equals("April")) {
			toReturn = 4;
		}else if (theMonth.equals("May")) {
			toReturn = 5;
		}else if (theMonth.equals("June")) {
			toReturn = 6;
		}else if (theMonth.equals("July")) {
			toReturn = 7;
		}else if (theMonth.equals("August")) {
			toReturn = 8;
		}else if (theMonth.equals("September")) {
			toReturn = 9;
		}else if (theMonth.equals("October")) {
			toReturn = 10;
		}else if (theMonth.equals("November")) {
			toReturn = 11;
		}else if (theMonth.equals("December")) {
			toReturn = 12;
		}else {
			toReturn = 13;
		}
		return toReturn;	
	}//convertMonthToString
	
	
	/**
	 * The method called when the user hits the add button.
	 * Adds a new BirthInfo to the database.
	 */
	private void stepsForAdd(){
		//get inputs from the textfields
		//only convert strings to ints if the field is not empty
		String theFirst = firstNameTF.getText();
		String theMiddle = middleNameTF.getText();
		String theLast = familyNameTF.getText();
		String theMonthString = monthCB.getSelectedItem().toString();
		int theMonth = convertMonthToInt(theMonthString);
		String theDayString = dayTF.getText();
		int theDay = 0;
		if(!theDayString.equals("")){
			theDay = Integer.parseInt(theDayString);
		}
		String theYearString = yearTF.getText();
		int theYear = 0;
		if(!theYearString.equals("")){
			theYear = Integer.parseInt(theYearString);
		}
		
		//User must provide at least a first and last name
		if(theFirst.equals("") || theLast.equals("")) {
			if(theFirst.equals(""))	{
				errorMessageTA.setText("Please provide a first name.");
			}
			if(theLast.equals("")) {
				errorMessageTA.setText("Please provide a last name.");
			}
			if(theFirst.equals("") && theLast.equals("")){
				errorMessageTA.setText("Please provide a first and last name.");
			}
			} else if(theDay <= 0 || theDay > 31) {//protext against impossible day inputs
					errorMessageTA.setText("Day is out of bounds.");
					dayTF.setText("");
			} else if(theMonth == 2 && theDay > 29) {
					errorMessageTA.setText("Invalid day for the month of February.");//protect against days over 29 in February
					dayTF.setText("");
			} else if (theDay > 30 && (theMonth == 4 || theMonth == 6 || theMonth == 9 || theMonth == 11)) {//protect against dats over 30
					errorMessageTA.setText("Invalid day for the given month.");
					dayTF.setText("");
			} else if(!theDayString.equals("") && (theMonth != 13)  && theYearString.equals("")){//user gave month and day but not year
				errorMessageTA.setText("");
				birthInfoTA.setText("");
				record = new BirthInfo(theFirst, theMiddle, theLast, theMonth, theDay);//...so construct a record without a year
					if(myAICollection.search(record) == null) {//check to make sure it's not already there
						myAICollection.add(record);
					} else {
						errorMessageTA.setText("That record already exists.");
					}
				firstNameTF.setText("");
				middleNameTF.setText("");
				familyNameTF.setText("");
				monthCB.setSelectedIndex(0);
				dayTF.setText("");
				yearTF.setText("");
			}else if (theMonth != 13 && theDayString.equals("")) {//user gave a month, but not a day
				errorMessageTA.setText("Please provide a day.");
			}else if (!theDayString.equals("") && theMonth == 13) {//user gave a day, but not a month
				errorMessageTA.setText("Please provide a month.");
			}else if (!theYearString.equals("") && theMonth == 13 && !theDayString.equals("")){//user gave a year and a day but no month
				errorMessageTA.setText("Please provide a month.");
			}
			else if(!theYearString.equals("") && theDayString.equals("") && theMonth!=13){//user gave a year and a month, but no day
				errorMessageTA.setText("Please provide a day.");
			}
			else if(!theYearString.equals("") && theDayString.equals("") && theMonth==13){//user gave a year but not a month or day
				errorMessageTA.setText("Please provide a day and month.");
			}
			
			else{//everything is good, so construct a normal record and clear all the fields
				errorMessageTA.setText("");
				birthInfoTA.setText("");
				record = new BirthInfo(theFirst, theMiddle, theLast, theMonth, theDay, theYear);
		
					if(myAICollection.search(record) == null) {//check to make sure record isn't already there
						myAICollection.add(record);	
					} else {
						errorMessageTA.setText("That record already exists.");
					}
			firstNameTF.setText("");
			middleNameTF.setText("");
			familyNameTF.setText("");
			monthCB.setSelectedIndex(0);
			dayTF.setText("");
			yearTF.setText("");
			}
	}//stepsForAdd
	
	/**
	 * The method called when the user hits the Alphabetical Sort and Display button.
	 * Calls the alphabetical toString method from the BirthInfoCollection class.
	 */
	private void stepsForAlpha(){
		errorMessageTA.setText("");
		birthInfoTA.setText(myAICollection.toStringAlphabetical());
	}//stepsForAlpha
	
	
	/**
	 * The method called when the user hits the Delete button.
	 * Prompts the user to enter name information correctly, if needed,
	 * and to confirm the intent to delete the record.
	 */
	private void stepsForDelete(){
		String theFirst = firstNameTF.getText();
		String theMiddle = middleNameTF.getText();
		String theLast = familyNameTF.getText();
		if(theFirst.equals("") || theLast.equals("")){
			errorMessageTA.setText("Please provide a first, middle, and last name.  If person has no middle name, leave that field blank.");
		}else{
			recordDelete = new BirthInfo(theFirst, theMiddle, theLast);
		}//create a new record using just name-based BirthInfo constructor
		BirthInfo myBirthInfo = myAICollection.search(recordDelete);
		if(myBirthInfo == null && theMiddle.equals("")) {
			birthInfoTA.setText(recordDelete.toString() + " was not found in the database. \n"
					+ "If original entry included a middle name, please search again using the middle name.");
		}else if (myBirthInfo == null && !theMiddle.equals("")) {
			birthInfoTA.setText(recordDelete.toString() + " was not found in the database.");
			firstNameTF.setText("");
			middleNameTF.setText("");
			familyNameTF.setText("");
		} else {
			birthInfoTA.setText(myBirthInfo.toString() + " is in the database. \n"
					+ "Do you really want to remove this record? \n"
					+ "If yes, please press Confirm Delete.  If no, please press Cancel Delete.");
		}
	}//stepsForDelete
	
	/**
	 * The method that is called when the user confirms intent to delete a record.
	 * Calls the delete method in the class BirthInfoCollection.
	 */
	private void stepsForConfirmDelete(){
		boolean myDelete = myAICollection.delete(recordDelete);
		if(myDelete){
			birthInfoTA.setText(recordDelete.toString() + " was deleted from the database.");
		}else {
			birthInfoTA.setText(recordDelete.toString() + " was not deleted from the database.");
		}	
		firstNameTF.setText("");
		middleNameTF.setText("");
		familyNameTF.setText("");
	}//stepsForConfirmDelete
	
	
	/**
	 * The method called when the user hits the Cancel button.
	 * Sets textfields to empty text and does not proceed to actual deletion of record.
	 */
	private void stepsForCancelDelete(){
		birthInfoTA.setText(recordDelete.toString() + " was not deleted from the database.");
		firstNameTF.setText("");
		middleNameTF.setText("");
		familyNameTF.setText("");
	}//stepsForCancelDelete
	
	
	/**
	 * The method called when the user hits the search button.
	 * Searches for the record in the collection using the BirthInfo's name value only.
	 * Calls the search method from the class BirthInfoCollection.
	 */
	private void stepsForSearch() {
		String theFirst = firstNameTF.getText();
		String theMiddle = middleNameTF.getText();
		String theLast = familyNameTF.getText();
		if(theFirst.equals("") || theLast.equals("")){
			errorMessageTA.setText("Please provide a first, middle, and last name.  If person has no middle name, leave that field blank.");
		}else{
			record = new BirthInfo(theFirst, theMiddle, theLast);
		}//create a new record using just name-based BirthInfo constructor
		BirthInfo myBirthInfo = myAICollection.search(record);
		if(myBirthInfo == null && theMiddle.equals("")) {
			birthInfoTA.setText(record.toString() + " was not found in the database. \n"
					+ "If original entry included a middle name, please search again using the middle name.");
		}else if (myBirthInfo == null && !theMiddle.equals("")) {
			birthInfoTA.setText(record.toString() + " was not found in the database.");
		} else {
			birthInfoTA.setText(myBirthInfo.toString() + " is in the database.");
		}
		firstNameTF.setText("");
		middleNameTF.setText("");
		familyNameTF.setText("");
	}//stepsForSearch
	
	/**
	 * A utility method to set buttons to enabled or disabled, as desired, upon 
	 * opening of application.
	 * @param tFValue The boolean value that will change the enabled setting of the button
	 */
	private void adjustButtons(boolean tFValue) {
		addB.setEnabled(tFValue);
		deleteB.setEnabled(tFValue);
		displayB.setEnabled(tFValue);
		searchB.setEnabled(tFValue);
		confirmDeleteB.setEnabled(tFValue);
		cancelDeleteB.setEnabled(tFValue);
		alphabetSortDisplayB.setEnabled(tFValue);
		chronSortDisplayB.setEnabled(tFValue);
		myFileLoadB.setEnabled(tFValue);
		myFileOkB.setEnabled(!tFValue);
		myFileCancelB.setEnabled(!tFValue);
		}//adjustButtons
	
	/**
	 * A utility method that calls the adjustButtons method of this class with the parameter true.
	 */
	private void reset() {
		adjustButtons(true);
		}//reset
	
	/**
	 * A utility method to clear all input fields.
	 * Sets text to empty string.
	 */
	private void clearInputFields() {
		familyNameTF.setText("");
		firstNameTF.setText("");
		middleNameTF.setText("");
		dayTF.setText("");
		yearTF.setText("");
		myFileTF.setText("");
		}//clearInputFields

	
	/**
	 * The main method of the Database class.  Constructs an instance of
	 * the Database class, and makes the close button in fact close the program.
	 */
	public static void main(String args[]) {
		
		// create an object of your class
		Database myAppF = new Database ();
		
		// make the close button close the program
		myAppF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}