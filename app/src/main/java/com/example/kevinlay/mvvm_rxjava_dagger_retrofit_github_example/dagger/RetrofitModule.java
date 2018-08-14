package com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.dagger;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kevinlay on 8/13/18.
 */

@Module
public class RetrofitModule {

    @Provides
    @RetrofitScope
    public Retrofit provideRetrofit(GsonConverterFactory gsonConverterFactory, RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {
        return new Retrofit.Builder()
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .baseUrl("https://api.github.com/")
                .build();
    }

    @Provides
    @RetrofitScope
    public GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @RetrofitScope
    public RxJava2CallAdapterFactory provideRxJAva2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }
}
