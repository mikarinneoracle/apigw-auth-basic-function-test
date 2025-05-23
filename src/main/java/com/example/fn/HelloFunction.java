package com.example.fn;

import com.fnproject.fn.api.RuntimeContext;
import com.fnproject.fn.api.httpgateway.HTTPGatewayContext;
import com.fnproject.fn.api.InputEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HelloFunction {

    public String handleRequest(final HTTPGatewayContext hctx, final InputEvent input) {

        System.out.println("==== FUNC ====\n");
        try {
            List<String> lines = Files.readAllLines(Paths.get("/func.yaml")).stream().limit(3).collect(Collectors.toList());
            lines.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error reading func.yaml: " + e.getMessage());
        }
        System.out.println("==============");

        System.out.println("======= CONFIG ======");
        //hctx.getHeaders().getAll().forEach((key, value) -> System.out.println(">>>>>" + key + ": " + value));
        //input.getHeaders().getAll().forEach((key, value) -> System.out.println(">>>>>" + key + ": " + value));
        System.out.println("=====================");

        // Use header transformation in APIGW Route to get username in headers from authorizer
        // Overwrite	username     ${request.auth[username]}
        String username = hctx.getHeaders().get("username").orElse("");
        return "Username: " + username;
    }

}