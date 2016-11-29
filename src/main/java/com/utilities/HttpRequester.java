/**
 * 11/26/2016
 * Author: Nicola Meneghetti
 * Project: SteelSeries-ColorChanger
 * File: HttpRequester.java
 * Version: 1.0
 */
package com.utilities;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class HttpRequester {

    private String host;
    private Integer port;

    private HttpRequester(){}

    public HttpRequester(String address){

        this();
        host = address.split(":")[0];
        port = Integer.parseInt(address.split(":")[1]);
    }

    public void postRequest(String enpoint, JSONObject params) throws URISyntaxException, IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        URI uriConfig = new URI("http", null, this.host, this.port, enpoint, null, null);
        HttpPost httpPostRequest = new HttpPost(uriConfig);

        StringEntity paramString = new StringEntity(params.toString());
        paramString.setContentType("application/json");
        httpPostRequest.setEntity(paramString);
        CloseableHttpResponse response = httpclient.execute(httpPostRequest);

        try {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                EntityUtils.consume(entity);
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status +", " + response.getStatusLine().getReasonPhrase());
            }
        } finally {
            response.close();
        }
    }

}
