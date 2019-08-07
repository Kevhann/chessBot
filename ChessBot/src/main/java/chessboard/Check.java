/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessboard;

import pieces.Piece;
import pieces.PieceType;
import utils.Colour;

/**
 *
 * @author kevin
 */
public class Check {

    private Chessboard board;

    public Check(Chessboard board) {
        this.board = board;
    }

    public boolean isChallenged(String place, Colour side) {
        int rank = 8 - Character.getNumericValue(place.charAt(1));
        int file = place.charAt(0) - 97;
        return isChallenged(rank, file, side);
    }

    public boolean isChallenged(int rank, int file, Colour side) {

        int direction = 1;
        if (side == Colour.WHITE) {
            direction = -1;
        }

        /**
         * Test for pawn and king captures
         */
        Piece temp = board.pieceOnBoard(rank + direction, file + 1);
        if (temp != null) {
            if (temp.side != side) {
                if (temp.type == PieceType.PAWN || temp.type == PieceType.KING) {
                    return true;
                }

            }
        }
        temp = board.pieceOnBoard(rank + direction, file - 1);
        if (temp != null) {
            if (temp.side != side) {
                if (temp.type == PieceType.PAWN || temp.type == PieceType.KING) {
                    return true;
                }
            }
        }
        
        /**
         * Test for the rest King cases
         */
        
        temp = board.pieceOnBoard(rank - direction, file + 1);
        if (temp != null) {
            if (temp.side != side) {
                if (temp.type == PieceType.KING) {
                    return true;
                }

            }
        }
        temp = board.pieceOnBoard(rank - direction, file - 1);
        if (temp != null) {
            if (temp.side != side) {
                if (temp.type == PieceType.KING) {
                    return true;
                }
            }
        }
        
        temp = board.pieceOnBoard(rank, file + 1);
        if (temp != null) {
            if (temp.side != side) {
                if (temp.type == PieceType.KING) {
                    return true;
                }

            }
        }
        
        temp = board.pieceOnBoard(rank, file - 1);
        if (temp != null) {
            if (temp.side != side) {
                if (temp.type == PieceType.KING) {
                    return true;
                }
            }
        }
        
        temp = board.pieceOnBoard(rank + 1,file);
        if (temp != null) {
            if (temp.side != side) {
                if (temp.type == PieceType.KING) {
                    return true;
                }

            }
        }
        
        temp = board.pieceOnBoard(rank - 1, file);
        if (temp != null) {
            if (temp.side != side) {
                if (temp.type == PieceType.KING) {
                    return true;
                }
            }
        }
        

        /**
         * Test for vertical and horizontal captures
         */
        for (int i = rank + 1; i < 8; i++) {
            temp = board.pieceOnBoard(i, file);
            if (temp != null) {
                if (temp.side != side) {
                    if (temp.type == PieceType.ROOK || temp.type == PieceType.QUEEN) {
                        return true;
                    }

                }
                break;
            }
        }

        for (int i = rank - 1; i > 0; i--) {
            temp = board.pieceOnBoard(i, file);
            if (temp != null) {
                if (temp.side != side) {
                    if (temp.type == PieceType.ROOK || temp.type == PieceType.QUEEN) {
                        return true;
                    }
                }
                break;
            }
        }

        for (int i = file + 1; i < 8; i++) {
            temp = board.pieceOnBoard(rank, i);
            if (temp != null) {
                if (temp.side != side) {
                    if (temp.type == PieceType.ROOK || temp.type == PieceType.QUEEN) {
                        return true;
                    }

                }
                break;
            }
        }

        for (int i = file - 1; i > 0; i--) {
            temp = board.pieceOnBoard(rank, i);
            if (temp != null) {
                if (temp.side != side) {
                    if (temp.type == PieceType.ROOK || temp.type == PieceType.QUEEN) {
                        return true;
                    }
                }
                break;
            }
        }

        /**
         * Test for diagonals
         */
        for (int i = rank + 1, j = file + 1; i < 8 || j < 8; i++, j++) {
            temp = board.pieceOnBoard(i, j);
            if (temp != null) {
                if (temp.side != side) {
                    if (temp.type == PieceType.BISHOP || temp.type == PieceType.QUEEN) {
                        return true;
                    }
                }
                break;
            }
        }

        for (int i = rank - 1, j = file + 1; i > 0 || j < 8; i--, j++) {
            temp = board.pieceOnBoard(i, j);
            if (temp != null) {
                if (temp.side != side) {
                    if (temp.type == PieceType.BISHOP || temp.type == PieceType.QUEEN) {
                        return true;
                    }

                }
                break;
            }
        }

        for (int i = rank + 1, j = file - 1; i < 8 || j > 0; i++, j--) {
            temp = board.pieceOnBoard(i, j);
            if (temp != null) {
                if (temp.side != side) {
                    if (temp.type == PieceType.BISHOP || temp.type == PieceType.QUEEN) {
                        return true;
                    }
                }
                break;
            }
        }

        for (int i = rank - 1, j = file - 1; i > 0 || j > 0; i--, j--) {
            temp = board.pieceOnBoard(i, j);
            if (temp != null) {
                if (temp.side != side) {
                    if (temp.type == PieceType.BISHOP || temp.type == PieceType.QUEEN) {
                        return true;
                    }
                }
                break;
            }
        }

        /**
         * Test for knights
         */
        temp = board.pieceOnBoard(rank + 1, file + 2);
        if (temp != null) {
            if (temp.side != side) {
                if (temp.type == PieceType.KNIGHT) {
                    return true;
                }
            }
        }

        temp = board.pieceOnBoard(rank - 1, file + 2);
        if (temp != null) {
            if (temp.side != side) {
                if (temp.type == PieceType.KNIGHT) {
                    return true;
                }
            }
        }

        temp = board.pieceOnBoard(rank + 1, file - 2);
        if (temp != null) {
            if (temp.side != side) {
                if (temp.type == PieceType.KNIGHT) {
                    return true;
                }
            }
        }

        temp = board.pieceOnBoard(rank - 1, file - 2);
        if (temp != null) {
            if (temp.side != side) {
                if (temp.type == PieceType.KNIGHT) {
                    return true;
                }
            }
        }

        temp = board.pieceOnBoard(rank + 2, file + 1);
        if (temp != null) {
            if (temp.side != side) {
                if (temp.type == PieceType.KNIGHT) {
                    return true;
                }
            }
        }

        temp = board.pieceOnBoard(rank - 2, file + 1);
        if (temp != null) {
            if (temp.side != side) {
                if (temp.type == PieceType.KNIGHT) {
                    return true;
                }
            }
        }

        temp = board.pieceOnBoard(rank + 2, file - 1);
        if (temp != null) {
            if (temp.side != side) {
                if (temp.type == PieceType.KNIGHT) {
                    return true;
                }
            }
        }

        temp = board.pieceOnBoard(rank - 2, file - 1);
        if (temp != null) {
            if (temp.side != side) {
                if (temp.type == PieceType.KNIGHT) {
                    return true;
                }
            }
        }
        return false;
    }
}
