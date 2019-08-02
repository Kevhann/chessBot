/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

/**
 *
 * @author kevin
 */
    public enum PieceType {
       
    PAWN( " ♙ ", " ♟ ", "pawn"), KNIGHT(" ♘ "," ♞ ", "knight"), BISHOP(" ♗ "," ♝ ", "bishop"),
    ROOK(" ♖ "," ♜ ", "rook"), QUEEN(" ♕ "," ♛ ", "queen"), KING(" ♔ "," ♚ ", "king");

    private final String whiteSymbol;
        private final String blackSymbol;

    private final String fullName;

    PieceType(String whiteSymbol, String blackSymbol, String fullName) {
        this.whiteSymbol = whiteSymbol;
        this.blackSymbol = blackSymbol;
        this.fullName = fullName;
    }

    public String getWhiteSymbol() {
        return whiteSymbol;
    }

    public String getBlackSymbol() {
        return blackSymbol;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return fullName;
    }
}
