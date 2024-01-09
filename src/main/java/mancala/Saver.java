package mancala;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Saver{
    static String direc = "assets";

    public static void saveObject(final Serializable toSave, final String filename){
        try(ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream(direc + "/" + filename))){
            oOut.writeObject(toSave);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public static Serializable loadObject(String filename) throws IOException{
        try(ObjectInputStream oIn = new ObjectInputStream(new FileInputStream(direc+ "/" +filename))){
            return (Serializable) oIn.readObject();
        } catch(IOException | ClassNotFoundException e){
            throw (IOException)new IOException().initCause(e);
        }
    }
    public static void mkdir(){
        File directory = new File(direc);
        if(!directory.exists()){
            directory.mkdirs();
        }
    }   
}
