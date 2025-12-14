package Pieces;

public class Pawn extends Pieces {

    public Pawn(int col, int row, boolean isWhite) {
        super("pawn", isWhite, col, row, 1);
    }

    @Override
    public boolean isValidMovement(int col, int row) {

        int direction = isWhite ? -1 : 1; // branco sobe (-1), preto desce (+1)
        int colDiff = col - this.col;
        int rowDiff = row - this.row;

        // --------------------------------------------
        // 1 — Movimento simples para frente
        // --------------------------------------------
        if (colDiff == 0 && rowDiff == direction) {
            // só pode andar reto se NÃO tiver peça no caminho
            return true;
        }

        // --------------------------------------------
        // 2 — Movimento duplo (primeiro lance)
        // --------------------------------------------
        if (colDiff == 0 && rowDiff == 2 * direction) {

            boolean firstMove = (isWhite && this.row == 6) || (!isWhite && this.row == 1);

            if (firstMove) {
                return true;
            }
        }

        // --------------------------------------------
        // 3 — Captura diagonal
        // --------------------------------------------
        if (Math.abs(colDiff) == 1 && rowDiff == direction) {
            return true; // captura será validada no Board
        }

        return false;
    }
}
