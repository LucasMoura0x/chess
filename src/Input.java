import Pieces.Pieces;

import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Input extends MouseAdapter {

    Board board;

    public Input(Board board) {
        this.board = board;
    }


    public void mousePressed(MouseEvent e) {
        int col = e.getX() / board.tileSize;
        int row = e.getY() / board.tileSize;

        Pieces clicked = board.getPiece(col, row);

        if (clicked != null) {
            board.selectedPiece = clicked;
        }

        board.repaint();
    }


    public void mouseReleased(MouseEvent e) {

        if (board.selectedPiece != null) {

            int col = e.getX() / board.tileSize;
            int row = e.getY() / board.tileSize;

            Move move = new Move(board, board.selectedPiece, col, row);

            if (board.isValidMove(move)) {

                board.makeMove(move);

                // ----------------------------
                // CHEQUE-MATE
                // ----------------------------
                boolean enemyIsWhite = !board.selectedPiece.isWhite;

                if (board.isCheckmate(enemyIsWhite)) {

                    String winner = board.selectedPiece.isWhite ? "Brancas" : "Pretas";

                    JOptionPane.showMessageDialog(
                            board,
                            "Cheque-mate!\nVitória das peças " + winner,
                            "Fim de jogo",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    System.exit(0);
                }

            } else {
                // Volta a peça
                board.selectedPiece.xPos = board.selectedPiece.col * board.tileSize;
                board.selectedPiece.yPos = board.selectedPiece.row * board.tileSize;
            }

            board.selectedPiece = null;
        }

        board.repaint();
    }


    public void mouseMoved(MouseEvent e) {
        if (board.selectedPiece != null) {
            board.mouseX = e.getX();
            board.mouseY = e.getY();
            board.repaint();
        }
    }
}
