package com.nikoyuwono.teamwork.service.account;

import android.support.annotation.Nullable;

import com.nikoyuwono.teamwork.data.model.Account;
import com.nikoyuwono.teamwork.service.RequestCallback;

import rx.Observable;

interface AccountService {

    void getAccountDetails(@Nullable RequestCallback<Account> accountCallback);
    Observable<Account> getAccountDetails();
    void authenticate(@Nullable RequestCallback<Account> accountCallback);
    Observable<Account> authenticate();
}
