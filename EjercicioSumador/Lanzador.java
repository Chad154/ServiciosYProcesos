
package EjercicioSumador;

import java.io.*;

public class Lanzador {
        public static void main(String[] args) {
                Lanzador l = new Lanzador();
                l.lanzarSumador(1, 2, "result1.txt");
                l.lanzarSumador(6, 8, "result2.txt");
                System.out.println("Ok");
        }

        public void lanzarSumador(Integer n1, Integer n2, String fichResultado) {
                String clase = "EjercicioSumador.Sumador";
                ProcessBuilder pb;
                try {
                        // Carpeta raíz del proyecto (donde está el paquete EjercicioSumador)
                        File f = new File("/home/chad/Escritorio/ServiciosYProcesos");

                        pb = new ProcessBuilder(
                                        "java", clase,
                                        n1.toString(),
                                        n2.toString());

                        pb.directory(f); // importante: debe contener la carpeta EjercicioSumador

                        pb.redirectError(new File("errores.txt"));
                        pb.redirectOutput(new File(fichResultado));
                        pb.start();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
}
