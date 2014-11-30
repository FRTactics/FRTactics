package model;

import model.classSystem.DefaultClass;
import view.ingame.DrawPanel;
import view.ingame.Tile;

public class CombatManager
{
    public CombatManager()
    {
        
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
    
    
}
