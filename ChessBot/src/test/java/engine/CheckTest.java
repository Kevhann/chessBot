/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import chessboard.Chessboard;
import engine.Check;
import engine.MoveChecker;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import utils.*;

/**
 *
 * @author kevin
 */
public class CheckTest {

    /**
     *
     * @author kevin
     */
    static Chessboard board = new Chessboard();
    Check check = new Check();
    MoveChecker checker = new MoveChecker(board);

    public CheckTest() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
        board.clearBoard();
    }

    @Test
    public void returnsFalseWithEmptyBoard() {
        assertFalse(check.isChallenged(board.getBoard(), 6, 3, (byte) 1));
        assertFalse(check.isChallenged(board.getBoard(), 4, 4, (byte) -1));
    }

    @Test
    public void whitePawnsChallengeCorrectly() {
        board.addPiece((byte) 6, "d2");
        assertTrue(check.isChallenged(board.getBoard(), "e3", (byte) -1));
        assertTrue(check.isChallenged(board.getBoard(), "c3", (byte) -1));
        assertFalse(check.isChallenged(board.getBoard(), "d3", (byte) -1));
        assertFalse(check.isChallenged(board.getBoard(), "e1", (byte) -1));
        assertFalse(check.isChallenged(board.getBoard(), "e2", (byte) -1));
        assertFalse(check.isChallenged(board.getBoard(), "c2", (byte) -1));
        assertFalse(check.isChallenged(board.getBoard(), "e4", (byte) -1));
        assertFalse(check.isChallenged(board.getBoard(), "c4", (byte) -1));
        assertFalse(check.isChallenged(board.getBoard(), "d1", (byte) -1));
    }

    @Test
    public void blackPawnsChallengeCorrectly() {
        board.addPiece((byte) -6, "d2");
        assertTrue(check.isChallenged(board.getBoard(), "e1", (byte) 1));
        assertTrue(check.isChallenged(board.getBoard(), "c1", (byte) 1));
        assertFalse(check.isChallenged(board.getBoard(), "d1", (byte) 1));
        assertFalse(check.isChallenged(board.getBoard(), "e3", (byte) 1));
        assertFalse(check.isChallenged(board.getBoard(), "e2", (byte) 1));
        assertFalse(check.isChallenged(board.getBoard(), "c2", (byte) 1));
        assertFalse(check.isChallenged(board.getBoard(), "e4", (byte) 1));
        assertFalse(check.isChallenged(board.getBoard(), "c4", (byte) 1));
        assertFalse(check.isChallenged(board.getBoard(), "d3", (byte) 1));
    }

    @Test
    public void pawnsDontChallengeSameColour() {
        board.addPiece((byte) 6, "d2");
        assertFalse(check.isChallenged(board.getBoard(), "e3", (byte) 1));
    }

    @Test
    public void knightsChallengeCorrectly() {
        board.addPiece((byte) 4, "d4");
        assertTrue(check.isChallenged(board.getBoard(), "e6", (byte) -1));
        assertTrue(check.isChallenged(board.getBoard(), "e2", (byte) -1));
        assertTrue(check.isChallenged(board.getBoard(), "f5", (byte) -1));
        assertTrue(check.isChallenged(board.getBoard(), "f3", (byte) -1));
        assertTrue(check.isChallenged(board.getBoard(), "c6", (byte) -1));
        assertTrue(check.isChallenged(board.getBoard(), "c2", (byte) -1));
        assertTrue(check.isChallenged(board.getBoard(), "b5", (byte) -1));
        assertTrue(check.isChallenged(board.getBoard(), "b3", (byte) -1));
    }

    @Test
    public void rooksChallengeCorrectly1() {
        board.addPiece((byte) -5, "c2");
        assertTrue(check.isChallenged(board.getBoard(), "h2", (byte) 1));
        assertTrue(check.isChallenged(board.getBoard(), "c8", (byte) 1));
        assertTrue(check.isChallenged(board.getBoard(), "a2", (byte) 1));
        assertTrue(check.isChallenged(board.getBoard(), "c1", (byte) 1));
        assertFalse(check.isChallenged(board.getBoard(), "e6", (byte) 1));
    }

    @Test
    public void rooksChallengeCorrectly2() {
        board.addPiece((byte) 5, "a5");
        assertTrue(check.isChallenged(board.getBoard(), "h5", (byte) -1));
        assertTrue(check.isChallenged(board.getBoard(), "g5", (byte) -1));
        assertTrue(check.isChallenged(board.getBoard(), "a2", (byte) -1));
        assertTrue(check.isChallenged(board.getBoard(), "a8", (byte) -1));
        assertFalse(check.isChallenged(board.getBoard(), "e6", (byte) -1));
    }

    @Test
    public void rooksCannotChallengeThroughPieces() {
        board.addPiece((byte) -5, "c2");
        board.addPiece((byte) 1, "g2");
        board.addPiece((byte) 1, "c5");
        assertFalse(check.isChallenged(board.getBoard(), "h2", (byte) 1));
        assertFalse(check.isChallenged(board.getBoard(), "c8", (byte) 1));
    }

    @Test
    public void bishopsChallengeCorrectly1() {
        board.addPiece((byte) -3, "d5");
        assertTrue(check.isChallenged(board.getBoard(), "a2", (byte) 1));
        assertTrue(check.isChallenged(board.getBoard(), "g8", (byte) 1));
        assertTrue(check.isChallenged(board.getBoard(), "b7", (byte) 1));
        assertTrue(check.isChallenged(board.getBoard(), "h1", (byte) 1));
        assertFalse(check.isChallenged(board.getBoard(), "e7", (byte) 1));
    }
    
    @Test
    public void bishopsChallengeCorrectly2() {
        board.addPiece((byte) 3, "a1");
        assertTrue(check.isChallenged(board.getBoard(), "c3", (byte) -1));
        assertFalse(check.isChallenged(board.getBoard(), "e2", (byte) -1));
    }

    @Test
    public void bishopsCannotChallengeThroughPieces() {
        board.addPiece((byte) -3, "d5");
        board.addPiece((byte) 5, "e4");
        board.addPiece((byte) 1, "e6");
        board.addPiece((byte) 4, "c4");
        board.addPiece((byte) 3, "c6");
        assertFalse(check.isChallenged(board.getBoard(), "a2", (byte) 1));
        assertFalse(check.isChallenged(board.getBoard(), "g8", (byte) 1));
        assertFalse(check.isChallenged(board.getBoard(), "b7", (byte) 1));
        assertFalse(check.isChallenged(board.getBoard(), "h1", (byte) 1));
    }

    @Test
    public void queensChallengeCorrectly1() {
        board.addPiece((byte) -2, "c2");
        assertTrue(check.isChallenged(board.getBoard(), "h2", (byte) 1));
        assertTrue(check.isChallenged(board.getBoard(), "c8", (byte) 1));
        assertTrue(check.isChallenged(board.getBoard(), "a2", (byte) 1));
        assertTrue(check.isChallenged(board.getBoard(), "c1", (byte) 1));
        assertFalse(check.isChallenged(board.getBoard(), "e6", (byte) 1));
    }

    @Test
    public void queensCannotChallengeThroughPieces1() {
        board.addPiece((byte) -2, "c2");
        board.addPiece((byte) 1, "g2");
        board.addPiece((byte) 1, "c5");
        assertFalse(check.isChallenged(board.getBoard(), "h2", (byte) 1));
        assertFalse(check.isChallenged(board.getBoard(), "c8", (byte) 1));
    }

    @Test
    public void queensChallengeCorrectly2() {
        board.addPiece((byte) -2, "d5");
        assertTrue(check.isChallenged(board.getBoard(), "a2", (byte) 1));
        assertTrue(check.isChallenged(board.getBoard(), "g8", (byte) 1));
        assertTrue(check.isChallenged(board.getBoard(), "b7", (byte) 1));
        assertTrue(check.isChallenged(board.getBoard(), "h1", (byte) 1));
        assertFalse(check.isChallenged(board.getBoard(), "e7", (byte) 1));
    }

    @Test
    public void queensCannotChallengeThroughPieces2() {
        board.addPiece((byte) -2, "d5");
        board.addPiece((byte) 5, "e4");
        board.addPiece((byte) 1, "e6");
        board.addPiece((byte) 4, "c4");
        board.addPiece((byte) 3, "c6");
        assertFalse(check.isChallenged(board.getBoard(), "a2", (byte) 1));
        assertFalse(check.isChallenged(board.getBoard(), "g8", (byte) 1));
        assertFalse(check.isChallenged(board.getBoard(), "b7", (byte) 1));
        assertFalse(check.isChallenged(board.getBoard(), "h1", (byte) 1));
    }

    @Test
    public void kingChallengesCorrectly() {
        board.addPiece((byte) 1, "d4");
        assertTrue(check.isChallenged(board.getBoard(), "c3", (byte) -1));
        assertTrue(check.isChallenged(board.getBoard(), "c4", (byte) -1));
        assertTrue(check.isChallenged(board.getBoard(), "c5", (byte) -1));
        assertTrue(check.isChallenged(board.getBoard(), "d3", (byte) -1));
        assertTrue(check.isChallenged(board.getBoard(), "d5", (byte) -1));
        assertTrue(check.isChallenged(board.getBoard(), "e3", (byte) -1));
        assertTrue(check.isChallenged(board.getBoard(), "e4", (byte) -1));
        assertTrue(check.isChallenged(board.getBoard(), "e5", (byte) -1));

        assertFalse(check.isChallenged(board.getBoard(), "e6", (byte) -1));
        assertFalse(check.isChallenged(board.getBoard(), "b4", (byte) -1));
    }

}
