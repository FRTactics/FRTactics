/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.gameStates;

import java.awt.CardLayout;
import model.GameManager;

/**
 * @author Charlie
 */
public class P2LoadoutState extends GameState{
    private static P2LoadoutState instance;

 
    private P2LoadoutState(){
        
    }
    public static synchronized P2LoadoutState getInstance(){
        if(instance == null){
            instance = new P2LoadoutState();
        }
        return instance;
    }
    @Override
    public GameState processEvent(int eventID) {
        if(eventID == GameManager.BACK_SELECTED){
            return nextState(GameState.p1State);
        }
        else if(eventID == GameManager.CONTINUE_SELECTED){
            //return nextState(GameState.smState);
        }
        return this;
    }

    @Override
    protected void enter() {    // upon entry of this class
        CardLayout layout = (CardLayout)gm.getView().getLayout();
        layout.show(gm.getView(), GameManager.P2_LOADOUT_MENU);
    }

    @Override
    protected void exit() {
        
    }

    @Override
    protected void doActivity() {
    }


 

 
    
}
