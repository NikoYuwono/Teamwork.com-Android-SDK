package com.nikoyuwono.teamwork;

import android.content.Context;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;

public class Teamwork {

    public static void initialize(final Context context,
                                  final String apiKey,
                                  final String baseUrl) {
        final Cache cache = createCache(context.getCacheDir());
        final OkHttpClient okHttpClient = createOkHttpClient(cache, apiKey);
        TeamworkRequest.initialize(okHttpClient, baseUrl);
    }

    private static Cache createCache(final File cacheDir) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(cacheDir, cacheSize);
    }

    private static OkHttpClient createOkHttpClient(final Cache cache, final String apiKey) {
        return new OkHttpClient.Builder()
                .authenticator((route, response) -> {
                    final String credential = Credentials.basic(apiKey, "");
                    if (credential.equals(response.request().header("Authorization"))) {
                        return null; // If we already failed with these credentials, don't retry.
                    }
                    return response.request().newBuilder()
                            .header("Authorization", credential)
                            .build();
                })
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .connectTimeout(15000, TimeUnit.MILLISECONDS)
                .cache(cache)
                .build();
    }

}
