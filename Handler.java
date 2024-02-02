import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

public class Handler implements Serializable {

    public static ArrayList<Integer> done = new ArrayList<Integer>();
    public static int flaggedCells = 0;
    public static int discoveredCells = 0;

    public static boolean win_lose = false;
    public static int correctFlagged = 0;
    public static boolean started = false;

    public Handler(){

    }

    public void click(int pos) {
        if (!Grid.cellGrid.get(pos).isFlagged()) {
            if(!started && Grid.cellGrid.get(pos).getType() == 1){
                boolean picked = false;
                while(!picked) {
                    int minePosition = (int) (Math.random() * Grid.bound);
                    if (!Grid.mines.contains(minePosition)) {
                        Grid.mines.add(minePosition);
                        Grid.cellGrid.get(minePosition).type = 1;
                        picked = true;
                    }
                }
                for(int i=0;i<Grid.mines.size();i++){
                    if(Grid.mines.get(i) == pos){
                        Grid.cellGrid.get(Grid.mines.get(i)).type = 0;
                        Grid.mines.remove(i);
                        break;
                    }
                }
                Searcher.dfs(pos,0);
            }
            started = true;
            Grid.cellGrid.get(pos).setEnabled(false);
            Searcher.dfs(pos,0);
            Settings.timer.time = 11;
            if(Settings.player.multi){
                Settings.player.changeTurn();
            }
            System.out.println("dfs done");
            Settings.computer.play_move();
            if (done.size() == Grid.bound - Settings.game.MINECOUNT || win_lose ) {
                int leftMines = Game.MINECOUNT - correctFlagged;
                Settings.player.score[0]+=leftMines*100;
                Settings.player.score[1]+=leftMines*100;
                Settings.player1.setText("P1: " + String.valueOf(Settings.player.score[0]));
                Settings.player2.setText("P2: " + String.valueOf(Settings.player.score[1]));
                Settings.timer.count = false;
                for (int x = 0; x < Grid.cellGrid.size(); x++) {
                    if (Grid.cellGrid.get(x).getType() == 1) {
                        Grid.cellGrid.get(x).setEnabled(false);
                        //Grid.cellGrid.get(x).setText("X");
                        Icon ico = new ImageIcon("wiMine.jpg");
                        Grid.cellGrid.get(x).setIcon(ico);
                    } else {
                        Grid.cellGrid.get(x).setEnabled(false);
                    }
                }
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
                    Settings.win_label.setText("YOU WIN");
                }
                Settings.win_frame.setVisible(true);
                win_lose = false;
            }
        }
    }

    public void rightClick(int pos) {
        if(!Grid.cellGrid.get(pos).isDiscovered()) {
            if (!Grid.cellGrid.get(pos).isFlagged()) {
                Grid.cellGrid.get(pos).setFlagged(true);
                //cell.setText("F");
                Icon ico = new ImageIcon("Marked.jpg");
                Grid.cellGrid.get(pos).setIcon(ico);
                flaggedCells++;
                if(Grid.cellGrid.get(pos).type == 1) {
                    Handler.correctFlagged++;
                    Settings.player.score[Settings.player.turn] += 5;
                    Settings.player1.setText("P1: " + String.valueOf(Settings.player.score[0]));
                    Settings.player2.setText("P2: " + String.valueOf(Settings.player.score[1]));
                }
                else{
                    Settings.player.score[Settings.player.turn] -= 1;
                    Settings.player1.setText("P1: " + String.valueOf(Settings.player.score[0]));
                    Settings.player2.setText("P2: " + String.valueOf(Settings.player.score[1]));
                }
                Settings.timer.time = 11;
                Window.update(flaggedCells);
            }
            else {
                Grid.cellGrid.get(pos).setFlagged(false);
                //cell.setText("");
                Icon ico = new ImageIcon("NotClicked.png");
                Grid.cellGrid.get(pos).setIcon(ico);
                flaggedCells--;
                if(Grid.cellGrid.get(pos).type == 1) {
                    Handler.correctFlagged--;
                }
                Window.update(flaggedCells);
            }
            if(Settings.player.multi){
                Settings.player.changeTurn();
            }
            Computer.play_move();
        }
        if((correctFlagged == Settings.game.MINECOUNT && done.size() == Grid.bound - Settings.game.MINECOUNT)||win_lose){
            Settings.timer.count = false;
            for(int x = 0; x < Grid.cellGrid.size(); x++) {
                if(Grid.cellGrid.get(x).getType() == 1) {
                    Grid.cellGrid.get(x).setEnabled(false);
                    //Grid.cellGrid.get(x).setText("X");
                    Icon ico = new ImageIcon("wiMine.jpg");
                    Grid.cellGrid.get(x).setIcon(ico);
                }
                else {
                    Grid.cellGrid.get(x).setEnabled(false);
                }
            }
            win_lose = false;
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
                Settings.win_label.setText("YOU WIN");
            }
            Settings.win_frame.setVisible(true);
        }
    }
}
