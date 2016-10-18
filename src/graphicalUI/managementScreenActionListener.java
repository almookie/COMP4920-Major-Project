package graphicalUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Markbook;

public class managementScreenActionListener implements ActionListener {
	private managementScreen ms;
	private Markbook mB;
	
	
	public managementScreenActionListener(managementScreen screen, Markbook markBook) {
		ms = screen;
		mB = markBook;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if ("setFilterStudent".equals(e.getActionCommand())) {
			ms.switchToStudent();
		} else if ("setFilterSubject".equals(e.getActionCommand())) {
			ms.switchToSubject();
		} else if ("setFilterGrade".equals(e.getActionCommand())) {
			ms.switchToGrade();
		} else if ("newStudent".equals(e.getActionCommand())) {
			ms.addStudent();
		} else if ("newSubject".equals(e.getActionCommand())) {
			ms.addSubject();
		} else if ("newGrade".equals(e.getActionCommand())) {
			ms.addGrade();
		} else if ("addNewClass".equals(e.getActionCommand())) {
			ms.addClass();
		} 
		
	}

}
