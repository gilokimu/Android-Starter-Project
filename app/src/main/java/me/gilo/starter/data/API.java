package me.gilo.starter.data;


import me.gilo.starter.data.body.LoginBody;
import me.gilo.starter.data.response.LoginResponse;
import me.gilo.starter.data.response.ApiResponse;
import me.gilo.starter.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface API {

    @POST("login")
    @Headers({"Content-Type:application/json"})
    Call<LoginResponse> login(
            @Body LoginBody data
    );

    @POST("register")
    @Headers({"Content-Type:application/json"})
    Call<ApiResponse> register(
            @Body User data
    );

}