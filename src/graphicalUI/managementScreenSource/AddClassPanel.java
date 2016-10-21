package graphicalUI.managementScreenSource;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Grade;
import main.Markbook;
import main.Subject;
import main.Subject_Class;

/*	panel used to add new classes
 * 
 */
public class AddClassPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Color backgroundColor = Color.WHITE;
	
	private Markbook mB;
	private JComboBox<GradeComboBoxHolder> grade;
	private JComboBox<SubjectComboBoxHolder> subject;
	private JButton submitButton;
	
	//label for input status feedback
	private JLabel statusLabel;
	private static String defaultText = "Please enter Class details";
	private static String wrongInputText = "Incorrect details entered";
	private static String classAddedText = "Class added: ";
		
		
	/*	default constructor
	 * 
	 */
	public AddClassPanel(Markbook newmB) {
		mB = newmB;
		
		//create the components
		grade = new JComboBox<GradeComboBoxHolder>();
		subject = new JComboBox<SubjectComboBoxHolder>();
		submitButton = new JButton("Add New Class");
		
		//label for input status feedback
		statusLabel = new JLabel(defaultText);
		
		setupGraphical();
		setupSubmitButton();
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
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weighty = 0.5;
		c.weightx = 0.6;
		this.add(subject, c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.4;
		this.add(grade, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.1;
		c.weightx = 1;
		this.add(statusLabel, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.4;
		c.weightx = 1;
		this.add(submitButton,c);
	}
	
	
	/*	allow the submit button to add students
	 * 
	 */
	private void setupSubmitButton() {
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (checkInput()) {
					Subject classSubject = ((SubjectComboBoxHolder)subject.getSelectedItem()).getSubject();
					Grade classGrade = ((GradeComboBoxHolder)grade.getSelectedItem()).getGrade();
					
					Subject_Class newClass = classSubject.addClass(classGrade);
					
					//update statusLabel
					String statusText = classAddedText + mB.getLongName(newClass) + " with subject: " 
							+ String.valueOf(classSubject) + ", and grade: " + String.valueOf(classGrade);
					
					statusLabel.setText(statusText);
					
					//TODO refresh data
				}
			}
			
		});
	}
	
	
	/*	check if textbox entry is of the correct format
	 * 	true if item only has allowed characters, otherwise false
	 */
	private boolean checkInput() {
		boolean isAllowed = true;
		
		//check if input is empty
		if (grade.getSelectedItem() == null) {
			isAllowed = false;
		} else if (subject.getSelectedItem() == null) {
			isAllowed = false;
		}
		
		if (!isAllowed) {
			statusLabel.setText(wrongInputText);
		}
		
		return isAllowed;
	}
	
}
