package Pieces;

public class Knight extends Pieces {

    public Knight(int col, int row, boolean isWhite) {
        super("knight", isWhite, col, row, 3);
    }

    public boolean isValidMovement(int col, int row) {

        // Movimento do cavalo: (2x1) ou (1x2)
        int dCol = Math.abs(col - this.col);
        int dRow = Math.abs(row - this.row);

        return dCol * dRow == 2;
    }
}
