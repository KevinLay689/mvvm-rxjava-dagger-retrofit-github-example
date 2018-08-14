package com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.mvvm;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import retrofit2.Retrofit;

/**
 * Created by kevinlay on 8/13/18.
 */

public class MainActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    Retrofit retrofit;

    public MainActivityViewModelFactory (Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainActivityViewModel(retrofit);
    }
}
