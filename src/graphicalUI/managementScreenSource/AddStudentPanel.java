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

import main.Markbook;

/*	panel used to add new students
 * 
 */
public class AddStudentPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Color backgroundColor = Color.WHITE;
	
	private Markbook mB;
	private JTextField givenName;
	private JTextField surName;
	private JComboBox<GradeComboBoxHolder> grade;
	private JButton submitButton;
	
	//label for input status feedback
	private JLabel statusLabel;
	private static String defaultText = "please enter student details";
	private static String wrongInputText = "incorrect student details";
	private static String studentAddedText = "Student added: ";
	
	
	/*	default constructor
	 * 
	 */
	public AddStudentPanel(Markbook newmB) {
		mB = newmB;
		
		//create the components
		givenName = new JTextField("Given Name");
		surName = new JTextField("Surname");
		grade = new JComboBox<GradeComboBoxHolder>();
		submitButton = new JButton("Add New Student");
		
		//label for input status feedback
		statusLabel = new JLabel(defaultText);
		
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
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weighty = 0.5;
		c.weightx = 0.4;
		this.add(givenName, c);
		
		c.gridx = 1;
		c.gridy = 0;
		this.add(surName, c);
		
		c.gridx = 3;
		c.gridy = 0;
		c.weightx = 0.2;
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
					mB.addStudent(givenName.getText(), surName.getText(), 
							((GradeComboBoxHolder)grade.getSelectedItem()).getGrade());
					
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
		
		//check if input is not only spaces
		if (givenName.getText().matches("^ +$")) {
			isAllowed = false;
		} else if (surName.getText().matches("^ +$")) {
			isAllowed = false;
		} else if ((givenName.getText() == "") || (surName.getText() == "")) {
			isAllowed = false;
		} else if (grade.getSelectedItem() == null) {
			isAllowed = false;
		}
		
		if (!isAllowed) {
			statusLabel.setText(wrongInputText);
		}
		
		return isAllowed;
	}
}
