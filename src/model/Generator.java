package model;


import view.ingame.GameWindow;
import view.ingame.Tile;
import java.awt.Dimension;
import java.awt.Polygon;
import java.util.Random;

public class Generator
{
    private final Tile grid[][];
    private final int baseWater = 15;
    private final int baseLandZero = 35;
    private final int baseLandOne = 25;
    private final int baseLandTwo = 15;
    //private final int baseLandThree = 10;
    
    public Generator(Tile grid [][])
    {
        this.grid = grid;
    }
    
    public void start(int x,int y)
    {
        calculateTile(x,y);
        createPolygon(x, y);
        loadTileImage(x,y);
        moveUp(x,y,0,1);
    }
    
    private void moveUp(int x,int y, int count, int limit)
    {
        if(count < limit && (x >= 0 &&  x < grid.length && y >= 0 && y < grid.length))
        {
            calculateTile(x,y);
            createPolygon(x, y);
            loadTileImage(x,y);
            moveUp(x,y-1, count + 1, limit);
        }
        else if(count == limit && (x >= 0 &&  x < grid.length && y >= 0 && y < grid.length))
        {
            calculateTile(x,y);
            createPolygon(x, y);
            loadTileImage(x,y);
            moveRight(x+1, y, 1, limit);
        }
    }
    
    private void moveRight(int x,int y, int count, int limit)
    {
        if(count < limit && (x >= 0 &&  x < grid.length && y >= 0 && y < grid.length))
        {
            calculateTile(x,y);
            createPolygon(x, y);
            loadTileImage(x,y);
            moveRight(x+1,y, count + 1, limit);
        }
        else if(count == limit && (x >= 0 &&  x < grid.length && y >= 0 && y < grid.length))
        {
            calculateTile(x,y);
            createPolygon(x, y);
            loadTileImage(x,y);
            moveDown(x, y+1, 1, limit + 1);
        }
    }
    
    private void moveDown(int x,int y, int count, int limit)
    {
        if(count < limit && (x >= 0 &&  x < grid.length && y >= 0 && y < grid.length))
        {
            calculateTile(x,y);
            createPolygon(x, y);
            loadTileImage(x,y);
            moveDown(x,y+1, count + 1, limit);
        }
        else if(count == limit && (x >= 0 &&  x < grid.length && y >= 0 && y < grid.length))
        {
            calculateTile(x,y);
            createPolygon(x, y);
            loadTileImage(x,y);
            moveLeft(x-1, y, 1, limit);
        }
    }
    
    private void moveLeft(int x,int y, int count, int limit)
    {
        if(count < limit && (x >= 0 &&  x < grid.length && y >= 0 && y < grid.length))
        {
            calculateTile(x,y);
            createPolygon(x, y);
            loadTileImage(x,y);
            moveLeft(x-1,y, count + 1, limit);
        }
        else if(count == limit && (x >= 0 &&  x < grid.length && y >= 0 && y < grid.length))
        {
            calculateTile(x,y);
            createPolygon(x, y);
            loadTileImage(x,y);
            moveUp(x, y-1, 1, limit + 1);
        }
    }
    
    private void calculateTile(int x, int y)
    {
        Random rand = new Random();       
        int value = rand.nextInt(100);
        
        int tileCount [] = {0,0,0,0,0};
        checkTiles(x,y,tileCount);
        
        int w = baseWater + 4*tileCount[0] - 1*tileCount[1] - 1*tileCount[2] - 1*tileCount[3] - 1*tileCount[4];
        int l0 = baseLandZero + 4*tileCount[1] - 1*tileCount[0] - 1*tileCount[2] - 1*tileCount[3] - 1*tileCount[4];
        int l1 = baseLandOne + 4*tileCount[2] - 1*tileCount[1] - 1*tileCount[0] - 1*tileCount[3] - 1*tileCount[4];
        int l2 = baseLandTwo + 4*tileCount[3] - 1*tileCount[1] - 1*tileCount[2] - 1*tileCount[0] - 1*tileCount[4];
        //int l3 = baseLandThree + 4*tileCount[4] - 1*tileCount[1] - 1*tileCount[2] - 1*tileCount[3] - 1*tileCount[0];
        
        
        
        if(value >= 0 && value <= w)
        {
            grid[x][y] = new Tile(Tile.LandType.WATER_TILE,grid.length,x,y);
        }
        else
        {
            if(value > w && value <= w + l0)
            {
                grid[x][y] = new Tile(Tile.LandType.GRASS_TILE,grid.length,x,y);
            }
            else
            {
                if(value > w + l0 && value <= w + l0 + l1)
                {
                    grid[x][y] = new Tile(Tile.LandType.HILL_TILE,grid.length,x,y);
                }
                else
                {
                    if(value > w + l0 + l1 && value <= w + l0 + l1 + l2)
                    {
                        grid[x][y] = new Tile(Tile.LandType.RIDGE_TILE,grid.length,x,y);
                    }
                    else
                    {
                        grid[x][y] = new Tile(Tile.LandType.MOUNTAIN_TILE,grid.length,x,y);
                    }
                }
            }
        }
    }
    
    private void checkTiles(int x, int y, int tileCount[])
    {
        if(x - 1 >= 0 && y- 1 >= 0)
        {
            if(grid[x-1][y-1] != null)
                getLandType(x-1,y-1,tileCount);
        }
        if(x - 1 >= 0)
        {
            if(grid[x-1][y] != null)
                getLandType(x-1,y,tileCount);
        }
        if(x - 1 >= 0 && y + 1 < grid.length)
        {
            if(grid[x-1][y+1] != null)
                getLandType(x-1,y+1,tileCount);
        }
        if(y - 1 >= 0)
        {
            if(grid[x][y-1] != null)
                getLandType(x,y-1,tileCount);
        }
        if(y + 1 < grid.length)
        {
            if(grid[x][y+1] != null)
                getLandType(x,y+1,tileCount);
        }
        if(x + 1 < grid.length && y - 1 >= 0)
        {
            if(grid[x+1][y-1] != null)
                getLandType(x+1,y-1,tileCount);
        }
        if(x + 1 < grid.length)
        {
            if(grid[x+1][y] != null)
                getLandType(x+1,y,tileCount);
        }
        if(x + 1 < grid.length && y + 1 < grid.length)
        {
            if(grid[x+1][y+1] != null)
                getLandType(x+1,y+1,tileCount);
        }
    }
    
    private void getLandType(int x, int y, int tileCount[])
    {
        
        switch(grid[x][y].returnLandType())
        {
            case WATER_TILE:
                tileCount[0]++;
                break;
            case GRASS_TILE:
                tileCount[1]++;
                break;
            case HILL_TILE:
                tileCount[2]++;
                break;
            case RIDGE_TILE:
                tileCount[3]++;
                break;
            case MOUNTAIN_TILE:
                tileCount[4]++;
                break;
        }
    }
    
    private void loadTileImage(int x, int y)
    {
        grid[x][y].loadTileImage();
    }
    
    private void createPolygon(int x, int y)
    {
        Polygon polygon = new Polygon();
        int hexaWidth = GameWindow.windowSize.width /grid.length;
        int hexaHeight = GameWindow.windowSize.height/grid.length;
        hexaWidth = (GameWindow.windowSize.width - hexaWidth/2) /grid.length;//creates extra spacing on the sides to account for indented tiles
        hexaHeight = (int) ((GameWindow.windowSize.height + ((hexaHeight * .25))* grid.length)/grid.length);//Should account for the extra spaceing caused by indented tiles
        
        polygon.npoints = 6;
        polygon.xpoints = new int []{0,(int)(.5*hexaWidth),(int)(hexaWidth),(int)(hexaWidth),(int)(.5*hexaWidth),0};
        polygon.ypoints = new int []{(int)(.25 * hexaHeight),0,(int)(.25 * hexaHeight),(int)(.75 * hexaHeight),(int)(hexaHeight),(int)(.75 * hexaHeight)};
        grid[x][y].createPolygon(polygon);
        grid[x][y].updatePreferredSize(new Dimension(hexaWidth,hexaHeight));
    }
}
