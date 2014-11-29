package view.ingame;


import controllers.screenControllers.ScrollPaneController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import view.GameApp;

public class GameWindow extends JPanel
{
    public static Dimension windowSize;
    public static JScrollPane pane = new JScrollPane();
    public GameWindow(){
        setLayout(new BorderLayout());
        createAndShowGUI();
    }
    public final void createAndShowGUI()
    {
        //Get the size of the windwow
        windowSize = GameApp.frame.getSize();
        //Make the Scroll Pane Visible
        pane.setVisible(true);
        //Create the game board
        DrawPanel dp = new DrawPanel(31);
        //add the scroll pane to the frame
        add(pane,BorderLayout.CENTER);
        //add the board to the scroll pane
        pane.setViewportView(dp);
        //Set the preferred size of the scroll pane
        //pane.setPreferredSize(GameApp.frame.getSize());
        //remove the scroll bars
        pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        //remove the boarder
        pane.setBorder((null));
        //place the hexagons on the board
        dp.placeHexagons();
        //Create the glass pane and add it to the frame
        GlassPane glass = new GlassPane();
        glass.setSize(GameApp.frame.getWidth(),GameApp.frame.getHeight());
        GameApp.frame.getRootPane().setGlassPane(glass);        
        //create the pause menu
        PauseDialog dialog = new PauseDialog(GameApp.frame, glass);
        //Create the instance for statspopup
        StatsPopup.createInstance(GameApp.frame);
        //add the listeners
        GameApp.frame.addKeyListener(new ScrollPaneController(pane.getViewport(),dp,this,dialog));
        pane.getVerticalScrollBar().addAdjustmentListener(event -> GameApp.frame.repaint());
        pane.getHorizontalScrollBar().addAdjustmentListener(event -> GameApp.frame.repaint());
    }
}
