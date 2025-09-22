import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Principal {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("bash", "-c", "ls");
        try {
            Process p = pb.start();
            InputStreamReader isr = new InputStreamReader((p.getInputStream()));
            BufferedReader br = new BufferedReader(isr);

            System.out.println("\n" + "\n" + br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
