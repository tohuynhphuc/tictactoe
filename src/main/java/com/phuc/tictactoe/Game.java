package com.phuc.tictactoe;

import java.util.Scanner;

public class Game {

    private GameState currentState;
    private boolean isUserFirst;

    private GameBoard board;
    private int numRows = 3;
    private int numCols = 3;

    private Player user;
    private Player computer;

    private Scanner scanner;

    public Game(Scanner scanner) {
        this.scanner = scanner;
    }

    private void initialize() {
        board = new GameBoard(numRows, numCols);

        user = new Player("User", false, scanner);
        computer = new Player("Computer", true, scanner);
    }

    // private void userTurn() {
    //     board.displayBoard();
    //     System.out.println("User turn. Please enter a move.");
    //     boolean isValidMove = false;
    //     Scanner scanner = new Scanner(System.in);
    //     while (!isValidMove) {
    //         int input = scanner.nextInt();
    //         isValidMove = board.setCell(input, currentState);
    //         if (!isValidMove) {
    //             System.out.println("Invalid move.");
    //         }
    //     }
    //     scanner.close();
    //     board.checkWinner();
    // }
    // private void computerTurn() {
    //     board.displayBoard();
    //     System.out.println("Computer turn. Please enter a move.");
    //     for (int i = 1; i <= numRows * numCols; i++) {
    //         if (board.setCell(i, currentState)) {
    //             System.out.println(i);
    //         }
    //     }
    //     board.checkWinner();
    // }
    public void gameLoop() {
        while (currentState != GameState.END) {
            switch (currentState) {
                case INITIALIZE:
                    initialize();
                    setCurrentState(isUserFirst ? GameState.USER : GameState.COMPUTER);
                    break;
                case USER:
                    board.setCell(user.playerTurn(board), currentState);
                    finishTurn(board, currentState);
                    break;
                case COMPUTER:
                    board.setCell(computer.playerTurn(board), currentState);
                    finishTurn(board, currentState);
                    break;
                case WIN:
                    user.announceWin(board);
                    setCurrentState(GameState.END);
                    break;
                case LOSE:
                    computer.announceWin(board);
                    setCurrentState(GameState.END);
                    break;
                case DRAW:
                    user.announceDraw(board);
                    setCurrentState(GameState.END);
                    break;
                case END:
                default:
                    return;
            }
        }
    }

    private void finishTurn(GameBoard board, GameState currentState) {
        CellState winner = board.checkWinner();
        switch (winner) {
            case COMPUTER:
                // Computer wins
                setCurrentState(GameState.LOSE);
                return;
            case USER:
                setCurrentState(GameState.WIN);
                return;
            case EMPTY:
            default:
                // No Winner
                if (board.checkDraw()) {
                    setCurrentState(GameState.DRAW);
                    return;
                }
        }

        // Switch players
        setCurrentState(currentState == GameState.COMPUTER ? GameState.USER : GameState.COMPUTER);
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

}
