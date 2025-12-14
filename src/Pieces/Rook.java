package Pieces;

public class Rook extends Pieces {

    public Rook(int col, int row, boolean isWhite) {
        super("rook", isWhite, col, row, 5);
    }

    public boolean isValidMovement(int col, int row) {
        // A torre só anda em linha reta
        return this.col == col || this.row == row;
    }
}
