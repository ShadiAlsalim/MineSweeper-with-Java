import java.io.*;
import java.util.Set;

public class SaveLoad implements Serializable{
    public static void save(){
        try {
            FileOutputStream fileOutputStream
                    = new FileOutputStream("save.ser");
            ObjectOutputStream oos
                    = new ObjectOutputStream(fileOutputStream);
            oos.writeObject(Settings.game);
            oos.flush();
            oos.close();
            System.out.println("game saved");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void load(String file){
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Settings.game = (Game) objectInputStream.readObject();
            objectInputStream.close();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
