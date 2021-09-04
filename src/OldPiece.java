/**
 * <b>Piece</b>
 * Inherited class for storing general game piece information
 *//*

public class OldPiece {
    protected int color;  //0 = white, 1 = black
    protected int x;
    protected int y;
    protected boolean isOmnidirectional = true;
    protected boolean isStraight;
    protected boolean isDiagonal;
    protected int maxDistance;

    public String toString() {
        return null;
    }

    */
/**
     * Please note: Possible moves are calculated from white's perspective, meaning pawns will
     * always be calculated going up. To solve this, the movements will be flipped when black makes
     * their move.
     * @param gameBoard
     * @return
     *//*

    public boolean[][] findPossibleMoves (Piece[][] gameBoard) {
        boolean[][] possibleMoves = new boolean[8][8];
        for (boolean[] bArr : possibleMoves) {
            for (boolean b : bArr) {
                b = false;
            }
        }

        if (!this.isOmnidirectional) {  // If the piece is a pawn, basically
            try {
                if (gameBoard[x][y + 1] == null) {
                    possibleMoves[x][y + 1] = true;
                    if (gameBoard[x][y + 2] == null) {
                        possibleMoves[x][y + 2] = true;
                    }
                }
            } catch (IndexOutOfBoundsException e) {}
            try {
                if (gameBoard[x - 1][y + 1] != null) {
                    possibleMoves[x - 1][y + 1] = true;
                }
            } catch (IndexOutOfBoundsException e) {}
            try {
                if (gameBoard[x + 1][y + 1] != null) {
                    possibleMoves[x + 1][y + 1] = true;
                }
            } catch (IndexOutOfBoundsException e) {}
        }

        return possibleMoves;
    }

    private boolean[][] checkColor (Piece[][] gameBoard, boolean[][] possibleMoves) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (possibleMoves[i][j]) {
                    if (gameBoard[i][j].color == this.color) {
                        possibleMoves[i][j] = false;
                    }
                }
            }
        }

        return possibleMoves;
    }
}
*/
