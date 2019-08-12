/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import utils.IllegalMoveException;
import java.util.Scanner;
import pieces.MoveGenerator;

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

    public void runGame() {
        board.addPieces();
        System.out.println("Choose game mode:");
        System.out.println("1 for player (white) vs cpu (black)");
        System.out.println("2 for cpu vs cpu");
        System.out.println("3 for player vs player");

        String input = scanner.nextLine();

        System.out.println("Black king pos: " + board.getBlackKing());
        System.out.println("White king pos: " + board.getWhiteKing());

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
            board.printBoard();
            if (turn == 1) {
                if (humanTurn(turn)) {
                    break;
                }
            } else if (turn == -1) {
                if (cpuTurn(turn)) {
                    break;
                }
            }

        }
    }

    public void gameMode2() {
        while (true) {
            System.out.println("-------------");
            board.printBoard();
            System.out.println("Black king pos: " + board.getBlackKing());
            System.out.println("White king pos: " + board.getWhiteKing());
            System.out.println("-------------");

            if (turn == 1) {
                if (cpuTurn(turn)) {
                    break;
                }
            } else if (turn == -1) {
                if (cpuTurn(turn)) {
                    break;
                }
            }

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
            System.out.println("Black to move");
        } else {
            System.out.println("White to move");
        }

        if (check.isChallenged(board.getBoardState(), side)) {
            System.out.println("Check!");
            System.out.println("Black king pos: " + board.getBlackKing());
            System.out.println("White king pos: " + board.getWhiteKing());
            System.out.println("kingpos: " + board.kingPos(side));
        }

        ArrayList<State> states = gen.getAll(side);
        Collections.shuffle(states);

        if (states.isEmpty()) {
            System.out.println("Checkmate!");
            if (side == 1) {
                System.out.println("Black won!");
            } else {
                System.out.println("White won!");
            }
            return true;
        }

        Collections.sort(states);
        if (turn == 1) {
            board.cpMove(states.get(0).move, (byte) -1);
        } else {
            board.cpMove(states.get(states.size() - 1).move, (byte) -1);
        }
        turn *= -1;
        return false;
    }

    public boolean humanTurn(Byte side) {
        board.printBoard();
        if (turn == 1) {
            System.out.println("White to move, format b1,c3");
        } else {
            System.out.println("Black to move, format b1,c3");
        }

        if (check.isChallenged(board.getBoardState(), side)) {
            System.out.println("Check!");
            System.out.println("Black king pos: " + board.getBlackKing());
            System.out.println("White king pos: " + board.getWhiteKing());
            System.out.println("kingpos: " + board.kingPos(side));
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
