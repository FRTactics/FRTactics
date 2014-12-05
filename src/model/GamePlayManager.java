package model;

import java.awt.Image;
import model.classSystem.DefaultClass;
import view.GameApp;
import view.ingame.CharacterGlassPane;
import view.ingame.DrawPanel;
import view.ingame.Tile;

public class GamePlayManager
{   
    public enum Action {MOVE, ATTACK, DEFEND, WAITING, MAGIC, HEAL};
    
    private boolean isP1Turn = false;        
    private boolean isP2Turn = false;
    private boolean isAttacking = false;
    private boolean isMoving = false;
    private boolean isDefending = false;
    private boolean isHealing = false;
    private boolean isMagic = false;
    private static GamePlayManager instance;
    private int sourceX;
    private int sourceY;
    
    public static GamePlayManager getInstance()
    {
        return instance == null ? instance = new GamePlayManager() : instance;
    }
    
    //Displays the movement range of the character on the tile
    public void displayRange(int x,int y,int rangeType)
    {
        Tile currentTile = DrawPanel.getGrid()[x][y];
        DefaultClass character = (DefaultClass)currentTile.retrieveCharacter()[0];
        
        RangeChecker checker = new RangeChecker();
        int range = (int)character.getMovementRange();
        
        checker.calculateRange(x, y, range, rangeType);
    }
    
    /** Sets the current selected unit
     * 
     * 
     * @param startX starting x position
     * @param startY starting y position 
     * @param action 
     */
    public void setUnit(int startX , int startY, Action action)
    {
        switch(action)
        {
            case MOVE:
                isMoving = true;
                break;
            case ATTACK:
                isAttacking = true;
                break;
            case DEFEND:
                isDefending = true;
                break;
            case HEAL:
                isHealing = true;
                break;
            case MAGIC:
                isMagic = true;
                break;
        }
        this.sourceX = startX;
        this.sourceY = startY;
    }
    public Action getGameplayStatus()
    {
        return isAttacking ? Action.ATTACK : isMoving ? Action.MOVE : isDefending ? Action.DEFEND : isHealing ? Action.HEAL : isMagic ? Action.MAGIC : Action.WAITING;
    }
    
    /** 
     * @param targetX this will be the final x value of the tile the user wants the character to move to
     * @param targetY this will be the final y value of the tile that the user wants the character to move to
     * @return 
     */
    public boolean moveUnit(int targetX, int targetY)
    {
        Tile currentTile = DrawPanel.getGrid()[sourceX][sourceY];
        Tile destinationTile = DrawPanel.getGrid()[targetX][targetY];
        if(destinationTile.isMovementRangeDisplayed())
        {
            if(!destinationTile.isCharacterOnTile())
            {      
                destinationTile.updateCharacter((DefaultClass)currentTile.retrieveCharacter()[0], (Image)currentTile.retrieveCharacter()[1]);
                currentTile.removeCharacter();
                removeDisplayedRange((DefaultClass)destinationTile.retrieveCharacter()[0]);
                isMoving = false;
                return true;
            }
            else
                return false;
        }
        else
           return false;
    }
    
    public void removeDisplayedRange(DefaultClass character)
    {      
        RangeChecker checker = new RangeChecker();
        int range = (int)character.getMovementRange();
        
        checker.calculateRange(sourceX, sourceY, range, 2);
    }
    
  
    public boolean attackUnit(int targetX, int targetY)
    {
        DefaultClass targetCharacter = (DefaultClass)DrawPanel.getGrid()[targetX][targetY].retrieveCharacter()[0];
        DefaultClass sourceCharacter = (DefaultClass)DrawPanel.getGrid()[sourceX][sourceY].retrieveCharacter()[0];
        if(targetCharacter != null)
        {
            
            return true;
        }
        else
            return false;
    }
    
    public void displayCharacterSelectionBar()
    {
        CharacterGlassPane pane = new CharacterGlassPane();
        GameApp.frame.setGlassPane(pane);
        pane.setVisible(true);
    }

     public void setPlayerTurn(int player)
    {
        if(player == 1)
        {
            isP1Turn = true;
            isP2Turn = false;
        }
        else if(player == 2)
        {
            isP1Turn = false;
            isP2Turn = true;
        }
    }
    
    public void switchTurns() 
    {
        if(isP1Turn)
        {
            setPlayerTurn(1);
        }
        else
        {
            setPlayerTurn(2);
        }
    }
}
