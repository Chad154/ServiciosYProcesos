import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class GestorUsuarios implements HttpHandler {

    private static final String FICHERO = "src\\usuarios.txt";

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        try {
            String metodo = exchange.getRequestMethod();

            if ("GET".equalsIgnoreCase(metodo)) {
                enviarRespuesta(exchange, 200, "Servidor activo. Usa POST en /login con usuario y password.");
                return;
            }

            if (!"POST".equalsIgnoreCase(metodo)) {
                enviarRespuesta(exchange, 405, "Metodo no permitido. Usa POST.");
                return;
            }

            String body = leerBody(exchange);
            Map<String, String> params = parsear(body);

            String usuario = params.get("usuario");
            String password = params.get("password");

            if (usuario == null || password == null) {
                enviarRespuesta(exchange, 400, "Peticion incorrecta. Faltan parametros usuario/password.");
                return;
            }

            boolean ok = validar(usuario, password);

            if (ok) {
                enviarRespuesta(exchange, 200, "Acceso concedido");
            } else {
                enviarRespuesta(exchange, 200, "Acceso denegado");
            }

        } catch (FileNotFoundException fnf) {
            enviarRespuesta(exchange, 500, "ERROR servidor: no se encuentra el fichero " + FICHERO);

        } catch (Exception e) {
            enviarRespuesta(exchange, 500, "ERROR servidor: " + e.getClass().getSimpleName() + " - " + e.getMessage());

        } finally {
            try { exchange.close(); } catch (Exception ignored) {}
        }
    }

    private String leerBody(HttpExchange exchange) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8)
        );

        StringBuilder sb = new StringBuilder();
        String linea;
        while ((linea = br.readLine()) != null) {
            sb.append(linea);
        }
        br.close();
        return sb.toString();
    }

    private Map<String, String> parsear(String datos) throws Exception {
        Map<String, String> mapa = new HashMap<>();
        if (datos == null || datos.trim().isEmpty()) return mapa;

        String[] pares = datos.split("&");
        for (String par : pares) {
            String[] kv = par.split("=", 2);
            if (kv.length == 2) {
                String key = URLDecoder.decode(kv[0], "UTF-8");
                String value = URLDecoder.decode(kv[1], "UTF-8");
                mapa.put(key, value);
            }
        }
        return mapa;
    }

    private boolean validar(String usuario, String password) throws IOException {
        String hashRecibido = HashUtil.sha256(password);

        try (BufferedReader br = new BufferedReader(new FileReader(FICHERO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length >= 2) {
                    String userFichero = partes[0];
                    String hashFichero = partes[1];

                    if (userFichero.equals(usuario) && hashFichero.equals(hashRecibido)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void enviarRespuesta(HttpExchange exchange, int status, String respuesta) throws IOException {
        byte[] bytes = respuesta.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8");
        exchange.sendResponseHeaders(status, bytes.length);

        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }
}
