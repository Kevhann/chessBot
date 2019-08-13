/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.chessbot;

import chessboard.Check;
import chessboard.Chessboard;
import chessboard.GameIO;
import chessboard.Move;
import chessboard.State;
import java.util.ArrayList;
import engine.MoveChecker;
import engine.MoveGenerator;
import utils.IllegalMoveException;
import utils.Position;

/**
 *
 * @author kevin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

       GameIO game = new GameIO();
       game.runGame();
//        test();

    }

    public static void test() {
        Chessboard board = new Chessboard();
        MoveGenerator gen = new MoveGenerator(board);
        Check c = new Check();

//        board.addPieces();
        board.addPiece((byte) -1, "g6");
        board.addPiece((byte) 5, "a5");

        board.setBlackKing(new Position("g6"));

        System.out.println("incheck g6: " + c.isChallenged(board.getBoard(), "g6", (byte) -1));
        System.out.println("incheck g5: " + c.isChallenged(board.getBoard(), "g5", (byte) -1));
        System.out.println("incheck g4: " + c.isChallenged(board.getBoard(), "g4", (byte) -1));
        System.out.println("incheck f5: " + c.isChallenged(board.getBoard(), "f5", (byte) -1));
        System.out.println("incheck h5: " + c.isChallenged(board.getBoard(), "h5", (byte) -1));
        ArrayList<State> states = new ArrayList();
        states = gen.getAll((byte) -1);

        board.printBoard();

        System.out.println("----Moves here-----");
        System.out.println("moves: " + states.size());

        for (int i = 0; i < states.size(); i++) {
            System.out.println("Score: " + states.get(i).getScore());
            board.printState(states.get(i).board);
            System.out.println("---");
            System.out.println(states.get(i).move);
            System.out.println("----");
        }
        System.out.println("-------------");

    }

    public static int trans(String s) {
        return 1;
    }
}
