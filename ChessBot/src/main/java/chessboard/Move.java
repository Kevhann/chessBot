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
    public byte fromRank;
    public byte toRank;
    public byte fromFile;
    public byte toFile;

    public Move(String move) {
        this.fromRank = (byte) (Character.getNumericValue(move.charAt(1)) - 1);
        this.fromFile = (byte) (move.charAt(0) - 97);
        this.toRank = (byte) (Character.getNumericValue(move.charAt(4)) - 1);
        this.toFile = (byte) (move.charAt(3) - 97);
    }

    public Move(byte fromRank, byte fromFile, byte toRank, byte toFile) {
        this.fromRank = fromRank;
        this.toRank = toRank;
        this.fromFile = fromFile;
        this.toFile = toFile;
    }
    
    
    
    public byte getFrom64() {
        return (byte) ((fromRank * 8) + fromFile);
    }
    
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