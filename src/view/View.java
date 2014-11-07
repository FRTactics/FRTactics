/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Charlie
 */
public class View extends JPanel{
    public View(){
        this.setLayout(new CardLayout());
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }
}
