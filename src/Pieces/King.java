package Pieces;

public class King extends Pieces {
    public King(int col, int row, boolean isWhite) {
        super("king", isWhite, col, row, 3);
    }
    public boolean isValidMovement(int col, int row) {
        int kx = Math.abs(col - this.col);
        int ky = Math.abs(row - this.row);

        return kx <= 1 && ky <= 1 && !(kx == 0 && ky == 0);
    }
}
