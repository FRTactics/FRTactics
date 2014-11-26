/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.awt.datatransfer.DataFlavor;

/**
 *
 * @author Charlie
 */
public class StringFlavor extends DataFlavor{
       public static final StringFlavor SHARED_INSTANCE = new StringFlavor();
       private StringFlavor(){
           super(String.class, null);
       }
   }
