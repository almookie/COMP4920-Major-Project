package markingPanelComponents;

import graphicalUI.markingPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import main.Assessment;
import main.Class;
import main.Markbook;
import main.Student;

public class AssesmentDisplay extends JPanel  {
	markingPanel markingPanel;

	Assessment myAssesment;
	Markbook mB;

	
	//two panels that make up the collapsible panel
	JPanel mainPanel;
	JPanel contentPanel;
	Class assesmentClass;
	String nameFilter;
	
	public AssesmentDisplay(markingPanel markingPanel, Assessment a, Markbook mB, Class thisClass) {
		this.markingPanel = markingPanel;
		this.myAssesment = a;
		this.mB = mB;
		this.assesmentClass = thisClass;
		setupGraphical();

	}


	public AssesmentDisplay(markingPanel markingPanel, Assessment a, Markbook mB, Class thisClass,
			String nameFilter) {
		this.markingPanel = markingPanel;

		this.myAssesment = a;
		this.mB = mB;
		this.assesmentClass = thisClass;
		this.nameFilter = nameFilter;
		setupGraphical();	
		}


	private void setupGraphical() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		
		String assName = myAssesment.getName();
		JLabel displayName = new JLabel(assName);
		displayName.setOpaque(true);
		displayName.setBackground(Color.GREEN);
		displayName.setForeground(Color.WHITE);


		mainPanel.add(displayName);
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;

		c.gridy = 1;

			
			String[][] data1 = null;
			
			String[] columnNames = new String[6];
			columnNames[0] = "First Name";
			columnNames[1] = "Last Name";		
			columnNames[2] = "Mark";		
			columnNames[3] = "ObjectClass";		
			columnNames[4] = "ObjectAssesment";		
			columnNames[5] = "ObjectStudent";		
			
			final DefaultTableModel model = new DefaultTableModel(data1, columnNames);

	 		final JTable table = new JTable(model);
	 		table.setFillsViewportHeight(true);
	 		table.setRowHeight(20);
	 		String fullName;
	 		int height = 0;

	 		for(Student s : assesmentClass.getStudents()){	
	 			fullName = s.getGivenName().concat(" " +s.getSurname());
	 			if(nameFilter!=null && !nameFilter.equals("")){

	 				if(s.getGivenName().contains(nameFilter)) {
	 					model.addRow(new Object[]{s.getGivenName(),s.getSurname(),myAssesment.getMark(s),assesmentClass,myAssesment,s});
	 					height += 18;
	 				}else if(s.getSurname().contains(nameFilter)){
	 					model.addRow(new Object[]{s.getGivenName(),s.getSurname(),myAssesment.getMark(s),assesmentClass,myAssesment,s});
	 					height += 18;
	 				}else if(fullName.contains(nameFilter)){
	 					model.addRow(new Object[]{s.getGivenName(),s.getSurname(),myAssesment.getMark(s),assesmentClass,myAssesment,s});
	 					height += 18;
	 				}
	 					
	 			}else{
	 				model.addRow(new Object[]{s.getGivenName(),
	 						s.getSurname(),
	 						myAssesment.getMark(s),
	 						assesmentClass,
	 						myAssesment,
	 						s});
		 			height += 18;
	 			}
	 			

	 		}
	 		 table.removeColumn(table.getColumnModel().getColumn(5));
	 		 table.removeColumn(table.getColumnModel().getColumn(4));
	 		 table.removeColumn(table.getColumnModel().getColumn(3));


	 		table.setPreferredScrollableViewportSize(new Dimension(750, 20+height));
	        JScrollPane scrollPane = new JScrollPane(table);
			c.fill = GridBagConstraints.BOTH;

			c.gridy = 2;
			
	        mainPanel.add(scrollPane,c);
	        
	 		
	 		model.addTableModelListener(new TableModelListener(){


				@Override
				public void tableChanged(TableModelEvent e) {
					// TODO Auto-generated method stub
					if(table.isEditing()){
 		            	System.out.println("DEBUG INFO: marking TABLES data edited");
 						String value = (String) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
 						System.out.println(value);
 						System.out.println(e.getColumn() + " --" +e.getFirstRow());
 						
 						Class returC = (Class) model.getValueAt(e.getFirstRow(), 3);
 						Assessment returA = (Assessment) model.getValueAt(e.getFirstRow(), 4);
 						
 						Student returS = (Student) model.getValueAt(e.getFirstRow(), 5);
 						String newFName = (String) (model.getValueAt(e.getFirstRow(), 0));
 						String newLName = (String) (model.getValueAt(e.getFirstRow(), 1));
 						Double newMark = Double.parseDouble((String) model.getValueAt(e.getFirstRow(), 2));

 						//marking
 						returS.setGivenName(newFName);
 						returS.setSurname(newLName);
 						
 						for(Class c : mB.getClasses()){
 							if(c.equals(returC)){
 		 						for(Assessment a : c.getAssessments()){
 		 							if(a.equals(returA)){
 		 								for(Student s : c.getStudents()){
 		 									if(s.equals(returS)){
 		 										s.setGivenName(newFName);
 		 										s.setSurname(newLName);
 		 										a.setMark(s, newMark);
 		 									}
 		 								}

 		 							}
 		 						}
 							}
 						}
 						
 						markingPanel.setMyMb(mB);
 						markingPanel.refreshClasses(null, null, null, mB.getClasses());
 					}
 					
				}	
             	
             });
			
		
		this.add(mainPanel);		
	}


	
}
