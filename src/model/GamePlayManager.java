package model;

import java.awt.Component;
import javax.swing.JPanel;
import model.classSystem.DefaultClass;
import view.GameApp;
import view.ingame.CharacterGlassPane;
import view.ingame.DrawPanel;
import view.ingame.Tile;

public class GamePlayManager
{
    private static GamePlayManager instance;
    
    
    public static GamePlayManager getInstance()
    {
        return instance == null ? instance = new GamePlayManager() : instance;
    }
    
    //Displays the movement range of the character on the tile
    public void displayRange(int x,int y)
    {
        Tile currentTile = DrawPanel.getGrid()[x][y];
        DefaultClass character = currentTile.retrieveCharacter();
        
        RangeChecker checker = new RangeChecker();
        int range = (int)character.getBaseMovementRange();
        
        checker.calculateRange(x, y, range);
    }
    
    public void displayCharacterSelectionBar()
    {
        Component oldGlassPane = GameApp.frame.getGlassPane();
        CharacterGlassPane pane = new CharacterGlassPane();
        GameApp.frame.setGlassPane(pane);
        pane.setVisible(true);
    }
}
