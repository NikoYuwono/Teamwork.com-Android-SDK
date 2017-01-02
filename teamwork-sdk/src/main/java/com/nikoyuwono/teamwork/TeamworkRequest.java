package com.nikoyuwono.teamwork;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nikoyuwono.teamwork.data.model.Account;
import com.nikoyuwono.teamwork.data.net.ApiClient;
import com.nikoyuwono.teamwork.service.account.AccountCallback;
import com.nikoyuwono.teamwork.service.account.AccountDeserializer;
import com.nikoyuwono.teamwork.service.account.AccountService;
import com.nikoyuwono.teamwork.service.account.AccountServiceImpl;

import okhttp3.OkHttpClient;
import rx.Observable;

public final class TeamworkRequest {

    private static TeamworkRequest instance;

    private final ApiClient apiClient;
    private Gson gson;

    static void initialize(final OkHttpClient okHttpClient, final String baseUrl) {
        final ApiClient apiClient = new ApiClient.Builder()
                .baseUrl(baseUrl)
                .okHttpClient(okHttpClient)
                .build();
        final Gson gson = new GsonBuilder()
                .registerTypeAdapter(Account.class, new AccountDeserializer())
                .create();
        instance = new TeamworkRequest(apiClient, gson);
    }

    private TeamworkRequest(final ApiClient apiClient,
                            final Gson gson) {
        this.apiClient = apiClient;
        this.gson = gson;
    }

    public static void newGetAccountDetailsRequest(AccountCallback accountCallback) {
        final AccountService accountService = new AccountServiceImpl(instance.apiClient, instance.gson);
        accountService.getAccountDetails(accountCallback);
    }

    public static Observable<Account> newGetAccountDetailsRequest() {
        return new AccountServiceImpl(instance.apiClient, instance.gson)
                .getAccountDetails();
    }

    public static void newAuthenticateRequest(AccountCallback accountCallback) {
        final AccountService accountService = new AccountServiceImpl(instance.apiClient, instance.gson);
        accountService.authenticate(accountCallback);
    }

    public static Observable<Account> newAuthenticateRequest() {
        return new AccountServiceImpl(instance.apiClient, instance.gson)
                .authenticate();
    }
}
