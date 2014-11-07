/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import javax.swing.JFrame;
import view.View;

/**
 *
 * @author Charlie
 */
public class GameManager{
    protected static GameState gState;
    protected View gameView;
    // menu events
    public static final int NEW_GAME_SELECTED = 10;
    public static final int EXIT_SELECTED = 20;
    public static final int CONTINUE_SELECTED = 30;      // AKA Launch Game in settings menu
    public static final int BACK_SELECTED = 40;
    public static final int ADD_UNIT_SELECTED = 50;
    public static final int REMOVE_UNIT_SELECTED = 60;
    public static final int OPTIONS_SELECTED = 70;
    // in game events
    public static final int UNIT_SELECTED = 0;
    public static final int MOVE_UNIT_SELECTED = 1;
    public static final int ATTACK_UNIT_SELECTED = 2;
    public static final int DEFEND_UNIT_SELECTED = 3;
    public static final int WAIT_SELECTED = 4;
    public static final int END_TURN_SELECTED = 5;
    public static final int P1_DEFEATED_EVENT = 6;
    public static final int P2_DEFEATED_EVENT = 7;
    // boolean to check for to trigger change events
    protected static boolean isP1Turn = false;        
    protected static boolean isP2Turn = false;
    protected static boolean isP1TeamDefeated = false;
    protected static boolean isP2TeamDefeated = false;
    
    public GameManager(View view){
        setView(view);
        gState = GameState.start(this);
    }
    public void setView(View view){
        gameView = view;
        System.out.println("Hello");
    }
    public View getView(){
        return gameView;
    }
    public static GameState getGameState(){
        return gState;
    }
    public void processEvent(int eventID){
        gState.processEvent(eventID);
    }
    public void endGame(){
        getView().getParent().setVisible(false);
        JFrame frame = (JFrame)getView().getParent();
        frame.dispose();
        System.exit(0);
    }
    public static void setPlayerTurn(int player){
        if(player == 1){
            isP1Turn = true;
            isP2Turn = false;
        }
        else if(player == 2){
            isP1Turn = false;
            isP2Turn = true;
        }
    }
  
    public static String getPlayerTurn(){
        String temp = "";
        if(isP1Turn){
            temp += "Player 1 Turn";
        }
        else{
            temp += "Player 2 Turn";
        }
        return temp;
    }
    
    public static void switchTurns(){
        if(isP1Turn){
            setPlayerTurn(1);
        }
        else{
            setPlayerTurn(2);
        }
    }
    
    
}
