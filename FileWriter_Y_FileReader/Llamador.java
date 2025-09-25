import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Llamador {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("java","Principal.java");
        try{
            File dir = new File(".\\bin");
            pb.directory(dir);
            Process p = pb.start();

            InputStream is = p.getInputStream();
            int c;
            while ((c = is.read()) != -1 ) {
                System.out.println((char) c);
            }
            is.close();
        }catch(IOException e){
            e.getStackTrace();
        }
    }
}
