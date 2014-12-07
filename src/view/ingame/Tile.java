package view.ingame;

import exceptions.InstanceNotCreatedException;
import java.awt.Component;
import model.ImageContainer;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetContext;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import model.CharacterFlavor;
import model.GamePlayManager;
import model.GamePlayManager.Action;
import model.classSystem.DefaultClass;
import view.GameApp;

public class Tile extends JPanel
{
    public enum LandType{
        WATER_TILE, GRASS_TILE, HILL_TILE, RIDGE_TILE, MOUNTAIN_TILE
    };
    
    private final LandType landType;
    private final int gridSize;
    private Polygon polygon;
    private Image tileImage;
    private Image attackRangeImage;
    private Image moveRangeImage;
    private Image placeUnitImage;
    private Image characterImage;
    private final int xLocation;
    private final int yLocation;
    private DefaultClass character;
    private boolean characterOnTile = false;
    private boolean displayMovementRange = false;
    private boolean displayAttackRange = false;
    private boolean displayPlaceUnit = false;
    /**The constructor is used to define the type of tile that the 
    *hexagon will become.
    * @param landType The enum value that will assign the land type to the tile
    * @param gridSize The size of the current map array
     * @param xLocation The tile x location in the grid
     * @param yLocation The tile y location in the grid
    */ 
    public Tile(LandType landType, int gridSize, int xLocation, int yLocation)
    {
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.landType = landType;
        this.gridSize = gridSize;
        this.addMouseListener(new TileMouseAdapter());
        this.addMouseMotionListener(new TileMouseMotionAdapter());
        DropTarget drop = new DropTarget(this, new TileDropTargetAdapter());
    }
    
    /**Method returns an enum of LandType based on its current set value.
    * @return The enum value of the current land type
    */
    public LandType returnLandType()
    {
        return landType;
    }
    
    public void updatePreferredSize(Dimension preferredSize)
    {
        setPreferredSize(preferredSize);
    }
        
    public boolean isCharacterOnTile()
    {
        return characterOnTile;   
    }
    
    public void updateCharacter(DefaultClass character, Image characterImage)
    {
        this.character = character;
        this.characterImage = characterImage;
        characterOnTile = true;
    }
    public void removeCharacter()
    {
        character = null;
        characterImage = null;
        characterOnTile = false;
    }
    
    public Object[] retrieveCharacter()
    {
        return (new Object[]{character, characterImage});
    }
    
    public void createPolygon(Polygon polygon)
    {
        this.polygon = polygon;
    }
    
    public void displayAttackRange(boolean enable)
    {
        if(attackRangeImage == null)
            attackRangeImage = ImageContainer.getInstance().retrieveTileEffects(ImageContainer.TileEffects.ATTACK_TILE);
        this.displayAttackRange = enable;
    }
    
   public boolean isAttackRangeDisplayed()
   {
        return displayAttackRange;
   }
    
    public void displayMovementRange(boolean enable)
    {
        if(moveRangeImage == null)
            moveRangeImage = ImageContainer.getInstance().retrieveTileEffects(ImageContainer.TileEffects.MOVE_TILE);
        this.displayMovementRange = enable;
    }
    
    public boolean isMovementRangeDisplayed()
    { 
        return displayMovementRange;
    }
    
    public void displayUnitPlacement(boolean enable)
    {
        if(placeUnitImage == null)
            placeUnitImage = ImageContainer.getInstance().retrieveTileEffects(ImageContainer.TileEffects.PLACE_UNIT);
        this.displayPlaceUnit = enable;    
    }
    
    public int getXLocation()
    {
        return xLocation;
    }
    
    public int getYLocation()
    {
        return yLocation;
    }
    
    public void loadTileImage()
    {
        try
        {
           switch(landType)
           {
                case WATER_TILE:
                    Random random = new Random();
                    if(random.nextInt(100) > 5)
                        tileImage = ImageContainer.getInstance().retrieveTileImage(ImageContainer.TileImage.WATER_TILE);
                    else
                        tileImage = ImageContainer.getInstance().retrieveTileImage(ImageContainer.TileImage.WHIRLPOOL_TILE);
                    break;
                case GRASS_TILE:
                    tileImage = ImageContainer.getInstance().retrieveTileImage(ImageContainer.TileImage.GRASS_TILE);
                    break;
                case HILL_TILE:
                    tileImage = ImageContainer.getInstance().retrieveTileImage(ImageContainer.TileImage.HILL_TILE);
                    break;
                case RIDGE_TILE:
                    tileImage = ImageContainer.getInstance().retrieveTileImage(ImageContainer.TileImage.RIDGE_TILE);
                    break;
                case MOUNTAIN_TILE:
                    tileImage = ImageContainer.getInstance().retrieveTileImage(ImageContainer.TileImage.MOUNTAIN_TILE);
                    break; 
           }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    
   @Override
   public boolean contains(int x, int y)
   {
       return polygon.contains(x,y);
   }
   
   @Override
   public void paintComponent(Graphics g)
   {
        Graphics2D g2d = (Graphics2D)g;
        
        int hexaWidth = (GameWindow.windowSize.width /gridSize)-2;
        int hexaHeight = GameWindow.windowSize.height/gridSize;
        
        hexaHeight = (int) ((GameWindow.windowSize.height + ((hexaHeight * .25))* gridSize)/gridSize);//Should account for the extra spaceing caused by indented tiles
        switch(landType)
        {
            case WATER_TILE:
                g2d.setClip(polygon);
                g.drawImage(tileImage,0,0,hexaWidth,hexaHeight, this);
                break;
            case GRASS_TILE:
                g2d.setClip(polygon);
                g.drawImage(tileImage,0,0,hexaWidth,hexaHeight, this);
                break;
            case HILL_TILE:
                g2d.setClip(polygon);
                g.drawImage(tileImage,0,0,hexaWidth,hexaHeight, this);
                break;
            case RIDGE_TILE:
                g2d.setClip(polygon);
                g.drawImage(tileImage,0,0,hexaWidth,hexaHeight, this);
                break;
            case MOUNTAIN_TILE:
                g2d.setClip(polygon);
                g.drawImage(tileImage,0,0,hexaWidth,hexaHeight, this);
                break;
        }
        
        if(characterOnTile)
        {
            g2d.setClip(polygon);
            g.drawImage(characterImage,0,0,hexaWidth,hexaHeight, this);
        }
        
        if(displayMovementRange && !characterOnTile)
        {
            g2d.setClip(polygon);
            g.drawImage(moveRangeImage,0,0,hexaWidth,hexaHeight, this);
        }
        
        if(displayAttackRange)
        {
            g2d.setClip(polygon);
            g.drawImage(attackRangeImage,0,0,hexaWidth,hexaHeight, this);
        }
        
        if(displayPlaceUnit && !characterOnTile)
        {
            g2d.setClip(polygon);
            g.drawImage(placeUnitImage,0,0,hexaWidth,hexaHeight, this);
        }
   }
   

   //Classes for the mouse listener and mouse motion listener
    public class TileMouseAdapter extends MouseAdapter
    {   
        @Override
        public void mouseClicked(MouseEvent e)
        {
            if(e.getButton() == MouseEvent.BUTTON1)
            {
                try 
                {
                    GamePlayManager manager = GamePlayManager.getInstance();
                    if(characterOnTile && (manager.getGameplayStatus() == Action.WAITING))
                    {
                        SelectionPopup popup = SelectionPopup.getInstance();
                        popup.updateCharacter(character, xLocation, yLocation);
                        popup.setLocation(e.getXOnScreen() + 20,(int)(e.getYOnScreen()- (.5*popup.getHeight())));
                        popup.setVisible(true);
                    }
                    else
                    {
                        SelectionPopup popup = SelectionPopup.getInstance();
                        popup.setVisible(false);

                        switch(manager.getGameplayStatus())
                        {
                            case MOVE:
                                manager.moveUnit(xLocation, yLocation);
                                GameApp.frame.repaint();
                                break;
                            case ATTACK:
                                manager.attackUnit(xLocation,yLocation);
                                GameApp.frame.repaint();
                                break;
                        }
                    }
                } 
                catch (InstanceNotCreatedException ex) 
                {
                    Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                if(e.getButton() == MouseEvent.BUTTON3)
                {
                    GamePlayManager manager = GamePlayManager.getInstance();
                    manager.resetGameplayStatus(character);
                    GameApp.frame.repaint();
                }
            }
        }
    }

   public class TileMouseMotionAdapter extends MouseMotionAdapter
   {

   }
   
   public class TileDropTargetAdapter extends DropTargetAdapter
   {
        @Override
        public void drop(DropTargetDropEvent dtde) 
        {

            if(dtde.isDataFlavorSupported(CharacterFlavor.instance))
            {
                Transferable transferable = dtde.getTransferable();

                try
                {
                    Object[] data = (Object[])transferable.getTransferData(CharacterFlavor.instance);
                    
                    if(data[0] instanceof DefaultClass)
                    {
                        DropTargetContext dtc = dtde.getDropTargetContext();
                        Component component = dtc.getComponent();
                        
                        if(component instanceof Tile)
                        {
                            character = (DefaultClass)data[0];
                            characterImage = (Image)data[1];
                            characterOnTile = true;
                            GameApp.frame.repaint();
                            dtde.acceptDrop(DnDConstants.ACTION_COPY);
                            dtde.dropComplete(true);
                        }
                        
                        else
                        {
                            dtde.rejectDrop();
                            dtde.dropComplete(false);
                        }
                    }
                    else
                    {
                        dtde.rejectDrop();
                        dtde.dropComplete(false);
                    }
                }
                catch(UnsupportedFlavorException | IOException ex)
                {
                    ex.printStackTrace(System.out);
                }
            
            }
        }   
   }
}
