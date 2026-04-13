package com.phuc.tictactoe.players;

import com.phuc.tictactoe.GameBoard;

public abstract class Player {

    protected final String name;

    public Player(String name) {
        this.name = name;
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
