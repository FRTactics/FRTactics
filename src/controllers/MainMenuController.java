/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import model.GameManager;

/**
 *
 * @author Charlie
 */
public class MainMenuController extends EventHandler{
    public MainMenuController(GameManager gm, JPanel view){ // create and add listeners, JPanel is a temp. fill in for MainMenuView (not created yet
        super(gm, view);                        
        
        // add listeners to view (views should have methods that allow listeners to be added to specific components
    }
    public class StartButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            gm.processEvent(GameManager.NEW_GAME_SELECTED);
        }
    }
    public class ExitButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            gm.processEvent(GameManager.EXIT_SELECTED);
        }
    }
    public class OptionButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            gm.processEvent(GameManager.OPTIONS_SELECTED);
        }
        
    }
}
