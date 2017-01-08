package com.nikoyuwono.teamwork.service.account;

import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.google.gson.Gson;
import com.nikoyuwono.teamwork.Teamwork;
import com.nikoyuwono.teamwork.data.model.Account;
import com.nikoyuwono.teamwork.data.net.ApiClient;
import com.nikoyuwono.teamwork.service.RequestCallback;
import com.nikoyuwono.teamwork.service.Util;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.Response;
import rx.Observable;

import static com.nikoyuwono.teamwork.data.net.ApiClient.HOST_PREFERENCE_KEY;

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
        apiClient.withPath(GET_ACCOUNT_DETAILS_URL_PATH).get(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (accountCallback != null) {
                    accountCallback.onError(e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String content = response.body().string();
                final Account account = gson.fromJson(content, Account.class);
                if (accountCallback != null) {
                    accountCallback.onGetContent(account);
                }
            }
        });
    }

    @Override
    public Observable<Account> getAccountDetails() {
        return apiClient.withPath(GET_ACCOUNT_DETAILS_URL_PATH)
                .get()
                .map(Util::getContent)
                .map(content -> gson.fromJson(content, Account.class));
    }

    @Override
    public void authenticate(String userName, String password, @Nullable RequestCallback<Account> accountCallback) {
        final String credentials = Credentials.basic(userName, password);
        createAuthenticateCall(credentials, accountCallback);
    }

    @Override
    public Observable<Account> authenticate(String userName, String password) {
        final String credentials = Credentials.basic(userName, password);
        return createAuthenticateCall(credentials);
    }

    @Override
    public void authenticate(String apiKey, @Nullable RequestCallback<Account> accountCallback) {
        final String credentials = Credentials.basic(apiKey, "");
        createAuthenticateCall(credentials, accountCallback);
    }

    @Override
    public Observable<Account> authenticate(String apiKey) {
        final String credentials = Credentials.basic(apiKey, "");
        return createAuthenticateCall(credentials);
    }

    private void createAuthenticateCall(final String credential, @Nullable final RequestCallback<Account> accountCallback) {
        apiClient.usingAuthenticateHostWithPath(AUTHENTICATE_URL_PATH)
                .authorizationHeader(credential)
                .get(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (accountCallback != null) {
                    accountCallback.onError(e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String content = response.body().string();
                final Account account = gson.fromJson(content, Account.class);
                saveAccountUrlHost(account);
                if (accountCallback != null) {
                    accountCallback.onGetContent(account);
                }
            }
        });
    }

    private Observable<Account> createAuthenticateCall(final String credential) {
        return apiClient.usingAuthenticateHostWithPath(AUTHENTICATE_URL_PATH)
                .authorizationHeader(credential)
                .get()
                .map(Util::getContent)
                .map(content -> gson.fromJson(content, Account.class))
                .map(account -> {
                    saveAccountUrlHost(account);
                    return account;
                });
    }

    private void saveAccountUrlHost(Account account) {
        String url = account.getUrl();
        HttpUrl httpUrl = HttpUrl.parse(url);
        Teamwork.getSharedPreferences().edit().putString(HOST_PREFERENCE_KEY ,httpUrl.host());
    }

}
