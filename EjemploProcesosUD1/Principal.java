package EjemploProcesosUD1;

import java.io.*;

public class Principal {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("bash","-c","ls");
        try {
            //Process p = pb.start();
            File ferror = new File("error.txt");
            File salida  =new File("salida.txt");
            pb.redirectError(ferror);
            pb.redirectOutput(salida);
            Process p = pb.start();
            
            System.out.println("Fin");
            /*InputStream is = p.getErrorStream();//p.getErrorStream(); para sacar por pantalla los errores, el otro(p.getInputStream()) es la salida en caso de q funcione
            int c;
            while ((c=is.read())!=-1) {
                System.out.print((char) c);
            }
            is.close();*/

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}