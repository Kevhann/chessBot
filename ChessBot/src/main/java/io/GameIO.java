package io;

import engine.Check;
import chessboard.Chessboard;
import utils.Move;
import chessboard.State;
import engine.Minimax;
import java.util.Scanner;
import engine.MoveGenerator;
import testers.EngineTester;

/**
 *
 * @author kevin
 */
public class GameIO {

    private final String[] files = {"   ", " a ", " b ", " c ", " d ", " e ", " f ", " g ", " h "};
    private final String[] symbols = {" ♟ ", " ♜ ", " ♞ ", " ♝ ", " ♛ ", " ♚ ", "   ", " ♔ ", " ♕ ", " ♗ ", " ♘ ", " ♖ ", " ♙ "};
    public Chessboard board = new Chessboard();
    Scanner scanner = new Scanner(System.in);
    Move currentMove;
    byte turn = 1;
    public MoveGenerator gen = new MoveGenerator(board);
    Check check = new Check();
    public Minimax minimax = new Minimax(gen);
    int depth = 4;
    int count = 1;

    public void start() {
        System.out.println("Choose to play game ('g') or to run performance tests ('t')");
        String input = scanner.nextLine();
        switch (input) {
            case "g":
                runGame();
                break;
            case "t":
                test();
                break;
            default:
                System.out.println("Goodbye");
        }
    }

    public void runGame() {
        board.addPieces();
        System.out.println("Choose game mode:");
        System.out.println("1 for player (white) vs cpu (black)");
        System.out.println("2 for cpu vs cpu");
        System.out.println("3 for player vs player");

        String input = scanner.nextLine();

        switch (input) {
            case "1":
                gameMode1();
                break;
            case "2":
                gameMode2();
                break;
            case "3":
                gameMode3();
                break;
            default:
                System.out.println("Goodbye");
        }
    }

    public void gameMode1() {
        System.out.println("Choose computer 'level' 0-5 (depth of search)");
        String input = scanner.nextLine();

        if (!input.matches("[0-5]")) {
            System.out.println("playing with default 'level 4");
        } else {
            depth = Integer.parseInt(input);
        }

        while (true) {
            System.out.println("---- Player Turn ----");
            if (humanTurn()) {
                break;
            }
            System.out.println("---- Computer Turn ----");
            if (cpuTurn()) {
                break;
            }

        }
    }

    public void gameMode2() {
        System.out.println("Choose computer 'level' 0-5 (depth of search)");
        String input = scanner.nextLine();

        if (!input.matches("[0-5]")) {
            System.out.println("playing with default 'level 4");
        } else {
            depth = Integer.parseInt(input);
        }
        while (true) {
            System.out.println("------ Move " + count + " -------");
            count++;
            if (cpuTurn()) {
                break;
            }
        }
    }

    public void gameMode3() {
        while (true) {
            printBoard();
            if (humanTurn()) {
                break;
            }
        }
    }

    public boolean cpuTurn() {

        if (turn == -1) {
            System.out.println("  -- Black to move --");
        } else {
            System.out.println("  -- White to move --");
        }
        printBoard();

        if (check.isChallenged(board.getBoardState(), turn)) {
            System.out.println("Check!");
            System.out.println("kingpos: " + board.getKingPos(turn));
        }
        if (board.piecesOnBoard() < 3) {
            return true;
        }

        State move = minimax.minimaxTurn(depth, turn);

        if (move == null) {
            System.out.println("Checkmate!");
            if (turn == 1) {
                System.out.println("Black won!");
            } else {
                System.out.println("White won!");
            }
            return true;
        }

        board.cpMove(move);
        turn *= -1;
        return false;
    }

    /**
     * The function that calls the minimax algorithm
     *
     * @param side
     * @param depth
     * @return
     */
    /**
     * The method handling human turns
     *
     * @return true if the game has ended, false if not
     */
    public boolean humanTurn() {
        if (turn == 1) {
            System.out.println("-- White to move, format b1,c3 --");
        } else {
            System.out.println("-- Black to move, format b1,c3 --");
        }

        printBoard();

        if (check.isChallenged(board.getBoardState(), turn)) {
            System.out.println("Check!");
            System.out.println("kingpos: " + board.getKingPos(turn));
            if (gen.getAll((byte) turn).isEmpty()) {
                System.out.println("Checkmate!!");
                System.out.println("Black Won");
                return true;
            }
        }
        while (true) {
            String move = scanner.nextLine();
            if (move.equals("q")) {
                return true;
            }
            if (!move.matches("[a-h][1-8],[a-h][1-8]")) {
                System.out.println("input moves in correct format: a2,c3");
            } else {
                currentMove = new Move(move);
                try {
                    board.move(currentMove, turn);
                    turn *= -1;
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e);
                }

            }
        }
        return false;
    }

    /**
     * Print the current state of the board before each turn
     */
    public void printBoard() {
        byte[] currentBoard = board.getBoard();
        int score = board.getScore();
        System.out.println("Score: " + score);
        for (int i = 7; i >= 0; i--) {
            System.out.print(i + 1 + "  ");
            for (int j = 0; j < 8; j++) {
                System.out.print(symbols[currentBoard[(8 * i) + j] + 6]);
            }
            System.out.println("");
        }
        for (int i = 0; i < 9; i++) {
            System.out.print(files[i]);
        }
        System.out.println("");
    }

    /**
     * Used to print the visual representation of the board Used for testing
     * purposes
     *
     * @param state the state to print out
     */
    public void printState(byte[] state) {
        System.out.println("");
        for (int i = 7; i >= 0; i--) {
            System.out.print(i + 1 + "  ");
            for (int j = 0; j < 8; j++) {
                System.out.print(symbols[state[(8 * i) + j] + 6]);
            }
            System.out.println("");
        }
        for (int i = 0; i < 9; i++) {
            System.out.print(files[i]);
        }
        System.out.println("");
    }

    public static void test() {
        EngineTester test = new EngineTester();
        test.testAll();
    }

}
