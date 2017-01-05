package com.nikoyuwono.teamwork.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.nikoyuwono.teamwork.Teamwork;
import com.nikoyuwono.teamwork.data.model.Account;
import com.nikoyuwono.teamwork.data.model.Project;
import com.nikoyuwono.teamwork.service.RequestCallback;

import java.util.List;

import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Teamwork.initialize(this, "twp_tAinFz7JhtmxhzNrXcY5MTfFj3h3", "nikoyuwono.teamwork.com");
        Teamwork.accountRequest()
                .newGetAccountDetailsRequest()
                .subscribe(new Action1<Account>() {
                    @Override
                    public void call(Account account) {
                        Log.i("NIKO", "Got account " + account);
                    }
                });
        Teamwork.projectRequest().newGetAllProjectsRequest(new RequestCallback<List<Project>>() {
            @Override
            public void onGetContent(List<Project> content) {
                Log.i("NIKO", "Got projects " + content.toString());
            }

            @Override
            public void onError(Exception e) {
                Log.i("NIKO", "Got error " + e.getMessage());
            }
        });
    }
}
