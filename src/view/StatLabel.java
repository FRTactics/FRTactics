/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author Charlie
 */
public class StatLabel extends JPanel{
    private Image image;
    public StatLabel(Image i){
        image = i;
        this.setVisible(true);
    }
    public void setImage(Image i){
        image = i;
    }
    @Override
    public void paintComponent(Graphics g){
        if(image != null)
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
