package model;

import java.awt.Component;
import java.awt.Image;
import javax.swing.JOptionPane;
import model.classSystem.DefaultClass;
import model.classSystem.HealerClass;
import view.GameApp;
import view.ingame.CharacterGlassPane;
import view.ingame.DrawPanel;
import view.ingame.Tile;

public class GamePlayManager
{   
    public enum Action {MOVE, ATTACK, DEFEND, WAITING, MAGIC, HEAL};
    
    private boolean isP1Turn = true;        
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
        int range;
        
        switch(rangeType)
        {
            case 0:
                range = (int)character.getMovementRange();
                break;
            default:
                range = (int)character.calcAttackRange();
                break;
        }
        
        checker.calculateRange(x, y, range, rangeType);
    }
    
    /** Sets the current selected unit
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
    public void resetUnits()
    {
        for(DefaultClass unit : GameManager.getInstance().getP1Loadout())
        {
            unit.movePerformed(false);
            unit.attackPerformed(false);
            unit.setDefending(false);
        }
        
         for(DefaultClass unit : GameManager.getInstance().getP2Loadout())
        {
            unit.movePerformed(false);
            unit.attackPerformed(false);
            unit.setDefending(false);
        }
    }
    
    public Action getGameplayStatus()
    {
        return isAttacking ? Action.ATTACK : isMoving ? Action.MOVE : isDefending ? Action.DEFEND : isHealing ? Action.HEAL : isMagic ? Action.MAGIC : Action.WAITING;
    }
    
    public void resetGameplayStatus()
    {
        removeDisplayedRange();
        isAttacking = false;
        isMoving = false;
        isHealing = false;
        isMagic = false;
    }
    
    public boolean healUnit(int targetX, int targetY)
    {
        Tile destinationTile = DrawPanel.getGrid()[targetX][targetY];
        Tile currentTile = DrawPanel.getGrid()[sourceX][sourceY];
        if(destinationTile.isHealRangeDisplayed())
        {
            if(destinationTile.isCharacterOnTile())
            {
                removeDisplayedRange();
                DefaultClass targetCharacter = (DefaultClass)destinationTile.retrieveCharacter()[0];
                HealerClass sourceCharacter = (HealerClass)currentTile.retrieveCharacter()[0];
                targetCharacter.setHealth(targetCharacter.getHealth() + sourceCharacter.calcHeal());
                sourceCharacter.attackPerformed(true);
                isHealing = false;
                return true;
            }
            else
                return false;
        }
        return false;
    }
    
    public boolean defendUnit()
    {
        DefaultClass sourceCharacter = (DefaultClass)DrawPanel.getGrid()[sourceX][sourceY].retrieveCharacter()[0];
        
        if(!sourceCharacter.isDefending())
        {
            sourceCharacter.setDefending(true); 
            sourceCharacter.attackPerformed(true);
            isDefending = false;
        }
        else
        {
            isDefending = false;
            return false;
        }
        
        return true;
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
                removeDisplayedRange();
                destinationTile.updateCharacter((DefaultClass)currentTile.retrieveCharacter()[0], (Image)currentTile.retrieveCharacter()[1]);
                currentTile.removeCharacter();
                ((DefaultClass)(destinationTile.retrieveCharacter()[0])).movePerformed(true);
                isMoving = false;
                return true;
            }
            else
                return false;
        }
        else
           return false;
    }
    
    public void removeDisplayedRange()
    {   
        DefaultClass character = (DefaultClass)DrawPanel.getGrid()[sourceX][sourceY].retrieveCharacter()[0];
        RangeChecker checker = new RangeChecker();
        int range = (int)(character.getMovementRange() + character.calcAttackRange());
        checker.calculateRange(sourceX, sourceY, range, 2);
    }
    
  
    public boolean attackUnit(int targetX, int targetY)
    {
        double health;
        Tile destinationTile = DrawPanel.getGrid()[targetX][targetY];
        DefaultClass targetCharacter = (DefaultClass)destinationTile.retrieveCharacter()[0];
        DefaultClass sourceCharacter = (DefaultClass)DrawPanel.getGrid()[sourceX][sourceY].retrieveCharacter()[0];
        if(destinationTile.isAttackRangeDisplayed())
        {
            if(targetCharacter != null)
            {  
                removeDisplayedRange();
                if(!targetCharacter.isDefending())
                {
                    health = targetCharacter.getHealth() - (sourceCharacter.calcAttackDamage() - targetCharacter.getArmor() - 20);
                    targetCharacter.setHealth(health);
                }
                else
                {
                    health = targetCharacter.getHealth() -(sourceCharacter.calcAttackDamage() - targetCharacter.getArmor());
                    targetCharacter.setHealth(health);    
                }
                // do the rest of the attacking stuff
                sourceCharacter.attackPerformed(true);
                
                // check to see if the unit died              
                if(targetCharacter.getHealth() < 0)
                {
                    destinationTile.removeCharacter();
                    for(DefaultClass unit: GameManager.getInstance().getP1Loadout())
                    {
                        if(unit.equals(targetCharacter))
                        {
                            GameManager.getInstance().getP1Loadout().remove(targetCharacter);
                            break;
                        }
                    }
                    for(DefaultClass unit: GameManager.getInstance().getP2Loadout())
                    {
                        if(unit.equals(targetCharacter))
                        {
                            GameManager.getInstance().getP2Loadout().remove(targetCharacter);
                            break;
                        }
                    }
                    checkWinCondition();
                }
                
                isAttacking = false;
                return true;
            }
            else
                return false;
        }
        else
            return false;
        
    }
    
    public void removeDiplsayedAttackRange(DefaultClass character)
    {
        RangeChecker checker = new RangeChecker();
        int range = (int)character.getAttackRange();
        checker.calculateRange(sourceX, sourceY, range, 2);
    }
    
    public boolean magicUnit(int targetX, int targetY)
    {   
        double health;
        Tile destinationTile = DrawPanel.getGrid()[targetX][targetY];
        DefaultClass targetCharacter = (DefaultClass)destinationTile.retrieveCharacter()[0];
        DefaultClass sourceCharacter = (DefaultClass)DrawPanel.getGrid()[sourceX][sourceY].retrieveCharacter()[0];
        if(destinationTile.isMagicRangeDisplayed())
        {
            if(targetCharacter != null)
            {
                removeDisplayedRange();
                if(!targetCharacter.isDefending())
                {
                    health = targetCharacter.getHealth() - (sourceCharacter.calcSpellDamage() - targetCharacter.getArmor() - 20);
                    targetCharacter.setHealth(health);    
                }
                else
                {
                    health = targetCharacter.getHealth() - (sourceCharacter.calcSpellDamage() - targetCharacter.getArmor());
                    targetCharacter.setHealth(health);    
                }
                sourceCharacter.attackPerformed(true);
                
                // check to see if the unit died              
                if(targetCharacter.getHealth() < 0)
                {
                    destinationTile.removeCharacter();
                    for(DefaultClass unit: GameManager.getInstance().getP1Loadout())
                    {
                        if(unit.equals(targetCharacter))
                        {
                            GameManager.getInstance().getP1Loadout().remove(targetCharacter);
                            break;
                        }
                    }
                    for(DefaultClass unit: GameManager.getInstance().getP2Loadout())
                    {
                        if(unit.equals(targetCharacter))
                        {
                            GameManager.getInstance().getP2Loadout().remove(targetCharacter);
                            break;
                        }
                    }
                    checkWinCondition();
                }
                
                isMagic = false;
                return true;
            }
            else
                return false;
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
    
    public void checkWinCondition()
    {
        if(GameManager.getInstance().getP1Loadout().isEmpty())
        {
            GameManager.getInstance().processEvent(GameManager.RETURN_TO_MAIN);
        }
        if(GameManager.getInstance().getP2Loadout().isEmpty())
        {
            GameManager.getInstance().processEvent(GameManager.RETURN_TO_MAIN);
        }
    }
    
    public String getPlayerTurn()
    {
        if(isP1Turn)
            return GameManager.PLAYER_1;
        else
            return GameManager.PLAYER_2;
    }
     
    public void switchTurns() 
    {
        if(isP1Turn)
        {
            setPlayerTurn(2);
            for(DefaultClass unit : GameManager.getInstance().getP2Loadout())
            {
                unit.movePerformed(false);
                unit.attackPerformed(false);
                unit.setDefending(false);
            }
        }
        else
        {
            setPlayerTurn(1);
            for(DefaultClass unit : GameManager.getInstance().getP1Loadout())
            {
                unit.movePerformed(false);
                unit.attackPerformed(false);
                unit.setDefending(false);
            }
        }
        GameApp.frame.repaint();
    }
    
}
