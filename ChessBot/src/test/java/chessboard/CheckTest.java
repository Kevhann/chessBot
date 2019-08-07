/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessboard;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;
import utils.Colour;

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
    Check check = new Check(board);
    Pawn wPawn = new Pawn(Colour.WHITE, board);
    Pawn bPawn = new Pawn(Colour.BLACK, board);
    Rook wRook = new Rook(Colour.WHITE, board);
    Rook bRook = new Rook(Colour.BLACK, board);
    Bishop wBishop = new Bishop(Colour.WHITE, board);
    Bishop bBishop = new Bishop(Colour.BLACK, board);
    Queen wQueen = new Queen(Colour.WHITE, board);
    Queen bQueen = new Queen(Colour.BLACK, board);
    Knight wKnight = new Knight(Colour.WHITE, board);
    Knight bKnight = new Knight(Colour.BLACK, board);
    King wKing = new King(Colour.WHITE, board);
    King bKing = new King(Colour.BLACK, board);

    public CheckTest() {
    }

    @Before
    public void setUp() {
        board.initializeBoard();

    }

    @After
    public void tearDown() {
        board.clearBoard();
    }

    @Test
    public void returnsFalseWithEmptyBoard() {
        assertFalse(check.isChallenged(0, 0, Colour.WHITE));
        assertFalse(check.isChallenged(0, 0, Colour.BLACK));
    }

    @Test
    public void whitePawnsChallengeCorrectly() {
        board.addPiece(wPawn, "d2");
        assertTrue(check.isChallenged("e3", Colour.BLACK));
        assertTrue(check.isChallenged("c3", Colour.BLACK));
        assertFalse(check.isChallenged("d3", Colour.BLACK));
        assertFalse(check.isChallenged("e1", Colour.BLACK));
    }

    @Test
    public void blackPawnsChallengeCorrectly() {
        board.addPiece(bPawn, "d2");
        assertTrue(check.isChallenged("e1", Colour.WHITE));
        assertTrue(check.isChallenged("c1", Colour.WHITE));
        assertFalse(check.isChallenged("d1", Colour.WHITE));
        assertFalse(check.isChallenged("e3", Colour.WHITE));
    }

    @Test
    public void pawnsDontChallengeSameColour() {
        board.addPiece(wPawn, "d2");
        assertFalse(check.isChallenged("e3", Colour.WHITE));
    }

    @Test
    public void knightsChallengeCorrectly() {
        board.addPiece(wKnight, "d4");
        assertTrue(check.isChallenged("e6", Colour.BLACK));
        assertTrue(check.isChallenged("e2", Colour.BLACK));
        assertTrue(check.isChallenged("f5", Colour.BLACK));
        assertTrue(check.isChallenged("f3", Colour.BLACK));
        assertTrue(check.isChallenged("c6", Colour.BLACK));
        assertTrue(check.isChallenged("c2", Colour.BLACK));
        assertTrue(check.isChallenged("b5", Colour.BLACK));
        assertTrue(check.isChallenged("b3", Colour.BLACK));
    }

    @Test
    public void rooksChallengeCorrectly() {
        board.addPiece(bRook, "c2");
        assertTrue(check.isChallenged("h2", Colour.WHITE));
        assertTrue(check.isChallenged("c8", Colour.WHITE));
        assertTrue(check.isChallenged("a2", Colour.WHITE));
        assertTrue(check.isChallenged("c1", Colour.WHITE));
        assertFalse(check.isChallenged("e6", Colour.WHITE));
    }

    @Test
    public void rooksCannotChallengeThroughPieces() {
        board.addPiece(bRook, "c2");
        board.addPiece(wPawn, "g2");
        board.addPiece(wPawn, "c5");
        assertFalse(check.isChallenged("h2", Colour.WHITE));
        assertFalse(check.isChallenged("c8", Colour.WHITE));
    }

    @Test
    public void bishopsChallengeCorrectly() {
        board.addPiece(bBishop, "d5");
        assertTrue(check.isChallenged("a2", Colour.WHITE));
        assertTrue(check.isChallenged("g8", Colour.WHITE));
        assertTrue(check.isChallenged("b7", Colour.WHITE));
        assertTrue(check.isChallenged("h1", Colour.WHITE));
        assertFalse(check.isChallenged("e7", Colour.WHITE));
    }

    @Test
    public void bishopsCannotChallengeThroughPieces() {
        board.addPiece(bBishop, "d5");
        board.addPiece(wRook, "e4");
        board.addPiece(wPawn, "e6");
        board.addPiece(wKnight, "c4");
        board.addPiece(wBishop, "c6");
        assertFalse(check.isChallenged("a2", Colour.WHITE));
        assertFalse(check.isChallenged("g8", Colour.WHITE));
        assertFalse(check.isChallenged("b7", Colour.WHITE));
        assertFalse(check.isChallenged("h1", Colour.WHITE));
    }

    @Test
    public void queensChallengeCorrectly1() {
        board.addPiece(bQueen, "c2");
        assertTrue(check.isChallenged("h2", Colour.WHITE));
        assertTrue(check.isChallenged("c8", Colour.WHITE));
        assertTrue(check.isChallenged("a2", Colour.WHITE));
        assertTrue(check.isChallenged("c1", Colour.WHITE));
        assertFalse(check.isChallenged("e6", Colour.WHITE));
    }

    @Test
    public void queensCannotChallengeThroughPieces1() {
        board.addPiece(bQueen, "c2");
        board.addPiece(wPawn, "g2");
        board.addPiece(wPawn, "c5");
        assertFalse(check.isChallenged("h2", Colour.WHITE));
        assertFalse(check.isChallenged("c8", Colour.WHITE));
    }

    @Test
    public void queensChallengeCorrectly2() {
        board.addPiece(bQueen, "d5");
        assertTrue(check.isChallenged("a2", Colour.WHITE));
        assertTrue(check.isChallenged("g8", Colour.WHITE));
        assertTrue(check.isChallenged("b7", Colour.WHITE));
        assertTrue(check.isChallenged("h1", Colour.WHITE));
        assertFalse(check.isChallenged("e7", Colour.WHITE));
    }

    @Test
    public void queensCannotChallengeThroughPieces2() {
        board.addPiece(bQueen, "d5");
        board.addPiece(wRook, "e4");
        board.addPiece(wPawn, "e6");
        board.addPiece(wKnight, "c4");
        board.addPiece(wBishop, "c6");
        assertFalse(check.isChallenged("a2", Colour.WHITE));
        assertFalse(check.isChallenged("g8", Colour.WHITE));
        assertFalse(check.isChallenged("b7", Colour.WHITE));
        assertFalse(check.isChallenged("h1", Colour.WHITE));
    }

    @Test
    public void kingChallengesCorrectly() {
        board.addPiece(wKing, "d4");
        assertTrue(check.isChallenged("c3", Colour.BLACK));
        assertTrue(check.isChallenged("c4", Colour.BLACK));
        assertTrue(check.isChallenged("c5", Colour.BLACK));
        assertTrue(check.isChallenged("d3", Colour.BLACK));
        assertTrue(check.isChallenged("d5", Colour.BLACK));
        assertTrue(check.isChallenged("e3", Colour.BLACK));
        assertTrue(check.isChallenged("e4", Colour.BLACK));
        assertTrue(check.isChallenged("e5", Colour.BLACK));
        
        assertFalse(check.isChallenged("e6", Colour.BLACK));
        assertFalse(check.isChallenged("b4", Colour.BLACK));
    }
}
