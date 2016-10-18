package graphicalUI.managementScreenSource;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Markbook;
import main.Student;

/*	selected students, can be removed from list
 * 
 */
public class StudentFilterSelectedItem extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Color backgroundColor = Color.WHITE;
	
	StudentFilterSelectedItem self = this;
	
	Student student;
	Markbook mB;
	StudentFilterSelected parentPanel;
	JButton removeButton;
	
	/*	default constructor
	 * 
	 */
	public StudentFilterSelectedItem(Student newStudent, Markbook newmB, StudentFilterSelected newParent) {
		student = newStudent;
		mB = newmB;
		parentPanel = newParent;
		
		setupGraphical();
	}
	
	
	/*	set up the display elements
	 * 
	 */
	private void setupGraphical() {
		//use gridbag format
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//set background color
		this.setOpaque(true);
		this.setBackground(backgroundColor);
		
		//create label with student name and grade
		String fullName = 
				student.getSurname() + "," + student.getGivenName();
		//String grade = TODO
		JLabel displayName = new JLabel(fullName);
		
		//display student name label in left of panel
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 3;
		c.gridwidth = 2;
		c.weighty = 1;
		c.weightx = 0.8;
		this.add(displayName, c);
	}
	
	
	/*	creates the remove student button
	 * 
	 */
	private void setupRemoveButton() {
		removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				parentPanel.removeStudent(self);
			}
			
		});
	}
}
