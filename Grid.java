import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Grid extends JPanel implements Serializable {

    public static int bound = Game.GRIDCOLS * Game.GRIDROWS;

    public boolean picked = false;

    public static ArrayList<Integer> mines = new ArrayList<Integer>();

    public static ArrayList<Cell> cellGrid = new ArrayList<Cell>();

    public Grid(GridLayout g, Handler h) {
        super(g);
        createCells(h);
        addCells();
    }

    public void createCells(Handler h) {
        for(int i = 1; i <= Game.MINECOUNT; i++) {
            while(!picked) {
                int minePosition = (int) (Math.random() * bound);
                if (!mines.contains(minePosition)) {
                    mines.add(minePosition);
                    picked = true;
                }
            }
            picked = false;
        }
        System.out.println(mines.get(0));
        for(int i = 0; i < bound; i++) {
            if(mines.contains(i)){
                cellGrid.add(new Cell(1, i, false, false, h));
            }
            else{
                if(mines.contains(i+1) ||
                        mines.contains(i-1)||
                        mines.contains(i+Game.GRIDCOLS) ||
                        mines.contains(i+Game.GRIDCOLS+1) ||
                        mines.contains(i+Game.GRIDCOLS-1) ||
                        mines.contains(i-Game.GRIDCOLS) ||
                        mines.contains(i-Game.GRIDCOLS+1) ||
                        mines.contains(i-Game.GRIDCOLS-1)){
                    cellGrid.add(new Cell(2, i, false, false, h));
                }
                else{
                    cellGrid.add(new Cell(0, i, false, false, h));
                }
            }
        }
    }

    public void addCells() {
        for(int i = 0; i < cellGrid.size(); i++) {
            add(cellGrid.get(i));
        }
    }
}
