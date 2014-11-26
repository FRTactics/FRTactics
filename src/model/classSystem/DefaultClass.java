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
   public String getClassName(){
       return className;
   }
   public double getBaseHealth(){     // return value of health
       return health;
   }
   public double getBaseMP(){               //return value of mp
       return mp;
   }
   public double getBaseDodgeChance(){      //return dodge chance
       return dodgeChance;
   }
   public double getBaseMeleeDamage(){      //return melee damage
       return meleeDamage;
   }
   public double getBaseRangedDamage(){     // return ranged damage
       return rangedDamage;
   }
   public double getBaseSpellDamage(){        // return spell damage
       return spellDamage;
   }
   public double getBaseRangedAttackRange(){        // return ranged attack range
       return rangedAttackRange;
   }
   public double getBaseMeleeAttackRange(){         // return melee attack range
       return meleeAttackRange;
   }
   public double getBaseMovementRange(){            // return movement range
       return movementRange;
   }
   public double getBaseHealthRegen(){         // return health regen value
       return healthRegen;
   }
   public double getBaseStrength(){         // return strength value
       return strength;
   }
   public double getBaseAgility(){          // return agility
       return agility;
   }
   public double getBaseArmor(){            // return armor value
       return armor;
   }
   public double getBaseIntelligence(){     // return intelligence
       return intelligence;
   }
   public double getBaseDexterity(){            // return Dexterity
       return dexterity;
   }
   public double getBaseVitality(){             // return vitality
       return vitality;
   }
}