package com.phuc.tictactoe;

import java.util.Scanner;

import com.phuc.tictactoe.players.Computer;
import com.phuc.tictactoe.players.Human;
import com.phuc.tictactoe.players.Player;

public class App {

    public static void main(String[] args) {
        if (args.length != 1 || (args.length == 1 && (!args[0].equals("1") && !args[0].equals("2")))) {
            System.out.println("Please, input a valid option [1-2]");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        Player user = new Human("Player#1", scanner);
        Player computer = new Computer("Player#2");

        Game game = new Game(user, computer);

        game.setFirstPlayer(Integer.parseInt(args[0]));
        game.setCurrentState(GameState.INITIALIZE);
        Thread gameThread = new Thread(() -> {
            game.gameLoop();
        });
        gameThread.start();
    }

}
