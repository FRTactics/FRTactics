/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers.screenControllers;

import javax.swing.JPanel;
import model.GameManager;

/**
 *
 * @author Charlie
 */
public abstract class EventHandler{     // abstract Event handler class that is used as a contract for controller classes
    public GameManager gm;              
    public EventHandler(){
        this.gm = GameManager.getInstance();    // retrieve instance of Game Manager
    }
    public void handleEvent(int eventID){       // method that passes event to Game Manager for processing
        gm.processEvent(eventID);
    }
}
