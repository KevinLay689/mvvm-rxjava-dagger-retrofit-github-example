package com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example;

import android.app.Application;

import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.dagger.AppComponent;
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.dagger.DaggerAppComponent;
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.dagger.DatabaseModule;
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.dagger.retrofit.RetrofitMode;

/**
 * Created by kevinlay on 8/21/18.
 */

public class App extends Application {
    private AppComponent appComponent;
    private RetrofitMode retrofitMode;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().databaseModule(new DatabaseModule(this)).build();
        retrofitMode = new RetrofitMode(appComponent);
    }
}
