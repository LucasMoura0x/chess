import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess");
        frame.getContentPane().setBackground(Color.black);
        frame.setLayout(new GridLayout());
        frame.setMinimumSize(new Dimension(695, 715));

        Board board = new Board();
        frame.add(board);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);

        // Toca a música
        MusicTheme music = new MusicTheme();
        music.play("src/resources/ChessMusic.wav");

        // Para a música ao fechar o app
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                music.stop();
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
