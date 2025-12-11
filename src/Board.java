import Pieces.Knight;
import Pieces.Pieces;
import Pieces.Pawn;
import Pieces.Bishop;
import Pieces.King;
import Pieces.Queen;
import Pieces.Rook;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {

    public ArrayList<Pieces> pieces = new ArrayList<>();
    final int tileSize = 85;
    private final int cols = 8;
    private final int rows = 8;

    public Pieces selectedPiece;

    Input input = new Input(this);


    public Board() {
        this.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));
        addPieces();

        this.addMouseListener(input);
        this.addMouseMotionListener(input);
    }

    public Pieces getPiece(int col, int row) {

        for (Pieces p : pieces) {
            if (p.col == col && p.row == row){
                return p;
            }
        }

        return null;
    }
    public void makeMove(Move move){
        move.pieces.col = move.newCol;
        move.pieces.row = move.newRow;
        move.pieces.xPos = move.newCol * tileSize;
        move.pieces.yPos = move.newRow * tileSize;

        capture(move);
    }

    public void capture(Move move){
        pieces.remove(move.capture);
    }



    public boolean isValidMove(Move move) {
        if(move.capture == null)
            return true;

        return move.pieces.isWhite != move.capture.isWhite;


}

    public void addPieces() {
        //Somente as peças brancas (White)
        addPieces(new Knight(1, 7, true));
        addPieces(new Knight(6, 7, true));
        addPieces(new Bishop (2, 7, true));
        addPieces(new Bishop (5, 7, true));
        addPieces(new Queen (3, 7, true));
        addPieces(new Rook (0, 7, true));
        addPieces(new Rook (7, 7, true));
        addPieces(new King(4, 7, true));


        //Laço para adicionar as 8 peças do peão Branco (White)
        for (int w = 0; w < 8; w++)
            addPieces(new Pawn (w, 6, true));

        //Somente as peças pretas (Black)
        addPieces(new Knight(1, 0, false));
        addPieces(new Knight(6, 0, false));
        addPieces(new Bishop (2, 0, false));
        addPieces(new Bishop (5, 0, false));
        addPieces(new Queen (3, 0, false));
        addPieces(new Rook (0, 0, false));
        addPieces(new Rook (7, 0, false));
        addPieces(new King(4, 0, false));

        //Laço para adicionar as 8 peças do peão Preto (Black)
        for (int b = 0; b < 8; b++)
            addPieces(new Pawn (b, 1, false));
    }
    public void addPieces(Pieces p) {
        pieces.add(p);
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;


        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                g2d.setColor((c + r) % 2 == 0 ? Color.WHITE : Color.BLACK);

                g2d.fillRect(
                        c * tileSize,
                        r * tileSize,
                        tileSize,
                        tileSize
                );

        }

            for (Pieces p : pieces) {
                if (p.sprite != null) {
                    g2d.drawImage(
                            p.sprite,
                            p.col * tileSize,
                            p.row * tileSize,
                            tileSize,
                            tileSize,
                            null);
                    g2d.drawImage(p.sprite, p.xPos, p.yPos, 85, 85, null);

}
        }
    }
}
}