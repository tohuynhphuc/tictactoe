package com.phuc.tictactoe.players;

import java.util.Scanner;

import com.phuc.tictactoe.GameBoard;

public abstract class Player {

    protected final String name;
    protected final Scanner scanner;

    public Player(String name, Scanner scanner) {
        this.name = name;
        this.scanner = scanner;
    }

    public abstract int makeMove(GameBoard board);

    public void announceWin(GameBoard board) {
        board.displayBoard();
        System.out.println(name + " WIN !!!");
    }

    public void announceDraw(GameBoard board) {
        board.displayBoard();
        System.out.println("DRAW");
    }

}
