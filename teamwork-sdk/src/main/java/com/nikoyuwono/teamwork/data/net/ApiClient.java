package com.nikoyuwono.teamwork.data.net;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.schedulers.Schedulers;

public class ApiClient {

    private static final String CONTENT_TYPE_FIELD_NAME = "Content-Type";
    private static final String CONTENT_TYPE_APPLICATION_JSON = "application/json; charset=utf-8";

    private final OkHttpClient okHttpClient;
    private final String baseUrl;

    private ApiClient(OkHttpClient okHttpClient, String baseUrl) {
        this.okHttpClient = okHttpClient;
        this.baseUrl = baseUrl;
    }

    private ApiClient(Builder builder) {
        this.okHttpClient = builder.okHttpClient;
        this.baseUrl = builder.baseUrl;
    }

    public Executor withPath(final String path) {
        return new Executor(this, baseUrl, path);
    }

    public static final class Builder {

        private OkHttpClient okHttpClient;
        private String baseUrl;

        public ApiClient build() {
            return new ApiClient(this);
        }

        public Builder okHttpClient(OkHttpClient okHttpClient) {
            this.okHttpClient = okHttpClient;
            return this;
        }

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }
    }

    public static class Executor {

        private final ApiClient apiClient;

        private final HttpUrl.Builder urlBuilder;

        private final Map<String, String> parameters = new HashMap<>();

        private RequestBody requestBody;

        Executor(final ApiClient apiClient,
                 final String baseUrl,
                 final String path) {
            this.apiClient = apiClient;
            this.urlBuilder = new HttpUrl.Builder()
                    .scheme("https")
                    .host(baseUrl)
                    .addPathSegment(path);
        }

        public <T> Executor param(final String key, final T value) {
            parameters.put(key, String.valueOf(value));
            return this;
        }

        public Executor jsonBody(final String body) {
            requestBody = RequestBody.create(MediaType.parse(CONTENT_TYPE_APPLICATION_JSON), body);
            return this;
        }

        public Observable<Response> get() {
            return execute(this.createGetRequest());
        }

        public void get(final Callback callback) {
            execute(this.createGetRequest(), callback);
        }

        private Request createGetRequest() {
            return new Request.Builder()
                    .url(buildRequestUrlWithQueryParameter())
                    .addHeader(CONTENT_TYPE_FIELD_NAME, CONTENT_TYPE_APPLICATION_JSON)
                    .get()
                    .build();
        }

        private Request createPostRequest() {
            return new Request.Builder()
                    .url(buildRequestUrl())
                    .post(requestBody)
                    .build();
        }

        private Request createPutRequest() {
            return new Request.Builder()
                    .url(buildRequestUrl())
                    .put(requestBody)
                    .build();
        }

        private Request createDeleteRequest() {
            return new Request.Builder()
                    .url(buildRequestUrlWithQueryParameter())
                    .delete(requestBody)
                    .build();
        }

        private HttpUrl buildRequestUrl() {
            return this.urlBuilder
                    .build();
        }

        private HttpUrl buildRequestUrlWithQueryParameter() {
            for (final Map.Entry<String, String> entry : parameters.entrySet()) {
                this.urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            }

            return this.urlBuilder.build();
        }

        private Observable<Response> execute(final Request request) {
            return Observable.<Response>create(subscriber -> {
                try {
                    final Response response = apiClient.okHttpClient.newCall(request).execute();
                    subscriber.onNext(response);
                    subscriber.onCompleted();
                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io());
        }

        private void execute(final Request request, Callback callback) {
            apiClient.okHttpClient.newCall(request).enqueue(callback);
        }
    }

}