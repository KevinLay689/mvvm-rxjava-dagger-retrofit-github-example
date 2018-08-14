package com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.retrofit;

/**
 * Created by kevinlay on 8/13/18.
 */

public class GithubRepo {
    private String name;

    public GithubRepo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}