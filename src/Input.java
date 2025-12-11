import Pieces.Pieces;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Input extends MouseAdapter {

    Board board;

    public Input(Board board){
        this.board = board;
    }


    public void mousePressed(MouseEvent e) {
        int col = e.getX() / board.tileSize;
        int row = e.getY() / board.tileSize;

        Pieces clicked = board.getPiece(col, row);

        if (clicked != null) {
            board.selectedPiece = clicked;
        }
    }

    public void mouseReleased(MouseEvent e) {

        if (board.selectedPiece != null) {

            int col = e.getX() / board.tileSize;
            int row = e.getY() / board.tileSize;

            Move move = new Move(board, board.selectedPiece, col, row);

            if (board.isValidMove(move)) {

                board.makeMove(move);

            } else {

                // Volta peça para o lugar original
                board.selectedPiece.xPos = board.selectedPiece.col * board.tileSize;
                board.selectedPiece.yPos = board.selectedPiece.row * board.tileSize;
            }
        }

        board.selectedPiece = null;
        board.repaint();
    }
}
