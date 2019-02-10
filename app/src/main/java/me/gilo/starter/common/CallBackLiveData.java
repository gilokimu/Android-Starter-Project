package me.gilo.starter.common;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;

import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallBackLiveData<T> extends LiveData<Resource<T>> implements Callback<T> {

    public CallBackLiveData() {
        setValue(new Resource<>(Status.LOADING));
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()){
            setValue(new Resource<>(response.body()));
        }else{
            String error = null;
            try {
                error = response.errorBody().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (error == null){
                error = "Something went wrong";
            }
            setValue(new Resource<>(new NetworkException(error)));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        setValue(new Resource<>( new NetworkException(t)));
    }
}
