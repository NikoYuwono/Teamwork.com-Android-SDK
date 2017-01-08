package com.nikoyuwono.teamwork;

import android.content.Context;
import android.content.SharedPreferences;

import com.nikoyuwono.teamwork.data.net.ApiClient;
import com.nikoyuwono.teamwork.service.account.AccountRequest;
import com.nikoyuwono.teamwork.service.project.ProjectRequest;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

public final class Teamwork {
    private static final String PREFERENCE_NAME = "com.nikoyuwono.teamwork.SDK_STORE";

    private static ApiClient apiClient;
    private static Context applicationContext;

    private Teamwork() {
        throw new AssertionError("This constructor shouldn't be called!");
    }

    public static synchronized void initialize(final Context applicationContext) {
        Teamwork.applicationContext = applicationContext.getApplicationContext();
        final Cache cache = createCache(applicationContext.getCacheDir());
        final OkHttpClient okHttpClient = createOkHttpClient(cache);
        apiClient = new ApiClient.Builder()
                .okHttpClient(okHttpClient)
                .build();
    }

    private static Cache createCache(final File cacheDir) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(cacheDir, cacheSize);
    }

    private static OkHttpClient createOkHttpClient(final Cache cache) {
        return new OkHttpClient.Builder()
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .connectTimeout(15000, TimeUnit.MILLISECONDS)
                .cache(cache)
                .build();
    }

    public static Context getApplicationContext() {
        return applicationContext;
    }

    public static SharedPreferences getSharedPreferences() {
        return applicationContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
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
