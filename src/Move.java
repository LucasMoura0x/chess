import Pieces.Pieces;

public class Move {

    int oldCol;
    int oldRow;
    int newCol;
    int newRow;

    Pieces pieces;
    Pieces capture;

    public Move(Board board, Pieces pieces, int newCol, int newRow) {

        this.oldCol = pieces.col;
        this.oldRow = pieces.row;
        this.newCol = newCol;
        this.newRow = newRow;
        this.pieces = pieces;
        this.capture = board.getPiece(newCol, newRow);
    }
}
