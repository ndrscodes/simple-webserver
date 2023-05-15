package com.ndrs.web.server.http;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;

import com.ndrs.web.server.model.HttpRequest;
import com.ndrs.web.server.model.HttpResponse;

public class RequestHandler {
	public HttpResponse handleRequest(HttpRequest request) {
		var res = new HttpResponse(200, "OK", request.getVersion(), Map.of(), "<h1>this is a test</h1>");
		return res;
	}
	
	public void handleResponse(OutputStream target, HttpResponse response) throws IOException {
		var writer = new PrintWriter(target);
		
		writer.println(response.getVersion() + " " + response.getStatus() + " " + response.getReason());
		var headers = response.getHeaders();
		for(var entry : headers.entrySet()) {
			if(entry.getKey().equalsIgnoreCase("Content-Length")) {
				continue;
			}
			writer.println(entry.getKey() + ": " + entry.getValue());
		}
		
		writeBody(response.getBody(), writer);
		writer.flush();
	}
	
	private void writeBody(Object body, PrintWriter writer) throws IOException {
		var res = body.toString();
		
		writer.println("Content-Length: " + res.length());
		writer.println();
		writer.write(res);
	}
}
