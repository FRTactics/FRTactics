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
public abstract class DefaultClass {        // abstract class used for polymorphism
   // base stats
   public String className;
   protected boolean hasMoved;
   protected boolean hasAttacked;
   protected double health;             
   protected double mp;                      
   protected double dodgeChance;                  
   protected double meleeDamage;               
   protected double rangedDamage;                  
   protected double spellDamage;               
   protected double rangedAttackRange;         
   protected double meleeAttackRange;             
   protected double movementRange;         
   protected double healthRegen;            
   protected double strength;          
   protected double agility;           
   protected double armor;               
   protected double intelligence;  
   protected double dexterity;             
   protected double vitality;        
   
   public abstract void calcStrength();
   public abstract double calcChanceToDodge();
   public abstract double calcDamageReduction(double incoming);
   public abstract double calcMovement();
   public abstract double calcSpellDamage();
   public abstract double calcRangedDamage();
   public abstract void calcHealth();
   public abstract double calcMeleeDamage();
   public abstract double calcRangeRange();
   public abstract double calcMeleeRange();
   
   // getter methods for the stats
   /**
    * 
    * @return The unit's class name
    */
   public String getClassName(){
       return className;
   }
   /**
    * 
    * @return the unit's base health
    */
   public double getHealth(){     // return value of health
       return health;
   }
   /**
    * 
    * @return the unit's base MP
    */
   public double getMP(){               //return value of mp
       return mp;
   }
   /**
    * 
    * @return The unit's base dodge chance
    */
   public double getDodgeChance(){      //return dodge chance
       return dodgeChance;
   }
   /**
    * 
    * @return The unit's base melee damage
    */
   public double getMeleeDamage(){      //return melee damage
       return meleeDamage;
   }
   /**
    * 
    * @return the unit's base ranged damage
    */
   public double getRangedDamage(){     // return ranged damage
       return rangedDamage;
   }
   /**
    * 
    * @return The unit's base spell damage
    */
   public double getSpellDamage(){        // return spell damage
       return spellDamage;
   }
   /**
    * 
    * @return The unit's base ranged attack range
    */
   public double getRangedAttackRange(){        // return ranged attack range
       return rangedAttackRange;
   }
   /**
    * 
    * @return The unit's base melee attack range
    */
   public double getMeleeAttackRange(){         // return melee attack range
       return meleeAttackRange;
   }
   /**
    * 
    * @return The unit's base movement range
    */
   public double getMovementRange(){            // return movement range
       return movementRange;
   }
   /**
    * 
    * @return The unit's base health regen value
    */
   public double getHealthRegen(){         // return health regen value
       return healthRegen;
   }
   /**
    * 
    * @return The unit's base strength
    */
   public double getStrength(){         // return strength value
       return strength;
   }
   /**
    * 
    * @return The unit's base agility 
    */
   public double getAgility(){          // return agility
       return agility;
   }
   /**
    * 
    * @return The unit's base armor
    */
   public double getArmor(){            // return armor value
       return armor;
   }
   /**
    * 
    * @return The unit's base intelligence
    */
   public double getIntelligence(){     // return intelligence
       return intelligence;
   }
   /**
    * 
    * @return The unit's base dexterity
    */
   public double getDexterity(){            // return Dexterity
       return dexterity;
   }
   /**
    * 
    * @return The unit's base vitality
    */
   public double getVitality(){             // return vitality
       return vitality;
   }
   /**
    * 
    * @return whether the unit has moved or not
    */
   public boolean hasMoved(){
       return hasMoved;
   }
   /**
    * 
    * @return whether the unit has attacked or not 
    */
   public boolean hasAttacked(){
       return hasAttacked;
   }
     /**
    * 
    * @return the unit's base health
    */
   
   //Setter methods

   /**Set the unit's health
    * 
    * 
    * @param health the new health value
    */
   public void setHealth(double health){     
       this.health = health;
   }
   /**Set the unit's MP
    * 
    * @param mp the new MP value
    */
   public void setMP(double mp){            
       this.mp = mp;
   }
   /**Set the unit's Dodge Chance
    * 
    * @param dodgeChance 
    */
   public void setDodgeChance(double dodgeChance){      
       this.dodgeChance = dodgeChance;
   }
   /**Set the unit's Melee Damage
    * 
    * @param meleeDamage 
    */
   public void setMeleeDamage(double meleeDamage){    
       this.meleeDamage = meleeDamage;
   }
  /**Set the unit's Ranged Damage
    * 
    * @param rangedDamage 
    */
   public void setRangedDamage(double rangedDamage){     
       this.rangedDamage = rangedDamage;
   }
   /**Set the unit's Spell Damage
    * 
    * @param spellDamage 
    */
   public void setSpellDamage(double spellDamage){        
       this.spellDamage = spellDamage;
   }
   /**Set the unit's Ranged Attack Range
    * 
    * @param rangedAttackRange 
    */
   public void setRangedAttackRange(int rangedAttackRange){        
       this.rangedAttackRange = rangedAttackRange;
   }
   /**Set the unit's Melee Attack Range
    * 
    * @param meleeAttackRange
    */
   public void setMeleeAttackRange(int meleeAttackRange){        
       this.meleeAttackRange =  meleeAttackRange;
   }
  /**Set the unit's MovementRange
   * 
   * @param movementRange 
   */
   public void setMovementRange(int movementRange){          
       this.movementRange = movementRange;
   }
   /**Set the unit's Health Regen
    * 
    * @param healthRegen 
    */
   public void setHealthRegen(double healthRegen){        
       this.healthRegen =  healthRegen;
   }
   /**Set the unit's Strength
    * 
    * @param strength 
    */
   public void setStrength(double strength){        
       this.strength = strength;
   }
   /**Set the unit's Agility
    * 
    * @param agility 
    */
   public void setAgility(double agility){       
       this.agility = agility;
   }
   /**Set the unit's Armor
    * 
    * @param armor 
    */
   public void setArmor(double armor){          
       this.armor = armor;
   }
   /**Set the unit's Intelligence
    * 
    * @param intelligence 
    */
   public void setIntelligence(double intelligence){    
       this.intelligence = intelligence;
   }
   /**Set the unit's Dexterity
    * 
    * @param dexterity 
    */
   public void setDexterity(double dexterity){          
       this.dexterity = dexterity;
   }
   /**Set the unit's Vitality
    * 
    * @param vitality 
    */
   public void setVitality(double vitality){             
       this.vitality = vitality;
   }

   public void setMoved(boolean moved){
       hasMoved = moved;
   }

   public void setAttacked(boolean attacked){
       hasAttacked = attacked;
   }
   
}