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
public abstract class Piece {
    
    public int moveCount;
    public Colour side;
    public PieceType type;
    public Chessboard board;
    
    public Piece(Colour colour, PieceType type, Chessboard board) {
        this.side = colour;
        this.type = type;
        this.moveCount = 0;
        this.board = board;
    }

    public abstract MoveType isLegalMove(Move move);
            
    public void setPosition() {
        
    }
    public void incrementMoveCounter() {
        moveCount++;
    }

    @Override
    public String toString() {
        switch(side){
            case WHITE:
                return type.getWhiteSymbol();
            case BLACK:
                return type.getBlackSymbol();
        }
        return null;
    }
    
    
}
