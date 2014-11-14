/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controllers.MainMenuController;
import view.View;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JFrame;
import model.GameManager;

/**
 *
 * @author Charlie
 */
public class GameApp{
    private View view;
    private JFrame frame;
    private static GameManager gm;
    private MainMenu mainMenu;
    
    private MainMenuController mmController;
    public GameApp(){
        // load in all the panels beforehand except map, that can be loaded later after we get the game settings
        // and then load all of them
        mainMenu = new MainMenu();
        mmController = new MainMenuController(gm, mainMenu);
        view = new View();
        frame = new JFrame();
        gm = new GameManager(view);
        gm.setView(view);
        
        view.add(mainMenu, GameManager.MAIN_MENU);
        
        frame.setTitle("FRTactics");
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());
        frame.add(view, BorderLayout.CENTER);
        frame.setUndecorated(true);
        
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
    }

    public static void main(String[] args){
        GameApp game = new GameApp();
    }
}
