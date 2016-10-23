package graphicalUI.statsSearchBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
 
import javax.swing.JFrame;
 
import main.Assessment;
import main.Markbook;
import main.Student;
import main.Subject_Class;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
 
public class GraphPanel extends JFXPanel {
   
    private JFXPanel graphPanel;
    private Assessment assessment;
   
    private Markbook mB;
   
    public GraphPanel(Markbook markbook) {
        this.mB = markbook;
        JFXPanel graphPanel = new JFXPanel();
    }
   
    public void refreshGraph() {
       
        NumberAxis markFreq = new NumberAxis();
        CategoryAxis mark = new CategoryAxis();
        BarChart<String, Number> barChart = new BarChart<String, Number>(mark, markFreq);
        barChart.setTitle("Mark Distribution");
        mark.setLabel("Mark");      
        markFreq.setLabel("Frequency");
       
        XYChart.Series<String, Number> markList = new XYChart.Series<String,Number>();
        markList.setName("Marks");
       
        ArrayList<Double> marks = assessment.getMarkList();
       
        HashMap<Double, Integer> tempMap = new HashMap<Double, Integer>();
       
        for(Double m : marks) {
            tempMap.put(m, tempMap.get(m)+1);
        }
       
        for(Map.Entry<Double, Integer> entry : tempMap.entrySet()) {
            markList.getData().add(new XYChart.Data(Double.toString(entry.getKey()), entry.getValue()));
        }
       
        barChart.getData().addAll(markList);
        this.add(barChart);
    }
   
    public void setAssessment(Assessment ass) {
        this.assessment = ass;
    }
   
    public static void initFX(JFXPanel fxPanel) {
        // This method is invoked on the JavaFX thread
        Scene scene = createScene();
        fxPanel.setScene(scene);
    }
   
    private static Scene createScene() {
        Group  root  =  new  Group();
        Scene  scene  =  new  Scene(root, Color.ALICEBLUE);
        return (scene);
    }
   
}