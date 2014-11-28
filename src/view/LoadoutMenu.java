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
public class LoadoutMenu extends JPanel {             // still a work in progress, looks like a piece of shit now - Charlie
    
    private ArrayList<CustomLabel> choiceList = new ArrayList();
    private String identifier;      //String used to indentify which loadout this is P1 or P2
    private JPanel loadoutPanel;
    private JList loadoutList;
    private JPanel choicePanel;
    private HashMap<String, Image> imageMap;
    private JPanel buttonPanel; // placed on east side
    private JPanel leftPanel;
    private JPanel centerPanel;     // will hold a jpanel containing all of the images (gridlayout)
    private JScrollPane centerScroll;
    //private JTabbedPane centerTabs;
    private JScrollPane loadoutScroll;
    private JPanel topBuffer;    // just a buffer placed on the top screen to make it pretty
    private JPanel statsPanel; // panel to hold all of the stats for each unit
    
   // stuff for the stats
    private GroupLayout statsLayout;
    // labels for the stats
    
    // Column 1
    private JLabel statsLabel;
    private JLabel classLabel;
    private JLabel hpLabel;
    private JLabel mpLabel;
    private JLabel strengthLabel;
    private JLabel agilityLabel;
    // Column 2
    private JLabel column2TempLabel;
    private JLabel classNameLabel;
    private JLabel hpValueLabel;
    private JLabel mpValueLabel;
    private JLabel strengthValueLabel;
    private JLabel agilityValueLabel;
    
    // Column 3
    private JLabel column3TempLabel;
    private JLabel meleeDamageLabel;
    private JLabel rangedDamageLabel;
    private JLabel spellDamageLabel;
    private JLabel meleeAttackRangeLabel;
    private JLabel rangedAttackRangeLabel;

    // Column 4
    private JLabel column4TempLabel;
    private JLabel meleeDamageValueLabel;
    private JLabel rangedDamageValueLabel;
    private JLabel spellDamageValueLabel;
    private JLabel meleeAttackRangeValueLabel; 
    private JLabel rangedAttackRangeValueLabel;
    
    // Column 5
    private JLabel column5TempLabel;
    private JLabel movementRangeLabel;
    private JLabel dexterityLabel;
    private JLabel vitalityLabel;
    private JLabel intelligenceLabel;
    private JLabel dodgeChanceLabel;
    // Column 6
    private JLabel column6TempLabel;
    private JLabel movementRangeValueLabel;
    private JLabel dexterityValueLabel;
    private JLabel vitalityValueLabel;
    private JLabel intelligenceValueLabel;
    private JLabel dodgeChanceValueLabel;
    
    // Column 7
    private JLabel column7TempLabel;
    private JLabel healthRegenLabel;
    private JLabel armorLabel;
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
    
    public LoadoutMenu(String identifier, Image image) {
        this.identifier = identifier;
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
        centerPanel.add(statsPanel);
        centerPanel.add(Box.createVerticalGlue());
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
        this.add(buttonPanel, BorderLayout.EAST);
        loadoutList.setDragEnabled(false);
    }
    // initializes the Button JPanel, located on the right side of the screen
    private void initButtonPanel(){
        buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(400,100));
        continueButton = new MenuButton(ImageContainer.getInstance().retrieveMenuImage(ImageContainer.MenuImage.MENU_CONTINUE));
        backButton = new MenuButton(ImageContainer.getInstance().retrieveMenuImage(ImageContainer.MenuImage.MENU_BACK));
        buttonPanel.add(continueButton);
        buttonPanel.add(backButton);
    }
    // initializes the loadout panel, located on the left side of the screen
    private void initLoadoutPanel(){ 
        loadoutPanel = new JPanel();
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.X_AXIS));
        loadoutPanel.setLayout(new BoxLayout(loadoutPanel, BoxLayout.Y_AXIS));
        //loadoutPanel.setLayout(new BoxLayout(loadoutPanel, BoxLayout.Y_AXIS));
        removeButton = new MenuButton(ImageContainer.getInstance().retrieveMenuImage(ImageContainer.MenuImage.MENU_REMOVE));
        loadoutPanel.setPreferredSize(new Dimension(400,600));
        LoadoutListModel model = new LoadoutListModel();
        loadoutList = new JList(model);
        loadoutList.setDragEnabled(false);
        loadoutList.setBackground(Color.DARK_GRAY);
        loadoutList.setOpaque(false);
        GameManager.getInstance().setLoadoutJList(identifier, loadoutList);
        loadoutScroll = new JScrollPane(loadoutList);
        loadoutScroll.setWheelScrollingEnabled(true);
        loadoutScroll.setPreferredSize(new Dimension(400,500));
        loadoutPanel.add(loadoutScroll);
        loadoutPanel.add(Box.createVerticalGlue());
        loadoutPanel.add(removeButton);
        loadoutPanel.add(Box.createRigidArea(new Dimension(100,100)));
        leftPanel.add(Box.createRigidArea(new Dimension(60,100)));
        leftPanel.add(loadoutPanel);
        leftPanel.add(Box.createRigidArea(new Dimension(60,100)));
        loadoutList.setDragEnabled(true);
        loadoutList.setPreferredSize(new Dimension(350,loadoutPanel.getPreferredSize().height));
        loadoutList.setOpaque(true);
    }
    // initializes the center panel, which contains a panel that holds all of the player choices
    private void initChoicePanel(){
        centerPanel = new JPanel();
        
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        choicePanel = new JPanel();
        choicePanel.setMinimumSize(new Dimension(400,500));
        choicePanel.setBackground(Color.DARK_GRAY);
        choicePanel.setLayout(new GridLayout(3,2));
        populateChoiceList();
        for(int i = 0; i < choiceList.size(); i++){
            choicePanel.add(choiceList.get(i));
        }
        centerScroll = new JScrollPane(choicePanel);
        //centerTabs = new JTabbedPane();
        //centerTabs.putClientProperty("JComponent.sizeVariant", "large");
        //centerTabs.addTab("Classes", centerScroll);
        //centerTabs.addTab("Empty", new JPanel());
        centerScroll.setPreferredSize(new Dimension(700,600));
        centerScroll.setWheelScrollingEnabled(true);
        centerPanel.add(centerScroll);
        centerPanel.add(Box.createVerticalGlue());
        
    }
    // initialize the status panel with all of the labels needed
    private void initStatsPanel(){
        statsPanel = new JPanel();
        // permanent labels
        
        statsLabel= new JLabel(new ImageIcon(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.STATS).getScaledInstance(100, 50, 0)));
        classLabel = new JLabel(new ImageIcon(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.CLASS).getScaledInstance(80, 40, 0)));
        meleeAttackRangeLabel = new JLabel(new ImageIcon(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.ATTACK_RANGE).getScaledInstance(120, 40, 0)));
        movementRangeLabel = new JLabel(new ImageIcon(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.MOVEMENT_RANGE).getScaledInstance(120, 40, 0)));
        mpLabel = new JLabel(new ImageIcon(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.MP).getScaledInstance(80, 40, 0)));
        hpLabel = new JLabel(new ImageIcon(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.HP).getScaledInstance(80, 40, 0)));
        strengthLabel = new JLabel(new ImageIcon(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.STRENGTH).getScaledInstance(80, 40, 0)));
        agilityLabel = new JLabel(new ImageIcon(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.AGILITY).getScaledInstance(80, 40, 0)));
        meleeDamageLabel = new JLabel(new ImageIcon(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.MELEE_DAMAGE).getScaledInstance(120, 40, 0)));
        rangedDamageLabel = new JLabel(new ImageIcon(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.RANGED_DAMAGE).getScaledInstance(120, 40, 0)));
        spellDamageLabel = new JLabel(new ImageIcon(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.SPELL_DAMAGE).getScaledInstance(120, 40, 0)));
        rangedAttackRangeLabel = new JLabel(new ImageIcon(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.RANGED_ATTACK_RANGE).getScaledInstance(80, 40, 0)));
        movementRangeLabel = new JLabel(new ImageIcon(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.MOVEMENT_RANGE).getScaledInstance(120, 40, 0)));
        dexterityLabel = new JLabel(new ImageIcon(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.DEXTERITY).getScaledInstance(120, 40, 0)));
        vitalityLabel = new JLabel(new ImageIcon(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.VITALITY).getScaledInstance(120, 40, 0)));
        intelligenceLabel = new JLabel(new ImageIcon(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.INTELLIGENCE).getScaledInstance(120, 40, 0)));
        dodgeChanceLabel = new JLabel(new ImageIcon(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.DODGE_CHANCE).getScaledInstance(120, 40, 0)));
        healthRegenLabel = new JLabel(new ImageIcon(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.HEALTH_REGEN).getScaledInstance(120, 40, 0)));
        armorLabel = new JLabel(new ImageIcon(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.ARMOR).getScaledInstance(80, 40, 0)));
        
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
        column2TempLabel = new JLabel();
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
   public void addContinueButtonListener(MouseAdapter adapter){
       continueButton.addMouseListener(adapter);
   }
   
   public void addBackButtonListener(MouseAdapter adapter){
       backButton.addMouseListener(adapter);
      
   }
   public void addRemoveButtonController(MouseAdapter adapter){
       removeButton.addMouseListener(adapter);
   }
   public void addLabelListener(CustomLabel label, MouseAdapter adapter){
       label.addMouseListener(adapter);
   }
   public void setLoadoutListCellRenderer(DefaultListCellRenderer d){
       loadoutList.setCellRenderer(d);
   }
   
   public ArrayList<CustomLabel> getChoiceList(){
       return choiceList;
   }
   public ArrayList<String> getLoadout(){
       return ((LoadoutListModel)loadoutList.getModel()).getSelections();
   }
   public JList getLoadoutJList(){
       return loadoutList;
   }
   public void addLoadoutListDropHandler(DropTargetListener handler){
       dropTarget = new DropTarget(loadoutList, handler);
   }
   
   public Font getLabelFont(){
       return font;
   }
   
   // getter methods for the stats labels
   public JLabel getClassNameLabel(){
       return classNameLabel;
   }
   public JLabel getMeleeAttackRangeLabel(){
       return meleeAttackRangeLabel;
   }
   public JLabel getMovementRangeLabel(){
       return movementRangeLabel;
   }
   public JLabel getMPValueLabel(){
       return mpValueLabel;
   }
   public JLabel getHPValueLabel(){
       return hpValueLabel;
   }
   public JLabel getStrengthValueLabel(){
       return strengthValueLabel;
   }
   public JLabel getAgilityValueLabel(){
       return agilityValueLabel;
   }
   public JLabel getMeleeDamageValueLabel(){
       return meleeDamageValueLabel;
   }
   public JLabel getRangedDamageValueLabel(){
       return rangedDamageValueLabel;
   }
   public JLabel getSpellDamageValueLabel(){
       return spellDamageValueLabel;
   }
   public JLabel getMeleeAttackRangeValueLabel(){
       return meleeAttackRangeValueLabel;
   }
   public JLabel getRangedAttackRangeValueLabel(){
       return rangedAttackRangeValueLabel;
   }
   public JLabel getMovementRangeValueLabel(){
       return movementRangeValueLabel;
   }
   public JLabel getDexterityValueLabel(){
       return dexterityValueLabel;
   }
   public JLabel getVitalityValueLabel(){
       return vitalityValueLabel;
   }
   public JLabel getIntelligenceValueLabel(){
       return intelligenceValueLabel;
   }
   public JLabel getDodgeChanceValueLabel(){
       return dodgeChanceValueLabel;
   }
   public JLabel getHealthRegenValueLabel(){
       return healthRegenValueLabel;
   }
   public JLabel getArmorValueLabel(){
       return armorValueLabel;
   }
   
}