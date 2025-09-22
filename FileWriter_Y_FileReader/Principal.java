import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Principal {

    public static void main(String[] args) {
        File f = new File("miFichero.txt");
        try{
            FileReader fw = new FileReader(f);
            BufferedReader bw = new BufferedReader(fw);

            bw.close();
            fw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}