import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Settings implements Serializable{
    public static Game game;
    public static Timer timer;
    public static Player player;
    public static Computer computer;
    public static JFrame second_frame = new JFrame();
    public static JLabel win_label = new JLabel("YOU WON!");
    public static JFrame win_frame = new JFrame();
    public static JLabel label_row = new JLabel("Rows:");
    public static JTextField entry_row = new JTextField();
    public static JLabel label_col = new JLabel("Columns:");
    public static JTextField entry_col = new JTextField();
    public static JLabel label_bomb = new JLabel("Bombs:");
    public static JTextField entry_bomb = new JTextField();
    public static JCheckBox comp = new JCheckBox("Computer?");
    public static JCheckBox multi = new JCheckBox("Multiplayer?");
    public static JButton start = new JButton("start");
    public static JButton save = new JButton("Save Game");
    public static JButton load = new JButton("Load Game");
    public static JTextField entry_load = new JTextField();
    public static JLabel player1 = new JLabel("P1: ");
    public static JLabel player2 = new JLabel("P2: ");
    public static JLabel timeLeft = new JLabel("Time:");
    public Settings(){

        player = new Player();
        computer = new Computer();
        win_frame.setSize(100,150);
        win_label.setVerticalAlignment(SwingConstants.CENTER);
        win_label.setHorizontalAlignment(SwingConstants.CENTER);
        win_frame.add(win_label);
        win_frame.setVisible(false);
        win_frame.setBounds(225,300,100,100);
        second_frame.setSize(365,400);

        comp.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                if (comp.isSelected()) {
                    computer.Playing = true;
                    System.out.println("YES");
                }
                else {
                    computer.Playing = false;
                    System.out.println("NO");
                }
            }
        });

        multi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(multi.isSelected()){
                    player.multi = true;
                }
                else{
                    player.multi = false;
                }
            }
        });

        label_row.setBounds(25,0,100,50);
        second_frame.add(label_row);

        label_col.setBounds(125,0,100,50);
        second_frame.add(label_col);

        label_bomb.setBounds(225,0,100,50);
        second_frame.add(label_bomb);

        entry_row.setBounds(25,50,100,50);
        second_frame.add(entry_row);

        entry_col.setBounds(125,50,100,50);
        second_frame.add(entry_col);

        entry_bomb.setBounds(225,50,100,50);
        second_frame.add(entry_bomb);

        comp.setBounds(25,100,100,50);
        second_frame.add(comp);

        multi.setBounds(225, 100, 100, 50);
        second_frame.add(multi);

        player1.setBounds(25, 160,100,50);
        second_frame.add(player1);

        player2.setBounds(230, 160,100,50);
        second_frame.add(player2);

        timeLeft.setBounds(130,160,100,50);
        second_frame.add(timeLeft);

        second_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        start.setBounds(125,100,100,50);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = Integer.parseInt(entry_row.getText());
                int c = Integer.parseInt(entry_col.getText());
                int b = Integer.parseInt(entry_bomb.getText());
                Game.GRIDROWS = r;
                Game.GRIDCOLS = c;
                Game.MINECOUNT = b;
                Game.WIDTH = 60*c;
                Game.HEIGHT = 60*r;
                Handler.flaggedCells = 0;
                Handler.correctFlagged = 0;
                Handler.discoveredCells = 0;
                Handler.done.clear();
                Handler.win_lose = false;
                Handler.started = false;
                Grid.mines.clear();
                Grid.cellGrid.clear();
                Grid.bound = r * c;
                player1.setText("P1: ");
                player2.setText("P2: ");
                player.score[0] = 0;
                player.score[1] = 0;
                player.turn = 0;
                game = new Game();
                timer = new Timer();
                timer.count = true;
                timer.start();
            }
        });

        save.setBounds(25,225,150,50);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveLoad.save();
            }
        });
        second_frame.add(save);

        entry_load.setBounds(25,280,300,50);
        second_frame.add(entry_load);

        load.setBounds(175,225,150,50);
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileLoad = entry_load.getText();
                SaveLoad.load(fileLoad);
                System.out.println("game loaded");
            }
        });
        second_frame.add(load);

        second_frame.setTitle("Setting Menu");
        second_frame.add(start);

        JLabel empty = new JLabel();
        second_frame.add(empty);

        second_frame.setResizable(false);
        second_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        second_frame.setVisible(true);
    }
}
