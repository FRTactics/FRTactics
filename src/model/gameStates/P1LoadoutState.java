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
        if(eventID == GameManager.BACK_SELECTED){  // move back a state (to Main menu)
            gm.clearLoadouts();
            return nextState(GameState.mmState);
        }
        else if(eventID == GameManager.CONTINUE_SELECTED){  // move to the next state
            return nextState(GameState.p2State);
        }
        return this;
    }

    @Override
    protected void enter() {            // upon entry, move associated screen to the front of the view
        CardLayout layout = (CardLayout)gm.getView().getLayout();
        layout.show(gm.getView(), GameManager.P1_LOADOUT_MENU);
       
    }

    @Override
    protected void exit() {
        setLoadout();
    }

    @Override
    protected void doActivity() {
    }
    private void setLoadout(){          // modify this later to convert the strings into actual units and place them in an array
        LoadoutMenu temp = ((LoadoutMenu)gm.getView().getFrontView());
        gm.populateLoadout(temp.getLoadout(), gm.getP1Loadout()); 
    }



    
}
