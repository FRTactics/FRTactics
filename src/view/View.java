/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JPanel;

/**
 *
 * @author Charlie
 */
public class View extends JPanel{
    private CardLayout layout = new CardLayout();
    public View(){
        this.setLayout(layout);
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setName("View");
    }
    public JPanel getFrontView(){
        JPanel frontComponent = null;
        for(Component component : getComponents()){
            if(component instanceof JPanel){
                if(component.isVisible()){
                    frontComponent = (JPanel)component;
                    break;
                }
            }
        }
        return frontComponent;
    }
}
