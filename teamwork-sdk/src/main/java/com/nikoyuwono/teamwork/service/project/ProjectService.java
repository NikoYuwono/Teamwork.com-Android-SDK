package com.nikoyuwono.teamwork.service.project;

import com.nikoyuwono.teamwork.data.model.GetProjectParameter;
import com.nikoyuwono.teamwork.data.model.NewProject;
import com.nikoyuwono.teamwork.data.model.Project;
import com.nikoyuwono.teamwork.service.RequestCallback;

import java.util.List;

import okhttp3.Response;
import rx.Observable;

public interface ProjectService {

    void createProject(NewProject newProject, RequestCallback<Response> callback);
    Observable<Response> createProject(NewProject newProject);

    void updateProject(String projectId, NewProject newProject, RequestCallback<Response> callback);
    Observable<Response> updateProject(String projectId, NewProject newProject);

    void deleteProject(String projectId, RequestCallback<Response> callback);
    Observable<Response> deleteProject(String projectId);

    void getAllProjects(RequestCallback<List<Project>> callback);
    Observable<List<Project>> getAllProjects();

    void getAllProjects(GetProjectParameter getProjectParameter, RequestCallback<List<Project>> callback);
    Observable<List<Project>> getAllProjects(GetProjectParameter getProjectParameter);

    void getProject(String projectId, boolean includePeople, RequestCallback<Project> callback);
    Observable<Project> getProject(String projectId, boolean includePeople);

    void getCompanyProjects(String companyId, RequestCallback<List<Project>> callback);
    Observable<List<Project>> getCompanyProjects(String companyId);

    void getStarredProjects(RequestCallback<List<Project>> callback);
    Observable<List<Project>> getStarredProjects();

    void starProject(String projectId, RequestCallback<Response> callback);
    Observable<Response> starProject(String projectId);

    void unstarProject(String projectId, RequestCallback<Response> callback);
    Observable<Response> unstarProject(String projectId);

}
