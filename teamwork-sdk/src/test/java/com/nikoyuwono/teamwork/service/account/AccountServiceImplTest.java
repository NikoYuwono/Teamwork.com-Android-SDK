package com.nikoyuwono.teamwork.service.account;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nikoyuwono.teamwork.data.model.Account;
import com.nikoyuwono.teamwork.data.net.ApiClient;
import com.nikoyuwono.teamwork.service.RequestCallback;
import com.nikoyuwono.teamwork.service.ServiceTestUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import rx.observers.TestSubscriber;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountServiceImplTest {

    private static final String GET_ACCOUNT_DETAILS_URL_PATH = "/account.json";
    private static final String AUTHENTICATE_URL_PATH = "/authenticate.json";


    private static final Dispatcher dispatcher = new Dispatcher() {
        @Override
        public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
            if (request == null || request.getPath() == null) {
                return new MockResponse().setResponseCode(400);
            }
            switch (request.getPath()) {
                case GET_ACCOUNT_DETAILS_URL_PATH:
                case AUTHENTICATE_URL_PATH:
                    return new MockResponse().setBody("{\n"
                            + "    \"STATUS\": \"OK\",\n"
                            + "    \"account\": {\n"
                            + "        \"requirehttps\": false,\n"
                            + "        \"time-tracking-enabled\": true,\n"
                            + "        \"name\": \"Teamwork Account Name\",\n"
                            + "        \"datesignedup\": \"2013-03-05T00:00:00Z\",\n"
                            + "        \"companyname\": \"Owner Company Name\",\n"
                            + "        \"ssl-enabled\": true,\n"
                            + "        \"created-at\": \"2011-08-22T12:57:00Z\",\n"
                            + "        \"cacheUUID\": \"C14A34C3-D5AE-86A3-B9A88A5377D2CD79\",\n"
                            + "        \"account-holder-id\": \"1\",\n"
                            + "        \"logo\": \"http://www.someteamworkurl.com/images/349C6BDFA9EA4F814B6822C2F8C13A61%2Ejpg\",\n"
                            + "        \"id\": \"1\",\n"
                            + "        \"URL\": \"http://sampleaccount.teamwork.com/\",\n"
                            + "        \"email-notification-enabled\": true,\n"
                            + "        \"companyid\": \"1\",\n"
                            + "        \"lang\": \"EN\",\n"
                            + "        \"code\": \"teamworksitecode\"\n"
                            + "    }\n"
                            + "}");
                default:
                    return new MockResponse().setResponseCode(404);
            }
        }
    };

    private static Gson gson;

    private MockWebServer mockWebServer;
    private ApiClient apiClient;

    @BeforeClass
    public static void setupForAll() {
        gson = new GsonBuilder()
                .registerTypeAdapter(Account.class, new AccountDeserializer())
                .create();
    }

    @Before
    public void setup() throws IOException {
        mockWebServer = new MockWebServer();

        final HttpUrl mockUrl = mockWebServer.url("/");
        final OkHttpClient okHttpClient = ServiceTestUtil.defaultOkHttpClient();
        apiClient = new ApiClient.Builder()
                .baseUrl(mockUrl)
                .okHttpClient(okHttpClient)
                .build();
    }

    @After
    public void finish() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void getAccountDetailsSuccess() throws InterruptedException {
        mockWebServer.setDispatcher(dispatcher);
        final AtomicReference<Account> accountReference = new AtomicReference<>();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AccountService accountService = new AccountServiceImpl(apiClient, gson);
        accountService.getAccountDetails(new RequestCallback<Account>() {
            @Override
            public void onGetContent(Account content) {
                accountReference.set(content);
                countDownLatch.countDown();
            }

            @Override
            public void onError(Exception e) {

            }
        });

        assertThat(countDownLatch.await(10, TimeUnit.SECONDS)).isTrue();

        final Account account = accountReference.get();
        assertThat(account).isNotNull();
        assertThat(account.getId()).isEqualTo("1");
        assertThat(account.getCompanyName()).isEqualTo("Owner Company Name");
        assertThat(account.getCacheUUID()).isEqualTo("C14A34C3-D5AE-86A3-B9A88A5377D2CD79");

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertThat(recordedRequest.getPath()).isEqualTo(GET_ACCOUNT_DETAILS_URL_PATH);
    }

    @Test
    public void getAccountDetailsWithObservableSuccess() throws InterruptedException {
        mockWebServer.setDispatcher(dispatcher);

        final TestSubscriber<Account> testSubscriber = new TestSubscriber<>();
        final AccountService accountService = new AccountServiceImpl(apiClient, gson);
        accountService.getAccountDetails().subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);
        testSubscriber.assertValueCount(1);
        final Account account = testSubscriber.getOnNextEvents().get(0);
        assertThat(account).isEqualToComparingFieldByField(createExpectedAccount());

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertThat(recordedRequest.getPath()).isEqualTo(GET_ACCOUNT_DETAILS_URL_PATH);
    }

    @Test
    public void getAccountDetailsFailed() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(500).setBody("Something went wrong on server side!"));

        final AtomicReference<Exception> exceptionReference = new AtomicReference<>();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AccountService accountService = new AccountServiceImpl(apiClient, gson);
        accountService.getAccountDetails(new RequestCallback<Account>() {
            @Override
            public void onGetContent(Account content) {
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

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertThat(recordedRequest.getPath()).isEqualTo(GET_ACCOUNT_DETAILS_URL_PATH);
    }

    @Test
    public void getAccountDetailsWithObservableFailed() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(500).setBody("Something went wrong on server side!"));

        final TestSubscriber<Account> testSubscriber = new TestSubscriber<>();
        final AccountService accountService = new AccountServiceImpl(apiClient, gson);
        accountService.getAccountDetails().subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);
        testSubscriber.assertError(IOException.class);

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertThat(recordedRequest.getPath()).isEqualTo(GET_ACCOUNT_DETAILS_URL_PATH);
    }

    @Test
    public void authenticateSuccess() throws InterruptedException {
        mockWebServer.setDispatcher(dispatcher);
        final AtomicReference<Account> accountReference = new AtomicReference<>();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AccountService accountService = new AccountServiceImpl(apiClient, gson);
        accountService.authenticate(new RequestCallback<Account>() {
            @Override
            public void onGetContent(Account content) {
                accountReference.set(content);
                countDownLatch.countDown();
            }

            @Override
            public void onError(Exception e) {

            }
        });

        assertThat(countDownLatch.await(10, TimeUnit.SECONDS)).isTrue();

        final Account account = accountReference.get();
        assertThat(account).isNotNull();
        assertThat(account.getId()).isEqualTo("1");
        assertThat(account.getCompanyName()).isEqualTo("Owner Company Name");
        assertThat(account.getCacheUUID()).isEqualTo("C14A34C3-D5AE-86A3-B9A88A5377D2CD79");

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertThat(recordedRequest.getPath()).isEqualTo(AUTHENTICATE_URL_PATH);
    }

    @Test
    public void authenticateWithObservableSuccess() throws InterruptedException {
        mockWebServer.setDispatcher(dispatcher);

        final TestSubscriber<Account> testSubscriber = new TestSubscriber<>();
        final AccountService accountService = new AccountServiceImpl(apiClient, gson);
        accountService.authenticate().subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);
        testSubscriber.assertValueCount(1);
        final Account account = testSubscriber.getOnNextEvents().get(0);
        assertThat(account).isEqualToComparingFieldByField(createExpectedAccount());

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertThat(recordedRequest.getPath()).isEqualTo(AUTHENTICATE_URL_PATH);
    }

    @Test
    public void authenticateFailed() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(500).setBody("Something went wrong on server side!"));

        final AtomicReference<Exception> exceptionReference = new AtomicReference<>();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AccountService accountService = new AccountServiceImpl(apiClient, gson);
        accountService.authenticate(new RequestCallback<Account>() {
            @Override
            public void onGetContent(Account content) {
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

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertThat(recordedRequest.getPath()).isEqualTo(AUTHENTICATE_URL_PATH);
    }

    @Test
    public void authenticateWithObservableFailed() throws InterruptedException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(500).setBody("Something went wrong on server side!"));

        final TestSubscriber<Account> testSubscriber = new TestSubscriber<>();
        final AccountService accountService = new AccountServiceImpl(apiClient, gson);
        accountService.authenticate().subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent(10, TimeUnit.SECONDS);
        testSubscriber.assertError(IOException.class);

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertThat(recordedRequest.getPath()).isEqualTo(AUTHENTICATE_URL_PATH);
    }

    private Account createExpectedAccount() {
        return Account.newBuilder()
                .requireHttps(false)
                .timeTrackingEnabled(true)
                .name("Teamwork Account Name")
                .dateSignedUp("2013-03-05T00:00:00Z")
                .companyName("Owner Company Name")
                .sslEnabled(true)
                .createdAt("2011-08-22T12:57:00Z")
                .cacheUUID("C14A34C3-D5AE-86A3-B9A88A5377D2CD79")
                .accountHolderId("1")
                .logo("http://www.someteamworkurl.com/images/349C6BDFA9EA4F814B6822C2F8C13A61%2Ejpg")
                .id("1")
                .url("http://sampleaccount.teamwork.com/")
                .emailNotificationEnabled(true)
                .companyId("1")
                .lang("EN")
                .code("teamworksitecode")
                .build();
    }

}
