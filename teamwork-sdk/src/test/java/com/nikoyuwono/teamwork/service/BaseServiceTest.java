package com.nikoyuwono.teamwork.service;

import com.google.gson.Gson;
import com.nikoyuwono.teamwork.data.net.ApiClient;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class BaseServiceTest {

    private static OkHttpClient okHttpClient;
    private static OkHttpClient okHttpClientWithoutAuthenticator;
    protected static final String FAKE_API_KEY = "51.924429,-8.489567";
    protected static final String AUTHORIZATION_CREDENTIAL = Credentials.basic(FAKE_API_KEY, "");
    protected static Gson gson;

    @Rule
    public final MockWebServer mockWebServer = new MockWebServer();
    protected ApiClient apiClient;
    protected ApiClient apiClientWithoutAuthenticator;

    @BeforeClass
    public static void setupOkHttpClient() {
        okHttpClient = defaultOkHttpClient();
        okHttpClientWithoutAuthenticator = okHttpClientWithoutAuthenticator();
    }

    @Before
    public void setup() throws IOException {
        final HttpUrl mockUrl = mockWebServer.url("/");
        apiClient = new ApiClient.Builder()
                .baseUrl(mockUrl)
                .okHttpClient(okHttpClient)
                .build();
        apiClientWithoutAuthenticator = new ApiClient.Builder()
                .baseUrl(mockUrl)
                .okHttpClient(okHttpClientWithoutAuthenticator)
                .build();
    }

    private static OkHttpClient defaultOkHttpClient() {
        return new OkHttpClient.Builder()
                .authenticator((route, response) -> {
                    if (AUTHORIZATION_CREDENTIAL.equals(response.request().header("Authorization"))) {
                        return null; // If we already failed with these credentials, don't retry.
                    }
                    return response.request().newBuilder()
                            .header("Authorization", AUTHORIZATION_CREDENTIAL)
                            .build();
                })
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .connectTimeout(15000, TimeUnit.MILLISECONDS)
                .build();
    }

    private static OkHttpClient okHttpClientWithoutAuthenticator() {
        return new OkHttpClient.Builder()
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .connectTimeout(15000, TimeUnit.MILLISECONDS)
                .build();
    }

    protected void assertRequestHasCorrectCredential() throws InterruptedException {
        final RecordedRequest recordedRequest1 = mockWebServer.takeRequest();
        assertThat(recordedRequest1.getHeader("Authorization")).isNull();

        final RecordedRequest recordedRequest2 = mockWebServer.takeRequest();
        assertThat(recordedRequest2.getHeader("Authorization")).isEqualTo(AUTHORIZATION_CREDENTIAL);
    }
}
