package testers;

import chessboard.Chessboard;
import chessboard.State;
import engine.Minimax;
import engine.MoveChecker;
import engine.MoveGenerator;
import java.util.Arrays;
import java.util.Random;
import structures.CustomList;
import utils.Move;

/**
 * Class to test the performance of generating moves
 *
 * @author kevin
 */
public class EngineTester {

    private Chessboard board = new Chessboard();
    private MoveGenerator gen = new MoveGenerator(board);
    private MoveChecker checker = new MoveChecker(board);
    private Minimax minimax = new Minimax(gen);

    public EngineTester() {

    }

    public void testAll() {
        addPieces();
        checkMoveValidity();
        generateMovesAndGetMedian();
        minimaxTurnsDepthOne();
    }

    /**
     * Generates random moves for white in the predetermined board state. Checks
     * the moves and returns the median time.
     */
    public void checkMoveValidity() {
        int n = 10000000;
        int legal = 0;
        int illegal = 0;
        long[] times = new long[n];
        Random rand = new Random(17);
        for (int i = 0; i < n; i++) {
            int fromRank = rand.nextInt(3);
            int fromFile = rand.nextInt(8);
            int toRank = rand.nextInt(8);
            int toFile = rand.nextInt(8);
            Move move = new Move((byte) fromRank, (byte) fromFile, (byte) toRank, (byte) toFile);
            byte piecetype = board.pieceOnBoard(fromRank, fromFile);

            long time = System.nanoTime();
            boolean res = checker.checkAll(piecetype, move, (byte) 1);
            times[i] = System.nanoTime() - time;
            if (res) {
                legal++;
            } else {
                illegal++;
            }

        }
        Arrays.sort(times);
        double mLegal = times[n - (legal / 2)] / 1000.0;
        System.out.println("Median time to check a random legal move was: " + mLegal + "µs");
        System.out.println("Of the generated moves " + legal + " were legal, and " + illegal + " were illegal");
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
        for (int i = 0; i < n; i++) {
            long time = System.nanoTime();
            minimax.minimaxTurn(1, (byte) 1);
            times[i] = System.nanoTime() - time;
        }
        Arrays.sort(times);
        double median = times[n / 2] / 1000000.0;
        System.out.println("Median time to generate moves with minimax depth 1 was: " + median + "ms");
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
}
