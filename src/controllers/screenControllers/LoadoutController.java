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
    public void addLabelListeners(){
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
            for(int i = 0; i < loadoutMenu.getChoiceList().size();i++){
               loadoutMenu.getChoiceList().get(i).setForeground(Color.WHITE);
               loadoutMenu.getChoiceList().get(i).setBorder(null);
            }
            label.setForeground(Color.red);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            //loadoutMenu.getClassNameLabel().setText(label.getUnit().getClassName());
            loadoutMenu.getClassNameLabel().setText("");
            switch(label.getUnit().getClassName()){
                case "Warrior":
                    loadoutMenu.getClassNameLabel().setIcon(new ImageIcon(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.WARRIOR_TEXT).getScaledInstance(80, 40, 0)));
                    break;
                case "Wizard":
                    loadoutMenu.getClassNameLabel().setIcon(new ImageIcon(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.WIZARD_TEXT).getScaledInstance(80, 40, 0)));
                    break;
                case "Rogue":
                    loadoutMenu.getClassNameLabel().setIcon(new ImageIcon(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.ROGUE_TEXT).getScaledInstance(80, 40, 0)));
                    break;
                case "Healer":
                    loadoutMenu.getClassNameLabel().setIcon(new ImageIcon(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.HEALER_TEXT).getScaledInstance(80, 40, 0)));
                    break;
                case "Archer":
                    loadoutMenu.getClassNameLabel().setIcon(new ImageIcon(ImageContainer.getInstance().retrieveStatusLabelImages(ImageContainer.StatusLabelImage.ARCHER_TEXT).getScaledInstance(80, 40, 0)));
                    break;
                default:
                    return;
                    
            }
            
            loadoutMenu.getMeleeAttackRangeValueLabel().setText("" + (int)label.getUnit().getBaseHealth());
            loadoutMenu.getMovementRangeValueLabel().setText("" + (int)label.getUnit().getBaseMovementRange());
            loadoutMenu.getMPValueLabel().setText("" +(int)label.getUnit().getBaseMP());
            loadoutMenu.getHPValueLabel().setText("" + (int)label.getUnit().getBaseHealth());
            loadoutMenu.getStrengthValueLabel().setText("" + (int)label.getUnit().getBaseStrength());
            loadoutMenu.getAgilityValueLabel().setText("" + (int)label.getUnit().getBaseAgility());
            loadoutMenu.getMeleeDamageValueLabel().setText("" + (int)label.getUnit().getBaseMeleeDamage());
            loadoutMenu.getRangedDamageValueLabel().setText("" + (int)label.getUnit().getBaseRangedDamage());
            loadoutMenu.getSpellDamageValueLabel().setText("" + (int)label.getUnit().getBaseSpellDamage());
            loadoutMenu.getMeleeAttackRangeValueLabel().setText("" + (int)label.getUnit().getBaseMeleeAttackRange());
            loadoutMenu.getRangedAttackRangeValueLabel().setText("" + (int)label.getUnit().getBaseRangedAttackRange());
            loadoutMenu.getMovementRangeValueLabel().setText("" + label.getUnit().getBaseMovementRange());
            loadoutMenu.getDexterityValueLabel().setText("" + (int)label.getUnit().getBaseDexterity());
            loadoutMenu.getVitalityValueLabel().setText("" + (int)label.getUnit().getBaseVitality());
            loadoutMenu.getIntelligenceValueLabel().setText("" + (int)label.getUnit().getBaseIntelligence());
            loadoutMenu.getDodgeChanceValueLabel().setText("" + (int)label.getUnit().getBaseDodgeChance());
            loadoutMenu.getHealthRegenValueLabel().setText("" + (int)label.getUnit().getBaseHealthRegen());
            loadoutMenu.getArmorValueLabel().setText("" + (int)label.getUnit().getBaseArmor());
            
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
           
           JLabel label = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
           //CustomLabel label = new CustomLabel((String)value, JLabel.CENTER, imageMap.get((String)value));
           //label.setIcon(new ImageIcon(imageMap.get((String) value).getScaledInstance(75, 75, 0)));
           label.setName((String)value);
           label.setFont(loadoutMenu.getLabelFont());
           label.setIcon(new ImageIcon(imageMap.get((String)value).getScaledInstance(100, 100, 0)));
           Color c = label.getBackground();
           
           if(isSelected){
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

               if(dtde.isDataFlavorSupported(StringFlavor.SHARED_INSTANCE)){

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
               if(dtde.isDataFlavorSupported(StringFlavor.SHARED_INSTANCE)){
                   Transferable transferable = dtde.getTransferable();

                   try{
                       Object data = transferable.getTransferData(StringFlavor.SHARED_INSTANCE);
                       if(data instanceof String){
                           String string = (String)data;
                           DropTargetContext dtc = dtde.getDropTargetContext();
                           Component component = dtc.getComponent();
                           if(component instanceof JList){
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
