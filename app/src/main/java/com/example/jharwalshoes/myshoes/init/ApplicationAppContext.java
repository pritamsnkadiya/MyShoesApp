package com.example.jharwalshoes.myshoes.init;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.example.jharwalshoes.myshoes.api.ApiClient;
import com.example.jharwalshoes.myshoes.api.ApiInjectComponent;
import com.example.jharwalshoes.myshoes.api.DaggerApiInjectComponent;
import com.example.jharwalshoes.myshoes.utils.AppConstants;

public class ApplicationAppContext extends Application {
    private ApiInjectComponent networkComponent;
    private static Context APP_CONTEXT = null;

    @Override
    public void onCreate() {
        super.onCreate();
        networkComponent = DaggerApiInjectComponent.builder()
                .apiClient(new ApiClient(AppConstants.BASE_URL))
                .build();
        ApplicationAppContext.APP_CONTEXT = getApplicationContext();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public static Context getAppContext() {
        return APP_CONTEXT;
    }

    public ApiInjectComponent getNetworkComponent() {
        return networkComponent;
    }
}