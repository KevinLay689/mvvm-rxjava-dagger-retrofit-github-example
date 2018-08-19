package com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.dagger;

import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.MainActivity;

import dagger.Subcomponent;

/**
 * Created by kevinlay on 8/18/18.
 */
@Subcomponent(modules = RetrofitModule.class)
@RetrofitScope
public interface RetrofitSubcomponent {

    @Subcomponent.Builder
    interface Builder {
        Builder requestRetrofitModule(RetrofitModule retrofitModule);
        RetrofitSubcomponent build();
    }

    void inject(MainActivity mainActivity);
}
