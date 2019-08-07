/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

import utils.MoveType;
import utils.Colour;
import chessboard.Chessboard;
import chessboard.Move;
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
public class PieceTest {

    static Chessboard board = new Chessboard();
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

    public PieceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
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
    public void pawnsCanMoveOnlyInCorrectDirection() {

        Move legalWhiteMove = new Move("g2,g3");
        Move legalBlackMove = new Move("b7,b6");

        Move illegalWhiteMove1 = new Move("g3,g2");
        Move illegalBlackMove1 = new Move("b6,b7");

        Move illegalWhiteMove2 = new Move("g6,h6");
        Move illegalBlackMove2 = new Move("b3,a3");

        Move illegalWhiteMove3 = new Move("g6,f6");
        Move illegalBlackMove3 = new Move("b3,a3");

        assertEquals(MoveType.VALID, wPawn.isLegalMove(legalWhiteMove));
        assertEquals(MoveType.VALID, bPawn.isLegalMove(legalBlackMove));

        assertEquals(MoveType.ILLEGAL, wPawn.isLegalMove(illegalWhiteMove1));
        assertEquals(MoveType.ILLEGAL, bPawn.isLegalMove(illegalBlackMove1));

        assertEquals(MoveType.ILLEGAL, wPawn.isLegalMove(illegalWhiteMove2));
        assertEquals(MoveType.ILLEGAL, bPawn.isLegalMove(illegalBlackMove2));
        assertEquals(MoveType.ILLEGAL, wPawn.isLegalMove(illegalWhiteMove3));
        assertEquals(MoveType.ILLEGAL, bPawn.isLegalMove(illegalBlackMove3));
    }

    @Test
    public void pawnsCanMoveTwoSquaresOnFirstMoveOnly() {
        Move wJump1 = new Move("h2,h4");
        Move wJump2 = new Move("h4,h6");
        Move bJump1 = new Move("b7,b5");
        Move bJump2 = new Move("b5,b3");

        assertEquals(MoveType.VALID, wPawn.isLegalMove(wJump1));
        assertEquals(MoveType.VALID, bPawn.isLegalMove(bJump1));

        wPawn.incrementMoveCounter();
        bPawn.incrementMoveCounter();

        assertEquals(MoveType.ILLEGAL, wPawn.isLegalMove(wJump2));
        assertEquals(MoveType.ILLEGAL, bPawn.isLegalMove(bJump2));

    }

    @Test
    public void pawnsCanMoveDiagonallyWhenCapturing() {

        Move whiteCaptures = new Move("b3,c4");
        Move blackCaptures = new Move("c7,b6");

        board.addPiece(wPawn, "b6");
        board.addPiece(bPawn, "c4");

        assertEquals(MoveType.CAPTURE, wPawn.isLegalMove(whiteCaptures));
        assertEquals(MoveType.CAPTURE, bPawn.isLegalMove(blackCaptures));
    }

    @Test
    public void rooksCanMoveHorizontally() {
        Move h1 = new Move("a1,a8");
        Move h2 = new Move("b5,b1");

        assertEquals(MoveType.VALID, wRook.isLegalMove(h1));
        assertEquals(MoveType.VALID, wRook.isLegalMove(h2));
    }

    @Test
    public void rooksCanMoveVertically() {
        Move v1 = new Move("a1,f1");
        Move v2 = new Move("g5,b5");

        assertEquals(MoveType.VALID, wRook.isLegalMove(v1));
        assertEquals(MoveType.VALID, wRook.isLegalMove(v2));
    }

    @Test
    public void rooksCannotMoveDiagonally() {
        Move d1 = new Move("a1,c3");
        Move d2 = new Move("g5,b1");

        assertEquals(MoveType.ILLEGAL, wRook.isLegalMove(d1));
        assertEquals(MoveType.ILLEGAL, wRook.isLegalMove(d2));
    }

    @Test
    public void rooksCannotMoveOverPieces() {
        Move j1 = new Move("a1,f1");
        Move j2 = new Move("b2,b7");
        board.addPiece(wPawn, "d1");
        board.addPiece(bPawn, "b4");

        assertEquals(MoveType.ILLEGAL, wRook.isLegalMove(j1));
        assertEquals(MoveType.ILLEGAL, wRook.isLegalMove(j2));
    }

    @Test
    public void knightsCanMoveOverPieces() {
        Move m1 = new Move("a1,b3");
        Move m2 = new Move("c2,a3");
        board.addPiece(wPawn, "a2");
        board.addPiece(bPawn, "b2");

        assertEquals(MoveType.VALID, wKnight.isLegalMove(m1));
        assertEquals(MoveType.VALID, bKnight.isLegalMove(m2));
    }

    @Test
    public void bishopsCanMoveDiagonally() {
        Move m1 = new Move("a1,d4");
        Move m2 = new Move("h2,b8");

        assertEquals(MoveType.VALID, wBishop.isLegalMove(m1));
        assertEquals(MoveType.VALID, bBishop.isLegalMove(m2));
    }

    @Test
    public void bishopsCannotMoveVerticallyOrHorizontally() {
        Move m1 = new Move("a1,a3");
        Move m2 = new Move("c2,h2");

        assertEquals(MoveType.ILLEGAL, wBishop.isLegalMove(m1));
        assertEquals(MoveType.ILLEGAL, bBishop.isLegalMove(m2));
    }

    @Test
    public void bishopsCannotMoveOverPieces() {
        Move m1 = new Move("a1,d4");
        Move m2 = new Move("c8,a6");
        board.addPiece(wPawn, "b2");
        board.addPiece(bPawn, "b7");
        assertEquals(MoveType.ILLEGAL, wBishop.isLegalMove(m1));
        assertEquals(MoveType.ILLEGAL, bBishop.isLegalMove(m2));
    }
    
    @Test
    public void queensCanMoveHorizontally() {
        Move h1 = new Move("a1,a8");
        Move h2 = new Move("b5,b1");

        assertEquals(MoveType.VALID, wQueen.isLegalMove(h1));
        assertEquals(MoveType.VALID, wQueen.isLegalMove(h2));
    }

    @Test
    public void queensCanMoveVertically() {
        Move v1 = new Move("a1,f1");
        Move v2 = new Move("g5,b5");

        assertEquals(MoveType.VALID, wQueen.isLegalMove(v1));
        assertEquals(MoveType.VALID, wQueen.isLegalMove(v2));
    }

    @Test
    public void queensCannotMoveOverPieces() {
        Move j1 = new Move("a1,f1");
        Move j2 = new Move("b2,b7");
        board.addPiece(wPawn, "d1");
        board.addPiece(bPawn, "b4");

        assertEquals(MoveType.ILLEGAL, wQueen.isLegalMove(j1));
        assertEquals(MoveType.ILLEGAL, wQueen.isLegalMove(j2));
    }

    @Test
    public void queensCanMoveDiagonally() {
        Move m1 = new Move("a1,d4");
        Move m2 = new Move("h2,b8");

        assertEquals(MoveType.VALID, wQueen.isLegalMove(m1));
        assertEquals(MoveType.VALID, bQueen.isLegalMove(m2));
    }

    @Test
    public void queensCannotMoveOverPiecesDiagonally() {
        Move m1 = new Move("a1,d4");
        Move m2 = new Move("c8,a6");
        board.addPiece(wPawn, "b2");
        board.addPiece(bPawn, "b7");
        assertEquals(MoveType.ILLEGAL, wQueen.isLegalMove(m1));
        assertEquals(MoveType.ILLEGAL, bQueen.isLegalMove(m2));
    }
    
    @Test
    public void queensCannotMoveIllegally() {
        Move m1 = new Move("a1,d3");
        Move m2 = new Move("c7,a6");
        assertEquals(MoveType.ILLEGAL, wQueen.isLegalMove(m1));
        assertEquals(MoveType.ILLEGAL, bQueen.isLegalMove(m2));
    }
    
    @Test
    public void kingsCannotMoveOverOneSpace() {
        Move m1 = new Move("a3,a1");
        Move m2 = new Move("c7,a7");
        assertEquals(MoveType.ILLEGAL, wKing.isLegalMove(m1));
        assertEquals(MoveType.ILLEGAL, bKing.isLegalMove(m2));
    }
    @Test
    public void kingsCanMoveOneSpace(){
        Move m1 = new Move("a1,b2");
        Move m2 = new Move("c7,b7");
        assertEquals(MoveType.VALID, wKing.isLegalMove(m1));
        assertEquals(MoveType.VALID, bKing.isLegalMove(m2));
    }
}
