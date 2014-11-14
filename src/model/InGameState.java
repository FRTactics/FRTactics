/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.awt.CardLayout;
import java.awt.Graphics;
import static model.GameState.gm;

/**
 *
 * @author Charlie
 */
public class InGameState extends GameState{
    private static InGameState instance;
    private InGameState(){
        
    }
    public static synchronized InGameState getInstance(){
        if(instance == null){
            instance = new InGameState();
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
        layout.show(gm.getView(), GameState.INGAME);
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
