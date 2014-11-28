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
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRootPane;

public class StatsPopup extends JDialog
{
   private Image characterImage;
   private final Image backgroundImage;
   private DefaultClass character;
   private static StatsPopup instance;
   
   private StatsPopup(Window parent)
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
               setVisible(false);
               getOwner().dispatchEvent(event);
           }
       });
       backgroundImage = ImageContainer.getInstance().retrieveMenuImage(ImageContainer.MenuImage.POPUP_BACKGROUND);
   }
   
   public static void createInstance(Window parent)
   {
       if(instance == null)
           instance = new StatsPopup(parent);
   }
   
   public static StatsPopup getInstance() throws InstanceNotCreatedException
   {
       if(instance == null)
           throw new InstanceNotCreatedException("No instance exists for StatsPopup");
       return instance;
   }
   
   public void updateCharacterImage(ImageContainer.CharacterImage imageSelection)
   {
       characterImage = ImageContainer.getInstance().retrieveCharacterImage(imageSelection);
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
           g.setFont(new Font("Comic Sans MS",Font.BOLD,12));
           g.setColor(Color.red);
           int constHeight = this.getHeight()/6;
           g.drawString("Health: ",20,constHeight + 15 );
           g.drawString("Magic Points: ",20,constHeight + 50);
           g.drawString("Attack Range: ",20,constHeight + 85);
           g.drawString("Move Range: ",20,constHeight + 120);
           g.drawString("Base Attack: ",20,constHeight + 155);
       }
       
   }
}
