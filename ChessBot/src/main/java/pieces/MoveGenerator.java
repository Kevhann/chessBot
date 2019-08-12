/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

import chessboard.*;
import java.util.ArrayList;
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

    Func func = new Func();

    public MoveGenerator(Chessboard board) {
        this.board = board;
        this.check = new Check();
    }

    public ArrayList<State> getAll(byte side) {
        ArrayList<State> states = new ArrayList();
        for (int i = 0; i < 64; i++) {
            byte piece = board.pieceOnBoard(i);
            if (func.sign(piece) == side) {
                ArrayList<State> state = new ArrayList();
                int type = side * piece;
                int rank = i / 8;
                int file = i - (rank * 8);
                switch (type) {
                    case 1:
                        state = king(rank, file, side);
                        break;
                    case 2:
                        state = queen(rank, file, side);
                        break;
                    case 3:
                        state = bishop(rank, file, side);
                        break;
                    case 4:
                        state = knight(rank, file, side);
                        break;
                    case 5:
                        state = rook(rank, file, side);
                        break;
                    case 6:
                        state = pawn(rank, file, side);
                        break;
                }
                states.addAll(state);
            }
        }
        int size = states.size();
        for (int i = size - 1; i >= 0; i--) {
            State s = states.get(i);
            Position king = s.kingPos(side);
            byte rank = king.getRank();
            byte file = king.getFile();
            if (check.isChallenged(s.board, rank, file, side)) {
                states.remove(i);
            }
        }
        return states;
    }

    public ArrayList<State> bishop(int rank, int file, byte side) {
        ArrayList<State> states = new ArrayList();
        State state;
        byte piece;
        for (int i = rank - 1, j = file - 1; i >= 0 && j >= 0; i--, j--) {
            piece = board.pieceOnBoard(i, j);
            state = new State(board.getBoardState());
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
            piece = board.pieceOnBoard(i, j);
            state = new State(board.getBoardState());

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
            piece = board.pieceOnBoard(i, j);
            state = new State(board.getBoardState());

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
            piece = board.pieceOnBoard(i, j);
            state = new State(board.getBoardState());

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
            piece = board.pieceOnBoard(rank - 1, file);
            if (piece * side <= 0) {
                state = new State(board.getBoardState());
                state.setState((byte) rank, (byte) file, (byte) (rank - 1), (byte) file, (byte) side);
                if (!check.isChallenged(state.board, rank - 1, file, side)) {
                    state.setKingPos(side, new Position((byte) (rank - 1), (byte) (file)));
                    states.add(state);

                }
            }
            if (file > 0) {
                piece = board.pieceOnBoard(rank - 1, file - 1);
                if (piece * side <= 0) {
                    state = new State(board.getBoardState());
                    state.setState((byte) rank, (byte) file, (byte) (rank - 1), (byte) (file - 1), (byte) side);
                    if (!check.isChallenged(state.board, rank - 1, file - 1, side)) {
                        state.setKingPos(side, new Position((byte) (rank - 1), (byte) (file - 1)));
                        states.add(state);
                    }
                }
            }
            if (file < 7) {
                piece = board.pieceOnBoard(rank - 1, file + 1);
                if (piece * side <= 0) {
                    state = new State(board.getBoardState());
                    state.setState((byte) rank, (byte) file, (byte) (rank - 1), (byte) (file + 1), (byte) side);
                    if (!check.isChallenged(state.board, rank - 1, file + 1, side)) {
                        state.setKingPos(side, new Position((byte) (rank - 1), (byte) (file + 1)));
                        states.add(state);
                    }
                }
            }
        }
        if (rank < 7) {
            piece = board.pieceOnBoard(rank + 1, file);
            if (piece * side <= 0) {
                state = new State(board.getBoardState());
                state.setState((byte) rank, (byte) file, (byte) (rank + 1), (byte) file, (byte) side);
                if (!check.isChallenged(state.board, rank + 1, file, side)) {
                    state.setKingPos(side, new Position((byte) (rank + 1), (byte) (file)));
                    states.add(state);
                }
            }
            if (file > 0) {
                piece = board.pieceOnBoard(rank + 1, file - 1);
                if (piece * side <= 0) {
                    state = new State(board.getBoardState());
                    state.setState((byte) rank, (byte) file, (byte) (rank + 1), (byte) (file - 1), (byte) side);
                    if (!check.isChallenged(state.board, rank + 1, file - 1, side)) {
                        state.setKingPos(side, new Position((byte) (rank + 1), (byte) (file - 1)));
                        states.add(state);
                    }
                }
            }
            if (file < 7) {
                piece = board.pieceOnBoard(rank + 1, file + 1);
                if (piece * side <= 0) {
                    state = new State(board.getBoardState());
                    state.setState((byte) rank, (byte) file, (byte) (rank + 1), (byte) (file + 1), (byte) side);
                    if (!check.isChallenged(state.board, rank + 1, file + 1, side)) {
                        state.setKingPos(side, new Position((byte) (rank + 1), (byte) (file + 1)));
                        states.add(state);
                    }
                }
            }
        }
        if (file > 0) {
            piece = board.pieceOnBoard(rank, file - 1);
            if (piece * side <= 0) {
                state = new State(board.getBoardState());
                state.setState((byte) rank, (byte) file, (byte) rank, (byte) (file - 1), (byte) side);
                if (!check.isChallenged(state.board, rank, file - 1, side)) {
                    state.setKingPos(side, new Position((byte) (rank), (byte) (file - 1)));
                    states.add(state);
                }
            }
        }
        if (file < 7) {
            piece = board.pieceOnBoard(rank, file + 1);
            if (piece * side <= 0) {
                state = new State(board.getBoardState());
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
            if (board.pieceOnBoard(rank + 1, file + 2) * side <= 0) {
                state = new State(board.getBoardState());
                state.setState((byte) rank, (byte) file, (byte) (rank + 1), (byte) (file + 2), (byte) (side * 4));
                states.add(state);
            }
        }

        if (func.isContained(rank - 1, file + 2)) {
            if (board.pieceOnBoard(rank - 1, file + 2) * side <= 0) {
                state = new State(board.getBoardState());
                state.setState((byte) rank, (byte) file, (byte) (rank - 1), (byte) (file + 2), (byte) (side * 4));
                states.add(state);
            }
        }

        if (func.isContained(rank + 1, file - 2)) {
            if (board.pieceOnBoard(rank + 1, file - 2) * side <= 0) {
                state = new State(board.getBoardState());
                state.setState((byte) rank, (byte) file, (byte) (rank + 1), (byte) (file - 2), (byte) (side * 4));
                states.add(state);
            }
        }

        if (func.isContained(rank - 1, file - 2)) {
            if (board.pieceOnBoard(rank - 1, file - 2) * side <= 0) {
                state = new State(board.getBoardState());
                state.setState((byte) rank, (byte) file, (byte) (rank - 1), (byte) (file - 2), (byte) (side * 4));
                states.add(state);
            }
        }

        if (func.isContained(rank + 2, file + 1)) {
            if (board.pieceOnBoard(rank + 2, file + 1) * side <= 0) {
                state = new State(board.getBoardState());
                state.setState((byte) rank, (byte) file, (byte) (rank + 2), (byte) (file + 1), (byte) (side * 4));
                states.add(state);
            }
        }

        if (func.isContained(rank - 2, file + 1)) {
            if (board.pieceOnBoard(rank - 2, file + 1) * side <= 0) {
                state = new State(board.getBoardState());
                state.setState((byte) rank, (byte) file, (byte) (rank - 2), (byte) (file + 1), (byte) (side * 4));
                states.add(state);
            }
        }

        if (func.isContained(rank + 2, file - 1)) {
            if (board.pieceOnBoard(rank + 2, file - 1) * side <= 0) {
                state = new State(board.getBoardState());
                state.setState((byte) rank, (byte) file, (byte) (rank + 2), (byte) (file - 1), (byte) (side * 4));
                states.add(state);
            }
        }

        if (func.isContained(rank - 2, file - 1)) {
            if (board.pieceOnBoard(rank - 2, file - 1) * side <= 0) {
                state = new State(board.getBoardState());
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
            piece = board.pieceOnBoard(i, file);
            state = new State(board.getBoardState());
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
            piece = board.pieceOnBoard(rank, i);
            state = new State(board.getBoardState());
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
            piece = board.pieceOnBoard(i, file);
            state = new State(board.getBoardState());
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
            piece = board.pieceOnBoard(rank, i);
            state = new State(board.getBoardState());
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
            piece = board.pieceOnBoard(i, j);
            state = new State(board.getBoardState());
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
            piece = board.pieceOnBoard(i, j);
            state = new State(board.getBoardState());

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
            piece = board.pieceOnBoard(i, j);
            state = new State(board.getBoardState());

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
            piece = board.pieceOnBoard(i, j);
            state = new State(board.getBoardState());

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
            piece = board.pieceOnBoard(i, file);
            state = new State(board.getBoardState());
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
            piece = board.pieceOnBoard(rank, i);
            state = new State(board.getBoardState());
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
            piece = board.pieceOnBoard(i, file);
            state = new State(board.getBoardState());
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
            piece = board.pieceOnBoard(rank, i);
            state = new State(board.getBoardState());
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

            byte piece1 = board.pieceOnBoard(2, file);
            byte piece2 = board.pieceOnBoard(3, file);
            if (piece1 == 0 && piece2 == 0) {
                state = new State(board.getBoardState());
                state.setState((byte) rank, (byte) file, (byte) (rank + 2), (byte) file, (byte) 6);
                states.add(state);
            }
        }
        if (rank == 6 && side == -1) {

            byte piece1 = board.pieceOnBoard(5, file);
            byte piece2 = board.pieceOnBoard(4, file);
            if (piece1 == 0 && piece2 == 0) {
                state = new State(board.getBoardState());
                state.setState((byte) rank, (byte) file, (byte) (rank - 2), (byte) file, (byte) -6);
                states.add(state);
            }

        }

        piece = board.pieceOnBoard(rank + side, file);
        if (piece == 0) {
            state = new State(board.getBoardState());
            if (rank == 7 || rank == 0) {
//                state.setState((byte) rank, (byte) file, (byte) (rank + side), (byte) file, (byte) (side * 6));
            } else {
                state.setState((byte) rank, (byte) file, (byte) (rank + side), (byte) file, (byte) (side * 6));
            }
            states.add(state);
        }

        piece = board.pieceOnBoard(rank + side, file + 1);
        if (piece * side < 0) {
            state = new State(board.getBoardState());
            state.setState((byte) rank, (byte) file, (byte) (rank + 1), (byte) (file + 1), (byte) (side * 6));
            states.add(state);
        }

        piece = board.pieceOnBoard(rank + side, file - 1);
        if (piece * side < 0) {
            state = new State(board.getBoardState());
            state.setState((byte) rank, (byte) file, (byte) (rank + 1), (byte) (file - 1), (byte) (side * 6));
            states.add(state);

        }

        return states;
    }
}
