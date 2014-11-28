/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author Charlie
 */
public class MenuButton extends JPanel          // gameButton class
{

    Image image;
    public MenuButton(Image image)
    {
        this.image = image;
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(400,100));
       
        this.setMinimumSize(new Dimension(40,10)); 
        this.setDoubleBuffered(true);
        this.setVisible(true);

        
    }
   
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
        
    }
}
