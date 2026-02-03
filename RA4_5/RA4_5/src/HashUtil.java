import java.security.MessageDigest;

public class HashUtil {

    public static String sha256(String texto) {
        return hash(texto, "SHA-256");
    }

    public static String hash(String texto, String algoritmo) {
        try {
            MessageDigest md = MessageDigest.getInstance(algoritmo);
            byte[] bytes = md.digest(texto.getBytes("UTF-8"));

            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (Exception e) {
            return null;
        }
    }
}
