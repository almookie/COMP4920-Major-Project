package graphicalUI.managementScreenSource;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	
	private static String messageTitle = "Confirm Deletion";
	private static String confirmationMessage = "Are you sure you wish to delete the Student?";
	
	
	StudentFilterItem self = this;
	
	Student student;
	Markbook mB;
	StudentFilterSelected selectedPanel;
	StudentFilterResults parentClass;
	JButton removeButton;
	
	/*	default constructor
	 * 
	 */
	public StudentFilterItem(Student newStudent, StudentFilterResults newParentClass, StudentFilterSelected newSelectedPanel, Markbook newmB) {
		student = newStudent;
		mB = newmB;
		selectedPanel = newSelectedPanel;
		parentClass = newParentClass;
		removeButton = new JButton("Delete");
		
		setupGraphical();
		setupSelect();
		setupRemove();
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
		c.weightx = 0.8;
		this.add(displayName, c);
		
		c.gridx = 2;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weighty = 1;
		c.weightx = 0.2;
		this.add(removeButton,c);
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
	
	
	/*	set up button to delete student
	 * 
	 */
	private void setupRemove() {
		removeButton.setFocusable(false);
		
		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//TODO remove student
				if (!parentClass.dontConfirm()) {
					
					int result = JOptionPane.showConfirmDialog
							(self, confirmationMessage, messageTitle, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					
					if (result == JOptionPane.YES_OPTION) {
						parentClass.refreshWholePage();
					}
				}
			}
			
		});
	}
	
}
