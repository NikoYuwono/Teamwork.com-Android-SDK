package com.nikoyuwono.teamwork.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.nikoyuwono.teamwork.Teamwork;
import com.nikoyuwono.teamwork.TeamworkRequest;
import com.nikoyuwono.teamwork.data.model.Account;
import com.nikoyuwono.teamwork.service.account.AccountCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Teamwork.initialize(this, "twp_tAinFz7JhtmxhzNrXcY5MTfFj3h3", "nikoyuwono.teamwork.com");
        TeamworkRequest.newGetAccountDetailsRequest(new AccountCallback() {
            @Override
            public void onGetAccount(Account account) {
                Log.i("NIKO", "Got account "+account);
            }

            @Override
            public void onError(Exception e) {
                Log.i("NIKO", "Got error "+e.getMessage());
            }
        });
    }
}
