/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import chessboard.State;
import utils.Func;
import utils.Position;

/**
 *
 * @author kevin
 */
public class Check {

    Func func = new Func();

    public Check() {

    }

    public boolean isChallenged(byte[] board, String place, byte side) {
        int rank = Character.getNumericValue(place.charAt(1)) - 1;
        int file = place.charAt(0) - 97;
        return isChallenged(board, rank, file, side);
    }

    public boolean isChallenged(State state, byte side) {
        Position king = state.getKingPos(side);
        return isChallenged(state.board, king.getRank(), king.getFile(), side);
    }

    public boolean isChallenged(byte[] board, Position pos, byte side) {
        return isChallenged(board, pos.getRank(), pos.getFile(), side);
    }

    /**
     *
     * @param board The board that is tested for
     * @param rank the rank of the square 0-7
     * @param file the file of the square 0-7
     * @param side the side tested for, -1 for black, 1 for white
     * @return true if the square is challenged by the opposite side
     */
    public boolean isChallenged(byte[] board, int rank, int file, byte side) {
        /**
         * Test for pawn and king captures
         */

        if (kingCheck(board, rank, file, side)) {
            return true;
        }
        if (pawnCheck(board, rank, file, side)) {
            return true;
        }

        if (straightCheck(board, rank, file, side)) {
            return true;
        }

        if (diagonalCheck(board, rank, file, side)) {
            return true;
        }

        if (knightCheck(board, rank, file, side)) {
            return true;
        }

        return false;
    }

    /**
     * Checks the the two corners for opposing pawns and kings
     *
     * @param board the board to be checked
     * @param rank the rank to be checked
     * @param file the file to be checked
     * @param side the side to check against
     * @return true if in check, false if not
     */
    public boolean pawnCheck(byte[] board, int rank, int file, byte side) {
        byte temp = func.pieceOnBoard(board, rank + side, file + 1);
        int type = temp * side;
        if (type == -6 || type == -1) {
            return true;

        }
        temp = func.pieceOnBoard(board, rank + side, file - 1);
        type = temp * side;
        if (type == -6 || type == -1) {
            return true;

        }
        return false;
    }

    /**
     * Checks the rest of the surrounding pieces for opposing kings
     *
     * @param board the board to be checked
     * @param rank the rank to be checked
     * @param file the file to be checked
     * @param side the side to check against
     * @return true if in check, false if not
     */
    public boolean kingCheck(byte[] board, int rank, int file, byte side) {
        byte temp;
        int type;
        temp = func.pieceOnBoard(board, rank - side, file + 1);
        type = temp * side;
        if (type == -1) {
            return true;
        }

        temp = func.pieceOnBoard(board, rank - side, file - 1);
        type = temp * side;
        if (type == -1) {
            return true;
        }

        temp = func.pieceOnBoard(board, rank, file + 1);
        type = temp * side;
        if (type == -1) {
            return true;
        }

        temp = func.pieceOnBoard(board, rank, file - 1);
        type = temp * side;
        if (type == -1) {
            return true;
        }

        temp = func.pieceOnBoard(board, rank + 1, file);
        type = temp * side;
        if (type == -1) {
            return true;
        }

        temp = func.pieceOnBoard(board, rank - 1, file);
        type = temp * side;
        if (type == -1) {
            return true;
        }
        return false;
    }

    /**
     * Checks the vertical and horizontal lines for opposing rooks and queens
     *
     * @param board the board to be checked
     * @param rank the rank to be checked
     * @param file the file to be checked
     * @param side the side to check against
     * @return true if in check, false if not
     */
    public boolean straightCheck(byte[] board, int rank, int file, byte side) {
        byte temp;
        int type;
        for (int i = rank + 1; i < 8; i++) {
            temp = func.pieceOnBoard(board, i, file);
            type = temp * side;
            if (type != 0) {
                if (type == -2 || type == -5) {
                    return true;
                }
                break;
            }
        }

        for (int i = rank - 1; i >= 0; i--) {
            temp = func.pieceOnBoard(board, i, file);
            type = temp * side;
            if (type != 0) {
                if (type == -2 || type == -5) {
                    return true;
                }
                break;
            }
        }

        for (int i = file + 1; i < 8; i++) {
            temp = func.pieceOnBoard(board, rank, i);
            type = temp * side;
            if (type != 0) {
                if (type == -2 || type == -5) {
                    return true;
                }
                break;
            }
        }

        for (int i = file - 1; i >= 0; i--) {
            temp = func.pieceOnBoard(board, rank, i);
            type = temp * side;
            if (type != 0) {
                if (type == -2 || type == -5) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    /**
     * Checks the diagonals for opposing bishops and queens
     *
     * @param board the board to be checked
     * @param rank the rank to be checked
     * @param file the file to be checked
     * @param side the side to check against
     * @return true if in check, false if not
     */
    public boolean diagonalCheck(byte[] board, int rank, int file, byte side) {
        byte temp;
        int type;
        for (int i = rank + 1, j = file + 1; i < 8 || j < 8; i++, j++) {
            temp = func.pieceOnBoard(board, i, j);
            type = temp * side;
            if (type != 0) {
                if (type == -2 || type == -3) {
                    return true;
                }
                break;
            }
        }

        for (int i = rank - 1, j = file + 1; i >= 0 || j < 8; i--, j++) {
            temp = func.pieceOnBoard(board, i, j);
            type = temp * side;
            if (type != 0) {
                if (type == -2 || type == -3) {
                    return true;
                }
                break;
            }
        }

        for (int i = rank + 1, j = file - 1; i < 8 || j >= 0; i++, j--) {
            temp = func.pieceOnBoard(board, i, j);
            type = temp * side;
            if (type != 0) {
                if (type == -2 || type == -3) {
                    return true;
                }
                break;
            }
        }

        for (int i = rank - 1, j = file - 1; i >= 0 || j >= 0; i--, j--) {
            temp = func.pieceOnBoard(board, i, j);
            type = temp * side;
            if (type != 0) {
                if (type == -2 || type == -3) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    /**
     * Check all the possible knight locations for opposing knights.
     *
     * @param board the board to be checked
     * @param rank the rank to be checked
     * @param file the file to be checked
     * @param side the side to check against
     * @return true if in check, false if not
     */
    public boolean knightCheck(byte[] board, int rank, int file, byte side) {
        byte temp;
        int type;
        temp = func.pieceOnBoard(board, rank + 1, file + 2);
        type = temp * side;
        if (type == -4) {
            return true;
        }

        temp = func.pieceOnBoard(board, rank - 1, file + 2);
        type = temp * side;
        if (type == -4) {
            return true;
        }

        temp = func.pieceOnBoard(board, rank + 1, file - 2);
        type = temp * side;
        if (type == -4) {
            return true;
        }

        temp = func.pieceOnBoard(board, rank - 1, file - 2);
        type = temp * side;
        if (type == -4) {
            return true;
        }

        temp = func.pieceOnBoard(board, rank + 2, file + 1);
        type = temp * side;
        if (type == -4) {
            return true;
        }

        temp = func.pieceOnBoard(board, rank - 2, file + 1);
        type = temp * side;
        if (type == -4) {
            return true;
        }

        temp = func.pieceOnBoard(board, rank + 2, file - 1);
        type = temp * side;
        if (type == -4) {
            return true;
        }

        temp = func.pieceOnBoard(board, rank - 2, file - 1);
        type = temp * side;
        if (type == -4) {
            return true;
        }
        return false;
    }
}
