/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.GameManager;
import model.ImageContainer;
import model.classSystem.*;
/**
 *
 * @author Charlie
 */
public class LoadoutMenu extends MenuPanel{             // still a work in progress, looks like a piece of shit now - Charlie
    
    private ArrayList<CustomLabel> choiceList = new ArrayList();
    private String identifier;      //String used to indentify which loadout this is P1 or P2
    private JPanel loadoutPanel;
    private JPanel loadoutPanelBottomFiller;
    private JPanel loadoutPanelMiddleFiller;
    private JList loadoutList;
    private JPanel choicePanel;
    private HashMap<String, Image> imageMap;
    private JPanel buttonPanel; // placed on east side
    private JPanel buttonPanelMiddleFiller;
    private JPanel buttonPanelBottomFiller;
    private JPanel leftPanel;
    private JPanel leftPanelLeftFiller;
    private JPanel leftPanelRightFiller;
    private JPanel centerPanel;     // will hold a jpanel containing all of the images (gridlayout)
    private JPanel bottomPanel;
    private JPanel bottomPanelLeftFiller;
    private JPanel bottomPanelRightFiller;
    private JPanel rightPanel;
    private JPanel rightPanelLeftFiller;
    private JPanel rightPanelRightFiller;
    private JScrollPane centerScroll;
    //private JTabbedPane centerTabs;
    private JScrollPane loadoutScroll;
    private JPanel topBuffer;    // just a buffer placed on the top screen to make it pretty
    private JPanel statsPanel; // panel to hold all of the stats for each unit
    
   // stuff for the stats
    private GroupLayout statsLayout;
    // labels for the stats
    
    // Column 1
    private StatLabel statsLabel;
    private StatLabel classLabel;
    private StatLabel hpLabel;
    private StatLabel mpLabel;
    private StatLabel strengthLabel;
    private StatLabel agilityLabel;
    // Column 2
    private JLabel column2TempLabel;
    private JLabel classNameLabel;
    private JLabel hpValueLabel;
    private JLabel mpValueLabel;
    private JLabel strengthValueLabel;
    private JLabel agilityValueLabel;
    
    // Column 3
    private JLabel column3TempLabel;
    private StatLabel meleeDamageLabel;
    private StatLabel rangedDamageLabel;
    private StatLabel spellDamageLabel;
    private StatLabel meleeAttackRangeLabel;
    private StatLabel rangedAttackRangeLabel;

    // Column 4
    private JLabel column4TempLabel;
    private JLabel meleeDamageValueLabel;
    private JLabel rangedDamageValueLabel;
    private JLabel spellDamageValueLabel;
    private JLabel meleeAttackRangeValueLabel; 
    private JLabel rangedAttackRangeValueLabel;
    
    // Column 5
    private JLabel column5TempLabel;
    private StatLabel movementRangeLabel;
    private StatLabel dexterityLabel;
    private StatLabel vitalityLabel;
    private StatLabel intelligenceLabel;
    private StatLabel dodgeChanceLabel;
    // Column 6
    private JLabel column6TempLabel;
    private JLabel movementRangeValueLabel;
    private JLabel dexterityValueLabel;
    private JLabel vitalityValueLabel;
    private JLabel intelligenceValueLabel;
    private JLabel dodgeChanceValueLabel;
    
    // Column 7
    private JLabel column7TempLabel;
    private StatLabel healthRegenLabel;
    private StatLabel armorLabel;
    // Column 8
    private JLabel column8TempLabel;
    private JLabel healthRegenValueLabel;
    private JLabel armorValueLabel;
    
    
    private final Image background;
    private MenuButton continueButton;
    private MenuButton backButton;
    private MenuButton removeButton;
    private DropTarget dropTarget;
    private final Font font;
    
    /**
     * Constructor for the LoadoutMenu, takes in an identifier and a background image
     * @param identifier
     * @param image 
     */
    public LoadoutMenu(String identifier, Image image) {
        this.identifier = identifier;
        this.setName(identifier);
        // initialize everything first
        font = new Font("Comic Sans MS" , Font.PLAIN, 20);
        imageMap = createImageMap();
        background = image;
        topBuffer = new JPanel();
        topBuffer.setPreferredSize(new Dimension(500,220));
        initButtonPanel();
        initLoadoutPanel();
        initChoicePanel();
        initStatsPanel();
        bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setLayout(new BorderLayout());
        bottomPanelLeftFiller = new JPanel();
        bottomPanelRightFiller = new JPanel();
        bottomPanelLeftFiller.setPreferredSize(new Dimension(50,50));
        bottomPanelLeftFiller.setOpaque(false);
        bottomPanelRightFiller.setPreferredSize(new Dimension(50,50));
        bottomPanelRightFiller.setOpaque(false);
        bottomPanel.add(bottomPanelLeftFiller, BorderLayout.WEST);
        bottomPanel.add(statsPanel, BorderLayout.CENTER);
        bottomPanel.add(bottomPanelRightFiller, BorderLayout.EAST);
        centerPanel.add(bottomPanel);
        centerPanel.add(Box.createVerticalGlue());
        rightPanel = new JPanel();
        rightPanel.setOpaque(false);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.X_AXIS));
        rightPanelLeftFiller = new JPanel();
        rightPanelRightFiller = new JPanel();
        rightPanelLeftFiller.setOpaque(false);
        rightPanelRightFiller.setOpaque(false);
        rightPanel.setPreferredSize(new Dimension(100,400));
        rightPanel.add(rightPanelLeftFiller);
        rightPanel.add(buttonPanel);
        rightPanel.add(rightPanelRightFiller);
        this.setLayout(new BorderLayout());
        topBuffer.setOpaque(false);
        loadoutPanel.setOpaque(false);
        centerPanel.setOpaque(false);
        leftPanel.setOpaque(false);
        //this.setVisible(true);
        buttonPanel.setOpaque(false);
        this.add(topBuffer, BorderLayout.NORTH);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);
        loadoutList.setDragEnabled(false);
        this.addComponentListener(new LoadoutMenuComponentListener());
    }
    /**
     * Initializes the Button JPanel, located on the right side of the screen
     **/
    private void initButtonPanel(){
        buttonPanel = new JPanel();
        buttonPanelMiddleFiller = new JPanel();
        buttonPanelBottomFiller = new JPanel();
        buttonPanelMiddleFiller.setOpaque(false);
        buttonPanelBottomFiller.setOpaque(false);
        buttonPanelMiddleFiller.setPreferredSize(new Dimension(50,50));
        buttonPanelBottomFiller.setPreferredSize(new Dimension(50,50));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setPreferredSize(new Dimension(400,100));
        continueButton = new MenuButton(ImageContainer.getInstance().retrieveMenuImage(ImageContainer.MenuImage.MENU_CONTINUE));
        backButton = new MenuButton(ImageContainer.getInstance().retrieveMenuImage(ImageContainer.MenuImage.MENU_BACK));
        buttonPanel.add(continueButton);
        buttonPanel.add(buttonPanelMiddleFiller);
        buttonPanel.add(backButton);
        buttonPanel.add(buttonPanelBottomFiller);
    }
    /**
     * initializes the loadout panel, located on the left side of the screen
     **/
    private void initLoadoutPanel(){ 
        loadoutPanel = new JPanel();
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.X_AXIS));
        loadoutPanel.setLayout(new BoxLayout(loadoutPanel, BoxLayout.Y_AXIS));
        //loadoutPanel.setLayout(new BoxLayout(loadoutPanel, BoxLayout.Y_AXIS));
        removeButton = new MenuButton(ImageContainer.getInstance().retrieveMenuImage(ImageContainer.MenuImage.MENU_REMOVE));
        //loadoutPanel.setPreferredSize(new Dimension(400,600));
        LoadoutListModel model = new LoadoutListModel();
        loadoutList = new JList(model);
        loadoutList.setDragEnabled(false);
        //loadoutList.setBackground(Color.DARK_GRAY);
        loadoutPanelMiddleFiller = new JPanel();
        loadoutPanelBottomFiller = new JPanel();
        loadoutList.setBackground(new Color(44,53,57, 200));
        loadoutList.setOpaque(false);
        GameManager.getInstance().setLoadoutJList(identifier, loadoutList);
        loadoutScroll = new JScrollPane(loadoutList);
        loadoutScroll.setWheelScrollingEnabled(true);
        loadoutScroll.setPreferredSize(new Dimension(150,500));
        loadoutScroll.setOpaque(false);
        loadoutScroll.setBackground(new Color(44,53,57, 200));
        loadoutScroll.getVerticalScrollBar().addAdjustmentListener(event -> loadoutScroll.repaint());
        
        loadoutScroll.setWheelScrollingEnabled(true);
        loadoutPanelMiddleFiller = new JPanel();
        loadoutPanelBottomFiller = new JPanel();
        loadoutPanelMiddleFiller.setOpaque(false);
        loadoutPanelBottomFiller.setOpaque(false);
        loadoutPanelMiddleFiller.setPreferredSize(new Dimension(50,50));
        loadoutPanelBottomFiller.setPreferredSize(new Dimension(50,50));
        loadoutPanel.add(loadoutScroll);
        //loadoutPanel.add(Box.createVerticalGlue());
        loadoutPanel.add(loadoutPanelMiddleFiller);
        loadoutPanel.add(removeButton);
        //loadoutPanel.add(Box.createVerticalGlue());
        loadoutPanel.add(loadoutPanelBottomFiller);
        leftPanelLeftFiller = new JPanel();
        leftPanelRightFiller = new JPanel();
        leftPanelLeftFiller.setOpaque(false);
        leftPanelRightFiller.setOpaque(false);
        leftPanelLeftFiller.setPreferredSize(new Dimension(50,50));
        leftPanelRightFiller.setPreferredSize(new Dimension(50,50));
        leftPanel.add(leftPanelLeftFiller);
        leftPanel.add(loadoutPanel);
        leftPanel.add(leftPanelRightFiller);
        
        /*
        leftPanel.add(Box.createHorizontalGlue());
        leftPanel.add(loadoutPanel);
        leftPanel.add(Box.createHorizontalGlue());
        */
        loadoutList.setDragEnabled(true);
        loadoutList.setPreferredSize(new Dimension(350,loadoutPanel.getPreferredSize().height));
        loadoutList.setOpaque(true);
    }
    /**
     * Initializes the center panel where the choices are displayed
     */
    private void initChoicePanel(){
        centerPanel = new JPanel();
        
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        choicePanel = new JPanel();
        choicePanel.setMinimumSize(new Dimension(400,500));
        choicePanel.setBackground(new Color(44,53,57, 200));
        choicePanel.setLayout(new GridLayout(3,2));
        populateChoiceList();
        for(int i = 0; i < choiceList.size(); i++){
            choicePanel.add(choiceList.get(i));
        }
        centerScroll = new JScrollPane(choicePanel);
        centerScroll.setBackground(new Color(44,53,57, 200));
        centerScroll.getVerticalScrollBar().addAdjustmentListener(event -> centerPanel.repaint());
        centerScroll.setPreferredSize(new Dimension(700,600));
        centerScroll.setWheelScrollingEnabled(true);
        centerPanel.add(centerScroll);
        centerPanel.add(Box.createVerticalGlue());
        
    }
    /**
     * Initializes the status panel with all of the needed information
     */
    private void initStatsPanel(){
        
        statsPanel = new JPanel();
        statsLabel= new StatLabel(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.STATS));
        classLabel = new StatLabel(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.CLASS));
        meleeAttackRangeLabel = new StatLabel(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.ATTACK_RANGE));
        movementRangeLabel = new StatLabel(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.MOVEMENT_RANGE));
        mpLabel = new StatLabel(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.MP));
        hpLabel = new StatLabel(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.HP));
        strengthLabel = new StatLabel(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.STRENGTH));
        agilityLabel = new StatLabel(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.AGILITY));
        meleeDamageLabel = new StatLabel(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.MELEE_DAMAGE));
        rangedDamageLabel = new StatLabel(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.RANGED_DAMAGE));
        spellDamageLabel = new StatLabel(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.SPELL_DAMAGE));
        rangedAttackRangeLabel = new StatLabel(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.RANGED_ATTACK_RANGE));
        movementRangeLabel = new StatLabel(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.MOVEMENT_RANGE));
        dexterityLabel = new StatLabel(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.DEXTERITY));
        vitalityLabel = new StatLabel(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.VITALITY));
        intelligenceLabel = new StatLabel(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.INTELLIGENCE));
        dodgeChanceLabel = new StatLabel(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.DODGE_CHANCE));
        healthRegenLabel = new StatLabel(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.HEALTH_REGEN));
        armorLabel = new StatLabel(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.ARMOR));
        
        // labels that can be changed
        classNameLabel = new JLabel("-------"); 
        classNameLabel.setFont(font);
        movementRangeValueLabel = new JLabel("--");
        movementRangeValueLabel.setFont(font);
        mpValueLabel = new JLabel("--");
        mpValueLabel.setFont(font);
        hpValueLabel = new JLabel("--");
        hpValueLabel.setFont(font);
        strengthValueLabel = new JLabel("--");
        strengthValueLabel.setFont(font);
        agilityValueLabel = new JLabel("--");
        agilityValueLabel.setFont(font);
        meleeDamageValueLabel = new JLabel("--");
        meleeDamageValueLabel.setFont(font);
        rangedDamageValueLabel = new JLabel("--");
        rangedDamageValueLabel.setFont(font);
        spellDamageValueLabel = new JLabel("--");
        spellDamageValueLabel.setFont(font);
        meleeAttackRangeValueLabel = new JLabel("--");
        meleeAttackRangeValueLabel.setFont(font);
        rangedAttackRangeValueLabel = new JLabel("--");
        rangedAttackRangeValueLabel.setFont(font);
        movementRangeValueLabel = new JLabel("--");
        movementRangeValueLabel.setFont(font);
        dexterityValueLabel = new JLabel("--");
        dexterityValueLabel.setFont(font);
        vitalityValueLabel = new JLabel("--");
        vitalityValueLabel.setFont(font);
        intelligenceValueLabel = new JLabel("--");
        intelligenceValueLabel.setFont(font);
        dodgeChanceValueLabel = new JLabel("--");
        dodgeChanceValueLabel.setFont(font);
        healthRegenValueLabel = new JLabel("--");
        healthRegenValueLabel.setFont(font);
        armorValueLabel = new JLabel("--");
        armorValueLabel.setFont(font);
        // fuck this
        statsLabel.setForeground(Color.WHITE);
        classLabel.setForeground(Color.WHITE);
        meleeAttackRangeLabel.setForeground(Color.WHITE);
        movementRangeLabel.setForeground(Color.WHITE);
        mpLabel.setForeground(Color.WHITE);
        hpLabel.setForeground(Color.WHITE);
        strengthLabel.setForeground(Color.WHITE);
        agilityLabel.setForeground(Color.WHITE);
        meleeDamageLabel.setForeground(Color.WHITE);
        rangedDamageLabel.setForeground(Color.WHITE);
        spellDamageLabel.setForeground(Color.WHITE);
        rangedAttackRangeLabel.setForeground(Color.WHITE);
        movementRangeLabel.setForeground(Color.WHITE);
        dexterityLabel.setForeground(Color.WHITE);
        vitalityLabel.setForeground(Color.WHITE);
        intelligenceLabel.setForeground(Color.WHITE);
        dodgeChanceLabel.setForeground(Color.WHITE);
        healthRegenLabel.setForeground(Color.WHITE);
        armorLabel.setForeground(Color.WHITE);
        classNameLabel.setForeground(Color.WHITE);
        meleeAttackRangeValueLabel.setForeground(Color.WHITE);
        movementRangeValueLabel.setForeground(Color.WHITE);
        mpValueLabel.setForeground(Color.WHITE);
        hpValueLabel.setForeground(Color.WHITE);
        strengthValueLabel.setForeground(Color.WHITE);
        agilityValueLabel.setForeground(Color.WHITE);
        meleeDamageValueLabel.setForeground(Color.WHITE);
        rangedDamageValueLabel.setForeground(Color.WHITE);
        spellDamageValueLabel.setForeground(Color.WHITE);
        rangedAttackRangeValueLabel.setForeground(Color.WHITE);
        movementRangeValueLabel.setForeground(Color.WHITE);
        dexterityValueLabel.setForeground(Color.WHITE);
        vitalityValueLabel.setForeground(Color.WHITE);
        intelligenceValueLabel.setForeground(Color.WHITE);
        dodgeChanceValueLabel.setForeground(Color.WHITE);
        healthRegenValueLabel.setForeground(Color.WHITE);
        armorValueLabel.setForeground(Color.WHITE);
        // temp labels to help with alignment
        column2TempLabel = new JLabel(" ");
        column2TempLabel.setFont(font);
        column3TempLabel = new JLabel();
        column4TempLabel = new JLabel();
        column5TempLabel = new JLabel();
        column6TempLabel = new JLabel();
        column7TempLabel = new JLabel();
        column8TempLabel = new JLabel();
        
        statsPanel.setOpaque(false);
        statsLayout = new GroupLayout(statsPanel);
        statsPanel.setLayout(statsLayout);
        statsLayout.setAutoCreateGaps(true);
        statsLayout.setAutoCreateContainerGaps(true);
        statsLayout.setHorizontalGroup(
            statsLayout.createSequentialGroup()
                .addGroup(statsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(statsLabel)
                        .addComponent(classLabel)
                        .addComponent(hpLabel)
                        .addComponent(mpLabel)
                        .addComponent(strengthLabel)
                        .addComponent(agilityLabel))
                .addGroup(statsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(column2TempLabel)
                        .addComponent(classNameLabel)
                        .addComponent(hpValueLabel)
                        .addComponent(mpValueLabel)
                        .addComponent(strengthValueLabel)
                        .addComponent(agilityValueLabel))
                .addGroup(statsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(column3TempLabel)
                        .addComponent(meleeDamageLabel)
                        .addComponent(rangedDamageLabel)
                        .addComponent(spellDamageLabel)
                        .addComponent(meleeAttackRangeLabel)
                        .addComponent(rangedAttackRangeLabel))
                .addGroup(statsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(column4TempLabel)
                        .addComponent(meleeDamageValueLabel)
                        .addComponent(rangedDamageValueLabel)
                        .addComponent(spellDamageValueLabel)
                        .addComponent(meleeAttackRangeValueLabel)
                        .addComponent(rangedAttackRangeValueLabel))
                .addGroup(statsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(column5TempLabel)
                        .addComponent(movementRangeLabel)
                        .addComponent(dexterityLabel)
                        .addComponent(vitalityLabel)
                        .addComponent(intelligenceLabel)
                        .addComponent(dodgeChanceLabel))
                .addGroup(statsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(column6TempLabel)
                        .addComponent(movementRangeValueLabel)
                        .addComponent(dexterityValueLabel)
                        .addComponent(vitalityValueLabel)
                        .addComponent(intelligenceValueLabel)
                        .addComponent(dodgeChanceValueLabel))
                .addGroup(statsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(column7TempLabel)
                        .addComponent(healthRegenLabel)
                        .addComponent(armorLabel))
                .addGroup(statsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(column8TempLabel)
                        .addComponent(healthRegenValueLabel)
                        .addComponent(armorValueLabel))
        );
        
        
        statsLayout.setVerticalGroup(
            statsLayout.createSequentialGroup()
                .addGroup(statsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(statsLabel)
                        .addComponent(column2TempLabel)
                        .addComponent(column3TempLabel)
                        .addComponent(column4TempLabel)
                        .addComponent(column5TempLabel)
                        .addComponent(column6TempLabel)
                        .addComponent(column7TempLabel)
                        .addComponent(column8TempLabel))
                .addGroup(statsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)    
                        .addComponent(classLabel)
                        .addComponent(classNameLabel)
                        .addComponent(meleeDamageLabel)
                        .addComponent(meleeDamageValueLabel)
                        .addComponent(movementRangeLabel)
                        .addComponent(movementRangeValueLabel)
                        .addComponent(healthRegenLabel)
                        .addComponent(healthRegenValueLabel))
                .addGroup(statsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)   
                        .addComponent(hpLabel)
                        .addComponent(hpValueLabel)
                        .addComponent(rangedDamageLabel)
                        .addComponent(rangedDamageValueLabel)
                        .addComponent(dexterityLabel)
                        .addComponent(dexterityValueLabel)
                        .addComponent(armorLabel)
                        .addComponent(armorValueLabel))
                .addGroup(statsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)   
                        .addComponent(mpLabel)
                        .addComponent(mpValueLabel)
                        .addComponent(spellDamageLabel)
                        .addComponent(spellDamageValueLabel)
                        .addComponent(vitalityLabel)
                        .addComponent(vitalityValueLabel))
                .addGroup(statsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)   
                        .addComponent(strengthLabel)
                        .addComponent(strengthValueLabel)
                        .addComponent(meleeAttackRangeLabel)
                        .addComponent(meleeAttackRangeValueLabel)
                        .addComponent(intelligenceLabel)
                        .addComponent(intelligenceValueLabel))
                .addGroup(statsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)   
                        .addComponent(agilityLabel)
                        .addComponent(agilityValueLabel)
                        .addComponent(rangedAttackRangeLabel)
                        .addComponent(rangedAttackRangeValueLabel)
                        .addComponent(dodgeChanceLabel)
                        .addComponent(dodgeChanceValueLabel))
        );
    }
    /**
     * Populates the choiceList with custom labels
     */
    private void populateChoiceList(){
        choiceList.add(new CustomLabel("Archer", JLabel.CENTER, ImageContainer.getInstance().retrieveCharacterImage(ImageContainer.CharacterImage.ARCHER), new ArcherClass()));
        choiceList.add(new CustomLabel("Warrior", JLabel.CENTER, ImageContainer.getInstance().retrieveCharacterImage(ImageContainer.CharacterImage.WARRIOR), new WarriorClass()));
        choiceList.add(new CustomLabel("Rogue", JLabel.CENTER, ImageContainer.getInstance().retrieveCharacterImage(ImageContainer.CharacterImage.ROGUE), new RogueClass()));
        choiceList.add(new CustomLabel("Wizard", JLabel.CENTER, ImageContainer.getInstance().retrieveCharacterImage(ImageContainer.CharacterImage.WIZARD), new WizardClass()));
        choiceList.add(new CustomLabel("Healer", JLabel.CENTER, ImageContainer.getInstance().retrieveCharacterImage(ImageContainer.CharacterImage.HEALER), new HealerClass()));
        for(int i = 0; i < choiceList.size(); i++){
            choiceList.get(i).setFont(font);
        }
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
    }
    
    public void updateScreen(){
        rightPanel.setPreferredSize(new Dimension(GameApp.frame.getWidth()/4, rightPanel.getHeight()));
        rightPanelLeftFiller.setPreferredSize(new Dimension(rightPanel.getWidth()/10, rightPanel.getHeight()));
        rightPanelRightFiller.setPreferredSize(new Dimension(rightPanel.getWidth()/10, rightPanel.getHeight()));
        leftPanel.setPreferredSize(new Dimension((GameApp.frame.getWidth()/4), leftPanel.getHeight()));
        leftPanelLeftFiller.setPreferredSize(new Dimension(leftPanel.getWidth()/10, leftPanel.getHeight()));
        leftPanelRightFiller.setPreferredSize(new Dimension(leftPanel.getWidth()/10, leftPanel.getHeight()));
        loadoutPanel.setPreferredSize(new Dimension((int)(leftPanel.getWidth()*.8), (int)(loadoutPanel.getHeight()*.7)));
        topBuffer.setPreferredSize(new Dimension(GameApp.frame.getWidth(), GameApp.frame.getHeight()/4));
        bottomPanel.setPreferredSize(new Dimension(centerPanel.getWidth(), GameApp.frame.getHeight()/4));
        loadoutPanelMiddleFiller.setPreferredSize(new Dimension(10,GameApp.frame.getHeight()/20));
        loadoutPanelBottomFiller.setPreferredSize(new Dimension(10,GameApp.frame.getHeight()/20));
        bottomPanelRightFiller.setPreferredSize(new Dimension(bottomPanel.getWidth()/10, bottomPanel.getHeight()));
        bottomPanelLeftFiller.setPreferredSize(new Dimension(bottomPanel.getWidth()/10, bottomPanel.getHeight()));
        removeButton.setPreferredSize(new Dimension(buttonPanel.getWidth(), GameApp.frame.getHeight()/10));
        backButton.setPreferredSize(new Dimension(buttonPanel.getWidth(), GameApp.frame.getHeight()/10));
        continueButton.setPreferredSize(new Dimension(buttonPanel.getWidth(), GameApp.frame.getHeight()/10));
        buttonPanelMiddleFiller.setPreferredSize(new Dimension(10, GameApp.frame.getHeight()/10));
        buttonPanelBottomFiller.setPreferredSize(new Dimension(10, (int)(GameApp.frame.getHeight()/2)));
        this.revalidate();
        repaint();
    }
   
   private HashMap<String, Image> createImageMap(){        // creates the image map
       HashMap<String, Image> map = new HashMap<>();
       try{
           map.put("Archer", ImageContainer.getInstance().retrieveCharacterImage(ImageContainer.CharacterImage.ARCHER));
           map.put("Warrior", ImageContainer.getInstance().retrieveCharacterImage(ImageContainer.CharacterImage.WARRIOR));
           map.put("Rogue", ImageContainer.getInstance().retrieveCharacterImage(ImageContainer.CharacterImage.ROGUE));
           map.put("Wizard", ImageContainer.getInstance().retrieveCharacterImage(ImageContainer.CharacterImage.WIZARD));
           map.put("Healer", ImageContainer.getInstance().retrieveCharacterImage(ImageContainer.CharacterImage.HEALER));
       }
       catch(Exception ex){
           ex.printStackTrace();
       }
       return map;
   }
   public HashMap<String, Image> getImageMap(){
       return imageMap;
   }
   
   // methods that allow for the controller to add listeners/adapters
   /**
    * Adds a mouse adapter to the continue button
    * @param adapter 
    */
   public void addContinueButtonListener(MouseAdapter adapter){
       continueButton.addMouseListener(adapter);
   }
   /**
    * Adds a mouse adapter to the back button
    * @param adapter 
    */
   public void addBackButtonListener(MouseAdapter adapter){
       backButton.addMouseListener(adapter);   
   }
   /**
    * adds a mouse adapter to the remove button to allow
    * @param adapter 
    */
   public void addRemoveButtonController(MouseAdapter adapter){
       removeButton.addMouseListener(adapter);
   }
   /**
    * adds a mouse adapter to the custom label
    * @param label
    * @param adapter 
    */
   public void addLabelListener(CustomLabel label, MouseAdapter adapter){
       label.addMouseListener(adapter);
   }
   /**
    * sets the loadout list's cell renderer
    * @param cellRenderer
    */
   public void setLoadoutListCellRenderer(DefaultListCellRenderer cellRenderer){
       loadoutList.setCellRenderer(cellRenderer);
   }
   /**
    * 
    * @return returns an arrayList containing all of the possible units available for the user to choose from
    */
   public ArrayList<CustomLabel> getChoiceList(){
       return choiceList;
   }
   /**
    * 
    * @return an ArrayList containing Strings associated with each value in the loadout JList
    */
   public ArrayList<String> getLoadout(){
       return ((LoadoutListModel)loadoutList.getModel()).getSelections();
   }
   /**
    * 
    * @return the Loadout JList
    */
   public JList getLoadoutJList(){
       return loadoutList;
   }
   /**
    * instantiates a drop target and associates it with the loadout list along with a custom drop handler
    * @param handler 
    */
   public void addLoadoutListDropHandler(DropTargetListener handler){
       dropTarget = new DropTarget(loadoutList, handler);
   }
   /**
    * 
    * @return the menu's specified Font
    */
   public Font getLabelFont(){
       return font;
   }
   
   // getter methods for the stats labels
  
   /**
    * 
    * @return the JLabel associated with the selected unit's class name
    */
   public JLabel getClassNameLabel(){
       return classNameLabel;
   }
   /**
    * 
    * @return the JLabel associated with the selected unit's MP
    */
   public JLabel getMPValueLabel(){
       return mpValueLabel;
   }
   /**
    * 
    * @return the JLabel associated with the selected unit's HP
    */
   public JLabel getHPValueLabel(){
       return hpValueLabel;
   }
   /**
    * 
    * @return the JLabel associated with the selected unit's strength
    */
   public JLabel getStrengthValueLabel(){
       return strengthValueLabel;
   }
   /**
    * 
    * @return the JLabel associated with the selected unit's agility
    */
   public JLabel getAgilityValueLabel(){
       return agilityValueLabel;
   }
   /**
    * 
    * @return the JLabel associated with the selected unit's melee damage
    */
   public JLabel getMeleeDamageValueLabel(){
       return meleeDamageValueLabel;
   }
   /**
    * 
    * @return the JLabel associated with the selected unit's ranged damage
    */
   public JLabel getRangedDamageValueLabel(){
       return rangedDamageValueLabel;
   }
   /**
    * 
    * @return the JLabel associated with the selected unit's spell damage
    */
   public JLabel getSpellDamageValueLabel(){
       return spellDamageValueLabel;
   }
   /**
    * 
    * @return the JLabel associated with the selected unit's melee attack range
    */
   public JLabel getMeleeAttackRangeValueLabel(){
       return meleeAttackRangeValueLabel;
   }
   /**
    * 
    * @return the JLabel associated with the selected unit's ranged attack range
    */
   public JLabel getRangedAttackRangeValueLabel(){
       return rangedAttackRangeValueLabel;
   }
   /**
    * 
    * @return the JLabel associated with the selected unit's movement range
    */
   public JLabel getMovementRangeValueLabel(){
       return movementRangeValueLabel;
   }
   /**
    * 
    * @return the JLabel associated with the selected unit's dexterity
    */
   public JLabel getDexterityValueLabel(){
       return dexterityValueLabel;
   }
   /**
    * 
    * @return the JLabel associated with the selected unit's vitality
    */
   public JLabel getVitalityValueLabel(){
       return vitalityValueLabel;
   }
   /**
    * 
    * @return the JLabel associated with the selected unit's intelligence
    */
   public JLabel getIntelligenceValueLabel(){
       return intelligenceValueLabel;
   }
   /**
    * 
    * @return the JLabel associated with the selected unit's dodge chance
    */
   public JLabel getDodgeChanceValueLabel(){
       return dodgeChanceValueLabel;
   }
   /**
    * 
    * @return the JLabel associated with the selected unit's health regen
    */
   public JLabel getHealthRegenValueLabel(){
       return healthRegenValueLabel;
   }
   /**
    * 
    * @return the JLabel associated with the selected unit's armor
    */
   public JLabel getArmorValueLabel(){
       return armorValueLabel;
   }
   public class LoadoutMenuComponentListener extends ComponentAdapter{
        @Override
        public void componentResized(ComponentEvent e){
            updateScreen();
        }
    }
}