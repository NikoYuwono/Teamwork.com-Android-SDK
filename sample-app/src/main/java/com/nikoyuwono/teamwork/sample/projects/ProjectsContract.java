package com.nikoyuwono.teamwork.sample.projects;

import com.nikoyuwono.teamwork.data.model.Project;
import com.nikoyuwono.teamwork.sample.BasePresenter;
import com.nikoyuwono.teamwork.sample.BaseView;

import java.util.List;

/**
 * Created by niko-yuwono on 17/01/08.
 */

interface ProjectsContract {

    interface View extends BaseView {
        void showProjects(List<Project> projects);
        void showNoProject();
        void showErrorView();
    }

    interface Presenter extends BasePresenter {
        void loadProjects();
    }
}
