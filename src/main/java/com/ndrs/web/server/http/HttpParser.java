package com.ndrs.web.server.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;

import com.ndrs.web.server.model.HttpRequest;
import com.ndrs.web.server.model.RequestType;

public class HttpParser {
	public HttpRequest handleRequest(Socket clientSocket) throws IOException {
		var input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		String line = input.readLine();
		
		String[] requestStart = line.split(" ");
		RequestType type = RequestType.valueOf(requestStart[0].toUpperCase());
		String path = requestStart[1];
		String version = requestStart[2];
		
		var headers = new HashMap<String, String>();
		while((line = input.readLine()) != null && line.length() != 0) {
			var parsed = line.split(":");
			var key = parsed[0].trim();
			var val = parsed[1].trim();
			headers.put(key, val);
		}
		
		return new HttpRequest(type, path, version, headers);
	}
	
	
}
