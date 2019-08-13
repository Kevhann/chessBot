/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessboard;

import java.util.ArrayList;
import utils.Position;

/**
 *
 * @author kevin
 */
public class State implements Comparable<State> {

    public byte[] board;
    private int score;
    public Move move;
    private Position whiteKing;
    private Position blackKing;

    public State(byte[] state) {
        this.board = state;
    }
    public State(State state) {
        this.board = state.board;
        this.whiteKing = state.getWhiteKing();
        this.blackKing = state.getBlackKing();
    }
    public State(byte[] state, Position whiteKing, Position blackKing) {
        this.board = state;
        this.whiteKing = whiteKing;
        this.blackKing = blackKing;
    }
    
    public void setState(byte fromRank, byte fromFile, byte toRank, byte toFile, byte value) {
        this.move = new Move(fromRank, fromFile, toRank, toFile);
        board[(fromRank * 8) + fromFile] = 0;
        board[(toRank * 8) + toFile] = value;
        evaluate();
    }
    
    public byte getPiece(int index) {
        return board[index];
    }
    
    public byte getPiece(int rank, int file) {
        return board[(rank * 8) + file];
    }
    
    public void setPiece(int index, byte value) {
        board[index] = value;
    }
    
    public void setPiece(int rank, int file, byte value) {
        board[(rank * 8) + file] = value;        
    }

    public byte[] getBoard() {
        return board;
    }

    public void setWhiteKing(Position whiteKing) {
        this.whiteKing = whiteKing;
    }

    public void setBlackKing(Position blackKing) {
        this.blackKing = blackKing;
    }

    public Position getBlackKing() {
        return blackKing;
    }

    public Position getWhiteKing() {
        return whiteKing;
    }
    
    public Position getKingPos(byte side) {
        if (side == 1) {
            return whiteKing;
        } else {
            return blackKing;
        }
    }
    
    
    
   
    public void evaluate() {
        int score = 0;
        for (int i = 0; i < 64; i++) {
            byte piece = board[i];
            int rank = i / 8;
            int file = i - (rank * 8);
            switch (piece) {
                case -1:
                    score += 900;
                    break;
                case -2:
                    score += 90;
                    break;
                case -3:
                    score += 30;
                    break;
                case -4:
                    score += 30;
                    break;
                case -5:
                    score += 50;
                    break;
                case -6:
                    score += 10;
                    break;
                case 1:
                    score -= 900;
                    break;
                case 2:
                    score -= 90;
                    break;
                case 3:
                    score -= 30;
                    break;
                case 4:
                    score -= 30;
                    break;
                case 5:
                    score -= 50;
                    break;
                case 6:
                    score -= 10;
                    break;
            }
        }
        this.score = score;
    }
    
    public Position kingPos(byte side) {
        if (side == 1) {
            return whiteKing;
        } else {
            return blackKing;
        }
    }

    @Override
    public int compareTo(State s) {
        return this.score - s.score;
    }
    
    public void setKingPos(byte side, Position pos) {
        if (side == 1) {
            this.whiteKing = pos;
        } else {
            this.blackKing = pos;
        }
    }
    public int getScore(byte side) {
        return score * side;
    }

}
