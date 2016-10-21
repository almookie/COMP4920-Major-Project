package graphicalUI.managementScreenSource;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/*	manages swapping between 3 pages
 * 
 */
public class PageSelectionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Color backgroundColor = Color.WHITE;

	//panels to switch between
	JPanel gradePanel;
	JPanel subjectPanel;
	JPanel studentPanel;
	
	//buttons to switch between panels
	JButton gradeButton;
	JButton subjectButton;
	JButton studentButton;
	
	JPanel selectedPanel;
	
	/*	default constructor
	 * 
	 */
	public PageSelectionPanel(JPanel newGradePanel, JPanel newSubjectPanel, JPanel newStudentPanel) {
		gradePanel = newGradePanel;
		gradePanel.setVisible(false);
		subjectPanel = newSubjectPanel;
		subjectPanel.setVisible(false);
		studentPanel = newStudentPanel;
		selectedPanel = studentPanel;
		
		gradeButton = new JButton("Grade");
		subjectButton = new JButton("Subject");
		studentButton = new JButton("Student");
		
		setupActionListener();
		setupGraphical();
	}
	
	
	private void switchToSubject() {
		selectedPanel.setVisible(false);
		selectedPanel = subjectPanel;
		subjectPanel.setVisible(true);
	}
	
	
	private void switchToStudent() {
		selectedPanel.setVisible(false);
		selectedPanel = studentPanel;
		studentPanel.setVisible(true);
	}
	
	
	private void switchToGrade() {
		selectedPanel.setVisible(false);
		selectedPanel = gradePanel;
		gradePanel.setVisible(true);
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
		
		//add in the buttons
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weighty = 1;
		c.weightx = 0.33;
		this.add(studentButton, c);
		
		c.gridx = 1;
		this.add(subjectButton, c);
		
		c.gridx = 2;
		this.add(gradeButton, c);
	}
	
	
	/*	setup the action listeners to switch pages
	 * 
	 */
	private void setupActionListener() {
		studentButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				switchToStudent();
			}
			
		});
		
		subjectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				switchToSubject();
			}
			
		});
		
		gradeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				switchToGrade();
			}
			
		});
	}
}
