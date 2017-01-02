package com.nikoyuwono.teamwork.service.account;

import com.nikoyuwono.teamwork.data.model.Account;
import com.nikoyuwono.teamwork.service.BaseCallback;

public interface AccountCallback extends BaseCallback {
    void onGetAccount(Account account);
}
