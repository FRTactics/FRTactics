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
public class WarriorClass extends DefaultClass{
    //needs to have base stats properly balanced
    public WarriorClass(){
        className = "Warrior";
        health = 500;
        mp = 100; 
        dodgeChance = 0;
        attackDamage = 50;
//        meleeDamage = 50;
//        rangedDamage = 10; 
        spellDamage = 5; 
        //rangedAttackRange = 3;
        //meleeAttackRange = 1; 
        movementRange = 2; 
        healthRegen = 0;
        strength = 20;
        agility = 20; 
        armor = 20;
        intelligence = 20;
        dexterity = 20; 
        vitality = 20; 
        hasAttacked = false;
        hasMoved = false;
        //calcStrength();
        calcHealth();
        calcMovement();
        
    }

    @Override
    public void calcHealth() 
    {    
       health = ((health * .1) + strength + health);       //increase base health based on strength
       health = (health *.2) + vitality + health;          // calculating max health
       mp = ((mp*.2) + intelligence + mp);                  //calculating your mana pool  
    }
    
    
    @Override
    public double calcChanceToDodge() {//need an if statement for the dodge ability within the combat method
       return (agility * .10);
       
    }

    @Override
    public double calcSpellDamage() {
       return  (intelligence * .3 + spellDamage);    //calculating you spell damage
       
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
        return ((int)(agility/5)) - ((int)(armor/10)) + movementRange; //you get moremovementbased on agility but less depending on armor
    }

    @Override
    public void calcStrength() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double calcAttackRange() {
        attackRange = (dexterity/10);                    //calculating theattack range of attacks
        return attackRange;
    }

    @Override
    public double calcAttackDamage() {
        attackDamage = ((attackDamage * .2) + strength + attackDamage);    //increase melee damage based on strength
        return attackDamage;
    }
}
