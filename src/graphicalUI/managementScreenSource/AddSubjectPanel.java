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

/*	panel used to add new subjects
 * 
 */
public class AddSubjectPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private static Color backgroundColor = Color.WHITE;
	
	private Markbook mB;
	private JTextField fullName;
	private JTextField shortenedName;
	private JButton submitButton;
	
	//label for input status feedback
	private JLabel statusLabel;
	private static String defaultText = "Please enter subject details";
	private static String wrongInputText = "Incorrect details entered";
	private static String subjectAddedText = "Subject added: ";
	
	
	public AddSubjectPanel(Markbook newmB) {
		mB = newmB;
		
		fullName = new JTextField("Full Name");
		shortenedName = new JTextField("Shortened Name");
		submitButton = new JButton("Add New Subject");
		
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
		c.weightx = 0.5;
		this.add(fullName, c);
		
		c.gridx = 1;
		c.gridy = 0;
		this.add(shortenedName, c);
		
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
	
	
	/*	allow the submit button to add subjects
	 * 
	 */
	private void setupSubmitButton() {
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (checkInput()) {
					String SubjectFullName = fullName.getText();
					String SubjectShortName = shortenedName.getText();
					
					mB.addSubject(SubjectFullName, SubjectShortName);
					//update statusLabel
					String statusText = subjectAddedText + SubjectShortName + ": "
							+ SubjectShortName;
					
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
		
		//check if input is not only spaces
		if (fullName.getText().matches("^ +$")) {
			isAllowed = false;
		} else if (shortenedName.getText().matches("^ +$")) {
			isAllowed = false;
		} else if ((fullName.getText() == "") || (shortenedName.getText() == "")) {
			isAllowed = false;
		}
		
		if (!isAllowed) {
			statusLabel.setText(wrongInputText);
		}
		
		return isAllowed;
	}
}
