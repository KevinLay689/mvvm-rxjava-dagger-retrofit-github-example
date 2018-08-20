package com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.mvvm;

import android.arch.lifecycle.ViewModel;

import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.database.Repo;
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.database.RepoDao;
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.retrofit.GithubApi;
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.retrofit.GithubRepo;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by kevinlay on 8/13/18.
 */

public class GithubFragmentViewModel extends ViewModel {

    private final Retrofit retrofit;

    private final RepoDao repoDao;

    public GithubFragmentViewModel(Retrofit retrofit, RepoDao repoDao) {
        this.retrofit = retrofit;
        this.repoDao = repoDao;
    }

    public Single<List<GithubRepo>> getGithubRepos(String user) {
        return retrofit.create(GithubApi.class).getRepos(user);
    }

    public Completable addUsersToDatabase(List<Repo> repos) {
        return Completable.fromAction(() -> repoDao
                .insertRepos(repos))
                .subscribeOn(Schedulers.io());
    }

    public Flowable<List<String>> getReposFromDatabase(String owner) {
        return repoDao.getReposByOwner(owner);
    }

    public Flowable<List<Repo>> getAllRepos() {
        return repoDao.getAllRepos();
    }
}
