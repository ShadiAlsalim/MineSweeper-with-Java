import java.io.Serializable;

public class Computer implements Serializable {
    public static boolean Playing = false;


    public Computer() {

    }

    public static void play_move() {
        if (Playing) {

            boolean play = false;
            if (play == false) {
                for(int i=0;i<Grid.bound;i++){
                    if(Grid.cellGrid.get(i).getType()!=1 && Grid.cellGrid.get(i).isDiscovered() == false){
                        play = true;
                        Searcher.dfs(i,0);
                        break;
                    }
                }
            }
            if(play == false){
                for (int i = 0; i < Grid.bound; i++) {
                    if (Grid.cellGrid.get(i).type == 1 && Grid.cellGrid.get(i).isFlagged() == false) {
                        System.out.println("comp played: "+i/Game.GRIDROWS + " " + i%Game.GRIDCOLS);
                        play = true;
                        Grid.cellGrid.get(i).rightClickButton();
                        break;
                    }
                }
            }
            if (play == false) {
                for (int i = 0; i < Grid.bound; i++) {
                    if (Grid.cellGrid.get(i).type != 1 && Grid.cellGrid.get(i).isFlagged() == true) {
                        play = true;
                        Grid.cellGrid.get(i).rightClickButton();
                        break;
                    }
                }
            }
            if(play == false){
                Handler.correctFlagged = Game.MINECOUNT;
                Handler.win_lose = true;
            }
        }
    }
}
