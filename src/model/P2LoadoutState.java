/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import static model.GameState.gm;
import java.awt.CardLayout;
import java.awt.Graphics;

/**
 *
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
        if(eventID == GameManager.ADD_UNIT_SELECTED){
            // do add unit stuff
            return this;
        }
        else if(eventID == GameManager.REMOVE_UNIT_SELECTED){
            // do remove unit stuff
            return this;
        }
        else if(eventID == GameManager.CONTINUE_SELECTED){
            return nextState(GameState.smState);
        }
        return this;
    }

    @Override
    protected void enter() {
        CardLayout layout = (CardLayout)gm.getView().getLayout();
        layout.show(gm.getView(), GameState.P2_LOADOUT_MENU);
    }

    @Override
    protected void exit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doActivity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


 

 
    
}
