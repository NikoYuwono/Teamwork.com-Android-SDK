package com.nikoyuwono.teamwork.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.nikoyuwono.teamwork.Teamwork;
import com.nikoyuwono.teamwork.data.model.Account;
import com.nikoyuwono.teamwork.data.model.Project;
import com.nikoyuwono.teamwork.service.RequestCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.project_list)
    RecyclerView projectList;

    private ProjectListAdapter projectListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        projectList.setLayoutManager(new LinearLayoutManager(this));
        Teamwork.initialize(this);
        Teamwork.accountRequest().newAuthenticateRequest("niko.yuwono.91@gmail.com", "Teamwork2017", new RequestCallback<Account>() {
            @Override
            public void onGetContent(Account content) {
                Log.i("NIKO", "Got account " + content.toString());
            }

            @Override
            public void onError(Exception e) {
                Log.i("NIKO", "Got error " + e.getMessage());
            }
        });
//        Teamwork.accountRequest()
//                .newGetAccountDetailsRequest()
//                .subscribe(account -> {
//                    Log.i("NIKO", "Got account " + account);
//                });
//        Teamwork.projectRequest().newGetAllProjectsRequest(new RequestCallback<List<Project>>() {
//            @Override
//            public void onGetContent(List<Project> content) {
//            }
//
//            @Override
//            public void onError(Exception e) {
//                Log.i("NIKO", "Got error " + e.getMessage());
//            }
//        });
//        Teamwork.projectRequest()
//                .newGetAllProjectsRequest()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(projects -> {
//                    Log.i("NIKO", "Got projects " + projects.toString());
//                    projectListAdapter = new ProjectListAdapter(projects);
//                    projectList.setAdapter(projectListAdapter);
//                });
    }
}
