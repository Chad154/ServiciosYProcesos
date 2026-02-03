import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClienteHTTP {

    public static void main(String[] args) {
        HttpURLConnection con = null;

        try {
            URL url = new URL("http://localhost:8080/login");
            con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

            String datos = "usuario=admin&password=admin";

            OutputStream os = con.getOutputStream();
            os.write(datos.getBytes("UTF-8"));
            os.flush();
            os.close();

            int code = con.getResponseCode();
            System.out.println("HTTP Code: " + code);

            InputStream is = (code >= 200 && code < 400) ? con.getInputStream() : con.getErrorStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String linea;
            StringBuilder sb = new StringBuilder();
            while ((linea = br.readLine()) != null) {
                sb.append(linea);
            }
            br.close();

            System.out.println("Respuesta: " + sb.toString());

        } catch (Exception e) {
            System.out.println("Error REAL:");
            e.printStackTrace();

        } finally {
            if (con != null) con.disconnect();
        }
    }
}
