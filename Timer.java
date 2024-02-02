public class Timer extends Thread{
    public static int time = 10;
    public static boolean count = true;
    @Override
    public void run(){
        while (count){
            do{
                String tim = "Time: " + String.valueOf(time);
                try{
                    sleep(1000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                time--;
                Settings.timeLeft.setText(tim);
            }while (time>0 && count);
            Settings.player.changeTurn();
            time = 10;
        }
        System.out.println("timer stopped");
    }
}
