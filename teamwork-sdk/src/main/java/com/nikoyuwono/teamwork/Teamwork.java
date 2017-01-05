package com.nikoyuwono.teamwork;

import android.content.Context;

import com.nikoyuwono.teamwork.data.net.ApiClient;
import com.nikoyuwono.teamwork.service.account.AccountRequest;
import com.nikoyuwono.teamwork.service.project.ProjectRequest;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;

public final class Teamwork {

    private static ApiClient apiClient;

    public static void initialize(final Context context,
                                  final String apiKey,
                                  final String baseUrl) {
        final Cache cache = createCache(context.getCacheDir());
        final OkHttpClient okHttpClient = createOkHttpClient(cache, apiKey);
        apiClient = new ApiClient.Builder()
                .host(baseUrl)
                .okHttpClient(okHttpClient)
                .build();
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

    public static AccountRequest accountRequest() {
        final AccountRequest accountRequest = AccountRequest.getInstance();
        accountRequest.init(apiClient);
        return accountRequest;
    }

    public static ProjectRequest projectRequest() {
        final ProjectRequest projectRequest = ProjectRequest.getInstance();
        projectRequest.init(apiClient);
        return projectRequest;
    }
}
