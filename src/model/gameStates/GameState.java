/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.gameStates;

import model.GameManager;

/**
 *
 * @author Charlie
 */
public abstract class GameState {       // abstact Game State class, acts as a contract for the states
    protected static GameManager gm;      
    // states of the state machine
    protected static MainMenuState mmState;
    protected static OptionsMenuState omState;
    protected static P1LoadoutState p1State;
    protected static P2LoadoutState p2State;
    protected static SettingsMenuState smState;
    protected static InGameState igState;
    
    public static GameState start(GameManager manager){     // create all other game states and then move into Main Menu State
        gm = manager;                                       
        // instantiate all of states
        mmState = MainMenuState.getInstance();
        p1State = P1LoadoutState.getInstance();
        p2State = P2LoadoutState.getInstance();
        smState = SettingsMenuState.getInstance();
        igState = InGameState.getInstance();
        mmState.enter();    // enter the first state, the main menu state
        return mmState;     // the abstract Game State is then set the the main menu state
    }
    public abstract GameState processEvent(int eventID); //abstract methods that subclasses will implement
    protected abstract void enter();                        // abstract method to handle what happens upon entry
    protected abstract void exit();                         // abstract method to handle what happens upon exit
    protected abstract void doActivity();                   // abstract method for handling any activity
    public GameState nextState(GameState newState){       // method that subclasses will use to move to different states
        if (this == newState) return this;                  // if the state is equal to the other state, just return the current state
        else {                                              // else, move to the new state
            this.exit();
            newState.enter();
            return newState;
        }
    }
    public GameState getState(){                // gets the current game state
        return this;
    }
    
   
    
}
