package com.nikoyuwono.teamwork.service.account;

import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.google.gson.Gson;
import com.nikoyuwono.teamwork.data.model.Account;
import com.nikoyuwono.teamwork.data.net.ApiClient;
import com.nikoyuwono.teamwork.service.RequestCallback;
import com.nikoyuwono.teamwork.service.Util;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import rx.Observable;

class AccountServiceImpl implements AccountService {

    @VisibleForTesting
    static final String GET_ACCOUNT_DETAILS_URL_PATH = "/account.json";
    @VisibleForTesting
    static final String AUTHENTICATE_URL_PATH = "/authenticate.json";

    private final ApiClient apiClient;
    private final Gson gson;

    public AccountServiceImpl(final ApiClient apiClient, final Gson gson) {
        this.apiClient = apiClient;
        this.gson = gson;
    }

    @Override
    public void getAccountDetails(@Nullable RequestCallback<Account> accountCallback) {
        createAccountCall(GET_ACCOUNT_DETAILS_URL_PATH, accountCallback);
    }

    @Override
    public Observable<Account> getAccountDetails() {
        return createAccountCall(GET_ACCOUNT_DETAILS_URL_PATH);
    }

    @Override
    public void authenticate(@Nullable RequestCallback<Account> accountCallback) {
        createAccountCall(AUTHENTICATE_URL_PATH, accountCallback);
    }

    @Override
    public Observable<Account> authenticate() {
        return createAccountCall(AUTHENTICATE_URL_PATH);
    }

    private void createAccountCall(final String path, final RequestCallback<Account> accountCallback) {
        apiClient.withPath(path).get(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                accountCallback.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String content = response.body().string();
                final Account account = gson.fromJson(content, Account.class);
                accountCallback.onGetContent(account);
            }
        });
    }

    private Observable<Account> createAccountCall(String path) {
        return apiClient.withPath(path)
                .get()
                .map(Util::getContent)
                .map(content -> gson.fromJson(content, Account.class));
    }
}
