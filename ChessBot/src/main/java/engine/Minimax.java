/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import chessboard.State;
import java.util.ArrayList;

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

    public State minimax(State state, boolean white, int depth) {
        ArrayList<State> states;
        if (white) {
            states = gen.getAll((byte) 1);
        } else {
            states = gen.getAll((byte) -1);
        }
        
        if (depth == 0 || states.size() == 0) {
            return state;
        } else {
            if (white) {
                State base = states.get(0);
                int size = states.size();
                for (int i = 1; i < size; i++) {
                    State newState = minimax(states.get(i), false, depth - 1);
                    if (base.getScore()  < newState.getScore()) {
                        base = newState;
                    }
                }
                return base;
            } else {
                State base = states.get(0);
                int size = states.size();
                for (int i = 1; i < size; i++) {
                    State newState = minimax(states.get(i), false, depth - 1);
                    if (base.getScore()  > newState.getScore()) {
                        base = newState;
                    }
                }
                return base;
            }
        }
    }
}
