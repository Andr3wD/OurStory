package org.webstory.ourstory.model;

import org.joda.time.DateTime;

import lombok.Data;

public @Data class HttpRequestInstance {
	
	private DateTime date;
	private String url; // It would be REALLY bad to save credentials here.
	private String ipSrc;
	
	public HttpRequestInstance(DateTime date, String url, String ipSrc) {
		setDate(date);
		setUrl(url);
		setIpSrc(ipSrc);
	}
	
	public void setUrl(String url) {
		// getRequestURI in GlobalIntercepter technically doesn't contain the queries, but still want to be safe.
		if (!(url.contains("?username") || url.contains("?password"))) {
			this.url = url;
		} else {
			this.url = "[REDACTED CREDENTIALS]";
		}
	}

}
