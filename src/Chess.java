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
    public static void main(String[] args) {
        BoardManager bMgr = new BoardManager();
        PieceManager pMgr = new PieceManager();

        bMgr.initializeBoard();
        bMgr.drawBoard();

        while (true) {
            Move move = pMgr.userMoveInput(bMgr.board);
            bMgr.playMove(move);

            ArrayList<Move> moveArrayList = pMgr.getMoves(bMgr.board, true);
            Random rand = new Random();
            move = moveArrayList.get(rand.nextInt(moveArrayList.size()));
            System.out.println(move.toString());
            bMgr.playMove(move);
        }
    }
}
