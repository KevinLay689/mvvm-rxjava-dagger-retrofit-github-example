package com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.dagger;

import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.dagger.retrofit.RetrofitComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by kevinlay on 8/13/18.
 */
@Component(modules = { DatabaseModule.class })
@Singleton
public abstract class AppComponent implements RetrofitComponent {
    // Add inject methods here
}
