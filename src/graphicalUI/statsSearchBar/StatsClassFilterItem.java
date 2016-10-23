package graphicalUI.statsSearchBar;

import graphicalUI.managementScreenSource.GradeFilterResults;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Markbook;
import main.Subject_Class;

public class StatsClassFilterItem extends JPanel  {

	private static final long serialVersionUID = 1L;
	private static Color backgroundColor = Color.WHITE;
	private static Color selectedBackgroundColor = new Color(220, 220, 220);
	
	Subject_Class myClass;
	Markbook mB;
	StatsSearchPanel parentPanel;
	JPanel self = this;
	
	public StatsClassFilterItem(Subject_Class newClass, Markbook newmB, StatsSearchPanel newParent) {
		myClass = newClass;
		mB = newmB;
		parentPanel = newParent;
		
		setupGraphical();
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
		
		//create label with grade
		JLabel displayName = new JLabel(mB.getLongName(myClass));
		//display class name label in panel
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 3;
		c.gridwidth = 2;
		c.weighty = 1;
		c.weightx = 0.8;
		this.add(displayName, c);
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
	}
}
