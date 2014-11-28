package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class ImageContainer 
{
    public enum CharacterImage{
      WARRIOR, WIZARD, ARCHER, HEALER, ROGUE
    };
    public enum StatusLabelImage{
        STATS, CLASS, HP, MP, STRENGTH, AGILITY, MELEE_DAMAGE, RANGED_DAMAGE, SPELL_DAMAGE, ATTACK_RANGE, RANGED_ATTACK_RANGE,
        MOVEMENT_RANGE, DEXTERITY, VITALITY, INTELLIGENCE, DODGE_CHANCE, HEALTH_REGEN, ARMOR, WARRIOR_TEXT, ARCHER_TEXT,
        ROGUE_TEXT, WIZARD_TEXT, HEALER_TEXT
    }
    public enum MenuImage{
        MAIN_MENU, NEW_GAME_SWORD, OPTIONS_SWORD, QUIT_SWORD,
        PAUSE_MENU_BACKGROUND,PAUSE_MENU_OPTIONS, PAUSE_MENU_RESUME, MENU_CONTINUE, MENU_BACK, MENU_REMOVE,
        PAUSE_MENU_QUIT,PAUSE_MENU_MAIN, MAIN_MENU_BACKGROUND, P1_LOADOUT_BACKGROUND,
        P2_LOADOUT_BACKGROUND, POPUP_BACKGROUND
    };
    
    public enum TileEffects{
       MOVE_TILE, ATTACK_TILE
    };
    
    public enum TileImage{
       GRASS_TILE, WATER_TILE, HILL_TILE, RIDGE_TILE, MOUNTAIN_TILE, WHIRLPOOL_TILE
    };
    
    private static ImageContainer instance;
    private Image popupBackground;
    private Image warriorTopDown;
    private Image archerTopDown;
    private Image healerTopDown;
    private Image wizardTopDown;
    private Image rogueTopDown;
    private Image warrior;
    private Image archer;
    private Image healer;
    private Image wizard;
    private Image rogue;
    private Image pauseMenuOptions;
    private Image pauseMenuResume;
    private Image pauseMenuQuit;
    private Image pauseMenuMain;
    private Image pauseMenuBackground;
    private Image move;
    private Image attack;
    private Image grass;
    private Image water;
    private Image hill;
    private Image ridge;
    private Image mountain;
    private Image whirlpool;
    private Image mainMenu; 
    private Image newGameSword;
    private Image optionsSword;
    private Image quitSword;
    private Image loadoutMenuRemove;
    private Image loadoutMenuContinue;
    private Image loadoutMenuBack;
    private Image mainMenuBackground;
    private Image p1LoadoutBackground;
    private Image p2LoadoutBackground;
    
    
    //stuff for stats
    private Image stats;
    private Image classImage;
    private Image hp;
    private Image mp;
    private Image strength;
    private Image agility;
    private Image meleeDamage;
    private Image rangedDamage;
    private Image spellDamage;
    private Image attackRange;
    private Image rangedAttackRange;
    private Image movementRange;
    private Image dexterity;
    private Image vitality;
    private Image intelligence;
    private Image dodgeChance;
    private Image healthRegen;
    private Image armor;
    private Image warriorText;
    private Image archerText;
    private Image wizardText;
    private Image healerText;
    private Image rogueText;

    private ImageContainer()
    {
        preloadImages();
    }
    
    public static ImageContainer getInstance()
    {
        if(instance == null)
            instance = new ImageContainer();
        return instance;
    }
    public Image retrieveMenuImage(MenuImage selection)
    {
        try 
        {
            switch(selection)
            {
                case MAIN_MENU:
                    return mainMenu;
                case NEW_GAME_SWORD:
                    return newGameSword;
                case OPTIONS_SWORD:
                    return optionsSword;
                case QUIT_SWORD:
                    return quitSword;
                case MENU_REMOVE:
                    return loadoutMenuRemove == null ? loadoutMenuRemove = ImageIO.read(new File(".//resources//menu//RemoveUnit.png")) : loadoutMenuRemove;
                case MENU_CONTINUE:
                    return loadoutMenuContinue == null ? loadoutMenuContinue = ImageIO.read(new File(".//resources//menu//Continue.png")) : loadoutMenuContinue;
                case MENU_BACK:
                    return loadoutMenuBack == null ? loadoutMenuBack = ImageIO.read(new File(".//resources//menu//Back.png")) : loadoutMenuBack;
                case MAIN_MENU_BACKGROUND:
                    return mainMenuBackground == null ? mainMenuBackground = ImageIO.read(new File(".//resources//menu//backgrounds//mainMenuBackground.png")) : mainMenuBackground;
                case P1_LOADOUT_BACKGROUND:
                    return p1LoadoutBackground == null ? p1LoadoutBackground = ImageIO.read(new File(".//resources//menu//backgrounds/p1LoadoutBackground.png")) : p1LoadoutBackground;
                case P2_LOADOUT_BACKGROUND:
                    return p2LoadoutBackground == null ? p2LoadoutBackground = ImageIO.read(new File(".//resources//menu//backgrounds/p2LoadoutBackground.png")) : p2LoadoutBackground;
                case PAUSE_MENU_BACKGROUND:
                    return pauseMenuBackground == null ? pauseMenuBackground = ImageIO.read(new File(".//resources//menu//pauseMenuBackground.png")): pauseMenuBackground;
                case PAUSE_MENU_OPTIONS:
                    return pauseMenuOptions == null ? pauseMenuOptions = ImageIO.read(new File(".//resources//menu//pauseMenuOptions.png")): pauseMenuOptions;
                case PAUSE_MENU_RESUME:
                    return pauseMenuResume == null ? pauseMenuResume = ImageIO.read(new File(".//resources//menu//pauseMenuResume.png")): pauseMenuResume;
                case PAUSE_MENU_QUIT:
                    return pauseMenuQuit == null ? pauseMenuQuit = ImageIO.read(new File(".//resources//menu//pauseMenuQuit.png")): pauseMenuQuit;
                case PAUSE_MENU_MAIN:
                    return pauseMenuMain == null ? pauseMenuMain = ImageIO.read(new File(".//resources//menu//pauseMenuMain.png")): pauseMenuMain;
                case POPUP_BACKGROUND:
                    return popupBackground == null ? popupBackground = ImageIO.read(new File(".//resources//menu//CombatPopup.png")) : popupBackground;
            }
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(ImageContainer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Image retrieveStatusLabelImages(StatusLabelImage selection){
        try 
        {
            switch(selection)
            {
                case STATS:
                    return stats == null ? stats = ImageIO.read(new File(".//resources//menu//statusImages//Stats.png")) : stats;
                case CLASS:
                    return classImage == null ? classImage = ImageIO.read(new File(".//resources//menu//statusImages//Class.png")) : classImage;
                case HP:
                    return hp == null ? hp = ImageIO.read(new File(".//resources//menu//statusImages//HP.png")) : hp;
                case MP:
                    return mp == null ? mp = ImageIO.read(new File(".//resources//menu//statusImages//MP.png")) : mp;
                case STRENGTH:
                    return strength == null ? strength = ImageIO.read(new File(".//resources//menu//statusImages//Strength.png")): strength;
                case AGILITY:
                    return agility == null ? agility = ImageIO.read(new File(".//resources//menu//statusImages//Agility.png")): agility;
                case MELEE_DAMAGE:
                    return meleeDamage == null ? meleeDamage = ImageIO.read(new File(".//resources//menu//statusImages//MeleeDamage.png")): meleeDamage;
                case RANGED_DAMAGE:
                    return rangedDamage == null ? rangedDamage = ImageIO.read(new File(".//resources//menu//statusImages//RangedDamage.png")): rangedDamage;
                case SPELL_DAMAGE:
                    return spellDamage == null ? spellDamage = ImageIO.read(new File(".//resources//menu//statusImages//SpellDamage.png")): spellDamage;
                case ATTACK_RANGE:
                    return attackRange == null ? attackRange = ImageIO.read(new File(".//resources//menu//statusImages//AttackRange.png")): attackRange;
                case RANGED_ATTACK_RANGE:
                    return rangedAttackRange == null ? rangedAttackRange = ImageIO.read(new File(".//resources//menu//statusImages//RangedAttackRange.png")): rangedAttackRange;
                case MOVEMENT_RANGE:
                    return movementRange == null ? movementRange = ImageIO.read(new File(".//resources//menu//statusImages//MovementRange.png")): movementRange;
                case DEXTERITY:
                    return dexterity == null ? dexterity = ImageIO.read(new File(".//resources//menu//statusImages//Dexterity.png")): dexterity;
                case VITALITY:
                    return vitality == null ? vitality = ImageIO.read(new File(".//resources//menu//statusImages//Vitality.png")): vitality;
                case INTELLIGENCE:
                    return intelligence == null ? intelligence = ImageIO.read(new File(".//resources//menu//statusImages//Intelligence.png")): intelligence;
                case DODGE_CHANCE:
                    return dodgeChance == null ? dodgeChance = ImageIO.read(new File(".//resources//menu//statusImages//DodgeChance.png")): dodgeChance;
                case HEALTH_REGEN:
                    return healthRegen == null ? healthRegen = ImageIO.read(new File(".//resources//menu//statusImages//HealthRegen.png")): healthRegen;
                case ARMOR:
                    return armor == null ? armor = ImageIO.read(new File(".//resources//menu//statusImages//Armor.png")): armor;
                case WARRIOR_TEXT:
                    return warriorText == null ? warriorText = ImageIO.read(new File(".//resources//menu//statusImages//WarriorText.png")): warriorText;
                case ARCHER_TEXT:
                    return archerText == null ? archerText = ImageIO.read(new File(".//resources//menu//statusImages//ArcherText.png")): archerText;
                case ROGUE_TEXT:
                    return rogueText == null ? rogueText = ImageIO.read(new File(".//resources//menu//statusImages//RogueText.png")): rogueText;
                case HEALER_TEXT:
                    return healerText == null ? healerText = ImageIO.read(new File(".//resources//menu//statusImages//HealerText.png")): healerText;
                case WIZARD_TEXT:
                    return wizardText == null ? wizardText = ImageIO.read(new File(".//resources//menu//statusImages//MageText.png")): wizardText;
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    
    public Image retrieveTileEffects(TileEffects selection)
    {
        try
        {
            switch(selection)
            {
                case ATTACK_TILE:
                        return attack == null ? attack = ImageIO.read(new File(".//resources//tile//effectsImages//attackTile.png")): attack;
                case MOVE_TILE:
                        return move == null ? move = ImageIO.read(new File(".//resources//tile//effectsImages//moveTile.png")): move;
            }
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
        return null;
    }
    
    public Image retrieveTileImage(TileImage selection)
    {
        try
        {
            switch(selection)
            {
                case WATER_TILE:
                    return water == null ? water = ImageIO.read(new File(".//resources//tile//landImages//waterTile.png")) : water;
                case GRASS_TILE:
                    return grass == null ? grass = ImageIO.read(new File(".//resources//tile//landImages//grassTile.png")) : grass;
                case HILL_TILE:
                    return hill == null ? hill = ImageIO.read(new File(".//resources//tile//landImages//hillTile.png")) : hill;
                case RIDGE_TILE:
                    return ridge == null ? ridge = ImageIO.read(new File(".//resources//tile//landImages//ridgeTile.png")) : ridge;
                case MOUNTAIN_TILE:
                    return mountain == null ? mountain = ImageIO.read(new File(".//resources//tile//landImages//mountainTile.png")) : mountain;
                case WHIRLPOOL_TILE:
                    return whirlpool == null ? whirlpool = ImageIO.read(new File(".//resources//tile//landImages//whirlpoolTile.png")) : whirlpool;     
            }
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
        return null;           
    }
    
    public Image retrieveCharacterTileImage(CharacterImage selection)
    {
        try
        {
            switch(selection)
            {
                case WARRIOR:
                    return warriorTopDown == null ? warriorTopDown = ImageIO.read(new File(".//resources//tile//characterImages//WarriorTopDown.png")) : warriorTopDown;
                case ARCHER:
                    return archerTopDown == null ? archerTopDown = ImageIO.read(new File(".//resources//tile//characterImages//ArcherTopDown.png")) : archerTopDown;
                case ROGUE:
                    return rogueTopDown == null ? rogueTopDown = ImageIO.read(new File(".//resources//tile//characterImages//RogueTopDown.png")) : rogueTopDown;
                case WIZARD:
                    return wizardTopDown == null ? wizardTopDown = ImageIO.read(new File(".//resources//tile//characterImages//WizardTopDown.png")) : wizardTopDown;
                case HEALER:
                    return healerTopDown == null ? healerTopDown = ImageIO.read(new File(".//resources//tile//characterImages//HealerTopDown.png")) : healerTopDown;
                
            }
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
        return null;
    }
    
    public Image retrieveCharacterImage(CharacterImage selection)
    {
        try
        {
            switch(selection)
            {
                case WARRIOR:
                    return warrior == null ? warrior = ImageIO.read(new File(".//resources//characters//Warrior.png")) : warrior;
                case ARCHER:
                    return archer == null ? archer = ImageIO.read(new File(".//resources//characters//Archer.png")) : archer;
                case ROGUE:
                    return rogue == null ? rogue = ImageIO.read(new File(".//resources//characters//Rogue.png")) : rogue;
                case WIZARD:
                    return wizard == null ? wizard = ImageIO.read(new File(".//resources//characters//Wizard.png")) : wizard;
                case HEALER:
                    return healer == null ? healer = ImageIO.read(new File(".//resources//characters//Healer.png")) : healer;
                
            }
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
        return null;
    }
    
    private void preloadImages() 
    {
        try
        {
            mainMenu = ImageIO.read(new File(".//resources//menu//MainMenu.png"));
            newGameSword = ImageIO.read(new File(".//resources//menu//NewGame.png"));
            optionsSword = ImageIO.read(new File(".//resources//menu//Options.png"));
            quitSword = ImageIO.read(new File(".//resources//menu//Quit.png"));
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
    }
}
