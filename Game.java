import java.io.Serializable;

public class Game extends Thread implements Serializable {

    public Handler handler = new Handler();
    public static int WIDTH = 720;
    public static int HEIGHT = 720;
    public static int GRIDCOLS = 10;
    public static int GRIDROWS = 10;
    public static int MINECOUNT = (int) Math.round(GRIDCOLS * GRIDROWS * 0.1);
    public Window window = new Window(WIDTH, HEIGHT, GRIDCOLS, GRIDROWS, "Minesweeper - 2.0", Game.this, this.handler);

    @Override
    public void run(){
        new Game();
    }

    public Game() {
        new Searcher(this.handler);
        window.update(handler.flaggedCells);
    }
}
