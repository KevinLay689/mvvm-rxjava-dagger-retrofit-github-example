package com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.retrofit;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by kevinlay on 8/13/18.
 */

public interface GithubApi {

    @GET("/users/{user}/repos")
    Single<List<GithubRepo>> getRepos(@Path("user") String user);
}
