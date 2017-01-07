package com.nikoyuwono.teamwork.service.project;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nikoyuwono.teamwork.data.model.NewProject;
import com.nikoyuwono.teamwork.data.model.Project;
import com.nikoyuwono.teamwork.data.net.ApiClient;
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
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;
import rx.observers.TestSubscriber;

import static com.nikoyuwono.teamwork.service.project.ProjectServiceImpl.COMPANY_PROJECTS_URL_PATH;
import static com.nikoyuwono.teamwork.service.project.ProjectServiceImpl.PROJECTS_URL_PATH;
import static com.nikoyuwono.teamwork.service.project.ProjectServiceImpl.PROJECTS_WITH_ID_URL_PATH;
import static com.nikoyuwono.teamwork.service.project.ProjectServiceImpl.STARRED_PROJECTS_URL_PATH;
import static com.nikoyuwono.teamwork.service.project.ProjectServiceImpl.STAR_A_PROJECT_URL_PATH;
import static com.nikoyuwono.teamwork.service.project.ProjectServiceImpl.UNSTAR_A_PROJECT_URL_PATH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class ProjectServiceImplTest extends BaseServiceTest {

    private static final String FAKE_PROJECT_ID = "2323";
    private static final String FAKE_COMPANY_ID = "8888";

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
    public void createProject_ShouldSucceed_WhenNewProjectIsPassedAndApiKeyIsCorrect() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        mockWebServer.enqueue(new MockResponse());
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
        assertRequestHasCorrectCredential();
    }

    @Test
    public void createProject_WithObservable_ShouldSucceed_WhenNewProjectIsPassedAndApiKeyIsCorrect() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        mockWebServer.enqueue(new MockResponse());
        final TestSubscriber<Response> testSubscriber = new TestSubscriber<>();
        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        projectService.createProject(mock(NewProject.class)).subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);
        testSubscriber.assertValueCount(1);

        final Response response = testSubscriber.getOnNextEvents().get(0);
        assertThat(response.isSuccessful()).isTrue();
        assertRequestHasCorrectCredential();
    }

    @Test
    public void createProject_ShouldFail_WhenNoApiKeyIsProvided() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        final AtomicReference<Exception> exceptionReference = new AtomicReference<>();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final ProjectService projectService = new ProjectServiceImpl(apiClientWithoutAuthenticator, gson);
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
    public void createProject_withObservable_ShouldFail_WhenNoApiKeyIsProvided() {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        final TestSubscriber<Response> testSubscriber = new TestSubscriber<>();
        final ProjectService projectService = new ProjectServiceImpl(apiClientWithoutAuthenticator, gson);
        projectService.createProject(mock(NewProject.class)).subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);
        testSubscriber.assertError(IOException.class);
    }

    @Test
    public void createProject_ShouldUsePost() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        mockWebServer.enqueue(new MockResponse());
        final TestSubscriber<Response> testSubscriber = new TestSubscriber<>();
        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        projectService.createProject(mock(NewProject.class)).subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertThat(recordedRequest.getPath()).isEqualTo(PROJECTS_URL_PATH);
        assertThat(recordedRequest.getMethod()).isEqualTo(HttpMethod.POST);
    }

    @Test
    public void updateProject_ShouldSucceed_WhenNewProjectIsPassedAndApiKeyIsCorrect() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        mockWebServer.enqueue(new MockResponse());
        final AtomicReference<Response> responseReference = new AtomicReference<>();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        projectService.updateProject(FAKE_PROJECT_ID, mock(NewProject.class), new RequestCallback<Response>() {
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
        assertRequestHasCorrectCredential();
    }

    @Test
    public void updateProject_WithObservable_ShouldSucceed_WhenNewProjectIsPassedAndApiKeyIsCorrect() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        mockWebServer.enqueue(new MockResponse());
        final TestSubscriber<Response> testSubscriber = new TestSubscriber<>();
        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        projectService.updateProject(FAKE_PROJECT_ID, mock(NewProject.class)).subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);
        testSubscriber.assertValueCount(1);

        final Response response = testSubscriber.getOnNextEvents().get(0);
        assertThat(response.isSuccessful()).isTrue();
        assertRequestHasCorrectCredential();
    }

    @Test
    public void updateProject_ShouldFail_WhenNoApiKeyIsProvided() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        final AtomicReference<Exception> exceptionReference = new AtomicReference<>();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final ProjectService projectService = new ProjectServiceImpl(apiClientWithoutAuthenticator, gson);
        projectService.updateProject(FAKE_PROJECT_ID, mock(NewProject.class),new RequestCallback<Response>() {
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
    public void updateProject_withObservable_ShouldFail_WhenNoApiKeyIsProvided() {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        final TestSubscriber<Response> testSubscriber = new TestSubscriber<>();
        final ProjectService projectService = new ProjectServiceImpl(apiClientWithoutAuthenticator, gson);
        projectService.updateProject(FAKE_PROJECT_ID, mock(NewProject.class)).subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);
        testSubscriber.assertError(IOException.class);
    }

    @Test
    public void updateProject_ShouldUsePut() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        mockWebServer.enqueue(new MockResponse());
        final TestSubscriber<Response> testSubscriber = new TestSubscriber<>();
        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        projectService.updateProject(FAKE_PROJECT_ID, mock(NewProject.class)).subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertThat(recordedRequest.getPath()).isEqualTo(String.format(PROJECTS_WITH_ID_URL_PATH, FAKE_PROJECT_ID));
        assertThat(recordedRequest.getMethod()).isEqualTo(HttpMethod.PUT);
    }

    @Test
    public void deleteProject_ShouldSucceed_WhenNewProjectIsPassedAndApiKeyIsCorrect() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        mockWebServer.enqueue(new MockResponse());
        final AtomicReference<Response> responseReference = new AtomicReference<>();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        projectService.deleteProject(FAKE_PROJECT_ID, new RequestCallback<Response>() {
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
        assertRequestHasCorrectCredential();
    }

    @Test
    public void deleteProject_WithObservable_ShouldSucceed_WhenNewProjectIsPassedAndApiKeyIsCorrect() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        mockWebServer.enqueue(new MockResponse());
        final TestSubscriber<Response> testSubscriber = new TestSubscriber<>();
        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        projectService.deleteProject(FAKE_PROJECT_ID).subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);
        testSubscriber.assertValueCount(1);

        final Response response = testSubscriber.getOnNextEvents().get(0);
        assertThat(response.isSuccessful()).isTrue();
        assertRequestHasCorrectCredential();
    }

    @Test
    public void deleteProject_ShouldFail_WhenNoApiKeyIsProvided() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        final AtomicReference<Exception> exceptionReference = new AtomicReference<>();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final ProjectService projectService = new ProjectServiceImpl(apiClientWithoutAuthenticator, gson);
        projectService.deleteProject(FAKE_PROJECT_ID, new RequestCallback<Response>() {
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
    public void deleteProject_withObservable_ShouldFail_WhenNoApiKeyIsProvided() {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        final TestSubscriber<Response> testSubscriber = new TestSubscriber<>();
        final ProjectService projectService = new ProjectServiceImpl(apiClientWithoutAuthenticator, gson);
        projectService.deleteProject(FAKE_PROJECT_ID).subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);
        testSubscriber.assertError(IOException.class);
    }

    @Test
    public void deleteProject_ShouldUseDelete() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        mockWebServer.enqueue(new MockResponse());
        final TestSubscriber<Response> testSubscriber = new TestSubscriber<>();
        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        projectService.deleteProject(FAKE_PROJECT_ID).subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertThat(recordedRequest.getPath()).isEqualTo(String.format(PROJECTS_WITH_ID_URL_PATH, FAKE_PROJECT_ID));
        assertThat(recordedRequest.getMethod()).isEqualTo(HttpMethod.DELETE);
    }

    @Test
    public void getAllProjects_ShouldReturnProjects_WhenApiKeyIsCorrect() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        mockWebServer.enqueue(projectsResponseMock);

        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        final AtomicReference<List<Project>> projectsReference = new AtomicReference<>();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        projectService.getAllProjects(new RequestCallback<List<Project>>() {
            @Override
            public void onGetContent(List<Project> content) {
                projectsReference.set(content);
                countDownLatch.countDown();
            }

            @Override
            public void onError(Exception e) {

            }
        });

        assertThat(countDownLatch.await(10, TimeUnit.SECONDS)).isTrue();

        final List<Project> projects = projectsReference.get();
        assertThat(projects).hasSize(2);
        assertThat(projects).usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(createExpectedProjects());
        assertRequestHasCorrectCredential();
    }

    @Test
    public void getAllProjects_WithObservable_ShouldReturnProjects_WhenApiKeyIsCorrect() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        mockWebServer.enqueue(projectsResponseMock);

        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        final TestSubscriber<List<Project>> testSubscriber = new TestSubscriber<>();
        projectService.getAllProjects().subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);
        testSubscriber.assertValueCount(1);

        final List<Project> projects = testSubscriber.getOnNextEvents().get(0);
        assertThat(projects).hasSize(2);
        assertThat(projects).usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(createExpectedProjects());
        assertRequestHasCorrectCredential();
    }

    @Test
    public void getAllProjects_ShouldFail_WhenNoApiKeyProvided() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        final AtomicReference<Exception> exceptionReference = new AtomicReference<>();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final ProjectService projectService = new ProjectServiceImpl(apiClientWithoutAuthenticator, gson);
        projectService.getAllProjects(new RequestCallback<List<Project>>() {
            @Override
            public void onGetContent(List<Project> content) {
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
    public void getAllProjects_WithObservable_ShouldFail_WhenNoApiKeyProvided() {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));

        final TestSubscriber<List<Project>> testSubscriber = new TestSubscriber<>();
        final ProjectService projectService = new ProjectServiceImpl(apiClientWithoutAuthenticator, gson);
        projectService.getAllProjects().subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);
        testSubscriber.assertError(IOException.class);
    }

    @Test
    public void getAllProjects_ShouldUseGet() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        mockWebServer.enqueue(projectsResponseMock);

        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        final TestSubscriber<List<Project>> testSubscriber = new TestSubscriber<>();
        projectService.getAllProjects().subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertThat(recordedRequest.getPath()).isEqualTo(PROJECTS_URL_PATH);
        assertThat(recordedRequest.getMethod()).isEqualTo(HttpMethod.GET);
    }

    @Test
    public void getProjects_ShouldReturnProject_WhenApiKeyIsCorrect() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        mockWebServer.enqueue(projectResponseMock);

        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        final AtomicReference<Project> projectsReference = new AtomicReference<>();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        projectService.getProject(FAKE_PROJECT_ID, new RequestCallback<Project>() {
            @Override
            public void onGetContent(Project content) {
                projectsReference.set(content);
                countDownLatch.countDown();
            }

            @Override
            public void onError(Exception e) {

            }
        });

        assertThat(countDownLatch.await(10, TimeUnit.SECONDS)).isTrue();

        final Project project = projectsReference.get();
        assertThat(project).isEqualToComparingFieldByFieldRecursively(createExpectedProject());
        assertRequestHasCorrectCredential();
    }

    @Test
    public void getProjects_WithObservable_ShouldReturnProject_WhenApiKeyIsCorrect() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        mockWebServer.enqueue(projectResponseMock);

        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        final TestSubscriber<Project> testSubscriber = new TestSubscriber<>();
        projectService.getProject(FAKE_PROJECT_ID).subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);
        testSubscriber.assertValueCount(1);

        final Project project = testSubscriber.getOnNextEvents().get(0);
        assertThat(project).isEqualToComparingFieldByFieldRecursively(createExpectedProject());
        assertRequestHasCorrectCredential();
    }

    @Test
    public void getProject_ShouldFail_WhenNoApiKeyProvided() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        final AtomicReference<Exception> exceptionReference = new AtomicReference<>();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final ProjectService projectService = new ProjectServiceImpl(apiClientWithoutAuthenticator, gson);
        projectService.getProject(FAKE_PROJECT_ID, new RequestCallback<Project>() {
            @Override
            public void onGetContent(Project content) {
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
    public void getProject_WithObservable_ShouldFail_WhenNoApiKeyProvided() {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));

        final TestSubscriber<Project> testSubscriber = new TestSubscriber<>();
        final ProjectService projectService = new ProjectServiceImpl(apiClientWithoutAuthenticator, gson);
        projectService.getProject(FAKE_PROJECT_ID).subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);
        testSubscriber.assertError(IOException.class);
    }

    @Test
    public void getProject_ShouldUseGet() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        mockWebServer.enqueue(projectsResponseMock);

        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        final TestSubscriber<Project> testSubscriber = new TestSubscriber<>();
        projectService.getProject(FAKE_PROJECT_ID).subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertThat(recordedRequest.getPath()).isEqualTo(String.format(PROJECTS_WITH_ID_URL_PATH, FAKE_PROJECT_ID) + "?includePeople=false");
        assertThat(recordedRequest.getMethod()).isEqualTo(HttpMethod.GET);
    }

    @Test
    public void getCompanyProjects_ShouldReturnProjects_WhenApiKeyIsCorrect() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        mockWebServer.enqueue(companyProjectsResponseMock);

        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        final AtomicReference<List<Project>> projectsReference = new AtomicReference<>();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        projectService.getCompanyProjects(FAKE_COMPANY_ID, new RequestCallback<List<Project>>() {
            @Override
            public void onGetContent(List<Project> content) {
                projectsReference.set(content);
                countDownLatch.countDown();
            }

            @Override
            public void onError(Exception e) {

            }
        });

        assertThat(countDownLatch.await(10, TimeUnit.SECONDS)).isTrue();

        final List<Project> projects = projectsReference.get();
        assertThat(projects).hasSize(2);
        for (final Project project : projects) {
            assertThat(project.getCompany().getId()).isEqualTo(FAKE_COMPANY_ID);
        }
        assertRequestHasCorrectCredential();
    }

    @Test
    public void getCompanyProjects_WithObservable_ShouldReturnProjects_WhenApiKeyIsCorrect() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        mockWebServer.enqueue(companyProjectsResponseMock);

        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        final TestSubscriber<List<Project>> testSubscriber = new TestSubscriber<>();
        projectService.getCompanyProjects(FAKE_COMPANY_ID).subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);
        testSubscriber.assertValueCount(1);

        final List<Project> projects = testSubscriber.getOnNextEvents().get(0);
        assertThat(projects).hasSize(2);
        for (final Project project : projects) {
            assertThat(project.getCompany().getId()).isEqualTo(FAKE_COMPANY_ID);
        }
        assertRequestHasCorrectCredential();
    }

    @Test
    public void getCompanyProjects_ShouldFail_WhenNoApiKeyProvided() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        final AtomicReference<Exception> exceptionReference = new AtomicReference<>();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final ProjectService projectService = new ProjectServiceImpl(apiClientWithoutAuthenticator, gson);
        projectService.getCompanyProjects(FAKE_COMPANY_ID, new RequestCallback<List<Project>>() {
            @Override
            public void onGetContent(List<Project> content) {
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
    public void getCompanyProjects_WithObservable_ShouldFail_WhenNoApiKeyProvided() {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));

        final TestSubscriber<List<Project>> testSubscriber = new TestSubscriber<>();
        final ProjectService projectService = new ProjectServiceImpl(apiClientWithoutAuthenticator, gson);
        projectService.getCompanyProjects(FAKE_COMPANY_ID).subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);
        testSubscriber.assertError(IOException.class);
    }

    @Test
    public void getCompanyProjects_ShouldUseGet() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        mockWebServer.enqueue(projectsResponseMock);

        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        final TestSubscriber<List<Project>> testSubscriber = new TestSubscriber<>();
        projectService.getCompanyProjects(FAKE_COMPANY_ID).subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertThat(recordedRequest.getPath()).isEqualTo(String.format(COMPANY_PROJECTS_URL_PATH, FAKE_COMPANY_ID));
        assertThat(recordedRequest.getMethod()).isEqualTo(HttpMethod.GET);
    }

    @Test
    public void getStarredProjects_ShouldReturnProjects_WhenApiKeyIsCorrect() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        mockWebServer.enqueue(projectsResponseMock);

        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        final AtomicReference<List<Project>> projectsReference = new AtomicReference<>();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        projectService.getStarredProjects(new RequestCallback<List<Project>>() {
            @Override
            public void onGetContent(List<Project> content) {
                projectsReference.set(content);
                countDownLatch.countDown();
            }

            @Override
            public void onError(Exception e) {

            }
        });

        assertThat(countDownLatch.await(10, TimeUnit.SECONDS)).isTrue();

        final List<Project> projects = projectsReference.get();
        assertThat(projects).hasSize(2);
        assertThat(projects).usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(createExpectedProjects());
        assertRequestHasCorrectCredential();
    }

    @Test
    public void getStarredProjects_WithObservable_ShouldReturnProjects_WhenApiKeyIsCorrect() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        mockWebServer.enqueue(projectsResponseMock);

        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        final TestSubscriber<List<Project>> testSubscriber = new TestSubscriber<>();
        projectService.getStarredProjects().subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);
        testSubscriber.assertValueCount(1);

        final List<Project> projects = testSubscriber.getOnNextEvents().get(0);
        assertThat(projects).hasSize(2);
        assertThat(projects).usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(createExpectedProjects());
        assertRequestHasCorrectCredential();
    }

    @Test
    public void getStarredProjects_ShouldFail_WhenNoApiKeyProvided() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        final AtomicReference<Exception> exceptionReference = new AtomicReference<>();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final ProjectService projectService = new ProjectServiceImpl(apiClientWithoutAuthenticator, gson);
        projectService.getStarredProjects(new RequestCallback<List<Project>>() {
            @Override
            public void onGetContent(List<Project> content) {
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
    public void getStarredProjects_WithObservable_ShouldFail_WhenNoApiKeyProvided() {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));

        final TestSubscriber<List<Project>> testSubscriber = new TestSubscriber<>();
        final ProjectService projectService = new ProjectServiceImpl(apiClientWithoutAuthenticator, gson);
        projectService.getStarredProjects().subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);
        testSubscriber.assertError(IOException.class);
    }

    @Test
    public void getStarredProjects_ShouldUseGet() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        mockWebServer.enqueue(projectsResponseMock);

        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        final TestSubscriber<List<Project>> testSubscriber = new TestSubscriber<>();
        projectService.getStarredProjects().subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertThat(recordedRequest.getPath()).isEqualTo(STARRED_PROJECTS_URL_PATH);
        assertThat(recordedRequest.getMethod()).isEqualTo(HttpMethod.GET);
    }

    @Test
    public void starProject_ShouldSucceed_WhenApiKeyIsCorrect() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        mockWebServer.enqueue(new MockResponse());
        final AtomicReference<Response> responseReference = new AtomicReference<>();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        projectService.starProject(FAKE_PROJECT_ID, new RequestCallback<Response>() {
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
        assertRequestHasCorrectCredential();
    }

    @Test
    public void starProject_WithObservable_ShouldSucceed_WhenApiKeyIsCorrect() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        mockWebServer.enqueue(new MockResponse());
        final TestSubscriber<Response> testSubscriber = new TestSubscriber<>();
        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        projectService.starProject(FAKE_PROJECT_ID).subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);
        testSubscriber.assertValueCount(1);

        final Response response = testSubscriber.getOnNextEvents().get(0);
        assertThat(response.isSuccessful()).isTrue();
        assertRequestHasCorrectCredential();
    }

    @Test
    public void starProject_ShouldFail_WhenNoApiKeyIsProvided() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        final AtomicReference<Exception> exceptionReference = new AtomicReference<>();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final ProjectService projectService = new ProjectServiceImpl(apiClientWithoutAuthenticator, gson);
        projectService.starProject(FAKE_PROJECT_ID, new RequestCallback<Response>() {
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
    public void starProject_withObservable_ShouldFail_WhenNoApiKeyIsProvided() {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        final TestSubscriber<Response> testSubscriber = new TestSubscriber<>();
        final ProjectService projectService = new ProjectServiceImpl(apiClientWithoutAuthenticator, gson);
        projectService.starProject(FAKE_PROJECT_ID).subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);
        testSubscriber.assertError(IOException.class);
    }

    @Test
    public void starProject_ShouldUsePut() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        mockWebServer.enqueue(new MockResponse());
        final TestSubscriber<Response> testSubscriber = new TestSubscriber<>();
        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        projectService.starProject(FAKE_PROJECT_ID).subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertThat(recordedRequest.getPath()).isEqualTo(String.format(STAR_A_PROJECT_URL_PATH, FAKE_PROJECT_ID));
        assertThat(recordedRequest.getMethod()).isEqualTo(HttpMethod.PUT);
    }

    @Test
    public void unstarProject_ShouldSucceed_WhenApiKeyIsCorrect() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        mockWebServer.enqueue(new MockResponse());
        final AtomicReference<Response> responseReference = new AtomicReference<>();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        projectService.unstarProject(FAKE_PROJECT_ID, new RequestCallback<Response>() {
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
        assertRequestHasCorrectCredential();
    }

    @Test
    public void unstarProject_WithObservable_ShouldSucceed_WhenApiKeyIsCorrect() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        mockWebServer.enqueue(new MockResponse());
        final TestSubscriber<Response> testSubscriber = new TestSubscriber<>();
        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        projectService.unstarProject(FAKE_PROJECT_ID).subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);
        testSubscriber.assertValueCount(1);

        final Response response = testSubscriber.getOnNextEvents().get(0);
        assertThat(response.isSuccessful()).isTrue();
        assertRequestHasCorrectCredential();
    }

    @Test
    public void unstarProject_ShouldFail_WhenNoApiKeyIsProvided() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        final AtomicReference<Exception> exceptionReference = new AtomicReference<>();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final ProjectService projectService = new ProjectServiceImpl(apiClientWithoutAuthenticator, gson);
        projectService.unstarProject(FAKE_PROJECT_ID, new RequestCallback<Response>() {
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
    public void unstarProject_withObservable_ShouldFail_WhenNoApiKeyIsProvided() {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        final TestSubscriber<Response> testSubscriber = new TestSubscriber<>();
        final ProjectService projectService = new ProjectServiceImpl(apiClientWithoutAuthenticator, gson);
        projectService.unstarProject(FAKE_PROJECT_ID).subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);
        testSubscriber.assertError(IOException.class);
    }

    @Test
    public void unstarProject_ShouldUsePut() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(401));
        mockWebServer.enqueue(new MockResponse());
        final TestSubscriber<Response> testSubscriber = new TestSubscriber<>();
        final ProjectService projectService = new ProjectServiceImpl(apiClient, gson);
        projectService.unstarProject(FAKE_PROJECT_ID).subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertThat(recordedRequest.getPath()).isEqualTo(String.format(UNSTAR_A_PROJECT_URL_PATH, FAKE_PROJECT_ID));
        assertThat(recordedRequest.getMethod()).isEqualTo(HttpMethod.PUT);
    }

    @Test
    public void createGetAllProjectsExecutor_ShouldHaveNoParameterInUrlPath_WhenParameterIsNull() {
        final ProjectServiceImpl projectServiceImpl = new ProjectServiceImpl(apiClient, gson);
        final ApiClient.Executor executor = projectServiceImpl.createGetAllProjectsExecutor(null);
        final String path = executor.getHttpUrl().encodedPath();

        assertThat(path).isEqualTo(PROJECTS_URL_PATH);
    }

    private List<Project> createExpectedProjects() {
        return gson.fromJson(projectsResponseMock.getBody().readUtf8(), new TypeToken<List<Project>>() {}.getType());
    }

    private Project createExpectedProject() {
        return gson.fromJson(projectResponseMock.getBody().readUtf8(), Project.class);
    }

    private static final MockResponse projectsResponseMock = new MockResponse().setBody("{\n" +
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
            "        },\n" +
            "        {\n" +
            "            \"company\": {\n" +
            "                \"name\": \"Demo 2 Company\",\n" +
            "                \"is-owner\": \"1\",\n" +
            "                \"id\": \"888\"\n" +
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
            "            \"logo\": \"http://demo1company.teamwork.com/images/logo2.jpg\",\n" +
            "            \"notifyeveryone\": false,\n" +
            "            \"id\": \"1000\",\n" +
            "            \"last-changed-on\": \"2014-03-18T11:20:49Z\",\n" +
            "            \"endDate\": \"20140313\",\n" +
            "            \"harvest-timers-enabled\":\"true\"\n" +
            "        }\n" +
            "    ]\n" +
            "}");

    private static final MockResponse companyProjectsResponseMock = new MockResponse().setBody("{\n" +
            "    \"STATUS\": \"OK\",\n" +
            "    \"projects\": [\n" +
            "        {\n" +
            "            \"company\": {\n" +
            "                \"name\": \"Test Company\",\n" +
            "                \"is-owner\": \"1\",\n" +
            "                \"id\": \"8888\"\n" +
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
            "        },\n" +
            "        {\n" +
            "            \"company\": {\n" +
            "                \"name\": \"Test Company\",\n" +
            "                \"is-owner\": \"1\",\n" +
            "                \"id\": \"8888\"\n" +
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
            "            \"logo\": \"http://demo1company.teamwork.com/images/logo2.jpg\",\n" +
            "            \"notifyeveryone\": false,\n" +
            "            \"id\": \"1000\",\n" +
            "            \"last-changed-on\": \"2014-03-18T11:20:49Z\",\n" +
            "            \"endDate\": \"20140313\",\n" +
            "            \"harvest-timers-enabled\":\"true\"\n" +
            "        }\n" +
            "    ]\n" +
            "}");

    private static final MockResponse projectResponseMock = new MockResponse().setBody("{\n" +
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
            "        \"id\": \"2323\",\n" +
            "        \"last-changed-on\": \"2014-04-01T14:29:32Z\",\n" +
            "        \"endDate\": \"\",\n" +
            "        \"harvest-timers-enabled\":\"true\"\n" +
            "    },\n" +
            "    \"STATUS\": \"OK\"\n" +
            "}");
}
