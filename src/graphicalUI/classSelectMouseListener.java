package graphicalUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JTable;

import main.Class;

public class classSelectMouseListener implements MouseListener {
	private managementScreen ms;
	private Class attachedClass;
	private JLabel attachedLabel;
	private JTable attachedTable;
	
	public classSelectMouseListener(managementScreen newScreen ,Class myClass, JLabel myLabel, JTable myTable) {
		ms = newScreen;
		attachedClass = myClass;
		attachedLabel = myLabel;
		attachedTable = myTable;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		ms.switchToNewClass(attachedLabel, attachedClass, attachedTable);
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
