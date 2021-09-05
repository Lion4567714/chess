/**
 * <b>Board Manager Class</b>
 * Manages all things related to the game board
 */
public class BoardManager {

    public Piece[][] board = new Piece[8][8];

    /**
     * <b>Initialize Board Method</b>
     * Creates a brand new board.
     */
    public void initializeBoard() {
        board[0][0] = new Rook(false, 0, 0);
        board[1][0] = new Knight(false, 1, 0);
        board[2][0] = new Bishop(false, 2, 0);
        board[3][0] = new Queen(false, 3, 0);
        board[4][0] = new King(false, 4, 0);
        board[5][0] = new Bishop(false, 5, 0);
        board[6][0] = new Knight(false, 6, 0);
        board[7][0] = new Rook(false, 7, 0);

        for (int i = 0; i < 8; i++) {
            board[i][1] = new Pawn(false, i, 1);
        }

        for (int i = 0; i < 8; i++) {
            board[i][6] = new Pawn(true, i, 6);
        }

        board[0][7] = new Rook(true, 0, 7);
        board[1][7] = new Knight(true, 1, 7);
        board[2][7] = new Bishop(true, 2, 7);
        board[3][7] = new Queen(true, 3, 7);
        board[4][7] = new King(true, 4, 7);
        board[5][7] = new Bishop(true, 5, 7);
        board[6][7] = new Knight(true, 6, 7);
        board[7][7] = new Rook(true, 7, 7);

        System.out.println("\033[3m Board Initialized...\033[0m");
    }

    /**
     * <b>Draw Board Method</b>
     * Draws a chess board with the current game state into the console.
     */
    public void drawBoard() {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_BLUE = "\u001B[34m";

        System.out.println("\n  |-------------------------------|");

        int i = 8;
        for (int y = 7; y >= 0; y--) {
            String line = i + " | ";
            i--;

            for (int x = 0; x <= 7; x++) {
                if (board[x][y] != null) {
                    if (board[x][y].getColor()) {
                        line += ANSI_RED;
                    } else  {
                        line += ANSI_BLUE;
                    }

                    line += board[x][y].getSymbol();

                } else {
                    line += " ";
                }

                line += ANSI_RESET + " | ";
            }

            System.out.println(line);
            System.out.println("  |-------------------------------|");
        }
        System.out.println("    A   B   C   D   E   F   G   H\n");
    }

    /**
     * <b>Play Move Method</b>
     * Changes the game state by making a move.
     * @param move Desired move to input
     */
    public void playMove(Move move) {
        board[move.endX][move.endY] = move.piece;
        board[move.piece.getX()][move.piece.getY()] = null;
        move.piece.setX(move.endX);
        move.piece.setY(move.endY);

        System.out.println("--------------------------------------------");
        drawBoard();
    }
}
