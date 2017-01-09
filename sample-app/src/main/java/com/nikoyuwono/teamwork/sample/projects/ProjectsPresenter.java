package com.nikoyuwono.teamwork.sample.projects;

import android.util.Log;

import com.nikoyuwono.teamwork.Teamwork;
import com.nikoyuwono.teamwork.data.model.Project;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by niko-yuwono on 17/01/09.
 */

class ProjectsPresenter implements ProjectsContract.Presenter {
    private static final String TAG = ProjectsPresenter.class.getName();

    private final ProjectsContract.View view;
    private final CompositeSubscription compositeSubscription;

    ProjectsPresenter(ProjectsContract.View view) {
        this.view = view;
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void stopAllPendingTasks() {
        compositeSubscription.clear();
    }

    @Override
    public void loadProjects() {
        view.showLoadingIndicator();
        final Subscription subscriptions = Teamwork.projectRequest()
                .newGetAllProjectsRequest()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(this::onGetError)
                .subscribe(this::onGetProjects);
        compositeSubscription.add(subscriptions);
    }

    private void onGetProjects(final List<Project> projects) {
        Log.d(TAG, "Got projects " + projects.toString());
        if (projects.size() > 0) {
            view.showProjects(projects);
        } else {
            view.showNoProject();
        }
        view.hideLoadingIndicator();
    }

    private void onGetError(final Throwable throwable) {
        Log.e(TAG, "Got Error " + throwable.getMessage());
        view.showNoProject();
        view.hideLoadingIndicator();
    }
}
