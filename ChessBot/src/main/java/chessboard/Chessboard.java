/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessboard;

import utils.IllegalMoveException;
import utils.MoveType;
import pieces.*;
import utils.Position;

/**
 *
 * @author kevin
 */
public class Chessboard {

    private final MoveChecker checker = new MoveChecker(this);
    private final String[] files = {"   ", " a ", " b ", " c ", " d ", " e ", " f ", " g ", " h "};
    private final String[] symbols = {" ♟ ", " ♜ ", " ♞ ", " ♝ ", " ♛ ", " ♚ ", "   ", " ♔ ", " ♕ ", " ♗ ", " ♘ ", " ♖ ", " ♙ "};
    private Check check = new Check();
    private byte[] board;
    private State current;
    private byte[] castle;
    private Position blackKing;
    private Position whiteKing;
    public boolean whiteCheck;
    public boolean blackCheck;

    /**
     * <pre>
     * The board representing the chessboard.
     * board[0] is bottom left (a1), board[63] top right (h8)
     * positive for white, negative for black
     * 1 for King
     * 2 for Queen
     * 3 for Bishop
     * 4 for Knight
     * 5 for Rook
     * 6 for Pawn
     * </pre>
     */
    public Chessboard() {
        this.current = new State(new byte[64]);
        this.board = current.getBoard();
        this.whiteKing = new Position("e1");
        this.blackKing = new Position("e8");
    }

    public void addPieces() {
        board[0] = 5;
        board[7] = 5;
        board[63] = -5;
        board[56] = -5;
        board[1] = 4;
        board[6] = 4;
        board[62] = -4;
        board[57] = -4;
        board[2] = 3;
        board[5] = 3;
        board[61] = -3;
        board[58] = -3;
        board[3] = 2;
        board[59] = -2;
        board[4] = 1;
        board[60] = -1;
        for (int i = 0; i < 8; i++) {
            board[8 + i] = 6;
            board[48 + i] = -6;
        }

    }

    public void printBoard() {
        System.out.println("");
        for (int i = 7; i >= 0; i--) {
            System.out.print(i + 1 + "  ");
            for (int j = 0; j < 8; j++) {
                System.out.print(symbols[board[(8 * i) + j] + 6]);
            }
            System.out.println("");
        }
        for (int i = 0; i < 9; i++) {
            System.out.print(files[i]);
        }
        System.out.println("");
    }

    public void move(Move move, byte turn) throws IllegalMoveException {
        byte fromRank = move.getFromRank();
        byte fromFile = move.getFromFile();
        byte toRank = move.getToRank();
        byte toFile = move.getToFile();

        byte currentPiece = board[(fromRank * 8) + fromFile];
        int piecetype = currentPiece * turn;
        if (piecetype <= 0) {
            throw new IllegalMoveException("Illegal move");
        }

        MoveType moveType;

        switch (piecetype) {
            case 1:
                moveType = checker.king(move, turn);
                break;
            case 2:
                moveType = checker.queen(move, turn);
                break;

            case 3:
                moveType = checker.bishop(move, turn);
                break;

            case 4:
                moveType = checker.knight(move, turn);
                break;

            case 5:
                moveType = checker.rook(move, turn);
                break;

            case 6:
                moveType = checker.pawn(move, turn);
                break;

            default:
                System.out.println("switch case default");
                moveType = MoveType.ILLEGAL;

        }

        System.out.println("Movetype: " + moveType);

        switch (moveType) {
            case ENPASSANT:
                //todo
                System.out.println("case enpassant");
            case ILLEGAL:
                throw new IllegalMoveException("Unvalid Move");
            default:
                if (piecetype == 1) {
                    if (check.isChallenged(board, toRank, toFile, turn)) {
                        throw new IllegalMoveException("King cannot move");
                    } else {
                        if (turn == 1) {
                            whiteKing.setPos(toRank, toFile);
                        } else {
                            blackKing.setPos(toRank, toFile);
                        }
                    }

                }
                board[(fromRank * 8) + fromFile] = 0;
                board[(toRank * 8) + toFile] = currentPiece;

        }
    }

    public byte pieceOnBoard(int rank, int file) {
        if (rank < 0 || rank > 7 || file < 0 || file > 7) {
            return 0;
        }
        return board[(rank * 8) + file];
    }

    public byte pieceOnBoard(String place) {
        int rank = Character.getNumericValue(place.charAt(1)) - 1;
        int file = place.charAt(0) - 97;
        return board[(rank * 8) + file];
    }

    public byte pieceOnBoard(int index) {
        return board[index];
    }

    public byte[] getBoard() {
        return board;
    }

    public State getBoardState() {
        byte[] state = board.clone();
        return new State(state, whiteKing, blackKing);
    }

    public void printState(byte[] state) {
        System.out.println("");
        for (int i = 7; i >= 0; i--) {
            System.out.print(i + 1 + "  ");
            for (int j = 0; j < 8; j++) {
                System.out.print(symbols[state[(8 * i) + j] + 6]);
            }
            System.out.println("");
        }
        for (int i = 0; i < 9; i++) {
            System.out.print(files[i]);
        }
        System.out.println("");
    }

    public void clearBoard() {
        board = new byte[64];
        this.whiteKing = new Position("e1");
        this.blackKing = new Position("e8");
    }

    public void setState(byte[] state) {
        this.board = state;
    }

    public void cpMove(Move move, byte turn) {
        byte fromRank = move.fromRank;
        byte fromFile = move.fromFile;
        byte toRank = move.toRank;
        byte toFile = move.toFile;
        byte to = move.getTo64();
        byte from = move.getFrom64();
        byte piece = board[from];
        if (piece == 1) {
            whiteKing.setPos(to);
        } else if (piece == -1) {
            blackKing.setPos(to);
        }
        board[from] = 0;
        board[to] = piece;

    }

    public void addPiece(byte piece, String place) {
        int rank = Character.getNumericValue(place.charAt(1)) - 1;
        int file = place.charAt(0) - 97;
        board[(rank * 8) + file] = piece;
    }

    public Position kingPos(byte side) {
        if (side == 1) {
            return whiteKing;
        } else {
            return blackKing;
        }
    }

    public Position getBlackKing() {
        return blackKing;
    }

    public Position getWhiteKing() {
        return whiteKing;
    }

    public void setBlackKing(Position blackKing) {
        this.blackKing = blackKing;
    }

    public void setWhiteKing(Position whiteKing) {
        this.whiteKing = whiteKing;
    }
    
}
