package com.dotahelper.http;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Server {

    public Server(int port) throws IOException {
        ResponseHandler responseHandler = new ResponseHandler();

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", responseHandler);
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();

        System.out.println("Server successfully started on port " + port);
    }

}
