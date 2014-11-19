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
public class SettingsMenuState extends GameState{   // state that handles all of the events that occur while this state is active
    private static SettingsMenuState instance;

    private SettingsMenuState(){
        
    }
    public static synchronized SettingsMenuState getInstance(){ // retrieve instance of this state
        if(instance == null){                                   // if one does not exist, create one
            instance = new SettingsMenuState();
        }
        return instance;
    }
    @Override
    public GameState processEvent(int eventID) {            // process any event that occurs
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void enter() {                    // upon entry, move associate screen to the front
       CardLayout layout = (CardLayout)gm.getView().getLayout();
       layout.show(gm.getView(), GameManager.SETTINGS_MENU);
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
