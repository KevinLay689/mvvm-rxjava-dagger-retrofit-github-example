package com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.dagger;

import android.content.Context;

import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.mvvm.MainActivityViewModelFactory;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kevinlay on 8/13/18.
 */

@Module
public class RetrofitModule {

    Context context;

    public RetrofitModule(Context context) {
        this.context = context;
    }

    // TODO: Should probably move out to its own module
    @Provides
    @Singleton
    public MainActivityViewModelFactory provideMainActivityViewModelFactory(Retrofit retrofit) {
        return new MainActivityViewModelFactory(retrofit);
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(GsonConverterFactory gsonConverterFactory, RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {
        return new Retrofit.Builder()
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .baseUrl("https://api.github.com/")
                .build();
    }

    @Provides
    @Singleton
    public GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    public RxJava2CallAdapterFactory provideRxJAva2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }
}
