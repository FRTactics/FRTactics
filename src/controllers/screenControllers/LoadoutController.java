/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers.screenControllers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetContext;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import model.GameManager;
import model.ImageContainer;
import view.CustomLabel;
import view.LoadoutListModel;
import view.LoadoutMenu;
import model.StringFlavor;

/**
 *
 * @author Charlie
 */
public class LoadoutController extends EventHandler{ // since both P1 and P2 Loadout 
    private LoadoutMenu loadoutMenu;
    HashMap <String, Image> imageMap;
    /**
     * Constructor for the LoadoutMenuController, adds all of the listeners to the loadoutMenu
     * @param loadoutMenu 
     */
    public LoadoutController(LoadoutMenu loadoutMenu){
        this.loadoutMenu = loadoutMenu;
        imageMap = loadoutMenu.getImageMap();
        loadoutMenu.addBackButtonListener(new BackButtonListener());
        loadoutMenu.addRemoveButtonController(new RemoveButtonListener());
        loadoutMenu.addContinueButtonListener(new ContinueButtonListener());
        loadoutMenu.setLoadoutListCellRenderer(new LoadoutListCellRenderer());
        loadoutMenu.addLoadoutListDropHandler(new DropHandler());
        addLabelListeners();
        
    }
    /**
     * Adds the Label MouseListeners to all of the choices in the loadoutMenu
     */
    private void addLabelListeners(){
        ArrayList<CustomLabel> choices = loadoutMenu.getChoiceList();
        for(int i = 0; i < choices.size(); i++){
            choices.get(i).addMouseListener(new LabelMouseListener(choices.get(i)));
        }
    }
    public class ContinueButtonListener extends MouseAdapter{
        public ContinueButtonListener(){
            
        }
        @Override
        public void mousePressed(MouseEvent e){
            if(!((LoadoutListModel)loadoutMenu.getLoadoutJList().getModel()).getSelections().isEmpty()){
                handleEvent(GameManager.CONTINUE_SELECTED);     
            }

        }
    }
    public class BackButtonListener extends MouseAdapter{
        public BackButtonListener(){
            
        }
        @Override
        public void mousePressed(MouseEvent e){
            handleEvent(GameManager.BACK_SELECTED);
        }
    }
    private class LabelMouseListener extends MouseAdapter{
       
       CustomLabel label;
       
       public LabelMouseListener(CustomLabel b){
           label = b;
       }
       
       @Override
       public void mousePressed(MouseEvent e){
           
            //for(int i = 0; i < loadoutMenu.getChoiceList().size();i++){
            if(loadoutMenu.getLastSelectedLabel() !=  null){
                loadoutMenu.getLastSelectedLabel().setForeground(Color.WHITE);
                loadoutMenu.getLastSelectedLabel().setBorder(null);
            }
            
            
            //loadoutMenu.getLastSelectedLabel().setForeground(Color.WHITE);
            //loadoutMenu.getLastSelectedLabel().setBorder(null);
               //loadoutMenu.getChoiceList().get(i).setForeground(Color.WHITE);
               //loadoutMenu.getChoiceList().get(i).setBorder(null);
            //}
            
            label.setForeground(Color.red);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            //loadoutMenu.getClassNameLabel().setText(label.getUnit().getClassName());
            //loadoutMenu.getClassNameLabel().setText("");
            //loadoutMenu.getClassNameLabel().setI
            
            switch(label.getUnit().getClassName()){
                case "Warrior":
                    loadoutMenu.getClassNameLabel().setImage(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.WARRIOR_TEXT));
                    //loadoutMenu.getClassNameLabel().setIcon(new ImageIcon(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.WARRIOR_TEXT).getScaledInstance(80, 40, 0)));
                    break;
                case "Wizard":
                    loadoutMenu.getClassNameLabel().setImage(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.WIZARD_TEXT));
                    //loadoutMenu.getClassNameLabel().setIcon(new ImageIcon(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.WIZARD_TEXT).getScaledInstance(80, 40, 0)));
                    break;
                case "Rogue":
                    loadoutMenu.getClassNameLabel().setImage(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.ROGUE_TEXT));
                    //loadoutMenu.getClassNameLabel().setIcon(new ImageIcon(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.ROGUE_TEXT).getScaledInstance(80, 40, 0)));
                    break;
                case "Healer":
                    loadoutMenu.getClassNameLabel().setImage(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.HEALER_TEXT));
                    //loadoutMenu.getClassNameLabel().setIcon(new ImageIcon(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.HEALER_TEXT).getScaledInstance(80, 40, 0)));
                    break;
                case "Archer":
                    loadoutMenu.getClassNameLabel().setImage(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.ARCHER_TEXT));
                    //loadoutMenu.getClassNameLabel().setIcon(new ImageIcon(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.ARCHER_TEXT).getScaledInstance(80, 40, 0)));
                    break;
                default:
                    return;
                    
            }
                    
            
            loadoutMenu.getMeleeAttackRangeValueLabel().setText("" + (int)label.getUnit().getHealth());
            loadoutMenu.getMovementRangeValueLabel().setText("" + (int)label.getUnit().getMovementRange());
            loadoutMenu.getMPValueLabel().setText("" +(int)label.getUnit().getMP());
            loadoutMenu.getHPValueLabel().setText("" + (int)label.getUnit().getHealth());
            loadoutMenu.getStrengthValueLabel().setText("" + (int)label.getUnit().getStrength());
            loadoutMenu.getAgilityValueLabel().setText("" + (int)label.getUnit().getAgility());
            loadoutMenu.getMeleeDamageValueLabel().setText("" + (int)label.getUnit().getMeleeDamage());
            loadoutMenu.getRangedDamageValueLabel().setText("" + (int)label.getUnit().getRangedDamage());
            loadoutMenu.getSpellDamageValueLabel().setText("" + (int)label.getUnit().getSpellDamage());
            loadoutMenu.getMeleeAttackRangeValueLabel().setText("" + (int)label.getUnit().getMeleeAttackRange());
            loadoutMenu.getRangedAttackRangeValueLabel().setText("" + (int)label.getUnit().getRangedAttackRange());
            loadoutMenu.getMovementRangeValueLabel().setText("" + label.getUnit().getMovementRange());
            loadoutMenu.getDexterityValueLabel().setText("" + (int)label.getUnit().getDexterity());
            loadoutMenu.getVitalityValueLabel().setText("" + (int)label.getUnit().getVitality());
            loadoutMenu.getIntelligenceValueLabel().setText("" + (int)label.getUnit().getIntelligence());
            loadoutMenu.getDodgeChanceValueLabel().setText("" + (int)label.getUnit().getDodgeChance());
            loadoutMenu.getHealthRegenValueLabel().setText("" + (int)label.getUnit().getHealthRegen());
            loadoutMenu.getArmorValueLabel().setText("" + (int)label.getUnit().getArmor());
            loadoutMenu.setLastSelectedLabel(label);
            loadoutMenu.repaint();
            label.updateUI();
       }  
   }
    private class RemoveButtonListener extends MouseAdapter{

        @Override
        public void mousePressed(MouseEvent e) {
            if(loadoutMenu.getLoadoutJList().getSelectedIndex() != -1 && loadoutMenu.getLoadoutJList().getModel().getSize() != 0){
                ((LoadoutListModel)(loadoutMenu.getLoadoutJList().getModel())).removeElementAt(loadoutMenu.getLoadoutJList().getSelectedIndex());
                loadoutMenu.getLoadoutJList().updateUI();
            }
        }
   }
   public class LoadoutListCellRenderer extends DefaultListCellRenderer {
       @Override
       public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
           // creates a Jlabel that is shown in the Jlist
           JLabel label = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
           label.setName((String)value);
           label.setFont(loadoutMenu.getLabelFont());
           label.setIcon(new ImageIcon(imageMap.get((String)value).getScaledInstance(100, 100, 0)));
           Color c = label.getBackground();
           
           if(isSelected){
               loadoutMenu.repaint();
               label.setBackground(new Color(0,0,0,0));
               label.setForeground(Color.RED);
               label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
           }
           else{
               label.setForeground(Color.WHITE);
               label.setBorder(null);
           }
           //label.setHorizontalTextPosition(JLabel.RIGHT);
           return label;
       }
       
   }
    public class DropHandler implements DropTargetListener{

           @Override
           public void dragEnter(DropTargetDragEvent dtde) {

               if(dtde.isDataFlavorSupported(StringFlavor.SHARED_INSTANCE)){            // upon entry of the drop target, allow the drop to happen if the 
                                                                                        // data flavor is a StringFlavor
                   dtde.acceptDrag(DnDConstants.ACTION_COPY);
               }
               else{
                   dtde.rejectDrag();
               }
           }
           @Override
           public void dragOver(DropTargetDragEvent dtde) { 
               //not needed
           }
           @Override
           public void dropActionChanged(DropTargetDragEvent dtde) {
               //not needed
           }
           @Override
           public void dragExit(DropTargetEvent dte) {
               //not needed
           }
           @Override
           public void drop(DropTargetDropEvent dtde) {
               boolean success = false;
               if(dtde.isDataFlavorSupported(StringFlavor.SHARED_INSTANCE)){                // check if the data flavor is supported
                   Transferable transferable = dtde.getTransferable();                      // if it is, create a transferable

                   try{
                       Object data = transferable.getTransferData(StringFlavor.SHARED_INSTANCE);    // get the data from the transferabke
                       if(data instanceof String){                                                  // if the data is a string
                           String string = (String)data;
                           DropTargetContext dtc = dtde.getDropTargetContext();
                           Component component = dtc.getComponent();
                           if(component instanceof JList){                                          // if the drop target is a JList, add the string to the list
                               success = true;
                               JList list = (JList)component;
                               LoadoutListModel listModel = (LoadoutListModel)(((JList)component).getModel());
                               if(listModel.getSize() < 5){
                                   listModel.addElement(string);
                               }

                               //list.setModel(listModel);
                               
                               dtde.acceptDrop(DnDConstants.ACTION_COPY);
                               list.invalidate();
                               list.updateUI();
                               list.repaint();
                               list.getParent().repaint();

                           }
                           else{
                               success = false;
                               dtde.rejectDrop();
                           }
                       }
                       else{
                           success = false;
                           dtde.rejectDrop();
                       }
                   }
                   catch(Exception ex){
                       ex.printStackTrace();
                   }
                   dtde.dropComplete(success);
               }
           }
           

       }


}
