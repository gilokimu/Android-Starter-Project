package me.gilo.starter.repo;

import me.gilo.starter.common.CallBackLiveData;
import me.gilo.starter.data.API;
import me.gilo.starter.data.RestAdapter;
import me.gilo.starter.data.body.LoginBody;
import me.gilo.starter.data.response.LoginResponse;
import me.gilo.starter.data.response.ApiResponse;
import me.gilo.starter.models.User;

import javax.inject.Inject;


public class UserRepository{

    @Inject
    public UserRepository() {

    }

    public CallBackLiveData<LoginResponse> login(String username, String password) {
        final CallBackLiveData<LoginResponse> callBack = new CallBackLiveData();
        API apiService = RestAdapter.createAPI();
        apiService.login(new LoginBody(username, password)).enqueue(callBack);

        return callBack;
    }
    public CallBackLiveData<ApiResponse> register(User user) {
        final CallBackLiveData<ApiResponse> callBack = new CallBackLiveData();
        API apiService = RestAdapter.createAPI();
        apiService.register(user).enqueue(callBack);

        return callBack;
    }

}
