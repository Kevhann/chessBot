/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

import utils.Colour;
import chessboard.Chessboard;
import utils.IllegalMoveException;
import chessboard.Move;
import utils.MoveType;

/**
 *
 * @author kevin
 */
public class Pawn extends Piece {

    boolean enpassant = false;

    public Pawn(Colour colour, Chessboard board) {
        super(colour, PieceType.PAWN, board);
    }

    /**
     * *
     *
     * @param move the checked move in format a2,a4
     * @return 1 for legal move, 2 for legal capture
     * @throws IllegalMoveException when a move is illegal
     */
    @Override
    public MoveType isLegalMove(Move move) {
        int fromRank = move.getFromRank();
        int fromFile = move.getFromFile();
        int toRank = move.getToRank();
        int toFile = move.getToFile();

        int dRank = toRank - fromRank;
        int dFile = toFile - fromFile;

        if (this.side == Colour.WHITE) {
            dRank *= -1;
        }

        Piece target = board.pieceOnBoard(toRank, toFile);
        if (dFile == 0) {
            if (dRank == 1) {
                enpassant = false;
                if (target != null) {
                    return MoveType.ILLEGAL;
                }
                return MoveType.VALID;
            } else if (dRank == 2 && moveCount == 0) {
                if (target != null) {
                    return MoveType.ILLEGAL;
                }
                enpassant = true;
                return MoveType.VALID;
            }
        }
        if ((dFile == 1 || dFile == -1) && dRank == 1) {
            System.out.println(this.side);
            if (target == null) {
                int checker = this.side == Colour.WHITE ? 1 : -1;
                Piece enpassantPiece = board.pieceOnBoard(toRank + checker, toFile);
                if (enpassantPiece == null || enpassantPiece.side == this.side) {
                    return MoveType.ILLEGAL;
                } else {
                    return MoveType.ENPASSANT;
                }
            } else if (target.side == this.side) {
                return MoveType.ILLEGAL;
            } else {
                return MoveType.CAPTURE;
            }
        }
        return MoveType.ILLEGAL;
    }

}
