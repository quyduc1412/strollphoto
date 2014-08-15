package com.slightstudio.common;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpUtils {
	
	public static String fetchPage(String url) throws IOException {
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpGet httpget = new HttpGet(url);
	    HttpResponse response;
	    try {
	        response = httpclient.execute(httpget);
	        HttpEntity entity = response.getEntity();
	        if (entity != null) {
	            return EntityUtils.toString(entity);
	        }
	    } catch (ClientProtocolException e) {
	        e.printStackTrace();
	    }

	    return null;
	}
}
