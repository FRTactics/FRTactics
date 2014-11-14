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
public class P1LoadoutState extends GameState{
    private static P1LoadoutState instance;
    private P1LoadoutState(){
        
    }
    public static synchronized P1LoadoutState getInstance(){
        if(instance == null){
            instance = new P1LoadoutState();
        }
        return instance;
    }
    @Override
    public GameState processEvent(int eventID) {
        if(eventID == GameManager.ADD_UNIT_SELECTED){
            // do add unit stuff            // retrieve selected entry from table view and add to P1's loadout
            return this;
        }
        else if(eventID == GameManager.REMOVE_UNIT_SELECTED){
            // do remove unit stuff         // retrieve selected entry from p1's loadout table view
            return this;
        }
        else if(eventID == GameManager.CONTINUE_SELECTED){
            return nextState(GameState.p2State);
        }
        return this;
    }

    @Override
    protected void enter() {
        CardLayout layout = (CardLayout)gm.getView().getLayout();
        layout.show(gm.getView(), GameState.P1_LOADOUT_MENU);
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
