package model;

import view.ingame.Tile;
import java.util.ArrayDeque;
import java.util.ArrayList;
import view.ingame.DrawPanel;

public class RangeChecker 
{
    private final int [][] neighborsEven = {{-1,-1},{0,-1},{+1,0},{0,+1},{-1,+1},{-1,0}};//{{-1,-1}, {0,-1}, {+1, 0}, {0, +1}, {-1, +1}, {-1, 0}};
    private final int [][] neighborsOdd ={{0,-1},{+1,-1},{+1,0},{+1,+1},{0,+1},{-1,0}}; //{{+1,+1},{+1,0},{0,-1},{-1,0},{+1,-1},{0,+1}};
    private final Tile grid[][];
    
    public RangeChecker()
    {
        this.grid = DrawPanel.getGrid();
    }
    
    public void calculateRange(int xstart,int ystart, int range, int rangeType)
    {        
       ArrayList start = new ArrayList();
       ArrayDeque nodes = new ArrayDeque();
       ArrayList finish = new ArrayList();
       
       //Populate the start array
       for(int i = 0; i < grid.length; ++i)
       {
           for(int j = 0; j < grid.length; ++j)
           {
               start.add(new CoordinatesHolder(j,i));
           }
       }
       //Get the size of the start array
       int size = start.size();
       //Store the start position in the node
       nodes.addFirst(new CoordinatesHolder(xstart,ystart, range));
       //start the search
       while((finish.size()) != size)
       {
           //Get the node from the top of the array deque
           CoordinatesHolder node = (CoordinatesHolder)nodes.getFirst();
           
           if(node.getY() % 2 == 0) //Even row
           {
               //Navigate through the neighboring tiles
               for(int i = 0; i < neighborsEven.length; ++i)
               {
                   //get the neighboring tiles x and y coordinate
                    int x = node.getX() + neighborsEven[i][0];
                    int y = node.getY() + neighborsEven[i][1];
                    
                    //search for the tile in the start array
                    for(int j = 0; j < start.size(); ++j)
                    {
                        //check to see if the tile is the same
                        if(((CoordinatesHolder)(start.get(j))).getX() == x && ((CoordinatesHolder)(start.get(j))).getY() == y)
                        {
                            //make sure the range is greater than zero
                            if(node.getRange() > 0)
                            {
                                
                                    nodes.add(start.get(j)); //add the found tile to the array deque
                                    ((CoordinatesHolder)(start.get(j))).setRange(node.getRange() + holyFuckingCaseStatementsBatman(node,x,y));// calculate the new movement range
                                    start.remove(j);// remove the node from the start array
                            }
                        }
                    }  
               }
            }
            else //Odd row
            {
                //Navigate through the neighboring tiles
                for(int i = 0; i < neighborsOdd.length; ++i)
                {
                    //get the neighboring tiles x and y coordinate
                    int x = node.getX() + neighborsOdd[i][0];
                    int y = node.getY() + neighborsOdd[i][1];
                    
                    //search for the tile in the start array
                    for(int j = 0; j < start.size(); ++j)
                    {
                        //check to see if the tile is the same
                        if(((CoordinatesHolder)(start.get(j))).getX() == x && ((CoordinatesHolder)(start.get(j))).getY() == y)
                        {
                            //make sure the range is greater than zero
                            if(node.getRange() > 0)
                            {
                               
                                nodes.add(start.get(j)); //add the found tile to the array deque
                                ((CoordinatesHolder)(start.get(j))).setRange(node.getRange() + holyFuckingCaseStatementsBatman(node,x,y));// calculate the new movement range
                                start.remove(j);// remove the node from the start array
                            }
                        }
                    }  
                }
            }
           
            finish.add(nodes.getFirst());
            nodes.removeFirst();

            if(nodes.isEmpty())
            {
                break;
            }
        }
      
        for (Object finish1 : finish) 
        {
            CoordinatesHolder node = (CoordinatesHolder) finish1;
            switch(rangeType)
            {
                case 0://move
                    grid[node.getX()][node.getY()].displayMovementRange(true);
                    break;
                case 1://attack
                    grid[node.getX()][node.getY()].displayAttackRange(true);
                    break;
                case 2://remove displayed range
                    grid[node.getX()][node.getY()].displayAttackRange(false);
                    grid[node.getX()][node.getY()].displayMovementRange(false);
                    break;
            }
        }
    }

    private int holyFuckingCaseStatementsBatman(CoordinatesHolder node,int x, int y)
    {
        switch(grid[x][y].returnLandType())
        {
            case WATER_TILE:
                return -100;
            case GRASS_TILE:
                return -1;
            case HILL_TILE:
                switch(grid[node.getX()][node.getY()].returnLandType())
                {
                    case GRASS_TILE:
                        return -2;
                    case HILL_TILE:
                        return -1;
                    case RIDGE_TILE:
                        return -1;
                    case MOUNTAIN_TILE:
                        return -1;
                }
            case RIDGE_TILE:
                switch(grid[node.getX()][node.getY()].returnLandType())
                {
                    case GRASS_TILE:
                        return -3;
                    case HILL_TILE:
                        return -2;
                    case RIDGE_TILE:
                        return -1;
                    case MOUNTAIN_TILE:
                        return -1;
                }
            case MOUNTAIN_TILE:
                switch(grid[node.getX()][node.getY()].returnLandType())
                {
                    case GRASS_TILE:
                        return -4;
                    case HILL_TILE:
                        return -3;
                    case RIDGE_TILE:
                        return -2;
                    case MOUNTAIN_TILE:
                        return -1;
                }
        }
        return 0;
    }
}
