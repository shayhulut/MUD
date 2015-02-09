package com.company;

public class GridTile {

    int positionX;
    int positionY;
    String monsterName;
    String monsterState;
    String possibleDirections;

    public GridTile(){
    }

    public void setPositionX(int x){
        positionX = x;
    }

    public void setPositionY(int y){
        positionY = y;
    }

    public void setMonsterName(String name){
        monsterName = name;
    }

    public void setMonsterState(String state){
        monsterState = state;
    }

    public void setPossibleDirections(String directions){
        possibleDirections = directions;
    }


    public enum possibleGridTiles{

        START       (0, 0,  "StartZone",    "Here is the Start",        "north",    "Pogopuschel"),
        FOREST      (0, 1,  "Start Forest", "You are in a dark Forest", "south",    ""),

        ;
        private int posX;
        private int posY;
        private String name;
        private String description;
        private String directions;
        private String monsterName;

    possibleGridTiles(int posX, int posY, String name, String description, String directions, String monsterName){
        this.posX=posX;
        this.posY=posY;
        this.name=name;
        this.description=description;
        this.directions=directions;
        this.monsterName=monsterName;
    }

        public int getPosX() {
            return posX;
        }
        public int getPosY() {
            return posY;
        }
        public String getName() {
            return name;
        }
        public String getDescription() {
            return description;
        }
        public String getDirections() {
            return directions;
        }
        public String getMonsterName() {
            return monsterName;
        }

        public void setMonsterName(String monsterName) {
            this.monsterName = monsterName;
        }
    }

    public enum possibleMonster{

        POGOPUSCHEL("Pogopuschel","passive",5,5,0,10,5,"alive");

        private String name;
        private String state;
        private int att;
        private int def;
        private int mag;
        private int hp;
        private int exp;
        private String living;

        possibleMonster(String name, String state, int att, int def, int mag, int hp, int exp, String live) {
            this.name = name;
            this.state = state;
            this.att = att;
            this.def = def;
            this.mag = mag;
            this.hp = hp;
            this.exp = exp;
            this.living = live;                                                                                                    }

        public String getName() {
            return name;
        }
        public String getState() {
            return state;
        }
        public int getAtt() {
            return att;
        }
        public int getDef() {
            return def;
        }
        public int getMag() {
            return mag;
        }
        public int getHp() {
            return hp;
        }
        public int getExp() {
            return exp;
        }
        public String getLiving() {
            return living;
        }

        public void setLiving(String living) {
            this.living = living;
        }
    }

}
