public class ExampleExit {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.exit(1);
        }
        String argumento = args[0];
        try {
            int numero = Integer.parseInt(argumento);
            if (numero < 0) {
                System.exit(3); 
            } else {
                System.exit(0);  
            }
        } catch (NumberFormatException e) {
            System.exit(2);
        }
    }
}
