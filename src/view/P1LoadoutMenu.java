/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import model.ImageContainer;
/**
 *
 * @author Charlie
 */
public class P1LoadoutMenu extends JPanel {             // still a work in progress, looks like a piece of shit now - Charlie
    private TableModel classTableModel;
    private JPanel loadoutTable;
    private JPanel buttonPanel; // placed on east side
    private JPanel centerPanel;  
    private JPanel topBuffer;    // just a buffer placed on the top screen
    private JPanel statsPanel; // panel to hold all of the stats for each unit
    private final JTextField statsField = new JTextField("Stats:");
    GameButton continueButton;
    public P1LoadoutMenu() {
        this.setLayout(new BorderLayout());
       
        this.setVisible(true);
    }
   
}