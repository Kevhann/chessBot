/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessboard;

/**
 *
 * @author kevin
 */
public class Move {
    public int fromRank;
    public int toRank;
    public int fromFile;
    public int toFile;

    public Move(String move) {
        this.fromRank = 8 - Character.getNumericValue(move.charAt(1));
        this.fromFile = move.charAt(0) - 97;
        this.toRank =  8 - Character.getNumericValue(move.charAt(4));
        this.toFile = move.charAt(3) - 97;
    }

    @Override
    public String toString() {
        return "" + fromFile + "," + fromRank + "," + toFile + "," + toRank;
    }

    public int getFromFile() {
        return fromFile;
    }

    public int getFromRank() {
        return fromRank;
    }

    public int getToFile() {
        return toFile;
    }

    public int getToRank() {
        return toRank;
    }
    
    
    
    
}
