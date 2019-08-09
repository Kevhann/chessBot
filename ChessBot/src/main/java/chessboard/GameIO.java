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
        while (true) {
            board.printBoard();
            if (turn == 1) {
                System.out.println("White to move, format b1,c3");
                if (check.isChallenged(board.getBoardState(), turn)) {
                    System.out.println("challallal");
                    System.out.println("White in check!");
                    if (gen.getAll((byte) 1).isEmpty())  {
                        System.out.println("Checkmate!!");
                    }
                }
                String move = scanner.nextLine();
                if (move.equals("")) {
                    break;
                }
                if (!move.matches("[a-h][1-8],[a-h][1-8]")) {
                    System.out.println("input moves in correct format: a2,c3");
                } else {
                    currentMove = new Move(move);
                    try {
                        board.move(currentMove, turn);
                        turn *= -1;
                    } catch (IllegalMoveException e) {
                        System.out.println(e);
                    }
                }
            } else if (turn == -1) {
                System.out.println("Black to move");
                if (check.isChallenged(board.getBoardState(), turn)) {
                    System.out.println("Black in check!");
                    
                }
                
                ArrayList<State> states = gen.getAll(turn);

                if (states.isEmpty()) {
                    System.out.println("Checkmate!!");
                }
                Collections.sort(states);                
                board.cpMove(states.get(0).move, (byte) -1);
                turn *= -1;
            }

        }
    }
}
