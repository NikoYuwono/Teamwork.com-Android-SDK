package com.nikoyuwono.teamwork.sample.login;

import com.nikoyuwono.teamwork.Teamwork;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by niko-yuwono on 17/01/09.
 */

class LoginPresenter implements LoginContract.Presenter {

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
                .subscribe(account -> {
                    view.hideLoadingIndicator();
                    view.launchProjectsView();
                });
        compositeSubscription.add(subscription);
    }

}
