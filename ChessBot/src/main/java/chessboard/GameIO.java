/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessboard;

import utils.IllegalMoveException;
import java.util.Scanner;
import utils.Colour;

/**
 *
 * @author kevin
 */
public class GameIO {

    Chessboard chessBoard = new Chessboard();
    Scanner scanner = new Scanner(System.in);
    Move currentMove;
    Colour turn = Colour.WHITE;

    public void runGame() {
        chessBoard.initializeBoard();
        chessBoard.addPieces();
        while (true) {
            chessBoard.printBoard();
            System.out.println(turn.getColour() + " to move, format b1,c3");
            String move = scanner.nextLine();
            if (move.equals("")) {
                break;
            }
            if (!move.matches("[a-h][1-8],[a-h][1-8]")) {
                System.out.println("input moves in correct format: a2,c3");
            } else {
                currentMove = new Move(move);
                try {
                    chessBoard.move(currentMove, turn);
                    turn = turn == Colour.WHITE ? Colour.BLACK : Colour.WHITE;
                } catch (IllegalMoveException e) {
                    System.out.println(e);
                }
            }

        }
    }
}
