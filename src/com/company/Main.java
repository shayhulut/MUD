package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        Game game = new Game();

        System.out.println("*******");
        System.out.println("**MUD**");
        System.out.println("*******");
        System.out.println();

        System.out.println("Enter start for a new game, continue to load last game or exit to end MUD");
        boolean run = true;

        while (run) {
            String user_input = input.nextLine();

            switch (user_input){
                case "start":
                    game.createChar();
                    run = game.onGrid(run);
                    break;
                case "continue":
                    game.loadGame();
                    run = game.onGrid(run);
                    break;
                case "load":
                    game.loadGame();
                    run = game.onGrid(run);
                    break;
                case "exit":
                    System.out.println("Sure you want to quit ? (yes/no)");
                    user_input = input.nextLine();
                        if ("yes".equals(user_input)) run=false;
                    break;
                default:
                    System.out.println("Please enter start, continue or exit");
                    break;
            }
        }
    }

}

