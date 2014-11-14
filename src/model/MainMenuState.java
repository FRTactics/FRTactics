/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.awt.CardLayout;
import java.awt.Graphics;

/**
 *
 * @author Charlie
 */
public class MainMenuState extends GameState{
    private static MainMenuState instance;


    private MainMenuState(){
        // initialize state's associated panel and add to the viewPanel's layout
    }
    public static synchronized MainMenuState getInstance(){
        if(instance == null){
            instance = new MainMenuState();
        }
        return instance;
    }
    @Override
    public GameState processEvent(int eventID) {
        if(eventID == GameManager.NEW_GAME_SELECTED){
            return nextState(GameState.p1State);
        }
        else if(eventID == GameManager.EXIT_SELECTED){
            
            gm.endGame();
            return this;
            
        }
        else if(eventID == GameManager.OPTIONS_SELECTED){
            return nextState(GameState.omState);
        }
        else{
            return this;
        }
    }

    @Override
    protected void enter() {
       CardLayout layout = (CardLayout)gm.getView().getLayout();
       layout.show(gm.getView(), GameState.MAIN_MENU);
    }

    @Override
    protected void exit() {
        
    }

    @Override
    protected void doActivity() {
        
    }

   

  

    
}
