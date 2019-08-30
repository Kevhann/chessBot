/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import chessboard.Chessboard;
import chessboard.State;
import engine.Minimax;
import engine.MoveGenerator;
import testers.EngineTester;
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
//        game.runGame();
        test();
    }

 

    public static void test() {
      EngineTester test = new EngineTester();
      test.testAll();
    }

}
