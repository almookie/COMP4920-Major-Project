package graphicalUI.managementScreenSource;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Student;

/*	Stores data on an individual student and handles interaction with
 * 	individual students
 * 
 */
public class StudentPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Color backgroundColor = Color.CYAN;
	private static Dimension preferredSize = new Dimension(130, 50);
	
	//private Font bodyFont = new Font("Helvetica", Font.BOLD, 12);
	
	//Student object stored in the panel
	Student student;
	
	
	/*	default constructor
	 * 	
	 */
	public StudentPanel(Student newStudent) {
		student = newStudent;
		
		setupGraphical();
	}
	
	
	/*	set up the display elements of the studentPanel
	 * 
	 */
	private void setupGraphical() {
		//use gridbag format
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//set background color
		this.setBackground(backgroundColor);
		
		//create label with student name
		String fullName = 
				student.getSurname() + "," + student.getGivenName();
		JLabel displayName = new JLabel(fullName);
		displayName.setPreferredSize(preferredSize);
		//displayName.setFont(bodyFont);
		
		//display student name label in center of panel
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weighty = 1;
		c.weightx = 1;
		this.add(displayName, c);
		
	}
	
}
