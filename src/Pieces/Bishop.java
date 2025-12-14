package Pieces;

public class Bishop extends Pieces {

    public Bishop(int col, int row, boolean isWhite) {
        super("bishop", isWhite, col, row, 3);
    }

    @Override
    public boolean isValidMovement(int col, int row) {
        // O bispo anda apenas em diagonais
        return Math.abs(this.col - col) == Math.abs(this.row - row);
    }
}
