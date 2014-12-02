package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import model.ImageContainer;

public class MainMenuSwordButton extends JPanel          // gameButton class
{

    private Image image;
    private int xPos;
    private int maxPos;
    public MainMenuSwordButton(Image image)
    {
        this.image = image;
        this.setOpaque(false);
        //this.setPreferredSize(new Dimension(400,100));
       
        //this.setMinimumSize(new Dimension(200,100)); 
        this.setDoubleBuffered(true);
        this.setVisible(true);
        maxPos = (int) (this.getPreferredSize().width*.4);
        xPos = maxPos;
        //xPos = 0;
        
    }
    public void setMaxImagePos(int pos){
        maxPos = pos;
        xPos = pos;
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image, xPos, 0, this.getWidth(), this.getHeight(), this);
        
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

