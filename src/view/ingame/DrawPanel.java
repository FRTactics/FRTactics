package view.ingame;


import model.Generator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Polygon;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class DrawPanel extends JPanel 
{
    public final static Dimension zoomModifier = new Dimension(200,100);
    private static Tile grid[][];
    private final SpringLayout layout = new SpringLayout();
    private final int arraySize;
        
    public DrawPanel(int arraySize)
    {
        this.setBackground(Color.BLACK);
        this.setMinimumSize(new Dimension(800,600));
        this.setPreferredSize(new Dimension(800,600));
        this.setMaximumSize(new Dimension(2000,1600));
        this.setVisible(true);
        this.setLayout(layout);
        grid = new Tile[arraySize][arraySize];
        this.arraySize = arraySize;
        Generator gen = new Generator(grid);
        gen.start((arraySize-1)/2,(arraySize- 1)/2);
    }
    
    public static Tile[][] getGrid()
    {
        return grid;
    }
    
    public void updateSpringLayout()
    {
        boolean firstHexagonInRow = false;
        int hexaWidth = (GameWindow.windowSize.width )/grid.length;
        int hexaHeight = (GameWindow.windowSize.height)/grid.length;
        
        hexaWidth = ((GameWindow.windowSize.width - hexaWidth) /grid.length);//To create padding around the edges
        hexaHeight = (int) ((GameWindow.windowSize.height + ((hexaHeight * .25))* grid.length)/grid.length);//Should account for the extra spaceing caused by indented tiles
        hexaHeight = (int) (hexaHeight - (hexaHeight * .25)); // To make the tiles fit into each other
        
        for(int i = 0; i < arraySize; ++i)
        {
            if(i%2 ==0)// If row is even or odd, this one is even
            {
                 for(int j = 0; j < arraySize; ++j)
                 {
                    if(!firstHexagonInRow)
                    {
                        layout.putConstraint(SpringLayout.WEST,grid[j][i],0,SpringLayout.WEST,this);//X
                        layout.putConstraint(SpringLayout.NORTH,grid[j][i],hexaHeight * i,SpringLayout.NORTH,this);//Y
                        firstHexagonInRow = true;
                    }
                    else
                    {
                        layout.putConstraint(SpringLayout.WEST,grid[j][i],hexaWidth,SpringLayout.WEST,grid[j-1][i]);//X
                        layout.putConstraint(SpringLayout.NORTH,grid[j][i],hexaHeight * i,SpringLayout.NORTH,this);//Y
                    }
                 }

                 firstHexagonInRow = false;
            }
            else//Odd
            {
                for(int j = 0; j < arraySize; ++j)
                {
                   if(!firstHexagonInRow)
                   {
                       layout.putConstraint(SpringLayout.WEST,grid[j][i],hexaWidth/2,SpringLayout.WEST,this);//X
                       layout.putConstraint(SpringLayout.NORTH,grid[j][i],(hexaHeight * i),SpringLayout.NORTH,this);//Y
                       firstHexagonInRow = true;
                   }
                   else
                   {
                       layout.putConstraint(SpringLayout.WEST,grid[j][i],hexaWidth,SpringLayout.WEST,grid[j-1][i]);//X
                       layout.putConstraint(SpringLayout.NORTH,grid[j][i],hexaHeight * i,SpringLayout.NORTH,this);//Y
                   }  
                }
                firstHexagonInRow = false;
            }
        } 
        this.updateUI();
    }
    
    public boolean preformZoom(boolean increasingZoom)
    {
        if(increasingZoom)
        {
            Dimension zoomWindow = new Dimension();
            zoomWindow.width = this.getPreferredSize().width + zoomModifier.width;
            zoomWindow.height = this.getPreferredSize().height + zoomModifier.height;
            if(zoomWindow.width <= this.getMaximumSize().width)
                this.setPreferredSize(zoomWindow); 
            else
                return false;
        }
        else
        {
            Dimension zoomWindow = new Dimension();
            zoomWindow.width = this.getPreferredSize().width - zoomModifier.width;
            zoomWindow.height = this.getPreferredSize().height - zoomModifier.height;
            if(zoomWindow.width >= this.getMinimumSize().width)
                this.setPreferredSize(zoomWindow);
            else
                return false;
        }
        
        GameWindow.windowSize = this.getPreferredSize();
        
        for(int i = 0; i < grid.length; ++i)
        {
            for(int j =0; j < grid.length; ++j)
            {
                Polygon polygon = new Polygon();
                int hexaWidth = GameWindow.windowSize.width /grid.length;
                int hexaHeight = GameWindow.windowSize.height/grid.length;

                hexaWidth = (GameWindow.windowSize.width - hexaWidth/2) /grid.length;
                hexaHeight = (int) ((GameWindow.windowSize.height + ((hexaHeight * .25))* grid.length)/grid.length);
                
                polygon.npoints = 6;
                polygon.xpoints = new int []{0,(int)(.5*hexaWidth),(int)(hexaWidth),(int)(hexaWidth),(int)(.5*hexaWidth),0};
                polygon.ypoints = new int []{(int)(.25 * hexaHeight),0,(int)(.25 * hexaHeight),(int)(.75 * hexaHeight),(int)(hexaHeight),(int)(.75 * hexaHeight)};
                grid[i][j].createPolygon(polygon);
                grid[i][j].updatePreferredSize(new Dimension(hexaWidth,hexaHeight));
            }
        }
        return true;
    }
    
    public void placeHexagons()
    {
        boolean firstHexagonInRow = false;
        int hexaWidth = (GameWindow.windowSize.width )/grid.length;//Creates base width for the tile
        int hexaHeight = (GameWindow.windowSize.height)/grid.length;//Creates base height for the tile
        
        hexaWidth = (GameWindow.windowSize.width - hexaWidth) /grid.length;//To create padding around the edges
        hexaHeight = (int) ((GameWindow.windowSize.height + ((hexaHeight * .25))* grid.length)/grid.length);//Adds extra height to the tile due to spacing caused by indented tiles
        
        hexaHeight = (int) (hexaHeight - (hexaHeight * .25)-1); // Adjust the height varibale by a 1/4 to indent the tile
        
        for(int i = 0; i < arraySize; ++i)
        {
            if(i%2 ==0)// If row is even or odd, this one is even
            {
                 for(int j = 0; j < arraySize; ++j)
                 {
                    if(!firstHexagonInRow)
                    {
                        layout.putConstraint(SpringLayout.WEST,grid[j][i],0,SpringLayout.WEST,this);//X
                        layout.putConstraint(SpringLayout.NORTH,grid[j][i],hexaHeight * i,SpringLayout.NORTH,this);//Y
                        this.add(grid[j][i]);
                        firstHexagonInRow = true;
                    }
                    else
                    {
                        layout.putConstraint(SpringLayout.WEST,grid[j][i],hexaWidth,SpringLayout.WEST,grid[j-1][i]);//X
                        layout.putConstraint(SpringLayout.NORTH,grid[j][i],hexaHeight * i,SpringLayout.NORTH,this);//Y
                        this.add(grid[j][i]);
                    }
                 }

                 firstHexagonInRow = false;
            }
            else//Odd
            {
                for(int j = 0; j < arraySize; ++j)
                {
                   if(!firstHexagonInRow)
                   {
                       layout.putConstraint(SpringLayout.WEST,grid[j][i],hexaWidth/2,SpringLayout.WEST,this);//X
                       layout.putConstraint(SpringLayout.NORTH,grid[j][i],(hexaHeight * i),SpringLayout.NORTH,this);//Y
                       this.add(grid[j][i]);
                       firstHexagonInRow = true;
                   }
                   else
                   {
                       layout.putConstraint(SpringLayout.WEST,grid[j][i],hexaWidth,SpringLayout.WEST,grid[j-1][i]);//X
                       layout.putConstraint(SpringLayout.NORTH,grid[j][i],hexaHeight * i,SpringLayout.NORTH,this);//Y
                       this.add(grid[j][i]);
                   }  
                }
                firstHexagonInRow = false;
            }
        }   
   }        
}
