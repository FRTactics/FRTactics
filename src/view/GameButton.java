package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import model.ImageContainer;

public class GameButton extends JPanel          // gameButton class
{

    Image image;
    int xPos;
    int maxPos;
    public GameButton(int selection)
    {
        addImage(selection);
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(400,100));
        this.setMinimumSize(new Dimension(400,100)); 
        this.setDoubleBuffered(true);
        this.setVisible(true);
        maxPos = (int) (this.getPreferredSize().width*.4);
        xPos = maxPos;
        
    }
    private void addImage(int selection)
    {
           image = ImageContainer.getInstance().retrieveBackgroundImage(selection);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image,xPos,0,null);
        
    }
    
    public void animateLeft()
    {
       
        xPos = 0;
        repaint();
    }
    public void animateRight()
    {
        xPos = maxPos;
        repaint();
    }               
}

