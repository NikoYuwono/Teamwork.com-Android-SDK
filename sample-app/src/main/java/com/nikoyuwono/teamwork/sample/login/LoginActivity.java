package com.nikoyuwono.teamwork.sample.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.nikoyuwono.teamwork.sample.R;
import com.nikoyuwono.teamwork.sample.projects.ProjectsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by niko-yuwono on 17/01/09.
 */

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    @BindView(R.id.api_key_input)
    EditText apiKeyInput;
    @BindView(R.id.loading_view)
    RelativeLayout loadingView;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenter(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (loginPresenter != null) {
            loginPresenter.stopAllPendingTasks();
        }
    }

    @Override
    public void showLoadingIndicator() {
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        loadingView.setVisibility(View.GONE);
    }

    @Override
    public void launchProjectsView() {
        final Intent intent = new Intent(this, ProjectsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @OnClick(R.id.login_button)
    void onClickLoginButton() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
        final String apiKey = apiKeyInput.getText().toString();
        if (!TextUtils.isEmpty(apiKey)) {
            loginPresenter.login(apiKey);
        }
    }
}
