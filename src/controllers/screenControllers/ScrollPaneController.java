package controllers.screenControllers;

import view.ingame.DrawPanel;
import view.ingame.GameWindow;
import view.ingame.PauseDialog;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class ScrollPaneController extends KeyAdapter
{
    private final JViewport port;
    private final DrawPanel dp;
    private final JPanel panel;
    private final PauseDialog dialog;
    private final JScrollPane pane = GameWindow.pane;
    
    public ScrollPaneController(JViewport port, DrawPanel dp, JPanel panel, PauseDialog dialog)
    {
        this.port = port;
        this.dp = dp;
        this.panel = panel;
        this.dialog = dialog;
    }
    
    @Override
    public void keyPressed(KeyEvent e)
    {   
        Point oldVP;
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                 pane.getVerticalScrollBar().setValue(pane.getVerticalScrollBar().getValue() - 50);
                 break;
            case KeyEvent.VK_DOWN:
                pane.getVerticalScrollBar().setValue(pane.getVerticalScrollBar().getValue() + 50);
                break;
            case KeyEvent.VK_LEFT:
                pane.getHorizontalScrollBar().setValue(pane.getHorizontalScrollBar().getValue() - 50);
                break;
            case KeyEvent.VK_RIGHT:
                pane.getHorizontalScrollBar().setValue(pane.getHorizontalScrollBar().getValue() + 50);
                break;
            case KeyEvent.VK_ESCAPE:
                dialog.setVisible(true);
                break;
            case KeyEvent.VK_ADD:
                oldVP = port.getViewPosition();
                if(dp.preformZoom(true))
                {
                    dp.updateSpringLayout();
                    port.setViewPosition(new Point(oldVP.x + DrawPanel.zoomModifier.width/2, oldVP.y + DrawPanel.zoomModifier.height/2 ));
                    panel.repaint();
                }
                break;
            case KeyEvent.VK_SUBTRACT:
                oldVP = port.getViewPosition();
                if(dp.preformZoom(false))
                {                       
                    dp.updateSpringLayout();
                    port.setViewPosition(new Point(oldVP.x - DrawPanel.zoomModifier.width/2, oldVP.y - DrawPanel.zoomModifier.height/2 ));
                    panel.repaint();
                }
                break;
        }
    }
}
