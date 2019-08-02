/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessTests;

import chessboard.*;
import pieces.*;
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

        Move legalWhiteMove = new Move("g7,g6");
        Move legalBlackMove = new Move("b2,b3");

        Move illegalWhiteMove1 = new Move("g6,g7");
        Move illegalBlackMove1 = new Move("b3,c3");

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
        Move wJump1 = new Move("h7,h5");
        Move wJump2 = new Move("h5,h3");
        Move bJump1 = new Move("b2,b4");
        Move bJump2 = new Move("b4,b6");

        assertEquals(MoveType.VALID, wPawn.isLegalMove(wJump1));
        assertEquals(MoveType.VALID, bPawn.isLegalMove(bJump1));
        
        wPawn.incrementMoveCounter();
        bPawn.incrementMoveCounter();

        assertEquals(MoveType.ILLEGAL, wPawn.isLegalMove(wJump2));
        assertEquals(MoveType.ILLEGAL, bPawn.isLegalMove(bJump2));

    }
    @Test
    public void pawnsCanMoveDiagonallyWhenCapturing() {
        
        Move whiteCaptures = new Move("b7,c6");
        Move blackCaptures = new Move("c6,b7");
        
        board.addPiece(wPawn, "b7");
        board.addPiece(bPawn,"c6");
        
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
}
