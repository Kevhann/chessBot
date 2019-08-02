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
public class King extends Piece {

    public King(Colour colour, Chessboard board) {
        super(colour, PieceType.KING, board);
    }
    @Override
    public MoveType isLegalMove(Move move) {
        return MoveType.VALID;
    }
    
}
