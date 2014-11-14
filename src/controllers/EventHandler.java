/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import javax.swing.JPanel;
import model.GameManager;

/**
 *
 * @author Charlie
 */
public abstract class EventHandler{
    public GameManager gm;
    public EventHandler(GameManager gm){
        this.gm = gm;
    }
    public void handleEvent(int eventID){
        gm.processEvent(eventID);
    }
}
