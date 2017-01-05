package com.nikoyuwono.teamwork.service.project;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nikoyuwono.teamwork.data.model.NewProject;
import com.nikoyuwono.teamwork.data.model.Project;
import com.nikoyuwono.teamwork.service.BaseServiceTest;
import com.nikoyuwono.teamwork.service.HttpMethod;
import com.nikoyuwono.teamwork.service.RequestCallback;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import okhttp3.Response;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;
import rx.observers.TestSubscriber;

import static com.nikoyuwono.teamwork.service.project.ProjectServiceImpl.COMPANY_PROJECTS_URL_PATH;
import static com.nikoyuwono.teamwork.service.project.ProjectServiceImpl.PROJECTS_URL_PATH;
import static com.nikoyuwono.teamwork.service.project.ProjectServiceImpl.PROJECTS_WITH_ID_URL_PATH;
import static com.nikoyuwono.teamwork.service.project.ProjectServiceImpl.STARRED_PROJECTS_URL_PATH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class ProjectServiceImplTest extends BaseServiceTest {

    @BeforeClass
    public static void setupGson() {
        gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .serializeNulls()
                .registerTypeAdapter(Project.class, new ProjectDeserializer())
                .registerTypeAdapter(new TypeToken<List<Project>>() {}.getType(), new ProjectsDeserializer())
                .create();
    }

    @Test
    public void createProjectSuccess() throws InterruptedException {
        mockWebServer.setDispatcher(dispatcher);
        final AtomicReference<Response> responseReference = new AtomicReference<>();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        projectService.createProject(mock(NewProject.class), new RequestCallback<Response>() {
            @Override
            public void onGetContent(Response content) {
                responseReference.set(content);
                countDownLatch.countDown();
            }

            @Override
            public void onError(Exception e) {

            }
        });

        assertThat(countDownLatch.await(10, TimeUnit.SECONDS)).isTrue();

        final Response response = responseReference.get();
        assertThat(response.isSuccessful()).isTrue();
    }

    @Test
    public void createProjectWithObservableSuccess() {
        mockWebServer.setDispatcher(dispatcher);
        final TestSubscriber<Response> testSubscriber = new TestSubscriber<>();
        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        projectService.createProject(mock(NewProject.class)).subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);
        testSubscriber.assertValueCount(1);

        final Response response = testSubscriber.getOnNextEvents().get(0);
        assertThat(response.isSuccessful()).isTrue();
    }

    @Test
    public void createProjectServerError() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(500).setBody("Something went wrong on server side!"));
        final AtomicReference<Exception> exceptionReference = new AtomicReference<>();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        projectService.createProject(mock(NewProject.class),new RequestCallback<Response>() {
            @Override
            public void onGetContent(Response content) {
            }

            @Override
            public void onError(Exception e) {
                exceptionReference.set(e);
                countDownLatch.countDown();
            }
        });

        assertThat(countDownLatch.await(10, TimeUnit.SECONDS)).isTrue();

        final Exception exception = exceptionReference.get();
        assertThat(exception).isInstanceOf(IOException.class);
    }

    @Test
    public void createProjectWithObservableServerError() {
        mockWebServer.enqueue(new MockResponse().setResponseCode(500).setBody("Something went wrong on server side!"));
        final TestSubscriber<Response> testSubscriber = new TestSubscriber<>();
        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        projectService.createProject(mock(NewProject.class)).subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);
        testSubscriber.assertError(IOException.class);
    }

    @Test
    public void createProjectShouldUsePost() throws InterruptedException {
        mockWebServer.setDispatcher(dispatcher);
        final TestSubscriber<Response> testSubscriber = new TestSubscriber<>();
        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        projectService.createProject(mock(NewProject.class)).subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertThat(recordedRequest.getPath()).isEqualTo(PROJECTS_URL_PATH);
        assertThat(recordedRequest.getMethod()).isEqualTo(HttpMethod.POST);
    }

    private static final Dispatcher dispatcher = new Dispatcher() {
        @Override
        public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
            if (request == null || request.getPath() == null) {
                return new MockResponse().setResponseCode(400);
            }
            switch (request.getMethod()) {
                case "POST":
                case "PUT":
                case "DELETE":
                    return new MockResponse();
                case "GET":
                    switch (request.getPath()) {
                        case PROJECTS_URL_PATH:
                        case COMPANY_PROJECTS_URL_PATH:
                        case STARRED_PROJECTS_URL_PATH:
                            return new MockResponse().setBody("{\n" +
                                    "    \"STATUS\": \"OK\",\n" +
                                    "    \"projects\": [\n" +
                                    "        {\n" +
                                    "            \"company\": {\n" +
                                    "                \"name\": \"Demo 1 Company\",\n" +
                                    "                \"is-owner\": \"1\",\n" +
                                    "                \"id\": \"999\"\n" +
                                    "            },\n" +
                                    "            \"starred\": false,\n" +
                                    "            \"name\": \"Demo Project\",\n" +
                                    "            \"show-announcement\": false,\n" +
                                    "            \"announcement\": \"\",\n" +
                                    "            \"description\": \"\",\n" +
                                    "            \"status\": \"active\",\n" +
                                    "            \"isProjectAdmin\": false,\n" +
                                    "            \"created-on\": \"2013-12-04T19:11:44Z\",\n" +
                                    "            \"category\": {\n" +
                                    "                \"name\": \"\",\n" +
                                    "                \"id\": \"\"\n" +
                                    "            },\n" +
                                    "            \"start-page\": \"projectoverview\",\n" +
                                    "            \"startDate\": \"20131204\",\n" +
                                    "            \"logo\": \"http://demo1company.teamwork.com/images/logo.jpg\",\n" +
                                    "            \"notifyeveryone\": false,\n" +
                                    "            \"id\": \"999\",\n" +
                                    "            \"last-changed-on\": \"2014-03-18T11:20:49Z\",\n" +
                                    "            \"endDate\": \"20140313\",\n" +
                                    "            \"harvest-timers-enabled\":\"true\"\n" +
                                    "        }\n" +
                                    "    ]\n" +
                                    "}");
                        case PROJECTS_WITH_ID_URL_PATH:
                            return new MockResponse().setBody("{\n" +
                                    "    \"project\": {\n" +
                                    "        \"company\": {\n" +
                                    "            \"name\": \"Demo 1 Company\",\n" +
                                    "            \"id\": \"999\"\n" +
                                    "        },\n" +
                                    "        \"starred\": false,\n" +
                                    "        \"name\": \"demo\",\n" +
                                    "        \"show-announcement\": false,\n" +
                                    "        \"announcement\": \"\",\n" +
                                    "        \"description\": \"A demo project\",\n" +
                                    "        \"status\": \"active\",\n" +
                                    "        \"created-on\": \"2014-03-28T15:24:22Z\",\n" +
                                    "        \"category\": {\n" +
                                    "            \"name\": \"\",\n" +
                                    "            \"id\": \"\"\n" +
                                    "        },\n" +
                                    "        \"start-page\": \"projectoverview\",\n" +
                                    "        \"logo\": \"http://demo1company.teamwork.com/images/logo.jpg\",\n" +
                                    "        \"startDate\": \"\",\n" +
                                    "        \"notifyeveryone\": false,\n" +
                                    "        \"id\": \"999\",\n" +
                                    "        \"last-changed-on\": \"2014-04-01T14:29:32Z\",\n" +
                                    "        \"endDate\": \"\",\n" +
                                    "        \"harvest-timers-enabled\":\"true\"\n" +
                                    "    },\n" +
                                    "    \"STATUS\": \"OK\"\n" +
                                    "}");
                    }
                default:
                    return new MockResponse().setResponseCode(404);
            }
        }
    };
}
