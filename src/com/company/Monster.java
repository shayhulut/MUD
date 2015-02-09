package com.company;

public class Monster {
    String name;
    String state;   //passive or aggressive
    int experienceGain;
    int attack;
    int defense;
    int magic;
    int health;
    String living = "alive";

    public Monster(){
    }

    public void setAttack (int enemyAttack){
        attack = enemyAttack;
    }

    public void setDefense (int enemyDefense){
        defense = enemyDefense;
    }

    public void setMagic (int enemyMagic){
        magic = enemyMagic;
    }

    public void setExperienceGain (int enemyExp){
        experienceGain = enemyExp;
    }

    public void setHealth (int enemyHealth){
        health = enemyHealth;
    }

    public void setName (String enemyName){
        name = enemyName;
    }

    public void setState (String enemyState){
        state = enemyState;
    }

    public void setLiving (String enemyLiving){
        living = enemyLiving;
    }
}
