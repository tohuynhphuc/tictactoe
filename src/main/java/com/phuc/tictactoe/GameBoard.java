package com.phuc.tictactoe;

public class GameBoard {

    private int numRows = 3;
    private int numCols = 3;

    private CellState[][] boardState = new CellState[numRows][numCols];

    public GameBoard(int rows, int cols) {
        numRows = rows;
        numCols = cols;

        boardState = new CellState[numRows][numCols];

        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                boardState[i][j] = CellState.EMPTY;
            }
        }
    }

    public void displayBoard() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                System.out.print("| " + displayCellState(getCell(convertToId(i, j))) + " ");
            }
            System.out.print("|\n");
        }
    }

    private String displayCellState(CellState state) {
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

    public CellState checkWinner() {
        // winner is currently using CellState but having a Player enum would be better
        CellState winner = CellState.EMPTY;

        // Check Rows
        for (int i = 0; i < numRows; i++) {
            if (getCell(convertToId(i, 0)) == CellState.EMPTY) {
                continue;
            }

            boolean flag = true;
            for (int j = 1; j < numCols; j++) {
                if (getCell(convertToId(i, j)) != getCell(convertToId(i, 0))) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                winner = getCell(convertToId(i, 0));
                return winner;
            }
        }

        // Check Cols
        for (int j = 0; j < numCols; j++) {
            if (getCell(convertToId(0, j)) == CellState.EMPTY) {
                continue;
            }

            boolean flag = true;
            for (int i = 1; i < numRows; i++) {
                if (getCell(convertToId(i, j)) != getCell(convertToId(0, j))) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                winner = getCell(convertToId(0, j));
                return winner;
            }
        }

        // We don't check diagonals if board isn't square
        if (numRows != numCols) {
            return winner;
        }

        // Check Diagonals \
        if (getCell(1) != CellState.EMPTY) {
            boolean flag = true;
            for (int i = 1; i < numRows; i++) {
                if (getCell(convertToId(i, i)) != getCell(1)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                winner = getCell(1);
                return winner;
            }
        }

        // Check Diagonals /
        if (getCell(convertToId(0, numRows - 1)) != CellState.EMPTY) {
            boolean flag = true;
            for (int i = 1; i < numRows; i++) {
                if (getCell(convertToId(i, numRows - 1 - i)) != getCell(convertToId(0, numRows - 1))) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                winner = getCell(convertToId(0, numRows - 1));
                return winner;
            }
        }

        return winner;
    }

    public boolean isFull() {
        for (int i = 1; i <= numRows * numCols; i++) {
            if (getCell(i) == CellState.EMPTY) {
                return false;
            }
        }

        return true;
    }

    private int convertToId(int n, int m) {
        return n * numCols + m + 1;
    }

    public boolean isOutOfBounds(int id) {
        return id < 1 || id > numRows * numCols;
    }

    public boolean isValid(int id) {
        int i = (id - 1) / numCols;
        int j = (id - 1) % numCols;

        return boardState[i][j] == CellState.EMPTY;
    }

    public CellState getCell(int id) {
        int i = (id - 1) / numCols;
        int j = (id - 1) % numCols;

        return boardState[i][j];
    }

    public void setCell(int id, boolean isTurnFirstPlayer) {
        int i = (id - 1) / numCols;
        int j = (id - 1) % numCols;

        boardState[i][j] = isTurnFirstPlayer ? CellState.USER : CellState.COMPUTER;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public void setupTestScenario(CellState[][] testScenario) {
        boardState = testScenario;
    }

}
