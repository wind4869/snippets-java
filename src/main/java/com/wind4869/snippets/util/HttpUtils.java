package com.wind4869.snippets.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * HttpUtils, HTTP Utilities
 *
 * @author wind4869
 * @since 1.0.0
 */
@Slf4j
public class HttpUtils {
    private static final int DEFAULT_TIMEOUT_IN_MILLIS = 30_000;
    private static final int DEFAULT_MAX_CONNECTION = 100;
    private static final int DEFAULT_MAX_CONNECTION_PER_ROUTE = 10;

    public static CloseableHttpClient createDefaultHttpClient() {
        RequestConfig config = RequestConfig.custom()
                .setSocketTimeout(DEFAULT_TIMEOUT_IN_MILLIS)
                .setConnectTimeout(DEFAULT_TIMEOUT_IN_MILLIS)
                .setConnectionRequestTimeout(DEFAULT_TIMEOUT_IN_MILLIS)
                .build();
        return HttpClientBuilder.create()
                .setDefaultRequestConfig(config)
                .setMaxConnTotal(DEFAULT_MAX_CONNECTION)
                .setMaxConnPerRoute(DEFAULT_MAX_CONNECTION_PER_ROUTE)
                .build();
    }

    public static String doGet(String url, Map<String, String> headers, CloseableHttpClient httpClient) {
        try {
            HttpGet request = new HttpGet();
            request.setURI(new URI(url));
            return execute(request, headers, httpClient);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Failed to finish get request: url=" + url, e);
        }
    }

    public static String doPost(String url, String requestBody, Map<String, String> headers, CloseableHttpClient httpClient) {
        if (headers == null) {
            headers = new HashMap<>(1);
        }
        headers.put("Content-Type", "application/json; charset=utf-8");
        StringEntity requestEntity = new StringEntity(requestBody, ContentType.APPLICATION_JSON);
        HttpPost request = new HttpPost();
        try {
            request.setURI(new URI(url));
            request.setEntity(requestEntity);
            return execute(request, headers, httpClient);
        } catch (IOException | URISyntaxException e) {
            log.error("Failed to finish post request: url={}, requestBody={}", url, requestBody);
            throw new RuntimeException("Failed to finish post request: url=" + url, e);
        }
    }

    private static String execute(HttpRequestBase request, Map<String, String> headers, CloseableHttpClient httpClient) throws IOException {
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                request.setHeader(entry.getKey(), entry.getValue());
            }
        }
        String responseBody = null;
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                responseBody = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
            }
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                log.error("Http response status isn't ok: statusCode={}, responseBody={}", statusCode, responseBody);
                throw new RuntimeException("Http response status isn't ok: statusCode=" + statusCode);
            }
        }
        return responseBody;
    }
}
