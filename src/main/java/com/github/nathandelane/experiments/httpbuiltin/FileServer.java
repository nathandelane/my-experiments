package com.github.nathandelane.experiments.httpbuiltin;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.SimpleFileServer;

import java.net.InetSocketAddress;
import java.nio.file.Path;

public final class FileServer {

	public static void main(final String[] args) {
    if (args.length < 2) {
      System.out.format("Usage: java SimplyServ <path-to-web-root> <port-number>%n");
      System.exit(1);
    }

    final String webRoot = args[0];
    final int portNumber = Integer.parseInt(args[1]);

		final InetSocketAddress address = new InetSocketAddress(portNumber);
    final Path pathToWebRoot = Path.of(webRoot);
//		final String currentDir = System.getProperty("user.dir");


		final HttpServer server = SimpleFileServer.createFileServer(address, pathToWebRoot, SimpleFileServer.OutputLevel.INFO);
		server.start();
	}

}
