/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessboard;

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
    

    public boolean isChallenged(byte[] board, int rank, int file, byte side) {
        /**
         * Test for pawn and king captures
         */
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
        /**
         * Test for the rest of the King cases
         */
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

        /**
         * Test for vertical and horizontal captures
         */
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

        for (int i = rank - 1; i > 0; i--) {
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

        for (int i = file - 1; i > 0; i--) {
            temp = func.pieceOnBoard(board, rank, i);
            type = temp * side;
            if (type != 0) {
                if (type == -2 || type == -5) {
                    return true;
                }
                break;
            }
        }

        /**
         * Test for diagonals
         */
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

        for (int i = rank - 1, j = file + 1; i > 0 || j < 8; i--, j++) {
            temp = func.pieceOnBoard(board, i, j);
            type = temp * side;
            if (type != 0) {
                if (type == -2 || type == -3) {
                    return true;
                }
                break;
            }
        }

        for (int i = rank + 1, j = file - 1; i < 8 || j > 0; i++, j--) {
            temp = func.pieceOnBoard(board, i, j);
            type = temp * side;
            if (type != 0) {
                if (type == -2 || type == -3) {
                    return true;
                }
                break;
            }
        }

        for (int i = rank - 1, j = file - 1; i > 0 || j > 0; i--, j--) {
            temp = func.pieceOnBoard(board, i, j);
            type = temp * side;
            if (type != 0) {
                if (type == -2 || type == -3) {
                    return true;
                }
                break;
            }
        }

        /**
         * Test for knights
         */
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
