package model;

public class CoordinatesHolder 
{
    private final int x;
    private final int y;
    private int range;
    
    public CoordinatesHolder(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public CoordinatesHolder(int x, int y, int range)
    {
        this.x = x;
        this.y = y;
        this.range = range;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public void setRange(int range)
    {
        this.range = range;
    }
    
    public int getRange()
    {
        return range;
    }
}
