package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageContainer                 // class used to load all of the images
{
    private static ImageContainer instance;         // uses singleton design pattern
    // static attributes, each associated with their own image
    public static final int MAIN_MENU = 1;
    public static final int NEW_GAME_SWORD = 2;
    public static final int OPTIONS_SWORD = 3;
    public static final int QUIT_SWORD = 4;
    public static final int GRASS_TILE = 5;
    public static final int WATER_TILE = 6;
    public static final int HILL_TILE = 7;
    public static final int MOUNTAIN_TILE = 8;
    
    // all of the images
    private Image grass;
    private Image water;
    private Image hill;
    private Image mountain;
    private Image mainMenu; 
    private Image newGameSword;
    private Image optionsSword;
    private Image quitSword;
    
    private ImageContainer()        // upon creation, preload all of the images
    {
        preloadImages();
    }
    
    public static ImageContainer getInstance()      // get instance of the class
    {                   
        if(instance == null)                        // if it does not exist, create an instance
            instance = new ImageContainer();
        return instance;
    }
    
    public Image retrieveBackgroundImage(int event)                    // retrieves images
    {
        switch(event)
        {
            case MAIN_MENU:
                return mainMenu;
            case NEW_GAME_SWORD:
                return newGameSword;
            case OPTIONS_SWORD:
                return optionsSword;
            case QUIT_SWORD:
                return quitSword;
        }
        return null;
    }
    
    public Image retrieveTileImage(int event)       // retrieves image of the tile based upon the integer passed in
    {
        try
        {
            switch(event)
            {
                case WATER_TILE:           
                    return water == null ? water = ImageIO.read(new File(".\\resources\\tile\\landImages\\waterTile.png")) : water;
                case GRASS_TILE:
                    return grass == null ? grass = ImageIO.read(new File(".\\resources\\tile\\landImages\\grassTile.png")) : grass;
                case HILL_TILE:
                    return hill == null ? hill = ImageIO.read(new File(".\\resources\\tile\\landImages\\hillTile.png")) : hill;
                case MOUNTAIN_TILE:
                    return mountain == null ? mountain = ImageIO.read(new File(".\\resources\\tile\\landImages\\mountainTile.png")) : mountain;
            }
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
        return null;           
    }
    
    private void preloadImages() // reloads all of the images needed for the game
    {
        try
        {
            mainMenu = ImageIO.read(new File(".\\resources\\menu\\MainMenu.png"));      // main menu background image
            newGameSword = ImageIO.read(new File(".\\resources\\menu\\NewGame.png"));   // new game sword image
            optionsSword = ImageIO.read(new File(".\\resources\\menu\\Options.png"));   // options menu sword image
            quitSword = ImageIO.read(new File(".\\resources\\menu\\Quit.png"));         // quit game sword image
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
    }
}
