package com.phuc.tictactoe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class AppTest {

    private final GameBoard board = new GameBoard(3, 3);

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void shouldCheckBoardIsFull() {
        CellState[][] testScenario = {
            {CellState.COMPUTER, CellState.USER, CellState.COMPUTER},
            {CellState.COMPUTER, CellState.USER, CellState.COMPUTER},
            {CellState.USER, CellState.COMPUTER, CellState.USER}
        };

        board.setupTestScenario(testScenario);

        assertTrue(board.isFull());
    }

    @Test
    public void shouldCheckBoardIsNotFull() {
        CellState[][] testScenario = {
            {CellState.COMPUTER, CellState.USER, CellState.COMPUTER},
            {CellState.COMPUTER, CellState.USER, CellState.COMPUTER},
            {CellState.USER, CellState.EMPTY, CellState.USER}
        };

        board.setupTestScenario(testScenario);

        assertFalse(board.isFull());
    }

    @Test
    public void shouldCheckComputerWin() {
        CellState[][] testScenario = {
            {CellState.COMPUTER, CellState.USER, CellState.COMPUTER},
            {CellState.COMPUTER, CellState.USER, CellState.COMPUTER},
            {CellState.COMPUTER, CellState.EMPTY, CellState.USER}
        };

        board.setupTestScenario(testScenario);

        assertEquals(board.checkWinner(), CellState.COMPUTER);
    }

    @Test
    public void shouldCheckUserWin() {
        CellState[][] testScenario = {
            {CellState.COMPUTER, CellState.USER, CellState.USER},
            {CellState.COMPUTER, CellState.USER, CellState.COMPUTER},
            {CellState.USER, CellState.EMPTY, CellState.COMPUTER}
        };

        board.setupTestScenario(testScenario);

        assertEquals(board.checkWinner(), CellState.USER);
    }

}
