/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessboard;

import engine.Check;
import utils.Move;
import engine.MoveChecker;
import utils.MoveType;
import utils.Position;

/**
 *
 * @author kevin
 */
public class Chessboard {

    private final MoveChecker checker = new MoveChecker(this);

    private Check check = new Check();
    private int score = 0;
    private byte[] board;
    private Position blackKing;
    private Position whiteKing;

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
//        this.current = new State(new byte[64]);
        this.board = new byte[64];
        this.whiteKing = new Position("e1");
        this.blackKing = new Position("e8");
    }

    /**
     * Adds the starting pieces to their initial places
     */
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

    /**
     * Method for moving the human players moves. Checks the validity of the
     * attempted move
     *
     * @param move the move to attempt
     * @param turn the turn of the player, 1 for white, -1 for black
     * @throws IllegalMoveException if the move is invalid
     */
    public void move(Move move, byte turn) throws IllegalArgumentException {
        byte fromRank = move.getFromRank();
        byte fromFile = move.getFromFile();
        byte toRank = move.getToRank();
        byte toFile = move.getToFile();

        byte currentPiece = board[(fromRank * 8) + fromFile];
        int piecetype = currentPiece * turn;
        if (piecetype <= 0) {
            throw new IllegalArgumentException("Illegal move");
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
        switch (moveType) {
            case ENPASSANT:
                //todo
            case ILLEGAL:
                throw new IllegalArgumentException("Unvalid Move");
            default:

                if (piecetype == 1) {
                    setKingPos(new Position(toRank, toFile), turn);
                }

                if (check.isChallenged(board, getKingPos(turn), turn)) {
                    throw new IllegalArgumentException("King cannot move");
                } else {
                    if (piecetype == 6) {
                        if (toRank == 0 || toRank == 7) {
                            board[(fromRank * 8) + fromFile] = 0;
                            board[(toRank * 8) + toFile] = (byte) (2 * turn);
                        }
                    }
                    board[(fromRank * 8) + fromFile] = 0;
                    board[(toRank * 8) + toFile] = currentPiece;
                }
        }
    }

    /**
     * The method for the computer to move a piece on the board
     *
     * @param state the state to use, copies the values to the current state of
     * the board
     */
    public void cpMove(State state) {
        whiteKing.setPos(state.getWhiteKing());
        blackKing.setPos(state.getBlackKing());
        board = state.board;
        score = state.getScore();
    }

    /**
     *
     * @param rank
     * @param file
     * @return (+/-)1-6 for pieces, 0 for empty and out of bounds
     */
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

    public void clearBoard() {
        board = new byte[64];
        this.whiteKing = new Position("e1");
        this.blackKing = new Position("e8");
    }

    public void setState(byte[] state) {
        this.board = state;
    }

    /**
     * Add a single piece on the board. Used for testing purposes
     *
     * @param piece The piece to add, 1-6, positive for white, negative for
     * black
     * @param place The place to add the piece in format "d4"
     */
    public void addPiece(byte piece, String place) {
        int rank = Character.getNumericValue(place.charAt(1)) - 1;
        int file = place.charAt(0) - 97;
        board[(rank * 8) + file] = piece;
    }

    /**
     *
     * @param side The desired side
     * @return the position of the desired king
     */
    public Position getKingPos(byte side) {
        if (side == 1) {
            return whiteKing;
        } else {
            return blackKing;
        }
    }

    public void setKingPos(Position pos, byte side) {
        if (side == 1) {
            whiteKing = pos;
        } else {
            blackKing = pos;
        }
    }

    /**
     *
     * @return the total number of pieces on the board
     */
    public int piecesOnBoard() {
        int amount = 0;
        for (int i = 0; i < 64; i++) {
            if (board[i] != 0) {
                amount++;
            }
        }
        return amount;
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

    public int getScore() {
        return score;
    }

}
