/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.chessbot;

import chessboard.Check;
import chessboard.Chessboard;
import io.GameIO;
import utils.Move;
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

    static GameIO game = new GameIO();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        game.runGame();
    }

   
}
