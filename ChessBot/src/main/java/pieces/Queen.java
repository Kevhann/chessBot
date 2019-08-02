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
public class Queen extends Piece {
    
    public Queen(Colour colour, Chessboard board) {
        super(colour, PieceType.QUEEN, board);
    }

    @Override
    public MoveType isLegalMove(Move move) {
        int fromRank = move.getFromRank();
        int fromFile = move.getFromFile();
        int toRank = move.getToRank();
        int toFile = move.getToFile();
        
        int dRank = toRank - fromRank;
        int dFile = toFile - fromFile;
        
        return MoveType.VALID;
    }
}
