package Pieces;

public class Queen extends  Pieces {
    public Queen(int col, int row, boolean isWhite) {
        super("queen", isWhite, col, row, 3);
    }
    public boolean isValidMovement(int col, int row) {
        boolean straight = (this.col == col || this.row == row);
        boolean diagonal = Math.abs(this.col - col) == Math.abs(this.row - row);
        return straight || diagonal;
}
}
