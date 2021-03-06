package com.nikoyuwono.teamwork.service.account;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nikoyuwono.teamwork.data.model.Account;
import com.nikoyuwono.teamwork.data.model.Project;
import com.nikoyuwono.teamwork.data.net.ApiClient;
import com.nikoyuwono.teamwork.service.BaseRequest;
import com.nikoyuwono.teamwork.service.RequestCallback;
import com.nikoyuwono.teamwork.service.project.ProjectDeserializer;
import com.nikoyuwono.teamwork.service.project.ProjectsDeserializer;

import java.util.List;

import rx.Observable;

public class AccountRequest extends BaseRequest<AccountService> {

    private static class AccountRequestLoader {
        private static final AccountRequest INSTANCE = new AccountRequest();
    }

    public void init(final ApiClient apiClient) {
        if (service == null) {
            this.service = new AccountServiceImpl(apiClient, gson);
        }
    }

    private AccountRequest() {
        if (AccountRequestLoader.INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Account.class, new AccountDeserializer())
                .create();
    }

    public static AccountRequest getInstance() {
        return AccountRequestLoader.INSTANCE;
    }

    public void newGetAccountDetailsRequest(RequestCallback<Account> callback) {
        service.getAccountDetails(callback);
    }

    public Observable<Account> newGetAccountDetailsRequest() {
        return service.getAccountDetails();
    }

    public void newAuthenticateRequest(String apiKey, RequestCallback<Account> callback) {
        service.authenticate(apiKey, callback);
    }

    public Observable<Account> newAuthenticateRequest(String apiKey) {
        return service.authenticate(apiKey);
    }

    public void newAuthenticateRequest(String userName, String password, RequestCallback<Account> callback) {
        service.authenticate(userName, password, callback);
    }

    public Observable<Account> newAuthenticateRequest(String userName, String password) {
        return service.authenticate(userName, password);
    }
}
