import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * <b>Chess</b>
 * Chess is cool.
 *
 * @author Anders Gilliland
 * @version 0.1
 * @since 26-08-2021
 */
public class Chess {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static void main(String args[]) {
        /*Piece[][] gameBoard = new Piece[8][8];

        gameBoard[0][0] = new Rook(1, 0, 0);
        gameBoard[0][1] = new Knight(1, 0, 1);
        gameBoard[0][2] = new Bishop(1, 0, 2);
        gameBoard[0][3] = new Queen(1, 0, 3);
        gameBoard[0][4] = new King(1, 0, 4);
        gameBoard[0][5] = new Bishop(1, 0, 5);
        gameBoard[0][6] = new Knight(1, 0, 6);
        gameBoard[0][7] = new Rook(1, 0, 7);

        for (int i = 0; i < 8; i++) {
            gameBoard[1][i] = new Pawn(1, 1, i);
        }

        for (int i = 0; i < 8; i++) {
            gameBoard[6][i] = new Pawn(0, 6, i);
        }

        gameBoard[7][0] = new Rook(0, 7, 0);
        gameBoard[7][1] = new Knight(0, 7, 1);
        gameBoard[7][2] = new Bishop(0, 7, 2);
        gameBoard[7][3] = new Queen(0, 7, 3);
        gameBoard[7][4] = new King(0, 7, 4);
        gameBoard[7][5] = new Bishop(0, 7, 5);
        gameBoard[7][6] = new Knight(0, 7, 6);
        gameBoard[7][7] = new Rook(0, 7, 7);


        DrawBoard(gameBoard);*/

        NewPiece newPiece = new NewPiece();
        newPiece.boardSetup();
        newDrawBoard(newPiece);

        ArrayList<Move> moves = newPiece.evaluateMoves(newPiece.whiteBoard, newPiece.blackBoard);

        // Order possible moves by their value (descending)
        Move best = moves.get(0);
        for (int i = 0; i < moves.size(); i++) {
            if (moves.get(i).value > best.value) {
                best = moves.get(i);
                moves.remove(best);
                moves.add(0, best);
            }
        }

        // Obviously, I will not be able to tell the computer how valuable each move is exactly, so
        // I am adding a margin of error the computer can have to its choice of move. This gives
        // the computer a chance to try something new each time. This can also be used to dumb the
        // computer down a bit for less experienced players.
        Random rand = new Random();

        for (int i = 0; i < 10; i++) {

        }
    }

    private static void DrawBoard(Piece[][] board) {
        System.out.println("|-------------------------------|");

        for (Piece[] pArr : board) {
            String line = "| ";

            for (Piece p : pArr) {
                if (p != null) {
                    if (p.color == 0) {
                        line += ANSI_RED;
                    } else if (p.color == 1) {
                        line += ANSI_BLUE;
                    }

                    line += p.toString();

                } else {
                    line += " ";
                }

                line += ANSI_RESET + " | ";
            }

            System.out.println(line);
            System.out.println("|-------------------------------|");
        }
    }

    private static void newDrawBoard(NewPiece board) {
        System.out.println("|-------------------------------|");

        for (int x = 0; x < 8; x++) {

            StringBuilder line = new StringBuilder("| ");

            for (int y = 0; y < 8; y++) {
                if (board.whiteBoard[x][y] != null) {
                    line.append(ANSI_RED).append(getSymbol(board.whiteBoard[x][y]))
                            .append(ANSI_RESET);
                } else if (board.blackBoard[x][y] != null) {
                    line.append(ANSI_BLUE).append(getSymbol(board.blackBoard[x][y]))
                            .append(ANSI_RESET);
                } else {
                    line.append(" ");
                }

                line.append(" | ");
            }

            System.out.println(line);
            System.out.println("|-------------------------------|");
        }
    }

    private static String getSymbol(PieceEnum piece) {
        if (piece == PieceEnum.Pawn) {
            return "p";
        } else if (piece == PieceEnum.Rook) {
            return "R";
        } else if (piece == PieceEnum.Knight) {
            return "N";
        } else if (piece == PieceEnum.Bishop) {
            return "B";
        } else if (piece == PieceEnum.Queen) {
            return "Q";
        } else {
            return "K";
        }
    }
}
