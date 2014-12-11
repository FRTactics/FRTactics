/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.gameStates;

import java.awt.CardLayout;
import model.GameManager;
import static model.gameStates.GameState.gm;
import view.LoadoutMenu;
import view.ingame.GameWindow;

/**
 * @author Charlie
 */
public class P2LoadoutState extends GameState{
    private static P2LoadoutState instance;

 
    private P2LoadoutState()
    {
        
    }
    public static synchronized P2LoadoutState getInstance()
    {
        if(instance == null){
            instance = new P2LoadoutState();
        }
        return instance;
    }
    
    @Override
    public GameState processEvent(int eventID) 
    {
        CardLayout layout = (CardLayout)gm.getView().getLayout();
        if(eventID == GameManager.BACK_SELECTED)
        {
            layout.previous(gm.getView());
            return nextState(GameState.p1State);
        }
        else if(eventID == GameManager.CONTINUE_SELECTED)
        {
            setLoadout();
            initGame();
            layout.next(gm.getView());
            return nextState(GameState.igState);
        }
        return this;
    }

    @Override
    protected void enter() 
    {    // upon entry of this class
        //CardLayout layout = (CardLayout)gm.getView().getLayout();
        //layout.show(gm.getView(), GameManager.P2_LOADOUT_MENU);
    }

    @Override
    protected void exit() 
    {
        // not needed
        
    }

    @Override
    protected void doActivity() // not needed
    {}
    
    private void setLoadout()
    {          // modify this later to convert the strings into actual units and place them in an array
        LoadoutMenu temp = ((LoadoutMenu)gm.getView().getFrontView());
        gm.populateLoadout(temp.getLoadout(), gm.getP2Loadout(), GameManager.PLAYER_2); 
    }
    private void initGame(){
        GameWindow gameWindow = new GameWindow();
        gameWindow.setName(GameManager.INGAME);
        gm.getView().add(gameWindow, GameManager.INGAME);
        
    }

 
    
}
