/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.chessbot;

import chessboard.Chessboard;
import chessboard.GameIO;

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
    }
}
