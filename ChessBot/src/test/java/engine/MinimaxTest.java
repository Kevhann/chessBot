/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import chessboard.Chessboard;
import chessboard.State;
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
public class MinimaxTest {

    static Chessboard board = new Chessboard();
    MoveChecker checker = new MoveChecker(board);
    MoveGenerator gen = new MoveGenerator(board);
    Minimax minimax = new Minimax(gen);

    public MinimaxTest() {
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
    public void willAlwaysCaptureWithDepthZero1() {
        board.addPiece((byte) 4, "d6");
        board.addPiece((byte) -2, "d1");
        State next = minimax.minimaxTurn(0, (byte) -1);
        Position location = new Position("d6");
        int loc = location.getPos();
        assertEquals(-2, next.getPiece(loc));
    }

    @Test
    public void willAlwaysCaptureWithDepthZero2() {
        board.addPiece((byte) 4, "d6");
        board.addPiece((byte) -2, "c4");
        State next = minimax.minimaxTurn(0, (byte) 1);
        Position location = new Position("c4");
        int loc = location.getPos();
        assertEquals(4, next.getPiece(loc));
    }

    @Test(timeout=5000)
    public void whiteWillFindMateInFive() {
        //8         ♜           ♚    
        //7                  ♘  ♟  ♟ 
        //6   ♖        ♟             
        //5   ♙        ♕             
        //4      ♞        ♙     ♛    
        //3                          
        //2                     ♙  ♙ 
        //1                        ♔ 
        //    a  b  c  d  e  f  g  h 
        board.addPiece((byte) 5, "a6");
        board.addPiece((byte) 6, "h2");
        board.addPiece((byte) 6, "g2");
        board.addPiece((byte) 6, "e4");
        board.addPiece((byte) 6, "a5");
        board.addPiece((byte) 2, "d5");
        board.addPiece((byte) 4, "f7");
        board.addPiece((byte) 1, "h1");

        board.addPiece((byte) -1, "g8");
        board.addPiece((byte) -6, "d6");
        board.addPiece((byte) -6, "g7");
        board.addPiece((byte) -6, "h7");
        board.addPiece((byte) -4, "b4");
        board.addPiece((byte) -2, "g4");
        board.addPiece((byte) -5, "c8");

        board.setBlackKing(new Position("g8"));
        board.setWhiteKing(new Position("h1"));

        State next = minimax.minimaxTurn(5, (byte) 1);

        assertEquals(99999, next.getScore());
    }

}
