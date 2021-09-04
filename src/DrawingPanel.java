import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.border.Border;
import javax.swing.event.*;
import java.io.*;
import java.util.ArrayList;

public class DrawingPanel extends MouseInputAdapter
        implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JLabel statusBar;
    private Graphics g;

    public DrawingPanel () {
        //BufferedImage boardImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        ArrayList<BufferedImage> pieceImages = new ArrayList<>();

        try {
            pieceImages.add(0, ImageIO.read(new File("Resources\\ChessBoard.png")));
            pieceImages.add(1, ImageIO.read(new File("Resources\\WhitePawn.png")));
            pieceImages.add(2, ImageIO.read(new File("Resources\\WhiteRook.png")));
            pieceImages.add(3, ImageIO.read(new File("Resources\\WhiteKnight.png")));
            pieceImages.add(4, ImageIO.read(new File("Resources\\WhiteBishop.png")));
            pieceImages.add(5, ImageIO.read(new File("Resources\\WhiteQueen.png")));
            pieceImages.add(6, ImageIO.read(new File("Resources\\WhiteKing.png")));
            pieceImages.add(7, ImageIO.read(new File("Resources\\BlackPawn.png")));
            pieceImages.add(8, ImageIO.read(new File("Resources\\BlackRook.png")));
            pieceImages.add(9, ImageIO.read(new File("Resources\\BlackKnight.png")));
            pieceImages.add(10, ImageIO.read(new File("Resources\\BlackBishop.png")));
            pieceImages.add(11, ImageIO.read(new File("Resources\\BlackQueen.png")));
            pieceImages.add(12, ImageIO.read(new File("Resources\\BlackKing.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<JLabel> pieceLabels = new ArrayList<>();
        for (int i = 0; i < pieceImages.size(); i++) {
            JLabel label = new JLabel();
            label.setIcon(new ImageIcon(pieceImages.get(i)));
            pieceLabels.add(label);
        }

        /////////////////
        // Set up initial game board
        ArrayList<JLabel> gameBoard = new ArrayList<>(64);
        gameBoard.add(pieceLabels.get(2));
        gameBoard.add(pieceLabels.get(3));
        gameBoard.add(pieceLabels.get(4));
        gameBoard.add(pieceLabels.get(5));
        gameBoard.add(pieceLabels.get(6));
        gameBoard.add(pieceLabels.get(4));
        gameBoard.add(pieceLabels.get(3));
        gameBoard.add(pieceLabels.get(2));

        for (int i = 0; i < 8; i++) {
            gameBoard.add(pieceLabels.get(1));
        }
        for (int i = 0; i < 32; i++) {
            gameBoard.add(new JLabel());
        }
        for (int i = 0; i < 8; i++) {
            gameBoard.add(pieceLabels.get(7));
        }

        gameBoard.add(pieceLabels.get(8));
        gameBoard.add(pieceLabels.get(9));
        gameBoard.add(pieceLabels.get(10));
        gameBoard.add(pieceLabels.get(12));
        gameBoard.add(pieceLabels.get(11));
        gameBoard.add(pieceLabels.get(10));
        gameBoard.add(pieceLabels.get(9));
        gameBoard.add(pieceLabels.get(8));
        /////////////////
        for (JLabel l : gameBoard) {
            System.out.println(l.getIcon());
        }

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(512, 512));
        //layeredPane.setBorder(BorderFactory.createTitledBorder("Nice"));
        //layeredPane.setLayout();

        //layeredPane.add(pieceLabels.get(0), 0);
        for (int i = 0; i < gameBoard.size(); i++) {
            layeredPane.add(gameBoard.get(i), 1);
        }

        panel = new JPanel();
        panel.setBackground(Color.CYAN);
        panel.setPreferredSize(new Dimension(512, 512));

        /*JLabel board = new JLabel();
        board = pieceLabels.get(0);
        panel.add(board);*/
        panel.add(layeredPane);
        /*for (JLabel l : pieceLabels) {
            panel.add(l);
        }*/

        statusBar = new JLabel(" ");

        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);

        frame = new JFrame("Drawing Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.add(statusBar, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);

        Timer timer = new Timer(250, this);
        timer.start();
    }

    public Graphics getGraphics() {
        return g;
    }

    public void setBackground(Color c) {
        panel.setBackground(c);
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    public void actionPerformed(ActionEvent e) {
        panel.repaint();
    }

    public void mouseMoved(MouseEvent e) {
        statusBar.setText("(" + e.getX() + ", " + e.getY() + ")");
    }
}
