package graphicalUI.managementScreenSource;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Grade;
import main.Markbook;
import main.Subject;


/*	Stores data on an individual student and handles interaction with
 * 	individual grades
 * 
 */
public class GradePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Color backgroundColor = Color.WHITE;
	
	//subject object stored in this
	Grade grade;
	Markbook mB;	
	GradeFilterResults parentPanel;
	JButton removeButton;
	JPanel self = this;
	
	
	/*	default constructor
	 * 	
	 */
	public GradePanel(Grade newGrade, Markbook newmB, GradeFilterResults newParent) {
		grade = newGrade;
		mB = newmB;
		parentPanel = newParent;
		
		setupGraphical();
		setupRemoveButton();
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
		String fullName = String.valueOf(grade.getYear(mB.getCurrentYear()));
				
		JLabel displayName = new JLabel(fullName);
		
		//display subject name label in panel
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 3;
		c.gridwidth = 2;
		c.weighty = 1;
		c.weightx = 0.8;
		this.add(displayName, c);
	}
	
	
	/*	creates the remove subject button
	 * 
	 */
	private void setupRemoveButton() {
		removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				parentPanel.remove(self);
				parentPanel.refreshResults();
			}
			
		});
		
		GridBagConstraints c = new GridBagConstraints();
		//display remove student button at right of panel
		c.gridx = 2;
		c.gridy = 0;
		c.gridheight = 3;
		c.gridwidth = 1;
		c.weighty = 1;
		c.weightx = 0.2;
		this.add(removeButton, c);
	}
}
