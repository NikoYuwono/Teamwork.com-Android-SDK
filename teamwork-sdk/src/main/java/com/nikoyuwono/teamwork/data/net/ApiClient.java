package com.nikoyuwono.teamwork.data.net;

import com.nikoyuwono.teamwork.Teamwork;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.schedulers.Schedulers;

public class ApiClient {

    public static final String HOST_PREFERENCE_KEY = "HOST_PREFERENCE_KEY";
    private static final String AUTHENTICATE_HOST = "authenticate.teamworkpm.net";

    private final OkHttpClient okHttpClient;
    private final HttpUrl baseUrl;

    private ApiClient(Builder builder) {
        this.okHttpClient = builder.okHttpClient;
        this.baseUrl = builder.baseUrl;
    }

    public Executor withPath(final String path) {
        if (baseUrl != null) {
            return new Executor(this, baseUrl, path);
        } else {
            final String host = Teamwork.getSharedPreferences().getString(HOST_PREFERENCE_KEY, null);
            return new Executor(this, host, path);
        }
    }

    public Executor usingAuthenticateHostWithPath(final String path) {
        return new Executor(this, AUTHENTICATE_HOST, path);
    }

    public static final class Builder {

        private OkHttpClient okHttpClient;
        private HttpUrl baseUrl;

        public ApiClient build() {
            if (okHttpClient == null)  {
                throw new IllegalArgumentException("Please set an OkHttpClient in order to build an ApiClient");
            }

            return new ApiClient(this);
        }

        public Builder okHttpClient(OkHttpClient okHttpClient) {
            this.okHttpClient = okHttpClient;
            return this;
        }

        public Builder baseUrl(HttpUrl baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }
    }

    public static class Executor {

        private static final String CONTENT_TYPE_FIELD_NAME = "Content-Type";
        private static final String CONTENT_TYPE_APPLICATION_JSON = "application/json; charset=utf-8";
        private static final String AUTHORIZATION_FIELD_NAME = "Authorization";

        private final ApiClient apiClient;

        private final HttpUrl.Builder urlBuilder;

        private final Map<String, String> parameters = new HashMap<>();

        private final Map<String, String> headers = new HashMap<>();

        private RequestBody requestBody;

        private boolean missingHost = false;

        private boolean shouldSaveCredential = false;

        Executor(final ApiClient apiClient,
                 final HttpUrl httpUrl,
                 final String path) {
            this.apiClient = apiClient;
            this.urlBuilder = httpUrl.newBuilder()
                    .encodedPath(path);
            addDefaultHeaders();
        }

        Executor(final ApiClient apiClient,
                 String host,
                 final String path) {
            if (host == null) {
                host = "host";
                missingHost = true;
            }
            this.apiClient = apiClient;
            this.urlBuilder = new HttpUrl.Builder()
                    .scheme("https")
                    .host(host)
                    .encodedPath(path);
            addDefaultHeaders();
        }

        public <T> Executor param(final String key, final T value) {
            parameters.put(key, String.valueOf(value));
            return this;
        }

        public <T> Executor header(final String key, final T value) {
            headers.put(key, String.valueOf(value));
            return this;
        }

        public Executor authorizationHeader(final String credential) {
            headers.put(AUTHORIZATION_FIELD_NAME, credential);
            shouldSaveCredential = true;
            return this;
        }

        public Executor jsonBody(final String body) {
            requestBody = RequestBody.create(MediaType.parse(CONTENT_TYPE_APPLICATION_JSON), body);
            return this;
        }

        public HttpUrl getHttpUrl() {
            return buildRequestUrlWithQueryParameter();
        }

        public Observable<Response> get() {
            return execute(this.createGetRequest());
        }

        public void get(final Callback callback) {
            execute(this.createGetRequest(), callback);
        }

        public Observable<Response> post() {
            return execute(this.createPostRequest());
        }

        public void post(final Callback callback) {
            execute(this.createPostRequest(), callback);
        }

        public Observable<Response> put() {
            return execute(this.createPutRequest());
        }

        public void put(final Callback callback) {
            execute(this.createPutRequest(), callback);
        }

        public Observable<Response> delete() {
            return execute(this.createDeleteRequest());
        }

        public void delete(final Callback callback) {
            execute(this.createDeleteRequest(), callback);
        }

        private Request createGetRequest() {
            return new Request.Builder()
                    .url(buildRequestUrlWithQueryParameter())
                    .headers(Headers.of(headers))
                    .get()
                    .build();
        }

        private Request createPostRequest() {
            return new Request.Builder()
                    .url(buildRequestUrl())
                    .headers(Headers.of(headers))
                    .post(getRequestBody())
                    .build();
        }

        private Request createPutRequest() {
            return new Request.Builder()
                    .url(buildRequestUrl())
                    .headers(Headers.of(headers))
                    .put(getRequestBody())
                    .build();
        }

        private Request createDeleteRequest() {
            return new Request.Builder()
                    .url(buildRequestUrlWithQueryParameter())
                    .headers(Headers.of(headers))
                    .delete(getRequestBody())
                    .build();
        }

        private HttpUrl buildRequestUrl() {
            return this.urlBuilder
                    .build();
        }

        private RequestBody getRequestBody() {
            if (requestBody != null) {
                return requestBody;
            } else {
                // Return empty body if there are no RequestBody available
                return RequestBody.create(null, new byte[0]);
            }
        }

        private HttpUrl buildRequestUrlWithQueryParameter() {
            for (final Map.Entry<String, String> entry : parameters.entrySet()) {
                this.urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            }

            return this.urlBuilder.build();
        }

        private Observable<Response> execute(final Request request) {
            if (missingHost) {
                return Observable.create(subscriber -> subscriber.onError(createUnauthorizedException()));
            } else {
                return Observable.<Response>create(subscriber -> {
                    try {
                        final Response response = apiClient.okHttpClient.newCall(request).execute();

                        if (!response.isSuccessful()) {
                            throw createExceptionFromResponse(response);
                        }

                        if (shouldSaveCredential) {
                            saveCredential(request.header(AUTHORIZATION_FIELD_NAME));
                        }

                        subscriber.onNext(response);
                        subscriber.onCompleted();
                    } catch (IOException e) {
                        subscriber.onError(e);
                    }
                }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io());
            }
        }

        private void execute(final Request request, Callback callback) {
            if (missingHost) {
                callback.onFailure(null, createUnauthorizedException());
            } else {
                apiClient.okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        callback.onFailure(call, e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            if (shouldSaveCredential) {
                                saveCredential(call.request().header(AUTHORIZATION_FIELD_NAME));
                            }
                            callback.onResponse(call, response);
                        } else {
                            callback.onFailure(call, createExceptionFromResponse(response));
                        }
                    }
                });
            }
        }

        private IOException createExceptionFromResponse(Response response) {
            final String errorName = isClientError(response.code()) ? "Client error" : "Server error";
            return new IOException(errorName + " occurred with Response code : "
                    + response.code()
                    + ", message : "
                    + response.message()
                    + ", for url : "
                    + response.request().url());
        }

        private IOException createUnauthorizedException() {
            return new IOException("Unauthorized Access! Please Authorize with calling AccountRequest.authenticate()");
        }

        private boolean isClientError(int responseCode) {
            return responseCode >= 400 && responseCode < 500;
        }

        private boolean isServerError(int responseCode) {
            return responseCode >= 500 && responseCode < 600;
        }

        private void saveCredential(final String credential) {
            CryptoUtils.encryptCredential(credential);
        }

        private void addDefaultHeaders() {
            this.headers.put(CONTENT_TYPE_FIELD_NAME, CONTENT_TYPE_APPLICATION_JSON);
            final String credential = CryptoUtils.getSavedCredential();
            if (credential != null) {
                this.headers.put(AUTHORIZATION_FIELD_NAME, credential);
            }
        }
    }

}