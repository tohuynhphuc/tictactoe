package com.phuc.tictactoe;

import java.util.Scanner;

import com.phuc.tictactoe.players.Player;

public class Game {

    private GameState currentState;
    private boolean isFirstPlayer;

    private GameBoard board;
    private final int numRows = 3;
    private final int numCols = 3;

    private Player firstPlayer;
    private Player secondPlayer;

    private final Scanner scanner;

    public Game(Scanner scanner, Player firstPlayer, Player secondPlayer) {
        this.scanner = scanner;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    private void initialize() {
        board = new GameBoard(numRows, numCols);
    }

    public void gameLoop() {
        while (currentState != GameState.END) {
            switch (currentState) {
                case INITIALIZE:
                    initialize();
                    setCurrentState(isFirstPlayer ? GameState.USER : GameState.COMPUTER);
                    break;
                case USER:
                    board.setCell(firstPlayer.makeMove(board), currentState);
                    finishTurn(currentState);
                    break;
                case COMPUTER:
                    board.setCell(secondPlayer.makeMove(board), currentState);
                    finishTurn(currentState);
                    break;
                case WIN:
                    firstPlayer.announceWin(board);
                    setCurrentState(GameState.END);
                    break;
                case LOSE:
                    secondPlayer.announceWin(board);
                    setCurrentState(GameState.END);
                    break;
                case DRAW:
                    firstPlayer.announceDraw(board);
                    setCurrentState(GameState.END);
                    break;
                case END:
                default:
                    return;
            }
        }
    }

    private void finishTurn(GameState currentState) {
        CellState winner = board.checkWinner();
        switch (winner) {
            case COMPUTER:
                setCurrentState(GameState.LOSE);
                return;
            case USER:
                setCurrentState(GameState.WIN);
                return;
            case EMPTY:
            default:
                if (board.isFull()) {
                    setCurrentState(GameState.DRAW);
                    return;
                }
        }

        // Switch players
        setCurrentState(currentState == GameState.COMPUTER ? GameState.USER : GameState.COMPUTER);
    }

    public void setFirstPlayer(int value) {
        switch (value) {
            case 1:
                isFirstPlayer = true;
                break;
            case 2:
                isFirstPlayer = false;
                break;
            default:
                System.err.println("Incorrect Argument. Please input EXACTLY 1 command line argument, 1 for Player first, 2 for Computer first.");
                break;
        }
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState newState) {
        currentState = newState;
    }

}
