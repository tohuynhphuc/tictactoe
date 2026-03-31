package com.phuc.tictactoe;

import java.util.Scanner;

import com.phuc.tictactoe.players.Computer;
import com.phuc.tictactoe.players.Human;
import com.phuc.tictactoe.players.Player;

public class Game {

    private GameState currentState;
    private boolean isUserFirst;

    private GameBoard board;
    private final int numRows = 3;
    private final int numCols = 3;

    private Player user;
    private Player computer;

    private final Scanner scanner;

    public Game(Scanner scanner) {
        this.scanner = scanner;
    }

    private void initialize() {
        board = new GameBoard(numRows, numCols);

        user = new Human("User", scanner);
        computer = new Computer("Computer", scanner);
    }

    public void gameLoop() {
        while (currentState != GameState.END) {
            switch (currentState) {
                case INITIALIZE:
                    initialize();
                    setCurrentState(isUserFirst ? GameState.USER : GameState.COMPUTER);
                    break;
                case USER:
                    board.setCell(user.makeMove(board), currentState);
                    finishTurn(currentState);
                    break;
                case COMPUTER:
                    board.setCell(computer.makeMove(board), currentState);
                    finishTurn(currentState);
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
