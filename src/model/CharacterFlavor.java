/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.datatransfer.DataFlavor;
import model.classSystem.DefaultClass;

/**
 *
 * @author Arin
 */
public class CharacterFlavor extends DataFlavor
{
    public static final CharacterFlavor instance = new CharacterFlavor();
    
    private CharacterFlavor()
    {
        super(DefaultClass.class,"Character Flavor");
    }
}
