/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import com.sun.prism.paint.Color;
import controllers.screenControllers.LoadoutController;
import controllers.screenControllers.MainMenuController;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Painter;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.plaf.ColorUIResource;
import model.GameManager;
import model.ImageContainer;

/**
 *
 * @author Charlie
 */
public class GameApp{
    private View view;  // container that holds all of the screens
    public static JFrame frame;   
    private static GameManager gm;  // static instance of the game manager
    // screens
    private MainMenu mainMenu;
    private LoadoutMenu p1LoadoutMenu;
    private LoadoutMenu p2LoadoutMenu;
    // controllers
    private MainMenuController mmController;
    private LoadoutController p1LoadoutController;
    private LoadoutController p2LoadoutController;
    public GameApp(){
        UIManager.put("nimbusBase" , new ColorUIResource(0x1C, 0x1C, 0x1C));
        //UIManager.put("nimbusBlueGrey" , Color.RED);
        //UIManager.put("control" , Color.RED);
        Painter <Component> p = new Painter<Component>(){

            @Override
            public void paint(Graphics2D g, Component c, int width, int height) {
                g.setColor(c.getBackground());
            }
            
        };
        UIManager.put("nimbusSelection", Color.RED);
        UIManager.put("TabbedPane:TabbedPaneTab[Enabled].backgroundPainter", p);
        try{
            UIManager.setLookAndFeel(getLookAndFeelClassName("Nimbus"));
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        }
        catch(Exception ex){
            
        }
        // load in all the panels beforehand except map, that can be loaded later after we get the game settings
        // and then load all of them
        frame = new JFrame();
        SwingUtilities.updateComponentTreeUI(frame);
        view = new View();
        // instantiate all of the screens
        mainMenu = new MainMenu();
        
        p1LoadoutMenu = new LoadoutMenu(GameManager.P1_LOADOUT_MENU, ImageContainer.getInstance().retrieveMenuImage(ImageContainer.MenuImage.P1_LOADOUT_BACKGROUND));
        p2LoadoutMenu = new LoadoutMenu(GameManager.P2_LOADOUT_MENU, ImageContainer.getInstance().retrieveMenuImage(ImageContainer.MenuImage.P2_LOADOUT_BACKGROUND));
        // instantiate all of the controllers using the appropriate screens
        mmController = new MainMenuController(mainMenu);
        p1LoadoutController = new LoadoutController(p1LoadoutMenu);
        p2LoadoutController = new LoadoutController(p2LoadoutMenu);
        // add the screens to the view, using the string associated with that menu
        view.add(mainMenu, GameManager.MAIN_MENU);
        view.add(p1LoadoutMenu, GameManager.P1_LOADOUT_MENU);
        view.add(p2LoadoutMenu, GameManager.P2_LOADOUT_MENU);
        
        // instantiate the GameManager
        gm = GameManager.getInstance();
        gm.initialize(view);            // immediately initialize it with the view, this also starts up the state machine
        // set of the frame to make it pretty
        frame.setTitle("FRTactics");
        //frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        //frame.setUndecorated(true);
        frame.setLayout(new BorderLayout());
        frame.add(view, BorderLayout.CENTER);   // add the view to the frame
        frame.setMinimumSize(new Dimension(1200, 800));
        frame.setVisible(true);                 // allow the frame to be visible
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        mainMenu.updateScreen();
        p1LoadoutMenu.updateScreen();
        p2LoadoutMenu.updateScreen();
        frame.revalidate();
        frame.repaint();
    }
    private static String getLookAndFeelClassName(String nameSnippet){
        LookAndFeelInfo[] plafs = UIManager.getInstalledLookAndFeels();
        for (LookAndFeelInfo info : plafs) {
            if (info.getName().contains(nameSnippet)) {
                return info.getClassName();
            }
        }
        return null;
    }

    public static void main(String[] args){         // main method, all it needs to do is create an instance of the GameApp
        GameApp game = new GameApp();
    }
}
