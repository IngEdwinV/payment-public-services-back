package edu.escuelaing.arem.ASE.app;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class HttpServerApp {
    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/", new MyHttpHandler());
        server.start();
        System.out.println("Servidor HTTP iniciado en el puerto " + PORT);
    }

    static class MyHttpHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            String requestMethod = httpExchange.getRequestMethod();
            String requestPath = httpExchange.getRequestURI().getPath();
            String responseBody = "Ruta no encontrada";

            if ("/".equals(requestPath)) {
                responseBody = "Hola desde el servidor sin frameworks!";
            } else if ("/api/endpoint".equals(requestPath)) {
                responseBody = "Hola desde el servidor sin frameworks en la ruta /api/endpoint!";
            } else if ("/sendRequest".equals(requestPath)) {
                responseBody = "Probando la ruta /sendRequest";
            } else if ("/form".equals(requestPath)) {
                responseBody = getFormHtml();
            }

            sendResponse(httpExchange, 200, responseBody);
        }

        private void sendResponse(HttpExchange httpExchange, int statusCode, String responseBody) throws IOException {
            byte[] responseBytes = responseBody.getBytes();
            httpExchange.getResponseHeaders().add("Content-Type", "text/html");
            httpExchange.sendResponseHeaders(statusCode, responseBytes.length);
            try (OutputStream os = httpExchange.getResponseBody()) {
                os.write(responseBytes);
            }
        }

        private String getFormHtml() {
            return "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "    <head>\n" +
                    "        <title>Form Example</title>\n" +
                    "        <meta charset=\"UTF-8\">\n" +
                    "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    </head>\n" +
                    "    <body>\n" +
                    "        <h1>Search your movie GET</h1>\n" +
                    "        <form action=\"/hello\">\n" +
                    "            <label for=\"name\">Title:</label><br>\n" +
                    "            <input type=\"text\" id=\"name\" name=\"name\" value=\"John\"><br><br>\n" +
                    "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n" +
                    "        </form> \n" + "<br>"+
                    "        <div id=\"getrespmsg\"></div>\n" +
                    "\n" +
                    "        <script>\n" +
                    "            function loadGetMsg() {\n" +
                    "                let nameVar = document.getElementById(\"name\").value;\n" +
                    "                const xhttp = new XMLHttpRequest();\n" +
                    "                xhttp.onload = function() {\n" +
                    "                    document.getElementById(\"getrespmsg\").innerHTML =\n" +
                    "                    this.responseText;\n" +
                    "                }\n" +
                    "                xhttp.open(\"GET\", \"/title?name=\"+nameVar);\n" +
                    "                xhttp.send();\n" +
                    "            }\n" +
                    "        </script>\n" +
                    "\n" +
                    "</html>";
        }
    }
}
