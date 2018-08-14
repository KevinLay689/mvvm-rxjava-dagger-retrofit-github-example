package com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.mvvm;

import android.arch.lifecycle.ViewModel;

import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.retrofit.GithubApi;
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.retrofit.GithubRepo;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;

/**
 * Created by kevinlay on 8/13/18.
 */

public class MainActivityViewModel extends ViewModel {

    Retrofit retrofit;

    public MainActivityViewModel(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public Single<List<GithubRepo>> getGithubRepos(String user) {
        return retrofit.create(GithubApi.class).getRepos(user);
    }
}
