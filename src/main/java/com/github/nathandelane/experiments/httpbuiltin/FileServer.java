package com.github.nathandelane.experiments.httpbuiltin;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.SimpleFileServer;

import java.net.InetSocketAddress;
import java.nio.file.Path;

public final class FileServer {

	public static void main(final String[] args) {
		final InetSocketAddress address = new InetSocketAddress(8082);
		final String currentDir = System.getProperty("user.dir");

		final HttpServer server = SimpleFileServer.createFileServer(address, Path.of(currentDir + "/http"), SimpleFileServer.OutputLevel.INFO);
		server.start();
	}

}
