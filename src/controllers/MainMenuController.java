/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import model.GameManager;
import view.GameButton;
import view.MainMenu;

/**
 *
 * @author Charlie
 */
public class MainMenuController extends EventHandler{
    private MainMenu menu;
    public MainMenuController(GameManager gm, MainMenu m){ // create and add listeners, JPanel is a temp. fill in for MainMenuView (not created yet
        super(gm);        
        menu = m;
        menu.addNewGameButtonController(new StartButtonListener(menu.getNewGameButton()));
        menu.addOptionsButtonController(new OptionButtonListener(menu.getOptionsButton()));
        menu.addQuitButtonController(new ExitButtonListener(menu.getQuitButton()));
        // add listeners to view (views should have methods that allow listeners to be added to specific components
    }
    public class StartButtonListener extends MouseAdapter{
        private GameButton button;
        public StartButtonListener(GameButton b){
            button = b;
        }
        @Override
        public void mousePressed(MouseEvent e) {
            handleEvent(GameManager.NEW_GAME_SELECTED);
        }
        public void mouseEntered(MouseEvent e) {
            button.animateLeft();  
            System.out.print("FUCK");
        }
        @Override
        public void mouseExited(MouseEvent e) {
            button.animateRight();
       }
    }
    public class ExitButtonListener extends MouseAdapter{
        private GameButton button;
        public ExitButtonListener(GameButton b){
            button = b;
        }
        @Override
        public void mousePressed(MouseEvent e) {
            handleEvent(GameManager.EXIT_SELECTED);
        }
        @Override
        public void mouseEntered(MouseEvent e) {
            button.animateLeft();
        }
        @Override
        public void mouseExited(MouseEvent e) {
            button.animateRight();
       }
    }
    public class OptionButtonListener extends MouseAdapter{
        private GameButton button;
        public OptionButtonListener(GameButton b){
            button = b;
        }
        @Override
        public void mousePressed(MouseEvent e) {
            handleEvent(GameManager.OPTIONS_SELECTED);
        }
        @Override
        public void mouseEntered(MouseEvent e) {
            button.animateLeft();  
            System.out.print("FUCK");
        }
        @Override
        public void mouseExited(MouseEvent e) {
            button.animateRight();
       }
    }
}
