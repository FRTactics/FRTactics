/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.classSystem;

/**
 *
 * @author Chris
 */
public class RogueClass extends DefaultClass{
    //will still need balanced
    public RogueClass(){
        className = "Rogue";
        health = 400; 
        baseHealth = 0;
        mp = 100;    
        dodgeChance = 15; 
        //meleeDamage = 50;
        //rangedDamage = 10;
        attackDamage = 50;
        spellDamage = 5;
        //rangedAttackRange = 1;
        //meleeAttackRange = 1; 
        attackDamage = 50;
        movementRange = 2;
        healthRegen = 0;  
        strength = 20; 
        agility = 35;
        armor = 10;
        intelligence = 10;
        dexterity = 40;
        vitality = 10;
        attackPerformed = false;
        movePerformed = false;
        //calcStrength();
        calcHealth();
        calcMovement();
    }

  
    @Override
    public final void calcHealth() {
        
       health = ((health * .1) + strength + health);       //increase base health based on strength
       health = (health *.2) + vitality + health; // calculating max health
       baseHealth = health;
       mp = ((mp*.2) + intelligence +mp);          //calculating your mana pool  
    }
    
    
    @Override
    public double calcChanceToDodge() {//need an if statement for the dodge ability within the combat method
       return dodgeChance = (agility * .10);
       
    }

    @Override
    public double calcSpellDamage() {
       return (intelligence * .3 + spellDamage);    //calculating you spell damage     
    }

    @Override
    public double calcAttackRange(){
         return (dexterity/7);                    //calculating theattack range of attacks
    }
    
    
    @Override
    public double calcDamageReduction(double incoming) {
        //using more variables than i need because fuck ram 
        //also for clarity        
        double outdamage = 0;               //damage after calaculation is done
        double idamage = incoming;          //incoming damage that needs to be calculated
        
        idamage = idamage -(armor * .2);    //calaculating the amount of damage taken after armor
        
        outdamage = idamage;                //setting the calculated damage to be returned
        
        return outdamage;                   //return the calculated damage       
    }
    
    @Override
    public final double calcMovement() {
        
        return ((int)(agility/5)) - ((int)(armor/10)) + movementRange; //you get moremovementbased on agility but less depending on armor
       
                
    }

    @Override
    public final void calcStrength() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double calcAttackDamage() {
         return ((attackDamage * .2) + (strength/2) + (dexterity/2) + attackDamage);    //increase melee damage based on strength
    }
    
}
