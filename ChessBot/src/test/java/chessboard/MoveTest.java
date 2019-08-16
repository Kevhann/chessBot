/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessboard;

import utils.Move;
import utils.IllegalMoveException;
import chessboard.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kevin
 */
public class MoveTest {

    public MoveTest() {
    }

    static Chessboard board = new Chessboard();
    Move legalWhiteMove = new Move("a2,a3");
    Move legalBlackMove = new Move("b7,b6");

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        board.addPieces();
    }

    @After
    public void tearDown() {
        board.clearBoard();
    }

    @Test
    public void CorrectColourMoves() {
        assertEquals(0, (byte) board.pieceOnBoard("a3"));
        try {
            board.move(legalWhiteMove, (byte) 1);
        } catch (IllegalMoveException e) {
        }
        assertEquals(6, (byte) board.pieceOnBoard("a3"));
    }

    @Test
    public void incorrectColourDoesNotMove() {
        assertEquals(0, (byte) board.pieceOnBoard("a3"));
        try {
            board.move(legalBlackMove, (byte) 1);
        } catch (IllegalMoveException e) {
            System.out.println(e);
        }
        assertEquals(0, (byte) board.pieceOnBoard("a3"));
    }

}
