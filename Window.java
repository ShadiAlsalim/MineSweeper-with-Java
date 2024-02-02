import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Window implements Serializable {

    public static JFrame frame;
    public static String title;

    public Window(int width, int height, int gridCols, int gridRows, String title, Game game, Handler handler) {
        Window.title = title;
        frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JPanel panel;panel = new Grid(new GridLayout(gridRows, gridCols), handler);

        frame.setContentPane(panel);
        frame.pack();

        frame.setVisible(true);
    }

    public static void update(int flagged) {
        frame.setTitle(title + "Mines: " + Game.MINECOUNT + " - Flags: " + flagged);
    }
}
