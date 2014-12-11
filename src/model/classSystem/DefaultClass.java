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
   public String owner;
   public String className;
   protected boolean hasMoved;
   protected boolean hasAttacked;
   protected boolean defending;
   protected double baseHealth;
   protected double health;             
   protected double mp;                      
   protected double dodgeChance;                  
   protected double attackDamage;               
//   protected double rangedDamage;                  
   protected double spellDamage;
   protected double attackRange;
//   protected double rangedAttackRange;         
//   protected double meleeAttackRange;             
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
   public abstract double calcAttackRange();
   public abstract void calcHealth();
   public abstract double calcAttackDamage();
   
   public String getOwner(){
       return owner;
   }
   public void setOwner(String owner){
       this.owner = owner;
   }

   /**getter method for the base health
    * 
    * @return the units base health this will remain constant
    */
   public double getBaseHealth(){
       return baseHealth;
   }
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
   public double getAttackDamage(){
       return attackDamage;
   }
   /**
    * 
    * @return The unit's base spell damage
    */
   public double getSpellDamage(){        // return spell damage
       return spellDamage;
   }

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
   public double getAttackRange(){
       return attackRange;
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
     
   public boolean isDefending(){
       return defending;
   }
   
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

   public void setAttackRange(double attackRange){
       this.attackRange = attackRange;
   }
   /**Set the unit's Spell Damage
    * 
    * @param spellDamage 
    */
   public void setSpellDamage(double spellDamage){        
       this.spellDamage = spellDamage;
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
   public void setDefending(boolean defending){
       this.defending = defending;
   }
   
}