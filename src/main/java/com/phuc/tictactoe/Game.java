package com.phuc.tictactoe;

import com.phuc.tictactoe.players.Player;

public class Game {

    private GameState currentState;
    private boolean isTurnFirstPlayer;

    private GameBoard board;
    private final int numRows = 3;
    private final int numCols = 3;

    private final Player firstPlayer;
    private final Player secondPlayer;

    public Game(Player firstPlayer, Player secondPlayer) {
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
                    System.out.println("Hello!");
                    setCurrentState(GameState.PLAYER);
                    break;
                case PLAYER:
                    playTurn();
                    finishTurn(currentState);
                    break;
                // case USER:
                //     board.setCell(firstPlayer.makeMove(board), isTurnFirstPlayer);
                //     finishTurn(currentState);
                //     break;
                // case COMPUTER:
                //     board.setCell(secondPlayer.makeMove(board), isTurnFirstPlayer);
                //     finishTurn(currentState);
                //     break;
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

    private void playTurn() {
        int move;
        if (isTurnFirstPlayer) {
            move = firstPlayer.makeMove(board);
        } else {
            move = secondPlayer.makeMove(board);
        }
        board.setCell(move, isTurnFirstPlayer);
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
        isTurnFirstPlayer = !isTurnFirstPlayer;
        // setCurrentState(currentState == GameState.COMPUTER ? GameState.USER : GameState.COMPUTER);
    }

    public void setFirstPlayer(int value) {
        isTurnFirstPlayer = value == 1;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState newState) {
        currentState = newState;
    }

}
