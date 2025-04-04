package com.github.nathandelane.experiments.httpbuiltin;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class CellServer {

	public static void main(final String[] args) throws IOException {
		final ExecutorService executorPool = Executors.newFixedThreadPool(200);
		final HttpHandler handler = new HttpHandler() {

			@Override
			public void handle(final HttpExchange exchange) throws IOException {
				System.out.println("Method: " + exchange.getRequestMethod());

				final Map<String, List<String>> headers = new HashMap(exchange.getRequestHeaders());

				System.out.println("Request headers: " + headers);
				System.out.println("Path: " + exchange.getRequestURI());
				System.out.println("Query: " + exchange.getRequestURI().getQuery());

				final byte[] response = "<!doctype html><html lang=\"en-US\"><body><h1>Hello, Web!</body></html>".getBytes(StandardCharsets.UTF_8);

				exchange.getResponseHeaders().add("Content-Type", "text/html");

				final StringBuilder sb = new StringBuilder();

				try (final InputStream is = exchange.getRequestBody()) {
					final byte[] requestBody = is.readAllBytes();
					final String strRequestBody = new String(requestBody);

					sb.append(strRequestBody);
				}

				System.out.println("Request body: " + sb.toString());

				exchange.sendResponseHeaders(200, (long) response.length);

				try (final OutputStream os = exchange.getResponseBody()) {
					os.write(response);
					os.flush();
				}
			}

		};
		final HttpServer server = HttpServer.create(new InetSocketAddress(8082), 0);
		server.createContext("/", handler);
		server.setExecutor(executorPool);

		server.start();
	}

}
