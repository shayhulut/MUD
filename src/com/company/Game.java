package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class Game {
    Character character = new Character();
    Monster monster = new Monster();
    GridTile gridTile = new GridTile();

    Scanner input = new Scanner(System.in);


    int gridSizeX = 10;
    int gridSizeY = 10;

    public static int dice(int high) {
        high++;
        return (int) (Math.random() * (high - 1) + 1);
    }

    public static void pause(long milliSeconds) {


        // previous way
        long endtime = System.currentTimeMillis() + milliSeconds;
        while (endtime > System.currentTimeMillis()) {
        }
    }


    public void createChar(){
        boolean run = true;
        System.out.println("You will start your adventure");
        System.out.println("Please select a class");
        System.out.println("warrior");
        while (run) {
            String charClass = input.nextLine();
            switch (charClass) {
                case "warrior":
                    character.setProfession(charClass);
                    character.setAttack(8);
                    character.setDefense(6);
                    character.setMagic(1);
                    character.setHealth(24);
                    character.setExperience(0);
                    character.setPositionX(0);
                    character.setPositionY(0);
                    run=false;
                    break;
                default:
                    System.out.println("Please select a valid Class");
                    break;
            }
        }
        System.out.println("What name has your Character ?");
        String name = input.nextLine();
        character.setName(name);



    }

    public int fightOutcome(int attack, int defense){
        int hpLoss;
        if (attack>defense)
            hpLoss = attack-defense;
        else
            hpLoss = 0;
        return hpLoss;
    }

    public int fighting(int attChar, int defChar, int hpChar, String nameEnemy, int attEnemy, int defEnemy, int hpEnemy){
        int charHPChange=0;
        int enemyHPChange=0;
        int temp;
        do {
            temp = fightOutcome(attChar+dice(6),defEnemy+dice(6));
            enemyHPChange = enemyHPChange + temp;
            if (temp==0) System.out.println("you couldn't do any damage to "+nameEnemy+".");
            else System.out.println("you did "+temp+" points of damage to "+nameEnemy);

            temp = fightOutcome(attEnemy+dice(6),defChar+dice(6));
            charHPChange = charHPChange + temp;
            if (temp==0) System.out.println(nameEnemy+" couldn't do any damage to you.");
            else System.out.println(nameEnemy+" did "+temp+" damage to you.");
            pause(500);
        }while (hpChar>charHPChange && hpEnemy>enemyHPChange);
        return charHPChange;
    }

    public void startFight(){
        int hpLoss = fighting(character.attack,character.defense,character.health,monster.name,monster.attack,monster.defense,monster.health);
        character.health = character.health - hpLoss;
        if (character.health>0) {
            character.experience = character.experience + monster.experienceGain;
            monster.living = "dead";
            System.out.println("You successfully killed " + monster.name + " and you earned "
                + monster.experienceGain + " Exp.");
            System.out.println("You lost " + hpLoss + " Health Point(s).");
        }
    }

    public void move(String direction){
        switch (direction){
            case "north":
                character.positionY++;
                break;
            case "east":
                character.positionX++;
                break;
            case "south":
                character.positionY--;
                break;
            case "west":
                character.positionX--;
                break;
            default:
                System.out.println("please choose a correct direction.");
        }
    }

    public void setMonsterToTile(int x, int y){
        for (GridTile.possibleGridTiles possibleGridTiles : GridTile.possibleGridTiles.values()){
            if (x==possibleGridTiles.getPosX())
                if (y==possibleGridTiles.getPosY()){
                    if (!possibleGridTiles.getMonsterName().isEmpty()) {
                        for (GridTile.possibleMonster possibleMonster : GridTile.possibleMonster.values())
                            if (possibleMonster.getName().equals(possibleGridTiles.getMonsterName()) && monster.living=="alive") {
                                monster.setName(possibleMonster.getName());
                                monster.setState(possibleMonster.getState());
                                monster.setAttack(possibleMonster.getAtt());
                                monster.setDefense(possibleMonster.getDef());
                                monster.setMagic(possibleMonster.getMag());
                                monster.setHealth(possibleMonster.getHp());
                                monster.setExperienceGain(possibleMonster.getExp());
                                System.out.println("You see a "+monster.name+" in this Area, staring at you ");
                            }
                    }
                    break;
                }
        }
    }

    public void setGridTile(){
        for (GridTile.possibleGridTiles possibleGridTiles : GridTile.possibleGridTiles.values()){
            if (character.positionX==possibleGridTiles.getPosX())
                if (character.positionY==possibleGridTiles.getPosY()){
                    gridTile.setPositionX(possibleGridTiles.getPosX());
                    gridTile.setPositionY(possibleGridTiles.getPosY());
                    gridTile.setPossibleDirections(possibleGridTiles.getDirections());
                    System.out.println("You enter "+possibleGridTiles.getName());
                    System.out.println(possibleGridTiles.getDescription());
                    if (!"".equals(possibleGridTiles.getDirections()))
                        System.out.println("you can go into following directions: "+possibleGridTiles.getDirections());
                    else
                        System.out.println("you can not go in any directions");
                    break;
                }
        }

    }

    public void showCharInfo(){
        System.out.println();
        System.out.println(character.name+" the "+character.profession);
        System.out.println("Your Characters Attack Value: "+character.attack);
        System.out.println("Your Characters Defense Value: "+character.defense);
        System.out.println("Your Characters Magic Value: "+character.magic);
        System.out.println("Your Characters Health Points: "+character.health);
        System.out.println("Your Characters Experience Points: "+character.experience);
        System.out.println();
    }

    public void possibleActions(String user_input){
        if (gridTile.possibleDirections.contains(user_input)) move(user_input);
        else if ("attack".contains(user_input)) startFight();
        else if ("character".contains(user_input)) showCharInfo();
        else if ("save".contains(user_input)) saveGame();
        else if ("load".contains(user_input));
        else if ("help".contains(user_input)) {
            System.out.println("Following commands are possible:");
            System.out.println(gridTile.possibleDirections);
            System.out.println("attack");
            System.out.println("character");
            System.out.println("exit");
            System.out.println();
        }
        else System.out.println("this command is not supported. please enter 'help' to see a list possible commands:");
    }

    public boolean onGrid(boolean run){
        while (character.health>0 && run){
            setGridTile();
            setMonsterToTile(character.positionX, character.positionY);
            if ("aggressive".equals(monster.state) && "alive".equals(monster.living)){
                System.out.println(monster.name + " sees you and attack instantly");
                pause(2000);
                startFight();
            }
            String user_input = input.nextLine();
            if (!"exit".equals(user_input)) possibleActions(user_input);
            else {
                System.out.println("Are you sure you want to quit ? all non saved progress will be lost.(yes/no)");
                user_input = input.nextLine();
                if ("yes".equals(user_input)) run=false;
            }
        }
        return run;
    }




    public void loadGame() throws IOException {

        String content = new String(Files.readAllBytes(Paths.get("charfile")));
        String []parts = content.split(",");
        character.setName(parts[0]);
        character.setProfession(parts[1]);
        character.setAttack(Integer.parseInt(parts[2]));
        character.setDefense(Integer.parseInt(parts[3]));
        character.setMagic(Integer.parseInt(parts[4]));
        character.setHealth(Integer.parseInt(parts[5]));
        character.setExperience(Integer.parseInt(parts[6]));
        character.setPositionX(Integer.parseInt(parts[7]));
        character.setPositionY(Integer.parseInt(parts[8]));
    }

    public void saveGame(){
        BufferedWriter writer = null;
        String charString="";
        charString = charString.concat(character.name+",");
        charString = charString.concat(character.profession+",");
        charString = charString.concat(character.attack+",");
        charString = charString.concat(character.defense+",");
        charString = charString.concat(character.magic+",");
        charString = charString.concat(character.health+",");
        charString = charString.concat(character.experience+",");
        charString = charString.concat((character.positionX)+",");
        charString = charString.concat(String.valueOf(character.positionY));
        try
        {
            writer = new BufferedWriter( new FileWriter("charfile"));
            writer.write(charString);
        }
        catch ( IOException e)
        {
        }
        finally
        {
            try
            {
                if ( writer != null)
                    writer.close( );
            }
            catch ( IOException e)
            {
            }
        }
    }
}
