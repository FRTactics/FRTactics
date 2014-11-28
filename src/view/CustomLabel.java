/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceContext;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DragSourceMotionListener;
import java.awt.dnd.InvalidDnDOperationException;
import java.awt.dnd.MouseDragGestureRecognizer;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import model.ImageContainer;
import model.classSystem.DefaultClass;

/**
 *
 * @author Charlie
 */
public class CustomLabel extends JLabel implements DragGestureListener, DragSourceListener {
    private DragSource ds = DragSource.getDefaultDragSource();
    private Image image;
    private DefaultClass unit;
    public CustomLabel(String s, int alignment, Image image, DefaultClass unit) { // add unit to the constructor later so we can use this class to
        super(s, alignment);                                   // modify the status panel of the l
        this.unit = unit;
        this.image = image;
        this.setForeground(Color.WHITE);
        //this.setOpaque(false);
        //this.setBackground(new Color(0,0,0,0));
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setVerticalTextPosition(JLabel.BOTTOM);
        
        setIcon(new ImageIcon(this.image.getScaledInstance(200, 200, 0)));
        int action = DnDConstants.ACTION_COPY_OR_MOVE;
        ds.createDefaultDragGestureRecognizer(this, action, this);
    }
    public DefaultClass getUnit(){
        return unit;
    }
    public Image getImage(){
        return image;
    }
    
    @Override
    public void dragGestureRecognized(DragGestureEvent e) {
        try {
            Transferable t = new StringSelection(getText());

            try{
                e.startDrag(DragSource.DefaultCopyNoDrop, image.getScaledInstance(100, 100, 0),new Point(0,0), t, this);
            }
            catch(Exception ex){
                 ex.printStackTrace();
            }
        }   
        catch (InvalidDnDOperationException e2) {
            System.out.println(e2);
        }
    }

    @Override
    public void dragDropEnd(DragSourceDropEvent e) {

        if (e.getDropSuccess() == false) {
            return;
        }

        int action = e.getDropAction();
        if ((action & DnDConstants.ACTION_MOVE) != 0)
            setText("");
  }

    @Override
    public void dragEnter(DragSourceDragEvent e) {

        DragSourceContext ctx = e.getDragSourceContext();

        int action = e.getDropAction();
        /*
        if ((action & DnDConstants.ACTION_COPY) != 0)
            ctx.setCursor(DragSource.DefaultCopyNoDrop);
        else
        */
        ctx.setCursor(DragSource.DefaultCopyDrop);
  }

    @Override
    public void dragExit(DragSourceEvent e) {
        DragSourceContext ctx = e.getDragSourceContext();
        ctx.setCursor(DragSource.DefaultCopyNoDrop);
    }

    @Override
    public void dragOver(DragSourceDragEvent e) {
        // do nothing
    }

    @Override
    public void dropActionChanged(DragSourceDragEvent e) {
        // do nothing
    }
}

