package view.ingame;

import exceptions.InstanceNotCreatedException;
import model.classSystem.DefaultClass;
import model.ImageContainer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

public class SelectionPopup extends JDialog
{
    public enum ButtonType
    {
        ATTACK, DEFEND, MOVE, MAGIC,HEAL
    }; 
    
    private final Image backgroundImage;
    private DefaultClass character;
    private static SelectionPopup instance;

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
        addKeyListener(new KeyAdapter(){
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
    }

    public static void createInstance(Window parent)
    {
        if(instance == null)
            instance = new SelectionPopup(parent);
    }

    public static SelectionPopup getInstance() throws InstanceNotCreatedException
    {
        if(instance == null)
            throw new InstanceNotCreatedException("No instance exists for StatsPopup");
        return instance;
    }

    public void updateCharacter(DefaultClass character)
    {
        this.character = character;
    }

    protected class StatsPanel extends JPanel
    {   
        @Override
        public void paintComponent(Graphics g)
        {
            g.drawImage(backgroundImage, 0,0,this.getWidth(),this.getHeight(), rootPane);
        }

    }

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
        
        private class SelectionButtonAdapter extends MouseAdapter
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                switch(type)
                {
                    case ATTACK:
                        
                    case DEFEND:
                        
                    case MOVE:
                        
                    case HEAL:
                       
                    case MAGIC:
                        
                }
            }
        }
    }
}
