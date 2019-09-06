package engine;

import chessboard.Chessboard;
import utils.Move;

/**
 * Checks the legality of player-inputted moves depending on the piece
 *
 * @author kevin
 */
public class MoveChecker {

    Chessboard board;

    public MoveChecker(Chessboard board) {
        this.board = board;
    }

    /**
     * Checks if the given move is valid. switches to the correct function
     * depending on the piece to check
     *
     * @param piecetype the type of piece from 1-6
     * @param move the attempted move
     * @param turn the turn to move
     * @return true for a legal move, false for an illegal one
     */
    public boolean checkAll(byte piecetype, Move move, byte turn) {

        switch (piecetype) {
            case 1:
                return king(move, turn);
            case 2:
                return queen(move, turn);
            case 3:
                return bishop(move, turn);
            case 4:
                return knight(move, turn);
            case 5:
                return rook(move, turn);
            case 6:
                return pawn(move, turn);
            default:
                return false;
        }
    }

    /**
     * Check the move for case bishop
     *
     * @param move the move to test
     * @param side 1 for white, -1 for black
     * @return true if legal, false otherwise
     */
    public boolean bishop(Move move, byte side) {
        int fromRank = move.getFromRank();
        int fromFile = move.getFromFile();
        int toRank = move.getToRank();
        int toFile = move.getToFile();

        int dRank = toRank - fromRank;
        int dFile = toFile - fromFile;

        int rankDir = 1;
        int fileDir = 1;

        if (dRank < 0) {
            rankDir = -1;
        }

        if (dFile < 0) {
            fileDir = -1;
        }

        if (dRank == dFile || dRank == -dFile) {
            for (int i = 1, j = 1; i < dRank * rankDir && j < dFile * fileDir; i++, j++) {
                if (board.pieceOnBoard(fromRank + (i * rankDir), fromFile + (j * fileDir)) != 0) {
                    return false;
                }
            }
            Byte target = board.pieceOnBoard(toRank, toFile);
            if (target == 0) {
                return true;
            } else if (target * side < 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check the move for case King
     *
     * @param move the move to test
     * @param side 1 for white, -1 for black
     * @return true if legal, false otherwise
     */
    public boolean king(Move move, byte side) {
        int fromRank = move.getFromRank();
        int fromFile = move.getFromFile();
        int toRank = move.getToRank();
        int toFile = move.getToFile();

        int dRank = toRank - fromRank;
        int dFile = toFile - fromFile;
        int dir = 1;

        if (dRank < 0) {
            dRank = -dRank;
        }
        if (dFile < 0) {
            dFile = -dFile;
            dir = -1;
        }

        if (dRank < 2 && dFile < 2 && (dFile == 1 || dRank == 1)) {
            byte target = board.pieceOnBoard(toRank, toFile);
            if (target == 0) {
                return true;
            } else if (target * side < 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check the move for case knight
     *
     * @param move the move to test
     * @param side 1 for white, -1 for black
     * @return true if legal, false otherwise
     */
    public boolean knight(Move move, byte side) {
        int fromRank = move.getFromRank();
        int fromFile = move.getFromFile();
        int toRank = move.getToRank();
        int toFile = move.getToFile();

        int dRank = toRank - fromRank;
        int dFile = toFile - fromFile;

        if (dRank < 0) {
            dRank *= -1;
        }
        if (dFile < 0) {
            dFile *= -1;
        }

        if ((dRank == 1 && dFile == 2) || (dRank == 2 && dFile == 1)) {
            Byte target = board.pieceOnBoard(toRank, toFile);
            if (target == 0) {
                return true;
            } else if (target * side < 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * Check the move for case rook
     *
     * @param move the move to test
     * @param side 1 for white, -1 for black
     * @return true if legal, false otherwise
     */
    public boolean rook(Move move, byte side) {
        int fromRank = move.getFromRank();
        int fromFile = move.getFromFile();
        int toRank = move.getToRank();
        int toFile = move.getToFile();

        int dRank = toRank - fromRank;
        int dFile = toFile - fromFile;

        int direction = 1;

        if (dRank == 0 && dFile != 0) {
            if (dFile < 0) {
                direction = -1;
            }

            for (int i = 1; i < dFile * direction; i++) {
                if (board.pieceOnBoard(fromRank, fromFile + (i * direction)) != 0) {
                    return false;
                }
            }
            byte target = board.pieceOnBoard(toRank, toFile);
            if (target == 0) {
                return true;
            } else if (side * target < 0) {
                return true;
            } else {
                return false;
            }

        } else if (dRank != 0 && dFile == 0) {
            if (dRank < 0) {
                direction = -1;
            }
            for (int i = 1; i < dRank * direction; i++) {
                if (board.pieceOnBoard(fromRank + (i * direction), fromFile) != 0) {
                    return false;
                }
            }
            byte target = board.pieceOnBoard(toRank, toFile);
            if (target == 0) {
                return true;
            } else if (side * target < 0) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    /**
     * Check the move for case queen
     *
     * @param move the move to test
     * @param side 1 for white, -1 for black
     * @return true if legal, false otherwise
     */
    public boolean queen(Move move, byte side) {
        int fromRank = move.getFromRank();
        int fromFile = move.getFromFile();
        int toRank = move.getToRank();
        int toFile = move.getToFile();

        int dRank = toRank - fromRank;
        int dFile = toFile - fromFile;

        int rankDir = 1;
        int fileDir = 1;

        if (dRank < 0) {
            rankDir = -1;
        }

        if (dFile < 0) {
            fileDir = -1;
        }

        if (dRank == dFile || dRank == -dFile) {
            for (int i = 1, j = 1; i < dRank * rankDir && j < dFile * fileDir; i++, j++) {
                if (board.pieceOnBoard(fromRank + (i * rankDir), fromFile + (j * fileDir)) != 0) {
                    return false;
                }
            }
            Byte target = board.pieceOnBoard(toRank, toFile);
            if (target == 0) {
                return true;
            } else if (target * side < 0) {
                return true;
            }
        } else if (dRank == 0 && dFile != 0) {

            for (int i = 1; i < dFile * fileDir; i++) {
                if (board.pieceOnBoard(fromRank, fromFile + (i * fileDir)) != 0) {
                    return false;
                }
            }
            Byte target = board.pieceOnBoard(toRank, toFile);
            if (target == 0) {
                return true;
            } else if (target * side < 0) {
                return true;
            } else {
                return false;
            }

        } else if (dRank != 0 && dFile == 0) {
            for (int i = 1; i < dRank * rankDir; i++) {
                if (board.pieceOnBoard(fromRank + (i * rankDir), fromFile) != 0) {
                    return false;
                }
            }
            Byte target = board.pieceOnBoard(toRank, toFile);
            if (target == 0) {
                return true;
            } else if (target * side < 0) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    /**
     * Check the move for case pawn
     *
     * @param move the move to test
     * @param side 1 for white, -1 for black
     * @return true if legal, false otherwise
     */
    public boolean pawn(Move move, byte side) {
        int fromRank = move.getFromRank();
        int fromFile = move.getFromFile();
        int toRank = move.getToRank();
        int toFile = move.getToFile();

        int dRank = toRank - fromRank;
        int dFile = toFile - fromFile;

        dRank *= side;

        byte target = board.pieceOnBoard(toRank, toFile);
        if (dFile == 0) {
            if (dRank == 1) {
                if (target == 0) {
                    return true;
                }
                return false;
            } else if ((fromRank == 1 && side == 1) || (side == -1 && fromRank == 6)) {
                if (dRank == 2) {
                    if (target == 0 && board.pieceOnBoard(fromRank + side, fromFile) == 0) {
                        return true;
                    }
                    return false;
                }

            }
        }
        if ((dFile == 1 || dFile == -1) && dRank == 1) {
            if (target * side >= 0) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
