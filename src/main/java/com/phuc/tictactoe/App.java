package com.phuc.tictactoe;

public class App {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err
                    .println("Please input EXACTLY 1 command line argument, 1 for Player first, 2 for Computer first.");
        }
        Game.setFirstPlayer(Integer.parseInt(args[1]));
        Game.setCurrentState(GameState.INITIALIZE);
    }

}
