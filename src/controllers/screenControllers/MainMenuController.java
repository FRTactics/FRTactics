/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers.screenControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import model.GameManager;
import view.MainMenuSwordButton;
import view.MainMenu;

/**
 *
 * @author Charlie
 */
public class MainMenuController extends EventHandler{
    private MainMenu menu;          // view that needs to controlled
    public MainMenuController(MainMenu m){ // create and add listeners, JPanel is a temp. fill in for MainMenuView (not created yet
          
        menu = m;
        menu.addNewGameButtonController(new StartButtonListener());
        menu.addOptionsButtonController(new OptionsButtonListener());
        menu.addQuitButtonController(new QuitButtonListener());
        // add listeners to view (views should have methods that allow listeners to be added to specific components
    }
    public class StartButtonListener extends MouseAdapter{      // listener for the start button
        private MainMenuSwordButton button;
        public StartButtonListener(){              
            button = menu.getNewGameButton();
        }
        @Override
        public void mousePressed(MouseEvent e) {            // upon pressing, move to the next screen( P1 Loadout)
            handleEvent(GameManager.NEW_GAME_SELECTED);
        }
        @Override
        public void mouseEntered(MouseEvent e) {            // animate button to left when mouse enters
            button.animateLeft();  
        }
        @Override
        public void mouseExited(MouseEvent e) {             // animate button to right when mouse exits
            button.animateRight();
       }
    }
    public class QuitButtonListener extends MouseAdapter{       // exit button listener
        private MainMenuSwordButton button;
        public QuitButtonListener(){
            button = menu.getQuitButton();
        }
        @Override
        public void mousePressed(MouseEvent e) {        // close game when button pressed
            handleEvent(GameManager.EXIT_SELECTED);
        }
        @Override
        public void mouseEntered(MouseEvent e) {        // animate button to the left when mouse enters
            button.animateLeft();
        }
        @Override
        public void mouseExited(MouseEvent e) {         // animate button to the right when mouse exits
            button.animateRight();
       }
    }
    public class OptionsButtonListener extends MouseAdapter{     // options button listener
        private MainMenuSwordButton button;
        public OptionsButtonListener(){
            button = menu.getOptionsButton();
        }
        @Override
        public void mousePressed(MouseEvent e) {            // when mouse is pressed, move to options screen
            handleEvent(GameManager.OPTIONS_SELECTED);
        }
        @Override
        public void mouseEntered(MouseEvent e) {            // animate the button to the left when the mouse enters
            button.animateLeft();  
        }
        @Override
        public void mouseExited(MouseEvent e) {             // animate the button to the right when the mouse exits
            button.animateRight();
       }
    }
}
