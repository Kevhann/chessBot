/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessboard;

/**
 * represents a single move on the chessboard
 * @author kevin
 */
public class Move {
    public byte fromRank;
    public byte toRank;
    public byte fromFile;
    public byte toFile;

    /**
     * 
     * @param move The move in string format "a2,a4"
     */
    public Move(String move) {
        this.fromRank = (byte) (Character.getNumericValue(move.charAt(1)) - 1);
        this.fromFile = (byte) (move.charAt(0) - 97);
        this.toRank = (byte) (Character.getNumericValue(move.charAt(4)) - 1);
        this.toFile = (byte) (move.charAt(3) - 97);
    }
    
    /**
     * 
     * @param fromRank the initial rank 0-7
     * @param fromFile the initial file 0-7 (a-h)
     * @param toRank the target rank 0-7
     * @param toFile the target file 0-7 (a-h)
     */
    public Move(byte fromRank, byte fromFile, byte toRank, byte toFile) {
        this.fromRank = fromRank;
        this.toRank = toRank;
        this.fromFile = fromFile;
        this.toFile = toFile;
    }
    
    
    /**
     * 
     * @return the initial position in the logical format 0-63
     */
    public byte getFrom64() {
        return (byte) ((fromRank * 8) + fromFile);
    }
    /**
     * 
     * @return the target position in the logical format 0-63 
     */
    public byte getTo64() {
        return (byte) ((toRank * 8) + toFile);
    }

    @Override
    public String toString() {
        return "fromRank: " + fromRank + ", fromFile: " + fromFile + ", toRank: " + toFile + ", toFile: " + toFile;
    }

    public byte getFromFile() {
        return fromFile;
    }

    public byte getFromRank() {
        return fromRank;
    }

    public byte getToFile() {
        return toFile;
    }

    public byte getToRank() {
        return toRank;
    }
}