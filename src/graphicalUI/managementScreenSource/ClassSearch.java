package graphicalUI.managementScreenSource;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import main.Markbook;

/*	handles seraching in classes
 *	TODO better search function 
 */
public class ClassSearch extends JPanel {

	private static final long serialVersionUID = 1L;
	private static String searchTitle = "Filter Classes:";
	
	private Markbook mB;
	private ClassDisplay displayPanel;
	
	private JTextField searchBar;
	
	/*	default constructor
	 * 
	 */
	public ClassSearch (Markbook newmB, ClassDisplay newDisplayPanel) {
		mB = newmB;
		displayPanel = newDisplayPanel;
		
		searchBar = new JTextField("please enter class, student, or subject name");
		setupGraphical();
		setupSearch();
	}
	
	private void setupGraphical() {
		//use gridbag format
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
				
		//set border
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		Border compound = BorderFactory.createCompoundBorder(
                raisedbevel, loweredbevel);
		this.setBorder(compound);
		
		//set insets
		c.insets = new Insets(0,10,0,5);
		
		//add title for search
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 3;
		c.gridwidth = 1;
		c.weighty = 1;
		c.weightx = 0.1;
		JLabel title = new JLabel(searchTitle, SwingConstants.CENTER);
		title.setOpaque(false);
		//title.setFont(titlefont);
		this.add(title, c);
		
		//add searchbar
		c.insets = new Insets(0,0,0,20);
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 3;
		c.gridwidth = 2;
		c.weighty = 1;
		c.weightx = 0.9;
		this.add(searchBar, c);
	}
	
	
	/*	setup search bar
	 * 
	 */
	private void setupSearch() {
		searchBar.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				displayPanel.refreshClass(mB.searchClasses(searchBar.getText()));
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				displayPanel.refreshClass(mB.searchClasses(searchBar.getText()));
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				displayPanel.refreshClass(mB.searchClasses(searchBar.getText()));
			}
			
		});
	}
}
