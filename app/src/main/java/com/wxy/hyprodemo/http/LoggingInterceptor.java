package com.wxy.hyprodemo.http;

import com.wxy.hyprodemo.utils.LogUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpEngine;
import okio.Buffer;
import okio.BufferedSource;


public final class LoggingInterceptor implements Interceptor {

    private static final String TAG = "OkHttp";
    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        StringBuilder requestSb = new StringBuilder();

        Request request = chain.request();

        boolean logBody = true;
        boolean logHeaders = true;

        RequestBody requestBody = request.body();
        boolean hasRequestBody = requestBody != null;

        Connection connection = chain.connection();
        Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
        String requestStartMessage =
                "--> " + request.method() + " Sending request " + request.url() + "\n" + protocol(protocol) + "\n";
        if (!logHeaders && hasRequestBody) {
            requestStartMessage += " (" + requestBody.contentLength() + "-byte body)";
        }
        requestSb.append(requestStartMessage);


        if (logHeaders) {
            if (hasRequestBody) {
                // Request body headers are only present when installed as a network interceptor. Force
                // them to be included (when available) so there values are known.
                if (requestBody.contentType() != null) {
                    requestSb.append("Content-Type: " + requestBody.contentType() + "\n");
                }
                if (requestBody.contentLength() != -1) {
                    requestSb.append("Content-Length: " + requestBody.contentLength() + "\n");
                }
            }

            Headers headers = request.headers();
            for (int i = 0, count = headers.size(); i < count; i++) {
                String name = headers.name(i);
                // Skip headers from the request body as they are explicitly logged above.
                if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
                    requestSb.append(name + ": " + headers.value(i) + "\n");
                }
            }

            if (!logBody || !hasRequestBody) {
                requestSb.append("--> END " + request.method() + "\n");
            } else if (bodyEncoded(request.headers())) {
                requestSb.append("--> END " + request.method() + " (encoded body omitted)");
            } else {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);

                Charset charset = UTF8;
                MediaType contentType = requestBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }

                String paramStr = buffer.readString(charset);
                StringBuilder sb = new StringBuilder();
                String[] params = paramStr.split("&");
                for (String param : params) {
                    sb.append("  " + param + ",\n");
                }
                requestSb.append("Request params:{\n" + sb.toString() + "}" + "\n");
                requestSb.append("--> END " + request.method()
                        + " (" + requestBody.contentLength() + "-byte body)" + "\n");
            }
        }

        long startNs = System.nanoTime();
        Response response = chain.proceed(request);
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        String bodySize = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
        requestSb.append("<-- " + response.code() + ' ' + response.message() + ' '
                + "Received response for " + response.request().url() + " (" + tookMs + "ms" + (!logHeaders ? ", "
                + bodySize + " body" : "") + ')' + "\n");


        StringBuilder responeSb = new StringBuilder();
        if (logHeaders) {
            Headers headers = response.headers();
            for (int i = 0, count = headers.size(); i < count; i++) {
                requestSb.append(headers.name(i) + ": " + headers.value(i) + "\n");
            }

            if (!logBody || !HttpEngine.hasBody(response)) {
                requestSb.append("<-- END HTTP \n");

            } else if (bodyEncoded(response.headers())) {
                requestSb.append("<-- END HTTP (encoded body omitted)\n");
            } else {
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE); // Buffer the entire body.
                Buffer buffer = source.buffer();

                Charset charset = UTF8;
                MediaType contentType = responseBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }

                if (contentLength != 0) {

                    responeSb.append(buffer.clone().readString(charset));

                }
                requestSb.append("<-- END HTTP (" + buffer.size() + "-byte body)\n");
            }
        }
        LogUtils.e(TAG, requestSb.toString());
        LogUtils.json(TAG, responeSb.toString());
        return response;
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }

    private static String protocol(Protocol protocol) {
        return protocol == Protocol.HTTP_1_0 ? "HTTP/1.0" : "HTTP/1.1";
    }
}
