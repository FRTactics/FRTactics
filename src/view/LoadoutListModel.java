/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.util.ArrayList;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Charlie
 */
public class LoadoutListModel implements ListModel{     // a custom list model that allows for the addition of strings
    ArrayList<String> selections;                       // made this because I didn't want to use vectors

    public LoadoutListModel() {
        this.selections = new ArrayList<String>();
    }
    @Override
    public int getSize() {              
        return selections.size();
    }
    @Override
    public Object getElementAt(int index) {
        return selections.get(index);
    }
    public void addElement(String element){
        selections.add(element);
    }
    public void removeElementAt(int index){
        selections.remove(index);
    }
    public void clear(){
        selections.clear();
    }
    public ArrayList<String> getSelections(){
        return selections;
    }
    @Override
    public void addListDataListener(ListDataListener l) {
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
    }

   
    
}
