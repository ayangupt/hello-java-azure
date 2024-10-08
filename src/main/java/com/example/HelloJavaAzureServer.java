package com.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.net.InetSocketAddress;
import java.io.OutputStream;

public class HelloJavaAzureServer {

    public static void main(String[] args) throws Exception {
        // Get the port from environment variables
        String portEnv = System.getenv("PORT");
        int port = (portEnv != null) ? Integer.parseInt(portEnv) : 8081;

        // Create an HttpServer instance on the specified port
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // Create a context to listen on "/" path
        server.createContext("/", new HelloHandler());

        // Start the server
        server.start();

        System.out.println("Server started on port " + port);
    }

    static class HelloHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws java.io.IOException {
            String response = "Hello, Java on Azure!";
            exchange.sendResponseHeaders(200, response.getBytes().length);

            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
