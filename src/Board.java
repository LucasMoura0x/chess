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
import java.util.Random;

public class Board extends JPanel {

    public ArrayList<Pieces> pieces = new ArrayList<>();
    final int tileSize = 85;
    private final int cols = 8;
    private final int rows = 8;

    public Pieces selectedPiece;
    public int mouseX, mouseY;

    public boolean whiteTurn = true;       // turno atual
    public boolean playerIsWhite;   // cor do jogador



    Input input = new Input(this);

    public Board() {
        this.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));

        // Sorteio da cor do jogador
        playerIsWhite = new Random().nextBoolean();
        System.out.println("Você está com as peças " + (playerIsWhite ? "brancas" : "pretas"));

        whiteTurn = true; // branco sempre começa
        addPieces();

        this.addMouseListener(input);
        this.addMouseMotionListener(input);
    }



    // -----------------------------------------------------------
    // RETORNA PEÇA NA POSIÇÃO
    // -----------------------------------------------------------
    public Pieces getPiece(int col, int row) {
        for (Pieces p : pieces) {
            if (p.col == col && p.row == row) return p;
        }
        return null;
    }

    // -----------------------------------------------------------
    // MOVE PEÇA
    // -----------------------------------------------------------
    public void makeMove(Move move) {
        move.pieces.col = move.newCol;
        move.pieces.row = move.newRow;
        move.pieces.xPos = move.newCol * tileSize;
        move.pieces.yPos = move.newRow * tileSize;

        capture(move);
         // troca turno
    }

    public void capture(Move move) {
        if (move.capture != null) pieces.remove(move.capture);
    }

    // -----------------------------------------------------------
    // CHECA CAMINHO BLOQUEADO (TORRE / BISPO / RAINHA)
    // -----------------------------------------------------------
    public boolean isPathBlocked(Pieces p, int newCol, int newRow) {
        int colDir = Integer.compare(newCol, p.col);
        int rowDir = Integer.compare(newRow, p.row);

        int c = p.col + colDir;
        int r = p.row + rowDir;

        while (c != newCol || r != newRow) {
            if (getPiece(c, r) != null) return true;
            c += colDir;
            r += rowDir;
        }
        return false;
    }

    // -----------------------------------------------------------
    // VALIDA MOVIMENTO
    // -----------------------------------------------------------
    public boolean isValidMove(Move move) {
        Pieces p = move.pieces;
        Pieces target = getPiece(move.newCol, move.newRow);

        if (!p.isValidMovement(move.newCol, move.newRow)) return false;

        if (target != null && target.isWhite == p.isWhite) return false;

        // Movimento (torre, bispo, rainha)
        if (p.name.equals("rook") || p.name.equals("bishop") || p.name.equals("queen")) {
            if (isPathBlocked(p, move.newCol, move.newRow)) return false;
        }

        // Movimento peão
        if (p.name.equals("pawn")) {
            int colDiff = move.newCol - p.col;
            int rowDiff = move.newRow - p.row;
            int direction = p.isWhite ? -1 : 1;

            // Captura diagonal
            if (Math.abs(colDiff) == 1 && rowDiff == direction) {
                if (target == null || target.isWhite == p.isWhite) return false;
            }

            // Andar para frente
            if (colDiff == 0) {
                if (rowDiff == direction && target != null) return false;
                if (rowDiff == 2 * direction && p.row == (p.isWhite ? 6 : 1)) {
                    if (getPiece(p.col, p.row + direction) != null || target != null) return false;
                }
                if (rowDiff != direction && rowDiff != 2 * direction) return false;
            }

            if (Math.abs(colDiff) == 1 && rowDiff != direction) return false;
            return !wouldBeInCheck(move);
        }

        return true;
    }

    // -----------------------------------------------------------
    // SIMULA MOVIMENTO PARA CHECAR XEQUE
    // -----------------------------------------------------------
    public boolean wouldBeInCheck(Move move) {
        Pieces p = move.pieces;
        Pieces target = getPiece(move.newCol, move.newRow);

        int oldCol = p.col;
        int oldRow = p.row;

        // simula
        p.col = move.newCol;
        p.row = move.newRow;
        if (target != null) pieces.remove(target);

        boolean inCheck = isKingInCheck(p.isWhite);

        // desfaz simulação
        p.col = oldCol;
        p.row = oldRow;
        if (target != null) pieces.add(target);

        return inCheck;
    }

    // -----------------------------------------------------------
    // CHEQUE E CHEQUE-MATE
    // -----------------------------------------------------------
    public boolean isKingInCheck(boolean isWhite) {
        Pieces king = null;
        for (Pieces p : pieces) {
            if (p.name.equals("king") && p.isWhite == isWhite) {
                king = p;
                break;
            }
        }
        if (king == null) return false;

        for (Pieces p : pieces) {
            if (p.isWhite != isWhite) continue; {
                Move testMove = new Move(this, p, king.col, king.row);
                if (isValidMove(testMove)) return true;
            }
        }
        return false;
    }

    public boolean isCheckmate(boolean isWhite) {
        if(isKingInCheck((isWhite))) return false;
        for (Pieces p : pieces) {
            if (p.isWhite != isWhite) continue;

            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    Move move = new Move(this, p, c, r);
                    if (!isValidMove(move)) continue;

                    Pieces captured = getPiece(c, r);
                    int oldCol = p.col;
                    int oldRow = p.row;

                    // simula
                    p.col = c;
                    p.row = r;
                    if (captured != null) pieces.remove(captured);

                    boolean stillInCheck = isKingInCheck(isWhite);

                    // desfaz
                    p.col = oldCol;
                    p.row = oldRow;
                    if (captured != null) pieces.add(captured);

                    if (!stillInCheck)
                        return false;
                }
            }
        }
        return true;
    }

    // -----------------------------------------------------------
    // BOT SIMPLES
    // -----------------------------------------------------------
    public void botMove(boolean isWhite) {
        ArrayList<Move> validMoves = new ArrayList<>();

        for (Pieces p : pieces) {
            if (p.isWhite != isWhite) continue;

            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    Move move = new Move(this, p, c, r);
                    if (!isValidMove(move)) continue;

                    validMoves.add(move);
                }
            }
        }

        if (!validMoves.isEmpty()) {
            Move chosen = validMoves.get(new Random().nextInt(validMoves.size()));
            makeMove(chosen);
            repaint();

            // Verifica xeque / xeque-mate
            if (isKingInCheck(!isWhite)) System.out.println("Xeque!");
            if (isCheckmate(!isWhite)) System.out.println("Xeque-mate! Vitória do " + (isWhite ? "preto" : "branco"));
        }
    }

    // -----------------------------------------------------------
    // JOGADOR FAZ UMA JOGADA
    // -----------------------------------------------------------
    public void playerMove(Move move) {
        if ((playerIsWhite && whiteTurn || !playerIsWhite && !whiteTurn) && isValidMove(move)) {
            makeMove(move);
            repaint();

            if (isKingInCheck(!playerIsWhite)) System.out.println("Xeque!");
            if (isCheckmate(!playerIsWhite)) System.out.println("Xeque-mate! Vitória do " + (playerIsWhite ? "branco" : "preto"));

            // Jogada do bot
            if (!isCheckmate(playerIsWhite)) {
                SwingUtilities.invokeLater(() -> {
                    botMove(!playerIsWhite);
                    whiteTurn = !whiteTurn;
                });
            }
        }
    }

    // -----------------------------------------------------------
    // ADICIONA PEÇAS
    // -----------------------------------------------------------
    public void addPieces() {
        // Peças brancas
        addPieces(new Rook(0, 7, true));
        addPieces(new Knight(1, 7, true));
        addPieces(new Bishop(2, 7, true));
        addPieces(new Queen(3, 7, true));
        addPieces(new King(4, 7, true));
        addPieces(new Bishop(5, 7, true));
        addPieces(new Knight(6, 7, true));
        addPieces(new Rook(7, 7, true));
        for (int i = 0; i < 8; i++) addPieces(new Pawn(i, 6, true));

        // Peças pretas
        addPieces(new Rook(0, 0, false));
        addPieces(new Knight(1, 0, false));
        addPieces(new Bishop(2, 0, false));
        addPieces(new Queen(3, 0, false));
        addPieces(new King(4, 0, false));
        addPieces(new Bishop(5, 0, false));
        addPieces(new Knight(6, 0, false));
        addPieces(new Rook(7, 0, false));
        for (int i = 0; i < 8; i++) addPieces(new Pawn(i, 1, false));
    }

    public void addPieces(Pieces p) {
        pieces.add(p);
    }

    // -----------------------------------------------------------
    // DESENHO DO TABULEIRO
    // -----------------------------------------------------------
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // TABULEIRO
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                g2d.setColor((c + r) % 2 == 0 ? Color.WHITE : Color.BLACK);
                g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
            }
        }

        // MOVIMENTOS VÁLIDOS
        if (selectedPiece != null) {
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    Move move = new Move(this, selectedPiece, c, r);
                    if (isValidMove(move)) {
                        g2d.setColor(new Color(124, 252, 0, 128));
                        g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
                    }
                }
            }
        }

        // DESENHAR PEÇAS (exceto a selecionada)
        for (Pieces p : pieces) {
            if (p != selectedPiece) g2d.drawImage(p.sprite, p.xPos, p.yPos, tileSize, tileSize, null);
        }

        // DESENHAR PEÇA SELECIONADA
        if (selectedPiece != null) {
            g2d.drawImage(selectedPiece.sprite, selectedPiece.xPos, selectedPiece.yPos, tileSize, tileSize, null);
        }
    }
}
