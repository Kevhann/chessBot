package engine;

import chessboard.State;
import structures.CustomList;

/**
 *
 * @author kevin
 */
public class Minimax {

    MoveGenerator gen;

    public Minimax(MoveGenerator g) {
        this.gen = g;
    }

    /**
     * Evaluates the best move using minimax to a given depth and returns it
     *
     * @param depth the depth of the search, 0 for only next turn, ++ for deeper search
     * @param side 1 for white, -1 for black
     * @return The best move evaluated with minimax
     */
    public State minimaxTurn(int depth, byte side) {
        CustomList<State> states = gen.getAll(side);
        
        if (states.isEmpty()) {
            return null;
        }

        states.shuffle();
        
        int min = 99999;
        int mini = 0;
        int max = -99999;
        int maxi = 0;

        for (int i = 0; i < states.length(); i++) {
            State temp = states.get(i);
            int score = minimax(temp, side == -1, depth, -99999, 99999);
            temp.setScore(score);
            if (score < min) {
                min = score;
                mini = i;
            }

            if (score > max) {
                max = score;
                maxi = i;
            }
        }

        if (side == 1) {
            return states.get(maxi);
        } else {
            return states.get(mini);
        }
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
    private int minimax(State state, boolean white, int depth, int alpha, int beta) {
        if (depth == 0) {
            int value = state.evaluate();
            return value;
        }
        CustomList<State> states;

        if (white) {
            states = gen.getAll(state, (byte) 1);
        } else {
            states = gen.getAll(state, (byte) -1);
        }

        if (states.isEmpty()) {
            if (white) {
                return -99999;
            } else {
                return 99999;
            }
        }

        if (white) {
            int base = -99999;

            int size = states.length();
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
            int base = 99999;
            int size = states.length();
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
