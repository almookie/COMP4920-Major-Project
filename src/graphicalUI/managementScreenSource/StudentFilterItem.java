package graphicalUI.managementScreenSource;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Markbook;
import main.Student;
import main.Subject_Class;

/*	results of the student filter, click to add to selected list
 * 
 */
public class StudentFilterItem extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Color backgroundColor = Color.WHITE;
	private static Color selectedBackgroundColor = new Color(220, 220, 220);
	
	StudentFilterItem self = this;
	
	Student student;
	Markbook mB;
	StudentFilterSelected selectedPanel;
	StudentFilterResults parentClass;
	/*	default constructor
	 * 
	 */
	public StudentFilterItem(Student newStudent, StudentFilterResults newParentClass, StudentFilterSelected newSelectedPanel, Markbook newmB) {
		student = newStudent;
		mB = newmB;
		selectedPanel = newSelectedPanel;
		parentClass = newParentClass;
		
		setupGraphical();
		setupSelect();
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
		
		//display student name label in center of panel
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weighty = 1;
		c.weightx = 1;
		this.add(displayName, c);
	}
	
	
	/*	sets up the mouse listeners for the selection of students
	 *	and colored hovering
	 * 
	 */
	private void setupSelect() {
		this.addMouseListener(new MouseAdapter() {
					
					@Override
					public void mouseClicked(MouseEvent e) {
						selectedPanel.addStudent(student);
						parentClass.refreshResults();
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						self.setBackground(selectedBackgroundColor);

					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						self.setBackground(backgroundColor);

					}
				});
	}
	
}
