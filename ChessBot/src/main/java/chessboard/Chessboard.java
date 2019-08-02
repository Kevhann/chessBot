/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessboard;

import pieces.*;
import pieces.Colour;

/**
 *
 * @author kevin
 */
public class Chessboard {

    private final String[] files = {"   ", " a ", " b ", " c ", " d ", " e ", " f ", " g ", " h "};
    private Square[][] board;

    public Chessboard() {
        this.board = new Square[8][8];
    }

    public void initializeBoard() {
        Colour colour = Colour.WHITE;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Square(colour, i, j);
                colour = colour == Colour.WHITE ? Colour.BLACK : Colour.WHITE;
            }
            colour = colour == Colour.WHITE ? Colour.BLACK : Colour.WHITE;
        }
    }

    public void addPieces() {
        for (int i = 0; i < 8; i++) {
            board[1][i].setPiece(new Pawn(Colour.BLACK, this));
            board[6][i].setPiece(new Pawn(Colour.WHITE, this));
        }
        board[0][0].setPiece(new Rook(Colour.BLACK, this));
        board[0][7].setPiece(new Rook(Colour.BLACK, this));
        board[0][1].setPiece(new Knight(Colour.BLACK, this));
        board[0][6].setPiece(new Knight(Colour.BLACK, this));
        board[0][2].setPiece(new Bishop(Colour.BLACK, this));
        board[0][5].setPiece(new Bishop(Colour.BLACK, this));
        board[0][4].setPiece(new King(Colour.BLACK, this));
        board[0][3].setPiece(new Queen(Colour.BLACK, this));

        board[7][0].setPiece(new Rook(Colour.WHITE, this));
        board[7][7].setPiece(new Rook(Colour.WHITE, this));
        board[7][1].setPiece(new Knight(Colour.WHITE, this));
        board[7][6].setPiece(new Knight(Colour.WHITE, this));
        board[7][2].setPiece(new Bishop(Colour.WHITE, this));
        board[7][5].setPiece(new Bishop(Colour.WHITE, this));
        board[7][4].setPiece(new King(Colour.WHITE, this));
        board[7][3].setPiece(new Queen(Colour.WHITE, this));

    }

    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            System.out.print("\n" + (8 - i) + "  ");
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j]);
            }
        }
        System.out.print("\n\n");
        for (int i = 0; i < 9; i++) {
            System.out.print(files[i]);
        }
        System.out.println("");
    }

    public void move(Move move, Colour turn) throws IllegalMoveException {
        int fromRank = move.getFromRank();
        int fromFile = move.getFromFile();
        int toRank = move.getToRank();
        int toFile = move.getToFile();

        Square currentSquare = board[fromRank][fromFile];
        Piece currentPiece = currentSquare.getCurrentPiece();
        if (currentPiece == null || currentPiece.side != turn) {
            throw new IllegalMoveException("Illegal move");
        }
        MoveType moveType = currentPiece.isLegalMove(move);
        
        System.out.println("Movetype: " + moveType);

        switch (moveType) {
            case ENPASSANT:
                //todo
                System.out.println("case enpassant");
            case ILLEGAL:
                throw new IllegalMoveException("Unvalid Move");
            default:
                currentPiece.incrementMoveCounter();

                Square targetSquare = board[toRank][toFile];
                Piece targetPiece = targetSquare.getCurrentPiece();
                targetSquare.setPiece(currentPiece);
                currentSquare.removePiece();
        }

    }

    public Piece pieceOnBoard(int rank, int file) {
        return board[rank][file].getCurrentPiece();
    }

    public Square[][] getBoard() {
        return board;
    }

    public void clearBoard() {
        board = new Square[8][8];
    }
    public void addPiece(Piece piece, String place) {
        int rank = 8 - Character.getNumericValue(place.charAt(1));
        int file = place.charAt(0) - 97;
        board[rank][file].setCurrentPiece(piece);
    }
//    public void addPiece(Piece piece, int rank, int file) {
//        board[rank][file].setCurrentPiece(piece);
//    }

}
