package com.ndrs.web.server;

import java.io.IOException;

public class Runner {
	public static void main(String[] args) throws IOException {
		var server = new Server(80);
		server.start();
	}
}
