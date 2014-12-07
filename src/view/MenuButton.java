/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jhlabs.image.GlowFilter;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Charlie
 */
public class MenuButton extends JPanel          // gameButton class
{
    private final GlowFilter glowFilter = new GlowFilter();
    private Image image;
    private Image drawnImage;
    private BufferedImage glowImage;
    /**
     * Constructor for the menu button, accepts an image
     * @param image 
     */
    public MenuButton(Image image)
    {
        this.image = image;
        drawnImage = image;
        glowImage = new BufferedImage(image.getWidth(this),image.getHeight(this),BufferedImage.TYPE_INT_ARGB);
        this.setOpaque(false);
        //this.setPreferredSize(new Dimension(400,100));
       
        //this.setMinimumSize(new Dimension(40,10)); 
        this.addMouseListener(new MenuButtonListener());
        this.setDoubleBuffered(true);
        this.setVisible(true);
        glowFilter.filter((BufferedImage)image, (BufferedImage)glowImage);

        
    }
   
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(drawnImage, 0, 0, this.getWidth(), this.getHeight(), this);
        
    }
    private class MenuButtonListener extends MouseAdapter{
        @Override
        public void mouseEntered(MouseEvent e){
            drawnImage = glowImage;
            repaint();
        }
        @Override
        public void mouseExited(MouseEvent e){
            drawnImage = image;
            repaint();
        }
    }
}
