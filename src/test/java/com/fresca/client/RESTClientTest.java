package com.fresca.client;

import com.fresca.domain.Airport;
import com.fresca.http.client.RESTClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.net.Authenticator;
import java.net.CookieHandler;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.WebSocket;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class RESTClientTest {

    @Test
    void getPassengerAirports_success200_parsesBody() throws Exception {
        HttpClient http = new HttpClientStubSuccess(
                200,
                "[{\"name\":\"Toronto Pearson International Airport\",\"code\":\"YYZ\"}," +
                        " {\"name\":\"Halifax Stanfield\",\"code\":\"YHZ\"}]"
        );

        List<Airport> parsed = List.of(
                new Airport("Toronto Pearson International Airport", "YYZ"),
                new Airport("Halifax Stanfield", "YHZ")
        );

        RESTClient client = new RESTClient() {
            @Override public HttpClient getClient() { return http; }
            @Override public List<Airport> buildAirportListFromResponse(String body) { return parsed; }
        };
        client.setServerURL("http://localhost:8080/airports/passenger");

        List<Airport> result = client.getPassengerAirports();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("YYZ", result.get(0).getCode());
        Assertions.assertEquals("YHZ", result.get(1).getCode());
    }

    @Test
    void getPassengerAirports_non200_returnsEmptyList() throws Exception {
        HttpClient http = new HttpClientStubSuccess(500, "Internal Server Error");

        RESTClient client = new RESTClient() {
            @Override public HttpClient getClient() { return http; }
            @Override public List<Airport> buildAirportListFromResponse(String body) { return new ArrayList<>(); }
        };
        client.setServerURL("http://localhost:8080/airports/passenger");

        List<Airport> result = client.getPassengerAirports();

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isEmpty());
    }



    static final class HttpClientStubSuccess extends HttpClient {
        private final int status;
        private final String body;

        HttpClientStubSuccess(int status, String body) {
            this.status = status;
            this.body = body;
        }

        @Override
        public <T> HttpResponse<T> send(HttpRequest request, HttpResponse.BodyHandler<T> handler)
                throws IOException, InterruptedException {
            @SuppressWarnings("unchecked")
            T cast = (T) body;
            return new FakeHttpResponse<>(status, cast, request.uri());
        }

        @Override public Optional<CookieHandler> cookieHandler() { return Optional.empty(); }
        @Override public Optional<Duration> connectTimeout() { return Optional.empty(); }
        @Override public Redirect followRedirects() { return Redirect.NEVER; }
        @Override public Optional<ProxySelector> proxy() { return Optional.empty(); }
        @Override public SSLContext sslContext() { return null; }
        @Override public SSLParameters sslParameters() { return null; }
        @Override public Optional<Authenticator> authenticator() { return Optional.empty(); }
        @Override public Version version() { return Version.HTTP_1_1; }
        @Override public Optional<Executor> executor() { return Optional.empty(); }
        @Override public WebSocket.Builder newWebSocketBuilder() { throw new UnsupportedOperationException(); }
        @Override public <T> CompletableFuture<HttpResponse<T>> sendAsync(HttpRequest r, HttpResponse.BodyHandler<T> h) { throw new UnsupportedOperationException(); }
        @Override public <T> CompletableFuture<HttpResponse<T>> sendAsync(HttpRequest r, HttpResponse.BodyHandler<T> h, HttpResponse.PushPromiseHandler<T> p) { throw new UnsupportedOperationException(); }
    }

    static final class FakeHttpResponse<T> implements HttpResponse<T> {
        private final int code; private final T body; private final URI uri;
        FakeHttpResponse(int code, T body, URI uri) { this.code = code; this.body = body; this.uri = uri; }
        @Override public int statusCode() { return code; }
        @Override public T body() { return body; }
        @Override public HttpRequest request() { return HttpRequest.newBuilder(uri).build(); }
        @Override public Optional<HttpResponse<T>> previousResponse() { return Optional.empty(); }
        @Override public HttpHeaders headers() { return HttpHeaders.of(Map.of(), (k,v)->true); }
        @Override public URI uri() { return uri; }
        @Override public HttpClient.Version version() { return HttpClient.Version.HTTP_1_1; }
        @Override public Optional<SSLSession> sslSession() { return Optional.empty(); }
    }
}
