package com.nikoyuwono.teamwork.service.project;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.nikoyuwono.teamwork.data.model.GetProjectParameter;
import com.nikoyuwono.teamwork.data.model.NewProject;
import com.nikoyuwono.teamwork.data.model.Project;
import com.nikoyuwono.teamwork.service.RequestCallback;

import java.util.List;

import okhttp3.Response;
import rx.Observable;

public interface ProjectService {

    void createProject(@NonNull NewProject newProject, @Nullable RequestCallback<Response> callback);
    Observable<Response> createProject(@NonNull NewProject newProject);

    void updateProject(@NonNull String projectId, @NonNull NewProject newProject, @Nullable RequestCallback<Response> callback);
    Observable<Response> updateProject(@NonNull String projectId, @NonNull NewProject newProject);

    void deleteProject(@NonNull String projectId, @Nullable RequestCallback<Response> callback);
    Observable<Response> deleteProject(@NonNull String projectId);

    void getAllProjects(@Nullable RequestCallback<List<Project>> callback);
    Observable<List<Project>> getAllProjects();

    void getAllProjects(@Nullable GetProjectParameter getProjectParameter, @Nullable RequestCallback<List<Project>> callback);
    Observable<List<Project>> getAllProjects(@Nullable GetProjectParameter getProjectParameter);

    void getProject(@NonNull String projectId, @Nullable RequestCallback<Project> callback);
    Observable<Project> getProject(@NonNull String projectId);

    void getProject(@NonNull String projectId, boolean includePeople, @Nullable RequestCallback<Project> callback);
    Observable<Project> getProject(@NonNull String projectId, boolean includePeople);

    void getCompanyProjects(@NonNull String companyId, @Nullable RequestCallback<List<Project>> callback);
    Observable<List<Project>> getCompanyProjects(@NonNull String companyId);

    void getStarredProjects(@Nullable RequestCallback<List<Project>> callback);
    Observable<List<Project>> getStarredProjects();

    void starProject(@NonNull String projectId, @Nullable RequestCallback<Response> callback);
    Observable<Response> starProject(@NonNull String projectId);

    void unstarProject(@NonNull String projectId, @Nullable RequestCallback<Response> callback);
    Observable<Response> unstarProject(@NonNull String projectId);

}
