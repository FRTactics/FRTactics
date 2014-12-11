/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.ingame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import model.GameManager;
import model.GamePlayManager;
import model.ImageContainer;
import model.classSystem.DefaultClass;
import view.GameApp;
import view.MenuButton;
import view.StatLabel;

public class CharacterGlassPane extends JPanel 
{
    private JPanel holderPanel;
    private JPanel topFiller;
    private JPanel middleFiller;
    private JPanel bottomFiller;
    
    private PlayerPanel playerOnePanel;
    private PlayerPanel playerTwoPanel;
    private JPanel p1CenterPanel;
    private JPanel p2CenterPanel;
    public CharacterGlassPane()
    {
        // Set up the glass pane
        setOpaque(false);
        //setLayout(new BorderLayout());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //Create vector to store labels into
        ArrayList playerOneList = new ArrayList();
        ArrayList playerTwoList = new ArrayList();
        //Populate the list for player one
        createCharacterLabels(playerOneList,GameManager.getInstance().getP1Loadout());
        //Populate the list for player two
        createCharacterLabels(playerTwoList,GameManager.getInstance().getP2Loadout());
        addKeyListener(new KeyAdapter(){
           @Override
           public void keyPressed(KeyEvent e)
           {
               GameApp.frame.dispatchEvent(e);
           }
        });
        //Create the holder panel for the new layout
        
        topFiller = new JPanel();
        topFiller.setOpaque(false);
        topFiller.setPreferredSize(new Dimension(GameApp.frame.getWidth(),((GameApp.frame.getHeight()*39)/40)));
        middleFiller = new JPanel();
        middleFiller.addMouseListener(new MouseAdapter() {});
        middleFiller.setOpaque(false);
        middleFiller.setDropTarget(new DropTarget(middleFiller, DnDConstants.ACTION_NONE, null, true));
        middleFiller.setPreferredSize(new Dimension(GameApp.frame.getWidth(), (GameApp.frame.getHeight()*12)/320));
        holderPanel = new JPanel();
        holderPanel.addMouseListener(new MouseAdapter() {});
        //holderPanel.setPreferredSize(new Dimension(GameApp.frame.getWidth(), (GameApp.frame.getHeight()*7)/80));
        holderPanel.setPreferredSize(new Dimension(GameApp.frame.getWidth(), (GameApp.frame.getHeight()*15)/160));
        holderPanel.setDropTarget(new DropTarget(holderPanel, DnDConstants.ACTION_NONE, null, true));
        System.out.println((GameApp.frame.getHeight()*5)/160);
        bottomFiller = new JPanel();
        bottomFiller.addMouseListener(new MouseAdapter() {});
        bottomFiller.setOpaque(false);
        //bottomFiller
        bottomFiller.setPreferredSize(new Dimension(GameApp.frame.getWidth(), (GameApp.frame.getHeight()*9)/320));
        bottomFiller.setDropTarget(new DropTarget(bottomFiller, DnDConstants.ACTION_NONE, null, true));
        holderPanel.setVisible(true);
        holderPanel.setOpaque(false);
       
        this.add(topFiller);
        this.add(middleFiller);
        this.add(holderPanel);
        this.add(bottomFiller);
        //Create the card layout for the two player and add to the pane
        CardLayout playerView = new CardLayout();
        holderPanel.setLayout(playerView);
        //create the player panel for player one for the jlabels
        
        p1CenterPanel = new JPanel();
        p1CenterPanel.setOpaque(false);
        //create the player panel for player two for the jlabels
        playerOnePanel = new PlayerPanel(ImageContainer.getInstance().retrieveMenuImage(ImageContainer.MenuImage.P1_TURN), holderPanel, p1CenterPanel);
        //add the labels to player one panel
        for(Object label: playerOneList)
        {
            p1CenterPanel.add((CharacterLabel)label);
            ((CharacterLabel)label).addMouseListener(new CharacterLabelListener(((CharacterLabel)label).getCharacter()));
        }
        
        p2CenterPanel = new JPanel();
        p2CenterPanel.setOpaque(false);
        playerTwoPanel = new PlayerPanel(ImageContainer.getInstance().retrieveMenuImage(ImageContainer.MenuImage.P2_TURN), holderPanel, p2CenterPanel);
        //add the labels to player one panel
        for(Object label: playerTwoList)
        {
            p2CenterPanel.add((CharacterLabel)label);
            ((CharacterLabel)label).addMouseListener(new CharacterLabelListener(((CharacterLabel)label).getCharacter()));
        }
        

        //Add the panels to the card layout

        holderPanel.add(playerOnePanel,"Player One Panel");
        holderPanel.add(playerTwoPanel,"Player Two Panel");
        playerView.show(holderPanel,"Player One Panel");
        this.setVisible(true);
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
                    character.setIcon(new ImageIcon(ImageContainer.getInstance().retrieveCharacterTileImage(ImageContainer.CharacterImage.WARRIOR).getScaledInstance(GameApp.frame.getWidth()/17,GameApp.frame.getHeight()/15, 0)));
                    character.setCharacter(((DefaultClass)(orginList.get(i))),ImageContainer.getInstance().retrieveCharacterTileImage(ImageContainer.CharacterImage.WARRIOR));
                    character.setText("Warrior");
                    break;
                case "Archer":
                    character.setIcon(new ImageIcon(ImageContainer.getInstance().retrieveCharacterTileImage(ImageContainer.CharacterImage.ARCHER).getScaledInstance(GameApp.frame.getWidth()/17,GameApp.frame.getHeight()/15, 0)));
                    character.setCharacter(((DefaultClass)(orginList.get(i))),ImageContainer.getInstance().retrieveCharacterTileImage(ImageContainer.CharacterImage.ARCHER));
                    character.setText("Archer");
                    break;
                case "Wizard":
                    character.setIcon(new ImageIcon(ImageContainer.getInstance().retrieveCharacterTileImage(ImageContainer.CharacterImage.WIZARD).getScaledInstance(GameApp.frame.getWidth()/17,GameApp.frame.getHeight()/15, 0)));
                    character.setCharacter(((DefaultClass)(orginList.get(i))),ImageContainer.getInstance().retrieveCharacterTileImage(ImageContainer.CharacterImage.WIZARD));
                    character.setText("Wizard");
                    break;
                case "Rogue":
                    character.setIcon(new ImageIcon(ImageContainer.getInstance().retrieveCharacterTileImage(ImageContainer.CharacterImage.ROGUE).getScaledInstance(GameApp.frame.getWidth()/17,GameApp.frame.getHeight()/15, 0)));
                    character.setCharacter(((DefaultClass)(orginList.get(i))),ImageContainer.getInstance().retrieveCharacterTileImage(ImageContainer.CharacterImage.ROGUE));
                    character.setText("Rogue");
                    break;
                case "Healer":
                    character.setIcon(new ImageIcon(ImageContainer.getInstance().retrieveCharacterTileImage(ImageContainer.CharacterImage.HEALER).getScaledInstance(GameApp.frame.getWidth()/17,GameApp.frame.getHeight()/15, 0)));
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
    
    private class PlayerPanel extends JPanel{

        // All of p1's shit
        // all the panels used for the P1 card
        private JComponent parent;
        private JPanel centerPanel;
        private JPanel leftPanel;
        private JPanel leftPanelRightFiller;
        private JPanel buttonPanelTopFiller;
        private JPanel buttonPanelBottomFiller;
        private JPanel buttonPanel;
        private JPanel leftPanelLeftFiller;
        private JPanel identifier;
        private JPanel statsPanel;
        private JPanel rightPanel;
        private JPanel rightPanelLeftFiller;
        private JPanel rightPanelRightFiller;
        // Labels for the stats (P1)
        // Column 1
        private StatLabel statsLabel;
        private StatLabel hpLabel;
        private StatLabel mpLabel;

        // Column 2
        private JLabel column2Label;
        private JLabel hpValue;
        private JLabel mpValue;

        // Column 3
        private JLabel column3Label;
        private StatLabel dodgeChanceLabel;
        private StatLabel armorLabel;
        // Column 4
        private JLabel column4Label;
        private JLabel dodgeChanceValue;
        private JLabel armorValue;

        private final Font font;
        // buttons
        private MenuButton endTurnButton;

        public PlayerPanel(Image identifierImage, JPanel parent, JPanel centerPanel){
            this.parent = parent;
            font = new Font("Comic Sans MS" , Font.PLAIN, GameApp.frame.getHeight()/50);
            // everything from the left panel
            leftPanel = new JPanel();
            leftPanel.setOpaque(false);
            leftPanel.setPreferredSize(new Dimension((GameApp.frame.getWidth()*2)/7, parent.getPreferredSize().height));
            leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.X_AXIS));
            buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
            buttonPanel.setPreferredSize(new Dimension((GameApp.frame.getWidth()*2)/7, parent.getPreferredSize().height));
            leftPanelLeftFiller = new JPanel();
            leftPanelLeftFiller.setOpaque(false);
            leftPanelLeftFiller.setPreferredSize(new Dimension(GameApp.frame.getWidth()/40, parent.getPreferredSize().height));
            leftPanelRightFiller = new JPanel();
            leftPanelRightFiller.setOpaque(false);
            leftPanelRightFiller.setPreferredSize(new Dimension(GameApp.frame.getWidth()/40, parent.getPreferredSize().height));
            identifier = new StatLabel(identifierImage);
            identifier.setPreferredSize(new Dimension(GameApp.frame.getWidth()/10, parent.getPreferredSize().height));
            identifier.setOpaque(false);
            endTurnButton = new MenuButton(ImageContainer.getInstance().retrieveMenuImage(ImageContainer.MenuImage.MENU_CONTINUE));
            endTurnButton.setPreferredSize(new Dimension(GameApp.frame.getWidth()/7, parent.getPreferredSize().height));
            endTurnButton.addMouseListener(new EndTurnButtonListener());
            buttonPanelBottomFiller = new JPanel();
            buttonPanelBottomFiller.setOpaque(false);
            buttonPanelBottomFiller.setPreferredSize(new Dimension(GameApp.frame.getWidth()/4, parent.getPreferredSize().height/3));
            buttonPanelTopFiller = new JPanel();
            buttonPanelTopFiller.setOpaque(false);
            buttonPanelTopFiller.setPreferredSize(new Dimension(GameApp.frame.getWidth()/4, parent.getPreferredSize().height/3));
            buttonPanel.add(buttonPanelTopFiller);
            buttonPanel.add(endTurnButton);
            buttonPanel.add(buttonPanelBottomFiller);
            buttonPanel.setOpaque(false);
            leftPanel.add(leftPanelLeftFiller);
            leftPanel.add(identifier);
            leftPanel.add(buttonPanel);
            leftPanel.add(leftPanelRightFiller);


            // Everything for the Stats Panel
            statsPanel = new JPanel();
            statsPanel.setOpaque(false);
            GroupLayout p1StatsLayout = new GroupLayout(statsPanel);
            statsPanel.setLayout(p1StatsLayout);
            statsLabel = new StatLabel(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.STATS));
            hpLabel = new StatLabel(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.HP));
            mpLabel = new StatLabel(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.MP));
            armorLabel = new StatLabel(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.ARMOR));
            dodgeChanceLabel = new StatLabel(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.DODGE_CHANCE));
            hpValue = new JLabel("--");
            hpValue.setFont(font);
            mpValue = new JLabel("--");
            mpValue.setFont(font);
            armorValue = new JLabel("--");
            armorValue.setFont(font);
            dodgeChanceValue = new JLabel("--");
            dodgeChanceValue.setFont(font);
            column2Label = new JLabel(" ");
            column2Label.setFont(font);
            column3Label = new JLabel(" ");
            column4Label = new JLabel(" ");
            p1StatsLayout.setHorizontalGroup(p1StatsLayout.createSequentialGroup()
                    .addGroup(p1StatsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(statsLabel)
                            .addComponent(hpLabel)
                            .addComponent(mpLabel))
                    .addGroup(p1StatsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(column2Label)
                            .addComponent(hpValue)
                            .addComponent(mpValue))
                    .addGroup(p1StatsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(column3Label)
                            .addComponent(dodgeChanceLabel)
                            .addComponent(armorLabel))
                    .addGroup(p1StatsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(column4Label)
                            .addComponent(dodgeChanceValue)
                            .addComponent(armorValue)));
            p1StatsLayout.setVerticalGroup(p1StatsLayout.createSequentialGroup()
                    .addGroup(p1StatsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(statsLabel)
                            .addComponent(column2Label)
                            .addComponent(column3Label)
                            .addComponent(column4Label))
                    .addGroup(p1StatsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)    
                            .addComponent(hpLabel)
                            .addComponent(hpValue)
                            .addComponent(dodgeChanceLabel)
                            .addComponent(dodgeChanceValue))
                    .addGroup(p1StatsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)   
                            .addComponent(mpLabel)
                            .addComponent(mpValue)
                            .addComponent(armorLabel)
                            .addComponent(armorValue)));
            statsPanel.setPreferredSize(new Dimension((GameApp.frame.getWidth()*2)/7, parent.getPreferredSize().height));
            // Everything for the Right Panel
            rightPanel = new JPanel();
            rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.X_AXIS)); 
            rightPanel.setPreferredSize(new Dimension((GameApp.frame.getWidth()*2)/7, parent.getPreferredSize().height));
            rightPanel.setOpaque(false);
            rightPanelLeftFiller = new JPanel();
            rightPanelLeftFiller.setOpaque(false);
            rightPanelLeftFiller.setPreferredSize(new Dimension((GameApp.frame.getWidth()*3)/80, parent.getPreferredSize().height));
            rightPanelRightFiller = new JPanel();
            rightPanelRightFiller.setOpaque(false);
            rightPanelRightFiller.setPreferredSize(new Dimension(GameApp.frame.getWidth()/40, parent.getPreferredSize().height));
            rightPanel.add(rightPanelLeftFiller);
            rightPanel.add(statsPanel);
            rightPanel.add(rightPanelRightFiller);
            // Bring everything together
            this.setLayout(new BorderLayout());
            this.add(leftPanel, BorderLayout.WEST);
            this.add(centerPanel, BorderLayout.CENTER);
            this.add(rightPanel, BorderLayout.EAST);
            this.setVisible(true);
            this.setOpaque(false);
        }
        public JLabel getDodgeChanceValueLabel(){
            return dodgeChanceValue;
        }
        public JLabel getArmorValueLabel(){
            return armorValue;
        }
        public JLabel getHPValueLabel(){
            return hpValue;
        }
        public JLabel getMPValueLabel(){
            return mpValue;
        }
        
        private class EndTurnButtonListener extends MouseAdapter
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                GamePlayManager.getInstance().switchTurns();
                CardLayout layout = (CardLayout)parent.getLayout();
                layout.next(parent);   
            }
        }
    }
    public class CharacterLabelListener extends MouseAdapter
    {
        private DefaultClass character;
        private PlayerPanel playerPanel;
        public CharacterLabelListener(DefaultClass character)
        {
            this.character = character;
            if(character.getOwner() == GameManager.PLAYER_1)
            {
                playerPanel = playerOnePanel;
            }
            else if(character.getOwner() == GameManager.PLAYER_2)
            {
                playerPanel = playerTwoPanel;
            }
            
        }
        @Override
        public void mouseClicked(MouseEvent e){
            playerPanel.getArmorValueLabel().setText("" + character.getArmor());
            playerPanel.getDodgeChanceValueLabel().setText("" + character.getDodgeChance()+ "%");
            playerPanel.getHPValueLabel().setText("" + character.getHealth());
            playerPanel.getMPValueLabel().setText("" + character.getMP());
        }
    }
}
