package com.phuc.tictactoe.players;

import java.util.Scanner;

import com.phuc.tictactoe.GameBoard;

public class Human extends Player {

    public Human(String name, Scanner scanner) {
        super(name, scanner);
    }

    @Override
    public int makeMove(GameBoard board) {
        board.displayBoard();
        System.out.println(name + "\'s turn. Please enter a move.");

        int cell = -1;

        boolean isValidMove = false;
        while (!isValidMove) {
            cell = Integer.parseInt(scanner.nextLine());

            isValidMove = board.isValid(cell);
            if (!isValidMove) {
                System.out.println("Invalid move.");
            }
        }

        return cell;
    }

}
