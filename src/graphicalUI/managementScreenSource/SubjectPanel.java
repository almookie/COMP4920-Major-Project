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
import javax.swing.JPanel;

import main.Markbook;
import main.Subject;


/*	Stores data on an individual student and handles interaction with
 * 	individual subjects
 * 
 */
public class SubjectPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Color backgroundColor = Color.WHITE;
	private static Color selectedBackgroundColor = new Color(220, 220, 220);
	
	//subject object stored in this
	Subject subject;
	Markbook mB;	
	SubjectFilterResults parentPanel;
	JButton removeButton;
	JPanel self = this;
	
	
	/*	default constructor
	 * 	
	 */
	public SubjectPanel(Subject newSubject, Markbook newmB, SubjectFilterResults newParent) {
		subject = newSubject;
		mB = newmB;
		parentPanel = newParent;
		
		setupGraphical();
		setupRemoveButton();
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
				subject.getShortcode() + ": " + subject.getName();
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
		removeButton = new JButton("Delete");
		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//TODO delete subject
				parentPanel.refreshWholePage();
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
	
	/*	sets up the mouse listeners for colored hovering
	 * 
	 */
	private void setupSelect() {
		this.addMouseListener(new MouseAdapter() {
			
					@Override
					public void mouseEntered(MouseEvent e) {
						self.setBackground(selectedBackgroundColor);

					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						self.setBackground(backgroundColor);

					}
				});
		removeButton.addMouseListener(new MouseAdapter() {
			
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
