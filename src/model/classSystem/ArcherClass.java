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
public class ArcherClass extends DefaultClass{
    //needs to have base stats properly balanced

    public ArcherClass(){
        className = "Archer";
        health = 300;
        baseHealth = 0;
        mp = 150;    
        dodgeChance = 0; 
        attackDamage = 70;
        spellDamage = 20; 
        movementRange = 5;
        healthRegen = 0; 
        strength = 15;
        agility = 35;
        armor = 10;   
        intelligence = 10; 
        dexterity = 30;
        vitality = 10; 
        attackPerformed = false;
        movePerformed = false;
        //calcStrength();
        calcHealth();
        calcMovement();
        
    }

    @Override
    public void calcHealth() {
        
       health = ((health * .1) + strength + health);       //increase base health based on strength
       health = (health *.2) + vitality + health; // calculating max health
       baseHealth= health;
       mp = ((mp*.2) + intelligence +mp);          //calculating your mana pool  
    }
    
    
    @Override
    public double calcChanceToDodge() {//need an if statement for the dodge ability within the combat method
       dodgeChance = (agility * .10);
       return dodgeChance;
    }

    @Override
    public double calcSpellDamage() {
 
       return (intelligence * .3 + spellDamage);    //calculating you spell damage
    }

//    @Override
//    public double calcRangedDamage() {
//        rangedDamage = (rangedDamage*.2) + dexterity + rangedDamage;   //calcuating the range damage
//        return rangedDamage;
//    }


    @Override
    public double calcAttackDamage() {
        
        return ((attackDamage * .2) + dexterity + attackDamage);    //increase melee damage based on strength
    }

    @Override
    public double calcAttackRange() {
        attackRange = (dexterity/5);                    //calculating theattack range of attacks
        return attackRange;
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
    public double calcMovement() {
        
        
        return movementRange = ((int)(agility/5)) - ((int)(armor/10)) + movementRange; //you get moremovementbased on agility but less depending on armor
                
    }

    @Override
    public void calcStrength() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
