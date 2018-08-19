package com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.dagger;


import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by kevinlay on 8/13/18.
 */
@Component(modules = ActivityModule.class)
@Singleton
public interface AppComponent {
    RetrofitSubcomponent.Builder retrofitComponent();
}
