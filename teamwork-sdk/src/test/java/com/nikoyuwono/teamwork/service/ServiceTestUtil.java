package com.nikoyuwono.teamwork.service;

import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;

public class ServiceTestUtil {

    public static final String FAKE_API_KEY = "51.924429,-8.489567";

    public static OkHttpClient defaultOkHttpClient() {
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
