package graphicalUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class managementScreenActionListener implements ActionListener {
	private managementScreen ms;
	
	public managementScreenActionListener(managementScreen screen) {
		ms = screen;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if ("setFilterStudent".equals(e.getActionCommand())) {
			ms.switchToStudent();
		} else if ("setFilterSubject".equals(e.getActionCommand())) {
			ms.switchToSubject();
		}
		
	}

}
