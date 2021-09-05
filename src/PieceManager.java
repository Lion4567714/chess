import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.*;

/**
 * <b>Move Class</b>
 * Class for storing possible moves in a more convenient format.
 */
class Move {
    public Piece piece;
    public int endX;
    public int endY;
    public int value;

    public Move(Piece piece, int endX, int endY) {
        this.piece = piece;
        this.endX = endX;
        this.endY = endY;
    }
}

/**
 * <b>Piece Manager Class</b>
 * Manages all thing related to the game pieces.
 */
public class PieceManager {

    /**
     * <b>User Move Input Method</b>
     * Get user input for the move they want to make and store it in a move object
     * @param board Current game state
     * @return Legal move ready to be performed
     */
    public Move userMoveInput(Piece[][] board) {
        Move move = null;

        System.out.println("Syntax: XYXY    Start Coordinates, End Coordinates");
        System.out.println("(ex. E2E4 or e2e4)");

        boolean passing = true;
        do {
            System.out.print("Please input your move:    ");

            Scanner console = new Scanner(System.in);
            String inputStr = console.nextLine();
            char[] inputArr = inputStr.toCharArray();

            inputArr[0] = Character.toUpperCase(inputArr[0]);
            inputArr[2] = Character.toUpperCase(inputArr[2]);

            int[] coords = new int[4];
            coords[0] = (int)inputArr[0] - 65;
            coords[1] = (int)inputArr[1] - 49;
            coords[2] = (int)inputArr[2] - 65;
            coords[3] = (int)inputArr[3] - 49;

            for (int i : coords) {
                if (i < 0 || i > 7) {
                    System.out.println("Invalid coordinates, please try again.");
                    passing = false;
                    break;
                } else {
                    passing = true;
                }
            }

            if (board[coords[0]][coords[1]] == null) {
                System.out.println("There is no piece at the coordinates specified, please try " +
                        "again.");
                passing = false;
            }

            if (passing) {
                move = new Move(board[coords[0]][coords[1]], coords[2], coords[3]);
                passing = checkLegality(move, board);

                if (!passing) {
                    System.out.println("Illegal move detected, please try again.");
                }
            }

        } while (!passing);

        return move;
    }

    /**
     * <b>Check Legality Method</b>
     * Checks if the move inputted by the user works or not, syntactically and legally.
     * @param move Move inputted by the user
     * @return Whether the move is legal
     */
    public boolean checkLegality(Move move, Piece[][] board) {
        Piece p = move.piece;
        boolean[][] possibleMoves = p.getPossibleMoves(board);
        return possibleMoves[move.endX][move.endY];
    }

    /**
     * <b>Get Moves Method</b>
     * Finds all possible moves the AI can make
     * @param board The current game state
     * @param color The color of whose turn it is
     * @return An ArrayList of all possible moves that could be made
     */
    public ArrayList<Move> getMoves(Piece[][] board, boolean color) {
        ArrayList<Move> possibleMoveArr = new ArrayList<>();

        for (Piece[] pArr : board) {
            for (Piece p : pArr) {
                if (p != null || p.color == color) {
                    assert p != null;
                    boolean[][] availabilityMap = p.getPossibleMoves(board);

                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            if (availabilityMap[i][j]) {
                                possibleMoveArr.add(new Move(p, i, j));
                            }
                        }
                    }
                }
            }
        }

        return possibleMoveArr;
    }
}
