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
   public double getBaseHealth(){     // return value of health
       return health;
   }
   /**
    * 
    * @return the unit's base MP
    */
   public double getBaseMP(){               //return value of mp
       return mp;
   }
   /**
    * 
    * @return The unit's base dodge chance
    */
   public double getBaseDodgeChance(){      //return dodge chance
       return dodgeChance;
   }
   /**
    * 
    * @return The unit's base melee damage
    */
   public double getBaseMeleeDamage(){      //return melee damage
       return meleeDamage;
   }
   /**
    * 
    * @return the unit's base ranged damage
    */
   public double getBaseRangedDamage(){     // return ranged damage
       return rangedDamage;
   }
   /**
    * 
    * @return The unit's base spell damage
    */
   public double getBaseSpellDamage(){        // return spell damage
       return spellDamage;
   }
   /**
    * 
    * @return The unit's base ranged attack range
    */
   public double getBaseRangedAttackRange(){        // return ranged attack range
       return rangedAttackRange;
   }
   /**
    * 
    * @return The unit's base melee attack range
    */
   public double getBaseMeleeAttackRange(){         // return melee attack range
       return meleeAttackRange;
   }
   /**
    * 
    * @return The unit's base movement range
    */
   public double getBaseMovementRange(){            // return movement range
       return movementRange;
   }
   /**
    * 
    * @return The unit's base health regen value
    */
   public double getBaseHealthRegen(){         // return health regen value
       return healthRegen;
   }
   /**
    * 
    * @return The unit's base strength
    */
   public double getBaseStrength(){         // return strength value
       return strength;
   }
   /**
    * 
    * @return The unit's base agility 
    */
   public double getBaseAgility(){          // return agility
       return agility;
   }
   /**
    * 
    * @return The unit's base armor
    */
   public double getBaseArmor(){            // return armor value
       return armor;
   }
   /**
    * 
    * @return The unit's base intelligence
    */
   public double getBaseIntelligence(){     // return intelligence
       return intelligence;
   }
   /**
    * 
    * @return The unit's base dexterity
    */
   public double getBaseDexterity(){            // return Dexterity
       return dexterity;
   }
   /**
    * 
    * @return The unit's base vitality
    */
   public double getBaseVitality(){             // return vitality
       return vitality;
   }
}