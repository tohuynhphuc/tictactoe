package com.phuc.tictactoe;

public class GameView {

    public static void displayBoard(int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("| " + displayCellState(Game.getCell(i, j)) + " ");
            }
            System.out.print("|\n\n");
        }
    }

    private static String displayCellState(CellState state) {
        switch (state) {
            case USER:
                return "1";
            case COMPUTER:
                return "2";
            case EMPTY:
            default:
                return "0";
        }
    }

}
