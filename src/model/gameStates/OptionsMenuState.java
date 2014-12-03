/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.gameStates;

import java.awt.CardLayout;
import model.GameManager;
/*
 *
 * @author Charlie
 */
public class OptionsMenuState extends GameState{        // state that handles all of the events for the Options Menu
    private static OptionsMenuState instance;
    private OptionsMenuState(){
        
    }
    public static synchronized OptionsMenuState getInstance(){      // retrieve the instance of the Options Menu State
        if(instance == null){                                       // if one does not exist, create one
            instance = new OptionsMenuState();
        }
        return instance;            
    }
    @Override
    public GameState processEvent(int eventID) {        // process all events that are thrown while in this state
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void enter() {                       
         
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
