package com.phuc.tictactoe;

import java.util.Scanner;

public class Game {

    private int numRows = 3;
    private int numCols = 3;

    private CellState[][] boardState = new CellState[numRows][numCols];

    private GameState currentState;
    private boolean isUserFirst;

    private void initializeBoard() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                boardState[i][j] = CellState.EMPTY;
            }
        }
    }

    private void userTurn() {
        GameView.displayBoard(numRows, numCols);
        System.out.println("User turn. Please enter a move.");

        boolean isValidMove = false;
        Scanner scanner = new Scanner(System.in);
        while (!isValidMove) {
            int input = scanner.nextInt();
            isValidMove = setCell(input);
            if (!isValidMove) {
                System.out.println("Invalid move.");
            }
        }
        scanner.close();

        checkWinner();
    }

    private void computerTurn() {
        GameView.displayBoard(numRows, numCols);
        System.out.println("Computer turn. Please enter a move.");

        for (int i = 1; i <= numRows * numCols; i++) {
            if (setCell(i)) {
                System.out.println(i);
            }
        }

        checkWinner();
    }

    privat CellState checkWinner() {
        // winner is currently using CellState but having a Player enum would be better
        CellState winner = CellState.EMPTY;

        // Check Rows
        for (int i = 0; i < numRows; i++) {
            if (boardState[i][0] == CellState.EMPTY) {
                continue;
            }

            boolean flag = true;
            for (int j = 1; j < numCols; j++) {
                if (boardState[i][j] != boardState[i][0]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                winner = boardState[i][0];
                return winner;
            }
        }

        // Check Cols
        for (int j = 0; j < numCols; j++) {
            if (boardState[0][j] == CellState.EMPTY) {
                continue;
            }

            boolean flag = true;
            for (int i = 1; i < numRows; i++) {
                if (boardState[i][j] != boardState[0][j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                winner = boardState[0][j];
                return winner;
            }
        }

        // Check Diagonals \
        if (boardState[0][0] != CellState.EMPTY) {
            boolean flag = true;
            for (int i = 1; i < Math.min(numRows, numCols); i++) {
                if (boardState[i][i] != boardState[0][0]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                winner = boardState[0][0];
                return winner;
            }
        }

        // Check Diagonals /
        if (boardState[0][0] != CellState.EMPTY) {
            boolean flag = true;
            for (int i = 1; i < Math.min(numRows, numCols); i++) {
                if (boardState[i][i] != boardState[0][0]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                winner = boardState[0][0];
                return winner;
            }
        }
    }

    public void gameLoop() {
        while (true) {
            switch (currentState) {
                case INITIALIZE:
                    initializeBoard();
                    setCurrentState(isUserFirst ? GameState.USER : GameState.COMPUTER);
                    break;
                case USER:
                    userTurn();
                    break;
                case COMPUTER:
                    computerTurn();
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    public void setFirstPlayer(int value) {
        if (value == 1) {
            isUserFirst = true;
        } else if (value == 2) {
            isUserFirst = false;
        } else {
            System.err.println("Incorrect Argument. Please input EXACTLY 1 command line argument, 1 for Player first, 2 for Computer first.");
        }
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState newState) {
        currentState = newState;
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
        return getCell((id - 1) % 3, (id - 1) / 3);
    }

    public boolean setCell(int id) {
        int n = (id - 1) % 3;
        int m = (id - 1) / 3;
        return setCell(n, m);
    }

    public boolean setCell(int i, int j) {
        if (boardState[i][j] == CellState.EMPTY) {
            boardState[i][j] = currentState == GameState.USER ? CellState.USER : CellState.COMPUTER;
            return true;
        }
        return false;
    }

    public CellState getCell(int i, int j) {
        return boardState[i][j];
    }

}
