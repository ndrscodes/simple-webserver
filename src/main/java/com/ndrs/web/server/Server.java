package com.ndrs.web.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ndrs.web.server.http.HttpParser;
import com.ndrs.web.server.http.RequestHandler;
import com.ndrs.web.server.model.HttpRequest;

public class Server {
	private ServerSocket socket;
	private HttpParser parser;
	private ExecutorService pool = Executors.newCachedThreadPool();
	
	
	public Server(int port) throws IOException {
		socket = new ServerSocket(port);
		parser = new HttpParser();
	}
	
	public void start() throws IOException {
		System.out.println("listening on port " + socket.getLocalPort());
		while(!Thread.interrupted()) {
			var client = socket.accept();
			var request = parser.handleRequest(client);
			
			System.out.println("request received: " + request);
			
			var os = client.getOutputStream();
			pool.execute(() -> {				
				try {
					handleRequest(os, request);
					os.flush();
					client.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
	}

	private void handleRequest(OutputStream target, HttpRequest request) throws IOException {
		var handler = new RequestHandler();
		var res = handler.handleRequest(request);
		
		System.out.println("created response: " + res);
		
		handler.handleResponse(target, res);
	}
	
}
