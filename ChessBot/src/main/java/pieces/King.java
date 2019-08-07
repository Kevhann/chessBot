/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

import utils.Colour;
import chessboard.Chessboard;
import chessboard.Move;
import utils.MoveType;

/**
 *
 * @author kevin
 */
public class King extends Piece {

    public King(Colour colour, Chessboard board) {
        super(colour, PieceType.KING, board);
    }

    @Override
    public MoveType isLegalMove(Move move) {
        int fromRank = move.getFromRank();
        int fromFile = move.getFromFile();
        int toRank = move.getToRank();
        int toFile = move.getToFile();

        int dRank = toRank - fromRank;
        int dFile = toFile - fromFile;

        if (dRank < 0) {
            dRank = -dRank;
        }
        if (dFile < 0) {
            dFile = -dFile;
        }

        if (dRank < 2 && dFile < 2 && (dFile == 1 || dRank == 1)) {
            Piece target = board.pieceOnBoard(toRank, toFile);
            if (target == null) {
                return MoveType.VALID;
            } else if (target.side != this.side) {
                return MoveType.CAPTURE;
            }
        }
        return MoveType.ILLEGAL;
    }

}
