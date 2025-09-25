import java.io.IOException;
import java.io.InputStream;

public class Principal2 {
        public static void main(String[] args) {
            ProcessBuilder pb = new ProcessBuilder("bash","-c","pepe");
        
        try{
            Process p = pb.start();

            InputStream is = p.getErrorStream();

            int c;
            while ((c  = is.read())!= -1) {
                System.out.print((char) c);
            }
            is.close();
        }catch(IOException e){
            e.getStackTrace();
        }
    }
}
