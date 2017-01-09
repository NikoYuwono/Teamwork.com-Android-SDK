package com.nikoyuwono.teamwork.sample.login;

import com.nikoyuwono.teamwork.sample.BasePresenter;
import com.nikoyuwono.teamwork.sample.BaseView;

/**
 * Created by niko-yuwono on 17/01/09.
 */

interface LoginContract {

    interface View extends BaseView {
        void launchProjectsView();
        void showErrorToast();
    }

    interface Presenter extends BasePresenter {
        void login(String apiKey);
    }

}
