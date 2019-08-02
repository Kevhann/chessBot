/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

import chessboard.Chessboard;
import chessboard.Move;
import chessboard.MoveType;

/**
 *
 * @author kevin
 */
public class Bishop extends Piece {

    public Bishop(Colour colour, Chessboard board) {
        super(colour, PieceType.BISHOP, board);
    }

    @Override
    public MoveType isLegalMove(Move move) {
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
            System.out.println("rankDir: " + rankDir + ", fileDir: " + fileDir);
            for (int i = 1, j = 1; i < dRank * rankDir && j < dFile * fileDir; i++, j++) {
                System.out.println("i: " + i + ", j: " + j);
                if (board.pieceOnBoard(fromRank + (i * rankDir),fromFile + (j * fileDir)) != null) {
                    return MoveType.ILLEGAL;
                }
            }
            Piece target = board.pieceOnBoard(toRank, toFile);
            if (target == null) {
                return MoveType.VALID;
            } else if (target.side != this.side) {
                return MoveType.CAPTURE;
            }
        }

        return MoveType.ILLEGAL;
    }
}
