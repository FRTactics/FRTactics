package view.ingame;

import exceptions.InstanceNotCreatedException;
import model.classSystem.DefaultClass;
import model.ImageContainer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import model.GamePlayManager;
import view.GameApp;

public class SelectionPopup extends JDialog
{
    public enum ButtonType
    {
        ATTACK, DEFEND, MOVE, MAGIC,HEAL
    }; 
    
    private final Image backgroundImage;
    private DefaultClass character;
    private static SelectionPopup instance;
    private int xLocation, yLocation;
    
    private SelectionPopup(Window parent)
    {
        super(parent);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);       
        setSize(new Dimension(150,250));
        setContentPane(new StatsPanel());
        getRootPane().setOpaque(false);
        setUndecorated(true);
        setBackground(new Color(0,0,0,0));
        setModalityType(ModalityType.MODELESS);
        addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent event)
            {
                if(event.getKeyCode() == KeyEvent.VK_ESCAPE)
                     setVisible(false);
            }
        });
        backgroundImage = ImageContainer.getInstance().retrieveMenuImage(ImageContainer.MenuImage.POPUP_BACKGROUND);
        this.add(new SelectionButton(ButtonType.ATTACK));
        this.add(new SelectionButton(ButtonType.DEFEND));
        this.add(new SelectionButton(ButtonType.MOVE));
        this.add(new SelectionButton(ButtonType.MAGIC));
        this.add(new SelectionButton(ButtonType.HEAL));
    }

    public static void createInstance(Window parent)
    {
        if(instance == null)
            instance = new SelectionPopup(parent);
    }

    public static SelectionPopup getInstance() throws InstanceNotCreatedException
    {
        if(instance == null)
            throw new InstanceNotCreatedException("No instance exists for SelectionPopup");
        return instance;
    }

    public void updateCharacter(DefaultClass character, int xLocation,int yLocation)
    {
        this.character = character;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
    }
    
    //Class for the jpanel the image is drawn on
    protected class StatsPanel extends JPanel
    {   
        @Override
        public void paintComponent(Graphics g)
        {
            g.drawImage(backgroundImage, 0,0,this.getWidth(),this.getHeight(), rootPane);
        }

    }
    
    //Inner Class for the button
    private class SelectionButton extends JPanel
    {
        private final ButtonType type;
        private Image buttonImage;
        
        public SelectionButton(ButtonType type)
        {
            this.type = type;
            setOpaque(true);
            setVisible(true);
            setPreferredSize(new Dimension(70,80));
            addMouseListener(new SelectionButtonAdapter());
            loadButtonImage();
        }
        
        public final void loadButtonImage()
        {
            switch(type)
            {
                case ATTACK:
                    buttonImage = ImageContainer.getInstance().retrieveMenuImage(ImageContainer.MenuImage.SELECTION_ATTACK);
                    break;
                case DEFEND:
                    buttonImage = ImageContainer.getInstance().retrieveMenuImage(ImageContainer.MenuImage.SELECTION_DEFEND);
                    break;
                case MOVE:
                    buttonImage = ImageContainer.getInstance().retrieveMenuImage(ImageContainer.MenuImage.SELECTION_MOVE);
                    break;
                case HEAL:
                    buttonImage = ImageContainer.getInstance().retrieveMenuImage(ImageContainer.MenuImage.SELECTION_HEAL);
                    break;
                case MAGIC:
                    buttonImage = ImageContainer.getInstance().retrieveMenuImage(ImageContainer.MenuImage.SELECTION_MAGIC);
                    break;
            }
        }
        
        @Override
        public void paintComponent(Graphics g)
        {
            g.drawImage(buttonImage, 0, 0, getWidth(), getHeight(), SelectionPopup.this);
        }
        
        //Inner Class for the button Adapter
        private class SelectionButtonAdapter extends MouseAdapter
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                GamePlayManager manager = GamePlayManager.getInstance();
                
                switch(type)
                {
                    case ATTACK:
                        manager.setUnit(xLocation, yLocation, GamePlayManager.Action.ATTACK);
                        manager.displayRange(xLocation, yLocation, 1);
                        SelectionPopup.this.setVisible(false);
                        GameApp.frame.repaint();
                        break;
                    case DEFEND:
                        manager.setUnit(xLocation, yLocation, GamePlayManager.Action.DEFEND);
                        SelectionPopup.this.setVisible(false);
                        break;
                    case MOVE:
                        manager.setUnit(xLocation, yLocation, GamePlayManager.Action.MOVE);
                        manager.displayRange(xLocation, yLocation, 0);
                        SelectionPopup.this.setVisible(false);
                        GameApp.frame.repaint();
                        break;
                    case HEAL:
                        manager.setUnit(xLocation, yLocation, GamePlayManager.Action.HEAL);
                        manager.displayRange(xLocation, yLocation, 1);
                        SelectionPopup.this.setVisible(false);
                        GameApp.frame.repaint();
                        break;
                    case MAGIC:
                        manager.setUnit(xLocation, yLocation, GamePlayManager.Action.MAGIC);
                        manager.displayRange(xLocation, yLocation, 1);
                        SelectionPopup.this.setVisible(false);
                        GameApp.frame.repaint();
                        break;
                }
            }
        }    
    }
}
