/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.gameStates;

import java.awt.CardLayout;
import model.GameManager;
import view.CustomLabel;
import view.LoadoutMenu;

/**
 *
 * @author Charlie
 */
public class P1LoadoutState extends GameState{
    private static P1LoadoutState instance;
    private P1LoadoutState(){
        
    }
    public static synchronized P1LoadoutState getInstance(){    // retrieve instance of this class
        if(instance == null){                                   // if one does not exist, create one
            instance = new P1LoadoutState();
        }
        return instance;
    }
    @Override
    public GameState processEvent(int eventID) {            // process all events associated with this state
        CardLayout layout = (CardLayout)gm.getView().getLayout();
        if(eventID == GameManager.BACK_SELECTED){  // move back a state (to Main menu)
            layout.previous(gm.getView());
            return nextState(GameState.mmState);
        }
        else if(eventID == GameManager.CONTINUE_SELECTED){  // move to the next state
            setLoadout();
            layout.next(gm.getView());
            return nextState(GameState.p2State);
        }
        return this;
    }

    @Override
    protected void enter() {            // upon entry, move associated screen to the front of the view
       
    }

    @Override
    protected void exit() {
        
    }

    @Override
    protected void doActivity() {
    }
    private void setLoadout(){          // modify this later to convert the strings into actual units and place them in an array
        LoadoutMenu temp = ((LoadoutMenu)gm.getView().getFrontView());
        gm.populateLoadout(temp.getLoadout(), gm.getP1Loadout(), GameManager.PLAYER_1); 
    }



    
}
