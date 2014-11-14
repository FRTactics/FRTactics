/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.awt.CardLayout;
import static model.GameState.gm;

/**
 *
 * @author Charlie
 */
public class OptionsMenuState extends GameState{
    private static OptionsMenuState instance;
    private OptionsMenuState(){
        
    }
    public static synchronized OptionsMenuState getInstance(){
        if(instance == null){
            instance = new OptionsMenuState();
        }
        return instance;
    }
    @Override
    public GameState processEvent(int eventID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void enter() {
       CardLayout layout = (CardLayout)gm.getView().getLayout();
       layout.show(gm.getView(), GameState.OPTIONS_MENU);   
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
