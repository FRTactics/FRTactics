package view.ingame;


import controllers.screenControllers.ScrollPaneController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class MainWindow
{
    public static Dimension windowSize;
    public static JScrollPane pane = new JScrollPane();
    
    public static void createAndShowGUI()
    {
        //Set up the JFrame
        JFrame frame = new JFrame();
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setUndecorated(true);
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Procedural Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Get the size of the windwow
        windowSize = frame.getSize();
        //Make the Scroll Pane Visible
        pane.setVisible(true);
        //Create the game board
        DrawPanel dp = new DrawPanel(31);
        //add the scroll pane to the frame
        frame.add(pane,BorderLayout.CENTER);
        //add the board to the scroll pane
        pane.setViewportView(dp);
        //remove the scroll bars
        pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        //remove the boarder
        pane.setBorder((null));
        //place the hexagons on the board
        dp.placeHexagons();
        //Create the glass pane and add it to the frame
        GlassPane glass = new GlassPane();
        glass.setSize(frame.getWidth(),frame.getHeight());
        frame.getRootPane().setGlassPane(glass);
        frame.setGlassPane(glass);        
        //create the pause menu
        PauseDialog dialog = new PauseDialog(frame,glass);
        //Create the instance for statspopup
        StatsPopup.createInstance(frame);
        //add the listeners
        frame.addKeyListener(new ScrollPaneController(pane.getViewport(),dp,frame,dialog));
        pane.getVerticalScrollBar().addAdjustmentListener(event -> frame.repaint());
        pane.getHorizontalScrollBar().addAdjustmentListener(event -> frame.repaint());
    }
    /*
    public static void main(String [] agrs)
    {
        java.awt.EventQueue.invokeLater(() -> {
            createAndShowGUI();
        });
    }
    */
}
