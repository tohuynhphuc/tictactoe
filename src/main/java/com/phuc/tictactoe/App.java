package com.phuc.tictactoe;

public class App {

    private int numRows = 3;
    private int numCols = 3;
    private CellState[][] boardState = new CellState[numRows][numCols];

    public static void main(String[] args) {
        System.out.println("Hello World");
    }

    public CellState getCell(int id) {
        /**
         * Hardcoded Cell IDs
         *
         * 1 2 3
         *
         * 4 5 6
         *
         * 7 8 9
         */
        return boardState[id % 3][(id - 1) / 3];
    }
}
