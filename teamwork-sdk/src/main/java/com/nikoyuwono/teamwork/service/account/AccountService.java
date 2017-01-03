package com.nikoyuwono.teamwork.service.account;

import com.nikoyuwono.teamwork.data.model.Account;
import com.nikoyuwono.teamwork.service.RequestCallback;

import rx.Observable;

public interface AccountService {

    void getAccountDetails(RequestCallback<Account> accountCallback);
    Observable<Account> getAccountDetails();
    void authenticate(RequestCallback<Account> accountCallback);
    Observable<Account> authenticate();
}
