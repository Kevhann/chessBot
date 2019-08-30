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
import utils.Position;

/**
 *
 * @author kevin
 */
public class StateTest {

    State state;

    public StateTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        state = new State(new byte[64]);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void calculateCorrectStateScore1() {
        state.setPiece(2, 2, (byte) 6); // 10
        state.setPiece(2, 3, (byte) 5); // 50
        state.setPiece(3, 2, (byte) 4); // 30
        state.setPiece(4, 1, (byte) 3); // 30
        state.setPiece(4, 4, (byte) 2); // 90
        state.setPiece(4, 6, (byte) 1); // 900

        assertEquals(1110, state.evaluate());
    }

    @Test
    public void calculateCorrectStateScore2() {
        state.setPiece(2, 2, (byte) -6); // -10
        state.setPiece(2, 3, (byte) -5); // -50
        state.setPiece(3, 2, (byte) -4); // -30
        state.setPiece(4, 1, (byte) -3); // -30
        state.setPiece(4, 4, (byte) -2); // -90
        state.setPiece(4, 6, (byte) -1); // -900

        assertEquals(-1110, state.evaluate());
    }

    @Test
    public void calculateCorrectStateScore3() {
        state.setPiece(2, 2, (byte) 6); // 10
        state.setPiece(2, 3, (byte) 5); // 50
        state.setPiece(3, 2, (byte) 4); // 30
        state.setPiece(4, 1, (byte) 3); // 30
        state.setPiece(4, 4, (byte) 2); // 90
        state.setPiece(4, 6, (byte) 1); // 900
        state.setPiece(1, 6, (byte) -1); // -900
        state.setPiece(1, 3, (byte) -5); // -50

        assertEquals(160, state.evaluate());
    }
    
    @Test
    public void createStateFromState() {
        
        state.setBlackKing(new Position("d1"));
        state.setWhiteKing(new Position("c4"));
        State newState = new State(state);
        
        assertEquals(3, newState.getBlackKing().getPos());
        assertEquals(26, newState.getWhiteKing().getPos());
    }
    
}
