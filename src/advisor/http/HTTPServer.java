package advisor.http;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HTTPServer {

    public static HttpServer SERVER;

    public static void start() throws IOException {
        SERVER = HttpServer.create();
        SERVER.bind(new InetSocketAddress(8081), 0);
        SERVER.start();
    }

    public static void stop() {
        SERVER.stop(1);
    }
}
