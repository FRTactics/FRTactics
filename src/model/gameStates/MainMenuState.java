/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.gameStates;

import java.awt.CardLayout;
import model.GameManager;

/**
 *
 * @author Charlie
 */
public class MainMenuState extends GameState{       // state that handles all events for the main menu screen
    private static MainMenuState instance;


    private MainMenuState(){

    }
    public static synchronized MainMenuState getInstance(){
        if(instance == null){
            instance = new MainMenuState();
        }
        return instance;
    }
    @Override
    public GameState processEvent(int eventID) {        // events are passed from the Main Menu's controller
        if(eventID == GameManager.NEW_GAME_SELECTED){   // when new game event is thrown, move to the next state (P1 loadout)
            return nextState(GameState.p1State);
        }
        else if(eventID == GameManager.EXIT_SELECTED){  // when exit is selected, close the program
            gm.endGame();
            return this;
        }
        else if(eventID == GameManager.OPTIONS_SELECTED){   // when the options menu is selected, move the the options menu state
            return nextState(GameState.omState);
        }
        else{                   // for any other event that is thrown, just return the current state
            return this;
        }
    }

    @Override
    protected void enter() {            // upon entry of the state, move the associated screen to the front
       CardLayout layout = (CardLayout)gm.getView().getLayout();
       layout.show(gm.getView(), GameManager.MAIN_MENU);
    }

    @Override                   
    protected void exit() {         // upon exit, nothing is really needed
        
    }

    @Override
    protected void doActivity() {       // no activities need to happen while this state is active
        
    }

   

  

    
}
