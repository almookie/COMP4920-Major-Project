package graphicalUI.managementScreenSource;

import java.awt.FlowLayout;

import javax.swing.JPanel;

/*	a row of StudentPanels
 * 
 */
public class ClassPanelRow extends JPanel {
	private static final long serialVersionUID = 1L;
	private static Integer maxItems = 5;
	
	Integer numItems;
	
	/*	default constructor
	 * 
	 */
	public ClassPanelRow() {
		numItems = 0;
		this.setLayout(new FlowLayout());
		this.setOpaque(false);
	}
	
	
	public void addItem(StudentPanel item) {
		numItems ++;
		this.add(item);
	}
	
	
	/*	check if current panel is full
	 *	returns true if full, false if not full
	 * 
	 */
	public boolean isFull() {
		boolean isFull = false;
		
		if (numItems >= maxItems) {
			isFull = true;
		}
		
		return isFull;
	}
	
}
