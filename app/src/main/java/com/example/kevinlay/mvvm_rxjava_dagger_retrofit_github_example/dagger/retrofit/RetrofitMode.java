package com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.dagger.retrofit;

/**
 * Created by kevinlay on 8/21/18.
 */

public class RetrofitMode {
    private static RetrofitComponent retrofitSubcomponent;

    public RetrofitMode(RetrofitComponent retrofitSubcomponent) {
        RetrofitMode.retrofitSubcomponent = retrofitSubcomponent;
    }

    public static RetrofitComponent getInstance() {
        return retrofitSubcomponent;
    }
}
