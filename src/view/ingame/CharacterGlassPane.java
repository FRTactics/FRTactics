/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.ingame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import model.GameManager;
import model.ImageContainer;
import model.classSystem.DefaultClass;

public class CharacterGlassPane extends JPanel 
{
    public CharacterGlassPane()
    {
        // Set up the glass pane
        setOpaque(false);
        setLayout(new BorderLayout());
        //Create vector to store labels into
        Vector list = new Vector();
        //Populate the list
        createCharacterLabels(list);
        //create the holder panel for the jlabels
        JPanel holderPanel = new JPanel();
        holderPanel.setLayout(new FlowLayout());
        holderPanel.setVisible(true);
        holderPanel.setOpaque(false);
        for(Object label: list)
        {
            holderPanel.add((CharacterLabel)label);
        }
        this.add(holderPanel, BorderLayout.SOUTH);
    }
    
    private void createCharacterLabels(Vector list)
    {
        ArrayList playerOneLoadout = GameManager.getInstance().getP1Loadout();
        CharacterLabel character;
        
        for(int i = 0; i < playerOneLoadout.size(); ++i)
        {
            character = new CharacterLabel();
            character.setVisible(true);
            character.setHorizontalTextPosition(JLabel.CENTER);
            character.setVerticalTextPosition(JLabel.BOTTOM);
            
            switch(((DefaultClass)(playerOneLoadout.get(i))).className)
            {
                case "Warrior":
                    character.setIcon(new ImageIcon(ImageContainer.getInstance().retrieveCharacterTileImage(ImageContainer.CharacterImage.WARRIOR).getScaledInstance(125, 100, 0)));
                    character.setCharacter(((DefaultClass)(playerOneLoadout.get(i))),ImageContainer.getInstance().retrieveCharacterTileImage(ImageContainer.CharacterImage.WARRIOR));
                    character.setText("Warrior");
                    break;
                case "Archer":
                    character.setIcon(new ImageIcon(ImageContainer.getInstance().retrieveCharacterTileImage(ImageContainer.CharacterImage.ARCHER).getScaledInstance(125, 100, 0)));
                    character.setCharacter(((DefaultClass)(playerOneLoadout.get(i))),ImageContainer.getInstance().retrieveCharacterTileImage(ImageContainer.CharacterImage.ARCHER));
                    character.setText("Archer");
                    break;
                case "Wizard":
                    character.setIcon(new ImageIcon(ImageContainer.getInstance().retrieveCharacterTileImage(ImageContainer.CharacterImage.WIZARD).getScaledInstance(125, 100, 0)));
                    character.setCharacter(((DefaultClass)(playerOneLoadout.get(i))),ImageContainer.getInstance().retrieveCharacterTileImage(ImageContainer.CharacterImage.WIZARD));
                    character.setText("Wizard");
                    break;
                case "Rogue":
                    character.setIcon(new ImageIcon(ImageContainer.getInstance().retrieveCharacterTileImage(ImageContainer.CharacterImage.ROGUE).getScaledInstance(125, 100, 0)));
                    character.setCharacter(((DefaultClass)(playerOneLoadout.get(i))),ImageContainer.getInstance().retrieveCharacterTileImage(ImageContainer.CharacterImage.ROGUE));
                    character.setText("Rogue");
                    break;
                case "Healer":
                    character.setIcon(new ImageIcon(ImageContainer.getInstance().retrieveCharacterTileImage(ImageContainer.CharacterImage.HEALER).getScaledInstance(125, 100, 0)));
                    character.setCharacter(((DefaultClass)(playerOneLoadout.get(i))),ImageContainer.getInstance().retrieveCharacterTileImage(ImageContainer.CharacterImage.HEALER));
                    character.setText("Healer");
                    break;
            }
            list.add(character);
        }        
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        Image hud = ImageContainer.getInstance().retrieveMenuImage(ImageContainer.MenuImage.HUD_BACKGROUND);
        g.drawImage(hud,0,0,getWidth(),getHeight(),this);
    }
    
    class CharacterCellRender extends DefaultListCellRenderer
    {
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
        {
            return (CharacterLabel)value;
        }
    }
}
