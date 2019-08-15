/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import chessboard.State;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author kevin
 */
public class Minimax {

    private int depth;
    MoveGenerator gen;

    public Minimax(MoveGenerator g, int d) {
        this.depth = d;
        this.gen = g;
    }

    /**
     * Depth-limited search algorithm using alpha-beta pruning
     *
     * @param state The state for which minimax finds a score
     * @param white True for white, false for black
     * @param depth the desired depth or number of turns minimax searches for
     * @param alpha alpha cutoff value. Negative infinity for initial call
     * @param beta beta cutoff value. Infinity for initial call
     * @return the minimax value
     */
    public int minimax(State state, boolean white, int depth, int alpha, int beta) {
        System.out.println("minimax depth: " + depth);
        System.out.println("minimax state: ");
        state.printState();
        System.out.println(" - - -");
        
        if (depth == 0) {
            int value = state.evaluate();
            System.out.println("minimax value: " + value);
            return value;
        } 
        
        ArrayList<State> states;
        
        if (white) {
            states = gen.getAll((byte) 1);
        } else {
            states = gen.getAll((byte) -1);
        }
        
        for (int i = 0; i < states.size(); i++) {
            System.out.println(" - - - ");
            states.get(i).printState();
            System.out.println(" - - - ");
        }
        
        if (states.isEmpty()) {
            if (white) {
                return 99999;
            } else {
                return -99999;
            }
        }

        if (white) {
            int base = -9999;

            int size = states.size();
            for (int i = 0; i < size; i++) {
                int value = minimax(states.get(i), false, depth - 1, alpha, beta);
                if (value > base) {
                    base = value;
                }
                if (value > alpha) {
                    alpha = value;
                }
                if (alpha >= beta) {
                    break;
                }
            }
            return base;
        } else {
            int base = 9999;
            int size = states.size();
            for (int i = 0; i < size; i++) {
                int value = minimax(states.get(i), true, depth - 1, alpha, beta);
                if (value < base) {
                    base = value;
                }
                if (value < beta) {
                    beta = value;
                }
                if (alpha >= beta) {
                    break;
                }
            }
            return base;
        }

    }
}
