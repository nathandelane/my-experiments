package com.github.nathandelane.experiments.http.library;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class SimpleHttpWebApplication {

  private final ExecutorService executorService;

  private final int portNumber;

  public SimpleHttpWebApplication(final int portNumber) {
    this(portNumber, 200);
  }

  public SimpleHttpWebApplication(final int portNumber, final int numberOfThreads) {
    this.portNumber = portNumber;
    this.executorService = Executors.newFixedThreadPool(numberOfThreads);
  }

  public void listen() {
    try (final ServerSocket serverSocket = new ServerSocket(portNumber)) {
      System.out.format("Listing for requests on port %d...%n", portNumber);

      while (true) {
        final Socket socket = serverSocket.accept();

        System.out.format("Accepted connection.%n");

        final HttpHandler httpHandler = new HttpHandler(socket);

        executorService.submit(httpHandler);
      }
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }
  }

}
