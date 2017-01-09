package com.nikoyuwono.teamwork.sample.projects;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nikoyuwono.teamwork.data.model.Project;
import com.nikoyuwono.teamwork.sample.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by niko-yuwono on 17/01/09.
 */

public class ProjectsActivity extends AppCompatActivity implements ProjectsContract.View {

    @BindView(R.id.project_list)
    RecyclerView projectList;
    @BindView(R.id.loading_view)
    RelativeLayout loadingView;
    @BindView(R.id.error_view)
    RelativeLayout errorView;
    @BindView(R.id.error_message)
    TextView errorMessage;

    private ProjectListAdapter projectListAdapter;
    private ProjectsPresenter projectsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        ButterKnife.bind(this);
        projectsPresenter = new ProjectsPresenter(this);
        setupProjectList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        projectsPresenter.loadProjects();
    }

    @Override
    protected void onPause() {
        super.onPause();
        projectsPresenter.stopAllPendingTasks();
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
    public void showProjects(List<Project> projects) {
        projectListAdapter.updateProjects(projects);
        projectListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNoProject() {
        errorView.setVisibility(View.VISIBLE);
        errorMessage.setText(R.string.projects_no_projects_found);
    }

    @Override
    public void showErrorView() {
        errorView.setVisibility(View.VISIBLE);
    }

    private void setupProjectList() {
        final LinearLayoutManager projectListLayoutManager = new LinearLayoutManager(this);
        projectListAdapter = new ProjectListAdapter();
        projectList.setLayoutManager(projectListLayoutManager);
        projectList.setAdapter(projectListAdapter);
    }
}
