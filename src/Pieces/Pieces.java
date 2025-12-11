package Pieces;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Pieces {

    public int col, row;
    public int xPos, yPos;
    public boolean isWhite;
    public String name;
    public int value;

    public BufferedImage sprite;

    public Pieces(String name, boolean isWhite, int col, int row, int value) {

        this.name = name;
        this.isWhite = isWhite;
        this.col = col;
        this.row = row;
        this.value = value;

        this.xPos = col * 85;
        this.yPos = row * 85;

        loadSprite(); // 🔥 Carrega automaticamente a imagem correspondente
    }

    private void loadSprite() {
        String color = isWhite ? "white" : "black";
        String path = "resources/" + name + "_" + color + ".png";

        try (var stream = ClassLoader.getSystemResourceAsStream(path)) {

            if (stream == null)
                throw new RuntimeException("Sprite não encontrada: " + path);

            sprite = ImageIO.read(stream);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar sprite: " + path, e);
        }
    }
}
