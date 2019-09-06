package io;

import chessboard.Chessboard;
import engine.Minimax;
import engine.MoveGenerator;

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
        game.start();
    }

 



}
