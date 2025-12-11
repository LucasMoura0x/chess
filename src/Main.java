import javax.swing.*;
import java.awt.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
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

    }
}