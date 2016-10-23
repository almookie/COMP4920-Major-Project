package markingPanelComponents;

import graphicalUI.markingPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import main.Assessment;
import main.Subject_Class;
import main.Markbook;
import main.Student;

public class AssesmentDisplay extends JPanel  {
	markingPanel markingPanel;

	Assessment myAssesment;
	Markbook mB;

	
	//two panels that make up the collapsible panel
	JPanel mainPanel;
	JPanel contentPanel;
	Subject_Class assesmentClass;
	String nameFilter;
	
	public AssesmentDisplay(markingPanel markingPanel, Assessment a, Markbook mB, Subject_Class thisClass) {
		this.markingPanel = markingPanel;
		this.myAssesment = a;
		this.mB = mB;
		this.assesmentClass = thisClass;
		setupGraphical();

	}


	public AssesmentDisplay(markingPanel markingPanel, Assessment a, Markbook mB, Subject_Class thisClass,
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
		JPanel labels = new JPanel(new GridBagLayout());
		JLabel displayName = new JLabel(assName);

		JLabel weighting = new JLabel("Change current Weight: ");
		final JTextField w = new JTextField(Double.toString(myAssesment.getWeighting()));
		
		w.addActionListener(new AbstractAction(){
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public void actionPerformed(ActionEvent e)
		    {
		    	for(Subject_Class c : mB.getClasses()){
		    		if(c.equals(assesmentClass)){
	 						for(Assessment a : c.getAssessments()){
	 									if(a.equals(myAssesment)){
	 										myAssesment.setWeighting(Double.parseDouble(w.getText()));
	 										a.setWeighting(Double.parseDouble(w.getText()));
	 										c = assesmentClass;

	 									}
	 						}

	 							
		    		}		
				}
					
				markingPanel.setMyMb(mB);
				markingPanel.refreshClasses(null, null, null, mB.getClasses());
		    }
			
		});
		w.getDocument().addDocumentListener(new DocumentListener() {


			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!w.getText().matches("-?\\d+(\\.\\d+)?")){
				 JOptionPane.showMessageDialog(null, "must be numeric", "Dialog",
					        JOptionPane.ERROR_MESSAGE);
				}else if (Double.parseDouble(w.getText())>100){
					JOptionPane.showMessageDialog(null, "an class can only have an total assesment worth of 100", "Dialog",
					        JOptionPane.ERROR_MESSAGE);
				}else{
					for(Subject_Class c : mB.getClasses()){
			    		if(c.equals(assesmentClass)){
		 						for(Assessment a : c.getAssessments()){
		 									if(a.equals(myAssesment)){
		 										myAssesment.setWeighting(Double.parseDouble(w.getText()));
		 										a.setWeighting(Double.parseDouble(w.getText()));
		 									}
		 						}

		 							
			    		}		
					}
						myAssesment.setWeighting(Double.parseDouble(w.getText()));

					myAssesment.setWeighting(Double.parseDouble(w.getText()));
					markingPanel.setMyMb(mB);
					markingPanel.refreshClasses(null, null, null, mB.getClasses());
				 }
				}

			@Override
			public void removeUpdate(DocumentEvent e) {
	            System.out.println("2");
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				System.out.print("3");	    	//do whatever you want here the user has entered some text
				
			}
	      }
	    );
		
		w.setPreferredSize(new Dimension(50, 25));
		weighting.setOpaque(true);
		weighting.setBackground(Color.lightGray);
		
		displayName.setOpaque(true);
		displayName.setBackground(Color.CYAN);

		GridBagConstraints g = new GridBagConstraints();
		
		g.gridy = 1;

		g.fill = GridBagConstraints.HORIZONTAL;	
		labels.add(displayName,g);
		g.gridy = 2;
		labels.add(weighting,g); 		labels.add(w,g); 


		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
		c.gridy = 1;
		mainPanel.add(labels);
		

			
			String[][] data1 = null;
			
			String[] columnNames = new String[6];
			columnNames[0] = "First Name";
			columnNames[1] = "Last Name";		
			columnNames[2] = "Mark";		
			columnNames[3] = "ObjectClass";		
			columnNames[4] = "ObjectAssesment";		
			columnNames[5] = "ObjectStudent";		
			
			final assementTableModel model = new assementTableModel(data1, columnNames);

	 		final JTable table = new JTable(model);
	        table.getColumnModel().getColumn(2).setCellRenderer(new tableValidator());

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
	 		table.getColumnModel().getColumn(0).getModelIndex();
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
 						//String value = (String) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
 						//System.out.println(value);
 						System.out.println(e.getColumn() + " --" +e.getFirstRow());
 						
 						Subject_Class returC = (Subject_Class) model.getValueAt(e.getFirstRow(), 3);
 						Assessment returA = (Assessment) model.getValueAt(e.getFirstRow(), 4);
 						
 						Student returS = (Student) model.getValueAt(e.getFirstRow(), 5);
 						String newFName = (String) (model.getValueAt(e.getFirstRow(), 0));
 						String newLName = (String) (model.getValueAt(e.getFirstRow(), 1));
 						if(((String) model.getValueAt(e.getFirstRow(), 2)).matches("-?\\d+(\\.\\d+)?")){
	 						Double newMark =  Double.valueOf((String) model.getValueAt(e.getFirstRow(), 2));
	 						
	 						//marking
	 						returS.setGivenName(newFName);
	 						returS.setSurname(newLName);
	 						
	 						for(Subject_Class c : mB.getClasses()){
	 							if(c.equals(returC)){
	 		 						for(Assessment a : c.getAssessments()){
	 		 							if(a.equals(returA)){
	 		 								for(Student s : c.getStudents()){
	 		 									if(s.equals(returS)){
	 		 										a.addMark(s, newMark);
	 		 									}
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
