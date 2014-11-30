package view.ingame;

import com.jhlabs.image.GlowFilter;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JDialog;
import javax.swing.JPanel;
import model.GameManager;

public class PauseMenuButton extends JPanel
{
    public enum ButtonType {
        OPTIONS, RESUME, QUIT, MAIN
    };
    
    private final GlowFilter glowFilter = new GlowFilter();
    private final Image buttonImage;
    private final BufferedImage glowImage;
    private final ButtonType type;
    private final JDialog parent;
    private Image drawImage;
    
    public PauseMenuButton(Image buttonImage, ButtonType type, JDialog parent)
    {
        this.buttonImage = buttonImage;
        this.drawImage = buttonImage;
        glowImage = new BufferedImage(buttonImage.getWidth(this),buttonImage.getHeight(this),BufferedImage.TYPE_INT_ARGB);
        glowImage.getGraphics().drawImage(buttonImage, 0, 0, this);
        this.type = type;
        this.parent = parent;
        this.addMouseListener(new PauseMenuListener());
        this.setOpaque(false);
        this.setVisible(true);
        glowFilter.filter((BufferedImage)buttonImage, (BufferedImage)glowImage);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        Dimension parentSize = parent.getSize();
        g.drawImage(drawImage, 15,0,parentSize.width - 30 ,(parentSize.height / 4) - 5,this);
    }
    
    
    public class PauseMenuListener extends MouseAdapter
    {
         @Override
         public void mousePressed(MouseEvent e)
         {
             switch(type)
             {
                 case OPTIONS:
                     
                 case RESUME:
                     parent.setVisible(false);
                     break;
                 case QUIT:
                     System.exit(0);
                     break;
                 case MAIN:
                     GameManager.getInstance().processEvent(GameManager.RETURN_TO_MAIN);
                     GameManager.getInstance().removeGameWindow();
                     break;
             }
         }
        
         @Override
         public void mouseEntered(MouseEvent e)
         {
             drawImage = glowImage;
             repaint();
         }
         
         @Override
         public void mouseExited(MouseEvent e)
         {
             drawImage = buttonImage;
             repaint();
         }
    };
    
}
