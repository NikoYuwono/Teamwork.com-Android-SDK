package com.nikoyuwono.teamwork.service.project;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nikoyuwono.teamwork.data.model.Project;
import com.nikoyuwono.teamwork.data.net.ApiClient;
import com.nikoyuwono.teamwork.service.BaseRequest;
import com.nikoyuwono.teamwork.service.RequestCallback;

import java.util.List;

import rx.Observable;

public final class ProjectRequest extends BaseRequest<ProjectService> {

    private static class ProjectRequestLoader {
        private static final ProjectRequest INSTANCE = new ProjectRequest();
    }

    public void init(final ApiClient apiClient) {
        this.service = new ProjectServiceImpl(apiClient, gson);
    }

    private ProjectRequest() {
        if (ProjectRequestLoader.INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Project.class, new ProjectDeserializer())
                .registerTypeAdapter(new TypeToken<List<Project>>(){}.getType(), new ProjectsDeserializer())
                .create();
    }

    public static ProjectRequest getInstance() {
        return ProjectRequestLoader.INSTANCE;
    }

    public void newGetAllProjectsRequest(RequestCallback<List<Project>> callback) {
        service.getAllProjects(callback);
    }

    public Observable<List<Project>> newGetAllProjectsRequest() {
        return service.getAllProjects();
    }
}
