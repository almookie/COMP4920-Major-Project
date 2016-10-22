package graphicalUI.managementScreenSource;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/*	manages swapping between 3 pages
 * 
 */
public class PageSelectionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Color backgroundColor = Color.WHITE;
	
	//button colors
	private static Color normalColor = Color.LIGHT_GRAY;
	private static Color selectedColor = Color.GRAY;
	
	private Integer preferredHeight = 40;
	
	//font for title labels
	//private Font titlefont = new Font("Helvetica", Font.BOLD, 14);
	
	//title for this panel
	private String titleText = "Select Screen";
	
	//panels to switch between
	JPanel gradePanel;
	JPanel subjectPanel;
	JPanel studentPanel;
	
	//buttons to switch between panels
	JButton gradeButton;
	JButton subjectButton;
	JButton studentButton;
	
	JPanel selectedPanel;
	JButton selectedButton;
	
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
		selectedButton = studentButton;
		studentButton.setBackground(selectedColor);
		
		setupActionListener();
		setupGraphical();
	}
	
	
	private void switchToSubject() {
		selectedPanel.setVisible(false);
		selectedPanel = subjectPanel;
		subjectPanel.setVisible(true);
		
		selectedButton.setBackground(normalColor);
		subjectButton.setBackground(selectedColor);
		selectedButton = subjectButton;
	}
	
	
	private void switchToStudent() {
		selectedPanel.setVisible(false);
		selectedPanel = studentPanel;
		studentPanel.setVisible(true);
		
		selectedButton.setBackground(normalColor);
		studentButton.setBackground(selectedColor);
		selectedButton = studentButton;
	}
	
	
	private void switchToGrade() {
		selectedPanel.setVisible(false);
		selectedPanel = gradePanel;
		gradePanel.setVisible(true);
		
		selectedButton.setBackground(normalColor);
		gradeButton.setBackground(selectedColor);
		selectedButton = gradeButton;
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
		
		//set border
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		Border compound = BorderFactory.createCompoundBorder(
                raisedbevel, loweredbevel);
		this.setBorder(compound);
		
		//add in title
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.weighty = 0.05;
		c.weightx = 1;
		JLabel title = new JLabel(titleText, SwingConstants.CENTER);
		//title.setFont(titlefont);
		this.add(title, c);
		
		//setup button size
		Dimension preferredSize = this.getPreferredSize();
		preferredSize.height = preferredHeight;
		this.setPreferredSize(preferredSize);
		
		//add in the buttons
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weighty = 0.9;
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
