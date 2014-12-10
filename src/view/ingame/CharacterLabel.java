package view.ingame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.io.IOException;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.TransferHandler;
import model.CharacterFlavor;
import model.classSystem.DefaultClass;

public class CharacterLabel extends JLabel implements Transferable, DragSourceListener, DragGestureListener
{
    private static final DataFlavor[] flavors = {CharacterFlavor.instance};
    private DefaultClass character;
    private Image characterImage;
    private TransferHandler transfer;
    private DragSource source;
    
    
    public void setCharacter(DefaultClass character, Image characterImage)
    {
        this.character = character;
        this.characterImage = characterImage;
        
        //Create a new TransferHandler
        transfer = new TransferHandler()
        {
            @Override
            public Transferable createTransferable(JComponent c)
            {
                return CharacterLabel.this;
            }
        };
        //Set the hander to the jlabel
        setTransferHandler(transfer);
        //Create the drag source and default gesture
        source = new DragSource();
        source.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_COPY, this);
        // Set the size of the label
        
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int maxY = getIcon().getIconHeight();
        
        g.setFont(new Font("Arial",Font.PLAIN,10));
        g.drawString(buildString(),0 ,maxY-2);
        g.setColor(Color.GREEN);
        g.drawLine(0,maxY,calculateHealth(),maxY);
        g.drawLine(0,maxY+1,calculateHealth(),maxY+1);
        g.setColor(Color.RED);
        g.drawLine(calculateHealth(),maxY,getWidth(),maxY);
        g.drawLine(calculateHealth(),maxY+1,getWidth(),maxY+1);
    }
    
    public String buildString()
    {
        StringBuilder string = new StringBuilder();
        if(character.getHealth() > 0)
            string.append((int)character.getHealth());
        else
            string.append("0");
        string.append("/");
        string.append((int)character.getBaseHealth());
        return string.toString();
    }
    
    public int calculateHealth()
    {
       double baseHealth = character.getBaseHealth();
       double currentHealth = character.getHealth();
       double percentage =  currentHealth / baseHealth;
       return (int)(getWidth()*percentage);
    }
    
    @Override
    public void dragEnter(DragSourceDragEvent dsde) 
    {}

    @Override
    public void dragOver(DragSourceDragEvent dsde) 
    {}

    @Override
    public void dropActionChanged(DragSourceDragEvent dsde) 
    {}

    @Override
    public void dragExit(DragSourceEvent dse) 
    {}

    @Override
    public void dragDropEnd(DragSourceDropEvent dsde) 
    {}

    @Override
    public void dragGestureRecognized(DragGestureEvent dge) 
    {
        source.startDrag(dge,DragSource.DefaultCopyDrop,characterImage.getScaledInstance(100,100,0),new Point(0,0),this,this);   
    }
    
    @Override
    public DataFlavor[] getTransferDataFlavors() 
    {
        return (DataFlavor[])flavors.clone();
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) 
    {
        for (DataFlavor flavor1 : flavors) 
        {
            if (flavor.equals(flavor1)) 
            {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException 
    {
        if(flavor.equals(flavors[0]))
            return (Object)(new Object[]{character, characterImage});
        else
            throw new UnsupportedFlavorException(flavor);
    }
}
