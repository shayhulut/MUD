package com.company;

/**
 * Created by pdargel on 06.02.15.
 */
public class Character {
    String name;
    String profession;
    int attack;
    int defense;
    int magic;
    int health;
    int experience;
    int positionX;
    int positionY;

    public Character(){
    }

    public void setName (String charName){
        name = charName;
    }

    public void setAttack (int charAttack){
        attack = charAttack;
    }

    public void setDefense (int charDefense){
        defense = charDefense;
    }

    public void setMagic (int charMagic){
        magic = charMagic;
    }

    public void setProfession (String charClass){
        profession = charClass;
    }

    public void setHealth (int charHealth){
        health = charHealth;
    }

    public void setExperience (int charExp){
        experience = charExp;
    }

    public void setPositionX (int charPosiX){
        positionX = charPosiX;
    }

    public void setPositionY (int charPosiY){
        positionY = charPosiY;
    }
}
