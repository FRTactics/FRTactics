/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.ingame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
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
        ArrayList playerOneList = new ArrayList();
        ArrayList playerTwoList = new ArrayList();
        //Populate the list for player one
        createCharacterLabels(playerOneList,GameManager.getInstance().getP1Loadout());
        //Populate the list for player two
        createCharacterLabels(playerTwoList,GameManager.getInstance().getP2Loadout());
        //Create the holder panel for the new layout
        JPanel holderPanel = new JPanel();
        holderPanel.setVisible(true);
        holderPanel.setOpaque(false);
        this.add(holderPanel,BorderLayout.SOUTH);
        //Create the card layout for the two player and add to the pane
        CardLayout playerView = new CardLayout();
        holderPanel.setLayout(playerView);
        //create the player panel for player one for the jlabels
        JPanel playerOnePanel = designCharacterOnePanel();
        //add the labels to player one panel
        for(Object label: playerOneList)
        {
            playerOnePanel.add((CharacterLabel)label);
        }
        //create the player panel for player two for the jlabels
        JPanel playerTwoPanel = designCharacterTwoPanel();
        //add the labels to player one panel
        for(Object label: playerTwoList)
        {
            playerTwoPanel.add((CharacterLabel)label);
        }
        //Add the panels to the card layout
        holderPanel.add(playerOnePanel,"Player One Panel");
        holderPanel.add(playerTwoPanel,"Player Two Panel");
        playerView.show(holderPanel,"Player One Panel");
    }
    
    private JPanel designCharacterOnePanel()
    {
        JPanel playerOnePanel = new JPanel();
        playerOnePanel.setLayout(new FlowLayout());
        playerOnePanel.setVisible(true);
        playerOnePanel.setOpaque(false);
        return playerOnePanel;
    }
    
    private JPanel designCharacterTwoPanel()
    {
        JPanel playerTwoPanel = new JPanel();
        playerTwoPanel.setLayout(new FlowLayout());
        playerTwoPanel.setVisible(true);
        playerTwoPanel.setOpaque(false);
        return playerTwoPanel;
    }
    
    private void createCharacterLabels(ArrayList list, ArrayList orginList)
    {
        CharacterLabel character;
        
        for(int i = 0; i < orginList.size(); ++i)
        {
            character = new CharacterLabel();
            character.setVisible(true);
            character.setHorizontalTextPosition(JLabel.CENTER);
            character.setVerticalTextPosition(JLabel.BOTTOM);
            
            switch(((DefaultClass)(orginList.get(i))).className)
            {
                case "Warrior":
                    character.setIcon(new ImageIcon(ImageContainer.getInstance().retrieveCharacterTileImage(ImageContainer.CharacterImage.WARRIOR).getScaledInstance(125, 100, 0)));
                    character.setCharacter(((DefaultClass)(orginList.get(i))),ImageContainer.getInstance().retrieveCharacterTileImage(ImageContainer.CharacterImage.WARRIOR));
                    character.setText("Warrior");
                    break;
                case "Archer":
                    character.setIcon(new ImageIcon(ImageContainer.getInstance().retrieveCharacterTileImage(ImageContainer.CharacterImage.ARCHER).getScaledInstance(125, 100, 0)));
                    character.setCharacter(((DefaultClass)(orginList.get(i))),ImageContainer.getInstance().retrieveCharacterTileImage(ImageContainer.CharacterImage.ARCHER));
                    character.setText("Archer");
                    break;
                case "Wizard":
                    character.setIcon(new ImageIcon(ImageContainer.getInstance().retrieveCharacterTileImage(ImageContainer.CharacterImage.WIZARD).getScaledInstance(125, 100, 0)));
                    character.setCharacter(((DefaultClass)(orginList.get(i))),ImageContainer.getInstance().retrieveCharacterTileImage(ImageContainer.CharacterImage.WIZARD));
                    character.setText("Wizard");
                    break;
                case "Rogue":
                    character.setIcon(new ImageIcon(ImageContainer.getInstance().retrieveCharacterTileImage(ImageContainer.CharacterImage.ROGUE).getScaledInstance(125, 100, 0)));
                    character.setCharacter(((DefaultClass)(orginList.get(i))),ImageContainer.getInstance().retrieveCharacterTileImage(ImageContainer.CharacterImage.ROGUE));
                    character.setText("Rogue");
                    break;
                case "Healer":
                    character.setIcon(new ImageIcon(ImageContainer.getInstance().retrieveCharacterTileImage(ImageContainer.CharacterImage.HEALER).getScaledInstance(125, 100, 0)));
                    character.setCharacter(((DefaultClass)(orginList.get(i))),ImageContainer.getInstance().retrieveCharacterTileImage(ImageContainer.CharacterImage.HEALER));
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
