/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.gameStates;

import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
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
            layout.show(gm.getView(), GameManager.MAIN_MENU);
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
    protected void exit() 
    {
        Component glass = GameApp.frame.getGlassPane();
        glass.setVisible(false);
        
        JOptionPane pane = new JOptionPane(new Object[]{"Congrats player, you have won"},JOptionPane.PLAIN_MESSAGE,JOptionPane.DEFAULT_OPTION);
        JDialog dialog = pane.createDialog(GameApp.frame, "Winner");
        dialog.setVisible(true);
    }
    

    @Override
    protected void doActivity() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
