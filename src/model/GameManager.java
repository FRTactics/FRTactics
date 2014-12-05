/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import model.gameStates.GameState;
import javax.swing.JList;
import javax.swing.JPanel;
import model.classSystem.*;
import view.GameApp;
import view.LoadoutListModel;
import view.View;
import view.ingame.GlassPane;

public class GameManager
{
    
    protected static GameState gState;              // abstract game state
    protected View gameView;                        // game view, needed for switching screens
    private static GameManager instance;            // instance used to implement singleton design
    // Strings that are associated with each of the screens
    public final static String MAIN_MENU = "Main Menu";
    public final static String P1_LOADOUT_MENU = "P1 Loadout Menu";
    public final static String P2_LOADOUT_MENU = "P2 Loadout Menu";
    public final static String OPTIONS_MENU = "Option Menu";
    public final static String SETTINGS_MENU = "Settings Menu";
    public final static String INGAME = "InGame";
    // string that is associated with the glass pane, so we can remove it
    public static final String GLASSPANE = "GlassPane";
    // ArrayLists for the player loadouts
    private ArrayList<DefaultClass> p1Loadout;
    private ArrayList<DefaultClass> p2Loadout;
    // the Jlists are in here so that we can clear them
    private JList p1LoadoutJList;
    private JList p2LoadoutJList;
    // menu events
    public static final int NEW_GAME_SELECTED = 10;
    public static final int EXIT_SELECTED = 20;
    public static final int CONTINUE_SELECTED = 30;      // AKA Launch Game in settings menu
    public static final int BACK_SELECTED = 40;
    public static final int ADD_UNIT_SELECTED = 50;
    public static final int REMOVE_UNIT_SELECTED = 60;
    public static final int OPTIONS_SELECTED = 70;
    public static final int RETURN_TO_MAIN = 80;
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
    
    public void initialize(View view)       // initialize view and state,
    {                                       // needs to be called right after GM is created
        p1Loadout = new ArrayList<>();
        p2Loadout = new ArrayList<>();
        setView(view);
        gState = GameState.start(this);     // start up state machine
    }
    public static GameManager getInstance()     // get the instance of the GM
    {
        if(instance == null)                    // if there is no instance of the Game Manager, create one
        {
            instance = new GameManager(); 
        }
        return instance;                       
    }
    private void setView(View view)             // sets the view for later usage
    {
        gameView = view;
    }
    public View getView()       // retrieves the view
    {
        return gameView;
    }
    public void removeGameWindow(){
        Component[] components = gameView.getComponents();
        System.out.println(components.length);
        for(int i = 0; i < components.length; i++){
            System.out.println(components[i].getName());
            if(components[i].getName().equals(GameManager.INGAME)){
                gameView.remove(components[i]);
                JPanel panel = new JPanel();
                GameApp.frame.removeKeyListener(GameApp.frame.getKeyListeners()[0]);
            }
        }
        
    }
    public static GameState getGameState()  // returns the game state
    {
        return gState;
    }
    public void processEvent(int eventID)   // process the event
    {
        gState = gState.processEvent(eventID);
    }
    public void endGame()                       // ends the program and closes the JFrame
    {
        getView().getParent().setVisible(false);
        System.exit(0);
    }
    public void populateLoadout(ArrayList<String> source, ArrayList <DefaultClass> target){
        target.clear();         // clear the target before hand
        for(int i = 0; i < source.size(); i++){
            switch(source.get(i)){
                case "Warrior":
                    target.add(new WarriorClass());
                    break;
                case "Archer":
                    target.add(new ArcherClass());
                    break;
                case "Rogue":
                    target.add(new RogueClass());
                    break;
                case "Healer":
                    target.add(new HealerClass());
                    break;
                case "Wizard":
                    target.add(new WizardClass());
                    break;
                default:
                    break;
            }
        }
    }
    public ArrayList<DefaultClass> getP1Loadout(){
        return p1Loadout;
    }
    public ArrayList<DefaultClass> getP2Loadout(){
        return p2Loadout;
    }
    public void setLoadoutJList(String menu, JList list){   // takes in which either P1_LOADOUT_MENU or P2_LOADOUT_MENU
        switch(menu){
            case GameManager.P1_LOADOUT_MENU:
                p1LoadoutJList = list;
                break;
            case GameManager.P2_LOADOUT_MENU:
                p2LoadoutJList = list;
                break;
            default:
                break;
        }
    }
    
    public void clearLoadouts(){    // clear the loadouts
        p1Loadout.clear();
        p2Loadout.clear();
        LoadoutListModel model1 = (LoadoutListModel)p1LoadoutJList.getModel();
        model1.clear();
        LoadoutListModel model2 = (LoadoutListModel)p2LoadoutJList.getModel();
        model2.clear();
    } 
}
