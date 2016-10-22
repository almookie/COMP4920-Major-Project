package graphicalUI;

import java.awt.BorderLayout;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import main.Grade;
import main.Markbook;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import javax.swing.JTextField;

public class statsScreen extends JPanel {

	private Markbook mB;

	private int SIDEBAR_WIDTH = 200;
	private int GRAPH_WIDTH = 500;
	private int STATS_WIDTH = 200;

	
	private int FILTER_HEIGHT = 150;
	private int GRAPH_HEIGHT = 500;
	private int STATS_HEIGHT = 400;
	
	private JPanel filterPanel;
	private JPanel graphPanel;
	
	private double height;
	private double width;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	
	
	public statsScreen(Markbook mB, double height, double width) {
	

		this.setSize(1980,1080);
		this.height = height;
		this.width = width;
		System.out.println(height + " " + width);
		
		double[] values = new double[100];
		Random rand = new Random();
		for(int i = 0; i < 100; i++) {
			values[i] = rand.nextInt(100);
		}
		
		HistogramDataset dataset = new HistogramDataset();
		dataset.addSeries("Series 1", values, 40);
		JFreeChart chart = ChartFactory.createHistogram("TITLE", "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, true);
		ChartPanel statsPanel = new ChartPanel(chart);
		
		JPanel filterPanel = new JPanel();
		filterPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		//JPanel statsPanel = new JPanel();
		statsPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		
		JPanel sideBarPanel = new JPanel();
		
		sideBarPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		table = new JTable();
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"Mode", 4},
				{"Mean", 4.5},
				{"Median", 5},
				{"Range", 4},
				{"Std Deviation", 2},
				{"Q1", 1.2},
				{"Q2", 2.2},
				{"Q3", 3.3},
				{"Q4", 5.4},
				{"Min", 1},
				{"Max", 10},
			},
			new String[] {
				"New column", "New column"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		table_3 = new JTable();
		table_3.setModel(new DefaultTableModel(
			new Object[][] {
				{"Total Students", "52"},
				{"Present", "2"},
				{"Absent", "2"},
				{"Zero (Present)", "1"},
				{"Failed", "6"},
				{"Perfect", "2"},
			},
			new String[] {
				"New column", "New column"
			}
		));
		table_3.getColumnModel().getColumn(0).setPreferredWidth(140);
		table_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(sideBarPanel, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE)
					.addGap(53)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(statsPanel, GroupLayout.PREFERRED_SIZE, 729, GroupLayout.PREFERRED_SIZE)
							.addGap(31)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(table_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
								.addComponent(table_3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(276)
							.addComponent(table, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
						.addComponent(filterPanel, GroupLayout.DEFAULT_SIZE, 1586, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(sideBarPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(filterPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(109)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(table, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
										.addComponent(table_3, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
									.addGap(33)
									.addComponent(table_1, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
								.addComponent(statsPanel, GroupLayout.PREFERRED_SIZE, 531, GroupLayout.PREFERRED_SIZE))
							.addGap(504)))
					.addContainerGap())
		);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{"STUDENT", "MARK"},
				{"Jacob", "4"},
				{"John", "2"},
				{"Brandon", "5"},
				{"James", "3"},
				{"Bob", "7"},
				{"Jackie", "6"},
				{"Wob", "10"},
				{"Tom", "2"},
				{"Jack", "1"},
			},
			new String[] {
				"New column", "New column"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JCheckBox chckbxAllStudents = new JCheckBox("Show All Students");
		
		JLabel lblNewLabel = new JLabel("Search Option 1");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel label_5 = new JLabel("Search Option 1");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JLabel label_6 = new JLabel("Search Option 1");
		GroupLayout gl_sideBarPanel = new GroupLayout(sideBarPanel);
		gl_sideBarPanel.setHorizontalGroup(
			gl_sideBarPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(table_2, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
				.addGroup(gl_sideBarPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(chckbxAllStudents)
					.addContainerGap(200, Short.MAX_VALUE))
				.addGroup(gl_sideBarPanel.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_sideBarPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_sideBarPanel.createSequentialGroup()
							.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_sideBarPanel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_sideBarPanel.createSequentialGroup()
								.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(gl_sideBarPanel.createSequentialGroup()
								.addComponent(lblNewLabel)
								.addGap(213))
							.addGroup(gl_sideBarPanel.createSequentialGroup()
								.addGroup(gl_sideBarPanel.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(textField, Alignment.LEADING)
									.addComponent(textField_3, Alignment.LEADING)
									.addComponent(textField_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE))
								.addContainerGap()))))
		);
		gl_sideBarPanel.setVerticalGroup(
			gl_sideBarPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_sideBarPanel.createSequentialGroup()
					.addGap(36)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(23)
					.addComponent(label_6)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label_5)
					.addGap(18)
					.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 247, Short.MAX_VALUE)
					.addComponent(chckbxAllStudents)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(table_2, GroupLayout.PREFERRED_SIZE, 734, GroupLayout.PREFERRED_SIZE))
		);
		sideBarPanel.setLayout(gl_sideBarPanel);
		
		statsPanel.setLayout(new BorderLayout(0, 0));
		
		
		String[] subjList = {"Math", "Science", "English", "History", "Geography"};
		String[] classList = {"MA101", "MA102", "MA103", "MA104", "MA105"};
		String[] assessList = {"Quiz 1", "Assignment 1", "Quiz 2", "Final Exam"};

		String[] gradeList = new String[mB.getGrades().size()];
		
		int i = 0;
		
		for(Grade g : mB.getGrades()) {
			gradeList[i] = Integer.toString(g.getGraduationYear());
			i++;
		}
		
		

		JComboBox gradeInput = new JComboBox(gradeList);
		//gradeInput.addActionListener(act1);

		JComboBox subjectInput = new JComboBox(subjList);
		//gradeInput.addActionListener(act2);
		
		JComboBox classInput = new JComboBox(classList);
		gradeInput.addActionListener( new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		          System.out.println("Command: " + actionEvent.getActionCommand());
		          ItemSelectable is = (ItemSelectable)actionEvent.getSource();
		          //System.out.println(", Selected: " + selectedString(is));
		      }
		});
		
		JComboBox assessmentInput = new JComboBox(assessList);
		gradeInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox b = (JComboBox)e.getSource();
				System.out.println("Assess " + b.getSelectedItem());
			}
		});
		
		JLabel label = new JLabel("Grade");
		
		JLabel label_1 = new JLabel("Subject");
		
		JLabel label_2 = new JLabel("Class");
		
		JLabel label_3 = new JLabel("Assessment");
		
		JButton button = new JButton(">");
		
		JButton button_1 = new JButton("<");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton button_3 = new JButton(">");
		
		JButton button_5 = new JButton("<");
		
		JButton button_6 = new JButton("<");
		
		JButton button_4 = new JButton(">");
		
		JButton button_7 = new JButton("<");
		
		JButton button_2 = new JButton(">");
		
		GroupLayout gl_filterPanel = new GroupLayout(filterPanel);
		gl_filterPanel.setHorizontalGroup(
			gl_filterPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_filterPanel.createSequentialGroup()
					.addGroup(gl_filterPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_filterPanel.createSequentialGroup()
							.addGap(9)
							.addComponent(button_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(gradeInput, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_filterPanel.createSequentialGroup()
							.addGap(49)
							.addComponent(label)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button)
					.addGap(46)
					.addComponent(button_5)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_filterPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_filterPanel.createSequentialGroup()
							.addComponent(subjectInput, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
						.addComponent(label_1))
					.addGap(38)
					.addComponent(button_6)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_filterPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(classInput, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_4)
					.addGap(58)
					.addComponent(button_7)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_filterPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_filterPanel.createSequentialGroup()
							.addComponent(assessmentInput, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_2))
						.addComponent(label_3))
					.addContainerGap(482, Short.MAX_VALUE))
		);
		gl_filterPanel.setVerticalGroup(
			gl_filterPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_filterPanel.createSequentialGroup()
					.addContainerGap(20, Short.MAX_VALUE)
					.addGroup(gl_filterPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(label_1)
						.addComponent(label_2)
						.addComponent(label_3))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_filterPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_1)
						.addComponent(gradeInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button)
						.addComponent(button_5)
						.addComponent(subjectInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_3)
						.addComponent(classInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_4)
						.addComponent(button_6)
						.addComponent(button_7)
						.addComponent(assessmentInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_2))
					.addContainerGap())
		);
		filterPanel.setLayout(gl_filterPanel);
		setLayout(groupLayout);
		
		
	}
}
