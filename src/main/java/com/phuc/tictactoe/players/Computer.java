package com.phuc.tictactoe.players;

import com.phuc.tictactoe.GameBoard;

public class Computer extends Player {

    public Computer(String name) {
        super(name);
    }

    @Override
    public int makeMove(GameBoard board) {
        board.displayBoard();
        System.out.println(name + "\'s turn. Please enter a move.");

        for (int i = 1; i <= board.getNumRows() * board.getNumCols(); i++) {
            if (board.isValid(i)) {
                return i;
            }
        }

        return -1;
    }

}
