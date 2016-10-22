package graphicalUI.managementScreenSource;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
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
	private static Color selectedBackgroundColor = new Color(0, 230, 230);
	private static Dimension preferredSize = new Dimension(130, 50);
	private static Dimension deletePreferredSize = new Dimension(50, 50);
	
	//private Font bodyFont = new Font("Helvetica", Font.BOLD, 12);
	
	//Student object stored in the panel
	Student student;
	JButton removeButton;
	ClassPanel parent;
	
	/*	default constructor
	 * 	
	 */
	public StudentPanel(Student newStudent, ClassPanel newParent) {
		student = newStudent;
		removeButton = new JButton("X");
		parent = newParent;
		
		setupGraphical();
		setupRemoveButton();
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
		
		//display student name label in center of panel
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weighty = 1;
		c.weightx = 0.8;
		this.add(displayName, c);
		
		//add the remove button
		c.gridx = 2;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weighty = 1;
		c.weightx = 0.2;
		removeButton.setPreferredSize(deletePreferredSize);
		removeButton.setBackground(backgroundColor);
		removeButton.setBorder(null);
		removeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(removeButton, c);
	}
	
	
	/*	setup the interactive features of the removeButton
	 * 
	 */
	private void setupRemoveButton() {
		//remove student functionality
		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parent.removeStudent(student);
				parent.refreshClass();
			}
			
		});
		
		//highlighting
		removeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				parent.setHighlight();
				removeButton.setBackground(selectedBackgroundColor);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				parent.removeHighlight();
				removeButton.setBackground(backgroundColor);
			}
		});
	}
	
}
