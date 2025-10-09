import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LeerArchivo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce la ruta del archivo de texto: ");
        String rutaArchivo = scanner.nextLine();

        int contadorCaracteres = 0;

        // Usamos try-with-resources para asegurarnos que FileReader se cierra automáticamente
        try (FileReader fr = new FileReader(rutaArchivo)) {
            int caracter;
            // Leemos carácter a carácter hasta que read() devuelva -1
            while ((caracter = fr.read()) != -1) {
                System.out.print((char) caracter);
                contadorCaracteres++;
            }
            System.out.println("\n\nNúmero total de caracteres leídos: " + contadorCaracteres);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        
        scanner.close();
    }
}
