/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessboard;

import utils.Colour;
import pieces.Piece;

/**
 *
 * @author kevin
 */
public class Square {

    private final Colour side;
    public int file;
    public int rank;
    private Piece currentPiece;

    public Square(Colour colour, int rank, int file) {
        this.side = colour;
        this.file = file;
        this.rank = rank;
    }

    public void setPiece(Piece piece) {
        this.currentPiece = piece;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public void removePiece() {
        this.currentPiece = null;
    }

    public int getRank() {
        return rank;
    }

    public int getFile() {
        return file;
    }

    public void setCurrentPiece(Piece currentPiece) {
        this.currentPiece = currentPiece;
    }

    public Colour getSide() {
        return side;
    }
    

    @Override
    public String toString() {
        if (currentPiece == null) {
            switch (side) {
                case WHITE:
                    return "   ";
                case BLACK:
                    return " # ";
            }
        }
        return currentPiece.toString();
    }

}
