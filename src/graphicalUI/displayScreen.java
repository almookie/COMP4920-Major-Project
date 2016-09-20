package graphicalUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class displayScreen {
	
    private static void createAndShowGUI() {
    	 final JFrame frame = new JFrame();
         frame.setTitle("Test Frame");
         
         //Note: This gives the effect of the page expanding 
         //from the center of the page
         
         //Center the display
         frame.setLocationRelativeTo(null);

         frame.setLayout(new BorderLayout());
         frame.add(new Button("Menue"),   BorderLayout.WEST);
         frame.add(new Button("PAne"), BorderLayout.CENTER);
         //maximise to display size
         frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
