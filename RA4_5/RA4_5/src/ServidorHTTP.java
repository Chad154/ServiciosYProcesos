import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class ServidorHTTP {

    public static void main(String[] args) {
        try {
            int puerto = 8080;

            HttpServer server = HttpServer.create(new InetSocketAddress(puerto), 0);

            server.createContext("/login", new GestorUsuarios());

            server.setExecutor(Executors.newCachedThreadPool());

            server.start();
            System.out.println("Servidor iniciado en: http://localhost:" + puerto + "/login");
            System.out.println("Estado (GET): http://localhost:" + puerto + "/login");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
