# Teamwork.com Android SDK
Unofficial Teamwork.com API wrapper for Android Application

▲ ▲ ▲ This Project is Work In Progress ▲ ▲ ▲

# Usage

Initialize in your application class 

```
public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Teamwork.initialize(this);
    }
}
```

Get the suitable request object for your needs e.g. AccountRequest (Currently there are only two Request available AccountRequest and ProjectRequest)

With RxJava
```
Teamwork.accountRequest()
    .newAuthenticateRequest(apiKey)
    .observeOn(AndroidSchedulers.mainThread())
    .doOnError(this::onGetError)
    .subscribe(this::onLoginSuccess);
```
The plain old callback

```
Teamwork.accountRequest()
    .newAuthenticateRequest(apiKey, new RequestCallback<Account>() {
        @Override
        public void onGetContent(Account content) {
            this.onLoginSuccess()
        }

        @Override
        public void onError(Exception e) {
            this.onGetError()
        }
    })
```
