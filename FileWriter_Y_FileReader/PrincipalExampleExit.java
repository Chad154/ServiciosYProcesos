import java.io.IOException;

public class PrincipalExampleExit {
    public static void main(String[] args)  {
        // Puedes cambiar este argumento para probar distintas situaciones
        String argumento = args.length > 0 ? args[0] : "";

        try {
            ProcessBuilder pb = new ProcessBuilder("java", "Comprobador", argumento);
            pb.inheritIO(); // Para que se vean los outputs, opcional
            Process proceso = pb.start();

            int codigoSalida = proceso.waitFor();

            switch (codigoSalida) {
                case 0:
                    System.out.println("Código 0: Argumento válido.");
                    break;
                case 1:
                    System.out.println("Código 1: No se proporcionaron argumentos.");
                    break;
                case 2:
                    System.out.println("Código 2: El argumento no es un número entero.");
                    break;
                case 3:
                    System.out.println("Código 3: El número es entero menor que 0.");
                    break;
                default:
                    System.out.println("Código desconocido: " + codigoSalida);
                    break;
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
