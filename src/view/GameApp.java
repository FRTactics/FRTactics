/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controllers.screenControllers.MainMenuController;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JFrame;
import model.GameManager;
import model.gameStates.GameState;
import view.View;

/**
 *
 * @author Charlie
 */
public class GameApp{
    private View view;  // container that holds all of the screens
    private JFrame frame;   
    private static GameManager gm;  // static instance of the game manager
    // screens
    private MainMenu mainMenu;
    private P1LoadoutMenu p1LoadoutMenu;
    // controllers
    private MainMenuController mmController;
    
    public GameApp(){
        // load in all the panels beforehand except map, that can be loaded later after we get the game settings
        // and then load all of them
        frame = new JFrame();
        view = new View();
        // instantiate all of the screens
        mainMenu = new MainMenu();
        p1LoadoutMenu = new P1LoadoutMenu();
        // instantiate all of the controllers using the appropriate screens
        mmController = new MainMenuController(mainMenu);
        // add the screens to the view, using the string associated with that menu
        view.add(p1LoadoutMenu, GameManager.P1_LOADOUT_MENU);
        view.add(mainMenu, GameManager.MAIN_MENU);
        // instantiate the GameManager
        gm = GameManager.getInstance();
        gm.initialize(view);            // immediately initialize it with the view, this also starts up the state machine
        // set of the frame to make it pretty
        frame.setTitle("FRTactics");
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setLayout(new BorderLayout());
        frame.add(view, BorderLayout.CENTER);   // add the view to the frame
        frame.setVisible(true);                 // allow the frame to be visible
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
    }

    public static void main(String[] args){         // main method, all it needs to do is create an instance of the GameApp
        GameApp game = new GameApp();
    }
}
