/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testers;

import chessboard.Chessboard;
import chessboard.State;
import engine.Minimax;
import engine.MoveGenerator;
import java.util.Arrays;
import structures.CustomList;

/**
 * Class to test the performance of generating moves
 *
 * @author kevin
 */
public class EngineTester {

    private Chessboard board = new Chessboard();
    private MoveGenerator gen = new MoveGenerator(board);
    private Minimax minimax = new Minimax(gen);

    public EngineTester() {

    }
    
    public void testAll() {
        addPieces();
        generateMovesAndGetMedian();
        minimaxTurnsDepthOne();
    }

    /**
     * Adds pieces to the board for testing purposes. Both white and black have
     * 51 legal moves from this position. Absolute number of moves is 52 but one
     * king move is blocked by opposing queen.
     */
    public void addPieces() {
        board.addPiece((byte) 1, "e1");
        board.addPiece((byte) -1, "e8");
        board.addPiece((byte) 2, "d1");
        board.addPiece((byte) -2, "d8");

        board.addPiece((byte) 3, "f1");
        board.addPiece((byte) 3, "c1");
        board.addPiece((byte) 4, "b1");
        board.addPiece((byte) 4, "g1");
        board.addPiece((byte) 5, "a1");
        board.addPiece((byte) 5, "h1");

        board.addPiece((byte) -3, "f8");
        board.addPiece((byte) -3, "c8");
        board.addPiece((byte) -4, "b8");
        board.addPiece((byte) -4, "g8");
        board.addPiece((byte) -5, "a8");
        board.addPiece((byte) -5, "h8");

        board.addPiece((byte) 6, "b2");
        board.addPiece((byte) 6, "f2");
        board.addPiece((byte) -6, "b7");
        board.addPiece((byte) -6, "f7");

        board.getBoardState().printState();
    }

    public void generateMovesAndGetMedian() {
        int n = 100000;
        long[] times = new long[n];
        for (int i = 0; i < n; i++) {
            long time = System.nanoTime();
            gen.getAll((byte) -1);
            times[i] = System.nanoTime() - time;
        }
        Arrays.sort(times);
        double median = times[n / 2] / 1000000.0;
        System.out.println("Median time to generate moves was: " + median + "ms");
    }
    
    public void minimaxTurnsDepthOne() {
        int n = 10000;
        long[] times = new long[n];
        CustomList<State> list;
        for (int i = 0; i < n; i++) {
            long time = System.nanoTime();
            minimax.minimaxTurn(1, (byte) 1);
            times[i] = System.nanoTime() - time;
        }
        Arrays.sort(times);
        double median = times[n / 2] / 1000000.0;
        System.out.println("Median time to generate moves was: " + median + "ms");
    }
}
