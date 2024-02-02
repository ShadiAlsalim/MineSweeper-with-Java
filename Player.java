import java.io.Serializable;

public class Player implements Serializable {
    public static int turn = 0;
    public static int[] score = new int[2];
    public static boolean multi = false;

    public Player(){

    }
    public void changeTurn(){
        turn = (turn+1)%2;
        Settings.timer.time = 11;
        System.out.println(turn);
    }

}
