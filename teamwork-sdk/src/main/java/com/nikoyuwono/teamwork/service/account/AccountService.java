package com.nikoyuwono.teamwork.service.account;

import com.nikoyuwono.teamwork.data.model.Account;

import rx.Observable;

public interface AccountService {

    void getAccountDetails(AccountCallback accountCallback);
    Observable<Account> getAccountDetails();
    void authenticate(AccountCallback accountCallback);
    Observable<Account> authenticate();
}
