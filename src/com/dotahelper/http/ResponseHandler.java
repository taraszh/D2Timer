package com.dotahelper.http;

import com.dotahelper.json.Mapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ResponseHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) {
        StringBuilder response = new StringBuilder();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()));
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line).append("\n");
            }
            in.close();
            new Mapper(response.toString());

        } catch (Exception e) {
            System.out.println(response.toString());
            e.printStackTrace();
        }
    }


}
