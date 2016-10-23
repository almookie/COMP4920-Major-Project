package graphicalUI;

import main.Markbook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class StatsDisplay extends JFXPanel{
	
	private Markbook mB;
	
	public StatsDisplay(Markbook mB) {
		
		//	Markbook
		this.mB = mB;
		
		
		//	SPLIT VERTICAL (MAIN)
		SplitPane sp1 = new SplitPane();
		sp1.setOrientation(Orientation.HORIZONTAL);
		sp1.setDividerPositions(0.1);
		
/*		sp1.setPrefHeight();
		sp1.setPrefWidth();		
		sp1.setMaxHeight();
		sp1.setMaxWidth();
		sp1.setMinHeight();
		sp1.setMinWidth();*/

		
		//	SIDEBAR (LEFT)
		StackPane sidePane = new StackPane();
		
/*		sp1.setPrefHeight();
		sp1.setPrefWidth();		
		sp1.setMaxHeight();
		sp1.setMaxWidth();
		sp1.setMinHeight();
		sp1.setMinWidth();*/
		
		
		//	SPLIT HORIZONTAL (RIGHT)
		SplitPane sp2 = new SplitPane();
		sp2.setOrientation(Orientation.VERTICAL);
		sp2.setDividerPositions(0.5);

/*		sp2.setPrefHeight();
		sp2.setPrefWidth();		
		sp2.setMaxHeight();
		sp2.setMaxWidth();
		sp2.setMinHeight();
		sp2.setMinWidth();*/
		
		
		//	TOP MENU (TOP)
		StackPane top = new StackPane();
		
/*		top.setPrefHeight();
		top.setPrefWidth();		
		top.setMaxHeight();
		top.setMaxWidth();
		top.setMinHeight();
		top.setMinWidth();*/
		
			//	GRAPH (MIDDLE)
			GridPane middle = new GridPane();
			middle.setPadding(new Insets(0, 10, 0, 10));
			middle.setHgap(20);
			middle.setVgap(20);
			
/*			middle.setPrefHeight();
			middle.setPrefWidth();		
			middle.setMaxHeight();
			middle.setMaxWidth();
			middle.setMinHeight();
			middle.setMinWidth();*/
		
			
			// 	GRAPHS
			NumberAxis markFreq = new NumberAxis();
			CategoryAxis mark = new CategoryAxis();
			BarChart<String, Number> barChart = new BarChart<String, Number>(mark, markFreq);
			barChart.setTitle("Mark Distribution");
	        mark.setLabel("Mark");       
	        markFreq.setLabel("Frequency");
			
	        XYChart.Series<String, Number> markList = new XYChart.Series<String,Number>();
	        markList.setName("Marks");
	        
	        markList.getData().add(new XYChart.Data("1", 10));
	        markList.getData().add(new XYChart.Data("2", 20));
	        markList.getData().add(new XYChart.Data("3", 30));
	        markList.getData().add(new XYChart.Data("4", 20));
	        markList.getData().add(new XYChart.Data("5", 10));
	        barChart.getData().addAll(markList);
	        
/*			barChart.setPrefHeight();
			barChart.setPrefWidth();		
			barChart.setMaxHeight();
			barChart.setMaxWidth();
			barChart.setMinHeight();
			barChart.setMinWidth();*/
		
	        
	        //	TABLE
	        //	Name Column
			TableColumn<testClass, String> nameCol = new TableColumn<testClass, String>("Name");
			nameCol.setMinWidth(200);
			nameCol.setCellValueFactory(new PropertyValueFactory<testClass, String>("name"));
			
			//	Mark Column
			TableColumn<testClass, Integer> markCol = new TableColumn<testClass, Integer>("Mark");
			markCol.setMinWidth(100);
			markCol.setCellValueFactory(new PropertyValueFactory<testClass, Integer>("mark"));
			
			//	Table
			TableView<testClass> table = new TableView<testClass>();
			table.setItems(getData());
			table.getColumns().addAll(nameCol, markCol);
	        
/*			table.setPrefHeight();
			table.setPrefWidth();		
			table.setMaxHeight();
			table.setMaxWidth();
			table.setMinHeight();
			table.setMinWidth();*/
			
			//	TREE
			
	        TreeItem<String> rootItem = new TreeItem<String> ("Inbox");
	        rootItem.setExpanded(true);
	        for (int i = 1; i < 6; i++) {
	            TreeItem<String> item = new TreeItem<String> ("Message" + i);            
	            rootItem.getChildren().add(item);
	        }
	        TreeView<String> tree = new TreeView<String> (rootItem);
			
			// 	TOP PANEL
			//sp1.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
			//sp2.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
			//sp1.getItems().add(sp2);
			//sp2.getItems().add(barChart);
			
			sp1.getItems().addAll(sidePane, sp2);
			sp2.getItems().addAll(top, middle);
			sidePane.getChildren().add(tree);
			middle.add(barChart, 1, 0);
			middle.add(table, 2, 0);
			
			Scene scene = new Scene(sp1);
			this.setScene(scene);
			
	}
	
	public ObservableList<testClass> getData() {
		ObservableList<testClass> stuff = FXCollections.observableArrayList();
		stuff.add(new testClass("asdfa", 10));
		stuff.add(new testClass("a", 12));
		stuff.add(new testClass("qwer", 15));
		stuff.add(new testClass("bob", 1));
		
		return stuff;
	}

}
