import java.util.ArrayList;
import java.util.Scanner;

/**
 * New Piece class to attempt to use enums for pieces instead of separate classes.
 * Black and white chess pieces will be stores in separate arrays to decrease access time or
 * something I don't know what im saying
 */


enum PieceEnum {
    Pawn    (1, true, true),
    Rook    (5, true, false),
    Knight  (3, true, true),
    Bishop  (3, false, true),
    Queen   (8, true, true),
    King    (100, true, true);

    public final int value;
    public final boolean movesStraight;
    public final boolean movesDiagonally;

    PieceEnum(int value, boolean movesStraight, boolean movesDiagonally) {
        this.value = value;
        this.movesStraight = movesStraight;
        this.movesDiagonally = movesDiagonally;
    }
}

class Move {
    public int startX;
    public int startY;
    public int endX;
    public int endY;
    public int value;

    public Move(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }
}

public class NewPiece {
    public PieceEnum[][] whiteBoard = new PieceEnum[8][8];
    public PieceEnum[][] blackBoard = new PieceEnum[8][8];

    public void boardSetup() {
        whiteBoard[0][0] = PieceEnum.Rook;
        whiteBoard[0][1] = PieceEnum.Knight;
        whiteBoard[0][2] = PieceEnum.Bishop;
        whiteBoard[0][3] = PieceEnum.King;
        whiteBoard[0][4] = PieceEnum.Queen;
        whiteBoard[0][5] = PieceEnum.Bishop;
        whiteBoard[0][6] = PieceEnum.Knight;
        whiteBoard[0][7] = PieceEnum.Rook;

        for (int i = 0; i < 8; i++) {
            whiteBoard[1][i] = PieceEnum.Pawn;
        }

        for (int i = 0; i < 8; i++) {
            blackBoard[6][i] = PieceEnum.Pawn;
        }

        blackBoard[7][0] = PieceEnum.Rook;
        blackBoard[7][1] = PieceEnum.Knight;
        blackBoard[7][2] = PieceEnum.Bishop;
        blackBoard[7][3] = PieceEnum.King;
        blackBoard[7][4] = PieceEnum.Queen;
        blackBoard[7][5] = PieceEnum.Bishop;
        blackBoard[7][6] = PieceEnum.Knight;
        blackBoard[7][7] = PieceEnum.Rook;
    }

    public ArrayList<Move> evaluateMoves(PieceEnum[][] board1, PieceEnum[][] board2) {
        ArrayList<Move> possibleMoves = new ArrayList<>();

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (board1[x][y] != null) {
                    Move m;

                    if (board1[x][y] == PieceEnum.Pawn) {
                        try {
                            if (board1[x][y + 1] == null && board2[x][y + 1] == null) {
                                m = new Move(x, y, x, y + 1);
                                m.value = calculateValue(board1, board2, m);
                                possibleMoves.add(m);

                                try {
                                    if (x == 2 && board1[x][y + 2] == null &&
                                            board2[x][y + 2] == null) {
                                        m = new Move(x, y, x, y + 2);
                                        m.value = calculateValue(board1, board2, m);
                                        possibleMoves.add(m);
                                    }
                                } catch (IndexOutOfBoundsException ignored) {};
                            }
                        } catch (IndexOutOfBoundsException ignored) {};

                        try {
                            if (board2[x - 1][y + 1] != null) {
                                m = new Move(x, y, x - 1, y + 1);
                                m.value = calculateValue(board1, board2, m);
                                possibleMoves.add(m);
                            }
                        } catch (IndexOutOfBoundsException ignored) {};

                        try {
                            if (board2[x + 1][y + 1] != null) {
                                m = new Move(x, y, x + 1, y + 1);
                                m.value = calculateValue(board1, board2, m);
                                possibleMoves.add(m);
                            }
                        } catch (IndexOutOfBoundsException ignored) {};
                    }
                }
            }
        }

        return possibleMoves;
    }

    /**
     * Note to self: to implement machine learning, have the computer create a file that saves the
     * scenario it finds itself in, and grade it based on how well or poor it was going. Then for
     * later, the computer will find the situation that is most similar to the one its in.
     * @param board1
     * @param board2
     * @param move
     * @return
     */
    public int calculateValue(PieceEnum[][] board1, PieceEnum[][] board2, Move move) {
        int value = 0;

        // Add value for capturing a piece
        if (board2[move.endX][move.endY] != null) {
            value += board2[move.endX][move.endY].value;
        }

        // Add value for taking/targeting more central squares
        // Coordinates start from 1, not 0
        if (1 < move.endX && move.endX < 8 && 1 < move.endY && move.endY < 8) {
            value += 0.1;
        }
        if (2 < move.endX && move.endX < 7 && 2 < move.endY && move.endY < 7) {
            value += 0.1;
        }
        if (3 < move.endX && move.endX < 6 && 3 < move.endY && move.endY < 6) {
            value += 0.1;
        }

        return value;
    }

    /**
     * Move maker method for the player to input their move via the console
     */
    public void makeMove() {
        Scanner console = new Scanner(System.in);
        System.out.println("Input syntax: xyxy    (i.e. start coordinates, end coordinates)");

        do {
            System.out.print("Input your move: ");
            String moveInput = console.nextLine();

            char[] moveInputArr = moveInput.toCharArray();

        }
    }

    /**
     * Move maker method to play a move
     * @param move Inputted move to make
     */
    public void makeMove(Move move) {
        PieceEnum[][] board1;
        PieceEnum[][] board2;

        // Find out which color/board is being moved
        if (whiteBoard[move.startX][move.startY] != null) {
            board1 = whiteBoard;
            board2 = blackBoard;
        } else {
            board1 = blackBoard;
            board2 = whiteBoard;
        }

        // Check if a piece is going to be captured, if so, remove it
        if (board2[move.endX][move.endY] != null) {
            board2[move.endX][move.endY] = null;
        }

        // Move the piece
        board1[move.endX][move.endY] = board1[move.startX][move.startY];
        board1[move.startX][move.endY] = null;
    }

    private boolean checkMoveLegality(Move move) {
        PieceEnum[][] board1;
        PieceEnum[][] board2;

        // Find out which color/board is being moved
        if (whiteBoard[move.startX][move.startY] != null) {
            board1 = whiteBoard;
            board2 = blackBoard;
        } else {
            board1 = blackBoard;
            board2 = whiteBoard;
        }


    }
}
