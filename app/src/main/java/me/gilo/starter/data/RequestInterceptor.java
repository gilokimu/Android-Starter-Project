package me.gilo.starter.data;

import me.gilo.starter.Config;
import me.gilo.starter.StarterApp;
import me.gilo.starter.utils.AppUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class RequestInterceptor implements Interceptor {

    String key = Config.API_KEY;
    String secret = Config.APP_SECRET;
    String bearer = "";

    boolean authenticated = false;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (isAuthenticated()) {
            request = request.newBuilder()
                    .addHeader("Authorization", "Bearer " + getBearer())
                    .addHeader("Content-Type", "application/json")
                    .build();
        }else {
            request = request.newBuilder()
                    .addHeader("api-key", getKey())
                    .addHeader("app-secret", getSecret())
                    .addHeader("Content-Type", "application/json")
                    .build();
        }

        return chain.proceed(request);
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getBearer() {
        return (new AppUtils(StarterApp.getContext())).getToken();
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }

    public boolean isAuthenticated() {
        return (new AppUtils(StarterApp.getContext())).isLoggedIn();
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
