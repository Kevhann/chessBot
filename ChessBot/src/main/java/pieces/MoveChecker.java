/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

import chessboard.Chessboard;
import chessboard.Move;
import utils.MoveType;

/**
 *
 * @author kevin
 */
public class MoveChecker {

    Chessboard board;

    public MoveChecker(Chessboard board) {
        this.board = board;
    }

    public MoveType bishop(Move move, byte side) {
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
                    return MoveType.ILLEGAL;
                }
            }
            Byte target = board.pieceOnBoard(toRank, toFile);
            if (target == 0) {
                return MoveType.VALID;
            } else if (target * side < 0) {
                return MoveType.CAPTURE;
            }
        }
        return MoveType.ILLEGAL;
    }

    public MoveType king(Move move, byte side) {
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
                return MoveType.VALID;
            } else if (target * side < 0) {
                return MoveType.CAPTURE;
            }
//        } else if (dRank == 0 && dFile == 2) {
//            if (!check.isChallenged(fromRank, fromFile, side)
//                    && !check.isChallenged(fromRank, fromFile + dir, side)
//                    && !check.isChallenged(dRank, toFile, side)) {
//                return MoveType.CASTLE;
//            }
        }
        return MoveType.ILLEGAL;
    }

    public MoveType knight(Move move, byte side) {
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
                return MoveType.VALID;
            } else if (target * side < 0) {
                return MoveType.CAPTURE;
            }
        }

        return MoveType.ILLEGAL;
    }

    public MoveType rook(Move move, byte side) {
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
                    return MoveType.ILLEGAL;
                }
            }
            byte target = board.pieceOnBoard(toRank, toFile);
            if (target == 0) {
                return MoveType.VALID;
            } else if (side * target < 0) {
                return MoveType.CAPTURE;
            } else {
                return MoveType.ILLEGAL;
            }

        } else if (dRank != 0 && dFile == 0) {
            if (dRank < 0) {
                direction = -1;
            }
            for (int i = 1; i < dRank * direction; i++) {
                if (board.pieceOnBoard(fromRank + (i * direction), fromFile) != 0) {
                    return MoveType.ILLEGAL;
                }
            }
            byte target = board.pieceOnBoard(toRank, toFile);
            if (target == 0) {
                return MoveType.VALID;
            } else if (side * target < 0) {
                return MoveType.CAPTURE;
            } else {
                return MoveType.ILLEGAL;
            }
        }

        return MoveType.ILLEGAL;
    }

    public MoveType queen(Move move, byte side) {
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
                    return MoveType.ILLEGAL;
                }
            }
            Byte target = board.pieceOnBoard(toRank, toFile);
            if (target == 0) {
                return MoveType.VALID;
            } else if (target * side < 0) {
                return MoveType.CAPTURE;
            }
        } else if (dRank == 0 && dFile != 0) {

            for (int i = 1; i < dFile * fileDir; i++) {
                if (board.pieceOnBoard(fromRank, fromFile + (i * fileDir)) != 0) {
                    return MoveType.ILLEGAL;
                }
            }
            Byte target = board.pieceOnBoard(toRank, toFile);
            if (target == 0) {
                return MoveType.VALID;
            } else if (target * side < 0) {
                return MoveType.CAPTURE;
            } else {
                return MoveType.ILLEGAL;
            }

        } else if (dRank != 0 && dFile == 0) {
            for (int i = 1; i < dRank * rankDir; i++) {
                if (board.pieceOnBoard(fromRank + (i * rankDir), fromFile) != 0) {
                    return MoveType.ILLEGAL;
                }
            }
            Byte target = board.pieceOnBoard(toRank, toFile);
            if (target == 0) {
                return MoveType.VALID;
            } else if (target * side < 0) {
                return MoveType.CAPTURE;
            } else {
                return MoveType.ILLEGAL;
            }
        }

        return MoveType.ILLEGAL;
    }

    public MoveType pawn(Move move, byte side) {
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
                    return MoveType.VALID;
                }
                return MoveType.ILLEGAL;
            } else if ((fromRank == 1 && side == 1) || (side == -1 && fromRank == 6)) {
                if (dRank == 2) {
                    if (target == 0 && board.pieceOnBoard(fromRank + side, fromFile) == 0) {
                        return MoveType.VALID;
                    }
                    return MoveType.ILLEGAL;
                }

            }
        }
        if ((dFile == 1 || dFile == -1) && dRank == 1) {
            if (target * side >= 0) {
                return MoveType.ILLEGAL;
            } else {
                return MoveType.CAPTURE;
            }
        }
        return MoveType.ILLEGAL;
    }
}
