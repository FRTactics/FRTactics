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
    
    public enum MenuImage{
        MAIN_MENU, NEW_GAME_SWORD, OPTIONS_SWORD, QUIT_SWORD,
        PAUSE_MENU_BACKGROUND,PAUSE_MENU_OPTIONS, PAUSE_MENU_RESUME,
        PAUSE_MENU_QUIT,PAUSE_MENU_MAIN, MAIN_MENU_BACKGROUND, P1_LOADOUT_BACKGROUND,
        P2_LOADOUT_BACKGROUND
    };
    
    public enum TileEffects{
       MOVE_TILE, ATTACK_TILE
    };
    
    public enum TileImage{
       GRASS_TILE, WATER_TILE, HILL_TILE, RIDGE_TILE, MOUNTAIN_TILE, WHIRLPOOL_TILE
    };
    
    private static ImageContainer instance;
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
    private Image mainMenuBackground;
    private Image p1LoadoutBackground;
    private Image p2LoadoutBackground;
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
            }
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(ImageContainer.class.getName()).log(Level.SEVERE, null, ex);
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
