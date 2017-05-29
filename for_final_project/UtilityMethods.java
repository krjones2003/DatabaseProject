package for_final_project;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * A UtilityMethods object makes five methods available to the Database class for creation of GUI elements.
 * @author Kristin Jones
 */
public class UtilityMethods {

	/**
	 * A method to create a JButton with the given parameters.
	 * @param theX  The position along the x-axis at which element should appear on screen.
	 * @param theY  The position along the y-axis at which element should appear on screen.
	 * @param theWidth  The pixel width of the element on the screen.
	 * @param theHeight  The pixel height of the element on the screen.
	 * @param theText  The text to appear on the element.  
	 * @param theForeground  The color of the foreground.  
	 * @param theHandler  The action to be performed by clicking on the element.
	 * @param theCP  The content pane to which the element must be added.
	 * @return	Returns the JButton created with these parameters.
	 */
	public static JButton makeButton(int theX, int theY, int theWidth, int theHeight, String theText, Color theForeground, ActionListener theHandler, Container theCP){
		JButton toReturn = new JButton(theText);
		toReturn.setSize(theWidth, theHeight);
		toReturn.setLocation(theX, theY);
		toReturn.setForeground(theForeground);
		theCP.add(toReturn);
		toReturn.addActionListener(theHandler);
		return toReturn;
	}//makeButton

	/**
	 * A method to create a JTextField with the given parameters.
	 * @param theX  The position along the x-axis at which element should appear on screen.
	 * @param theY  The position along the y-axis at which element should appear on screen.
	 * @param theWidth  The pixel width of the element on the screen.
	 * @param theHeight  The pixel height of the element on the screen.
	 * @param theForeground  The color of the foreground.  
	 * @param theCP  The content pane to which the element must be added.
	 * @return Returns the JTextField created with these parameters. 
	 */
	public static JTextField makeTextField(int theX, int theY, int theWidth, int theHeight, Color theForeground, Container theCP) {
		JTextField toReturn = new JTextField(20);
		toReturn.setSize(theWidth, theHeight);
		toReturn.setLocation(theX, theY);
		toReturn.setForeground(theForeground);
		theCP.add(toReturn);
		//toReturn.addActionListener(theHandler);
		return toReturn;
	}//makeTextField
	
	
	/**
	 * A method to create a JLabel with the given parameters.
	 * @param theX  The position along the x-axis at which element should appear on screen.
	 * @param theY  The position along the y-axis at which element should appear on screen.
	 * @param theWidth  The pixel width of the element on the screen.
	 * @param theHeight  The pixel height of the element on the screen.
	 * @param theText  The text to appear on the element.  
	 * @param theForeground  The color of the foreground.  
	 * @param theCP  The content pane to which the element must be added.
	 * @return Returns the JLabel created with these parameters.
	 */
	public static JLabel makeLabel(int theX, int theY, int theWidth, int theHeight, String theText, Color theForeground, Container theCP) {
		JLabel toReturn = new JLabel(theText);
		toReturn.setSize(theWidth, theHeight);
		toReturn.setLocation(theX, theY);
		toReturn.setForeground(theForeground);
		theCP.add(toReturn);
		return toReturn;
	}//make JLabel
	
	/**
	 * A method to create a JComboBox with the given parameters.
	 * @param theX  The position along the x-axis at which element should appear on screen.
	 * @param theY  The position along the y-axis at which element should appear on screen.
	 * @param theWidth  The pixel width of the element on the screen.
	 * @param theHeight  The pixel height of the element on the screen.
	 * @param theForeground  The color of the foreground.  
	 * @param theCP  The content pane to which the element must be added.
	 * @return Returns the JComboBox created with these parameters.
	 */
	public static JComboBox makeComboBox(int theX, int theY, int theWidth, int theHeight, Color theForeground, Container theCP ) {
		JComboBox toReturn = new JComboBox(BirthDate.MONTHS);
		toReturn.setSize(theWidth, theHeight);
		toReturn.setLocation(theX, theY);
		toReturn.setForeground(theForeground);
		theCP.add(toReturn);
		//toReturn.addActionListener(theHandler);
		return toReturn;	
	}//makeComboBox
	
	
	/**
	 * A method to create a JTextArea with the given parameters.
	 * @param theX  The position along the x-axis at which element should appear on screen.
	 * @param theY  The position along the y-axis at which element should appear on screen.
	 * @param theWidth  The pixel width of the element on the screen.
	 * @param theHeight  The pixel height of the element on the screen.
	 * @param theForeground  The color of the foreground.  
	 * @param theCP  The content pane to which the element must be added.
	 * @return  Returns the JTextArea created with these parameters.  
	 */
	public static JTextArea makeTextArea(int theX, int theY, int theWidth, int theHeight, Color theForeground, Container theCP ) {
		JTextArea toReturn = new JTextArea();
		toReturn.setSize(theWidth, theHeight);
		toReturn.setLocation(theX, theY);
		toReturn.setForeground(theForeground);
		theCP.add(toReturn);
		return toReturn;	
	}//makeComboBox
	
	

	
}
