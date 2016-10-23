package graphicalUI.managementScreenSource;

import graphicalUI.managementScreen;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import main.Grade;
import main.Markbook;
import main.Subject;
import main.Subject_Class;

/*	panel used to add new classes
 * 
 */
public class AddClassPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	//private static Color backgroundColor = Color.WHITE;
	
	private Markbook mB;
	private managementScreen mS;
	private JComboBox<GradeComboBoxHolder> grade;
	private JComboBox<SubjectComboBoxHolder> subject;
	private JButton submitButton;
	
	//label for input status feedback
	private JLabel statusLabel;
	private static String defaultText = "Please enter Class details";
	private static String wrongInputText = "<html><font color='red'>Incorrect details entered</font></html>";
	private static String classAddedText = "Class added: ";
		
	JCheckBox confirmationCheck;
		
	/*	default constructor
	 * 
	 */
	public AddClassPanel(Markbook newmB, managementScreen newmS,  JCheckBox newConfirmationCheck) {
		mB = newmB;
		mS = newmS;
		confirmationCheck = newConfirmationCheck;
		
		//create the components
		grade = new JComboBox<GradeComboBoxHolder>();
		subject = new JComboBox<SubjectComboBoxHolder>();
		submitButton = new JButton("Add New Class");
		
		//label for input status feedback
		statusLabel = new JLabel(defaultText);
		
		setupGraphical();
		setupSubmitButton();
		
		refreshComboBox();
	}
	
	
	/*	refresh the combobox display
	 * 
	 */
	public void refreshComboBox() {
		grade.removeAllItems();
		subject.removeAllItems();
		
		ArrayList<Grade> allGrades = mB.getGrades();
		for (Grade currentGrade : allGrades) {
			GradeComboBoxHolder comboBoxItem = new GradeComboBoxHolder(currentGrade, mB);
			grade.addItem(comboBoxItem);
		}
		
		ArrayList<Subject> allSubjects = mB.getSubjects();
		for (Subject currentSubject : allSubjects) {
			SubjectComboBoxHolder comboBoxItem = new SubjectComboBoxHolder(currentSubject, mB);
			subject.addItem(comboBoxItem);
		}
		
		this.revalidate();
		this.repaint();
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
		//this.setBackground(backgroundColor);
		
		c.fill = GridBagConstraints.BOTH;
		
		//set insets
		c.insets = new Insets(0,5,10,5);
		
		//panel to stoore confirmation checkbox
		JPanel confirmationPanel = new JPanel(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 3;
		c.gridwidth = 3;
		c.weighty = 1;
		c.weightx = 1;
		confirmationPanel.add(confirmationCheck, c);
		
		//set border
		confirmationPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.LIGHT_GRAY));
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.02;
		c.weightx = 1;
		this.add(confirmationPanel, c);
		
		c.insets = new Insets(5,5,0,5);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weighty = 0.5;
		c.weightx = 0.6;
		this.add(subject, c);
		
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 0.4;
		this.add(grade, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.1;
		c.weightx = 1;
		this.add(statusLabel, c);
		
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.38;
		c.weightx = 1;
		this.add(submitButton,c);
	}
	
	
	/*	allow the submit button to add class
	 * 
	 */
	private void setupSubmitButton() {
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (checkInput()) {
					Subject classSubject = ((SubjectComboBoxHolder)subject.getSelectedItem()).getSubject();
					Grade classGrade = ((GradeComboBoxHolder)grade.getSelectedItem()).getGrade();
					
					Subject_Class newClass = new Subject_Class
							(mB.getNextAvailableClassID(), classGrade, classSubject, mB.getNextAvailableClassNumber(classSubject, classGrade));
					classSubject.addClass(newClass);
					
					//update statusLabel
					String statusText = classAddedText + "[" + mB.getLongName(newClass) + "], with subject: [" 
							+ classSubject.getShortcode() + ": " + classSubject.getName() + "], and grade: [" 
							+ String.valueOf(classGrade.getYear(mB.getCurrentYear())) + "]";
					
					statusLabel.setText(statusText);
					
					mS.refresh();
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
