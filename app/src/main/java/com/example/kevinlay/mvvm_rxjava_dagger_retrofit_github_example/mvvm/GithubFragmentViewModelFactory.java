package com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.mvvm;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.database.RepoDao;

import retrofit2.Retrofit;

/**
 * Created by kevinlay on 8/13/18.
 */

public class GithubFragmentViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    Retrofit retrofit;
    RepoDao repoDao;

    public GithubFragmentViewModelFactory(Retrofit retrofit, RepoDao repoDao) {
        this.retrofit = retrofit;
        this.repoDao = repoDao;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new GithubFragmentViewModel(retrofit, repoDao);
    }
}
