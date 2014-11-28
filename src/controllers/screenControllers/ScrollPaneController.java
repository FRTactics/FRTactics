package controllers.screenControllers;

import view.ingame.DrawPanel;
import view.ingame.MainWindow;
import view.ingame.PauseDialog;
import view.ingame.Tile;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class ScrollPaneController extends KeyAdapter
{
    private final JViewport port;
    private final DrawPanel dp;
    private final JFrame frame;
    private final PauseDialog dialog;
    private final JScrollPane pane = MainWindow.pane;
    
    public ScrollPaneController(JViewport port, DrawPanel dp, JFrame frame, PauseDialog dialog)
    {
        this.port = port;
        this.dp = dp;
        this.frame = frame;
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
                Timer timer = Tile.getTimer();
                if(timer != null)
                {
                    timer.cancel();
                    timer.purge();
                }
                dialog.setVisible(true);
                break;
            case KeyEvent.VK_ADD:
                oldVP = port.getViewPosition();
                if(dp.preformZoom(true))
                {
                    dp.updateSpringLayout();
                    port.setViewPosition(new Point(oldVP.x + DrawPanel.zoomModifier.width/2, oldVP.y + DrawPanel.zoomModifier.height/2 ));
                    frame.repaint();
                }
                break;
            case KeyEvent.VK_SUBTRACT:
                oldVP = port.getViewPosition();
                if(dp.preformZoom(false))
                {                       
                    dp.updateSpringLayout();
                    port.setViewPosition(new Point(oldVP.x - DrawPanel.zoomModifier.width/2, oldVP.y - DrawPanel.zoomModifier.height/2 ));
                    frame.repaint();
                }
                break;
        }
    }
}
