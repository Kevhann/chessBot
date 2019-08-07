/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessboard;

import utils.IllegalMoveException;
import chessboard.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.Colour;

/**
 *
 * @author kevin
 */
public class MoveTest {

    public MoveTest() {
    }

    static Chessboard chessboard = new Chessboard();
    Square[][] board = chessboard.getBoard();
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
        chessboard.initializeBoard();
        chessboard.addPieces();
    }

    @After
    public void tearDown() {
        
    }

    @Test
    public void CorrectColourMoves() {
        assertNull(board[5][0].getCurrentPiece());
        try {
            chessboard.move(legalWhiteMove, Colour.WHITE);
        } catch (IllegalMoveException e) {
        }
        assertNotNull(board[5][0].getCurrentPiece());

    }

    @Test
    public void incorrectColourDoesNotMove() {
        assertNull(board[5][0].getCurrentPiece());
        try {
            chessboard.move(legalWhiteMove, Colour.BLACK);
        } catch (IllegalMoveException e) {
            System.out.println(e);
        }
        assertNull(board[5][0].getCurrentPiece());
    }
    

}
