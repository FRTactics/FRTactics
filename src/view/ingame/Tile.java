package view.ingame;

import exceptions.InstanceNotCreatedException;
import model.ImageContainer;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
import model.classSystem.DefaultClass;

public class Tile extends JPanel
{
    public enum LandType{
        WATER_TILE, GRASS_TILE, HILL_TILE, RIDGE_TILE, MOUNTAIN_TILE
    };
    
    private final LandType landType;
    private final int gridSize;
    private static Timer timer;
    private Polygon polygon;
    private Image tileImage;
    private Image attackRange;
    private Image moveRange;
    private Image characterImage;
    private final int xLocation;
    private final int yLocation;
    private DefaultClass character;
    private boolean characterOnTile = false;
    private boolean displayMovementRange = false;
    private boolean displayAttackRange = false;
    
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
    }
    
    /**Method returns an enum of LandType based on its current set value.
    * @return The enum value of the current land type
    */
    public LandType returnLandType()
    {
        return landType;
    }
    
    public static Timer getTimer()
    {
        return timer;
    }
    
    public void updatePreferredSize(Dimension preferredSize)
    {
        setPreferredSize(preferredSize);
    }
    
    public void updateCharacterClass(DefaultClass character)
    {
        this.character = character;
    }
    
    public void updateCharacterImage(Image characterImage)
    {
        this.characterImage = characterImage;
    }
    
    public void createPolygon(Polygon polygon)
    {
        this.polygon = polygon;
    }
    
    public void displayAttackRange(boolean enable)
    {
        if(attackRange == null)
            attackRange = ImageContainer.getInstance().retrieveTileEffects(ImageContainer.TileEffects.ATTACK_TILE);
        this.displayAttackRange = enable;
    }
    
    public void displayMovementRange(boolean enable)
    {
        if(moveRange == null)
            moveRange = ImageContainer.getInstance().retrieveTileEffects(ImageContainer.TileEffects.MOVE_TILE);
        this.displayMovementRange = enable;
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
            this.getParent().repaint();
        }
        
        if(displayMovementRange && !characterOnTile)
        {
            g2d.setClip(polygon);
            g.drawImage(moveRange,0,0,hexaWidth,hexaHeight, this);
            this.getParent().repaint();
        }
        
        if(displayAttackRange)
        {
            g2d.setClip(polygon);
            g.drawImage(attackRange,0,0,hexaWidth,hexaHeight, this);
            this.getParent().repaint();
        }
   }
   
   //Classes for the mouse listener and mouse motion listener
   public class TileMouseAdapter extends MouseAdapter
   {
       @Override
       public void mouseEntered(MouseEvent e)
       {
            timer = new Timer();
            TimerTask task = new TimerTask(){
                @Override
                public void run() 
                {
                    try
                    {
                        StatsPopup statsPopup = StatsPopup.getInstance();
                        statsPopup.updateCharacterImage(ImageContainer.CharacterImage.WARRIOR);
                        statsPopup.setLocation(e.getXOnScreen() + 20,(int)(e.getYOnScreen()- (.5*statsPopup.getHeight())));
                        statsPopup.setVisible(true);
                    }
                    catch(InstanceNotCreatedException ex)
                    {
                        System.out.println(ex);
                    }
                }
            };
            timer.schedule(task, 2500); 
       }
       
       @Override
       public void mouseExited(MouseEvent e)
       {
           try 
           {
               timer.cancel();
               timer.purge();
               StatsPopup statsPopup = StatsPopup.getInstance();
               statsPopup.setVisible(false);
           } 
           catch (InstanceNotCreatedException ex) 
           {
               System.out.println(ex);
           }
       }
       
   }

   public class TileMouseMotionAdapter extends MouseMotionAdapter
   {
       @Override
       public void mouseMoved(MouseEvent e)
       {
           try 
           {
               StatsPopup statsPopup = StatsPopup.getInstance();
               statsPopup.setLocation(e.getXOnScreen() + 20,(int)(e.getYOnScreen()- (.5*statsPopup.getHeight())));
           } 
           catch (InstanceNotCreatedException ex) 
           {
               System.out.println(ex);
           }
       }
   }
}
