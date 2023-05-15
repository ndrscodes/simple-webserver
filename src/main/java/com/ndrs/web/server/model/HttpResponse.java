package com.ndrs.web.server.model;

import java.util.Map;

public class HttpResponse {
	private int status;
	private String reason;
	Map<String, String> headers;
	private Object body;
	private String version;

	public HttpResponse(int status, String reason, String version, Map<String, String> headers, Object body) {
		this.status = status;
		this.reason = reason;
		this.body = body;
		this.version = version;
		this.headers = headers;
	}
	
	public Map<String, String> getHeaders() {
		return headers;
	}
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Object getBody() {
		return body;
	}
	public void setBody(Object body) {
		this.body = body;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "HttpResponse [status=" + status + ", reason=" + reason + ", body=" + body + "]";
	}
}
