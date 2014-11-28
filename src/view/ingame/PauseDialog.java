package view.ingame;

import model.ImageContainer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRootPane;

public class PauseDialog extends JDialog
{
    public PauseDialog(Window frame,final JPanel glass)
    {
        super(frame);
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);       
        this.setSize(new Dimension(GameWindow.windowSize.width/3,GameWindow.windowSize.height/2));
        int locationX = GameWindow.windowSize.width/2 - this.getWidth()/2;
        int locationY = GameWindow.windowSize.height/2 - this.getHeight()/2;
        this.setLocation(new Point(locationX,locationY));
        this.setContentPane(new PauseMenu(this));
        this.getRootPane().setOpaque(false);
        this.setUndecorated(true);
        this.setBackground(new Color(0,0,0,0));
        this.setModalityType(ModalityType.TOOLKIT_MODAL);
        this.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
                    PauseDialog.this.setVisible(false);
            }
        });
        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowActivated(WindowEvent e)
            {
                glass.setVisible(true);
            }
            
            @Override
            public void windowDeactivated(WindowEvent e)
            {
                glass.setVisible(false);
            }
        });

    }
    
    public class PauseMenu extends JPanel
    {
        private final Image background;

        public PauseMenu(JDialog dialog)
        {
            background = ImageContainer.getInstance().retrieveMenuImage(ImageContainer.MenuImage.PAUSE_MENU_BACKGROUND);

            this.setVisible(true);
            this.setOpaque(false);
            this.setBackground(new Color(0,0,0,0));
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.add(Box.createRigidArea(new Dimension(0,5)));
            this.add(new PauseMenuButton(ImageContainer.getInstance().retrieveMenuImage(ImageContainer.MenuImage.PAUSE_MENU_RESUME),PauseMenuButton.ButtonType.RESUME,dialog));
            this.add(Box.createRigidArea(new Dimension(0,5)));
            this.add(new PauseMenuButton(ImageContainer.getInstance().retrieveMenuImage(ImageContainer.MenuImage.PAUSE_MENU_OPTIONS),PauseMenuButton.ButtonType.OPTIONS,dialog));
            this.add(Box.createRigidArea(new Dimension(0,5)));
            this.add(new PauseMenuButton(ImageContainer.getInstance().retrieveMenuImage(ImageContainer.MenuImage.PAUSE_MENU_MAIN),PauseMenuButton.ButtonType.MAIN,dialog));
            this.add(Box.createRigidArea(new Dimension(0,5)));
            this.add(new PauseMenuButton(ImageContainer.getInstance().retrieveMenuImage(ImageContainer.MenuImage.PAUSE_MENU_QUIT),PauseMenuButton.ButtonType.QUIT,dialog));
            this.add(Box.createRigidArea(new Dimension(0,5)));
        }

        @Override
        public void paintComponent(Graphics g)
        {
            g.drawImage(background, 0, 0,this.getWidth(),this.getHeight(), this);
        }
    }
}
