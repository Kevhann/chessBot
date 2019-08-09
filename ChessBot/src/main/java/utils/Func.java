/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author kevin
 */
public class Func {
    public int sign(int i) {
        if (i < 0) {
            return -1;
        } else if (i > 0) {
            return 1;
        } else return 0;
    }
    
    public boolean isContained(int rank, int file) {
        if (rank < 0 || rank > 7 || file < 0 || file > 7) {
            return false;
        }
        return true;
    }
    public byte pieceOnBoard(byte[] board, int rank, int file) {
        if (rank < 0 || rank > 7 || file < 0 || file > 7) {
            return 0;
        }
        return board[(rank * 8) + file];
    }
}
