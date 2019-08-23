/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import chessboard.Chessboard;
import io.GameIO;
import chessboard.State;
import engine.Minimax;
import engine.MoveGenerator;
import utils.Position;

/**
 *
 * @author kevin
 */
public class Main {

    static GameIO game = new GameIO();
    static Chessboard board = game.board;
    static Minimax minimax = game.minimax;
    static MoveGenerator gen = game.gen;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        game.runGame();
//        test();
    }

    public static void test2() {
        board.addPiece((byte) 5, "f1");
        board.addPiece((byte) 5, "a5");
        board.addPiece((byte) 6, "f4");
        board.addPiece((byte) 2, "d5");
        board.addPiece((byte) 1, "b1");

        board.addPiece((byte) -1, "h8");
        board.addPiece((byte) -6, "h6");
        board.addPiece((byte) -3, "g7");
        board.addPiece((byte) -2, "c3");
        board.setBlackKing(new Position("h8"));
        board.setWhiteKing(new Position("b1"));

        game.printBoard();

//        ArrayList<State> s = gen.getAll((byte) 1);
//        System.out.println("moves: " + s.size());
//
//        for (State state : s) {
//            game.printState(state.board);
//        }
//
        State next = minimax.minimaxTurn(2, (byte) -1);
        Position location = new Position("c4");
        game.printState(next.getBoard());
        int loc = location.getPos();

    }

    public static void test() {
        board.addPiece((byte) 5, "a6");
        board.addPiece((byte) 6, "h2");
        board.addPiece((byte) 6, "g2");
        board.addPiece((byte) 6, "e4");
        board.addPiece((byte) 6, "a5");
        board.addPiece((byte) 2, "d5");
        board.addPiece((byte) 4, "f7");
        board.addPiece((byte) 1, "h1");

        board.addPiece((byte) -1, "g8");
        board.addPiece((byte) -6, "d6");
        board.addPiece((byte) -6, "g7");
        board.addPiece((byte) -6, "h7");
        board.addPiece((byte) -4, "b4");
        board.addPiece((byte) -2, "g4");
        board.addPiece((byte) -5, "c8");

        board.setBlackKing(new Position("g8"));
        board.setWhiteKing(new Position("h1"));

        game.printBoard();

        State next = minimax.minimaxTurn(4, (byte) 1);
        Position location = new Position("b2");
        game.printState(next.getBoard());
        int loc = location.getPos();

//        while(!game.cpuTurn()){
//            ;
//        }
    }

}
