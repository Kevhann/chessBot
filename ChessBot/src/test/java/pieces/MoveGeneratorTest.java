/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

import engine.MoveChecker;
import engine.MoveGenerator;
import chessboard.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.Position;

/**
 *
 * @author kevin
 */
public class MoveGeneratorTest {

    static Chessboard board = new Chessboard();
    MoveChecker checker = new MoveChecker(board);
    MoveGenerator gen = new MoveGenerator(board);

    public MoveGeneratorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
        board.clearBoard();
    }

    @Test
    public void white20MovesAtStart() {
        board.addPieces();
        assertEquals(20, gen.getAll((byte) 1).size());
    }
    
    @Test
    public void black20MovesAtStart() {
        board.addPieces();
        assertEquals(20, gen.getAll((byte) -1).size());
    }

    @Test
    public void knightInCornerHasTwoMoves() {
        board.addPiece((byte) 4, "a1");
        assertEquals(2, gen.getAll((byte) 1).size());
    }

    @Test
    public void knightInCenterHas8Moves() {
        board.addPiece((byte) 4, "d6");
        assertEquals(8, gen.getAll((byte) 1).size());
    }

    @Test
    public void kingInCornerHas3Moves() {
        board.addPiece((byte) -1, "a1");
        assertEquals(3, gen.getAll((byte) -1).size());
    }

    @Test
    public void kingInCenterHas8Moves() {
        board.addPiece((byte) -1, "d6");
        assertEquals(8, gen.getAll((byte) -1).size());
    }
    
    @Test
    public void kingCannotMoveIntoChallengedSquares1() {
        board.addPiece((byte) -1, "d6");
        board.addPiece((byte) 6, "d4");
        assertEquals(6, gen.getAll((byte) -1).size());
    }
    @Test
    public void kingCannotMoveIntoChallengedSquares2() {
        board.addPiece((byte) -1, "d3");
        board.setBlackKing(new Position("d3"));
        board.addPiece((byte) 6, "c3");
        board.addPiece((byte) 6, "e3");
        board.addPiece((byte) 6, "d2");
        System.out.println("whiteking: " + board.getWhiteKing());
        assertEquals(5, gen.getAll((byte) -1).size());
    }
    
    @Test
    public void kingCannotMoveIntoChallengedSquares3() {
        board.addPiece((byte) -1, "d3");
        board.setBlackKing(new Position("d3"));
        board.addPiece((byte) 5, "h2");
        board.addPiece((byte) 5, "h4");
        assertEquals(2, gen.getAll((byte) -1).size());
    }
    
    @Test
    public void kingCannotMoveIntoChallengedSquares4() {
        board.addPiece((byte) 1, "d3");
        board.setWhiteKing(new Position("d3"));
        board.addPiece((byte) -5, "e8");
        board.addPiece((byte) -5, "c8");
        assertEquals(2, gen.getAll((byte) 1).size());
    }

    @Test
    public void pawnsInStartingPositionHaveTwoMoves() {
        board.addPiece((byte) 6, "a2");
        assertEquals(2, gen.getAll((byte) 1).size());
    }
    
    @Test
    public void checkmateReturnsZeroMoves1() {
        board.addPiece((byte) 5, "e7");
        board.addPiece((byte) 5, "f5");
        board.addPiece((byte) 4, "c4");
        board.addPiece((byte) -1, "c6");
        board.addPiece((byte) -2, "d2");
        assertEquals(0, gen.getAll((byte) -1).size());
    }

    @Test
    public void capturingWhitePawnHas3Moves() {
        board.addPiece((byte) 6, "d6");
        assertEquals(1, gen.getAll((byte) 1).size());
        board.addPiece((byte) -4, "e7");
        assertEquals(2, gen.getAll((byte) 1).size());
        board.addPiece((byte) -4, "c7");
        assertEquals(3, gen.getAll((byte) 1).size());
    }
    
    @Test
    public void capturingBlackPawnHas3Moves() {
        board.addPiece((byte) -6, "d5");
        assertEquals(1, gen.getAll((byte) -1).size());
        board.addPiece((byte) 4, "e4");
        assertEquals(2, gen.getAll((byte) -1).size());
        board.addPiece((byte) 4, "c4");
        assertEquals(3, gen.getAll((byte) -1).size());
    }
    
    @Test
    public void queenHas27MovesFromCentre() {
        board.addPiece((byte) -2, "d5");
        assertEquals(27, gen.getAll((byte) -1).size());
    }

}
