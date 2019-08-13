/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessboard;

import engine.Minimax;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import utils.IllegalMoveException;
import java.util.Scanner;
import engine.MoveGenerator;

/**
 *
 * @author kevin
 */
public class GameIO {

    Chessboard board = new Chessboard();
    Scanner scanner = new Scanner(System.in);
    Move currentMove;
    byte turn = 1;
    MoveGenerator gen = new MoveGenerator(board);
    Check check = new Check();
    Minimax minimax = new Minimax(gen, 2);

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
                System.out.println("Incorrect choice");
        }
    }

    public void gameMode1() {
        while (true) {
            System.out.println("---- Player Turn ----");
            if (humanTurn(turn)) {
                break;
            }
            System.out.println("---- Computer Turn ----");
            if (cpuTurn(turn)) {
                break;
            }

        }
    }

    public void gameMode2() {
        while (true) {
            System.out.println("-------------");

            if (cpuTurn(turn)) {
                break;
            }
            System.out.println("-------------");

        }
    }

    public void gameMode3() {
        while (true) {
            board.printBoard();
            if (turn == 1) {
                if (humanTurn(turn)) {
                    break;
                }
            } else if (turn == -1) {
                if (humanTurn(turn)) {
                    break;
                }
            }

        }
    }

    public boolean cpuTurn(Byte side) {

        if (side == -1) {
            System.out.println("  -- Black to move --");
        } else {
            System.out.println("  -- White to move --");
        }
        board.printBoard();

        if (check.isChallenged(board.getBoardState(), side)) {
            System.out.println("Check!");
            System.out.println("kingpos: " + board.getKingPos(side));
        }
        if (board.piecesOnBoard() < 3) {
            return true;
        }

        State move = generateTurn(side);

        if (move == null) {
            return true;
        }

        board.cpMove(move);
        turn *= -1;
        return false;
    }

    public State generateTurn(byte side) {
        ArrayList<State> states = gen.getAll(side);
        Collections.shuffle(states);

        if (states.isEmpty()) {
            System.out.println("Checkmate!");
            if (side == 1) {
                System.out.println("Black won!");
            } else {
                System.out.println("White won!");
            }
            return null;
        }

        Collections.sort(states);
        if (side == 1) {
            return states.get(0);
        } else {
            return states.get(states.size() - 1);
        }

    }
    
//    public State minimaxTurn(byte turn, int depth) {
//        minimax.minimax(state, true, depth)
//    }

    public boolean humanTurn(Byte side) {
        if (turn == 1) {
            System.out.println("-- White to move, format b1,c3 --");
        } else {
            System.out.println("-- Black to move, format b1,c3 --");
        }

        board.printBoard();

        if (check.isChallenged(board.getBoardState(), side)) {
            System.out.println("Check!");
            System.out.println("kingpos: " + board.getKingPos(side));
            if (gen.getAll((byte) side).isEmpty()) {
                System.out.println("Checkmate!!");
                System.out.println("Black Won");
                return true;
            }
        }
        String move = scanner.nextLine();
        if (move.equals("q")) {
            return true;
        }
        if (!move.matches("[a-h][1-8],[a-h][1-8]")) {
            System.out.println("input moves in correct format: a2,c3");
        } else {
            currentMove = new Move(move);
            try {
                board.move(currentMove, side);
                turn *= -1;
            } catch (IllegalMoveException e) {
                System.out.println(e);
            }

        }
        return false;
    }

}
