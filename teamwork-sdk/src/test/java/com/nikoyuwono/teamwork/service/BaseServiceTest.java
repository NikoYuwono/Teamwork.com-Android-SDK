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

public abstract class BaseServiceTest {

    private static final String FAKE_API_KEY = "51.924429,-8.489567";
    private static OkHttpClient okHttpClient;
    protected static Gson gson;

    @Rule
    public final MockWebServer mockWebServer = new MockWebServer();
    protected ApiClient apiClient;

    @BeforeClass
    public static void setupOkHttpClient() {
        okHttpClient = defaultOkHttpClient();
    }

    @Before
    public void setup() throws IOException {
        final HttpUrl mockUrl = mockWebServer.url("/");
        apiClient = new ApiClient.Builder()
                .baseUrl(mockUrl)
                .okHttpClient(okHttpClient)
                .build();
    }

    private static OkHttpClient defaultOkHttpClient() {
        return new OkHttpClient.Builder()
                .authenticator((route, response) -> {
                    final String credential = Credentials.basic(FAKE_API_KEY, "");
                    if (credential.equals(response.request().header("Authorization"))) {
                        return null; // If we already failed with these credentials, don't retry.
                    }
                    return response.request().newBuilder()
                            .header("Authorization", credential)
                            .build();
                })
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .connectTimeout(15000, TimeUnit.MILLISECONDS)
                .build();
    }
}
