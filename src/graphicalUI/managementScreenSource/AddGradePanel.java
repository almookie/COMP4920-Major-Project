package graphicalUI.managementScreenSource;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Markbook;

/*	panel used to add new grades
 * 
 */
public class AddGradePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Color backgroundColor = Color.WHITE;
	
	private Markbook mB;
	private JTextField grade;
	private JButton submitButton;
	
	//label for input status feedback
	private JLabel statusLabel;
	private static String defaultText = "Please enter subject details";
	private static String wrongInputText = "Please enter a number for the grade";
	private static String gradeAddedText = "Grade added: ";
	
	
	public AddGradePanel(Markbook newmB) {
		mB = newmB;
		
		grade = new JTextField("Grade Number");
		submitButton = new JButton("Add New Grade");
		
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
		c.weightx = 0.1;
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
	
	
	/*	allow the submit button to add grades
	 * 
	 */
	private void setupSubmitButton() {
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (checkInput()) {
					String gradeName = grade.getText();
					
					mB.addGrade(Integer.parseInt(gradeName));
					//update statusLabel
					String statusText = gradeAddedText + gradeName;
					
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
		boolean isAllowed = false;
		
		/*	check if a string is numeric
		 * 	credit to "CraigTP"
		 */
		if (grade.getText().matches("-?\\d+(\\.\\d+)?")) {
			isAllowed = true;
		}
		
		if (!isAllowed) {
			statusLabel.setText(wrongInputText);
		}
		
		return isAllowed;
	}
}
