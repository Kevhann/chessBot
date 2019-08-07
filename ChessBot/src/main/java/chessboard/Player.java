/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessboard;

import java.util.ArrayList;
import pieces.Piece;
import utils.Colour;

/**
 *
 * @author kevin
 */
public class Player {
    private Colour side;
    private ArrayList<Piece> pieces;
    
    public Player(Colour colour) {
        this.side = colour;
        this.pieces = new ArrayList();
    }
    public void addPiece(Piece piece) {
        this.pieces.add(piece);
    }
}
