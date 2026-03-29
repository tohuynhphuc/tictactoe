package com.phuc.tictactoe;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err
                    .println("Please input EXACTLY 1 command line argument, 1 for Player first, 2 for Computer first.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        Game game = new Game(scanner);

        game.setFirstPlayer(Integer.parseInt(args[0]));
        game.setCurrentState(GameState.INITIALIZE);
        game.gameLoop();

        scanner.close();
    }

}
