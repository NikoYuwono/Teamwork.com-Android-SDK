package com.nikoyuwono.teamwork.service.account;

import android.support.annotation.Nullable;

import com.nikoyuwono.teamwork.data.model.Account;
import com.nikoyuwono.teamwork.service.RequestCallback;

import rx.Observable;

public interface AccountService {

    void getAccountDetails(@Nullable RequestCallback<Account> accountCallback);
    Observable<Account> getAccountDetails();
    void authenticate(String apiKey, @Nullable RequestCallback<Account> accountCallback);
    Observable<Account> authenticate(String apiKey);
    void authenticate(String userName, String password, @Nullable RequestCallback<Account> accountCallback);
    Observable<Account> authenticate(String userName, String password);
}
