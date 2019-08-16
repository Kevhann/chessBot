/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import chessboard.*;
import java.util.ArrayList;
import java.util.Collections;
import utils.Func;
import utils.*;

/**
 *
 * @author kevin
 */
public class MoveGenerator {

    Chessboard board;
    Check check;
    boolean inCheck = false;
    State current;

    Func func = new Func();

    public MoveGenerator(Chessboard board) {
        this.board = board;
        this.check = new Check();
    }

    public State getBestNextMove(byte side) {
        ArrayList<State> states = getAll(side);

        Collections.shuffle(states);
        Collections.sort(states);
        if (side == 1) {
            return states.get(0);
        } else {
            return states.get(states.size() - 1);
        }
    }

    /**
     * Generates all legal moves for the current state of the game
     * @param side side of player
     * @return list of all legal moves
     */
    public ArrayList<State> getAll(byte side) {
        this.current = board.getBoardState();
        ArrayList<State> allStates = new ArrayList();
        for (int i = 0; i < 64; i++) {
            byte piece = current.getPiece(i);
            if (func.sign(piece) == side) {

                int rank = i / 8;
                int file = i - (rank * 8);

                ArrayList<State> states = pieceSwitch(piece, rank, file, side);
                allStates.addAll(states);
            }
        }
        return removeStatesInCheck(allStates, side);
    }

    /**
     * Generates all legal moves for a given state of the game
     * @param side side of player
     * @return list of all legal moves
     */
    public ArrayList<State> getAll(State state, byte side) {
        ArrayList<State> allStates = new ArrayList();
        this.current = state;

        for (int i = 0; i < 64; i++) {
            byte piece = state.getPiece(i);
            if (func.sign(piece) == side) {

                int rank = i / 8;
                int file = i - (rank * 8);

                ArrayList<State> states = pieceSwitch(piece, rank, file, side);
                allStates.addAll(states);
            }
        }
        return removeStatesInCheck(allStates, side);

    }

    public ArrayList<State> removeStatesInCheck(ArrayList<State> allStates, byte side) {
        int size = allStates.size();
        for (int i = size - 1; i >= 0; i--) {
            State s = allStates.get(i);

            if (check.isChallenged(s, side)) {
                allStates.remove(i);
            }
        }
        return allStates;
    }

    /**
     * Switch to the correct function based on the type of piece
     *
     * @param piece the signed (white/black) piece 1-6
     * @param rank the rank of the piece 0-7
     * @param file the file of the piece 0-7
     * @param side the side of the player (1 for white, -1 for black)
     * @return A list of possible moves for the piece
     */
    public ArrayList<State> pieceSwitch(byte piece, int rank, int file, byte side) {

        ArrayList<State> states = new ArrayList();
        int type = side * piece;
        switch (type) {
            case 1:
                return king(rank, file, side);
            case 2:
                return queen(rank, file, side);
            case 3:
                return bishop(rank, file, side);
            case 4:
                return knight(rank, file, side);
            case 5:
                return rook(rank, file, side);
            case 6:
                return pawn(rank, file, side);
        }
        return states;
    }

    public ArrayList<State> bishop(int rank, int file, byte side) {
        ArrayList<State> states = new ArrayList();
        State state;
        byte piece;
        for (int i = rank - 1, j = file - 1; i >= 0 && j >= 0; i--, j--) {
            piece = current.getPiece(i, j);
            state = new State(current);
            if (piece == 0) {
                state.setState((byte) rank, (byte) file, (byte) i, (byte) j, (byte) (side * 3));
                states.add(state);
            } else if (piece * side < 0) {
                state.setState((byte) rank, (byte) file, (byte) i, (byte) j, (byte) (side * 3));
                states.add(state);
                break;
            } else {
                break;
            }
        }
        for (int i = rank + 1, j = file - 1; i < 8 && j >= 0; i++, j--) {
            piece = current.getPiece(i, j);
            state = new State(current);

            if (piece == 0) {
                state.setState((byte) rank, (byte) file, (byte) i, (byte) j, (byte) (side * 3));
                states.add(state);
            } else if (piece * side < 0) {
                state.setState((byte) rank, (byte) file, (byte) i, (byte) j, (byte) (side * 3));
                states.add(state);
                break;
            } else {
                break;
            }
        }
        for (int i = rank - 1, j = file + 1; i >= 0 && j < 8; i--, j++) {
            piece = current.getPiece(i, j);
            state = new State(current);

            if (piece == 0) {
                state.setState((byte) rank, (byte) file, (byte) i, (byte) j, (byte) (side * 3));
                states.add(state);
            } else if (piece * side < 0) {
                state.setState((byte) rank, (byte) file, (byte) i, (byte) j, (byte) (side * 3));
                states.add(state);
                break;
            } else {
                break;
            }
        }
        for (int i = rank + 1, j = file + 1; i < 8 && j < 8; i++, j++) {
            piece = current.getPiece(i, j);
            state = new State(current);

            if (piece == 0) {
                state.setState((byte) rank, (byte) file, (byte) i, (byte) j, (byte) (side * 3));
                states.add(state);
            } else if (piece * side < 0) {
                state.setState((byte) rank, (byte) file, (byte) i, (byte) j, (byte) (side * 3));
                states.add(state);
                break;
            } else {
                break;
            }
        }

        return states;
    }

    public ArrayList<State> king(int rank, int file, byte side) {
        ArrayList<State> states = new ArrayList();
        State state;
        byte piece;
        if (rank > 0) {
            piece = current.getPiece(rank - 1, file);
            if (piece * side <= 0) {
                state = new State(current);
                state.setState((byte) rank, (byte) file, (byte) (rank - 1), (byte) file, (byte) side);
                if (!check.isChallenged(state.board, rank - 1, file, side)) {
                    state.setKingPos(side, new Position((byte) (rank - 1), (byte) (file)));
                    states.add(state);

                }
            }
            if (file > 0) {
                piece = current.getPiece(rank - 1, file - 1);
                if (piece * side <= 0) {
                    state = new State(current);
                    state.setState((byte) rank, (byte) file, (byte) (rank - 1), (byte) (file - 1), (byte) side);
                    if (!check.isChallenged(state.board, rank - 1, file - 1, side)) {
                        state.setKingPos(side, new Position((byte) (rank - 1), (byte) (file - 1)));
                        states.add(state);
                    }
                }
            }
            if (file < 7) {
                piece = current.getPiece(rank - 1, file + 1);
                if (piece * side <= 0) {
                    state = new State(current);
                    state.setState((byte) rank, (byte) file, (byte) (rank - 1), (byte) (file + 1), (byte) side);
                    if (!check.isChallenged(state.board, rank - 1, file + 1, side)) {
                        state.setKingPos(side, new Position((byte) (rank - 1), (byte) (file + 1)));
                        states.add(state);
                    }
                }
            }
        }
        if (rank < 7) {
            piece = current.getPiece(rank + 1, file);
            if (piece * side <= 0) {
                state = new State(current);
                state.setState((byte) rank, (byte) file, (byte) (rank + 1), (byte) file, (byte) side);
                if (!check.isChallenged(state.board, rank + 1, file, side)) {
                    state.setKingPos(side, new Position((byte) (rank + 1), (byte) (file)));
                    states.add(state);
                }
            }
            if (file > 0) {
                piece = current.getPiece(rank + 1, file - 1);
                if (piece * side <= 0) {
                    state = new State(current);
                    state.setState((byte) rank, (byte) file, (byte) (rank + 1), (byte) (file - 1), (byte) side);
                    if (!check.isChallenged(state.board, rank + 1, file - 1, side)) {
                        state.setKingPos(side, new Position((byte) (rank + 1), (byte) (file - 1)));
                        states.add(state);
                    }
                }
            }
            if (file < 7) {
                piece = current.getPiece(rank + 1, file + 1);
                if (piece * side <= 0) {
                    state = new State(current);
                    state.setState((byte) rank, (byte) file, (byte) (rank + 1), (byte) (file + 1), (byte) side);
                    if (!check.isChallenged(state.board, rank + 1, file + 1, side)) {
                        state.setKingPos(side, new Position((byte) (rank + 1), (byte) (file + 1)));
                        states.add(state);
                    }
                }
            }
        }
        if (file > 0) {
            piece = current.getPiece(rank, file - 1);
            if (piece * side <= 0) {
                state = new State(current);
                state.setState((byte) rank, (byte) file, (byte) rank, (byte) (file - 1), (byte) side);
                if (!check.isChallenged(state.board, rank, file - 1, side)) {
                    state.setKingPos(side, new Position((byte) (rank), (byte) (file - 1)));
                    states.add(state);
                }
            }
        }
        if (file < 7) {
            piece = current.getPiece(rank, file + 1);
            if (piece * side <= 0) {
                state = new State(current);
                state.setState((byte) rank, (byte) file, (byte) rank, (byte) (file + 1), (byte) side);
                if (!check.isChallenged(state.board, rank, file + 1, side)) {
                    state.setKingPos(side, new Position((byte) (rank), (byte) (file + 1)));
                    states.add(state);
                }
            }
        }
//        } else if (dRank == 0 && dFile == 2) {
//            if (!check.isChallenged(fromRank, fromFile, side)
//                    && !check.isChallenged(fromRank, fromFile + dir, side)
//                    && !check.isChallenged(dRank, toFile, side)) {
//                return type.CASTLE;
//            }
        return states;
    }

    public ArrayList<State> knight(int rank, int file, byte side) {
        ArrayList<State> states = new ArrayList();
        State state;
        byte piece;

        if (func.isContained(rank + 1, file + 2)) {
            if (current.getPiece(rank + 1, file + 2) * side <= 0) {
                state = new State(current);
                state.setState((byte) rank, (byte) file, (byte) (rank + 1), (byte) (file + 2), (byte) (side * 4));
                states.add(state);
            }
        }

        if (func.isContained(rank - 1, file + 2)) {
            if (current.getPiece(rank - 1, file + 2) * side <= 0) {
                state = new State(current);
                state.setState((byte) rank, (byte) file, (byte) (rank - 1), (byte) (file + 2), (byte) (side * 4));
                states.add(state);
            }
        }

        if (func.isContained(rank + 1, file - 2)) {
            if (current.getPiece(rank + 1, file - 2) * side <= 0) {
                state = new State(current);
                state.setState((byte) rank, (byte) file, (byte) (rank + 1), (byte) (file - 2), (byte) (side * 4));
                states.add(state);
            }
        }

        if (func.isContained(rank - 1, file - 2)) {
            if (current.getPiece(rank - 1, file - 2) * side <= 0) {
                state = new State(current);
                state.setState((byte) rank, (byte) file, (byte) (rank - 1), (byte) (file - 2), (byte) (side * 4));
                states.add(state);
            }
        }

        if (func.isContained(rank + 2, file + 1)) {
            if (current.getPiece(rank + 2, file + 1) * side <= 0) {
                state = new State(current);
                state.setState((byte) rank, (byte) file, (byte) (rank + 2), (byte) (file + 1), (byte) (side * 4));
                states.add(state);
            }
        }

        if (func.isContained(rank - 2, file + 1)) {
            if (current.getPiece(rank - 2, file + 1) * side <= 0) {
                state = new State(current);
                state.setState((byte) rank, (byte) file, (byte) (rank - 2), (byte) (file + 1), (byte) (side * 4));
                states.add(state);
            }
        }

        if (func.isContained(rank + 2, file - 1)) {
            if (current.getPiece(rank + 2, file - 1) * side <= 0) {
                state = new State(current);
                state.setState((byte) rank, (byte) file, (byte) (rank + 2), (byte) (file - 1), (byte) (side * 4));
                states.add(state);
            }
        }

        if (func.isContained(rank - 2, file - 1)) {
            if (current.getPiece(rank - 2, file - 1) * side <= 0) {
                state = new State(current);
                state.setState((byte) rank, (byte) file, (byte) (rank - 2), (byte) (file - 1), (byte) (side * 4));
                states.add(state);
            }
        }

        return states;
    }

    public ArrayList<State> rook(int rank, int file, byte side) {
        ArrayList<State> states = new ArrayList();
        State state;
        byte piece;

        for (int i = rank - 1; i >= 0; i--) {
            piece = current.getPiece(i, file);
            state = new State(current);
            if (piece == 0) {
                state.setState((byte) rank, (byte) file, (byte) i, (byte) file, (byte) (side * 5));
                states.add(state);
            } else if (piece * side < 0) {
                state.setState((byte) rank, (byte) file, (byte) i, (byte) file, (byte) (side * 5));
                states.add(state);
                break;
            } else {
                break;
            }
        }
        for (int i = file - 1; i >= 0; i--) {
            piece = current.getPiece(rank, i);
            state = new State(current);
            if (piece == 0) {
                state.setState((byte) rank, (byte) file, (byte) rank, (byte) i, (byte) (side * 5));
                states.add(state);
            } else if (piece * side < 0) {
                state.setState((byte) rank, (byte) file, (byte) rank, (byte) i, (byte) (side * 5));
                states.add(state);
                break;
            } else {
                break;
            }
        }
        for (int i = rank + 1; i < 8; i++) {
            piece = current.getPiece(i, file);
            state = new State(current);
            if (piece == 0) {
                state.setState((byte) rank, (byte) file, (byte) i, (byte) file, (byte) (side * 5));
                states.add(state);
            } else if (piece * side < 0) {
                state.setState((byte) rank, (byte) file, (byte) i, (byte) file, (byte) (side * 5));
                states.add(state);
                break;
            } else {
                break;
            }
        }
        for (int i = file + 1; i < 8; i++) {
            piece = current.getPiece(rank, i);
            state = new State(current);
            if (piece == 0) {
                state.setState((byte) rank, (byte) file, (byte) rank, (byte) i, (byte) (side * 5));
                states.add(state);
            } else if (piece * side < 0) {
                state.setState((byte) rank, (byte) file, (byte) rank, (byte) i, (byte) (side * 5));
                states.add(state);
                break;
            } else {
                break;
            }
        }
        return states;
    }

    public ArrayList<State> queen(int rank, int file, byte side) {
        ArrayList<State> states = new ArrayList();
        State state;
        byte piece;
        for (int i = rank - 1, j = file - 1; i >= 0 && j >= 0; i--, j--) {
            piece = current.getPiece(i, j);
            state = new State(current);
            if (piece == 0) {
                state.setState((byte) rank, (byte) file, (byte) i, (byte) j, (byte) (side * 2));
                states.add(state);
            } else if (piece * side < 0) {
                state.setState((byte) rank, (byte) file, (byte) i, (byte) j, (byte) (side * 2));
                states.add(state);
                break;
            } else {
                break;
            }
        }
        for (int i = rank + 1, j = file - 1; i < 8 && j >= 0; i++, j--) {
            piece = current.getPiece(i, j);
            state = new State(current);

            if (piece == 0) {
                state.setState((byte) rank, (byte) file, (byte) i, (byte) j, (byte) (side * 2));
                states.add(state);
            } else if (piece * side < 0) {
                state.setState((byte) rank, (byte) file, (byte) i, (byte) j, (byte) (side * 2));
                states.add(state);
                break;
            } else {
                break;
            }
        }
        for (int i = rank - 1, j = file + 1; i >= 0 && j < 8; i--, j++) {
            piece = current.getPiece(i, j);
            state = new State(current);

            if (piece == 0) {
                state.setState((byte) rank, (byte) file, (byte) i, (byte) j, (byte) (side * 2));
                states.add(state);
            } else if (piece * side < 0) {
                state.setState((byte) rank, (byte) file, (byte) i, (byte) j, (byte) (side * 2));
                states.add(state);
                break;
            } else {
                break;
            }
        }
        for (int i = rank + 1, j = file + 1; i < 8 && j < 8; i++, j++) {
            piece = current.getPiece(i, j);
            state = new State(current);

            if (piece == 0) {
                state.setState((byte) rank, (byte) file, (byte) i, (byte) j, (byte) (side * 2));
                states.add(state);
            } else if (piece * side < 0) {
                state.setState((byte) rank, (byte) file, (byte) i, (byte) j, (byte) (side * 2));
                states.add(state);
                break;
            } else {
                break;
            }
        }
        for (int i = rank - 1; i >= 0; i--) {
            piece = current.getPiece(i, file);
            state = new State(current);
            if (piece == 0) {
                state.setState((byte) rank, (byte) file, (byte) i, (byte) file, (byte) (side * 2));
                states.add(state);
            } else if (piece * side < 0) {
                state.setState((byte) rank, (byte) file, (byte) i, (byte) file, (byte) (side * 2));
                states.add(state);
                break;
            } else {
                break;
            }
        }
        for (int i = file - 1; i >= 0; i--) {
            piece = current.getPiece(rank, i);
            state = new State(current);
            if (piece == 0) {
                state.setState((byte) rank, (byte) file, (byte) rank, (byte) i, (byte) (side * 2));
                states.add(state);
            } else if (piece * side < 0) {
                state.setState((byte) rank, (byte) file, (byte) rank, (byte) i, (byte) (side * 2));
                states.add(state);
                break;
            } else {
                break;
            }
        }
        for (int i = rank + 1; i < 8; i++) {
            piece = current.getPiece(i, file);
            state = new State(current);
            if (piece == 0) {
                state.setState((byte) rank, (byte) file, (byte) i, (byte) file, (byte) (side * 2));
                states.add(state);
            } else if (piece * side < 0) {
                state.setState((byte) rank, (byte) file, (byte) i, (byte) file, (byte) (side * 2));
                states.add(state);
                break;
            } else {
                break;
            }
        }
        for (int i = file + 1; i < 8; i++) {
            piece = current.getPiece(rank, i);
            state = new State(current);
            if (piece == 0) {
                state.setState((byte) rank, (byte) file, (byte) rank, (byte) i, (byte) (side * 2));
                states.add(state);
            } else if (piece * side < 0) {
                state.setState((byte) rank, (byte) file, (byte) rank, (byte) i, (byte) (side * 2));
                states.add(state);
                break;
            } else {
                break;
            }
        }

        return states;
    }

    public ArrayList<State> pawn(int rank, int file, byte side) {
        ArrayList<State> states = new ArrayList();
        State state;
        byte piece;
        if (rank == 1 && side == 1) {

            byte piece1 = current.getPiece(2, file);
            byte piece2 = current.getPiece(3, file);
            if (piece1 == 0 && piece2 == 0) {
                state = new State(current);
                state.setState((byte) rank, (byte) file, (byte) (rank + 2), (byte) file, (byte) 6);
                states.add(state);
            }
        }
        if (rank == 6 && side == -1) {

            byte piece1 = current.getPiece(5, file);
            byte piece2 = current.getPiece(4, file);
            if (piece1 == 0 && piece2 == 0) {
                state = new State(current);
                state.setState((byte) rank, (byte) file, (byte) (rank - 2), (byte) file, (byte) -6);
                states.add(state);
            }

        }

        piece = current.getPiece(rank + side, file);
        if (piece == 0) {
            state = new State(current);
            if (rank + side == 7 || rank + side == 0) {
                state.setState((byte) rank, (byte) file, (byte) (rank + side), (byte) file, (byte) (side * 2));
                states.add(state);
            } else if (!(rank == 7 || rank == 0)) {
                state.setState((byte) rank, (byte) file, (byte) (rank + side), (byte) file, (byte) (side * 6));
                states.add(state);
            }
        }

        if (func.isContained(rank + side, file + 1)) {
            piece = current.getPiece(rank + side, file + 1);
            if (piece * side < 0) {
                state = new State(current);
                if (rank + side == 7 || rank + side == 0) {
                    state.setState((byte) rank, (byte) file, (byte) (rank + side), (byte) (file + 1), (byte) (side * 2));
                    states.add(state);
                } else {
                    state.setState((byte) rank, (byte) file, (byte) (rank + side), (byte) (file + 1), (byte) (side * 6));
                    states.add(state);
                }

            }
        }

        if (func.isContained(rank + side, file - 1)) {
            piece = current.getPiece(rank + side, file - 1);
            if (piece * side < 0) {
                state = new State(current);
                if (rank + side == 7 || rank + side == 0) {
                    state.setState((byte) rank, (byte) file, (byte) (rank + side), (byte) (file - 1), (byte) (side * 2));
                    states.add(state);
                } else {
                    state.setState((byte) rank, (byte) file, (byte) (rank + side), (byte) (file - 1), (byte) (side * 6));
                    states.add(state);
                }
            }
        }
        return states;
    }
}
