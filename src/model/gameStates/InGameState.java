/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.gameStates;

import java.awt.CardLayout;
import model.GameManager;
import model.GamePlayManager;
import view.GameApp;

// name subject to change
public class InGameState extends GameState
    {        
    private static InGameState instance;        
    private InGameState()
    {}
    
    public static synchronized InGameState getInstance()
    {
        if(instance == null){
            instance = new InGameState();
        }
        return instance;
    }
    @Override
    public GameState processEvent(int eventID) {
        CardLayout layout = (CardLayout)gm.getView().getLayout();
        if(eventID == GameManager.RETURN_TO_MAIN){
            layout.show(gm.getView(), GameManager.INGAME);
            return nextState(GameState.mmState);
        }
        else{
            return this;
        }
    }

    @Override
    protected void enter() 
    {
        GamePlayManager manager = new GamePlayManager();
        manager.displayCharacterSelectionBar();
        GameApp.frame.repaint();
    }

    @Override
    protected void exit() {
    }
    

    @Override
    protected void doActivity() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
