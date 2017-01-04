package com.nikoyuwono.teamwork.service.project;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nikoyuwono.teamwork.data.model.GetProjectParameter;
import com.nikoyuwono.teamwork.data.model.NewProject;
import com.nikoyuwono.teamwork.data.model.Project;
import com.nikoyuwono.teamwork.data.model.ProjectStatus;
import com.nikoyuwono.teamwork.data.net.ApiClient;
import com.nikoyuwono.teamwork.service.RequestCallback;
import com.nikoyuwono.teamwork.service.Util;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import rx.Observable;

public class ProjectServiceImpl implements ProjectService {

    private static final String PROJECTS_URL_PATH = "projects.json";
    private static final String PROJECTS_WITH_ID_URL_PATH = "projects/%s.json";
    private static final String COMPANY_PROJECTS_URL_PATH = "companies/%s/projects.json";
    private static final String STARRED_PROJECTS_URL_PATH = "projects/starred.json";
    private static final String STAR_A_PROJECT_URL_PATH = "/projects/%s/star.json";
    private static final String UNSTAR_A_PROJECT_URL_PATH = "/projects/%s/unstar.json";

    private static final Type PROJECT_LIST_TYPE = new TypeToken<ArrayList<Project>>() {}.getType();

    private final ApiClient apiClient;
    private final Gson gson;

    public ProjectServiceImpl(ApiClient apiClient, Gson gson) {
        this.apiClient = apiClient;
        this.gson = gson;
    }

    @Override
    public void createProject(@NonNull NewProject newProject, @Nullable RequestCallback<Response> callback) {
        apiClient.withPath(PROJECTS_URL_PATH)
                .jsonBody(gson.toJson(newProject))
                .post(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        if (callback != null) {
                            callback.onError(e);
                        }
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (callback != null) {
                            callback.onGetContent(response);
                        }
                    }
                });
    }

    @Override
    public Observable<Response> createProject(@NonNull NewProject newProject) {
        return apiClient.withPath(PROJECTS_URL_PATH)
                .jsonBody(gson.toJson(newProject))
                .post();
    }

    @Override
    public void updateProject(@NonNull String projectId, @NonNull NewProject newProject, RequestCallback<Response> callback) {
        apiClient.withPath(projectsUrlPath(projectId))
                .jsonBody(gson.toJson(newProject))
                .put(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        if (callback != null) {
                            callback.onError(e);
                        }
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (callback != null) {
                            callback.onGetContent(response);
                        }
                    }
                });
    }

    @Override
    public Observable<Response> updateProject(@NonNull String projectId, @NonNull NewProject newProject) {
        return apiClient.withPath(projectsUrlPath(projectId))
                .jsonBody(gson.toJson(newProject))
                .put();
    }

    @Override
    public void deleteProject(@NonNull String projectId, RequestCallback<Response> callback) {
        apiClient.withPath(projectsUrlPath(projectId))
                .delete(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        if (callback != null) {
                            callback.onError(e);
                        }
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (callback != null) {
                            callback.onGetContent(response);
                        }
                    }
                });
    }

    @Override
    public Observable<Response> deleteProject(@NonNull String projectId) {
        return apiClient.withPath(projectsUrlPath(projectId))
                .delete();
    }

    @Override
    public void getAllProjects(RequestCallback<List<Project>> callback) {
        apiClient.withPath(PROJECTS_URL_PATH)
                .get(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        if (callback != null) {
                            callback.onError(e);
                        }
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (callback != null) {
                            final String content = response.body().string();
                            final List<Project> projects = gson.fromJson(content, PROJECT_LIST_TYPE);
                            callback.onGetContent(projects);
                        }
                    }
                });
    }

    @Override
    public Observable<List<Project>> getAllProjects() {
        return apiClient.withPath(PROJECTS_URL_PATH)
                .get()
                .map(Util::getContent)
                .<List<Project>>map(content -> gson.fromJson(content, PROJECT_LIST_TYPE));
    }

    @Override
    public void getAllProjects(GetProjectParameter getProjectParameter, RequestCallback<List<Project>> callback) {
        createGetAllProjectsExecutor(getProjectParameter)
                .get(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        if (callback != null) {
                            callback.onError(e);
                        }
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (callback != null) {
                            final String content = response.body().string();
                            final List<Project> projects = gson.fromJson(content, PROJECT_LIST_TYPE);
                            callback.onGetContent(projects);
                        }
                    }
                });
    }

    @Override
    public Observable<List<Project>> getAllProjects(GetProjectParameter getProjectParameter) {
        return createGetAllProjectsExecutor(getProjectParameter)
                .get()
                .map(Util::getContent)
                .<List<Project>>map(content -> gson.fromJson(content, PROJECT_LIST_TYPE));
    }

    @Override
    public void getProject(@NonNull String projectId, boolean includePeople, RequestCallback<Project> callback) {
        apiClient.withPath(projectsUrlPath(projectId))
                .param("includePeople", includePeople)
                .get(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        if (callback != null) {
                            callback.onError(e);
                        }
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (callback != null) {
                            final String content = response.body().string();
                            final Project projects = gson.fromJson(content, Project.class);
                            callback.onGetContent(projects);
                        }
                    }
                });
    }

    @Override
    public Observable<Project> getProject(@NonNull String projectId, boolean includePeople) {
        return apiClient.withPath(projectsUrlPath(projectId))
                .param("includePeople", includePeople)
                .get()
                .map(Util::getContent)
                .map(content -> gson.fromJson(content, Project.class));
    }

    @Override
    public void getCompanyProjects(@NonNull String companyId, RequestCallback<List<Project>> callback) {
        apiClient.withPath(String.format(COMPANY_PROJECTS_URL_PATH, companyId))
                .get(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        if (callback != null) {
                            callback.onError(e);
                        }
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (callback != null) {
                            final String content = response.body().string();
                            final List<Project> projects = gson.fromJson(content, PROJECT_LIST_TYPE);
                            callback.onGetContent(projects);
                        }
                    }
                });
    }

    @Override
    public Observable<List<Project>> getCompanyProjects(@NonNull String companyId) {
        return apiClient.withPath(String.format(COMPANY_PROJECTS_URL_PATH, companyId))
                .get()
                .map(Util::getContent)
                .<List<Project>>map(content -> gson.fromJson(content, PROJECT_LIST_TYPE));
    }

    @Override
    public void getStarredProjects(RequestCallback<List<Project>> callback) {
        apiClient.withPath(STARRED_PROJECTS_URL_PATH)
                .get(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        if (callback != null) {
                            callback.onError(e);
                        }
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (callback != null) {
                            final String content = response.body().string();
                            final List<Project> projects = gson.fromJson(content, PROJECT_LIST_TYPE);
                            callback.onGetContent(projects);
                        }
                    }
                });
    }

    @Override
    public Observable<List<Project>> getStarredProjects() {
        return apiClient.withPath(STARRED_PROJECTS_URL_PATH)
                .get()
                .map(Util::getContent)
                .<List<Project>>map(content -> gson.fromJson(content, PROJECT_LIST_TYPE));
    }

    @Override
    public void starProject(@NonNull String projectId, RequestCallback<Response> callback) {
        apiClient.withPath(String.format(STAR_A_PROJECT_URL_PATH, projectId))
                .put(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        if (callback != null) {
                            callback.onError(e);
                        }
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (callback != null) {
                            callback.onGetContent(response);
                        }
                    }
                });
    }

    @Override
    public Observable<Response> starProject(@NonNull String projectId) {
        return apiClient.withPath(String.format(STAR_A_PROJECT_URL_PATH, projectId)).put();
    }

    @Override
    public void unstarProject(@NonNull String projectId, RequestCallback<Response> callback) {
        apiClient.withPath(String.format(UNSTAR_A_PROJECT_URL_PATH, projectId))
                .put(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        if (callback != null) {
                            callback.onError(e);
                        }
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (callback != null) {
                            callback.onGetContent(response);
                        }
                    }
                });
    }

    @Override
    public Observable<Response> unstarProject(@NonNull String projectId) {
        return apiClient.withPath(String.format(UNSTAR_A_PROJECT_URL_PATH, projectId)).put();
    }

    private String projectsUrlPath(@NonNull String projectId) {
        return String.format(PROJECTS_WITH_ID_URL_PATH, projectId);
    }

    private ApiClient.Executor createGetAllProjectsExecutor(@Nullable GetProjectParameter getProjectParameter) {
        final ApiClient.Executor executor = apiClient.withPath(PROJECTS_URL_PATH);

        if (getProjectParameter == null) {
            return executor;
        }

        final ProjectStatus projectStatus = getProjectParameter.getProjectStatus();
        if (projectStatus != null) {
            executor.param("projectStatus", projectStatus);
        }

        final String updatedAfterDate = getProjectParameter.getUpdatedAfterDate();
        if (!TextUtils.isEmpty(updatedAfterDate)) {
            executor.param("updatedAfterDate", updatedAfterDate);
        }

        final String updatedAfterTime = getProjectParameter.getUpdatedAfterTime();
        if (!TextUtils.isEmpty(updatedAfterTime)) {
            executor.param("updatedAfterTime", updatedAfterTime);
        }

        final String createdAfterDate = getProjectParameter.getCreatedAfterDate();
        if (!TextUtils.isEmpty(createdAfterDate)) {
            executor.param("createdAfterDate", createdAfterDate);
        }

        final String createdAfterTime = getProjectParameter.getCreatedAfterTime();
        if (!TextUtils.isEmpty(createdAfterTime)) {
            executor.param("createdAfterTime", createdAfterTime);
        }

        final String categoryId = getProjectParameter.getCategoryId();
        if (!TextUtils.isEmpty(categoryId)) {
            executor.param("categoryId", categoryId);
        }

        final String orderBy = getProjectParameter.getOrderBy();
        if (!TextUtils.isEmpty(orderBy)) {
            executor.param("orderBy", orderBy);
        }

        final boolean includePeople = getProjectParameter.isIncludePeople();
        executor.param("includePeople", includePeople);

        return executor;
    }
}
