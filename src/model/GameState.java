/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.awt.Graphics;

/**
 *
 * @author Charlie
 */
public abstract class GameState {
    protected static GameManager gm;
    
    protected static MainMenuState mmState;
    protected static OptionsMenuState omState;
    protected static P1LoadoutState p1State;
    protected static P2LoadoutState p2State;
    protected static SettingsMenuState smState;
    protected static InGameState igState;
    protected final static String MAIN_MENU = "Main Menu";
    protected final static String P1_LOADOUT_MENU = "P1 Loadout Menu";
    protected final static String P2_LOADOUT_MENU = "P2 Loadout Menu";
    protected final static String OPTIONS_MENU = "Option Menu";
    protected final static String SETTINGS_MENU = "Settings Menu";
    protected final static String INGAME = "InGame";
    
    public GameState(){
        
    }
    public static GameState start(GameManager manager){
        gm = manager;
        mmState = new MainMenuState();
        p1State = new P1LoadoutState();
        p2State = new P2LoadoutState();
        smState = new SettingsMenuState();
        igState = new InGameState();
        mmState.enter();
        return mmState;
    }
    public abstract GameState processEvent(int eventID); //abstract methods that subclasses will implement
    protected abstract void enter();            
    protected abstract void exit();                  
    protected abstract void doActivity();               
    public GameState nextState(GameState newState){       // method that subclasses will use to move to different states
        if (this == newState) return this;
        else {
            this.exit();
            newState.enter();
            return newState;
        }
    }
    public GameState getState(){
        return this;
    }
    
   
    
}
