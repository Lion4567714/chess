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
        board[0][1] = new Knight(false, 0, 1);
        board[0][2] = new Bishop(false, 0 , 2);
        board[0][3] = new Queen(false, 0, 3);
        board[0][4] = new King(false, 0, 4);
        board[0][5] = new Bishop(false, 0, 5);
        board[0][6] = new Knight(false, 0, 6);
        board[0][7] = new Rook(false, 0, 7);

        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(false, 1, i);
        }

        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(true, 6, i);
        }

        board[7][0] = new Rook(true, 7, 0);
        board[7][1] = new Knight(true, 7, 1);
        board[7][2] = new Bishop(true, 7, 2);
        board[7][3] = new Queen(true, 7, 3);
        board[7][4] = new King(true, 7, 4);
        board[7][5] = new Bishop(true, 7, 5);
        board[7][6] = new Knight(true, 7, 6);
        board[7][7] = new Rook(true, 7, 7);

        System.out.println("\\033[3mBoard Initialized...\\033[0m");
    }

    /**
     * <b>Draw Board Method</b>
     * Draws a chess board with the current game state into the console.
     */
    private void drawBoard() {
        System.out.println("|-------------------------------|");

        for (Piece[] pArr : board) {
            StringBuilder line = new StringBuilder("| ");

            for (Piece p : pArr) {
                if (p != null) {
                    if (p.color) {
                        line.append("\u001B[31m");
                    } else  {
                        line.append("\u001B[34m");
                    }

                    line.append(p.toString());

                } else {
                    line.append(" ");
                }

                line.append("\u001B[0m | ");
            }

            System.out.println(line);
            System.out.println("|-------------------------------|");
        }
    }
}
