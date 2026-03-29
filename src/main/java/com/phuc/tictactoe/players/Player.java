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

    /* {
        board.displayBoard();
        System.out.println(name + "\'s turn. Please enter a move.");

        int cell = -1;

        if (isComputer) {
            for (int i = 1; i <= board.getNumRows() * board.getNumCols(); i++) {
                if (board.isValid(i)) {
                    cell = i;
                    break;
                }
            }
        } else {
            boolean isValidMove = false;
            while (!isValidMove) {
                cell = Integer.parseInt(scanner.nextLine());
                isValidMove = board.isValid(cell);
                if (!isValidMove) {
                    System.out.println("Invalid move.");
                }
            }
        }

        return cell;
    }*/
    public void announceWin(GameBoard board) {
        board.displayBoard();
        System.out.println(name + " WIN !!!");
    }

    public void announceDraw(GameBoard board) {
        board.displayBoard();
        System.out.println("DRAW");
    }

}
