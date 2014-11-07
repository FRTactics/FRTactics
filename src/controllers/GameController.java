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
public class GameController extends EventHandler{
    public GameController(GameManager gm, JPanel view){
        super(gm, view);
    }
    
}
