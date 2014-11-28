package view.ingame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GlassPane extends JPanel
{
    public GlassPane()
    {
        this.setOpaque(false);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(new Color(0,0,0,.85f));
        g2d.fillRect(0, 0, this.getWidth(),this.getHeight());
    }
}
