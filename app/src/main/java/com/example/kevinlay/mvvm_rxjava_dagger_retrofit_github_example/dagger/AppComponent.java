package com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.dagger;

import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.MainActivity;

import dagger.Component;

/**
 * Created by kevinlay on 8/13/18.
 */

@Component(modules = RetrofitModule.class)
@RetrofitScope
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
