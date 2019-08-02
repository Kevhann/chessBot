/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

import chessboard.Chessboard;
import chessboard.Move;
import chessboard.MoveType;

/**
 *
 * @author kevin
 */
public class Rook extends Piece {

    public Rook(Colour colour, Chessboard board) {
        super(colour, PieceType.ROOK, board);
    }

    @Override
    public MoveType isLegalMove(Move move) {
        int fromRank = move.getFromRank();
        int fromFile = move.getFromFile();
        int toRank = move.getToRank();
        int toFile = move.getToFile();

        int dRank = toRank - fromRank;
        int dFile = toFile - fromFile;

        int direction = 1;

        if (dRank == 0 && dFile != 0) {
            if (dFile < 0) {
                direction = -1;
            }

            for (int i = 1; i < dFile * direction; i++) {
                if (board.pieceOnBoard(fromRank, fromFile + (i * direction)) != null) {
                    return MoveType.ILLEGAL;
                }
            }
            Piece target = board.pieceOnBoard(toRank, toFile);
            if (target == null) {
                return MoveType.VALID;
            } else if (target.side != this.side) {
                return MoveType.CAPTURE;
            } else {
                return MoveType.ILLEGAL;
            }
            
        } else if (dRank != 0 && dFile == 0) {
            if (dRank < 0) {
                direction = -1;
            }
            for (int i = 1; i < dRank * direction; i++) {
                if (board.pieceOnBoard(fromRank + (i * direction), fromFile) != null) {
                    return MoveType.ILLEGAL;
                }
            }
            Piece target = board.pieceOnBoard(toRank, toFile);
            if (target == null) {
                return MoveType.VALID;
            } else if (target.side != this.side) {
                return MoveType.CAPTURE;
            } else {
                return MoveType.ILLEGAL;
            }
        }

        return MoveType.ILLEGAL;
    }
}
