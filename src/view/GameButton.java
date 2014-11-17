/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author cmb6094
 */
public class GameButton extends javax.swing.JPanel {

    Image image;
    int xPos;
    int maxPos;
    public GameButton(File imageFile)
    {
        //initComponents();
        
        try{
            addImage(imageFile);
        }
        catch(IOException e){
            System.err.println(e.getLocalizedMessage());
        }
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(400,100));
        this.setMinimumSize(new Dimension(400,100));
        
      
        this.setDoubleBuffered(true);
        maxPos = (int) (this.getPreferredSize().width*.4);
        xPos = maxPos;
        this.setVisible(true);
    }
    public void addImage(File f) throws IOException{
           image = ImageIO.read(f);
       
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image,xPos,0,null);
        
    }
    
    public void animateLeft(){
       
        xPos = 0;
        repaint();
    }
    public void animateRight(){
        xPos = maxPos;
        repaint();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
