/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessboard;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
public class ChessBoardTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    static Chessboard board = new Chessboard();

    public ChessBoardTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        board.addPieces();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);

    }

    @Test
    public void chessBoardHasCorrectDimensions() {
        assertEquals(64, board.getBoard().length);
    }

    @Test
    public void chessBoardIsInitializedCorrectly() {
        String expected = "\n8   ♜  ♞  ♝  ♛  ♚  ♝  ♞  ♜ \n"
                + "7   ♟  ♟  ♟  ♟  ♟  ♟  ♟  ♟ \n"
                + "6                          \n"
                + "5                          \n"
                + "4                          \n"
                + "3                          \n"
                + "2   ♙  ♙  ♙  ♙  ♙  ♙  ♙  ♙ \n"
                + "1   ♖  ♘  ♗  ♕  ♔  ♗  ♘  ♖ \n"
                + "    a  b  c  d  e  f  g  h \n";
        board.printBoard();
        assertEquals(expected, outContent.toString());
    }

}
