import javax.swing.*;

public class Searcher {
    public static Handler handler;
    public Searcher(Handler handler){
        this.handler = handler;
    }
    public static void dfs(int i, int val){
        //System.out.println(i/Game.GRIDROWS + " " + i%Game.GRIDCOLS);
        if(Grid.cellGrid.get(i).isFlagged() || Grid.cellGrid.get(i).isDiscovered())
            return;
        if(Grid.cellGrid.get(i).getType() == 1 && val == 0){
            Settings.timer.count = false;
            for(int j = 0;j<Grid.bound;j++){
                Grid.cellGrid.get(j).setDiscovered(true);
                Grid.cellGrid.get(j).setEnabled(false);
                if(Grid.cellGrid.get(j).getType() == 1){
                    Icon ico = new ImageIcon("boMine.jpg");
                    Grid.cellGrid.get(j).setIcon(ico);
                    if(Settings.player.multi){
                        if(Settings.player.score[0]>Settings.player.score[1]){
                            Settings.win_label.setText("Player1 WINS!");
                        }
                        else if(Settings.player.score[0]<Settings.player.score[1]){
                            Settings.win_label.setText("Player2 WINS!");
                        }
                        else{
                            Settings.win_label.setText("DRAW!");
                        }
                    }
                    else {
                        Settings.win_label.setText("YOU LOSE");
                    }
                    Settings.win_frame.setVisible(true);
                }
            }
        }

        if(!Handler.done.contains(i)){
            Handler.done.add(i);
        }
        if(Grid.cellGrid.get(i).getType() == 1 && val == 1)
            return;
        if(Grid.cellGrid.get(i).getType() != 1) {
            boolean number = false;
            int dangerCount = 0;
            // 4 corners
            if(i == 0){
                if(Grid.cellGrid.get(i+1).getType() == 1)dangerCount++;
                if(Grid.cellGrid.get(i+Game.GRIDCOLS).getType() == 1)dangerCount++;
                if(Grid.cellGrid.get(i+Game.GRIDCOLS+1).getType() == 1)dangerCount++;
            }
            else if(i == Game.GRIDCOLS-1){
                if(Grid.cellGrid.get(i-1).getType() == 1)dangerCount++;
                if(Grid.cellGrid.get(i+Game.GRIDCOLS).getType() == 1)dangerCount++;
                if(Grid.cellGrid.get(i+Game.GRIDCOLS-1).getType() == 1)dangerCount++;
            }
            else if(i == Grid.bound-1){
                if(Grid.cellGrid.get(i-1).getType() == 1)dangerCount++;
                if(Grid.cellGrid.get(i-Game.GRIDCOLS-1).getType() == 1)dangerCount++;
                if(Grid.cellGrid.get(i-Game.GRIDCOLS).getType() == 1)dangerCount++;
            }
            else if(i+Game.GRIDCOLS == Grid.bound){
                if(Grid.cellGrid.get(i+1).getType() == 1)dangerCount++;
                if(Grid.cellGrid.get(i-Game.GRIDCOLS).getType() == 1)dangerCount++;
                if(Grid.cellGrid.get(i-Game.GRIDCOLS+1).getType() == 1)dangerCount++;
            }
            // 4 sides
            else if(i<Game.GRIDCOLS){
                if(Grid.cellGrid.get(i+1).getType() == 1)dangerCount++;
                if(Grid.cellGrid.get(i+Game.GRIDCOLS).getType() == 1)dangerCount++;
                if(Grid.cellGrid.get(i+Game.GRIDCOLS+1).getType() == 1)dangerCount++;
                if(Grid.cellGrid.get(i+Game.GRIDCOLS-1).getType() == 1)dangerCount++;
                if(Grid.cellGrid.get(i-1).getType() == 1)dangerCount++;
            }
            else if(i%Game.GRIDCOLS == 0){
                if(Grid.cellGrid.get(i+1).getType() == 1)dangerCount++;
                if(Grid.cellGrid.get(i+Game.GRIDCOLS).getType() == 1)dangerCount++;
                if(Grid.cellGrid.get(i-Game.GRIDCOLS).getType() == 1)dangerCount++;
                if(Grid.cellGrid.get(i+Game.GRIDCOLS+1).getType() == 1)dangerCount++;
                if(Grid.cellGrid.get(i- Game.GRIDCOLS+1).getType() == 1)dangerCount++;
            }
            else if(i%Game.GRIDCOLS == Game.GRIDCOLS-1){
                if(Grid.cellGrid.get(i-1).getType() == 1)dangerCount++;
                if(Grid.cellGrid.get(i-Game.GRIDCOLS).getType() == 1)dangerCount++;
                if(Grid.cellGrid.get(i+Game.GRIDCOLS).getType() == 1)dangerCount++;
                if(Grid.cellGrid.get(i-Game.GRIDCOLS-1).getType() == 1)dangerCount++;
                if(Grid.cellGrid.get(i+Game.GRIDCOLS-1).getType() == 1)dangerCount++;
            }
            else if(i+Game.GRIDCOLS>Grid.bound){
                if(Grid.cellGrid.get(i+1).getType() == 1)dangerCount++;
                if(Grid.cellGrid.get(i-1).getType() == 1)dangerCount++;
                if(Grid.cellGrid.get(i- Game.GRIDCOLS+1).getType() == 1)dangerCount++;
                if(Grid.cellGrid.get(i-Game.GRIDCOLS).getType() == 1)dangerCount++;
                if(Grid.cellGrid.get(i-Game.GRIDCOLS-1).getType() == 1)dangerCount++;
            }
            // every other cell inside
            else{
                //right and left
                if(Grid.cellGrid.get(i+1).getType() == 1)dangerCount++;
                if(Grid.cellGrid.get(i-1).getType() == 1)dangerCount++;
                // up, upper right, upper left
                if(Grid.cellGrid.get(i-Game.GRIDCOLS).getType() == 1)dangerCount++;
                if(Grid.cellGrid.get(i-Game.GRIDCOLS+1).getType() == 1)dangerCount++;
                if(Grid.cellGrid.get(i-Game.GRIDCOLS-1).getType() == 1)dangerCount++;
                // down, lower right, lower left
                if(Grid.cellGrid.get(i+Game.GRIDCOLS).getType() == 1)dangerCount++;
                if(Grid.cellGrid.get(i+Game.GRIDCOLS+1).getType() == 1)dangerCount++;
                if(Grid.cellGrid.get(i+Game.GRIDCOLS-1).getType() == 1)dangerCount++;
            }
            if(dangerCount!=0) {
                number = true;
                String num = String.valueOf(dangerCount);
                Grid.cellGrid.get(i).setText(num);
                Settings.player.score[Settings.player.turn]+=dangerCount;
                Settings.player1.setText("P1: " + String.valueOf(Settings.player.score[0]));
                Settings.player2.setText("P2: " + String.valueOf(Settings.player.score[1]));
            }
            Grid.cellGrid.get(i).setDiscovered(true);
            Grid.cellGrid.get(i).setEnabled(false);
            if(number)
                return;
        }
        Grid.cellGrid.get(i).setEnabled(false);
        Grid.cellGrid.get(i).setDiscovered(true);
        // 4 corners
        Settings.player.score[Settings.player.turn]+=1;
        Settings.player1.setText("P1: " + String.valueOf(Settings.player.score[0]));
        Settings.player2.setText("P2: " + String.valueOf(Settings.player.score[1]));
        if(i == 0){
            dfs(i+1, 1);
            dfs(i+Game.GRIDCOLS,1);
            dfs(i+Game.GRIDCOLS+1,1);
        }
        else if(i == Game.GRIDCOLS-1){
            dfs(i-1,1);
            dfs(i+Game.GRIDCOLS,1);
            dfs(i+Game.GRIDCOLS-1,1);
        }
        else if(i == Grid.bound-1){
            dfs(i-1,1);
            dfs(i-Game.GRIDCOLS-1,1);
            dfs(i-Game.GRIDCOLS,1);
        }
        else if(i+Game.GRIDCOLS == Grid.bound){
            dfs(i+1,1);
            dfs(i-Game.GRIDCOLS,1);
            dfs(i-Game.GRIDCOLS+1,1);
        }
        // 4 sides
        else if(i<Game.GRIDCOLS){
            dfs(i+1,1);
            dfs(i-1,1);
            dfs(i+Game.GRIDCOLS,1);
            dfs(i+Game.GRIDCOLS+1,1);
            dfs(i+Game.GRIDCOLS-1,1);
        }
        else if(i%Game.GRIDCOLS == 0){
            dfs(i+1,1);
            dfs(i+Game.GRIDCOLS,1);
            dfs(i-Game.GRIDCOLS,1);
            dfs(i+Game.GRIDCOLS+1,1);
            dfs(i-Game.GRIDCOLS+1,1);
        }
        else if(i%Game.GRIDCOLS == Game.GRIDCOLS-1){
            dfs(i-1,1);
            dfs(i-Game.GRIDCOLS,1);
            dfs(i+Game.GRIDCOLS,1);
            dfs(i-Game.GRIDCOLS-1,1);
            dfs(i+Game.GRIDCOLS-1,1);
        }
        else if(i+Game.GRIDCOLS>Grid.bound){
            dfs(i+1,1);
            dfs(i-1,1);
            dfs(i-Game.GRIDCOLS+1,1);
            dfs(i-Game.GRIDCOLS,1);
            dfs(i-Game.GRIDCOLS-1,1);
        }
        // every other cell inside
        else{
            //right and left
            dfs(i+1,1);
            dfs(i-1,1);
            // up, upper right, upper left
            dfs(i-Game.GRIDCOLS,1);
            dfs(i-Game.GRIDCOLS+1,1);
            dfs(i-Game.GRIDCOLS-1,1);
            // down, lower right, lower left
            dfs(i+Game.GRIDCOLS,1);
            dfs(i+Game.GRIDCOLS+1,1);
            dfs(i+Game.GRIDCOLS-1,1);
        }
        //end of dfs function
    }
}
