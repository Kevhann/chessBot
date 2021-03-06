package engine;

import utils.Move;
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
public class MoveCheckerTest {

    static Chessboard board = new Chessboard();
    MoveChecker checker = new MoveChecker(board);

    public MoveCheckerTest() {
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
    public void pawnsCanMoveOnlyInCorrectDirection() {

        Move legalWhiteMove = new Move("g2,g3");
        Move legalBlackMove = new Move("b7,b6");

        Move illegalWhiteMove1 = new Move("g3,g2");
        Move illegalBlackMove1 = new Move("b6,b7");

        Move illegalWhiteMove2 = new Move("g6,h6");
        Move illegalBlackMove2 = new Move("b3,a3");

        Move illegalWhiteMove3 = new Move("g6,f6");
        Move illegalBlackMove3 = new Move("b3,a3");

        assertTrue(checker.pawn(legalWhiteMove, (byte) 1));
        assertTrue(checker.pawn(legalBlackMove, (byte) -1));

        assertFalse(checker.pawn(illegalWhiteMove1, (byte) 1));
        assertFalse(checker.pawn(illegalBlackMove1, (byte) -1));

        assertFalse(checker.pawn(illegalWhiteMove2, (byte) 1));
        assertFalse(checker.pawn(illegalBlackMove2, (byte) -1));
        assertFalse(checker.pawn(illegalWhiteMove3, (byte) 1));
        assertFalse(checker.pawn(illegalBlackMove3, (byte) -1));
    }

    @Test
    public void pawnsCanMoveTwoSquaresOnFirstMoveOnly() {
        Move wJump1 = new Move("h2,h4");
        Move wJump2 = new Move("h4,h6");
        Move bJump1 = new Move("b7,b5");
        Move bJump2 = new Move("b5,b3");

        assertTrue(checker.pawn(wJump1, (byte) 1));
        assertTrue(checker.pawn(bJump1, (byte) -1));

        assertFalse(checker.pawn(wJump2, (byte) 1));
        assertFalse(checker.pawn(bJump2, (byte) -1));

    }

    @Test
    public void pawnsCanMoveDiagonallyWhenCapturing() {

        Move whiteCaptures = new Move("b3,c4");
        Move blackCaptures = new Move("c7,b6");

        board.addPiece((byte) 1, "b6");
        board.addPiece((byte) -1, "c4");

        assertTrue(checker.pawn(whiteCaptures, (byte) 1));
        assertTrue(checker.pawn(blackCaptures, (byte) -1));

    }
    
    @Test
    public void pawnsCannotJumpOverPieces() {

        Move m1 = new Move("b2,b4");
        Move m2 = new Move("c7,c5");

        board.addPiece((byte) 1, "b3");
        board.addPiece((byte) 1, "c6");

        assertFalse(checker.pawn(m1, (byte) 1));
        assertFalse(checker.pawn(m2, (byte) -1));

    }

    @Test
    public void rooksCanMoveHorizontally() {
        Move h1 = new Move("a1,a8");
        Move h2 = new Move("b5,b1");

        assertTrue(checker.rook(h1, (byte) 1));
        assertTrue(checker.rook(h2, (byte) -1));

    }

    @Test
    public void rooksCanMoveVertically() {
        Move v1 = new Move("a1,f1");
        Move v2 = new Move("g5,b5");

        assertTrue(checker.rook(v1, (byte) 1));
        assertTrue(checker.rook(v2, (byte) -1));
    }

    @Test
    public void rooksCannotMoveDiagonally() {
        Move d1 = new Move("a1,c3");
        Move d2 = new Move("g5,b1");

        assertFalse(checker.rook(d1, (byte) 1));
        assertFalse(checker.rook(d2, (byte) -1));
    }

    @Test
    public void rooksCannotMoveOverPieces() {
        Move j1 = new Move("a1,f1");
        Move j2 = new Move("b2,b7");
        board.addPiece((byte) 1, "d1");
        board.addPiece((byte) -1, "b4");

        assertFalse(checker.rook(j1, (byte) 1));
        assertFalse(checker.rook(j2, (byte) -1));
    }

    @Test
    public void knightsCanMoveOverPieces() {
        Move m1 = new Move("a1,b3");
        Move m2 = new Move("c2,a3");
        board.addPiece((byte) 1, "a2");
        board.addPiece((byte) -1, "b2");

        assertTrue(checker.knight(m1, (byte) 1));
        assertTrue(checker.knight(m2, (byte) -1));
    }

    @Test
    public void bishopsCanMoveDiagonally() {
        Move m1 = new Move("a1,d4");
        Move m2 = new Move("h2,b8");

        assertTrue(checker.bishop(m1, (byte) 1));
        assertTrue(checker.bishop(m2, (byte) -1));

    }

    @Test
    public void bishopsCannotMoveVerticallyOrHorizontally() {
        Move m1 = new Move("a1,a3");
        Move m2 = new Move("c2,h2");

        assertFalse(checker.bishop(m1, (byte) 1));
        assertFalse(checker.bishop(m2, (byte) -1));
    }

    @Test
    public void bishopsCannotMoveOverPieces() {
        Move m1 = new Move("a1,d4");
        Move m2 = new Move("c8,a6");
        board.addPiece((byte) 1, "b2");
        board.addPiece((byte) -1, "b7");

        assertFalse(checker.bishop(m1, (byte) 1));
        assertFalse(checker.bishop(m2, (byte) -1));
    }

    @Test
    public void queensCanMoveHorizontally() {
        Move h1 = new Move("a1,a8");
        Move h2 = new Move("b5,b1");

        assertTrue(checker.queen(h1, (byte) 1));
        assertTrue(checker.queen(h2, (byte) -1));
    }

    @Test
    public void queensCanMoveVertically() {
        Move v1 = new Move("a1,f1");
        Move v2 = new Move("g5,b5");

        assertTrue(checker.queen(v1, (byte) 1));
        assertTrue(checker.queen(v2, (byte) -1));
    }

    @Test
    public void queensCannotMoveOverPieces() {
        Move j1 = new Move("a1,f1");
        Move j2 = new Move("b2,b7");
        board.addPiece((byte) 1, "d1");
        board.addPiece((byte) -1, "b4");

        assertFalse(checker.queen(j1, (byte) 1));
        assertFalse(checker.queen(j2, (byte) -1));
    }

    @Test
    public void queensCanMoveDiagonally() {
        Move m1 = new Move("a1,d4");
        Move m2 = new Move("h2,b8");

        assertTrue(checker.queen(m1, (byte) 1));
        assertTrue(checker.queen(m2, (byte) -1));
    }

    @Test
    public void queensCannotMoveOverPiecesDiagonally() {
        Move m1 = new Move("a1,d4");
        Move m2 = new Move("c8,a6");

        board.addPiece((byte) 1, "b2");
        board.addPiece((byte) -1, "b7");

        assertFalse(checker.queen(m1, (byte) 1));
        assertFalse(checker.queen(m2, (byte) -1));
    }

    @Test
    public void queensCannotMoveIllegally() {
        Move m1 = new Move("a1,d3");
        Move m2 = new Move("c7,a6");

        assertFalse(checker.queen(m1, (byte) 1));
        assertFalse(checker.queen(m2, (byte) -1));
    }

    @Test
    public void kingsCannotMoveOverOneSpace() {
        Move m1 = new Move("a3,a1");
        Move m2 = new Move("c7,a7");

        assertFalse(checker.king(m1, (byte) 1));
        assertFalse(checker.king(m2, (byte) -1));
    }

    @Test
    public void kingsCanMoveOneSpace() {
        Move m1 = new Move("a1,b2");
        Move m2 = new Move("c7,b7");

        assertTrue(checker.king(m1, (byte) 1));
        assertTrue(checker.king(m2, (byte) -1));
    }
}
