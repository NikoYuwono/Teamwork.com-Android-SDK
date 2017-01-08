package com.nikoyuwono.teamwork.sample.projects;

import com.nikoyuwono.teamwork.data.model.Project;

import java.util.List;

/**
 * Created by niko-yuwono on 17/01/08.
 */

public interface ProjectsContract {

    interface View {

        void showLoadingIndicator();
        void hideLoadingIndicator();
        void showProjects(List<Project> projects);
        void showNoProject();

    }

    interface Presenter {

        void loadProjects();

    }
}
