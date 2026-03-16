package com.phuc.tictactoe;

import java.util.Scanner;

public class Player {

    private boolean isComputer;
    private String name;
    private Scanner scanner;

    public Player(String name, boolean isComputer, Scanner scanner) {
        this.isComputer = isComputer;
        this.name = name;
        this.scanner = scanner;
    }

    public int playerTurn(GameBoard board) {
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
    }

    public void announceWin(GameBoard board) {
        board.displayBoard();
        System.out.println(name + " WIN !!!");
    }

    public void announceDraw(GameBoard board) {
        board.displayBoard();
        System.out.println("DRAW");
    }

}
