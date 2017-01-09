package com.nikoyuwono.teamwork.sample.login;

import android.util.Log;

import com.nikoyuwono.teamwork.Teamwork;
import com.nikoyuwono.teamwork.data.model.Account;
import com.nikoyuwono.teamwork.sample.projects.ProjectsPresenter;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by niko-yuwono on 17/01/09.
 */

class LoginPresenter implements LoginContract.Presenter {
    private static final String TAG = LoginPresenter.class.getName();

    private LoginContract.View view;
    private final CompositeSubscription compositeSubscription;

    LoginPresenter(LoginContract.View view) {
        this.view = view;
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void stopAllPendingTasks() {
        compositeSubscription.clear();
    }

    @Override
    public void login(String apiKey) {
        view.showLoadingIndicator();
        final Subscription subscription = Teamwork.accountRequest()
                .newAuthenticateRequest(apiKey)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(this::onGetError)
                .subscribe(this::onLoginSuccess);
        compositeSubscription.add(subscription);
    }

    private void onLoginSuccess(Account account) {
        Log.i(TAG, "Login success with accout " + account.toString());
        view.hideLoadingIndicator();
        view.launchProjectsView();
    }

    private void onGetError(final Throwable throwable) {
        Log.e(TAG, "Got Error " + throwable.getMessage());
        view.showErrorToast();
        view.hideLoadingIndicator();
    }
}
