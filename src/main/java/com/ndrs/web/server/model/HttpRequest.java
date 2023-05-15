package com.ndrs.web.server.model;

import java.util.Map;

public class HttpRequest {
	private String version;
	private Map<String, String> headers;
	private String path;
	private RequestType requestType;
	
	public HttpRequest(RequestType requestType, String path, String version, Map<String, String> headers) {
		this.version = version;
		this.headers = headers;
		this.path = path;
		this.requestType = requestType;
	}
	
	public String getVersion() {
		return version;
	}
	
	public Map<String, String> getHeaders() {
		return headers;
	}
	
	public String getPath() {
		return path;
	}
	
	public RequestType getRequestType() {
		return requestType;
	}

	@Override
	public String toString() {
		return "HttpRequest [version=" + version + ", headers=" + headers + ", path=" + path + ", requestType="
				+ requestType + "]";
	}
}
